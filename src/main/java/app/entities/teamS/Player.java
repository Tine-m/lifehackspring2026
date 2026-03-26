package app.entities.teamS;


import app.entities.login.User;

public class Player{
    private String username;
    private double time;
    public Player(User user, Timer time){
        this.username = user.getUserName();
        this.time = time.getTime();

    }
    public String toString (){
        return "Name: " + username + " Best time: " + time;
    }
}
