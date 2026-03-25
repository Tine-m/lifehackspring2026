package app.services;


import app.entities.teamR.Database;
import app.entities.teamR.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class QuestionService {

    public static Optional<Question> getQuestion(String difficulty, int id) {
        String sql = "SELECT * FROM questions WHERE difficulty = ? AND id = ?";

        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, difficulty);
            stmt.setInt(2, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Question q = new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct"),
                        rs.getString("difficulty")
                );
                return Optional.of(q);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}