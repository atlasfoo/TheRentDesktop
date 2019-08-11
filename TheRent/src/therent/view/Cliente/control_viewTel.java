package therent.view.Cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class control_viewTel  implements Initializable {

    @FXML
    private JFXListView<String> idLIst;

    @FXML
    private JFXTextField idTel;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnAceptar;

    @FXML
    private JFXButton idAdd;

    @FXML
    private JFXButton idDell;

  public static  ObservableList<String> tel = FXCollections.observableArrayList();

    @FXML
    void Agregar()
    {



        tel.add(idTel.getText());
        idLIst.setItems(tel);
        idTel.setText("");
    }

    @FXML
    void Aceptar()
    {

    }

    @FXML
    void Cancelar()
    {
        tel.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idLIst.setItems(tel);
    }
}
