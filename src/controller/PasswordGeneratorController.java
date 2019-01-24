package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PasswordGeneratorController implements Initializable {
    
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345678901234567890123456789__________";
    
    @FXML
    private ToggleGroup length;
    @FXML
    private TextField gpField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    

    @FXML
    private void generate(ActionEvent event) {
        int l = Integer.parseInt(((RadioButton)length.getSelectedToggle()).getText());
        String ps = "";
        for(int i = 0; i < l; i++) {
            ps += ALPHA.charAt((int)(Math.random() * ALPHA.length()));
        }
        gpField.setText(ps);
    }

}
