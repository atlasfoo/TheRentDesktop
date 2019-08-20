package therent.view.Reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import therent.control.CRentaDialog;
import therent.model.Auto;
import therent.model.RentaModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RentSearchWindowController implements Initializable {

    @FXML
    private TableView<Auto> tabla_auto;

    @FXML
    private TableColumn<Auto, String> column_id_auto;

    @FXML
    private TableColumn<Auto, String> column_marca;

    @FXML
    private TableColumn<Auto, String> column_modelo;

    @FXML
    private TableColumn<Auto, String> column_color;

    @FXML
    private TableColumn<Auto, String> column_año;

    @FXML
    private TableColumn<Auto, String> column_transmision;

    @FXML
    private TableColumn<Auto, String> column_carroceria;

    @FXML
    private TableColumn<Auto, String> column_combustible;

    @FXML
    private TableColumn<Auto, String> column_placa;

    @FXML
    private TableColumn<Auto, String> column_vin;

    @FXML
    private TableColumn<Auto, String> column_chasis;

    @FXML
    private TableColumn<Auto, String> column_categoria;

    @FXML
    private TableColumn<Auto, String> column_precio;

    @FXML
    private TableColumn<Auto, String> column_deposito;

    @FXML
    private TableColumn<Auto, String> column_habilitado;

    @FXML
    private TableColumn<Auto, String> column_estado;

    @FXML
    private JFXDatePicker fx_fecha_entrega;

    @FXML
    private JFXDatePicker fx_fecha_recibo;

    @FXML
    private JFXButton fx_button_search;

    @FXML
    private JFXTextField fx_costo_renta;

    @FXML
    private JFXTextField fx_auto;

    @FXML
    private Label mensaje;

    @FXML
    private JFXTextField fx_id_auto;

    public void MostrandoDatosAuto(){
        //columnas
        try {
            column_id_auto.setCellValueFactory(new PropertyValueFactory<Auto, String>("IdAuto"));
            column_marca.setCellValueFactory(new PropertyValueFactory<Auto, String>("marca"));
            column_modelo.setCellValueFactory(new PropertyValueFactory<Auto, String>("modelo"));
            column_color.setCellValueFactory(new PropertyValueFactory<Auto, String>("color"));
            column_año.setCellValueFactory(new PropertyValueFactory<Auto, String>("Anho"));
            column_transmision.setCellValueFactory(new PropertyValueFactory<Auto, String>("Transmision"));
            column_carroceria.setCellValueFactory(new PropertyValueFactory<Auto, String>("carrocerria"));
            column_combustible.setCellValueFactory(new PropertyValueFactory<Auto, String>("combustible"));
            column_placa.setCellValueFactory(new PropertyValueFactory<Auto, String>("placa"));
            column_vin.setCellValueFactory(new PropertyValueFactory<Auto, String>("VIN"));
            column_chasis.setCellValueFactory(new PropertyValueFactory<Auto, String>("chasis"));
            column_categoria.setCellValueFactory(new PropertyValueFactory<Auto, String>("categoria"));
            column_precio.setCellValueFactory(new PropertyValueFactory<Auto, String>("precio"));
            column_deposito.setCellValueFactory(new PropertyValueFactory<Auto, String>("deposito"));
            column_habilitado.setCellValueFactory(new PropertyValueFactory<Auto, String>("estado"));
            column_estado.setCellValueFactory(new PropertyValueFactory<Auto, String>("isenabled"));
            tabla_auto.setItems(CRentaDialog.MostrarDispAuto());
        }catch(Exception e){
            msgerr(e.getMessage());
        }
        //Cuando se da click mostrar los registros en sus respectivos campos
        //GestionEvento();
    }

    @FXML
    private void ButtonSearchCar(ActionEvent actionEvent)
    {
        try{
            column_id_auto.setCellValueFactory(new PropertyValueFactory<Auto, String>("IdAuto"));
            column_marca.setCellValueFactory(new PropertyValueFactory<Auto, String>("marca"));
            column_modelo.setCellValueFactory(new PropertyValueFactory<Auto, String>("modelo"));
            column_color.setCellValueFactory(new PropertyValueFactory<Auto, String>("color"));
            column_año.setCellValueFactory(new PropertyValueFactory<Auto, String>("Anho"));
            column_transmision.setCellValueFactory(new PropertyValueFactory<Auto, String>("Transmision"));
            column_carroceria.setCellValueFactory(new PropertyValueFactory<Auto, String>("carrocerria"));
            column_combustible.setCellValueFactory(new PropertyValueFactory<Auto, String>("combustible"));
            column_placa.setCellValueFactory(new PropertyValueFactory<Auto, String>("placa"));
            column_vin.setCellValueFactory(new PropertyValueFactory<Auto, String>("VIN"));
            column_chasis.setCellValueFactory(new PropertyValueFactory<Auto, String>("chasis"));
            column_categoria.setCellValueFactory(new PropertyValueFactory<Auto, String>("categoria"));
            column_precio.setCellValueFactory(new PropertyValueFactory<Auto, String>("precio"));
            column_deposito.setCellValueFactory(new PropertyValueFactory<Auto, String>("deposito"));
            column_habilitado.setCellValueFactory(new PropertyValueFactory<Auto, String>("estado"));
            column_estado.setCellValueFactory(new PropertyValueFactory<Auto, String>("isenabled"));
            tabla_auto.setItems(CRentaDialog.BuscarDatoAuto(fx_fecha_entrega.getValue(),fx_fecha_recibo.getValue()));
            //msjText2("LA RESERVA SE AGREGO CORRECTAMENTE");
        }catch (Exception E){
            msgerr(E.getMessage());
        }

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
            msjText("LA RESERVA SE AGREGO CORRECTAMENTE");
            Bloqueobotones(true,"AGREGAR");
        }catch (Exception E){
            msgerr(E.getMessage());
        }
    }

    public void msjText(String texto)
    {
        mensaje.setText("");
        mensaje.setText(texto);

    }

    public void GestionEvento2()
    {
        tabla_auto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Auto>() {
            @Override
            public void changed(ObservableValue<? extends Auto> observable, Auto oldValue, Auto newValue){
                fx_auto.setText(newValue.getMarca() + " " + newValue.getModelo());
                fx_id_auto.setText(String.valueOf(newValue.getIDAuto()));
                fx_costo_renta.setText(String.valueOf(Float.valueOf(newValue.getPrecio())));
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
        //fx_id_auto.setVisible(false);
        MostrandoDatosAuto();
        GestionEvento2();
        fx_id_auto.setVisible(false);
        fx_auto.setEditable(false);
        fx_costo_renta.setEditable(false);
    }

    private void Bloqueobotones(boolean b, String m)
    {
        if(m.equals("AGREGAR")) {
            fx_id_auto.setDisable(false);
            fx_auto.setDisable(false);
            fx_fecha_entrega.setDisable(false);
            fx_fecha_recibo.setDisable(false);
            fx_costo_renta.setDisable(false);
        }
        else{
            fx_id_auto.setDisable(b);
            fx_auto.setDisable(b);
            fx_fecha_entrega.setDisable(b);
            fx_fecha_recibo.setDisable(b);
            fx_costo_renta.setDisable(b);
        }
    }
}
