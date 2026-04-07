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
        String sql = "select user_id, username, password from teame_users where username=? and password=?";

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
                throw new DatabaseException("Kunne ikke logge ind, prøv igen.");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Database fejl", e.getMessage());
        }
    }

    public static void createUser(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "insert into teame_users (username, password) values (?,?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Kunne ikke oprette ny bruger.");
            }
        }
        catch (SQLException e) {
            String msg = "Der skete en fejl.";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Brugernavn allerede i brug, vælg et andet.";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }
}
