package therent.view.client;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import therent.Main;

public class control_HboxOpciones {


    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private JFXButton id_Agregar;

    @FXML
    private JFXButton id_Buscar;

    @FXML
    private JFXButton id_Actualizar;

    @FXML
    private JFXButton id_Deshabilitar;



}
