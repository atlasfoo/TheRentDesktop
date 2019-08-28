package therent.model.car;

import jdk.nashorn.internal.codegen.CompilerConstants;
import therent.model.beans.Auto;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AutoModel {
    public AutoModel(){}

    public List<Auto> getAllAutos() throws SQLException {
        // Crear conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        //Statement convencional ya que no se retorna nada
        CallableStatement s=conn.prepareCall("{CALL sp_auto_all()}");
        ResultSet rs=s.executeQuery();
        if(rs==null){
            return null;
        }
        List<Auto> autos=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("IDAuto");
            String marc=rs.getString("Marca");
            String mod=rs.getString("Modelo");
            String col=rs.getString("Color");
            int yr=rs.getInt("Año");
            String trans=rs.getString("Transmisión");
            String carr=rs.getString("Carrocería");
            String comb=rs.getString("Combustible");
            String plac=rs.getString("Placa");
            String vin=rs.getString("VIN");
            String chas=rs.getString("Chasis");
            String cat=rs.getString("Categoría");
            float prec=rs.getFloat("Precio");
            float depo=rs.getFloat("Depsósito");
            String ishab=rs.getString("Habilitado");
            String est=rs.getString("Estado");

            autos.add(new Auto(id, marc, mod, col, yr, trans, carr, comb, plac, vin, chas, cat, prec, depo, est, ishab));
        }
        conn.close();
        return autos;
    }
    public boolean newAuto(String plac, int yr, String chasis, String vin, String color, int trans, int id_mod) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{CALL sp_new_auto(?,?,?,?,?,?,?)}");
        cs.setString(1, plac);
        cs.setInt(2, yr);
        cs.setString(3, chasis);
        cs.setString(4, vin);
        cs.setString(5, color);
        cs.setInt(6, trans);
        cs.setInt(7, id_mod);
        boolean r=cs.execute();
        conn.close();
        return r;
    }
    public void editAuto(int id, String plac, String col) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_edit_auto(?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2, plac);
        cs.setString(3,col);
        cs.execute();
        conn.close();
    }
    public void enable_disable(int id) throws SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_enable_car(?)}");
        cs.setInt(1,id);
        cs.execute();
        conn.close();
    }

    //Metodo para la dsiponibilidad del auto
    public List<Auto> disponibilidad_auto(LocalDate fecha_entrega, LocalDate fecha_recibo) throws SQLException {
        //Declaracion de variables
        List<Auto> listaAuto=new ArrayList<>();

        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs = conn.prepareCall("{call sp_disponibilidad_auto(?,?)}"
        );
        cs.setDate(1,Date.valueOf(fecha_entrega));
        cs.setDate(2, Date.valueOf(fecha_recibo));
        ResultSet resultado = cs.executeQuery();

        while (resultado.next())
        {
            listaAuto.add(new Auto
                    (
                            resultado.getInt("Id_Auto"),
                            resultado.getString("Marca"),
                            resultado.getString("Modelo"),
                            resultado.getString("Color"),
                            resultado.getInt("Año"),
                            resultado.getString("Transmisión"),
                            resultado.getString("Carrocería"),
                            resultado.getString("Combustible"),
                            resultado.getString("Placa"),
                            resultado.getString("VIN"),
                            resultado.getString("Chasis"),
                            resultado.getString("Categoría"),
                            resultado.getFloat("Precio"),
                            resultado.getFloat("Depósito"),
                            resultado.getString("Habilitado"),
                            resultado.getString("Estado")
                    ));
        }
        conn.close();
        return listaAuto;
    }

    public Auto getbyPlaca(String placa) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_auto_byplaca(?)}");
        cs.setString(1, placa);
        ResultSet rs=cs.executeQuery();
        if(!rs.next()){
            return null;
        }

        int id=rs.getInt("IDAuto");
        String marc=rs.getString("Marca");
        String mod=rs.getString("Modelo");
        String col=rs.getString("Color");
        int yr=rs.getInt("Año");
        String trans=rs.getString("Transmisión");
        String carr=rs.getString("Carrocería");
        String comb=rs.getString("Combustible");
        String plac=rs.getString("Placa");
        String vin=rs.getString("VIN");
        String chas=rs.getString("Chasis");
        String cat=rs.getString("Categoría");
        float prec=rs.getFloat("Precio");
        float depo=rs.getFloat("Depsósito");
        String ishab=rs.getString("Habilitado");
        String est=rs.getString("Estado");

        Auto a=new Auto(id, marc, mod, col, yr, trans, carr, comb, plac, vin, chas, cat, prec, depo, est, ishab);
        conn.close();
        return a;
    }

}
