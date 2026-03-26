package app.controllers.teamS;

import app.entities.teamS.Timer;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public class TimerController {
    public static void addRoutes(Javalin app) {
        app.get("/timer", ctx -> timerHome(ctx));
        app.post("/timer", ctx -> handleTimer(ctx));
    }

    private static void handleTimer(Context ctx) {
        Timer request = ctx.bodyAsClass(Timer.class);
        System.out.println("Tid: " + request.getTime());
    }
    private static void timerHome(@NotNull Context ctx) {
        ctx.render("teamS/index.html");
    }

}