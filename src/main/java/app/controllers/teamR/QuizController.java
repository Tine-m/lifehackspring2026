package app.controllers.teamR;

import app.entities.teamR.Question;
import app.persistence.ConnectionPool;
import app.persistence.teamR.QuestionMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class QuizController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamR", ctx -> ctx.render("teamR/start.html"));
        app.get("/{set}/{number}", ctx -> showQuestion(ctx, connectionPool));
        app.post("/quiz/check", ctx -> checkAnswer(ctx,  connectionPool));
        app.get("/public", ctx -> {ctx.render("index.html");});

    }

    public static void showQuestion(Context ctx, ConnectionPool connectionPool) {

        String set = ctx.pathParam("set");
        int questionNumber = Integer.parseInt(ctx.pathParam("number"));

        if (questionNumber == 1) {
            ctx.sessionAttribute("score", 0);
        }
        Question question = QuestionMapper.getQuestion(connectionPool, questionNumber, set);

        ctx.attribute("question", question);
        ctx.attribute("questionNumber", questionNumber);
        ctx.attribute("set", set);

        ctx.render("teamR/quiz.html");
    }

    public static void checkAnswer(Context ctx, ConnectionPool connectionPool) {

        int questionNumber = Integer.parseInt(ctx.formParam("questionNumber"));
        String set = ctx.formParam("set");
        String answer = ctx.formParam("answer");

        Question question = QuestionMapper.getQuestion(connectionPool, questionNumber, set);

        boolean correct = answer.equals(question.getCorrect());

        Integer score = ctx.sessionAttribute("score");

        if (score == null) {
            score = 0;
        }

        if (correct) {
            score++;
        }

        ctx.sessionAttribute("score", score);


        int nextQuestion = questionNumber + 1;
        Question next = QuestionMapper.getQuestion(connectionPool, nextQuestion, set);

        if (next == null) {
            ctx.attribute("score", score);
            ctx.attribute("maxScore", 10);
            ctx.render("teamR/score.html");
            return;
        }

        ctx.attribute("score", score);
        ctx.attribute("correct", correct);
        ctx.attribute("nextQuestion", questionNumber + 1);
        ctx.attribute("set", set);

        ctx.render("teamR/result.html");
    }
}
