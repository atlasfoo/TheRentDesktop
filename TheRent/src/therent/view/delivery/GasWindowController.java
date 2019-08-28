package therent.view.delivery;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import therent.Main;
import therent.control.deliver.CDeliver;
import therent.model.beans.DetalleEntrega;
import therent.util.Validators;

public class GasWindowController {

    @FXML
    private Slider fx_slider_gas;

    @FXML
    private JFXButton fx_button_ok;

    @FXML
    private JFXTextField kmTxt;

    @FXML
    private JFXTextArea detTxta;


    private Main main;

    private Stage dlgStage;

    private boolean isRecibo;

    private DetalleEntrega dr;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setDlgStage(Stage dlgStage) {
        this.dlgStage = dlgStage;
    }

    public void setRecibo(boolean recibo) {
        isRecibo = recibo;
    }

    public void setDr(DetalleEntrega dr) {
        this.dr = dr;
    }

    public void initialize(){
        //validators
        Validators.ValidarCampos(kmTxt,"Numero");
        //-----------------------------

   }

   public void handleSav(){
        String tipo = (isRecibo)? "RECIBO": "ENTREGA";
       try {
           CDeliver.addEntrega(dr.getId(),Long.parseLong(kmTxt.getText()),(float)fx_slider_gas.getValue(), detTxta.getText(),tipo,main.getActive_session().getId());
       } catch (Exception e) {
           msgerr(e.getMessage());
       }finally {
           dlgStage.close();
       }
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
