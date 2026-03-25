package app.persistence.teamK;

import app.entities.teamK.Word;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordMapper {

    ConnectionPool connectionPool;

    public WordMapper (ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    public List<Word> generateWordList(){
        List<Word> wordList = new ArrayList<>();

        String sql = "SELECT * from teamk_words";
        try (Connection cp = connectionPool.getConnection();
             PreparedStatement preparedStatement = cp.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()){

            while (rs.next()){

                int id = rs.getInt("id");
                String wordName = rs.getString("word");
                String hint = rs.getString("hint");
                String category = rs.getString("category");
                int length = rs.getInt("word_length");

                Word word = new Word(id, wordName, hint, category, length);
                wordList.add(word);
            }
            return wordList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
