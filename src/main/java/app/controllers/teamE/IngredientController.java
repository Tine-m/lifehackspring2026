package app.controllers.teamE;

import app.entities.teamE.Ingredient;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamE.IngredientMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class IngredientController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {


        app.get("/api/recipe/{id}/ingredients",
                ctx -> getIngredients(ctx, connectionPool));
    }

    public static void getIngredients(Context ctx, ConnectionPool connectionPool) {
        int recipeId = Integer.parseInt(ctx.pathParam("id"));

        try {
            List<Ingredient> ingredients =
                    IngredientMapper.getIngredientsByRecipe(recipeId, connectionPool);

            ctx.json(ingredients);

        } catch (DatabaseException e) {
            ctx.status(500).result("Database error: " + e.getMessage());
        }
    }
}