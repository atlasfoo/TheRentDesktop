package therent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.setTitle("TheRent Link System");
        showLogin();
    }
    private void showLogin(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LogWindow.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.setTitle("Inicio de Sesión");
            dlgStage.setResizable(false);
            dlgStage.setScene(new Scene(root));
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
