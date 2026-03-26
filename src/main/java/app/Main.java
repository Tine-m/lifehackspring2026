package app;

import app.config.ThymeleafConfig;
import app.controllers.MainController;
import app.controllers.teamM.SubscriptionController;
import app.controllers.teamteachers.QuoteController;
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

    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver
        Javalin javApp = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);


        // Routing
        // Frontpage - you must register your app here.
        MainController.addRoutes(javApp, connectionPool);

        // General Login - only included as example code
        app.controllers.login.UserController.addRoutes(javApp, connectionPool);

        //Philosophers app - teamteachers
        QuoteController.addRoutes(javApp, connectionPool);

        //SubStats app - Team - A
        app.controllers.teamA.UserController.addRoutes(javApp, connectionPool);
        app.controllers.teamA.SubscriptionController.addRoutes(javApp, connectionPool);

        //Trackly app - TeamM
        SubscriptionController controller = new SubscriptionController(connectionPool);
        controller.addRoutes(javApp);
    }
}