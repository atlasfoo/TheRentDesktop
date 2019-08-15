package therent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.view.LogWindowControl;
import therent.view.MainFrameControl;
import therent.view.Reservation.MenuReservaWindowController;

import java.io.IOException;

public class Main extends Application {
    Stage primaryStage;
    String session_role;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        primaryStage.setTitle("TheRent Link System");
        showLogin();
    }

    // Redireccionar a Main teniendo la sesión abierta
    public void redirectMain(){
        showMainFrame(this.session_role);
    }

    public void showMainFrame(String role){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainFrame.fxml"));
        try {
            AnchorPane root=loader.load();
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            MainFrameControl ctrl=loader.getController();
            this.session_role=role;
            ctrl.setRole(role);
            ctrl.setMain(this);
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

    //mostrar ventana principal para abrir reserva
    public void ShowReservationFrame(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Reservation/MenuReservaWindow.fxml"));
        try {
            AnchorPane root=loader.load();
            MenuReservaWindowController ctrl=loader.getController();
            ctrl.setMain(this);
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
