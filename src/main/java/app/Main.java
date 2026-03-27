package app;

import app.config.ThymeleafConfig;
import app.controllers.MainController;
import app.controllers.login.UserController;
import app.controllers.teamB.TeamBQuoteController;
import app.controllers.teamG.HackController;
import app.controllers.teamC.QuizController;
import app.controllers.teamO.TeamOController;
import app.controllers.teamteachers.QuoteController;
import app.controllers.login.UserController;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
public class Main
{

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);


        // Routing
        // Frontpage - you muest register your app here.
        MainController.addRoutes(app, connectionPool);

        // Team O
        TeamOController.addRoutes(app, connectionPool);

        // General Login - only included as example code
        UserController.addRoutes(app, connectionPool);

        //Philosophers app - teamteachers
        QuoteController.addRoutes(app, connectionPool);

        // Team C
        QuizController.addRoutes(app, connectionPool);

        // teamG:
        HackController.addRoutes(app, connectionPool);

        // teamB:
        TeamBQuoteController.addRoutes(app, connectionPool);

    }
}