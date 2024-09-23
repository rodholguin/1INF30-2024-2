package pe.edu.pucp.softprog.gestclientes.mysql;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import pe.edu.pucp.softprog.config.DBManager;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.model.Categoria;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;

public class ClienteMySQL implements ClienteDAO{

    private Connection con; 
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(Cliente cliente) {
        int resultado=0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_CLIENTE(?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_cliente", java.sql.Types.INTEGER);
            cs.setString("_DNI", cliente.getDNI());
            cs.setString("_nombre", cliente.getNombre());
            cs.setString("_apellido_paterno", cliente.getApellidoPaterno());
            cs.setString("_genero", String.valueOf(cliente.getGenero()));
            cs.setDate("_fecha_nacimiento", 
                    new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            cs.setDouble("_linea_credito", cliente.getLineaCredito());
            cs.setString("_categoria", cliente.getCategoria().toString());
            cs.executeUpdate();
            cliente.setIdPersona(cs.getInt("_id_cliente"));
            resultado = cliente.getIdPersona();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_CLIENTES_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdPersona(rs.getInt("id_cliente"));
                cliente.setDNI(rs.getString("DNI"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
                cliente.setGenero(rs.getString("genero").charAt(0));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setLineaCredito(rs.getDouble("linea_credito"));
                cliente.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public Cliente obtenerPorId(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
