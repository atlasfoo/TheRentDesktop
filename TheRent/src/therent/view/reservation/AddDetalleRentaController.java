package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.car.CAuto;
import therent.control.reservation.CDetalleRenta;
import therent.model.beans.Auto;
import therent.model.beans.DetalleRenta;
import therent.model.beans.Renta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddDetalleRentaController {

    @FXML
    private TableView<Auto> tabla_reservas;


    @FXML
    private TableColumn<Auto, String> column_marca;

    @FXML
    private TableColumn<Auto, String> column_modelo;

    @FXML
    private TableColumn<Auto, Integer> column_año;

    @FXML
    private TableColumn<Auto, String> column_color;


    @FXML
    private JFXDatePicker fx_date_fecha_entrega;

    @FXML
    private JFXDatePicker fx_date_fecha_recibo;

    @FXML
    private  TableColumn<Auto,String> column_Placa;

    @FXML
    private JFXButton buscarBtn;

    private Main main;

    private DetalleRenta dr;

    private BorderPane parentPane;

    private ObservableList<Auto> autos;

    //puntero de renta para regresar al caso anterior
    private Renta r;

    private boolean edit;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setDr(DetalleRenta dr) {
        this.dr = dr;
        if(dr!=null){
            fx_date_fecha_entrega.setValue(dr.getF_in());
            fx_date_fecha_recibo.setValue(dr.getF_out());
            autos=FXCollections.observableArrayList();
            System.out.println(dr.getPlaca());
            try {
                Auto a=CAuto.getbyPlaca(dr.getPlaca());
                if(a==null){
                    msgerr("Internal error");
                    return;
                }
                autos.add(a);
                tabla_reservas.setItems(autos);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
        buscarBtn.setDisable(true);
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setR(Renta r) {
        this.r = r;
    }

    public void initialize(){
        column_marca.setCellValueFactory(dv->dv.getValue().marcaProperty());
        column_modelo.setCellValueFactory(dv->dv.getValue().modeloProperty());
        column_año.setCellValueFactory(dv -> dv.getValue().anhoProperty().asObject());
        column_color.setCellValueFactory(dv->dv.getValue().colorProperty());
        column_Placa.setCellValueFactory(dv->dv.getValue().placaProperty());
    }

    public void getDispAutos(){
        try {
            this.autos= FXCollections.observableArrayList(CAuto.getDisponibility(fx_date_fecha_entrega.getValue(), fx_date_fecha_recibo.getValue()));
            tabla_reservas.setItems(this.autos);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }

    @FXML
    public void handleGetAutos(){
        getDispAutos();
    }

    @FXML
    public void handleBack(){
        main.showRentDetail(parentPane, r);
    }

    public void handleSave(){
        Auto aut=tabla_reservas.getSelectionModel().getSelectedItem();
        if(aut==null){
            msgerr("Seleccione un auto de la tabla");
            return;
        }
        if(edit){
            try {
                if(CDetalleRenta.editDetail(this.dr.getId(), aut.getIDAuto(), fx_date_fecha_entrega.getValue(), fx_date_fecha_recibo.getValue())){
                    msgconf("Se ha actualizado correctamente!");
                    main.showRentDetail(parentPane,r);
                    return;
                }else{
                    msgerr("No se ha podido actualizar!\nEl auto ya está ocupado en el rango de fechas ingresado");
                    main.showRentDetail(parentPane, r);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                CDetalleRenta.insertDetail(r.getId(), aut.getIDAuto(), fx_date_fecha_entrega.getValue(), fx_date_fecha_recibo.getValue());
                main.showRentDetail(parentPane, r);
            } catch (Exception e) {
                msgerr(e.getMessage());
            }
        }
    }

    private static void msgerr(String msg) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    @FXML
    public void msgconf(String msg){
        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("TheRent Link System");
        al.setHeaderText("INFORMACIÓN");
        al.setContentText(msg);
        al.getButtonTypes().setAll(ButtonType.OK);
        al.showAndWait();
    }
}
