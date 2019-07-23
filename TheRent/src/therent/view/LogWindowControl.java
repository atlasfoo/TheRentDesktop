package therent.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import therent.Main;
import therent.control.CLogin;

public class LogWindowControl {

    @FXML
    private JFXTextField usrtxt;

    @FXML
    private JFXPasswordField pswdtxt;

    @FXML
    private JFXButton logbtn;



    public void setMain(Main main) {
        this.main = main;
    }

    public boolean isLogged(){
        return this.logged;
    }


    //para acceder al metodo de mostrar ventana principal
    private Main main;
    //muestra si se ha iniciado sesion
    private boolean logged=false;
    //permite cerrar la ventana
    private Stage dlgStage;

    @FXML
    public void initialize(){
        //nada para inicializar
    }
    @FXML
    public void handleLogin(){
        //rol de usuario
        String role=null;
        //el metodo de modelo retornará el rol
        try {
            role=CLogin.Login(usrtxt.getText(), pswdtxt.getText());
        } catch (Exception e) {
            msgerr(e.getMessage());
            return;
        }
        //si el rol no se obtuvo o fue denegado
        if(role==null || role.equals("DENEGADO")) {
            msgerr("El usuario o la contraseña son incorrectos");
            usrtxt.setText("");
            pswdtxt.setText("");
            return;
        }
        //si no, se abre la ventana principal enviando el rol
        main.showMainFrame(role);
        //y se cierra la ventana actual
        dlgStage.close();
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

    public void setDlgStage(Stage dlgStage) {
        this.dlgStage = dlgStage;
    }
}
