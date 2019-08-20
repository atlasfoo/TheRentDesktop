package therent;

import therent.model.ModeloAutoModel;
import therent.model.beans.Auto;
import therent.model.beans.ModeloAuto;
import therent.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
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

    private String is_disponible(int id_auto, LocalDate f_in, LocalDate f_out) throws SQLException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        CallableStatement cs=conn.prepareCall("{call sp_disponibilidad_auto(?,?,?,?)}");
        cs.setInt(1, id_auto);
        cs.setDate(2,Date.valueOf(f_in));
        cs.setDate(3, Date.valueOf(f_out));
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.execute();
        String s=cs.getString(4);
        conn.close();
        return s;
    }

    public static void main(String[] args) {
        try {
            List<Auto> autos= new Test().getAllAutos();
            List<Auto> autos_disp=new ArrayList<>();
            for(Auto a : autos){
                if(new Test().is_disponible(a.getIDAuto(), LocalDate.of(2019,8,1), LocalDate.of(2019,8,10)).equals("DISPONIBLE")){
                    autos_disp.add(a);
                }
            }
            for (Auto a : autos_disp){
                System.out.printf("%s | %s | %d\n", a.getMarca(), a.getModelo(), a.getAnho());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
