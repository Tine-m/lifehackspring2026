package app.entities.teamI.ingredients;


import app.entities.teamI.Coffee;

import java.awt.*;
import java.util.Map;

public class Milk extends Coffee {

    Coffee coffee;
    double totalVolume;

    public Milk(Coffee coffee, double totalVolume){
        this.coffee = coffee;
        this.totalVolume = totalVolume;
    }

    @Override
    public Color getColor() {
        return calculateNewColor(coffee.getColor(), new Color(200,20,3), 40);
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
}
