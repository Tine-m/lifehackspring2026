    package app.controllers.teamQ;

    import app.entities.login.User;
    import app.persistence.ConnectionPool;
    import app.persistence.teamQ.PlantMapper;
    import io.javalin.Javalin;
    import io.javalin.http.Context;
    import org.jetbrains.annotations.NotNull;

    public class PlantController {

        public static void addRoutes (Javalin app, ConnectionPool connectionPool) {
            app.get("/plantGuide", ctx -> plantHome(ctx));
            app.get("/plantSelect", ctx -> findPlants(ctx, connectionPool));
            app.post("/addPlant", ctx -> addPlants(ctx,connectionPool));


        }

        private static void addPlants(Context ctx, ConnectionPool connectionPool) {
            int plantID = Integer.parseInt(ctx.pathParam("id"));
            User currentUser = ctx.sessionAttribute("currentUser");

            if (currentUser == null) {
                ctx.redirect("/main-page");
                return;
            }

            String username = currentUser.getUserName();
            String password = currentUser.getPassword();

            try {
                PlantMapper.addPlantToUser(username, password, connectionPool);
            } catch (app.exceptions.common.DatabaseException e) {
                ctx.attribute("message", e.getMessage());

            }
        }

        private static void plantHome(@NotNull Context ctx) {
            ctx.render("/teamQ/index.html");
        }

        public static void findPlants(Context ctx, ConnectionPool connectionPool) {

        }
    }
