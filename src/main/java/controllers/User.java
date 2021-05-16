package controllers;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleStringProperty Username, Highscore;

    public User(String Username, String Highscore){
        this.Username = new SimpleStringProperty(Username);
        this.Highscore = new SimpleStringProperty(Highscore);
    }
}
