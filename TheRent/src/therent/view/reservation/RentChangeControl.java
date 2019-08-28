package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import therent.control.reservation.CRenta;
import therent.model.beans.Renta;

import java.sql.SQLException;

public class RentChangeControl {

    @FXML
    private JFXComboBox<String> statCmb;

    @FXML
    private JFXButton okBtn;

    private Renta r;

    private Stage dlgStage;

    public void setR(Renta r) {
        this.r = r;
    }

    public void setDlgStage(Stage dlgStage) {
        this.dlgStage = dlgStage;
    }

    public void initialize(){
        statCmb.getItems().addAll("RESERVADO", "PAGADO", "CANCELADO");
    }

    public void handleOk(){
        try {
            CRenta.changeStat(r.getId(), statCmb.getSelectionModel().getSelectedIndex());
        } catch (SQLException e) {
            msgerr(e.getMessage());
        } finally {
            dlgStage.close();
        }
    }
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }
}
