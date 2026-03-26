package app.controllers.teamE;

import app.persistence.ConnectionPool;
import app.persistence.teamE.RecipeMapper;
import app.exceptions.DatabaseException;
import io.javalin.Javalin;

import java.util.List;
import app.entities.teamE.Recipe;

public class RecipeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        //Get recipes by category
        app.get("/categories/{categoryId}/recipes", ctx -> {
            try {
                int categoryId = Integer.parseInt(ctx.pathParam("categoryId"));

                List<Recipe> recipes =
                        RecipeMapper.getRecipesByCategory(categoryId, connectionPool);

                ctx.json(recipes);

            } catch (DatabaseException e) {
                ctx.status(500).result(e.getMessage());
            }
        });

        //Get saved recipes for a user
        app.get("/users/{userId}/recipes", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("userId"));

                List<Recipe> recipes =
                        RecipeMapper.getSavedRecipes(userId, connectionPool);

                ctx.json(recipes);

            } catch (DatabaseException e) {
                ctx.status(500).result(e.getMessage());
            }
        });

        //Save a recipe for a user
        app.post("/users/{userId}/recipes/{recipeId}", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("userId"));
                int recipeId = Integer.parseInt(ctx.pathParam("recipeId"));

                RecipeMapper.saveRecipe(userId, recipeId, connectionPool);

                ctx.status(201).result("Recipe saved");

            } catch (DatabaseException e) {
                ctx.status(400).result(e.getMessage());
            }
        });

        //Remove saved recipe
        app.delete("/users/{userId}/recipes/{recipeId}", ctx -> {
            try {
                int userId = Integer.parseInt(ctx.pathParam("userId"));
                int recipeId = Integer.parseInt(ctx.pathParam("recipeId"));

                RecipeMapper.removeSavedRecipe(userId, recipeId, connectionPool);

                ctx.status(200).result("Recipe removed");

            } catch (DatabaseException e) {
                ctx.status(500).result(e.getMessage());
            }
        });
    }
}