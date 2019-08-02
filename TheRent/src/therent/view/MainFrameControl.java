package therent.view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    //Evento auxx para poder probar la ventana cliente
    @FXML
    void abrirCliente() {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Cliente/MenuCliente.fxml"));
        try {
            AnchorPane root=loader.load();
            Stage dlgStage=new Stage();
            dlgStage.initModality(Modality.WINDOW_MODAL);
            dlgStage.setScene(new Scene(root));
            dlgStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String role;

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

}
