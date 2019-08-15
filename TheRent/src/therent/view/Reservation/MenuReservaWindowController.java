package therent.view.Reservation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import therent.Main;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuReservaWindowController implements Initializable {

    @FXML
    private JFXButton boton_agregar_reserva;

    @FXML
    private JFXButton boton_visualizar_reserva;

    @FXML
    private JFXButton boton_salir;

    @FXML
    private BorderPane borderPaneContainer;

    @FXML
    private HBox hboxPnLabel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public BorderPane getSidepane() {
        return borderPaneContainer;
    }

    public void setSidepane(BorderPane borderPaneContainer) {
        this.borderPaneContainer = borderPaneContainer;
    }


    @FXML
    private void openReservaScene1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Node child = loader.load(getClass().getResource("../Reservation/addRentationWindow.fxml").openStream());
            borderPaneContainer.getChildren().clear();
            borderPaneContainer.setCenter(child);
            Label lblTitle = new Label("AGREGAR RESERVAS");
            lblTitle.setFont(new Font(24));
            hboxPnLabel.getChildren().clear();
            hboxPnLabel.getChildren().add(lblTitle);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void openReservaScene2(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Node child = loader.load(getClass().getResource("../Reservation/RentSearchWindow.fxml").openStream());
            borderPaneContainer.getChildren().clear();
            borderPaneContainer.setCenter(child);
            Label lblTitle = new Label("BUSQUEDA RESERVAS");
            lblTitle.setFont(new Font(24));
            hboxPnLabel.getChildren().clear();
            hboxPnLabel.getChildren().add(lblTitle);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void btnAgregarOnMouseExited(MouseEvent event) {
        boton_agregar_reserva.setStyle("-fx-background-color:transparent;");
    }

    @FXML
    private void btnAgregarOnMouseEntered(MouseEvent event) {
        boton_agregar_reserva.setStyle("-fx-background-color:#dae7f3;");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //boton_agregar_reserva.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("empleados.png"))));

    }

    @FXML
    private void btnSearchOnMouseExited(MouseEvent mouseEvent) {

        boton_visualizar_reserva.setStyle("-fx-background-color:transparent;");
    }

    @FXML
    private void btnSearchOnMouseEntered(MouseEvent mouseEvent) {
        boton_visualizar_reserva.setStyle("-fx-background-color:#dae7f3;");
    }

    @FXML
    private void btnExitOnMouseExited(MouseEvent event) {
        boton_salir.setStyle("-fx-background-color:transparent;");
    }

    @FXML
    private void btnExitOnMouseEntered(MouseEvent event) {
        boton_salir.setStyle("-fx-background-color:#dae7f3;");
    }
}
