package pe.edu.pucp.softprog.ventas.mysql;

import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softprog.config.DBManager;

public class OrdenVentaMySQL implements OrdenVentaDAO{
    
   private Connection con;
    
    @Override
    public int insertar(OrdenVenta ordenVenta){
        try {
            con = DBManager.getInstance().getConnection();
            for(LineaOrdenVenta lov : ordenVenta.getLineasOrdenVenta()){
            
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(OrdenVentaMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally{
            
        }
    }
}
