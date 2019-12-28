package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIUtil {
    
    /* Show a new parentStage at the center of `stage` */
    public static void dialog(Stage parentStage, Stage dialogStage) {
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parentStage);
        
        /* Calculate the center position of the parent parentStage */
        double centerXPosition = parentStage.getX() + parentStage.getWidth()/2d;
        double centerYPosition = parentStage.getY() + parentStage.getHeight()/2d;

        /* Hide the pop-up parentStage before it is shown and becomes relocated */
        dialogStage.setOnShowing(ev -> dialogStage.hide());

        /* Relocate the pop-up parentStage */
        dialogStage.setOnShown(ev -> {
            dialogStage.setX(centerXPosition - dialogStage.getWidth()/2d);
            dialogStage.setY(centerYPosition - dialogStage.getHeight()/2d);
            dialogStage.show();
        });
        
        dialogStage.showAndWait();
    }
    
    public static void error(Stage where, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.initOwner(where);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
    
    public static void success(Stage where, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.initOwner(where);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
    
}
