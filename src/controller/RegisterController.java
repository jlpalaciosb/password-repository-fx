package controller;

import dao.UserDAO;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Encryptor;
import util.GlobalData;

public class RegisterController implements Initializable {

    private Stage stage;
    private UserDAO dao;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new UserDAO();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void info(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.initOwner(stage);
        alert.showAndWait();
    }

    @FXML
    private void register(ActionEvent event) {
        String un = usernameField.getText();
        if(!checkUsername(un)) return;
        
        String ps = passwordField.getText();
        if(!checkPassword(ps)) return;
        
        String pc = confirmField.getText();
        if(!checkPasswordConfirm(ps, pc)) return;
        
        User newUser = new User(un, Encryptor.sha256(ps));
        dao.save(newUser);
        
        GlobalData.setUser(dao.get(un));
        GlobalData.setKey(ps);
        
        stage.close();
    }
    
    private boolean checkUsername(String un) {
        boolean valid = true;
        
        if(un.length() == 0) {
            info("The username field is empty.");
            valid = false;
        } else if(un.length() < 2) {
            info("The username minimum length is 2.");
            valid = false;
        } else if(un.length() > 20) {
            info("The username maximum length is 20.");
            valid = false;
        } else if(!Character.isLetter(un.charAt(0))) {
            info("The username must start with a letter.");
            valid = false;
        } else if(!un.matches("[A-Za-z0-9_]+")) {
            info("The username must only contain letters, numbers, underscores.");
            valid = false;
        } else if(dao.get(un) != null) {
            info("The entered username is already in use, please try another one.");
            valid = false;
        }
        
        String color = valid ? "green" : "red";
        usernameLabel.setTextFill(Color.web(color));
        
        return valid;
    }
    
    private boolean checkPassword(String ps) {
        boolean valid = true;
        
        if(ps.length() == 0) {
            info("The password field is empty.");
            valid = false;
        } else if(ps.length() < 8) {
            info("The password minimum length is 8.");
            valid = false;
        } else if(ps.length() > 20) {
            info("The password maximum length is 20.");
            valid = false;
        } else if(!ps.matches("[A-Za-z0-9_]+")) {
            info("The username must only contain letters, numbers, underscores.");
            valid = false;
        }
        
        String color = valid ? "green" : "red";
        passwordLabel.setTextFill(Color.web(color));
        
        return valid;
    }
    
    private boolean checkPasswordConfirm(String ps, String pc) {
        boolean valid = true;
        
        if(pc.length() == 0) {
            info("The password confirmation field is empty.");
            valid = false;
        } else if(!ps.equals(pc)) {
            valid = false;
            info("Passwords don't match");
        }
        
        String color = valid ? "green" : "red";
        confirmLabel.setTextFill(Color.web(color));
        
        return valid;
    }
    
}
