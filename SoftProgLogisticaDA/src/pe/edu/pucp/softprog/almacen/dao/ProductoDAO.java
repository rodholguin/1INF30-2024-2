package pe.edu.pucp.softprog.almacen.dao;
import java.util.ArrayList;
import pe.edu.pucp.softprog.almacen.model.Producto;

public interface ProductoDAO {
    int insertar(Producto producto);
    int modificar(Producto producto);
    int eliminar(int idProducto);
    ArrayList<Producto> listarTodos();
    Producto obtenerPorId(int idProducto);
}
