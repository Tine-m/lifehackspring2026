package app.controllers.teamC;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class QuizController {

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

    public static void showQuestion(Context ctx) {

    }


    public static void endQuiz(Context ctx) {

    }
}
