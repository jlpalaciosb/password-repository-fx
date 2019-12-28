package controller;

import dao.PasswordDAO;
import entity.Password;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import util.GUIUtil;
import util.GlobalData;

public class MyPasswordsController implements Initializable {
    
    private Stage stage;
    private PasswordDAO dao;
    
    @FXML
    private TextField siteField;
    @FXML
    private TableView<Password> table;
    @FXML
    private TableColumn<Password, String> siteCol;
    @FXML
    private TableColumn<Password, String> identityCol;
    @FXML
    private TableColumn<Password, String> passwordCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new PasswordDAO();
        
        /* Table Initialization */
        siteCol.setCellValueFactory(new PropertyValueFactory<>("site"));
        identityCol.setCellValueFactory(new PropertyValueFactory<>("identity"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("clearPassword"));
        passwordCol.setCellFactory((TableColumn<Password, String> col) -> new PasswordCell());
        
        table.setRowFactory(
            (TableView<Password> tableView) -> {
                final TableRow<Password> row = new TableRow<>();
                
                final ContextMenu rowMenu = new ContextMenu();
                
                MenuItem editItem = new MenuItem("Edit");
                editItem.setOnAction((ActionEvent ev) -> {
                    edit(row.getItem());
                });
                
                MenuItem removeItem = new MenuItem("Delete");
                removeItem.setOnAction((ActionEvent ev) -> {
                    delete(row.getItem());
                });
                
                rowMenu.getItems().addAll(editItem, removeItem);

                // only display context menu for non-null items:
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise((ContextMenu)null));

                return row;
            }
        );
        
        filter(null);
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void newPassword(ActionEvent event) {
        try {
            Stage npStage = new Stage();
            npStage.setTitle("New Password");
            npStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewPassword.fxml"));
            npStage.setScene(new Scene(loader.load()));
            ((NewPasswordController)loader.getController()).setStage(npStage);
            GUIUtil.dialog(stage, npStage);
            filter(null);
        } catch (IOException ex) {
            ex.printStackTrace(System.err); System.exit(0);
        }
    }
    
    private void edit(Password pwd) {
        try {
            Stage epStage = new Stage();
            epStage.setTitle("Edit Password");
            epStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditPassword.fxml"));
            epStage.setScene(new Scene(loader.load()));
            ((EditPasswordController)loader.getController()).setStage(epStage);
            ((EditPasswordController)loader.getController()).setPassword(pwd);
            GUIUtil.dialog(stage, epStage);
            filter(null);
        } catch (IOException ex) {
            ex.printStackTrace(System.err); System.exit(0);
        }
    }
    
    private void delete(Password pwd) {
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Are you sure you want to permanently delete the selected record?", ButtonType.YES, ButtonType.NO);
        confirm.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        confirm.setTitle("Delete Password");
        confirm.initOwner(stage);
        confirm.showAndWait();
        if(confirm.getResult() == ButtonType.YES) {
            if(dao.delete(pwd)) filter(null);
            else GUIUtil.error(stage, "Some error occurred while trying to delete the selected record");
        }
    }

    @FXML
    private void filter(ActionEvent event) {
        String site = siteField.getText();
        List<Password> list = dao.get(GlobalData.getUser(), site);
        table.getItems().setAll(list);
    }
    
    private class PasswordCell extends TableCell<Password, String> {
        private Label label;
        private String password;

        public PasswordCell() {
            label = new Label();
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setGraphic(null);
            this.setOnMouseEntered((MouseEvent event) -> label.setText(password));
            this.setOnMouseExited((MouseEvent event) -> label.setText(dots(password)));
        }

        private String dots(String str) {
            if(str == null) return null;
            String dots = "";
            for (int i = 0; i < str.length(); i++) dots += "\u2022";
            return dots;
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            password = item;
            super.updateItem(item, empty);
            if (!empty) {
                label.setText(dots(password));
                setGraphic(label);
            } else {
                setGraphic(null);
            }
        }
    }

}
