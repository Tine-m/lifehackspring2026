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
    public static void saveTimer(Timer time, ConnectionPool connectionPool){
       int userId = user.getUserId();

            String sql = "insert into score from public.\"leaderboard\" where userId=?";

            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                ps.setString(1, userId);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int id = rs.getInt("user_id");
                    String role = rs.getString("role");
                    return new User(id, userName, password, role);
                } else
                {
                    throw new app.exceptions.common.DatabaseException("Fejl i login. Prøv igen");
                }
            }
            catch (SQLException e)
            {
                throw new app.exceptions.common.DatabaseException("DB fejl", e.getMessage());

        }
    }
}
