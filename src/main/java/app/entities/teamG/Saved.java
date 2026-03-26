package app.entities.teamG;

public class Saved {
    private int userId;
    private int hackId;

    public Saved(int userId, int hackId) {
        this.userId = userId;
        this.hackId = hackId;
    }

    public int getUserId() {
        return userId;
    }

    public int getHackId() {
        return hackId;
    }
}
