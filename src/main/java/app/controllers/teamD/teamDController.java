package app.controllers.teamD;

import app.entities.teamD.Ingredient;
import app.entities.teamD.Recipe;
import app.persistence.ConnectionPool;
import app.persistence.teamD.CsvImporter;
import app.persistence.teamD.IngredientMapper;
import app.persistence.teamD.RecipeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class teamDController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/teamD", ctx -> nemMadHomePage(ctx, connectionPool));
        try {
            CsvImporter.importCsv("src/main/resources/data/teamD/recipes_clean2.csv", "src/main/resources/data/teamD/ingredients_categorized.csv", connectionPool);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void nemMadHomePage(@NotNull Context ctx, ConnectionPool connectionPool) {
        Map<String, String> categoryImages = getCategoryImages();

        Map<String, List<Ingredient>> groupedIngredients = getIngredients(connectionPool);

        List<Recipe> recipesList = getRecipes(connectionPool);

        ctx.attribute("categoryImages", categoryImages);
        ctx.attribute("groupedIngredients", groupedIngredients);
        ctx.render("teamD/index.html");
    }

}
