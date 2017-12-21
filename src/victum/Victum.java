/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 *
 * @author Addict
 */
public class Victum extends Application {
    static Victum appInstance;
    static Stage mainStage;
    
    public void setInspectionSelScene() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InspectionSelector.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("inspectionselector.css").toExternalForm());
        mainStage.setTitle("Inspection Selector");
        mainStage.setScene(scene);
        mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        mainStage.setFullScreen(true);
    }
    
    public void setLoginScene() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("LoginForm.css").toExternalForm());
        mainStage.setTitle("Login Form");
        mainStage.setScene(scene);
    }
    @Override
    public void start(Stage stage) throws Exception {
        appInstance = this;
        mainStage = stage;
        setLoginScene();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
