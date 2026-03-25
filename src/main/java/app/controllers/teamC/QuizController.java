package app.controllers.teamC;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class QuizController {

    /*statiske metoder gør koden sværere at teste, og de passer ikke så godt ind i
     * objektorienteret tankegang. Derfor er metoderne ikke statiske, og vi skal så huske
     * at oprette en instans af controlleren i Main.*/

    public void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("startquiz", ctx -> showQuestion(ctx));
        app.get("endquiz", ctx -> endQuiz(ctx));
    }

    public void showQuestion(Context ctx) {

    }


    public void endQuiz(Context ctx) {

    }
}
