package app.controllers.teamE;

import app.entities.teamE.User;
import app.entities.teamE.Recipe;
import app.persistence.ConnectionPool;
import app.persistence.teamE.RecipeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class RecipeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/teamE/index", ctx -> ctx.render("teamE/index.html"));

        app.get("/teamE/hacks", ctx -> ctx.render("teamE/hacks.html"));

        app.get("/teamE/favorites", ctx -> ctx.render("teamE/favorites.html"));

        app.get("/api/recipes/{categoryId}", ctx -> getRecipesByCategory(ctx, connectionPool));

        app.post("/teamE/saveRecipe",
                ctx -> saveRecipe(ctx, connectionPool));

        app.post("/teamE/removeRecipe",
                ctx -> removeRecipe(ctx, connectionPool));
    }

    public static void getRecipesByCategory(Context ctx, ConnectionPool connectionPool) {
        try {
            int categoryId = Integer.parseInt(ctx.pathParam("categoryId"));

            List<Recipe> recipes =
                    RecipeMapper.getRecipesByCategory(categoryId, connectionPool);

            ctx.json(recipes);

        } catch (Exception e) {
            ctx.status(500).result(e.getMessage());
        }
    }

    public static void saveRecipe(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        if (user == null) {
            ctx.status(401).result("Not logged in");
            return;
        }

        try {
            int recipeId = Integer.parseInt(ctx.formParam("recipeId"));

            RecipeMapper.saveRecipe(user.getUserId(), recipeId, connectionPool);

            ctx.status(200);

        } catch (Exception e) {
            ctx.status(500).result(e.getMessage());
        }
    }

    public static void removeRecipe(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        if (user == null) {
            ctx.status(401).result("Not logged in");
            return;
        }

        try {
            int recipeId = Integer.parseInt(ctx.formParam("recipeId"));

            RecipeMapper.removeSavedRecipe(user.getUserId(), recipeId, connectionPool);

            ctx.status(200);

        } catch (Exception e) {
            ctx.status(500).result(e.getMessage());
        }
    }
}