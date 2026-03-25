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

    // Hvor skal denne metode ligge? Det er ikke database. Burde ligge i en service-klasse
    public List<Question> shuffleQuestions(ConnectionPool connectionPool) throws DatabaseException {
        List<Question> shuffledQuestions;
        try {
            shuffledQuestions = new ArrayList<>(getAllQuestions(connectionPool));
        } catch (DatabaseException e) {
            throw new DatabaseException("Kunne ikke hente quiz " + e.getMessage());
        }
        return shuffledQuestions;
    }

   // Hvor skal denne metode ligge? Det er ikke database. Burde ligge i en service-klasse
   public static Question getRandomQuestion(ConnectionPool connectionPool) throws DatabaseException {

       //Et loop, der returnere et spørgsmål fra den blandede liste.
       //For-each eller while der stadig er et spørgsmål på listen.


       //check før liste er blandet
        try (Connection connection = connectionPool.getConnection()) {
            System.out.println(getAllQuestions(connectionPool));
            List<Question> questions = getAllQuestions(connectionPool);
            return questions.get(0);
            // List<Question> randomQuestions = questions. // new ArrayList<>(Collections.shuffle(getAllQuestions(connectionPool)));
            // til check at listen er blandet
            // System.out.println(getAllQuestions(connectionPool));
        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke hente spørgsmål " + e.getMessage());
        }
    }

    //Skal vi have statisk eller ej? Måske ikke
    public static List<Question> getAllQuestions(ConnectionPool connectionPool) throws DatabaseException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM teamC_quiz";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
          /*  ps.setString(1, "questionText");
            ps.setString(2, "answerCorrect");
            ps.setString(3, "answerWrong1");
            ps.setString(4, "answerWrong2");
            ps.setString(5, "imgCorrect");
            ps.setString(6, "imgWrong");
            ps.setString(7, "soundCorrect");
            ps.setString(8, "soundWrong"); */

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
