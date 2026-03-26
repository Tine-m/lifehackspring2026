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

        app.get("/recipe/{id}/ingredients", ctx -> getIngredients(ctx, connectionPool));
    }

        public static void getIngredients(Context ctx, ConnectionPool connectionpool){
            int recipeId = Integer.parseInt(ctx.pathParam("id"));

            try {
                List<Ingredient> ingredients =
                        IngredientMapper.getIngredientsByRecipe(recipeId, connectionpool);

                ctx.json(ingredients); // or ctx.render(...) if using Thymeleaf???
                ctx.render("teamE/hacks.html");
            } catch (DatabaseException e) {
                ctx.status(500).result("Database fejl: " + e.getMessage());
            }
        }
    }
