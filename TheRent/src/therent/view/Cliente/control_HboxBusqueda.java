package therent.view.Cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import therent.control.CCliente;
import therent.model.Cliente;

public class control_HboxBusqueda {

    @FXML
    private JFXTextField tfBuscar;

    @FXML
    public void texto()
    {
       // Cliente c= new Cliente();
       // c.EscribirArchivoAux(tfBuscar.getText());
        control_MenuCliente.text = tfBuscar.getText();
    }


}
