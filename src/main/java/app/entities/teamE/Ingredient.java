package app.entities.teamE;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String quantity;

    public Ingredient(int ingredientId, String ingredientName, String quantity) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
