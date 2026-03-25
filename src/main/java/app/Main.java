package app;

import app.config.ThymeleafConfig;
import app.controllers.MainController;
import app.controllers.login.UserController;
import app.controllers.teamB.TeamBQuoteController;
import app.controllers.teamteachers.QuoteController;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "Lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);

        MainController.addRoutes(app, connectionPool);
        UserController.addRoutes(app, connectionPool);
        QuoteController.addRoutes(app, connectionPool);

        // Team B routes
        TeamBQuoteController.addRoutes(app, connectionPool);
    }
}