package app.persistence.teamE;

import app.entities.teamE.Ingredient;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientMapper {

    public static List<Ingredient> getIngredientsByRecipe(int recipeId, ConnectionPool connectionPool)
            throws DatabaseException {

        List<Ingredient> ingredients = new ArrayList<>();

        String sql = """
            SELECT i.ingredient_id, i.ingredient_name, ri.quantity
            FROM teame_ingredient i
            JOIN teame_recipe_ingredient ri ON i.ingredient_id = ri.ingredient_id
            WHERE ri.recipe_id = ?
        """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, recipeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getInt("ingredient_id"),
                        rs.getString("ingredient_name"),
                        rs.getString("quantity")
                ));
            }

            return ingredients;

        } catch (SQLException e) {
            throw new DatabaseException("Database fejl", e.getMessage());
        }
    }
}
