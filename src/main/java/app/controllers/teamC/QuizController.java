package app.controllers.teamC;

import app.entities.teamC.Question;
import app.exceptions.common.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamC.QuizMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class QuizController {

    QuizMapper quizMapper = new QuizMapper();
    ConnectionPool connectionPool;

    public QuizController(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /*statiske metoder gør koden sværere at teste, og de passer ikke så godt ind i
     * objektorienteret tankegang. Derfor er metoderne ikke statiske, og vi skal så huske
     * at oprette en instans af controlleren i Main.*/

    public void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/startquiz", ctx -> showQuestion(ctx));
        app.get("/endquiz", ctx -> endQuiz(ctx));
    }

    public Question showQuestion(Context ctx) {
        try {
            Question question = quizMapper.getRandomQuestion(connectionPool);

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("teamC/index.html");
        }
        return null;
    }


    public void endQuiz(Context ctx) {

    }
}