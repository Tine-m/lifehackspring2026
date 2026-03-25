package app.controllers.teamG;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class HackController {
    public static void addRoutes(@NotNull Javalin app, ConnectionPool connectionPool){
        app.get("/lifehackseption", ctx -> ctx.render("teamG/index.html"));

    }


}
