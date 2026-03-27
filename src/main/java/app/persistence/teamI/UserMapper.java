package app.persistence.teamI;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper {

    private ConnectionPool connectionPool;
    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public boolean login(String email, String password) {
        String sql = """
                SELECT u.user_id, u.firstname, u.lastname, u.email, a.password
                FROM teami_users u
                JOIN teami_account a ON u.user_id = a.user_id
                WHERE u.email = ? AND a.password = ?
                """;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createUser(String firstname, String lastname, String email, String password) throws DatabaseException {
        String createUser = "INSERT INTO teami_users (firstname, lastname, email) VALUES (?, ?, ?)";
  String createAccount = "INSERT INTO teami_account (user_id, password) VALUES (?, ?)";
      
        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
          
            int userId;
          
            try (PreparedStatement user = connection.prepareStatement(createUser, PreparedStatement.RETURN_GENERATED_KEYS)) {
                user.setString(1, firstname);
                user.setString(2, lastname);
                user.setString(3, email);

                user.executeUpdate();
              
                ResultSet rs = user.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                   
                } else {
                    throw new DatabaseException("Kunne ikke hente user_id");

                }
            }
          
            try (PreparedStatement account = connection.prepareStatement(createAccount)) {
                account.setInt(1, userId);
                account.setString(2, password);

                account.executeUpdate();

            }
            connection.commit();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

