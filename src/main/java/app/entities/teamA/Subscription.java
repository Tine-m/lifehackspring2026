package app.entities.teamA;

public class Subscription {
    private int subId;
    private String subName;
    private double subCost;
    private int subUsage;
    private String subCategory;
    private int userId;
    private String userName;

    public Subscription(int subId, String subName, double subCost, int subUsage, String subCategory) {
        this.subId = subId;
        this.subName = subName;
        this.subCost = subCost;
        this.subUsage = subUsage;
        this.subCategory = subCategory;
    }

    public Subscription(int subId, String subName, double subCost, int subUsage, String subCategory, int userId, String userName) {
        this.subId = subId;
        this.subName = subName;
        this.subCost = subCost;
        this.subUsage = subUsage;
        this.subCategory = subCategory;
        this.userId = userId;
        this.userName = userName;
    }

    public int getSubId() {
        return subId;
    }

    public String getSubName() {
        return subName;
    }

    public double getSubCost() {
        return subCost;
    }

    public int getSubUsage() {
        return subUsage;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "ID: " + userId;
    }
}
