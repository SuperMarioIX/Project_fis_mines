package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.management.relation.Role;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class HomeScreen implements Initializable {

    public String vip;

    @FXML
    private Text text;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    public Button Login;

    @FXML
    void initialize() {
        Login.setOnAction(event -> {
            try {
                handleLoginAction();
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void Login(ActionEvent event) throws IOException {
        String un = usernameField.getText();
        String pw = passwordField.getText();
        JSONArray userList = readFile("src/main/java/sources/users.json");
        assert userList != null;
        for(Object user : userList){
            JSONObject jsonObject = (JSONObject) user;
            JSONObject credentials = (JSONObject) jsonObject.get("Credentials");
            String username = (String) credentials.get("Username");
            String password = (String) credentials.get("Password");
            if (username.equals(un) && password.equals(pw)){
                if(((String) jsonObject.get("Role")).equals("admin")){
                    Parent viewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/AdminHomeScreen.fxml")));
                    Scene viewScene = new Scene(viewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(viewScene);
                    window.show();
                } else {
                    Parent viewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/UserHomeScreen.fxml")));
                    Scene viewScene = new Scene(viewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(viewScene);
                    window.show();
                }
            } else {
                System.out.println("Crasavcic iopta");
            }
        }

    }

    @FXML
    public boolean handleLoginAction() throws ParseException, IOException {
        Login.setOnAction(event -> {
            String un = usernameField.getText();
            String pw = passwordField.getText();
            String client = un.substring(un.length() - 3);
            vip = un.substring(un.length() - 4);
            JSONArray userList = readFile("src/main/java/sources/users.json");
            assert userList != null;
            for (Object o : userList) {
                JSONObject jsonObject = (JSONObject) o;
                JSONObject credentials = (JSONObject) jsonObject.get("Credentials");
                String username = (String) credentials.get("Username");
                String password = (String) credentials.get("Password");

                if (username.equals(un) && password.equals(pw)) {
                    Stage appStage = (Stage) Login.getScene().getWindow();
                    Parent root;
                    if (client.equals("clt"))
                        try {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/UserHomeScreen.fxml")));
                            Scene scene = new Scene(root);
                            appStage.setScene(scene);
                            appStage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    else if (client.equals("adm"))
                        try {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/AdminHomeScreen.fxml")));
                            Scene scene = new Scene(root);
                            appStage.setScene(scene);
                            appStage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                } else text.setText("Log in failed, wrong credentials");
            }
        });
        return true;
    }

    public JSONArray readFile(String sourceFilePath) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(sourceFilePath));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        JSONArray userList = (JSONArray) obj;
        return userList;
    }
    @FXML
    public void Guest(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/PlayGuest.fxml")));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
    @FXML
    public void Leaderboard(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmlFiles/Leaderboard.fxml")));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Signin(ActionEvent actionEvent) {
    }
}
