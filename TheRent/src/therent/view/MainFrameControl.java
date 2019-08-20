package therent.view;

import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import therent.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import therent.Main;
import therent.view.Cliente.control_MenuCliente;

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

    @FXML
    private AnchorPane anchorpane;

    private Main main;

    public void setMain(Main main) {

        this.main = main;

    }


    //Evento auxx para poder probar la ventana cliente
    @FXML
    void abrirCliente() throws Exception {

      main.showClient();
    }


    private String role;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    //mostrar ventana principal para abrir reserva
    @FXML
    void JFXButttonReservation() {
        main.ShowReservationFrame();
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
    @FXML
    public void handleAdminAutoBtn(){
        main.showCar();
    }


}
