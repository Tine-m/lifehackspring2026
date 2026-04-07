package app.controllers.teamG;

import app.entities.teamG.Hacks;
import app.entities.teamteachers.Quote;
import app.persistence.ConnectionPool;
import app.persistence.teamG.HackMapper;
import app.persistence.teamteachers.QuoteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;


import java.util.List;

public class HackController {
    public static void addRoutes(@NotNull Javalin app, ConnectionPool connectionPool){
        app.get("/lifehackseption", ctx -> ctx.render("teamG/index.html"));
        app.get("/categories", ctx -> getHackByCategory(ctx, connectionPool));
        app.get("/chooseCategory", ctx -> ctx.render("teamG/showCategoryLifeHack"));
        app.get("/showCategory", ctx -> ctx.render("teamG/showCategory"));
        app.post("showCategory", ctx -> getHackByCategory(ctx, connectionPool));
        app.get("showRandomHack", ctx -> getRandomHack(ctx, connectionPool));
        app.get("showHack", ctx -> getHackById(ctx, connectionPool));
    }

    public static void getHackByCategory(Context ctx, ConnectionPool connectionPool) {
       String category = ctx.formParam("kategori");

       List<Hacks> hacksList = HackMapper.getHacksByCategory(category, connectionPool);
       ctx.attribute("kategori", category);
       ctx.attribute("Hacks", hacksList);

       ctx.render("teamG/showCategory");

    }

    public static void getRandomHack(Context ctx, ConnectionPool connectionPool){
        Hacks randomHack = HackMapper.getRandomHack(connectionPool);
        ctx.attribute("teamg_hacks", randomHack);

        ctx.render("teamG/showMyLifeHacks");
    }

    public static void getHackById(Context ctx, ConnectionPool connectionPool){
        int id = Integer.parseInt(ctx.queryParam("id"));
        Hacks hack = HackMapper.getHacksById(id, connectionPool);

        ctx.attribute("teamg_hacks", hack);
        ctx.render("teamG/showMyLifeHacks");
    }


}
