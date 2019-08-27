package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.reservation.CRenta;
import therent.model.beans.Renta;

import java.time.LocalDate;
import java.util.Observable;

public class RentOverviewControl {
    @FXML
    private TableView<Renta> rentTable;

    @FXML
    private TableColumn<Renta, String> clientCol;

    @FXML
    private TableColumn<Renta, String> empCol;

    @FXML
    private TableColumn<Renta, LocalDate> dateCol;

    @FXML
    private TableColumn<Renta, String> estCol;

    @FXML
    private JFXButton detalleBtn;

    @FXML
    private JFXButton statchangeBtn;

    private Main main;

    private BorderPane parentPane;

    private ObservableList<Renta> rentas;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void initialize(){
        refreshTable();
        clientCol.setCellValueFactory(cellData -> cellData.getValue().clienteProperty());
        empCol.setCellValueFactory(cellData -> cellData.getValue().empleadoProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        estCol.setCellValueFactory(cellData->cellData.getValue().estadoProperty());
    }

    public void refreshTable(){
        try {
            this.rentas= FXCollections.observableArrayList(CRenta.getRentas());
        } catch (Exception e) {
            msgerr(e.getMessage());
        }finally {
            rentTable.setItems(this.rentas);
        }
    }

    @FXML
    public void handleRentDetail(){
        if(rentTable.getSelectionModel().getSelectedItem()==null){
            msgerr("Por favor seleccione una renta a detallar");
            return;
        }
        main.showRentDetail(this.parentPane, rentTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void msgerr(String msg){
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
