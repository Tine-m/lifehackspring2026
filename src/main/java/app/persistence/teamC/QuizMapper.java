package app.persistence.teamC;

import app.entities.teamC.Question;
import app.persistence.ConnectionPool;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizMapper {

    public static List<Question> getAllQuestions(ConnectionPool connectionPool) throws DatabaseException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM teamC_quiz";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String questionText = rs.getString("question_text");
                String answerCorrect = rs.getString("answer_correct");
                String answerWrong1 = rs.getString("answer_wrong1");
                String answerWrong2 = rs.getString("answer_wrong2");
                String imgCorrect = rs.getString("img_correct");
                String imgWrong = rs.getString("img_wrong");
                String soundCorrect = rs.getString("sound_correct");
                String soundWrong = rs.getString("sound_wrong");
                questions.add(new Question(questionText, answerCorrect, answerWrong1, answerWrong2, imgCorrect,
                        imgWrong, soundCorrect, soundWrong));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke hente spørgsmål: " + e.getMessage());
        }
        return questions;
    }
}
