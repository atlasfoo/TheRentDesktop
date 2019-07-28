package therent.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaWindowControl implements Initializable {

    @FXML
    private JFXButton boton_agregar_reserva;

    @FXML
    private JFXButton boton_visualizar_reserva;

    @FXML
    private BorderPane borderPaneContainer;

    @FXML
    private HBox hboxPnLabel;

    @FXML
    private void openReservaScene1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Node child = loader.load(getClass().getResource("../view/RentaDialogWindow.fxml").openStream());
            borderPaneContainer.getChildren().clear();
            borderPaneContainer.setCenter(child);
            Label lblTitle = new Label("RESERVAS");
            lblTitle.setFont(new Font(24));
            hboxPnLabel.getChildren().clear();
            hboxPnLabel.getChildren().add(lblTitle);
        } catch (IOException ex) {
            Logger.getLogger(ReservaWindowControl.class.getName()).log(Level.SEVERE, null, ex);
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

            /*for(Node ob : hboxPnLabel.getChildren())
            {
                if (ob.getAccessibleText() != null)
                {
                    ob.addEventHandler(MouseEvent.MOUSE_CLICKED,(e)->
                    {
                        switch (ob.getAccessibleText())
                        {
                            case "Agregar Reserva":
                                try
                                {
                                    FXMLLoader loader = new FXMLLoader();
                                    Node child = loader.load(getClass().getResource("view/RentaDialogWindow.fxml").openStream());

                                    borderPaneContainer.getChildren().clear();
                                    borderPaneContainer.setCenter(child);
                                    hboxPnLabel.getChildren().clear();

                                }catch (IOException ex)
                                {
                                    ex.printStackTrace();
                                }
                                //idArchor.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF50"), CornerRadii.EMPTY, Insets.EMPTY)));
                                break;
                            case "Buscar":
//                                idArchor.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF55"), CornerRadii.EMPTY, Insets.EMPTY)));
                                break;
                            case "Eliminar":
 //                               idArchor.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF90"), CornerRadii.EMPTY, Insets.EMPTY)));
                                break;
                            case "Editar":
         //                       idArchor.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF20"), CornerRadii.EMPTY, Insets.EMPTY)));
                                break;
                            case "Exit":
                                //System.exit(0);
                                //Controller_OpcMenu entrada = new Controller_OpcMenu();
                                //entrada.close();
                                break;

                        }
                    });
                }
            }*/
    }






    /*@FXML
    private void Action_abrir_VR(ActionEvent actionEvent)
    {
        try
        {
        FXMLLoader loader = new FXMLLoader();
        Node child = loader.load(getClass().getResource("view/RentaDialogWindow.fxml").openStream());

        borderPaneContainer.getChildren().clear();
        borderPaneContainer.setCenter(child);
        hboxPnLabel.getChildren().clear();

        }catch (IOException ex)
        {
        ex.printStackTrace();
        }
    }*/


}
