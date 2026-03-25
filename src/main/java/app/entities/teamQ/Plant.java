package app.entities.teamQ;

public class Plant {

    private int plantID;
    private String plantName;
    private int health;
    private int lightHappiness;
    private int waterLevel;

    public Plant(int plantID, String plantName, int health, int lightHappiness, int waterLevel) {
        this.plantID = plantID;
        this.plantName = plantName;
        this.health = health;
        this.lightHappiness = lightHappiness;
        this.waterLevel = waterLevel;
    }

    public Plant(String plantName, int health, int lightHappiness, int waterLevel) {
        this.waterLevel = waterLevel;
        this.lightHappiness = lightHappiness;
        this.health = health;
        this.plantName = plantName;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLightHappiness() {
        return lightHappiness;
    }

    public void setLightHappiness(int lightHappiness) {
        this.lightHappiness = lightHappiness;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }
}
