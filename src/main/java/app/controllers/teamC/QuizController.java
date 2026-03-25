package app.controllers.teamC;

import app.entities.teamC.Question;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamC.QuizMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class QuizController {

    QuizMapper quizMapper = new QuizMapper();
    ConnectionPool connectionPool;

    public QuizController(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /*statiske metoder gør koden sværere at teste, og de passer ikke så godt ind i
     * objektorienteret tankegang. Derfor er metoderne ikke statiske, og vi skal så huske
     * at oprette en instans af controlleren i Main.*/

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamC", ctx -> index(ctx));
        app.get("startquiz", ctx -> showQuestion(ctx));
        app.get("endquiz", ctx -> endQuiz(ctx));
    }

    private static void index(Context ctx){
        ctx.render("teamC/index-quiz.html");
    }

    public static void showQuestion(Context ctx)
    {
        try{
            Question question = QuizMapper.getRandomQuestion(ConnectionPool.getInstance());
            ctx.attribute("question", question);

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("teamC/index.html");
        }

    }


    public static void endQuiz(Context ctx) {

    }
}