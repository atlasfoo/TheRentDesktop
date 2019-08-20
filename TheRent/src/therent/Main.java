package therent;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.model.beans.Auto;
import therent.view.LogWindowControl;
import therent.view.MainFrameControl;
import therent.view.car.*;

import therent.view.Cliente.control_MenuCliente;
import therent.view.LogWindowControl;
import therent.view.MainFrameControl;
import therent.view.*;
import java.io.IOException;

public class Main extends Application {
    Stage primaryStage;
    String session_role;


    // Metodo start

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("TheRent Link System");
        showLogin();

    }

    // Redireccionar a Main teniendo la sesión abierta
    public void redirectMain(){
        showMainFrame(this.session_role);
    }


    // Mostrar ventana principal
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
    public void showMainFrame(String role) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainFrame.fxml"));
        try {
            AnchorPane root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            MainFrameControl ctrl = loader.getController();
            ctrl.setRole(role);
            ctrl.setMain(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Mostrar inicio de sesion
    public void showLogin(){
        FXMLLoader loader=new FXMLLoader();
    public void showLogin() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/LogWindow.fxml"));
        try {
            AnchorPane root = loader.load();
            //la ventana de login NO se carga en el primaryStage
            Stage dlgStage = new Stage();
            dlgStage.setTitle("Inicio de Sesión");
            dlgStage.initModality(Modality.WINDOW_MODAL);
            dlgStage.setResizable(false);
            dlgStage.setScene(new Scene(root));
            LogWindowControl ctrl = loader.getController();
            ctrl.setDlgStage(dlgStage);
            ctrl.setMain(this);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Evento para mostrar el carOverview Automaticamente dentro de root layout auto
    public void showCar(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/car/RootLayout.fxml"));
        try {
            AnchorPane root=loader.load();
            RootLayoutControl ctrl=loader.getController();
            ctrl.setMain(this);
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            showCarOverview(ctrl.getSidepane());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showCarOverview(BorderPane sidepane){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/car/CarOverview.fxml"));
        try {
            AnchorPane root=loader.load();
            sidepane.setCenter(root);
            CarOverviewControl ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setParentPane(sidepane);
            //CarOverviewControl ctrl=loader.getController();

    public void showClient() {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("view/Cliente/MenuCliente.fxml"));

        try {

            AnchorPane root=loader.load();

           control_MenuCliente ctrl=loader.getController();

            ctrl.setMain(this);

            primaryStage.setMaximized(false);

            primaryStage.setScene(new Scene(root));

            primaryStage.setMaximized(true);

            primaryStage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    //Abre la ventana cliente
    public void showClienteTel()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Cliente/viewTelefono.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.setScene(new Scene(root));
            dlgStage.setResizable(false);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //para auto nuevo
    public void showNewCar(BorderPane parentPane){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/car/CarAddEditScene.fxml"));
        try{
            BorderPane root=loader.load();
            CarAddEditControl ct=loader.getController();
            ct.setMain(this);
            ct.setParentPane(parentPane);
            ct.setEdit(false);
            parentPane.setCenter(root);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    //Mostrar ventana de editar auto
    public void showEditCar(BorderPane parentPane, Auto a){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/car/CarAddEditScene.fxml"));
        try{
            BorderPane root=loader.load();
            CarAddEditControl ct=loader.getController();
            ct.setMain(this);
            ct.setParentPane(parentPane);
            ct.setEdit(true);
            ct.setAut(a);
            parentPane.setCenter(root);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //Mostrar ventana de ingresar modelo
    public void showNewModel(BorderPane parentPane){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/car/ModelScene.fxml"));
        try {
            BorderPane root=loader.load();
            ModelSceneControl ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setParentPane(parentPane);
            parentPane.setCenter(root);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //Mostrar ventana de ingresar mantenimiento
    public void showManteniance(BorderPane parentPane){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("view/car/AddMantScene.fxml"));
        try {
            AnchorPane root=loader.load();
            AddMantControl ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setParentPane(parentPane);
            parentPane.setCenter(root);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    //Abre la ventana correo
    public void showClienteCorreo()
    {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Cliente/viewCorreo.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.setScene(new Scene(root));
            dlgStage.setResizable(false);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Abre la ventana para mostrar los datos completos
    public  void showDatosCompleto()
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Cliente/viewDatosCompleto.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.setScene(new Scene(root));
            dlgStage.setResizable(false);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Cierra ventana, es creado para cerrar el showCliente y showCorreo
    public void closeViewCliente(JFXButton button)
    {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /*TODO:
       1: implementar el resto de laas funcionalidades, cada quien en su rama
    *  issue 1: RESUELTO
    *  issue 2: investigar como crear una pantalla de carga, ya que el servidor bd
    *  remoto toma tiempo en responder*/

    public static void main(String[] args) {
        launch(args);
    }
}
