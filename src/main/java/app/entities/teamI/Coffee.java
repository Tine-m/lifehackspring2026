package app.entities.teamI;
import java.awt.*;
import java.util.Map;

public abstract class Coffee extends Calculator {

    public abstract String getName();

    public abstract double getVolume();

    public abstract Map<String, Double> getIngredients();

    public abstract Color getColor();

}
