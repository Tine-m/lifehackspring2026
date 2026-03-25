package app.persistence.teamC;

import app.entities.teamC.Question;
import app.persistence.ConnectionPool;
import app.exceptions.common.DatabaseException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizMapper {

    /*Måske skal metoderne ikke være void...
    Metoderne er ikke statiske, så vi skal huske at oprette en instans af
    mapperen i vores controller.
     */

    public void getRandomQuestion(List<Question> questions) {
        //check før liste er blandet
        System.out.println(questions);
        Collections.shuffle(questions);
        //til check at listen er blandet
        System.out.println(questions);
        /*
        Hvor skal denne metode ligge? Det er ikke database
        Bruger getAllQuestions()
         */
    }

    //Skal vi have statisk eller ej? Måske ikke
    public static List<Question> getAllQuestions(ConnectionPool connectionPool) throws DatabaseException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM teamC_quiz";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "questionText");
            ps.setString(2, "answerCorrect");
            ps.setString(3, "answerWrong1");
            ps.setString(4, "answerWrong2");
            ps.setString(5, "imgCorrect");
            ps.setString(6, "imgWrong");
            ps.setString(7, "soundCorrect");
            ps.setString(8, "soundWrong");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String questionText = rs.getString("questionText");
                String answerCorrect = rs.getString("answerCorrect");
                String answerWrong1 = rs.getString("answerWrong1");
                String answerWrong2 = rs.getString("answerWrong2");
                String imgCorrect = rs.getString("imgCorrect");
                String imgWrong = rs.getString("imgWrong");
                String soundCorrect = rs.getString("soundCorrect");
                String soundWrong = rs.getString("soundWrong");
                questions.add(new Question(questionText, answerCorrect, answerWrong1, answerWrong2, imgCorrect,
                        imgWrong, soundCorrect, soundWrong));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke hente spørgsmål: " + e.getMessage());
        }
        return questions;

    }
}
