package app.persistence.teamD;

import app.entities.teamD.Recipe;
import app.persistence.ConnectionPool;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RecipeMapper {

    public static List<Recipe> getAllRecipesWithIngredients(ConnectionPool conn) {
        List<Recipe> recipes = new ArrayList<>();
        String sql_recipe = "SELECT * FROM teamd_recipes";


        try(
            Connection connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql_recipe)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String url = rs.getString("url");
                String image_url = rs.getString("image_url");
                String totalTime = rs.getString("total_time");
                String yields = rs.getString("yields");
                String instructions = rs.getString("instructions");
                List<String> ingredients = new ArrayList<>(getIngredientsRecipe(id, conn));
                recipes.add(new Recipe(id, title, url, image_url, totalTime, yields, instructions, ingredients));
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getIngredientsRecipe (int recipeID, ConnectionPool conn){
        List<String> ingredientsInRecipe = new ArrayList<>();
        String sql_ingredients = "SELECT ingredient_name FROM teamd_recipe_ingredients\n" +
                "WHERE recipe_id = ?";

        try(
                Connection connection = conn.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql_ingredients);
        ) {
            ps.setInt(1, recipeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String ingredientName = rs.getString("ingredient_name");
                ingredientsInRecipe.add(ingredientName);
            }
            return ingredientsInRecipe;

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
