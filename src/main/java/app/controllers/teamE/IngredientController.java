package app.controllers.teamE;

import app.entities.teamE.Ingredient;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.teamE.IngredientMapper;
import io.javalin.Javalin;

import java.util.List;

public class IngredientController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/recipe/{id}/ingredients", ctx -> {

            int recipeId = Integer.parseInt(ctx.pathParam("id"));

            try {
                List<Ingredient> ingredients =
                        IngredientMapper.getIngredientsByRecipe(recipeId, connectionPool);

                ctx.json(ingredients); // or ctx.render(...) if using Thymeleaf???

            } catch (DatabaseException e) {
                ctx.status(500).result("Database fejl: " + e.getMessage());
            }
        });
    }
}
