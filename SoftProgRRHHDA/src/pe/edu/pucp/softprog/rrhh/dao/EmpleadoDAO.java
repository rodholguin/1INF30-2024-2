package pe.edu.pucp.softprog.rrhh.dao;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Empleado;

public interface EmpleadoDAO {
    int insertar(Empleado empleado);
    ArrayList<Empleado> listarTodos();
}
