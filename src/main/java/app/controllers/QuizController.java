package app.controllers;

import app.entities.Question;
import app.persistence.ConnectionPool;
import app.persistence.QuestionDAO;
import io.javalin.http.Context;

public class QuizController {

    public static void showQuestion(Context ctx, ConnectionPool connectionPool) {

        String set = ctx.pathParam("set");
        int questionNumber = Integer.parseInt(ctx.pathParam("number"));

        Question question = QuestionDAO.getQuestion(connectionPool, questionNumber, set);

        ctx.attribute("question", question);
        ctx.attribute("questionNumber", questionNumber);
        ctx.attribute("set", set);

        ctx.render("teamR/quiz.html");
    }

    public static void checkAnswer(Context ctx, ConnectionPool connectionPool) {

        int questionNumber = Integer.parseInt(ctx.formParam("questionNumber"));
        String set = ctx.formParam("set");
        String answer = ctx.formParam("answer");

        Question question = QuestionDAO.getQuestion(connectionPool, questionNumber, set);

        boolean correct = answer.equals(question.getCorrect());

        ctx.attribute("correct", correct);
        ctx.attribute("nextQuestion", questionNumber + 1);
        ctx.attribute("set", set);

        ctx.render("teamR/result.html");
    }
}
