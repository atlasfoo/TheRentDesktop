package therent.view.Cliente;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import therent.Main;
import therent.control.client.CCliente;
import javafx.scene.control.TableView;
import therent.model.client.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

public class control_viewDatosCompleto implements Initializable {

   @FXML
    private TableView<Cliente> tablaTel;

    @FXML
    private TableView<Cliente> tablaCorreo;

    @FXML
    private TableColumn<Cliente, String> cCorreo;

    @FXML
    private TableColumn<Cliente, String> cTelefono;

    @FXML
    private Text idN;

    @FXML
    private Text idC;

    @FXML
    private Text idD;

    @FXML
    private Text idTC;

    @FXML
    private JFXButton idCerrar;

    @FXML
    private Text idE;

    Main main = new Main();

    ObservableList<Cliente> datT;
    ObservableList<Cliente>datC;
//este metodo es creado para cuando se inicia la ventana caputurar los datos del registro que se selcciona en la tabla
    public void iniciar()
    {
        idN.setText(control_MenuCliente.n);
        idC.setText(control_MenuCliente.c);
        idD.setText(control_MenuCliente.d);
        idTC.setText(control_MenuCliente.tp); //revisar
        idE.setText(control_MenuCliente.e);  //revisar
        datT=CCliente.TelRegistrado(idC.getText());
        datC = CCliente.CorrRegistrado(idC.getText());
    }

    public void MostrarTabla()
    {
        cTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tel"));
        tablaTel.setItems(datT);
        cCorreo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("correo"));
        tablaCorreo.setItems(datC);
    }

    @FXML
    void Cerrar()
    {
     //   main.closeViewCliente(idCerrar);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciar();
        MostrarTabla();
    }
}
