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
            String cat=rs.getString("Categoría");
            float prec=rs.getFloat("Precio");
            float depo=rs.getFloat("Depsósito");
            String ishab=rs.getString("Habilitado");
            String est=rs.getString("Estado");

            autos.add(new Auto(id, marc, mod, col, yr, trans, carr, comb, plac, vin, cat, prec, depo, est, ishab));
        }
        conn.close();
        return autos;
    }
}
