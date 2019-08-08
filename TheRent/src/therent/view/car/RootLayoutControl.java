package therent.view.car;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import therent.Main;

public class RootLayoutControl {
    @FXML
    private JFXButton newbtn;

    @FXML
    private JFXButton editbtn;

    @FXML
    private JFXButton enablebtn;

    @FXML
    private JFXButton newmodbtn;

    @FXML
    private JFXButton mantbtn;

    @FXML
    private JFXButton BackBtn;

    public BorderPane getSidepane() {
        return sidepane;
    }

    public void setSidepane(BorderPane sidepane) {
        this.sidepane = sidepane;
    }

    @FXML
    private BorderPane sidepane;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
    }

    public void handlebackmn(){
        main.redirectMain();
    }

    public void handleNewCar() { main.showNewCar(this.sidepane);}

    public void handleNewModel(){main.showNewModel(this.sidepane);}

}
