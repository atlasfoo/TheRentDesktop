package therent.view.delivery;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import therent.Main;

public class GasWindowController {

    @FXML
    private Slider fx_slider_gas;

    @FXML
    private JFXButton fx_button_ok;

    @FXML
    private JFXTextField kmTxt;

    @FXML
    private JFXTextArea detTxta;

    @FXML
    private Label combLbl;

    private Main main;

    private Stage dlgStage;

    private boolean isRecibo;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setDlgStage(Stage dlgStage) {
        this.dlgStage = dlgStage;
    }

    public void setRecibo(boolean recibo) {
        isRecibo = recibo;
    }

    public void initialize(){
        fx_slider_gas.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
                combLbl.textProperty().setValue(String.valueOf((float)fx_slider_gas.getValue()));
            } });
   }

    public void labelSet(String s){
        combLbl.setText(s);
    }
}
