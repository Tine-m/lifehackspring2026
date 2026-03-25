package app.controllers.teamD;

import app.entities.teamD.Ingredient;
import app.persistence.ConnectionPool;
import app.persistence.teamD.CsvImporter;
import app.persistence.teamD.IngredientMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Ingredient> ingredients = IngredientMapper.getIngredients(connectionPool);

        Map<String, List<Ingredient>> groupedIngredients = new HashMap<>();

        for (Ingredient ingredient : ingredients) {
            groupedIngredients
                .computeIfAbsent(ingredient.getCategory(), key -> new ArrayList<>())
                .add(ingredient);
        }

            Map<String, String> categoryImages = Map.of(
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

        ctx.attribute("categoryImages", categoryImages);
        ctx.attribute("groupedIngredients", groupedIngredients);
        ctx.render("teamD/index.html");
    }

}
