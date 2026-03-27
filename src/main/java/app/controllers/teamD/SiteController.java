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

public class SiteController {
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
        ctx.attribute("recipes", recipesList);
        ctx.render("teamD/index.html");
    }

    private static Map<String, String> getCategoryImages() {
        return Map.of(
                "Grøntsager", "vegetable.png",
                "Frugter", "fruit.png",
                "Kød & Fisk", "meat.png",
                "Mejeri", "dairy.png",
                "Krydderier", "spice.png",
                "Tørvarer", "colonial.png",
                "Drikkevarer", "drink.png",
                "Færdigvarer", "fastfood.png",
                "Søde Sager", "dessert.png",
                "Andet", "other.png"
        );
    }

    private static Map<String, List<Ingredient>> getIngredients(ConnectionPool connectionPool) {
        List<Ingredient> ingredients = IngredientMapper.getIngredients(connectionPool)
                .stream()
                .sorted(Comparator.comparing(Ingredient::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        List<String> categoryOrder = List.of(
                "Grøntsager",
                "Frugter",
                "Kød & Fisk",
                "Mejeri",
                "Krydderier",
                "Tørvarer",
                "Drikkevarer",
                "Færdigvarer",
                "Søde Sager",
                "Andet"
        );
        Map<String, List<Ingredient>> groupedIngredients = new LinkedHashMap<>();

        for (String category : categoryOrder) {
            groupedIngredients.put(category, new ArrayList<>());
        }
        for (Ingredient ingredient : ingredients) {
            groupedIngredients
                    .computeIfAbsent(ingredient.getCategory(), key -> new ArrayList<>())
                    .add(ingredient);
        }

        return groupedIngredients;
    }

    private static List<Recipe> getRecipes(ConnectionPool connectionPool) {
        List<Recipe> recipesNoIngredients = RecipeMapper.getAllRecipes(connectionPool);
        List<Recipe> recipesWithIngredients = RecipeMapper.getAllRecipesWithIngredients(connectionPool);

        List<Recipe> recipesLimit = recipesNoIngredients.stream()
                .limit(1000)
                .toList();
        return recipesLimit;
    }

}
