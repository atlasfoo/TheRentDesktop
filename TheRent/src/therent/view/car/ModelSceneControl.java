package therent.view.car;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import therent.Main;
import therent.control.CCategoria;
import therent.control.CModeloAuto;
import therent.model.beans.Categoria;
import therent.util.converters.CategoriaConverter;

import javax.swing.border.Border;

public class ModelSceneControl {
    @FXML
    private JFXTextField marcaTxt;

    @FXML
    private JFXTextField modeloTxt;

    @FXML
    private JFXTextField motorTxt;

    @FXML
    private JFXComboBox<String> combCmb;

    @FXML
    private JFXComboBox<Categoria> catCmb;

    @FXML
    private JFXComboBox<String> carrCmb;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private JFXButton backBtn;

    private BorderPane parentPane;

    private Main main;

    private ObservableList<Categoria> categorias;

    public void setParentPane(BorderPane parentPane) {
        this.parentPane = parentPane;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setCategorias(ObservableList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void initialize(){
        combCmb.getItems().addAll("GASOLINA", "DIESEL", "HIBRIDO");
        carrCmb.getItems().addAll("Sedán", "Hatchback", "PickUp", "Liftback", "Coupé", "Berlina", "Camioneta cerrada");
        try {
            this.categorias= FXCollections.observableArrayList(CCategoria.getAll());
            catCmb.setConverter(new CategoriaConverter());
            catCmb.setItems(this.categorias);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }

    @FXML
    private void handleSumbit(){
        try {
            CModeloAuto.newModelo(marcaTxt.getText(),modeloTxt.getText(),motorTxt.getText(),carrCmb.getSelectionModel().getSelectedItem(),combCmb.getSelectionModel().getSelectedIndex(),catCmb.getSelectionModel().getSelectedItem().getId_categoria());
        } catch (Exception e) {
            msgerr(e.getMessage());
            return;
        }
        msgconf("Se ha agregado el modelo correctamente");
        main.showCarOverview(parentPane);

    }

    @FXML
    public void handleBack(){
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
