package therent;

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

public class Test {
    public static void main(String[] args) {
        try {
            Connection conn= DriverManager.getConnection(JDBCUtil.getDatabaseUri());
            String path="src/therent/reporting/ReporteMantenimiento.jasper";
            Map params=new HashMap<>();
            params.put("id_auto", 10);
            JasperPrint jp= JasperFillManager.fillReport(path, params, conn);
            JasperViewer jv= new JasperViewer(jp, false);
            jv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jv.setVisible(true);
        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }

    }
}
