package app.persistence.teamG;

import app.entities.teamG.User;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper {

    public static User login(String username, String password, ConnectionPool connectionPool) throws app.exceptions.common.DatabaseException {
        String sql = "select * from users where username=? and password=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    return new User(id, username, password);
                } else {
                    throw new DatabaseException("Fejl i login. Prøv igen");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createuser(String name, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (username, password) values (?, ?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, password);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("Fejl ved oprettelse af bruger");
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage() + "Det indtastede brugernavn eksistere allerede. Prøv en anden brugernavn");
        }
    }
    public static void getSavedLifeHacks(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT hack.title" +
                "FROM hack" +
                "INNER JOIN saved ON hack.hack_id=saved.hack_id WHERE user_id = (?);";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1) {
                    throw new DatabaseException("Fejl ved oprettelse af bruger");
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage() + "Det indtastede brugernavn eksistere allerede. Prøv en anden brugernavn");
        }
    }

}
