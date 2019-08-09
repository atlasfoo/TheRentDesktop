package therent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.view.LogWindowControl;
import therent.view.MainFrameControl;
import therent.view.Reservation.ReservaWindowControl;

import java.io.IOException;

public class Main extends Application {
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.setTitle("TheRent Link System");
        //showLogin();
        showReservation();
    }

    public void showMainFrame(String role){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainFrame.fxml"));
        try {
            AnchorPane root=loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            MainFrameControl ctrl=loader.getController();
            ctrl.setRole(role);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showLogin(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LogWindow.fxml"));
        try {
            AnchorPane root=loader.load();
            //la ventana de login NO se carga en el primaryStage
            Stage dlgStage=new Stage();
            dlgStage.setTitle("Inicio de Sesión");
            dlgStage.initModality(Modality.WINDOW_MODAL);
            dlgStage.setResizable(false);
            dlgStage.setScene(new Scene(root));
            LogWindowControl ctrl=loader.getController();
            ctrl.setDlgStage(dlgStage);
            ctrl.setMain(this);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReservation(){
        BorderPane sidepane = ShowReservationFrame();
        if(sidepane==null){
            return;
        }
    }

    //mostrar ventana principal para abrir reserva
    public BorderPane ShowReservationFrame(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Reservation/ReservaWindow.fxml"));
        try {
            AnchorPane root=loader.load();
            ReservaWindowControl ctrl=loader.getController();
            ctrl.setMain(this);
            //primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            primaryStage.show();
            //return ctrl.getSidepane();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

/*TODO:
       1: implementar el resto de las funcionalidades, cada quien en su rama
    *  issue 1: RESUELTO
    *  issue 2: investigar como crear una pantalla de carga, ya que el servidor bd
    *  remoto toma tiempo en responder*/

    public static void main(String[] args) {
        launch(args);
    }
}
