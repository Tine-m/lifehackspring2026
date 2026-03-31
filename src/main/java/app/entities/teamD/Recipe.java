package app.entities.teamD;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int id;
    private String title;
    private String url;
    private String imageUrl;
    private String totalTime;
    private String yields;
    private String instructions;
    private List<String> ingredients;


    public Recipe(int id, String title, String url, String imageUrl,
                  String totalTime, String yields, String instructions) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.totalTime = totalTime;
        this.yields = yields;
        this.instructions = instructions;
        this.ingredients = new ArrayList<>();
    }

    public Recipe(int id, String title, String url, String imageUrl,
                  String totalTime, String yields,
                  String instructions, List<String> ingredients) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.totalTime = totalTime;
        this.yields = yields;
        this.instructions = instructions;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getYields() {
        return yields;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
