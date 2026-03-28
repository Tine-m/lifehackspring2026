package app.persistence.teamS;

import app.entities.login.User;
import app.entities.teamS.Player;
import app.entities.teamS.Timer;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TimeMapper {

    public static void saveTimer(Timer timer, User user, ConnectionPool connectionPool) {
        int userId = user.getUserId();
        double time = timer.getTime();

        String sql = "INSERT INTO leaderboard (user_id, time) VALUES (?, ?)";

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

    public ArrayList<Player> getLeaderboard(ConnectionPool connectionPool) throws SQLException {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        String sql = "SELECT u.username, l.time "+
                "FROM leaderboard l " +
                "JOIN users u ON l.user_id = u.user_id "+
                "ORDER BY ABS(l.time - 10) ASC";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                String username = rs.getString("username");
                double time = rs.getDouble("time");
                Player player = new Player(username, time);
                playerArrayList.add(player);
            }
        } catch (Exception e) {
            throw e;
        }
        return playerArrayList;
    }
}


