package therent.model;

import therent.model.beans.Auto;
import therent.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoModel {
    public AutoModel(){}

    public List<Auto> getAllAutos() throws SQLException {
        // Crear conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        //Statement convencional ya que no se retorna nada
        Statement s=conn.createStatement();
        ResultSet rs=s.executeQuery("CALL sp_auto_all();");
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

}
