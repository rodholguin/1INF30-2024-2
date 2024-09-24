package pe.edu.pucp.softprog.rrhh.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

public class EmpleadoMySQL implements EmpleadoDAO {
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Empleado empleado){
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "INSERT INTO persona(DNI, nombre,"
                    + "apellido_paterno, genero, fecha_nacimiento) "
                    + "VALUES(?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, empleado.getDNI());
            pst.setString(2, empleado.getNombre());
            pst.setString(3, empleado.getApellidoPaterno());
            pst.setString(4, String.valueOf(empleado.getGenero()));
            pst.setDate(5, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            resultado = pst.executeUpdate();
            sql = "SELECT @@last_insert_id as id";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            empleado.setIdPersona(rs.getInt("id"));
            sql = "INSERT INTO empleado(id_empleado, cargo, sueldo, activo) "
                    + "VALUES(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, empleado.getIdPersona());
            pst.setString(2, empleado.getCargo());
            pst.setDouble(3, empleado.getSueldo());
            pst.setBoolean(4, true);
            resultado = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
    
    @Override
    public ArrayList<Empleado> listarTodos(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            String sql = "SELECT * FROM persona INNER JOIN "
                    + "empleado" + " ON persona.id_persona = " +
                    "empleado.id_empleado;";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Empleado emp = new Empleado();
                emp.setIdPersona(rs.getInt("id_persona"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellidoPaterno(rs.getString("apellido_paterno"));
                empleados.add(emp); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return empleados;
    }

    @Override
    public Empleado obtenerPorId(int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
