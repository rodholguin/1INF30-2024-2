package pe.edu.pucp.softprog.rrhh.mysql;

import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.model.Area;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import pe.edu.pucp.softprog.config.DBManager;

public class AreaMySQL implements AreaDAO{
    
    private Statement st;
    private Connection con;

    @Override
    public int insertar(Area area) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO area(nombre,activa) "
                    + "VALUES('" + area.getNombre() + "',1)";
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                con.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(Area area) {
        return 0;
    }

    @Override
    public int eliminar(int idArea) {
                int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            String sql = "DELETE FROM area WHERE id_area = "
                    + idArea + ";";
            st = con.createStatement();
            resultado = st.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                con.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public Area obtenerPorId(int idArea) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Area> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}