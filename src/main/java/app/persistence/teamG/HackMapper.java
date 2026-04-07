package app.persistence.teamG;

import app.entities.teamG.Hacks;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HackMapper {

    public static List<Hacks> getHacksByCategory(String kategori, ConnectionPool connectionPool) {
        List<Hacks>hackList = new ArrayList<>();
        String sql = "select * from teamg_hacks where kategori =?";

        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, kategori);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("hack_id");
                    String videoLink = rs.getString("videolink");
                    String category = rs.getString("kategori");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    hackList.add(new Hacks(id, videoLink, category, title, description));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hackList;
    }

    public static Hacks getHacksById(int id, ConnectionPool connectionPool) {
        Hacks hackById = null;
        String sql = "select * from teamg_hacks where hack_id =?";

        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int hackId = rs.getInt("hack_id");
                    String videoLink = rs.getString("videolink");
                    String category = rs.getString("kategori");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    hackById = new Hacks(hackId, videoLink, category, title, description);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hackById;
    }

    public static Hacks getRandomHack (ConnectionPool connectionPool){
        String sql = "SELECT * FROM teamg_hacks ORDER BY RANDOM() LIMIT 1";
        try(Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                int hackId = rs.getInt("hack_id");
                String videoLink = rs.getString("videolink");
                String category = rs.getString("kategori");
                String title = rs.getString("title");
                String description = rs.getString("description");
                return new Hacks(hackId, videoLink, category, title, description) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
