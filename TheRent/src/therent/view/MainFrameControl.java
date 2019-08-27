package therent.view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import therent.Main;
import javafx.scene.layout.AnchorPane;

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

    //Evento auxx para poder probar la ventana cliente
    @FXML
    void abrirCliente() throws Exception {
        main.showClient();
    }

    public void setMain(Main main) {
        this.main = main;
        switch (this.main.getActive_session().getUser_role()){
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

    //mostrar ventana principal para abrir reserva
    @FXML
    public void handleReservation(){
        main.showReservationMain();
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
