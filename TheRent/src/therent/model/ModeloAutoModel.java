package therent.model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import therent.model.beans.Auto;
import therent.model.beans.ModeloAuto;
import therent.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloAutoModel {
    public List<ModeloAuto> getAllModelos() throws SQLException {
        // Crear conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        Statement s=conn.createStatement();
        ResultSet rs=s.executeQuery("CALL sp_model_all();");
        if(rs==null){
            return null;
        }
        List<ModeloAuto> modelos=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("ID");
            String marca=rs.getString("Marca");
            String model=rs.getString("Modelo");
            String cat=rs.getString("Categoria");
            float costo=rs.getFloat("Costo");
            modelos.add(new ModeloAuto(id, marca, model, cat, costo));
        }
        conn.close();
        return modelos;
    }

}