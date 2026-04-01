package app.entities.teamI;

import java.util.Objects;

public class CoffeeFavorits {
    private int user_id;
    private String coffeetype;
    private String milk;
    private String water;
    private String bean;
    private String brand;

    public CoffeeFavorits(String coffeetype, String milk, String water, String bean, String brand) {
        this.coffeetype = coffeetype;
        this.milk = milk;
        this.water = water;
        this.bean = bean;
        this.brand = brand;
    }

    public CoffeeFavorits(int user_id, String coffeetype, String milk, String water, String bean, String brand) {
        this.user_id = user_id;
        this.coffeetype = coffeetype;
        this.milk = milk;
        this.water = water;
        this.bean = bean;
        this.brand = brand;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCoffeetype() {
        return coffeetype;
    }

    public void setCoffeetype(String coffeetype) {
        this.coffeetype = coffeetype;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String beans) {
        this.bean = beans;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "CoffeeFavorits{" +
                "coffeetype='" + coffeetype + '\'' +
                ", milk=" + milk +
                ", water=" + water +
                ", bean=" + bean +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeFavorits that = (CoffeeFavorits) o;
        return milk == that.milk && water == that.water && bean == that.bean && Objects.equals(coffeetype, that.coffeetype) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeetype, milk, water, bean, brand);
    }
}
