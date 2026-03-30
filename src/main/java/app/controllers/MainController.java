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
         lifehackSites.add(new LifehackSite("Filosoffernes hjørne", "Få et råd", "/teamteachers/philosophers", "images/teamteachers/socrates.jpg"));
        lifehackSites.add(new LifehackSite("Cirkusquizzen", "Tag en quiz", "/teamC", "images/teamC/media/cirkus.jpg"));
        //teamA, SubStats
        lifehackSites.add(new LifehackSite("SubStats", "Få styr på dine abonnementer", "teamA/login", "images/teamA/substats.png"));

        //Team O, RandomMovies
        lifehackSites.add(new LifehackSite("RandomMovies", "Find en tilfældig film", "teamO/index", "images/teamO/movie.png"));

        //TeamM, Trackly
        lifehackSites.add(new LifehackSite("Trackly", "Track your subscriptions", "teamM", "images/teamM/image.png"));

        ctx.attribute("lifehackSites", lifehackSites);
        ctx.render("index.html");

        // Team G
        lifehackSites.add(new LifehackSite("Lifehackseption", "Quick hacks?","/lifehackseption", "images/teamG/lifehackseption.png"));

        lifehackSites.add(new LifehackSite("Nem Mad", "Find Opskrifter", "/teamD", "images/teamD/cookbook.png"));

        ctx.attribute("lifehackSites", lifehackSites);
        ctx.render("index.html");
    }
}
