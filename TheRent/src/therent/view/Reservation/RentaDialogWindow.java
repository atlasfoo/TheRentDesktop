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
import therent.model.TablaVistaA;
import therent.model.TablaVistaC;

import java.net.URL;
import java.util.ResourceBundle;

public class RentaDialogWindow implements Initializable {


    @FXML
    private JFXTextField fx_nombre_cliente;

    @FXML
    private JFXComboBox<String> fx_estado;

    @FXML
    private Label msjText;

    @FXML
    private Label mensaje_save_DR;

    @FXML
    private JFXTextField fx_costo_renta;

    @FXML
    private JFXButton btn_agregar_renta;

    @FXML
    private JFXButton btn_guardar_renta;

    @FXML
    private JFXDatePicker fx_fecha_entrega;

    @FXML
    private JFXDatePicker fx_fecha_recibo;

    @FXML
    private JFXTextField fx_auto;

    @FXML
    private TableView<TablaVistaC> tabla_cliente;

    @FXML
    private TableColumn<TablaVistaC,String> column_id_cliente;

    @FXML
    private TableColumn<TablaVistaC,String> column_primer_nombre;

    @FXML
    private TableColumn<TablaVistaC,String> column_primer_apellido;

    @FXML
    private TableView<TablaVistaA> tabla_auto;

    @FXML
    private TableColumn<TablaVistaA,String> column_id_auto;

    @FXML
    private TableColumn<TablaVistaA,String> column_marca;

    @FXML
    private TableColumn<TablaVistaA,String> column_modelo;

    @FXML
    private JFXTextField fx_id_cliente;

    @FXML
    private JFXTextField fx_id_auto;

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

    public void MostrandoDatosAuto()
    {
        //columnas
        column_id_auto.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Id_Auto"));
        column_marca.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Marca"));
        column_modelo.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Modelo"));
        tabla_auto.setItems(CRentaDialog.MostrarAuto());
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
       MostrandoDatosAuto();
       GestionEvento();
       GestionEvento2();
       fx_id_cliente.setVisible(false);
       fx_id_auto.setVisible(false);
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
        tabla_auto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TablaVistaA>() {
            @Override
            public void changed(ObservableValue<? extends TablaVistaA> observable, TablaVistaA oldValue, TablaVistaA newValue){
                //fx_id_auto.setText(newValue.getIDAuto());
                fx_auto.setText(newValue.getMarca() + " " + newValue.getModelo());
                fx_id_auto.setText(String.valueOf(newValue.getId_Auto()));
            }
        });
    }

    @FXML
    private void Button_addRenta(ActionEvent actionEvent) {
        try {
            CRentaDialog.addRenta(Integer.parseInt(fx_id_cliente.getText()),fx_estado.getValue());
            msjText("Renta Agregado Correctamente.");
            LimpiarCampos();
        }catch (Exception E){msgerr(E.getMessage());}

        LimpiarCampos();

    }

    @FXML
    private void Button_addDetalleRenta(ActionEvent actionEvent)
    {
        try{
            CRentaDialog.addDetalleRenta(
                    Integer.parseInt(fx_id_auto.getText()),
                    fx_fecha_entrega.getValue(),
                    fx_fecha_recibo.getValue(),
                    Double.parseDouble(fx_costo_renta.getText())

            );
            msjText2("LA RESERVA SE AGREGO CORRECTAMENTE");
        }catch (Exception E){
            msgerr(E.getMessage());
        }
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

    //Metodo para mandar el mensaje a escribir en el text ejemplo cuando se agrege un registro, se busque, etc.
    public void msjText2(String texto)
    {
        mensaje_save_DR.setText("");
        mensaje_save_DR.setText(texto);
    }

}
