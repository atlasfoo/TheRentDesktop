package therent.view.reservation;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import therent.Main;

public class MainRentControl {

    @FXML
    private JFXButton reservaBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private BorderPane parentPane;

    private Main main;

    public void setMain(Main main) {
        this.main = main;

    }

    public BorderPane getParentPane() {
        return parentPane;
    }

    public void initialize(){}

    @FXML
    public void handleReservation(){
        main.showAddRent();
        main.showRentOverview(parentPane);
    }


}
