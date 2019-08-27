package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.reservation.CDetalleRenta;
import therent.model.beans.DetalleRenta;
import therent.model.beans.Renta;


import java.sql.SQLException;
import java.time.LocalDate;

public class RentDetailControl {
    @FXML
    private TableView<DetalleRenta> detTab;

    @FXML
    private TableColumn<DetalleRenta, String> autoCol;

    @FXML
    private TableColumn<DetalleRenta, Integer> yrCol;

    @FXML
    private TableColumn<DetalleRenta, String> colorCol;

    @FXML
    private TableColumn<DetalleRenta, String> placCol;

    @FXML
    private TableColumn<DetalleRenta, LocalDate> f_inCol;

    @FXML
    private TableColumn<DetalleRenta, LocalDate> f_outCol;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton borrBtn;

    @FXML
    private JFXButton backBtn;

    private Main main;

    private BorderPane parentPane;

    private ObservableList<DetalleRenta> dr;

    private Renta r;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setR(Renta r) {
        this.r = r;
        refreshDetail();
    }

    public void initialize(){
        autoCol.setCellValueFactory(dv->dv.getValue().autoProperty());
        yrCol.setCellValueFactory(dv->dv.getValue().añoProperty().asObject());
        colorCol.setCellValueFactory(dv->dv.getValue().colorProperty());
        placCol.setCellValueFactory(dv->dv.getValue().placaProperty());
        f_inCol.setCellValueFactory(dv->dv.getValue().f_inProperty());
        f_outCol.setCellValueFactory(dv->dv.getValue().f_outProperty());
    }

    public void refreshDetail(){
        try {
            this.dr= FXCollections.observableArrayList(CDetalleRenta.allDetail(r.getId()));
        } catch (SQLException e) {
            msgerr(e.getMessage());
        }finally {
            detTab.setItems(this.dr);
        }
    }
    //Metodo para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

}
