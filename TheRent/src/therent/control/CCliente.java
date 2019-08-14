package therent.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import therent.model.Cliente;


public class CCliente {
    //metodo para mandar mensajes de error
    private static void msgerr(String msg) {
        Alert al=new Alert(Alert.AlertType.ERROR);
        al.setTitle("TheRent Link System");
        al.setHeaderText("ERROR");
        al.setContentText(msg);
        al.showAndWait();
    }
    //metodo para mandar mensajes de correcto
    private static void msgevr(String msg) {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("TheRent Link System");
        al.setHeaderText("Correcto.");
        al.setContentText(msg);
        al.showAndWait();
    }
    //Metodo estatico para ingresar cliente.
    public static void IngresarCliente(String a, String b, String c, String d, String e, String f, String g) throws Exception
    {
        if(a.equals("") || a == null ||
                b.equals("") || b == null ||
                c.equals("") || c == null ||
                d.equals("") || d == null ||
                e.equals("") || e == null ||
                f.equals("") || f == null ||
                g.equals("") || g == null  )
        {
            msgerr("No se pueden guardar los datos, verificar que ningún campo este vacío.");

        }
        else
        {
            boolean v = false;

            for(String s :Cliente.cedulasRegistradas())
            {
                if(s.equals(e))
                {
                    v = true;
                }
            }

            if(v ==  true)
            {
                msgerr("Ya existe ese número de cedula.");
            }
            else {
                Cliente cliente = new Cliente(a, b, c, d, e, f, g);
                cliente.IngresarCliente(cliente);
                msgevr("Registro guardado correctamente.");
            }
        }
    }
    //Metodo estatico para mostrar los datos. Y retorna una coleccion
    public static ObservableList<Cliente> MostrarDatos() throws Exception
    {
        Cliente cliente = new Cliente();
        ObservableList<Cliente> cli;

        cli = cliente.MostrarRegistros();

        return cli;
    }
    //Metodo estatico que realiza ka busqueda de dato, el parametro que resibe es el string a buscar. Y retorna una coleccion
    public static ObservableList<Cliente> BuscarDato(String a) throws Exception
    {
        ObservableList<Cliente> cli = FXCollections.observableArrayList();
        if(a.equals("") || a == null){msgerr("verificar, que el campo de busqueda no este vacío.");}
        else {
            Cliente cliente = new Cliente();
            cli = cliente.BuscarRegistro(a);
            msgevr("Registro encontrado.");
            return cli;
        }

       return MostrarDatos();
    }
    //
    public static void estadoCliente(String a) throws Exception
    {
        if(a.equals("")||a == null)
        {
            msgerr("verificar, ningún registro esta seleccionado.");
        }
        else{
        Cliente c = new Cliente();
        c.estadoCliente(a);
        msgevr("El estado ha sido cambiado exitosamente.");
        }
    }
    //
    public static void modificarDatos(String a, String b , String c, String d) throws  Exception
    {
        if(a.equals("")||a == null || a.equals("")||a == null || a.equals("")||a == null)
        {
            msgerr("No se pueden guardar los datos, verificar que ningún campo este vacío.");
        }
        else
            {
            Cliente s = new Cliente();
            s.ModificarDatos(a, b, c, d);
            msgevr("Los datos han sido actualizado exitosamente.");
        }
    }

    // metodo que resive el telefono y la cedula para poder ingresar el cliente
    public static void IngresarTel(String Tel, String dat) throws Exception
    {
        if(Tel.equals("") || Tel == null || dat.equals("") || dat == null)
        {
            msgerr("No se pueden guardar los datos, verificar que la lista no este vacía.");
        }
        else
            {
                Cliente cliente = new Cliente();
                cliente.IngresarTel(Integer.parseInt(Tel), dat);
            }

    }

    //para agregar mas telefono a un registro ya existente
    public static void ModificarTel(String tel, String dat) throws Exception
    {
        boolean resultado = false;
        Cliente c = new Cliente();

        for(String s :c.TelefonoRegistradoPorCliente(dat))
        {
            if(s.equals(tel))
            {
                resultado = true;
            }
        }

        if(resultado ==  true)
        {
            msgerr("El usuario ya tiene registrado el número.");
        }
        else {
            Cliente cliente = new Cliente();
            cliente.IngresarTel(Integer.parseInt(tel),dat);
        }

    }

    //ingresar correo
    public static void IngresarCorreo(String c, String dat) throws Exception
    {
        if(c.equals("") || c == null || dat.equals("") || dat == null)
        {
          //  System.out.println(c+" "+dat);
            msgerr("No se pueden guardar los datos, verificar que la lista de correo no este vacía.");
        }
        else
        {
            Cliente cliente = new Cliente();
            cliente.IngresarCorreo(c, dat);
        }

    }

    public static void ModificarCorreo(String correo, String dat) throws Exception
    {
        boolean resultado = false;
        Cliente c = new Cliente();

        for(String s :c.CorreonoRegistradoPorCliente(dat))
        {
            if(s.equals(correo))
            {
                resultado = true;
            }
        }

        if(resultado ==  true)
        {
            msgerr("El usuario ya tiene registrado el Correo.");
        }
        else {
            Cliente cliente = new Cliente();
            cliente.IngresarCorreo(correo,dat);
        }

    }

}

