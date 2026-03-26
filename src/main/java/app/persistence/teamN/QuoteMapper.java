package app.persistence.teamN;

import app.entities.teamN.Quote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuoteMapper {

    public static List<Quote> getAllQuotes(ConnectionPool connectionPool) throws DatabaseException {

        List<Quote> quoteList = new ArrayList<>();

        String sql = "select * from public.\"quotes\"";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String quote = rs.getString("quote");
                int year = rs.getInt("year");
                quoteList.add(new Quote(id, name, quote, year));
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
        return quoteList;
    }

    public static Quote getQuoteById(int id, ConnectionPool connectionPool) throws DatabaseException {

        Quote q = null;

        String sql = "select * from public.\"quotes\" " +
                "where id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                String quote = rs.getString("quote");
                int year = rs.getInt("year");
                q = new Quote(id, name, quote, year);
            } else {
                throw new DatabaseException("Intet citat med dette ID fundet");
            }
        } catch (SQLException e) {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
        return q;
    }
}