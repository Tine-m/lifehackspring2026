package app.entities.teamE;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String method;
    private String whyItWorks;

    public Recipe(int recipeId, String recipeName, String method, String whyItWorks) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.method = method;
        this.whyItWorks = whyItWorks;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getMethod() {
        return method;
    }

    public String getWhyItWorks() {
        return whyItWorks;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", method='" + method + '\'' +
                ", whyItWorks='" + whyItWorks + '\'' +
                '}';
    }
}