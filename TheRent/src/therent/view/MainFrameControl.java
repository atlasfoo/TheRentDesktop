package therent.view;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.Main;

import java.io.IOException;

public class MainFrameControl {

    @FXML
    private JFXButton adminautobtn;

    @FXML
    private JFXButton reservbtn;

    @FXML
    private JFXButton clientbtn;

    @FXML
    private JFXButton deliverbtn;

    private String role;

    Stage primaryStage;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMain(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void setRole(String role) {
        this.role = role;
        //al modificar el rol
        switch (this.role){
            //en caso de vendedor, se deshabilita la adm de autos
            case "VENDEDOR":{
                adminautobtn.setDisable(true);
                return;
            }
            //en caso de entrega, solo de deja habilitado la entrega y recepcion
            case "ENTREGA":{
                adminautobtn.setDisable(true);
                clientbtn.setDisable(true);
                reservbtn.setDisable(true);
                return;
            }
        }
    }


    @FXML
    public void initialize(){
        //void
    }

    //mostrar ventana principal para abrir reserva
    @FXML
    public void ShowReservationFrame(){
        main.showReservation();
        /*FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Reservation/ReservaWindow.fxml"));
        try {
            AnchorPane root=loader.load();
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.setMaximized(true);
            primaryStage.setScene(new Scene(root));
            primaryStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}
