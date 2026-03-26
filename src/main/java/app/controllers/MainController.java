package app.controllers;

import app.entities.LifehackSite;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/", ctx -> index(ctx));
    }

    private static void index(@NotNull Context ctx) {
        List<LifehackSite> lifehackSites = new ArrayList<>();
        //teamteachers
        lifehackSites.add(new LifehackSite("Filosoffernes hjørne", "Få et råd", "teamteachers/philosophers", "images/teamteachers/socrates.jpg"));

        //teamA, SubStats
        lifehackSites.add(new LifehackSite("SubStats", "Få styr på dine abonnementer", "teamA/login", "images/teamA/substats.jpg"));

        //Team O, RandomMovies
        lifehackSites.add(new LifehackSite("RandomMovies", "Find en tilfældig film", "teamO/index", "images/teamO/movie.png"));

        //Team N, countdown
        lifehackSites.add(new LifehackSite("Countdown", "Countdown til eksamen", "teamN/index", "images/teamN/cockclock.jpg"));

        ctx.attribute("lifehackSites", lifehackSites);
        ctx.render("index.html");
    }
}
