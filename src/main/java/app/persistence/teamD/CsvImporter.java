package app.persistence.teamD;

import app.persistence.ConnectionPool;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CsvImporter {

    public static void importCsv(String recipesPath, String ingredientsPath, ConnectionPool connectionPool) throws Exception {

        try (Connection conn = connectionPool.getConnection()) {
            conn.setAutoCommit(false);

            importIngredients(conn, ingredientsPath);
            importRecipes(conn, recipesPath);

            conn.commit();
        }
    }

    private static Map<String, Integer> loadCanonicalIngredients(Connection conn) throws SQLException {

        Map<String, Integer> map = new HashMap<>();

        String sql = "SELECT ingredient_id, ingredient_name FROM teamd_ingredients";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("ingredient_name"), rs.getInt("ingredient_id"));
            }
        }

        return map;
    }

    public static void importIngredients(Connection conn, String ingredientsPath) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(ingredientsPath))) {
            reader.readNext(); // Skip header
            String[] row;

            while ((row = reader.readNext()) != null) {
                if (row.length < 3) continue;

                int ingredientId = Integer.parseInt(row[0].trim());
                String ingredientName = row[1].trim().toLowerCase();
                String ingredientCategory = row[2].trim();

                insertIngredient(conn, ingredientId, ingredientName, ingredientCategory);
            }
        }
    }

    public static void importRecipes(Connection conn, String reicpePath) throws Exception {
        Map<String, Integer> canonicalMap = loadCanonicalIngredients(conn);
        try (CSVReader reader = new CSVReader(new FileReader(reicpePath))) {
            reader.readNext(); // Skip header
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

                if (title.isEmpty() || url.isEmpty()) continue;

                int recipeId = insertRecipe(conn, title, url, imageUrl, totalTime, yields, instructions);

                if (recipeId == -1) continue;

                if (!ingredients.isEmpty()) {
                    for (String raw : ingredients.split(" \\| ")) {
                        String rawName = raw.trim().toLowerCase();
                        if (rawName.isEmpty()) continue;

                        Integer ingredientId = findIngredientIdSmart(rawName, canonicalMap);

                        if (ingredientId == null) {
                            System.out.println("Missing canonical ingredient: " + rawName);
                            continue;
                        }

                        insertIngredientAlias(conn, ingredientId, rawName);
                        insertRecipeIngredient(conn, recipeId, ingredientId);
                    }
                }
            }
        }
    }

    private static void insertIngredient(Connection conn, int ingredientId, String ingredientName, String ingredientCategory)
        throws SQLException {
        String sql = """
                INSERT INTO teamd_ingredients (ingredient_id, ingredient_name, ingredient_category)
                VALUES (?, ?, ?)
                ON CONFLICT (ingredient_id) DO NOTHING
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ingredientId);
            ps.setString(2, ingredientName);
            ps.setString(3, ingredientCategory);
            ps.executeUpdate();
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

    private static void insertRecipeIngredient(Connection conn, int recipeId, int ingredientId) throws SQLException {
        String sql = """
                INSERT INTO teamd_recipe_ingredients (recipe_id, ingredient_id)
                VALUES (?, ?)
                ON CONFLICT DO NOTHING
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipeId);
            ps.setInt(2, ingredientId);
            ps.executeUpdate();
        }
    }

    private static void insertIngredientAlias(Connection conn, int ingredientId, String rawIngredientName) throws SQLException {
        String sql = """
                INSERT INTO teamd_ingredient_aliases (raw_ingredient_name, ingredient_id)
                VALUES (?, ?)
                ON CONFLICT DO NOTHING
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rawIngredientName);
            ps.setInt(2, ingredientId);
            ps.executeUpdate();
        }
    }

    private static Integer findIngredientIdSmart(String rawName,
                                                 Map<String, Integer> canonicalMap) {

        // 1️⃣ Direkte match
        if (canonicalMap.containsKey(rawName)) {
            return canonicalMap.get(rawName);
        }

        // 2️⃣ Contains match
        for (String canonical : canonicalMap.keySet()) {

            if (rawName.contains(canonical) || canonical.contains(rawName)) {
                return canonicalMap.get(canonical);
            }

            // plural/singular fix
            if (rawName.endsWith("er") &&
                canonical.equals(rawName.substring(0, rawName.length() - 2))) {
                return canonicalMap.get(canonical);
            }
        }

        return null;
    }
}