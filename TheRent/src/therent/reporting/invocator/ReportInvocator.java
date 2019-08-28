package therent.reporting.invocator;

import jdk.nashorn.internal.scripts.JD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import therent.util.JDBCUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportInvocator {
    public static void mantReport(int id_auto) throws JRException, SQLException {
        Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        String path="src/therent/reporting/ReporteMantenimiento.jasper";
        Map params=new HashMap<>();
        params.put("id_auto", id_auto);
        JasperPrint jp= JasperFillManager.fillReport(path, params, conn);
        JasperViewer jv= new JasperViewer(jp, false);
        jv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jv.setVisible(true);
    }
    public static void historialReport(int id_auto) throws SQLException, JRException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        String path= "src/therent/reporting/ReporteRecibidoEntrega.jasper";
        Map parametros = new HashMap();
        //donde se captura el jtexfield se captura el id del vehiculo del cual se creara el reporte
        parametros.put("id_auto", id_auto);
        //   reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jprint = JasperFillManager.fillReport(path,parametros, conn);
        JasperViewer view  = new JasperViewer(jprint,false);
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.setVisible(true);
        conn.close();
    }
    public static void facturaReport(int id_renta) throws SQLException, JRException {
        Connection conn=DriverManager.getConnection(JDBCUtil.getDatabaseUri());
        String path= "src/therent/reporting/FacturaCliente.jasper";
        Map parametros = new HashMap();
        //donde se captura el jtexfield se captura el id del vehiculo del cual se creara el reporte
        parametros.put("id_renta", id_renta);
        //   reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
        JasperPrint jprint = JasperFillManager.fillReport(path,parametros, conn);
        JasperViewer view  = new JasperViewer(jprint,false);
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.setVisible(true);
        conn.close();
    }

}
