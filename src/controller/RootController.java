package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.GUIUtil;

public class RootController implements Initializable {
    
    private AnchorPane myPasswordsNode;
    private MyPasswordsController myPasswordsController;
    
    private AnchorPane myAccoutNode;
    private Stage stage; /* The stage to which this node (root) belongs */
    
    @FXML
    private VBox root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader mpLoader = new FXMLLoader(getClass().getResource("/fxml/MyPasswords.fxml"));
            myPasswordsNode = mpLoader.load();
            myPasswordsController = mpLoader.getController();
            root.getChildren().add(myPasswordsNode);
        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
        myPasswordsController.setStage(stage);
    }

    @FXML
    private void myPasswords(ActionEvent event) throws Exception {
    }

    @FXML
    private void myAccount(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void passwordGenerator(ActionEvent event) {
        try {
            Stage genStage = new Stage();
            genStage.setTitle("Password Generator");
            genStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/PasswordGenerator.fxml"))));
            genStage.setResizable(false);
            GUIUtil.dialog(stage, genStage);
        } catch (IOException ex) {
            ex.printStackTrace(System.err); System.exit(0);
        }
    }

    @FXML
    private void about(ActionEvent event) {
        
    }
    
}
