package app.controllers.teamQ;

import app.entities.teamQ.DatingQuery;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamQ.DatingQueryMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class DatingQueryController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamQ/", ctx -> home(ctx, connectionPool));
        app.get("/teamQ/home", ctx -> home(ctx, connectionPool));
        app.post("/like/{id}", ctx -> addLike(ctx, connectionPool));
    }

    private static void home(Context ctx, ConnectionPool connectionPool) {
        try {
            DatingQuery datingQuery = DatingQueryMapper.getQuery(1, connectionPool);
            ctx.attribute("likeCounter", datingQuery.getLike_counter());
            ctx.render("teamQ/index.html");
        } catch (DatabaseException e) {
            ctx.status(500).result("Error: " + e.getMessage());
        }
    }

    public static void addLike(Context ctx, ConnectionPool connectionPool) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            DatingQueryMapper.addLike(id, connectionPool);

            DatingQuery datingQuery = DatingQueryMapper.getQuery(id, connectionPool);

            ctx.result(String.valueOf(datingQuery.getLike_counter()));

        } catch (DatabaseException e) {
            ctx.status(500).result("Error: " + e.getMessage());
        }
    }
}