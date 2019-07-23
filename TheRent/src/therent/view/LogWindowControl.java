package therent.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import therent.control.CLogin;

public class LogWindowControl {

    @FXML
    private JFXTextField usrtxt;

    @FXML
    private JFXPasswordField pswdtxt;

    @FXML
    private JFXButton logbtn;

    @FXML
    public void initialize(){
        //nada para inicializar
    }
    @FXML
    public void handleLogin(){
        try {
            String role=CLogin.Login(usrtxt.getText(), pswdtxt.getText());
            msgerr(role);
        } catch (Exception e) {
            msgerr(e.getMessage());
        }
    }
    //mensaje de error genérico
    @FXML
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

}
