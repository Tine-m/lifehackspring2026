package app.controllers.teamL;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class UserController {
    public static void addRouts(Javalin app) {
        app.get("teamL", ctx -> ctx.render("teamL/index.html"));
    }
}
