package pe.edu.pucp.softprog.gestclientes.dao;

import java.util.ArrayList;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;

public interface ClienteDAO {
    int insertar(Cliente cliente);
    int modificar(Cliente cliente);
    int eliminar(int idCliente);
    ArrayList<Cliente> listarTodos();
    Cliente obtenerPorId(int idCliente);
}
