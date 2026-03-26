package app.persistence.teamO;

import app.entities.teamO.Movie;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static List<Movie> getAllMovies(ConnectionPool connectionPool) throws DatabaseException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT film_id, title, year FROM teamO_films ORDER BY title";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("film_id"),
                        rs.getString("title"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved hentning af film", e.getMessage());
        }
        return movies;
    }
}
