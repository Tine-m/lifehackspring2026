package app.persistence;

import app.entities.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO {

    public static Question getQuestion(ConnectionPool connectionPool, int questionNumber, String setName) {

        String sql = "SELECT * FROM questions WHERE question_number = ? AND set_name = ?";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, questionNumber);
            ps.setString(2, setName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct"),
                        rs.getInt("difficulty"),
                        rs.getInt("question_number"),
                        rs.getString("set_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
