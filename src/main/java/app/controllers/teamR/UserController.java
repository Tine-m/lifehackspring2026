package app.controllers.teamR;

import app.entities.teamR.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamR.UserMapper;
import app.services.teamR.UserChecker;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamR/register", ctx -> ctx.render("teamA/create-user.html"));
        app.post("/teamR/register", ctx -> createUser(ctx, connectionPool));
        app.get("/teamR/login", ctx -> ctx.render("teamA/login.html"));
        app.post("/teamR/login", ctx -> login(ctx, connectionPool));
        app.get("/teamR/frontpage", ctx -> ctx.render("teamA/frontpage.html"));
        app.get("/teamR/logout", ctx -> logout(ctx));

        app.get("/teamR",ctx -> ctx.render("teamR/start.html"));

    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String passwordCheck = ctx.formParam("password-check");
        System.out.println("hej");
        List<String> messages = UserChecker.validate(username, password, passwordCheck);
        if (messages.isEmpty()){
            try {
                UserMapper.createuser(username, password, connectionPool);
                ctx.redirect("/teamR/frontpage");
            } catch (DatabaseException e) {
                ctx.attribute("msg", e.getMessage());
                ctx.render("/teamA/create-user.html");
                System.out.println("1. catch");
            }
        } else {
            String message = "| ";
            for (String errorMsg : messages) {
                if (errorMsg != null && message.length()<95) {
                    message = message + errorMsg + " | ";
                }
            }
            ctx.attribute("errorMessage", message);
            ctx.render("teamR/create-user.html");
            System.out.println("2. catch");
            return;
        }
        //ctx.redirect("/login");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            // test data - simulerer kald til DB via mapper
            ctx.redirect("/teamR/frontpage");
        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("teamR/login.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("teamR/login");
    }


}
