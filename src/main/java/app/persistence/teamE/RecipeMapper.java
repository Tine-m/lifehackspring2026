package app.persistence.teamE;

import app.entities.teamE.Recipe;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeMapper {

    public static List<Recipe> getRecipesByCategory(int categoryId, ConnectionPool connectionPool)
            throws DatabaseException {

        List<Recipe> recipes = new ArrayList<>();

        String sql = """
            SELECT r.recipe_id, r.recipe_name, r.method, r.why_it_works
            FROM teamE_recipe r
            JOIN teamE_recipe_categories rc ON r.recipe_id = rc.recipe_id
            WHERE rc.category_id = ?
        """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                recipes.add(mapRecipe(rs));
            }

            return recipes;

        } catch (SQLException e) {
            throw new DatabaseException("Database fejl", e.getMessage());
        }
    }


    public static void saveRecipe(int userId, int recipeId, ConnectionPool connectionPool)
            throws DatabaseException {

        String sql = "INSERT INTO teamE_users_recipe (user_id, recipe_id) VALUES (?, ?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate")) {
                throw new DatabaseException("Opskrift allerede gemt");
            }
            throw new DatabaseException("Kunne ikke gemme opskrift", e.getMessage());
        }
    }


    public static List<Recipe> getSavedRecipes(int userId, ConnectionPool connectionPool)
            throws DatabaseException {

        List<Recipe> recipes = new ArrayList<>();

        String sql = """
            SELECT r.recipe_id, r.recipe_name, r.method, r.why_it_works
            FROM teamE_recipe r
            JOIN teamE_users_recipe ur ON r.recipe_id = ur.recipe_id
            WHERE ur.user_id = ?
        """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                recipes.add(mapRecipe(rs));
            }

            return recipes;

        } catch (SQLException e) {
            throw new DatabaseException("Database fejl", e.getMessage());
        }
    }


    public static void removeSavedRecipe(int userId, int recipeId, ConnectionPool connectionPool)
            throws DatabaseException {

        String sql = "DELETE FROM teamE_users_recipe WHERE user_id=? AND recipe_id=?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, recipeId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke fjerne opskrift", e.getMessage());
        }
    }

    // Helper method to map ResultSet -> Recipe
    private static Recipe mapRecipe(ResultSet rs) throws SQLException {
        return new Recipe(
                rs.getInt("recipe_id"),
                rs.getString("recipe_name"),
                rs.getString("method"),
                rs.getString("why_it_works")
        );
    }
}