package main;

import controller.LoginController;
import controller.RootController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GUIUtil;
import util.GlobalData;
import util.HibernateUtil;

public class PasswordRepository extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        buildSessionFactory();
        
        loginOrRegister();
        if(GlobalData.getUser() == null) System.exit(0);
        
        showRoot(primaryStage);
    }
    
    public void buildSessionFactory() {
        try {
            HibernateUtil.buildSessionFactory();
        } catch(Exception ex) {
            GUIUtil.error(null, "Database Connection Error");
            ex.printStackTrace(System.err);
            System.exit(0);
        }
    }
    
    public void loginOrRegister() throws IOException {
        Stage loginRegisterStage = new Stage();
        loginRegisterStage.setResizable(false);
        loginRegisterStage.setTitle("Login");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        loginRegisterStage.setScene(new Scene(loader.load()));
        ((LoginController)loader.getController()).setStage(loginRegisterStage);
        
        loginRegisterStage.showAndWait();
    }
    
    public void showRoot(Stage ps) throws IOException {
        ps.setTitle("Password Repository");
        ps.getIcons().add(new Image(getClass().getResourceAsStream("/res/icon.png")));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Root.fxml"));
        VBox root = loader.load();
        RootController rc = loader.getController();
        rc.setStage(ps);
        Scene scene = new Scene(root);
        ps.setScene(scene);
        ps.show();
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtil.getSessionFactory().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
