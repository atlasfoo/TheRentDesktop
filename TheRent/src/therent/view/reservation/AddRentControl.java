package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import therent.Main;
import therent.control.client.CCliente;
import therent.control.reservation.CRenta;
import therent.model.beans.ClientBean;

public class AddRentControl {
    @FXML
    private TableView<ClientBean> clientTab;

    @FXML
    private TableColumn<ClientBean, String> nomCol;

    @FXML
    private TableColumn<ClientBean, String> cedCol;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXTextField searchField;

    private ObservableList<ClientBean> cl;

    private Stage dlgStage;

    private Main main;

    public void setDlgStage(Stage dlgStage) {
        this.dlgStage = dlgStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
        refreshtable();
        nomCol.setCellValueFactory(cd->cd.getValue().nombreProperty());
        cedCol.setCellValueFactory(cd->cd.getValue().dniProperty());
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> refreshtable()));
    }

    public void refreshtable(){
        try {
            this.cl= FXCollections.observableArrayList(CCliente.ovClient(searchField.getText()));
            clientTab.setItems(this.cl);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }

    public void searchTextChanged(){
        refreshtable();
    }

    @FXML
    public void handleSubmit(){
        if(clientTab.getSelectionModel().getSelectedItem()==null){
            msgerr("Por favor seleccione un cliente de la tabla");
            return;
        }
        try {
            CRenta.addRenta(clientTab.getSelectionModel().getSelectedItem().getId(), main.getActive_session().getId());
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
        dlgStage.close();
    }

    @FXML
    public void handleBack(){
        dlgStage.close();
    }

    private static void msgerr(String msg) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

}
