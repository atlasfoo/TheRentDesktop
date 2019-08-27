package therent.view.car;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import therent.control.car.CAuto;
import therent.control.car.CMantenimiento;
import therent.model.beans.Auto;
import therent.Main;
public class AddMantControl {

    @FXML
    private TableView<Auto> carTable;

    @FXML
    private TableColumn<Auto, String> marcaCol;

    @FXML
    private TableColumn<Auto, String> modeloCol;

    @FXML
    private TableColumn<Auto, Integer> añoCol;

    @FXML
    private TableColumn<Auto, String> colorCol;

    @FXML
    private TableColumn<Auto, String> placaCol;

    @FXML
    private JFXDatePicker f_inDatepick;

    @FXML
    private JFXDatePicker f_outDatepick;

    @FXML
    private JFXTextArea descrTxta;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private JFXButton backBtn;

    private Main main;

    private BorderPane parentPane;

    private ObservableList<Auto> autos;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void initialize(){
        marcaCol.setCellValueFactory(obj->obj.getValue().marcaProperty());
        modeloCol.setCellValueFactory(obj->obj.getValue().modeloProperty());
        añoCol.setCellValueFactory(obj->obj.getValue().anhoProperty().asObject());
        colorCol.setCellValueFactory(obj->obj.getValue().colorProperty());
        placaCol.setCellValueFactory(obj->obj.getValue().placaProperty());
        refreshAutos();
    }

    private void refreshAutos(){
        try {
            this.autos= FXCollections.observableArrayList(CAuto.getAutos());
            this.carTable.setItems(this.autos);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }
    @FXML
    private void handleSubmit(){
        Auto selected=carTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            msgerr("Seleccione un auto");
            return;
        }

        try {
            CMantenimiento.addMant(f_inDatepick.getValue(), f_outDatepick.getValue(), descrTxta.getText(), selected.getIDAuto());
        } catch (Exception e) {
            msgerr(e.getMessage());
            return;
        }
        msgconf("Se ha ingresado correctamente");
        main.showCarOverview(parentPane);
    }
    @FXML
    private void handleBack(){
        main.showCarOverview(parentPane);
    }

    @FXML
    private void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    @FXML
    private void msgconf(String msg){
        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("TheRent Link System");
        al.setHeaderText("INFORMACIÓN");
        al.setContentText(msg);
        al.getButtonTypes().setAll(ButtonType.OK);
        al.showAndWait();
    }
}
