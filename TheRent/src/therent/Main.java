package therent;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.model.beans.*;
import therent.view.client.control_MenuCliente;
import therent.view.LogWindowControl;
import therent.view.MainFrameControl;
import therent.view.car.*;
import therent.view.delivery.DeliveryWindowController;
import therent.view.delivery.GasWindowController;
import therent.view.reservation.*;

import java.io.IOException;

public class Main extends Application {
    Stage primaryStage;
    Empleado active_session;

    public Empleado getActive_session() {
        return active_session;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("TheRent Link System");
        //Limpiar sesion
        active_session=null;
        this.primaryStage.getIcons().add(new Image("file:media/img/rental.png"));
        showLogin();
    }
    // Redireccionar a Main teniendo la sesión abierta
    public void redirectMain(){
        showMainFrame(this.active_session);
    }
    // Mostrar ventana principal
    public void showMainFrame(Empleado role){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainFrame.fxml"));
        try {
            AnchorPane root=loader.load();
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            MainFrameControl ctrl=loader.getController();
            this.active_session=role;
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
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
            LogWindowControl ctrl=loader.getController();
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


    public void showClient() {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("view/client/MenuCliente.fxml"));

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
        loader.setLocation(Main.class.getResource("view/client/viewTelefono.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
            dlgStage.setScene(new Scene(root));
            dlgStage.setResizable(false);
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Abre la ventana correo
    public void showClienteCorreo()
    {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/client/viewCorreo.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.setScene(new Scene(root));
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
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
        loader.setLocation(Main.class.getResource("view/client/viewDatosCompleto.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.APPLICATION_MODAL);
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
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

    public void showReservationMain(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/MainRentView.fxml"));
        try {
            AnchorPane pane=loader.load();
            MainRentControl ctrl=loader.getController();
            ctrl.setMain(this);
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(pane));
            primaryStage.setMaximized(true);
            showRentOverview(ctrl.getParentPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRentOverview(BorderPane parent){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/RentOverview.fxml"));
        try {
            AnchorPane pane=loader.load();
            RentOverviewControl ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setParentPane(parent);
            parent.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddRent(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/AddRent.fxml"));
        try {
            AnchorPane pane=loader.load();
            AddRentControl ctrl=loader.getController();
            Stage dlgStage=new Stage();
            dlgStage.setTitle("The Rent Link System");
            dlgStage.setResizable(false);
            dlgStage.initModality(Modality.WINDOW_MODAL);
            dlgStage.initOwner(this.primaryStage);
            ctrl.setMain(this);
            ctrl.setDlgStage(dlgStage);
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
            dlgStage.setScene(new Scene(pane));
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRentDetail(BorderPane parent, Renta r){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/RentDetailOV.fxml"));
        try {
            AnchorPane pane=loader.load();
            RentDetailControl ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setParentPane(parent);
            ctrl.setR(r);
            parent.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewRentDetail(Renta r, BorderPane parent){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/AddDetalleRenta.fxml"));
        try {
            AnchorPane pane=loader.load();
            AddDetalleRentaController ctrl= loader.getController();
            ctrl.setMain(this);
            ctrl.setEdit(false);
            ctrl.setParentPane(parent);
            ctrl.setR(r);
            ctrl.setDr(null);
            parent.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditRentDetail(Renta r, DetalleRenta dr, BorderPane parent){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/AddDetalleRenta.fxml"));
        try {
            AnchorPane pane=loader.load();
            AddDetalleRentaController ctrl= loader.getController();
            ctrl.setMain(this);
            ctrl.setEdit(true);
            ctrl.setParentPane(parent);
            ctrl.setR(r);
            ctrl.setDr(dr);
            parent.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChangeStatRent(Renta r){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/reservation/RentChange.fxml"));
        try {
            AnchorPane pane=loader.load();
            RentChangeControl ctrl=loader.getController();
            Stage dlgStage=new Stage();
            dlgStage.setTitle("The Rent Link System");
            dlgStage.setResizable(false);
            dlgStage.initModality(Modality.WINDOW_MODAL);
            ctrl.setDlgStage(dlgStage);
            ctrl.setR(r);
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
            dlgStage.setScene(new Scene(pane));
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeliverMain(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/delivery/DeliveryWindow.fxml"));
        try {
            AnchorPane pane = loader.load();
            DeliveryWindowController ctrl=loader.getController();
            ctrl.setMain(this);
            primaryStage.setMaximized(false);
            primaryStage.setScene(new Scene(pane));
            primaryStage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEntregar(DetalleEntrega de, boolean isRecibo){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/delivery/GasWindow.fxml"));
        try {
            AnchorPane pane=loader.load();
            Stage dlgStage=new Stage();
            GasWindowController ctrl=loader.getController();
            ctrl.setMain(this);
            ctrl.setRecibo(isRecibo);
            ctrl.setDr(de);
            dlgStage.setTitle("The Rent Link System");
            dlgStage.setResizable(false);
            dlgStage.initModality(Modality.WINDOW_MODAL);
            dlgStage.initOwner(primaryStage);
            dlgStage.setScene(new Scene(pane));
            dlgStage.getIcons().add(new Image("file:media/img/rental.png"));
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*TODO:
       * Model for Entrega
       * Reporting connection
       * Optional: InputValidators
       * SUPER OPTIONAL: loading screen
       */

    public static void main(String[] args) {
        launch(args);
    }

}
