package app.entities.teamM;

public class Subscription {
    private String name;
    private double price;
    private String dueDate;

    public Subscription(String name, double price, String dueDate) {
        this.name = name;
        this.price = price;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDueDate() {
        return dueDate;
    }
}