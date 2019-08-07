package therent.view.car;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.CAuto;
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
    private JFXTextField yrTxt;

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

    private BorderPane parentPane;

    private ObservableList<ModeloAuto> modelos;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        if(!edit){
            return;
        }
        vinTxt.setDisable(true);
        chasisTxt.setDisable(true);
        ModCmb.setDisable(true);
        transCmb.setDisable(true);
        yrTxt.setDisable(true);
    }

    public void setAut(Auto aut) {
        this.aut = aut;
        vinTxt.setText(aut.getVIN());
        chasisTxt.setText(aut.getChasis());
        colorTxt.setText(aut.getColor());
        placaTxt.setText(aut.getPlaca());
        yrTxt.setText(Integer.toString(aut.getAnho()));
        transCmb.setValue(aut.getTransmision());
        ModCmb.setValue(new ModeloAuto(-1, aut.getMarca(), aut.getModelo(), aut.getCategoria(), aut.getPrecio()));
    }

    public void setModelos(ObservableList<ModeloAuto> modelos) {
        this.modelos = modelos;
    }

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
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
        try {
            CAuto.addAuto(placaTxt.getText(), Integer.parseInt(yrTxt.getText()), chasisTxt.getText(), vinTxt.getText(), colorTxt.getText(), transCmb.getSelectionModel().getSelectedIndex(), ModCmb.getValue().getId_modelo());
        } catch (Exception e) {
            msgerr(e.getMessage());
            return;
        }

        msgconf("Se ha ingresado el auto correctamente");
        main.showCarOverview(parentPane);
    }

    @FXML
    public void handleBack(){
        main.showCarOverview(parentPane);
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
        al.showAndWait();
    }
}
