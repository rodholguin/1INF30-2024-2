package pe.edu.pucp.softprog.rrhh.dao;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.model.Area;

public interface AreaDAO {
    int insertar(Area area);
    int modificar(Area area);
    int eliminar(int idArea);
    Area obtenerPorId(int idArea);
    ArrayList<Area> listarTodas();
}
