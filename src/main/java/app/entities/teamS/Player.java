package app.entities.teamS;

public class Player{
    private String username;
    private double time;
    public Player(String username, double time){
        this.username = username;
        this.time = time;
    }
    public String toString (){
        return "Name: " + username + " Best time: " + time;
    }
}
