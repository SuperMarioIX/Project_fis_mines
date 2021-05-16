package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Leaderboard implements Initializable {
    @FXML
     TableView<User> boardTable;
    @FXML
    private TableColumn<User, String> boardTableUsername;
    @FXML
    private TableColumn<User, String> boardTableTime;
    public String dndpath = "src/main/java/sources/board.json";

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/MainPage.fxml")));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONArray board = readFile("src/main/java/sources/board.json");
        System.out.println(board);

        boardTableUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        boardTableTime.setCellValueFactory(new PropertyValueFactory<User, String>("Highscore"));

        boardTableUsername.setCellFactory(TextFieldTableCell.forTableColumn());
        boardTableTime.setCellFactory(TextFieldTableCell.forTableColumn());
        //boardTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 

        JSONParser parser = new JSONParser();
        Object obj;
        JSONArray array = new JSONArray();

        try{
            FileReader readFile = new FileReader(dndpath);
            BufferedReader buffer = new BufferedReader(readFile);
            obj = parser.parse(buffer);
            if(obj instanceof JSONArray){
                array = (JSONArray) obj;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        for(JSONObject table : (Iterable<JSONObject>) array){
            User users = new User((String) ((JSONObject) table).get("User"), (String) ((JSONObject) table).get("Score"));
            boardTable.getItems().add(users);
            users.getUserData();
        }
    }

    public JSONArray readFile(String sourceFilePath){

        JSONParser parser = new JSONParser();
        Object obj = null;

        try {
            obj = parser.parse(new FileReader(sourceFilePath));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        return (JSONArray) obj;
    }
}
