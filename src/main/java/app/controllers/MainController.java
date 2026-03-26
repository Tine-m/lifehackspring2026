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
        lifehackSites.add(new LifehackSite("Filosoffernes hjørne", "Få et råd", "/philosophers", "images/teamteachers/socrates.jpg"));

        //teamA, SubStats
       // lifehackSites.add(new LifehackSite("SubStats", "Få styr på dine abonnementer", "teamR/login", "images/teamA/substats.jpg"));

        //TeamR
        lifehackSites.add(new LifehackSite("Be A Millionare - Quiz", "Open me!", "/start", "images/main-page/GetRich.jpg"));




        ctx.attribute("lifehackSites", lifehackSites);
        ctx.render("index.html");
    }
}
