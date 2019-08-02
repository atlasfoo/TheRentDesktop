
package therent.view.Cliente;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import therent.control.CCliente;
import therent.model.Cliente;

import javax.xml.transform.sax.SAXSource;
import java.awt.event.*;
import java.net.URL;
import java.security.Key;
import java.util.EventListener;
import java.util.ResourceBundle;

public class control_MenuCliente implements Initializable {

    @FXML
    private JFXButton idGuardar;

    @FXML
    private Text idTextoMensaje;

    @FXML
    private JFXTextField idPNombre;

    @FXML
    private JFXTextField idSNombre;

    @FXML
    private JFXTextField idPApellido;

    @FXML
    private JFXTextField idSApellido;

    @FXML
    private JFXTextField idCedula;

    @FXML
    private JFXTextField idDireccion;

    @FXML
    private JFXComboBox<String> idTipoCliente;

    @FXML
    private TableView<Cliente> TablaDatos;

    @FXML
    private TableColumn<Cliente, String> cNombre;

    @FXML
    private TableColumn<Cliente, String> cApellido;

    @FXML
    private TableColumn<Cliente, String> cCedula;

    @FXML
    private TableColumn<Cliente, String> cDireccion;

    @FXML
    private TableColumn<Cliente, String> cTipoCliente;

    @FXML
    private TableColumn<Cliente, String> cEstado;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer plegable;

    @FXML
    private JFXDrawer plegable1;
    @Override

    public void initialize(URL location, ResourceBundle resources) {

        try{
            CargaMenu();

            initHamburger();

            MostrandoDatos();

        }catch (Exception e){msgerr(e.getMessage());}


        ObservableList<String> lis = FXCollections.observableArrayList();
        lis.add("Convencional"); //Revisar el getValue
        lis.add("Turista");
        lis.add("Ejecutivo");
        idTipoCliente.setItems(lis);

    }


    /*Efecto de transicion cuando se da click*/
    public void initHamburger()
    {

        HamburgerNextArrowBasicTransition transition = new HamburgerNextArrowBasicTransition(menu);
        transition.setRate(-1);

        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
                {
                    transition.setRate(transition.getRate() * -1);
                    transition.play();

                    if (plegable.isOpened())
                        plegable.close();
                    else
                        plegable.open();

                }
        );
    }

    //Metodo para cargar el menu, carga el vbox
    public void CargaMenu() throws Exception
    {
        HBox box;
        /*Se carga las opciones del menu (el otro fxml ) dentro del jfxdrawer*/

            box = FXMLLoader.load(getClass().getResource("HboxOpciones.fxml"));
            plegable.setSidePane(box);

            for (Node ob : box.getChildren()) {
                if (ob.getAccessibleText() != null) {
                    ob.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
                    {
                        switch (ob.getAccessibleText()) {
                            case "agregar":
                                Bloqueobotones(false,"agregar");
                                break;
                            case "buscar":
                                Bloqueobotones(true,"buscar");
                                CargandoHboxBusqueda();
                                break;
                            case "modificar":
                                Bloqueobotones(false,"modificar");
                                break;
                            case "deshabilitar":
                                Bloqueobotones(true,"deshabilitar");
                                idGuardar.setText("Deshabilitar");
                                idGuardar.setDisable(false);

                                break;

                        }
                    });
                }
            }
    }
    //metodo para bloquear los campos de texto de acorde al boton seleccionado
    public void Bloqueobotones(boolean b, String m)
    {
        if(m.equals("modificar")) {
            idPNombre.setDisable(b);
            idSNombre.setDisable(b);
            idPApellido.setDisable(b);
            idSApellido.setDisable(b);
            idDireccion.setDisable(b);
            idGuardar.setDisable(b);
        }
        else{
        idPNombre.setDisable(b);
        idSNombre.setDisable(b);
        idPApellido.setDisable(b);
        idSApellido.setDisable(b);
        idCedula.setDisable(b);
        idDireccion.setDisable(b);
        idGuardar.setDisable(b);
        idTipoCliente.setDisable(b);
        }
    }

    //Cargando la barra de busqueda al tocar la opcion Buscar.
    public void CargandoHboxBusqueda()
    {
        HBox box1;
        try {
            if (plegable1.isOpened())
            {
                plegable1.close();
            }
            else
                {
                plegable1.open();

            box1 = FXMLLoader.load(getClass().getResource("HboxBusqueda.fxml"));
            plegable1.setSidePane(box1);
            /* -------------------------*/
            for (Node ob : box1.getChildren()) {
                if (ob.getAccessibleText() != null) {
                    ob.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->
                    {
                        switch (ob.getAccessibleText()) {
                            case "BuscarRegistro":
                                System.out.println(text);
                                     MostrandoDatosBuscados(text);
                                break;
                        }
                    });
                }
            }
                }
            /* -------------------------*/
        }catch (Exception E){msgerr(E.getMessage());}
    }
    //Evento de boton para agregar un cliente
    @FXML
     void AgregandoCliente()
    {
            try {
                    CCliente.IngresarCliente(idPNombre.getText(), idSNombre.getText(), idPApellido.getText(), idSApellido.getText()
                            , idCedula.getText(), idDireccion.getText(),idTipoCliente.getValue());
                    MostrandoDatos();
                    // msgerr(idTipoCliente.getValue());
                }catch (Exception E){msgerr(E.getMessage());}

    }
    // Metodo para enviar los datos a la tabla
    public void MostrandoDatos()
    {
        //columnas
        cNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre1"));
        cApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido1"));
        cCedula.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cedula"));
        cDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        cEstado.setCellValueFactory(new PropertyValueFactory<Cliente,String>("estado"));
        cTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        TablaDatos.setItems(CCliente.MostrarDatos());
        //Cuando se da click mostrar los registros en sus respectivos campos
        GestionEvento();
    }

    //Metodo para mostrar los datos buscados.
    public void MostrandoDatosBuscados(String a)
    {

        // control_HboxBusqueda c = new control_HboxBusqueda();
        //columnas
        cNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre1"));
        cApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido1"));
        cCedula.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cedula"));
        cDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        cEstado.setCellValueFactory(new PropertyValueFactory<Cliente,String>("estado"));
        cTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        TablaDatos.setItems(CCliente.BuscarDato(a));
        //Cuando se da click mostrar los registros en sus respectivos campos
        GestionEvento();
    }


    //Evento para mandar mensajes
    public void msgerr(String msg){
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }

    //Metodo que gestiona el metodo change, para cuando se selecciona una fila de la tabla se ballan los registros de las tablas a los campos
    public void GestionEvento()
    {
        TablaDatos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
            @Override
            public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
                idPNombre.setText(newValue.getNombre1());
                idSNombre.setText(newValue.getNombre2());
                idPApellido.setText(newValue.getApellido1());
                idSApellido.setText(newValue.getApellido2() );
                idCedula.setText(newValue.getCedula());
                idDireccion.setText(newValue.getDireccion());
                idTipoCliente.setValue(newValue.gettipocliente());
            }
        });
    }

    public static String text;

}

