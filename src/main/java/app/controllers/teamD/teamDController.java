package app.controllers.teamD;

import app.controllers.MainController;
import app.entities.LifehackSite;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class teamDController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamD", ctx -> nemMadHomePage(ctx));
    }

    private static void nemMadHomePage(@NotNull Context ctx) {
        ctx.render("teamD/index.html");
    }

}
