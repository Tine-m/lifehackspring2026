package app.persistence.TeamN;

import app.entities.TeamN.FunQuote;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FunQuoteMapper {

    public static List<FunQuote> getAllFunQuotes(ConnectionPool connectionPool) throws DatabaseException {
        List<FunQuote> allFunQuotes = new ArrayList<>();
        String sql = "SELECT * from funQuotes";
        try {
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("funQuote_id");
                String text = rs.getString("funQuote_text");
                String image = rs.getString("funQuote_image");
                FunQuote funQuote = new FunQuote(id,text,image);
                allFunQuotes.add(funQuote);
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
        return allFunQuotes;
    }
}
