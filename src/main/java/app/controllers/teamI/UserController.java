package app.controllers.teamI;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamI.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/teamI/aboutUs", ctx -> ctx.render("/teamI/AboutUs.html"));
        app.get("/teamI/coffeeBrands", ctx -> ctx.render("/teamI/CoffeeBrands.html"));
        app.get("/teamI/coffeeType", ctx -> ctx.render("/teamI/CoffeeType.html"));
        app.get("/teamI/colorPanel", ctx -> ctx.render("/teamI/ColorPanel.html"));
        app.get("/teamI/contactUs", ctx -> ctx.render("/teamI/ContactUs.html"));
        app.get("/teamI/createAccount", ctx -> ctx.render("/teamI/CreateAccount.html"));
        app.get("/teamI/creatingPanel", ctx -> ctx.render("/teamI/CreatingPanel.html"));
        app.get("/teamI/favorites", ctx -> ctx.render("/teamI/Favorites.html"));
        app.get("/teamI/index", ctx -> ctx.render("teamI/Index.html"));
        app.get("/teamI/login", ctx -> ctx.render("/teamI/Login.html"));
        app.get("/teamI/options", ctx -> ctx.render("/teamI/Options.html"));
        
        //app.post("/index", ctx -> login(ctx, connectionPool));
        app.post("/teamI/createAccount", ctx -> createUser(ctx, connectionPool));
        app.post("/teamI/login", ctx -> login(ctx,connectionPool));



        // coffee types picture link
        app.get("/teamI/americano", ctx -> ctx.render("/teamI/Americano.html"));
        app.get("/teamI/cafeLatte", ctx -> ctx.render("/teamI/CafféLatte.html"));
        app.get("/teamI/cappuccino", ctx -> ctx.render("/teamI/Cappuccino.html"));
        app.get("/teamI/cortado", ctx -> ctx.render("/teamI/Cortado.html"));
        app.get("/teamI/espresso", ctx -> ctx.render("/teamI/Espresso.html"));
        app.get("/teamI/flatWhite", ctx -> ctx.render("/teamI/FlatWhite.html"));
        app.get("/teamI/latteMacchiato", ctx -> ctx.render("/teamI/LatteMacchiato.html"));
        app.get("/teamI/lungo", ctx -> ctx.render("/teamI/Lungo.html"));
        app.get("/teamI/macchiato", ctx -> ctx.render("/teamI/Macchiato.html"));
        app.get("/teamI/mocha", ctx -> ctx.render("/teamI/Mocha.html"));


        //brands link
        app.get("/teamI/coffeDoff", ctx -> ctx.render("/teamI/CoffeeDoff.html"));
        app.get("/teamI/bean", ctx -> ctx.render("/teamI/Bean.html"));
        app.get("/teamI/kaf", ctx -> ctx.render("/teamI/Kaf.html"));

    }



    public static void createUser(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String firstname = ctx.formParam("firstname");
        String lastname = ctx.formParam("lastname");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        UserMapper createAccount = new UserMapper(connectionPool);

        if (createAccount.createUser(firstname, lastname, email, password)) {
            ctx.redirect("/teamI/index");
        } else {
            ctx.result("Something went wrong. Check username or password");
        }
    }

    public static void login(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        UserMapper user = new UserMapper(connectionPool);

        if (user.login(email, password)) {
            ctx.redirect("/teamI/index");
        } else {
            ctx.result("Something went wrong. Check username or password");
        }
    }

    public static void logout() {

    }
}
