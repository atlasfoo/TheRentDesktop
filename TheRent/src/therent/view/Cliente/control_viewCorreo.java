package therent.view.Cliente;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import therent.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class control_viewCorreo implements Initializable {

    @FXML
    private JFXListView<String> idLIst;

    @FXML
    private JFXButton idAdd;

    @FXML
    private JFXTextField idCorr;

    @FXML
    private JFXButton idDell;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnAceptar;


    public static ObservableList<String> tele = FXCollections.observableArrayList();

    Main main = new Main();

    private static void msgerr(String msg) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    @FXML
    void Agregar() {

        if (idCorr.getText().equals("") || idCorr.getText() == null) {
            msgerr("No se pueden guardar los datos, verificar que la lista no este vacía.");
           // System.out.println(idCorr.getText());
        } else {

            if(tele == null)
            {
                tele = FXCollections.observableArrayList();
            }

           tele.add(idCorr.getText());
            idLIst.setItems(tele);
           idCorr.setText("");
            System.out.println(idCorr.getText());
        }
    }
    @FXML
    void Aceptar()
    {
        main.closeViewCliente(btnAceptar);
    }

    @FXML
    void Cancelar()
    {
        if(tele!=null) {
            tele.clear();
            tele = null;
            idLIst.setItems(null);
            main.closeViewCliente(btnCancelar);

        }
        else
            main.closeViewCliente(btnCancelar);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        idLIst.setItems(tele);
    }

}