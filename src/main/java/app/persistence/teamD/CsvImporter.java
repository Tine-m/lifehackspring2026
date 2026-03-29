package app.persistence.teamD;

import app.persistence.ConnectionPool;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CsvImporter {

    public static void importCsv(String recipesPath, String categoriesPath,
                                 ConnectionPool connectionPool) throws Exception {


        Map<String, String> categoryMap = new HashMap<>();
        try (CSVReader catReader = new CSVReader(new FileReader(categoriesPath))) {
            catReader.readNext();
            String[] row;
            while ((row = catReader.readNext()) != null) {
                if (row.length >= 2) {
                    categoryMap.put(row[0].trim().toLowerCase(), row[1].trim());
                }
            }
        }

        try (
                Connection conn = connectionPool.getConnection();
                CSVReader reader = new CSVReader(new FileReader(recipesPath))
        ) {
            reader.readNext();

            String[] row;

            while ((row = reader.readNext()) != null) {
                if (row.length < 8) continue;

                String title = row[1].trim();
                String url = row[2].trim();
                String imageUrl = row[3].trim();
                String totalTime = row[4].trim();
                String yields = row[5].trim();
                String ingredients = row[6].trim();
                String instructions = row[7].trim();

                if (title.isEmpty() || url.isEmpty()) {
                    continue;
                }

                int recipeId = insertRecipe(conn, title, url, imageUrl,
                        totalTime, yields, instructions);
                if (recipeId == -1) {
                    continue; // allerede i DB
                }

                if (!ingredients.isEmpty()) {
                    for (String raw : ingredients.split(" \\| ")) {
                        String name = raw.trim().toLowerCase();
                        if (name.isEmpty()) continue;

                        String category = categoryMap.getOrDefault(name, "Andet");
                        insertIngredient(conn, name, category);
                        insertRecipeIngredient(conn, recipeId, name);
                    }
                }
            }
        }
    }

    private static int insertRecipe(Connection conn, String title, String url, String imageUrl, String totalTime, String yields, String instructions)
            throws SQLException {
        String sql = """
                INSERT INTO teamd_recipes (title, url, image_url, total_time, yields, instructions)
                VALUES (?, ?, ?, ?, ?, ?)
                ON CONFLICT (url) DO NOTHING
                RETURNING id
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, url);
            ps.setString(3, imageUrl.isEmpty() ? null : imageUrl);
            ps.setString(4, totalTime.isEmpty() ? null : totalTime);
            ps.setString(5, yields.isEmpty() ? null : yields);
            ps.setString(6, instructions.isEmpty() ? null : instructions);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    private static void insertIngredient(Connection conn, String name, String category)
            throws SQLException {
        String sql = """
                INSERT INTO teamd_ingredients (ingredient_name, ingredient_category)
                VALUES (?, ?)
                ON CONFLICT (ingredient_name) DO NOTHING
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.executeUpdate();
        }
    }

    private static void insertRecipeIngredient(Connection conn, int recipeId, String ingredientName) throws SQLException {
        String sql = """
                INSERT INTO teamd_recipe_ingredients (recipe_id, ingredient_name)
                VALUES (?, ?)
                ON CONFLICT DO NOTHING
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipeId);
            ps.setString(2, ingredientName);
            ps.executeUpdate();
        }
    }
}