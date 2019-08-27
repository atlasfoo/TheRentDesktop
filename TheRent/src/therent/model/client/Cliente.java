package therent.model.client;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.scripts.JD;
import therent.model.beans.ClientBean;
import therent.util.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {


    private StringProperty nombre1;
    private StringProperty nombre2;
    private StringProperty apellido1;
    private StringProperty apellido2;
    private StringProperty cedula ;
    private StringProperty direccion;
    private StringProperty tipoCliente;
    private StringProperty estado;
    private IntegerProperty id;
    private StringProperty tel;
    private StringProperty correo;

   public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String cedula, String direccion, String tipocliente)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
        this.nombre2 = new SimpleStringProperty(nombre2);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.cedula = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipoCliente = new SimpleStringProperty(tipocliente);
    }

    public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String cedula, String direccion, String tipocliente, String estado)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
        this.nombre2 = new SimpleStringProperty(nombre2);
        this.apellido1 = new SimpleStringProperty(apellido1);
        this.apellido2 = new SimpleStringProperty(apellido2);
        this.cedula = new SimpleStringProperty(cedula);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipoCliente = new SimpleStringProperty(tipocliente);
        this.estado = new SimpleStringProperty(estado);
    }

    public Cliente(){}

    public  Cliente(String n){this.tel = new SimpleStringProperty(n);}

    public Cliente(String correo,String vacio){this.correo = new SimpleStringProperty(correo);}


    public Integer getId(){return id.get();}

    public void setId(Integer id){this.id =  new SimpleIntegerProperty(id);}

    public String getNombre1()
    {
        return  nombre1.get();
    }

    public void setNombre1(String nombre1)
    {
        this.nombre1 = new SimpleStringProperty(nombre1);
    }

    public String getNombre2()
    {
        return  nombre2.get();
    }

    public void setNombre2(String nombre2)
    {
        this.nombre2 = new SimpleStringProperty(nombre2);
    }

    public String getApellido1()
    {
        return  apellido1.get();
    }

    public void setApellido1(String apellido1)
    {
        this.apellido1 = new SimpleStringProperty(apellido1);
    }

    public String getApellido2()
    {
        return  apellido2.get();
    }

    public void setApellido2(String apellido2)
    {
        this.apellido2 = new SimpleStringProperty(apellido2);
    }

    public String getCedula()
    {
        return cedula.get();
    }

    public void setCedula(String cedula)
    {
        this.cedula = new SimpleStringProperty(cedula);
    }

    public String getDireccion()
    {
        return  direccion.get();
    }

    public void setDireccion(String direccion)
    {
        this.direccion = new SimpleStringProperty(direccion);
    }

    public String gettipocliente()
    {
        return  tipoCliente.get();
    }

    public void setTipocliente(String  tipocliente)
    {
        this.tipoCliente = new SimpleStringProperty(tipocliente);
    }

    public String getEstado() { return estado.get(); }

    public void setEstado(String estado) { this.estado = new SimpleStringProperty(estado); }

    public String getTel(){return tel.get();}

    public void setTel(String tel){this.tel =  new SimpleStringProperty(tel);}

    public String getCorreo(){return correo.get();}

    public void setCorreo(String correo){this.correo =  new SimpleStringProperty(correo);}


    //Metodos get y set property

    public IntegerProperty idproperty(){return  id;}

    public StringProperty nombre1property()
    {
        return nombre1;
    }

    public StringProperty nombre2property()
    {
        return nombre2;
    }
    public StringProperty telefonoproperty()
    {
        return tel;
    }
    public StringProperty correoproperty()
    {
        return correo;
    }

    public StringProperty apellido1Property(){

        return apellido1;

    }

    public StringProperty apellido2Property(){

        return apellido2;

    }

    public StringProperty cedulaProperty(){

        return cedula;

    }

    public StringProperty direccionProperty() {

        return direccion;

    }

    public StringProperty tipoClienteProperty() {

        return tipoCliente;

    }

    public StringProperty estadoProperty() {

        return estado;

    }

//Metodo para ingresar cliente a la base de datos
    public void IngresarCliente(Cliente cliente) throws  Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_cliente(?, ?, ?, ?, ?, ?, ?)}");
        cs.setString(1,cliente.getNombre1());
        cs.setString(2,cliente.getNombre2());
        cs.setString(3,cliente.getApellido1());
        cs.setString(4,cliente.getApellido2());
        cs.setString(5,cliente.getCedula());
        cs.setString(6,cliente.getDireccion());
        cs.setString(7,cliente.gettipocliente());
        cs.execute();
        conn.close();
    }

    //Metodo para mostrar todos los registros
    public ObservableList<Cliente> MostrarRegistros() throws Exception
    {
        //Declaracion de variables
        ObservableList<Cliente> listaCliente;
        //Inicializando el observablelist
        listaCliente = FXCollections.observableArrayList();


            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call sp_mostrar_cliente}");
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                /*aqui se llena la coleccion con objetos de tipo cliente*/
                /*el resultado.getSting(parametro) se caputra el valor que general el proceso almacenado*/
                listaCliente.add(new Cliente
                        (
                                resultado.getString("Primer_Nombre"),
                                resultado.getString("Segundo_Nombre"),
                                resultado.getString("Primer_Apellido"),
                                resultado.getString("Segundo_Apellido"),
                                resultado.getString("Cedula"),
                                resultado.getString("Dirreccion"),
                                resultado.getString("Tipo_Cliente"),
                                resultado.getString("Estado")

                              ));
            }
            conn.close();

        return  listaCliente;
    }

    //Metodo para BuscarRegistro
    public ObservableList<Cliente> BuscarRegistro(String a) throws  Exception
    {
        //Declaracion de variables
        ObservableList<Cliente> listaCliente;
        //Inicializando el observablelist
        listaCliente = FXCollections.observableArrayList();

            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{CALL sp_buscar_Cliente (?)}");
            cs.setString(1,a);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                /*aqui se llena la coleccion con objetos de tipo cliente*/
                /*el resultado.getSting(parametro) se caputra el valor que general el proceso almacenado*/
                listaCliente.add(new Cliente
                        (
                                resultado.getString("Primer_Nombre"),
                                resultado.getString("Segundo_Nombre"),
                                resultado.getString("Primer_Apellido"),
                                resultado.getString("Segundo_Apellido"),
                                resultado.getString("Cedula"),
                                resultado.getString("Dirreccion"),
                                resultado.getString("Tipo_Cliente"),
                                resultado.getString("Estado")

                        ));
            }
            conn.close();

        return  listaCliente;
    }

    //Metodo para cambiar el estado de cliente, solo resive el parametro de cedula, atraves de ese el sp encontrara el registro.
    public void estadoCliente(String Cedula) throws Exception {
        Connection conn = DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs = conn.prepareCall("{call sp_CambiarEstadoCliente(?)}");
        cs.setInt(1,ObtenerId(Cedula).get(0));
        cs.execute();
        conn.close();
    }

    //Este metodo es creado con el fin de retonar el id del cliente para poder mandarselo a estado cliente o actualizar datos
    public ObservableList<Integer> ObtenerId(String dat)
    {
        //Declaracion de variables
        ObservableList<Integer> listaCliente;
        //Inicializando el observablelist
        listaCliente = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement cs = conn.prepareCall("{call mostrarTodoCliente(?)}");
            cs.setString(1,dat);
            ResultSet resultado = cs.executeQuery();

            while (resultado.next())
            {
                /*aqui se llena la coleccion con objetos de tipo cliente*/
                /*el resultado.getSting(parametro) se caputra el valor que general el proceso almacenado*/
                listaCliente.add(resultado.getInt("Id_Cliente"));
            }
            conn.close();
        }catch (Exception e){}
        return  listaCliente;
    }
    //metodo par modificar o actualizar los registros
    public void ModificarDatos(String Cedula, String a, String b, String c) throws Exception
    {
        System.out.println(ObtenerId(Cedula).get(0));
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_ModificarDatosCliente(?, ?, ?, ?)}");
        cs.setInt(1,ObtenerId(Cedula).get(0));
        cs.setString(2,a);
        cs.setString(3,b);
        cs.setString(4,c);
        cs.execute();
        cs.close();
    }

    //este metodo es utilizado para saver todas las cedulas de los registros y poder validar que no se repitan
    public static ObservableList<String> cedulasRegistradas()
    {
        ObservableList<String> listaCedula;
        //Inicializando el observablelist
        listaCedula = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery("Select * from Cliente");

            while (resultado.next())
            {
                listaCedula.add(resultado.getString("Cedula"));
            }
            conn.close();
        }catch (Exception e){}
        return  listaCedula;
    }
//--------------------------------------------------------------------------------
    //para validar que el cliente no registre el mismo telefono
    public  ObservableList<Cliente> TelefonoRegistradoPorCliente(String Cedula)
    {
        ObservableList<Cliente> listaTel;
        //Inicializando el observablelist
        listaTel = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
           CallableStatement statement = conn.prepareCall("Call sp_mostrarTelporCliente (?)");
           statement.setInt(1,ObtenerId(Cedula).get(0));
           ResultSet resultado = statement.executeQuery();

            while (resultado.next())
            {
                listaTel.add(new Cliente(resultado.getString("Telefono")));
            }
            conn.close();
        }catch (Exception e){}
        return  listaTel;
    }

    public  ObservableList<Cliente> CorreonoRegistradoPorCliente(String Cedula)
    {
        ObservableList<Cliente> listaTel;
        //Inicializando el observablelist
        listaTel = FXCollections.observableArrayList();

        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            CallableStatement statement = conn.prepareCall("call sp_mostrarCorreoCliente(?)");
            statement.setInt(1,ObtenerId(Cedula).get(0));
            ResultSet resultado = statement.executeQuery();

            while (resultado.next())
            {
                listaTel.add( new Cliente (resultado.getString("Email"),""));
            }
            conn.close();
        }catch (Exception e){}
        return  listaTel;
    }
//--------------------------------------------------------------------------------
    //Este metodo ingresara el telefono del cliente, resive un entero que sera el numero y el otro es para extraer el numero de cedula y usar el metodo
    //de arriba que extrae apartir de la cedula el id
    public void IngresarTel(int tel,String dat) throws Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_cliente_telefono(?,?)}");
        cs.setInt(1,ObtenerId(dat).get(0));
        cs.setInt(2,tel);
        cs.execute();
        conn.close();
    }

    //Ingresar Correo
    public void IngresarCorreo(String c,String dat) throws Exception
    {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_new_cliente_email(?,?)}");
        cs.setInt(1,ObtenerId(dat).get(0));
        cs.setString(2,c);
        cs.execute();
        conn.close();
    }

    //Vista general de cliente para formulario de ingreso de renta
    public List<ClientBean> clientOverview(String dato) throws SQLException {
       Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
       CallableStatement cs=conn.prepareCall("{call sp_cliente_ov(?)}");
       cs.setString(1, dato);
       ResultSet rs=cs.executeQuery();
       List<ClientBean> cl=new ArrayList<>();
       while (rs.next()){
           cl.add(
                   new ClientBean(
                           rs.getInt("Id_Cliente"),
                           rs.getString("nombre"),
                           rs.getString("Cedula")
                   )
           );
       }
       conn.close();
       return cl;
    }
}
