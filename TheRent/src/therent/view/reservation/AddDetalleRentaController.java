package therent.view.reservation;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddDetalleRentaController {

    @FXML
    private TableView<?> tabla_reservas;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<?, ?> column_marca;

    @FXML
    private TableColumn<?, ?> column_modelo;

    @FXML
    private TableColumn<?, ?> column_año;

    @FXML
    private TableColumn<?, ?> column_color;

    @FXML
    private JFXTextField fx_id_detalle_renta;

    @FXML
    private JFXDatePicker fx_date_fecha_entrega;

    @FXML
    private JFXDatePicker fx_date_fecha_recibo;

}
