package app.entities.teamI;

import java.awt.*;
import java.util.Map;

public class Calculator {

    public Color calculateNewColor(Color coffeeColor, Color ingredientColor, int ingredientPercentages){
        float ingredientRatio = (float) ingredientPercentages/100;

        //Equation: (r3,g3,b3) = (r1,g1,b1)*(1-ingredientRatio)+(r2,g2,b2)*ingredientRatio
        float newR = coffeeColor.getRed()*(1-ingredientRatio)+ingredientColor.getRed()*ingredientRatio;
        float newG = coffeeColor.getGreen()*(1-ingredientRatio)+ingredientColor.getGreen()*ingredientRatio;
        float newB = coffeeColor.getBlue()*(1-ingredientRatio)+ingredientColor.getBlue()*ingredientRatio;

        return new Color(newR/255f,newG/255f,newB/255f);
    }

    public int calculateNewVolume(int coffeeVolume){
        return coffeeVolume;
    }

    public Map<String, Double> calculateNewIngredients(Map<String, Double> coffeeIngredients, String ingredient, double amount){

        if(coffeeIngredients.containsKey(ingredient)){
            coffeeIngredients.put(ingredient, coffeeIngredients.get(ingredient) + amount);
        } else {
            coffeeIngredients.put(ingredient, amount);
        }
        return coffeeIngredients;
    }
}
