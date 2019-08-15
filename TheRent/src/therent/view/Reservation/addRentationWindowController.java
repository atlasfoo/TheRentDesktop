package therent.view.Reservation;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import therent.control.CRentaDialog;
import therent.model.RentaModel;
import therent.model.TablaVistaC;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class addRentationWindowController implements Initializable {


    @FXML
    private JFXTextField fx_nombre_cliente;

    @FXML
    private JFXComboBox<String> fx_estado;

    @FXML
    private JFXButton fx_btn_agregar_renta;

    @FXML
    private Label msjText;

    @FXML
    private JFXButton fx_button_modificar;

    @FXML
    private JFXButton fx_button_eliminar;

    @FXML
    private JFXTextField fx_id_cliente;

    @FXML
    private TableView<TablaVistaC> tabla_cliente;

    @FXML
    private TableColumn<TablaVistaC,String> column_id_cliente;

    @FXML
    private TableColumn<TablaVistaC,String> column_primer_nombre;

    @FXML
    private TableColumn<TablaVistaC,String> column_primer_apellido;

    @FXML
    private TableView<RentaModel> tabla_renta;

    @FXML
    private TableColumn<RentaModel,String> column_primer_nombre2;

    @FXML
    private TableColumn<RentaModel,String> column_segundo_nombre;

    @FXML
    private TableColumn<RentaModel,String> column_primer_apellido2;

    @FXML
    private TableColumn<RentaModel,String> column_segundo_apellido;

    @FXML
    private TableColumn<RentaModel,Date> column_fecha;

    @FXML
    private TableColumn<RentaModel,String> column_estado;


    @FXML
    private void SaveNewReserva(ActionEvent actionEvent){
    }

    public void MostrandoDatosCliente()
    {
        //columnas
        column_id_cliente.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Id_Cliente"));
        column_primer_nombre.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Primer_Nombre"));
        column_primer_apellido.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Primer_Apellido"));
        tabla_cliente.setItems(CRentaDialog.MostrarClientes());
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    public void MostrandoDatosRenta()
    {
        try {
            column_primer_nombre2.setCellValueFactory(new PropertyValueFactory<RentaModel, String>("Primer_Nombre"));
            column_segundo_nombre.setCellValueFactory(new PropertyValueFactory<RentaModel, String>("Segundo_Nombre"));
            column_primer_apellido2.setCellValueFactory(new PropertyValueFactory<RentaModel, String>("Primer_Apellido"));
            column_segundo_apellido.setCellValueFactory(new PropertyValueFactory<RentaModel, String>("Segundo_Apellido"));
            column_fecha.setCellValueFactory(new PropertyValueFactory<RentaModel, Date>("fecha"));
            column_estado.setCellValueFactory(new PropertyValueFactory<RentaModel, String>("estado"));
            tabla_renta.setItems(CRentaDialog.MostrarRenta());
        }catch (Exception E){
            msgerr(E.getMessage());
        }//columnas
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> lis = FXCollections.observableArrayList();
        lis.add("RESERVADO"); //Revisar el getValue
        lis.add("CANCELADO");
        lis.add("PAGADO");
        fx_estado.setItems(lis);
       MostrandoDatosCliente();
       MostrandoDatosRenta();
       GestionEvento();
       GestionEvento2();
       fx_id_cliente.setVisible(false);

    }

    //Evento para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    public void GestionEvento()
    {
        tabla_cliente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TablaVistaC>() {
            @Override
            public void changed(ObservableValue<? extends TablaVistaC> observable, TablaVistaC oldValue, TablaVistaC newValue){
                //fx_id_auto.setText(newValue.getIDAuto());
                fx_nombre_cliente.setText(newValue.getPrimer_Nombre()+ " " + newValue.getPrimer_Apellido());
                fx_id_cliente.setText(String.valueOf(newValue.getId_Cliente()));
            }
        });
    }

    public void GestionEvento2()
    {
        /*tabla_renta.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TablaVistaA>() {
            @Override
            public void changed(ObservableValue<? extends TablaVistaA> observable, TablaVistaA oldValue, TablaVistaA newValue){
                //fx_id_auto.setText(newValue.getIDAuto());
                fx_auto.setText(newValue.getMarca() + " " + newValue.getModelo());
                fx_id_auto.setText(String.valueOf(newValue.getId_Auto()));
            }
        });*/
    }

    @FXML
    private void Button_addRenta(ActionEvent actionEvent) {
        try {
            CRentaDialog.addRenta(Integer.parseInt(fx_id_cliente.getText()),fx_estado.getValue());
            msjText("Renta Agregado Correctamente.");
            LimpiarCampos();
            MostrandoDatosRenta();
            Bloqueobotones(true, "AGREGAR");
        }catch (Exception E){msgerr(E.getMessage());}

        LimpiarCampos();

    }

    public void LimpiarCampos()
    {
        fx_nombre_cliente.setText("");
        fx_id_cliente.setText("");
    }

    //Metodo para mandar el mensaje a escribir en el text ejemplo cuando se agrege un registro, se busque, etc.
    public void msjText(String texto)
    {
        msjText.setText("");
        msjText.setText(texto);

    }

    //metodo para bloquear los campos de texto de acorde al boton seleccionado
    private void Bloqueobotones(boolean b, String m)
    {
        if(m.equals("AGREGAR")) {
            fx_id_cliente.setDisable(false);
            fx_nombre_cliente.setDisable(false);
            fx_estado.setDisable(false);

        }
        else{
            fx_id_cliente.setDisable(b);
            fx_nombre_cliente.setDisable(b);
            fx_estado.setDisable(b);

        }
    }

}
