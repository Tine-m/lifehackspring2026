package app.entities.teamI.ingredients.beansType;




import app.entities.teamI.Coffee;

import java.awt.*;
import java.util.Map;

public class BeanKaf extends Coffee {


    double totalVolume;
    public BeanKaf(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getVolume() {
        return 0;
    }

    @Override
    public Map<String, Double> getIngredients() {
        return Map.of();
    }

    @Override
    public Color getColor() {
        return null;
    }
}
