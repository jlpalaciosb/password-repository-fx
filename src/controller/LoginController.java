package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Encryptor;
import util.GlobalData;

public class LoginController implements Initializable {

    private Stage stage;
    private UserDAO dao;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new UserDAO();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void login(ActionEvent event) {
        String un = usernameField.getText();
        if(!checkUsername(un)) return;
        
        User user = dao.get(un);
        
        String ps = passwordField.getText();
        if(!checkPassword(user, ps)) return;
        
        GlobalData.setUser(user);
        GlobalData.setKey(ps);
        stage.close();
    }
    
    private boolean checkUsername(String un) {
        boolean valid = true;
        
        if(dao.get(un) == null) valid = false;
        
        String color = valid ? "green" : "red";
        usernameLabel.setTextFill(Color.web(color));
        
        return valid;
    }
    
    private boolean checkPassword(User user, String ps) {
        boolean valid = true;
        byte[] psh = Encryptor.sha256(ps);
        
        if(!Encryptor.equal(psh, user.getPasswordSHA256())) valid = false;
        
        String color = valid ? "green" : "red";
        passwordLabel.setTextFill(Color.web(color));
        
        return valid;
    }

    @FXML
    private void newUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            AnchorPane register = loader.load();
            RegisterController rc = loader.getController();
            rc.setStage(stage);
            
            Scene registerScene = new Scene(register);
            stage.setTitle("New User");
            stage.setScene(registerScene);
        } catch (IOException ex) {
            ex.printStackTrace(System.err); System.exit(0);
        }
    }
    
}
