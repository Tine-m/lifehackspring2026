package app.controllers.teamA;

import app.entities.teamA.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamA.UserMapper;
import app.services.teamA.UserChecker;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamA/register", ctx -> ctx.render("teamA/create-user.html"));
        app.post("/teamA/register", ctx -> createUser(ctx, connectionPool));
        app.get("/teamA/login", ctx -> ctx.render("teamA/login.html"));
        app.post("/teamA/login", ctx -> login(ctx, connectionPool));
        app.get("/teamA/frontpage", ctx -> ctx.render("teamA/frontpage.html"));
        app.get("/teamA/logout", ctx -> logout(ctx));
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
                ctx.redirect("/teamA/frontpage");
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
            ctx.render("teamA/create-user.html");
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
            ctx.redirect("/teamA/frontpage");
        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("teamA/login.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("teamA/login");
    }


}
