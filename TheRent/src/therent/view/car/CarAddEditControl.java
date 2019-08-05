package therent.view.car;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import therent.Main;
import therent.control.CModeloAuto;
import therent.model.beans.Auto;
import therent.model.beans.ModeloAuto;
import therent.util.ModelConverter;

import java.util.List;

public class CarAddEditControl {


    @FXML
    private JFXTextField placaTxt;

    @FXML
    private JFXTextField chasisTxt;

    @FXML
    private JFXTextField vinTxt;

    @FXML
    private JFXTextField colorTxt;

    @FXML
    private JFXComboBox<String> transCmb;

    @FXML
    private JFXComboBox<ModeloAuto> ModCmb;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private JFXButton backBtn;

    private Main main;

    private boolean isEdit;

    private Auto aut;

    private RootLayoutControl ctrl;

    private ObservableList<ModeloAuto> modelos;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public void setAut(Auto aut) {
        this.aut = aut;
    }

    public void setModelos(ObservableList<ModeloAuto> modelos) {
        this.modelos = modelos;
    }

    public void setCtrl(RootLayoutControl ctrl) {
        this.ctrl = ctrl;
    }

    public void initialize(){
        transCmb.getItems().addAll("Manual", "Automático");
        try {
            modelos= FXCollections.observableArrayList(CModeloAuto.getAllModelos());
            ModCmb.setConverter(new ModelConverter());
            ModCmb.setItems(modelos);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }



    @FXML
    public void handleSubmit(){
        main.showCarOverview(ctrl.getSidepane());
    }

    @FXML
    public void handleBack(){
        main.showCarOverview(ctrl.getSidepane());
    }

    @FXML
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }
}

