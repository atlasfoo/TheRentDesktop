package therent.view.Reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import therent.control.CRentaDialog;
import therent.model.DetalleRentaModel;
import therent.model.RentaModel;
import therent.model.TablaVistaC;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class IncomeVisualizationWindowController implements Initializable {

    @FXML
    private JFXButton fx_button_eliminar;

    @FXML
    private TableView<DetalleRentaModel> tabla_reservas;

    @FXML
    private TableColumn<DetalleRentaModel,String> column_id_detalle_renta;

    @FXML
    private TableColumn<DetalleRentaModel,String> column_marca;

    @FXML
    private TableColumn<DetalleRentaModel,String> column_modelo;

    @FXML
    private TableColumn<DetalleRentaModel, String> column_fecha_entrega;

    @FXML
    private TableColumn<DetalleRentaModel, String> column_fecha_recibo;

    @FXML
    private TableColumn<DetalleRentaModel,String> column_costo;

    @FXML
    private JFXTextField fx_id_detalle_renta;

    public void MostrandoReservas()
    {
        try {
            column_id_detalle_renta.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel, String>("Id_detalle_renta"));
            column_marca.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel, String>("marca"));
            column_modelo.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel, String>("modelo"));
            column_fecha_entrega.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel,String>("Fecha_Entrega"));
            column_fecha_recibo.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel, String>("Fecha_Recibo"));
            column_costo.setCellValueFactory(new PropertyValueFactory<DetalleRentaModel, String>("costo"));
            tabla_reservas.setItems(CRentaDialog.MostrarReservas());
        }catch (Exception E){
            msgerr(E.getMessage());
        }//columnas
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    public void GestionEvento()
    {
        tabla_reservas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DetalleRentaModel>() {
            @Override
            public void changed(ObservableValue<? extends DetalleRentaModel> observable, DetalleRentaModel oldValue, DetalleRentaModel newValue){
                fx_id_detalle_renta.setText(String.valueOf(newValue.getId_Detalle_Renta()));
            }
        });
    }


    //Evento para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MostrandoReservas();
        GestionEvento();
        fx_id_detalle_renta.setVisible(false);
    }

    @FXML
    private void DeleteAReservation(ActionEvent actionEvent) {

        try {
            tabla_reservas.setItems(CRentaDialog.EliminarReserva(Integer.parseInt(fx_id_detalle_renta.getText())));
            MostrandoReservas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

