package app.persistence.teamN;

import app.entities.teamN.FunQuote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FunQuoteMapper {

    public static List<FunQuote> getAllFunQuotes(ConnectionPool connectionPool) throws DatabaseException {
        List<FunQuote> allFunQuotes = new ArrayList<>();
        String sql = "SELECT * from funquotes";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
            ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("funquote_id");
                String setup = rs.getString("funquote_setup");
                String punchLine = rs.getString("funquote_punchline");
                FunQuote funQuote = new FunQuote(id,setup,punchLine);
                allFunQuotes.add(funQuote);
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
        return allFunQuotes;
    }

    public static FunQuote getFunQuoteById(int id, ConnectionPool connectionPool) throws DatabaseException {

        FunQuote funQuote = null;

        String sql = "SELECT * from funQuotes WHERE funquote_id=?";
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int quote_id = resultSet.getInt("funquote_id");
                String setup = resultSet.getString("funquote_setup");
                String punchLine = resultSet.getString("funquote_punchLine");
                funQuote = new FunQuote(quote_id, setup, punchLine);
            }

        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
        return funQuote;
    }
}
