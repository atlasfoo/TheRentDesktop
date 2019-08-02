package therent.view.car;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import therent.model.beans.Auto;

import java.util.List;

public class CarOverviewControl {

    @FXML
    private TableView<Auto> carTable;

    @FXML
    private TableColumn<Auto, String> marcColumn;

    @FXML
    private TableColumn<Auto, String> modColumn;

    @FXML
    private TableColumn<Auto, String> colorColumn;

    @FXML
    private TableColumn<Auto, Integer> yrColumn;

    @FXML
    private Label marcaLbl;

    @FXML
    private Label modLbl;

    @FXML
    private Label colLbl;

    @FXML
    private Label yrLbl;

    @FXML
    private Label transLbl;

    @FXML
    private Label combLbl;

    @FXML
    private Label placLbl;

    @FXML
    private Label vinLbl;

    @FXML
    private Label catLbl;

    @FXML
    private Label precLbl;

    @FXML
    private Label depoLbl;

    @FXML
    private Label estLbl;

    @FXML
    private Label ishabLbl;

    private List<Auto> autos;

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public void initialize(){

    }
}

