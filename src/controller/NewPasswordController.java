package controller;

import dao.PasswordDAO;
import entity.Password;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Encryptor;
import util.GUIUtil;
import util.GlobalData;

public class NewPasswordController implements Initializable {
    
    private Stage stage;
    private PasswordDAO dao;

    @FXML
    private TextField siteField;
    @FXML
    private TextField identityField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label infoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new PasswordDAO();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void save(ActionEvent event) {
        String site = siteField.getText();
        String identity = identityField.getText();
        String password = passwordField.getText();
        if(site.equals("") || identity.equals("") || password.equals("")) {
            infoLabel.setVisible(true);
            return;
        } else {
            infoLabel.setVisible(false);
        }
        
        Password np = new Password(GlobalData.getUser(), site, identity, Encryptor.encrypt(GlobalData.getKey(), password));
        
        if(dao.save(np)) {
            GUIUtil.success(stage, "Record Saved!");
            stage.close();
        } else {
            GUIUtil.error(stage, "Some error occurred. It could be that you have already saved a password for the account you are loading now.");
        }
    }
    
}
