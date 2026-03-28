package app.controllers.teamS;

import app.entities.login.User;
import app.entities.teamS.Player;
import app.entities.teamS.Timer;
import app.persistence.ConnectionPool;
import app.persistence.teamS.TimeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TimerController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/leaderboard", ctx -> showLeaderboard(ctx, connectionPool));
        app.get("/timer", ctx -> timerHome(ctx));
        app.post("/timer", ctx -> handleTimer(ctx, connectionPool));

    }

    private static void handleTimer(Context ctx, ConnectionPool connectionPool) {
        try {
            Timer request = ctx.bodyAsClass(Timer.class);
            User user = (User) ctx.sessionAttribute("user"); // antag at brugeren er logget ind
            System.out.println("Tid: " + request.getTime());

            TimeMapper timeMapper = new TimeMapper();
            timeMapper.saveTimer(request, user, connectionPool);//Gemmer tiden i databasen

            ArrayList<Player> leaderboard = timeMapper.getLeaderboard(connectionPool); //Henter leaderboard fra databasen
            //Sætter leaderboard som attributter til HTML
            ctx.attribute("leaderboard", leaderboard);
            ctx.render("teamS/index.html");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void showLeaderboard(Context ctx, ConnectionPool connectionPool) {
        try {
            TimeMapper timeMapper = new TimeMapper();
            ArrayList<Player> leaderboard = timeMapper.getLeaderboard(connectionPool);
            ctx.attribute("leaderboard", leaderboard);
            ctx.render("teamS/index.html");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Kunne ikke hente leaderboard");
        }
    }
    private static void timerHome(@NotNull Context ctx) {
        ctx.render("teamS/index.html");
    }

}