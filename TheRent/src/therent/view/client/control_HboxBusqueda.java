package therent.view.client;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class control_HboxBusqueda {

    @FXML
    private JFXTextField tfBuscar;

    //Evento para mandar el valor que se desea buscar al controlador de la ventana principal de cliente
    //Ya que ahi tenemos el menu desplegable(que es esta ventana)
    @FXML
    public void texto()
    {
        control_MenuCliente.text = tfBuscar.getText();
    }


}
