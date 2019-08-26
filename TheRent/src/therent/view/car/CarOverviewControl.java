package therent.view.car;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.auto.CAuto;
import therent.model.beans.Auto;

import java.util.List;

public class CarOverviewControl {

    @FXML
    private TableView<Auto> carTable;

    @FXML
    private TableColumn<Auto, String> marcColumn;

    @FXML
    private TableColumn<Auto, String> modColumn;

    @FXML
    private TableColumn<Auto, String> colorColumn;

    @FXML
    private TableColumn<Auto, Integer> yrColumn;

    @FXML
    private Label marcaLbl;

    @FXML
    private Label modLbl;

    @FXML
    private Label colLbl;

    @FXML
    private Label yrLbl;

    @FXML
    private Label transLbl;

    @FXML
    private Label combLbl;

    @FXML
    private Label placLbl;

    @FXML
    private Label carrLbl;

    @FXML
    private Label vinLbl;

    @FXML
    private Label catLbl;

    @FXML
    private Label precLbl;

    @FXML
    private Label depoLbl;

    @FXML
    private Label estLbl;

    @FXML
    private Label ishabLbl;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton enableBtn;

    private BorderPane parentPane;

    private Main main;

    private ObservableList<Auto> autos;

    public void setAutos(List<Auto> autos) {
        this.autos = FXCollections.observableArrayList(autos);
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
        refreshAutos();
        marcColumn.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        modColumn.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        yrColumn.setCellValueFactory(cellData -> cellData.getValue().anhoProperty().asObject());
        colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        // Limpiar vista de detalles
        carOverview(null);
        carTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldvalue, newvalue) -> carOverview(newvalue)
                ));
    }

    private void refreshAutos() {
        try {
            this.autos = FXCollections.observableArrayList(CAuto.getAutos());
        } catch (Exception ex) {
            msgerr(ex.getMessage());
        } finally {
            carTable.setItems(this.autos);
        }
    }


    private void carOverview(Auto auto) {
        if (auto == null) {
            marcaLbl.setText("---");
            modLbl.setText("---");
            colLbl.setText("---");
            yrLbl.setText(("---"));
            transLbl.setText("---");
            combLbl.setText("---");
            placLbl.setText("---");
            vinLbl.setText("---");
            catLbl.setText("---");
            carrLbl.setText("---");
            precLbl.setText(("---"));
            depoLbl.setText(("---"));
            ishabLbl.setText("---");
            estLbl.setText("---");
            return;
        }

        marcaLbl.setText(auto.getMarca());
        modLbl.setText(auto.getModelo());
        colLbl.setText(auto.getColor());
        yrLbl.setText(Integer.toString(auto.getAnho()));
        transLbl.setText(auto.getTransmision());
        combLbl.setText(auto.getCombustible());
        placLbl.setText(auto.getPlaca());
        vinLbl.setText(auto.getVIN());
        catLbl.setText(auto.getCategoria());
        carrLbl.setText(auto.getCarrocerria());
        precLbl.setText(Float.toString(auto.getPrecio()));
        depoLbl.setText(Float.toString(auto.getDeposito()));
        ishabLbl.setText(auto.getIsenabled());
        estLbl.setText(auto.getEstado());
    }

    @FXML
    private void handleEdit(){
        Auto selected=carTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            msgerr("Por favor seleccione un vehiculo para editar.");
            return;
        }
        main.showEditCar(parentPane,selected);
    }

    @FXML
    private void handleEnableDisable(){
        Auto selected=carTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            msgerr("Por favor seleccione un vehículo de la lista");
            return;
        }
        try {
            CAuto.enable_disable_auto(selected.getIDAuto());
        } catch (Exception e) {
            msgerr(e.getMessage());
            return;
        }finally {
            refreshAutos();
        }
        msgconf("Hecho");

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

