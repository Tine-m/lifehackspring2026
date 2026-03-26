package app.controllers.teamI;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamI.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/aboutUs", ctx -> ctx.render("templates/teamI/AboutUs.html"));
        app.get("/coffeeBrands", ctx -> ctx.render("templates/teamI/CoffeeBrands.html"));
        app.get("/coffeeType", ctx -> ctx.render("templates/teamI/CoffeeType.html"));
        app.get("/colorPanel", ctx -> ctx.render("templates/teamI/ColorPanel.html"));
        app.get("/contactUs", ctx -> ctx.render("templates/teamI/ContactUs.html"));
        app.get("/createAccount", ctx -> ctx.render("templates/teamI/CreateAccount.html"));
        app.get("/creatingPanel", ctx -> ctx.render("templates/teamI/CreatingPanel.html"));
        app.get("/favorites", ctx -> ctx.render("templates/teamI/Favorites.html"));
        app.get("/index", ctx -> ctx.render("templates/teamI/Index.html"));
        app.get("/login", ctx -> ctx.render("templates/teamI/Login.html"));
        app.get("/options", ctx -> ctx.render("templates/teamI/Options.html"));
        
        //app.post("/index", ctx -> login(ctx, connectionPool));
        app.post("/createAccount", ctx -> createUser(ctx, connectionPool));
        app.post("/login", ctx -> login(ctx,connectionPool));



        // coffee types picture link
        app.get("/americano", ctx -> ctx.render("templates/teamI/Americano.html"));
        app.get("/cafeLatte", ctx -> ctx.render("templates/teamI/CafféLatte.html"));
        app.get("/cappuccino", ctx -> ctx.render("templates/teamI/Cappuccino.html"));
        app.get("/cortado", ctx -> ctx.render("templates/teamI/Cortado.html"));
        app.get("/espresso", ctx -> ctx.render("templates/teamI/Espresso.html"));
        app.get("/flatWhite", ctx -> ctx.render("templates/teamI/FlatWhite.html"));
        app.get("/latteMacchiato", ctx -> ctx.render("templates/teamI/LatteMacchiato.html"));
        app.get("/lungo", ctx -> ctx.render("templates/teamI/Lungo.html"));
        app.get("/macchiato", ctx -> ctx.render("templates/teamI/Macchiato.html"));
        app.get("/mocha", ctx -> ctx.render("templates/teamI/Mocha.html"));


        //brands link
        app.get("/coffeDoff", ctx -> ctx.render("templates/teamI/CoffeeDoff.html"));
        app.get("/bean", ctx -> ctx.render("templates/teamI/Bean.html"));
        app.get("/kaf", ctx -> ctx.render("templates/teamI/Kaf.html"));

    }



    public static void createUser(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String firstname = ctx.formParam("firstname");
        String lastname = ctx.formParam("lastname");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        UserMapper createAccount = new UserMapper(connectionPool);

        if (createAccount.createUser(firstname, lastname, email, password)) {
            ctx.redirect("/");
        } else {
            ctx.result("Something went wrong. Check username or password");
        }
    }

    public static void login(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        UserMapper user = new UserMapper(connectionPool);

        if (user.login(email, password)) {
            ctx.redirect("/index");
        } else {
            ctx.result("Something went wrong. Check username or password");
        }
    }

    public static void logout() {

    }
}
