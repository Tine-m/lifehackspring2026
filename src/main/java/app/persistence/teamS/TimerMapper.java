package app.persistence.teamS;

import app.entities.login.User;
import app.entities.teamS.Timer;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TimeMapper {
    static User user;

    public static void saveTimer(Timer timer, User user, ConnectionPool connectionPool) {
        int userId = user.getUserId();
        double time = timer.getTime();

        String sql = "INSERT INTO leaderboard (user_id, time) VALUES (?, ?);";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.setDouble(2, time);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getPlayers(User userId, ConnectionPool connectionPool) throws SQLException {


        String sql = "select leaderboard where user_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, userId);
            ps.setDouble(2, time);
        }
    }
}


