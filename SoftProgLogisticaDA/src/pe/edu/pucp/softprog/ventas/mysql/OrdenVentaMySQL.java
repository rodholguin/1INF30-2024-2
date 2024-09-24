package pe.edu.pucp.softprog.ventas.mysql;

import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softprog.config.DBManager;
import java.sql.CallableStatement;

public class OrdenVentaMySQL implements OrdenVentaDAO{
    
    private Connection con;
    private CallableStatement cs;
   
    @Override
    public int insertar(OrdenVenta ordenVenta){
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall("{call INSERTAR_ORDEN_VENTA(?,?,?,?)}");
            cs.registerOutParameter("_id_orden_venta", java.sql.Types.INTEGER);
            cs.setInt("_fid_cliente", ordenVenta.getCliente().getIdPersona());
            cs.setInt("_fid_empleado", ordenVenta.getEmpleado().getIdPersona());
            cs.setDouble("_total", ordenVenta.getTotal());
            cs.executeUpdate();
            ordenVenta.setIdOrdenVenta(cs.getInt("_id_orden_venta"));
            for(LineaOrdenVenta lov : ordenVenta.getLineasOrdenVenta()){
                cs = con.prepareCall("{call INSERTAR_LINEA_ORDEN_VENTA(?,?,?,?,?)}");
                cs.registerOutParameter("_id_linea_orden_venta", java.sql.Types.INTEGER);
                cs.setInt("_fid_orden_venta", ordenVenta.getIdOrdenVenta());
                cs.setInt("_fid_producto", lov.getProducto().getIdProducto());
                cs.setInt("_cantidad", lov.getCantidad());
                cs.setDouble("_subtotal", lov.getSubtotal());
                cs.executeUpdate();
                lov.setIdLineaOrdenVenta(cs.getInt("_id_linea_orden_venta"));
            }
            resultado = ordenVenta.getIdOrdenVenta();
            con.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            try {
                con.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultado;
    }
}
