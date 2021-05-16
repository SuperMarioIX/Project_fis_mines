package controllers;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleStringProperty Username;
    private SimpleStringProperty Highscore;

    public User(String Username, String Highscore){
        this.Username = new SimpleStringProperty(Username);
        this.Highscore = new SimpleStringProperty(Highscore);
    }
    public String getUsername() {
        return Username.get();
    }
    public String getHighscore() {
        return Highscore.get();
    }

    public void getUserData(){
        System.out.println(this.Username + " " + this.Highscore);
    }
}
