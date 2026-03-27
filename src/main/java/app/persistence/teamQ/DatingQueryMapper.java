package app.persistence.teamQ;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.entities.teamQ.DatingQuery;

public class DatingQueryMapper {

    public static void addLike(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE dating_query SET like_counter = like_counter + 1 WHERE id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Like failed", e.getMessage());
        }
    }

    public static DatingQuery getQuery(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM dating_query WHERE id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new DatingQuery(
                        rs.getInt("id"),
                        rs.getString("weekly_title"),
                        rs.getString("content"),
                        rs.getInt("like_counter")
                );
            } else {
                throw new DatabaseException("Query not found");
            }

        } catch (SQLException e) {
            throw new DatabaseException("DB Error", e.getMessage());
        }
    }
}
