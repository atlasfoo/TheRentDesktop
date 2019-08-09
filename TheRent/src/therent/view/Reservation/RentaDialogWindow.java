package therent.view.Reservation;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import therent.control.CReserva;
import therent.model.TablaVistaA;
import therent.model.TablaVistaC;

import java.net.URL;
import java.text.Format;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class RentaDialogWindow implements Initializable {


    @FXML
    private JFXTextField fx_nombre_cliente;

    @FXML
    private JFXComboBox<String> fx_estado;

    @FXML
    private JFXTextField fx_costo_renta;

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
    private JFXTextField id_cliente;

    @FXML
    private JFXTextField id_auto;


    ObservableList<String> list = FXCollections.observableArrayList();

    /*
    private Date getDatePicker(DatePicker fecha){
        fecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate fechadada = fecha.getValue();
                fechadada.toString();
            }
        });
        return null;
    }


    Date fecha_1 = getDatePicker(fx_fecha_entrega);
    Date fecha_2 = getDatePicker(fx_fecha_recibo);

*/


    @FXML
    private void SaveNewReserva(ActionEvent actionEvent){
 /*      try {
            CReserva.Ingresar_Reserva(Integer.parseInt(fx_nombre_cliente.getText()),
                                            fx_estado.getValue(),
                                            Integer.parseInt(fx_auto.getText()),
                                            fecha_1,
                                            fecha_2,
                                            Double.parseDouble(fx_costo_renta.getText()));
            //MostrandoDatos();
            msgerr(fx_estado.getValue());
        }catch (Exception E){msgerr(E.getMessage());}*/
    }

    public void MostrandoDatosCliente()
    {
        //columnas
        column_id_cliente.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Id_Cliente"));
        column_primer_nombre.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Nombre"));
        column_primer_apellido.setCellValueFactory(new PropertyValueFactory<TablaVistaC,String>("Apellido"));
        tabla_cliente.setItems(CReserva.MostrarClientes());
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    public void MostrandoDatosAuto()
    {
        //columnas
        column_id_auto.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Id_Auto"));
        column_marca.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Marca"));
        column_modelo.setCellValueFactory(new PropertyValueFactory<TablaVistaA,String>("Modelo"));
        tabla_auto.setItems(CReserva.MostrarAutos());
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* ObservableList<String> lis = FXCollections.observableArrayList();
        lis.add("RESERVADO"); //Revisar el getValue
        lis.add("CANCELADO");
        lis.add("PAGADO");
        fx_estado.setItems(lis);*/
       fx_estado.getItems().addAll("RESERVADO", "CANCELADO", "PAGADO");
       MostrandoDatosCliente();
       MostrandoDatosAuto();
    }

    //Evento para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }
}
