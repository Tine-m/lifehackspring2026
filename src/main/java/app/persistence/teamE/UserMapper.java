package app.persistence.teamE;

import app.entities.teamE.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper
{

    public static User login(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "select user_id, username, password from teamE_users where username=? and password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String dbPassword = rs.getString("password");
                return new User(id, username, dbPassword);
            } else
            {
                throw new DatabaseException("Failed to log in, try again.");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Database error", e.getMessage());
        }
    }

    public static void createUser(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "insert into teamE_users (username, password) values (?,?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Failed to create new user.");
            }
        }
        catch (SQLException e) {
            String msg = "An error occurred try again.";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Username already in use, pick another.";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }
}
