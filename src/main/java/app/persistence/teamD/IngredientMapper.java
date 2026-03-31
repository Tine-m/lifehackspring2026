package app.persistence.teamD;

import app.entities.teamD.Ingredient;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientMapper {
    public static List<Ingredient> getIngredients(ConnectionPool connectionPool) {
        List<Ingredient> foundIngredients = new ArrayList<>();
        ArrayList<String> existingCategories = new ArrayList<>(List.of(
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
        ));

        String sql = "SELECT * FROM teamd_ingredients";

        try (
            Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ingredientName = rs.getString("ingredient_name");
                String ingredientCategory = rs.getString("ingredient_category");

                if(!existingCategories.contains(ingredientCategory)) {
                    ingredientCategory = "Andet";
                }
                foundIngredients.add(new Ingredient(ingredientName, ingredientCategory));
            }
            return foundIngredients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
