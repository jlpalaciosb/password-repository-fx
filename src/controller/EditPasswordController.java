package controller;

import dao.PasswordDAO;
import entity.Password;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.GUIUtil;

public class EditPasswordController implements Initializable {

    private Stage stage;
    private PasswordDAO dao;
    private Password pwd; // record to update

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
    
    public void setPassword(Password pwd) {
        this.pwd = pwd;
        siteField.setText(pwd.getSite());
        identityField.setText(pwd.getIdentity());
        passwordField.setText(pwd.getClearPassword());
    }

    @FXML
    private void update(ActionEvent event) {
        pwd.setSite(siteField.getText());
        pwd.setIdentity(identityField.getText());
        pwd.setClearPassword(passwordField.getText());
        if(pwd.getSite().equals("") || pwd.getIdentity().equals("") || pwd.getClearPassword().equals("")) {
            infoLabel.setVisible(true);
            return;
        } else {
            infoLabel.setVisible(false);
        }
        
        if(dao.update(pwd)) {
            GUIUtil.success(stage, "Record Updated!");
            stage.close();
        } else {
            GUIUtil.error(stage, "Some error occurred while trying to update the selected record");
        }
    }
    
}
