package app.entities.teamR;

public class Subscription {
   private int subId;
   private String subName;
   private double subCost;
   private int subUsage;
   private String subCategory;

    public Subscription(int subId, String subName, double subCost, int subUsage, String subCategory) {
        this.subId = subId;
        this.subName = subName;
        this.subCost = subCost;
        this.subUsage = subUsage;
        this.subCategory = subCategory;
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
}
