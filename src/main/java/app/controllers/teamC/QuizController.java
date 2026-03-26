package app.controllers.teamC;

import app.entities.teamC.Question;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamC.QuizMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Collections;
import java.util.List;

public class QuizController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamC", ctx -> index(ctx));
        app.get("cirkusquiz", ctx -> showQuestion(ctx, connectionPool));
        app.get("/nextquestion", ctx -> nextQuestion(ctx));
        app.get("endquiz", ctx -> endQuiz(ctx));
        app.get("backtomainpage", ctx -> backToMainPage(ctx));
    }

    private static void index(Context ctx){
        ctx.render("teamC/index-quiz.html");
    }

    private static void showQuestion(Context ctx, ConnectionPool connectionPool) {
        try {
            //Hent spørgsmål via mapperen
            List<Question> questions = ctx.sessionAttribute("questions"); //Opretter en ny (tom) variabel og gemmer den i sessionen.

            //Første gang er listen tom. Derfor griber vi nu listen fra getAllQuestions() og blander den herfter og gemmer den i sessionen.
            if (questions == null || questions.isEmpty()) { //hvis listen ikke findes, eller den er tom.
                questions = QuizMapper.getAllQuestions(connectionPool);
                Collections.shuffle(questions);
                ctx.sessionAttribute("questions", questions);
            }

            //Tag det første spørgsmål fra listen.
            Question question = questions.get(0);
            ctx.attribute("question", question); //Lægger et enkelt Question-objekt ned i en pakke, som Thymeleaf kan åbne i html.
            ctx.render("teamC/cirkusquiz.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("teamC/index-quiz.html");
        }
    }

    private static void nextQuestion(Context ctx) {
        List<Question> questions = ctx.sessionAttribute("questions");
        if (questions != null && !questions.isEmpty()) {
            questions.remove(0);
            ctx.sessionAttribute("questions", questions);
        }
        ctx.redirect("/cirkusquiz");
    }

    private static void endQuiz(Context ctx) {
        ctx.render("teamC/endquiz.html");
    }

    private static void backToMainPage(Context ctx) {
        ctx.render("teamC/index-quiz.html");
    }
}