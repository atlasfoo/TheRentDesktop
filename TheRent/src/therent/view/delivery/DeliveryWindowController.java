package therent.view.delivery;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import therent.Main;
import therent.control.deliver.CDeliver;
import therent.model.beans.DetalleEntrega;

import java.sql.SQLException;

public class DeliveryWindowController{

    @FXML
    private Tab tab_pane_entrega;

    @FXML
    private TableView<DetalleEntrega> tabla_entrega;

    @FXML
    private TableColumn<DetalleEntrega, String> column_auto;

    @FXML
    private TableColumn<DetalleEntrega, Integer> column_año;

    @FXML
    private TableColumn<DetalleEntrega, String> column_color;

    @FXML
    private TableColumn<DetalleEntrega, String> column_placa;

    @FXML
    private TableColumn<DetalleEntrega, String> column_cliente;

    @FXML
    private Tab tab_pane_recepcion;

    @FXML
    private TableView<DetalleEntrega> tabla_recepcion;

    @FXML
    private TableColumn<DetalleEntrega, String> column_auto2;

    @FXML
    private TableColumn<DetalleEntrega, Integer> column_año2;

    @FXML
    private TableColumn<DetalleEntrega, String> column_color2;

    @FXML
    private TableColumn<DetalleEntrega, String> column_placa2;

    @FXML
    private TableColumn<DetalleEntrega, String> column_cliente2;

    @FXML
    private JFXButton fx_button_entrega;

    @FXML
    private JFXButton fx_button_recibo;

    private ObservableList<DetalleEntrega> aEntregar;

    private ObservableList<DetalleEntrega> aRecibir;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
        column_auto.setCellValueFactory(dv->dv.getValue().autoProperty());
        column_año.setCellValueFactory(dv->dv.getValue().añoProperty().asObject());
        column_color.setCellValueFactory(dv->dv.getValue().colorProperty());
        column_placa.setCellValueFactory(dv->dv.getValue().placaProperty());
        column_cliente.setCellValueFactory(dv->dv.getValue().clienteProperty());

        column_auto2.setCellValueFactory(dv->dv.getValue().autoProperty());
        column_año2.setCellValueFactory(dv->dv.getValue().añoProperty().asObject());
        column_color2.setCellValueFactory(dv->dv.getValue().colorProperty());
        column_placa2.setCellValueFactory(dv->dv.getValue().placaProperty());
        column_cliente2.setCellValueFactory(dv->dv.getValue().clienteProperty());

        refreshTables();
    }

    public void refreshTables(){
        try {
            aEntregar= FXCollections.observableArrayList(CDeliver.aEntregar());
            aRecibir=FXCollections.observableArrayList(CDeliver.aRecibir());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            tabla_entrega.setItems(aEntregar);
            tabla_recepcion.setItems(aRecibir);
        }

    }

    @FXML
    public void handleEntrega(){
        main.showEntregar(false);
    }

    @FXML
    public void handleRecibo(){
        main.showEntregar(true);
    }

    @FXML
    public void handleBack(){
        main.redirectMain();
    }

}

