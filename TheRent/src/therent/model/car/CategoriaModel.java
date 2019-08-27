package therent.model.car;

import therent.model.beans.Categoria;
import therent.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaModel {
    public List<Categoria> getAllCategoria() throws SQLException {
        // Crear conexión
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        //Statement convencional ya que no se retorna nada
        Statement s=conn.createStatement();
        ResultSet rs=s.executeQuery("CALL sp_categoria_all();");
        if(rs==null){
            conn.close();
            return null;
        }
        List<Categoria> cats=new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt("ID");
            String cat=rs.getString("Categoría");
            String descr=rs.getString("Descripción");
            float prec=rs.getFloat("Costo");
            float depo=rs.getFloat("Depósito");
            cats.add(new Categoria(id, cat, descr,prec, depo));
        }
        conn.close();
        return cats;
    }
}
