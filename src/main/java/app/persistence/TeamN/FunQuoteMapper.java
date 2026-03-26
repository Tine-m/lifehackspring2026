package app.persistence.TeamN;

import app.entities.TeamN.FunQuote;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FunQuoteMapper {

    public static List<FunQuote> getAllFunQuotes(ConnectionPool connectionPool) throws DatabaseException {
        List<FunQuote> allFunQuotes = new ArrayList<>();
        String sql = "SELECT * from funquotes";
        try {
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("funquote_id");
                String setup = rs.getString("funquote_setup");
                String punchLine = rs.getString("funquote_punchLine");
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

        String sql = "SELECT * from funQuotes WHERE ? = funquote_id";
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String setup = resultSet.getString("funquote_setup");
            String punchLine = resultSet.getString("funquote_punchLine");
            return new FunQuote(id, setup, punchLine);

        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
    }
}
