package therent.view.Cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import therent.Main;

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

    Main main = new Main();

    private static void msgerr(String msg) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    @FXML
    void Agregar()
    {
        if(idTel.getText().equals("")|| idTel.getText() == null)
        {
                msgerr("No se pueden guardar los datos, verificar que la lista no este vacía.");
        }
        else  {
            boolean correcto ;
            String aux = idTel.getText();
            correcto = aux.matches("-?([0-9]*)?");  //aca el aux.matches nos devolvera verdadero si el texto en el texfield son numeros
            if(correcto == true){ //si es verdadero ingresamos
                if(tel == null)
                {
                    tel = FXCollections.observableArrayList();
                }

                tel.add(idTel.getText());
                idLIst.setItems(tel);
                idTel.setText("");
            }
            else //caso contrario no dejamos
                {
                    msgerr("Se permite ingresar solo Números .");
                }
        }
    }

    @FXML
    void Aceptar()
    {
        main.closeViewCliente(btnAceptar);
    }

    //Verificar
    @FXML
    void borrar()
    {
        String dat;
        dat = idLIst.getSelectionModel().getSelectedItem();
        idLIst.getItems().remove(dat);
    }
    @FXML
    void Cancelar()
    {
        if(tel!=null) {
            tel.clear();
            tel = null;
            idLIst.setItems(null);
            main.closeViewCliente(btnCancelar);

        }
        else
            main.closeViewCliente(btnCancelar);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idLIst.setItems(tel);
    }
}
