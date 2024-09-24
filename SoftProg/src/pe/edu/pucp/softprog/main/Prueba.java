package pe.edu.pucp.softprog.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softprog.almacen.dao.ProductoDAO;
import pe.edu.pucp.softprog.almacen.model.Producto;
import pe.edu.pucp.softprog.almacen.mysql.ProductoMySQL;
import pe.edu.pucp.softprog.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.softprog.gestclientes.model.Categoria;
import pe.edu.pucp.softprog.gestclientes.model.Cliente;
import pe.edu.pucp.softprog.gestclientes.mysql.ClienteMySQL;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;
import pe.edu.pucp.softprog.rrhh.mysql.AreaMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.EmpleadoMySQL;
import pe.edu.pucp.softprog.ventas.dao.OrdenVentaDAO;
import pe.edu.pucp.softprog.ventas.model.LineaOrdenVenta;
import pe.edu.pucp.softprog.ventas.model.OrdenVenta;
import pe.edu.pucp.softprog.ventas.mysql.OrdenVentaMySQL;

public class Prueba {
    public static void main(String[] args) throws ParseException{
//        Area area = new Area();
//        area.setNombre("ANIMACION");
//        AreaDAO daoArea = new AreaMySQL();
//        int resultado = daoArea.eliminar(6);
//        daoArea.eliminar(7);
//        if(resultado != 0)
//            System.out.println("El area se ha eliminado "
//                    + "correctamente");
//        else  
//            System.out.println("Ha ocurrido un error en la "
//                    + "eliminación del area");
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Empleado emp = new Empleado();
//        emp.setDNI("17625444");
//        emp.setNombre("RENZO");
//        emp.setApellidoPaterno("GAMARRA");
//        emp.setGenero('M');
//        emp.setFechaNacimiento(sdf.parse("01-03-1983"));
//        emp.setCargo("TI");
//        emp.setSueldo(1500.00);
//        
//        EmpleadoDAO daoEmpleado = new EmpleadoMySQL();
//        daoEmpleado.insertar(emp);
//        
//        ArrayList<Empleado> empleados = daoEmpleado.listarTodos();
//        for(Empleado empt : empleados){
//            System.out.println(empt.getNombre());
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Cliente cliente = new Cliente("98179815", "MARIO",
//                "LOPEZ", 'M',
//                sdf.parse("15-04-1990"), 1200.00, 
//                Categoria.Platinum);
//        ClienteDAO clienteDAO = new ClienteMySQL();
//        int resultado = clienteDAO.insertar(cliente);
//        if(resultado!=0)
//            System.out.println("Se ha realizado correctamente el registro del cliente");
//
//        ArrayList<Cliente> clientes = clienteDAO.listarTodos();
//        for(Cliente c: clientes){
//            System.out.println(c.getDNI() + " - " + c.getNombre() + " " + 
//                    c.getApellidoPaterno());
//        }
        ProductoDAO daoProducto = new ProductoMySQL();
        ClienteDAO daoCliente = new ClienteMySQL();
        EmpleadoDAO daoEmpleado = new EmpleadoMySQL();
        
        Producto prod1 = daoProducto.obtenerPorId(1);
        Producto prod2 = daoProducto.obtenerPorId(2);
        
        OrdenVenta ov1 = new OrdenVenta();
        
        LineaOrdenVenta lov1 = new LineaOrdenVenta(prod1, 3);
        LineaOrdenVenta lov2 = new LineaOrdenVenta(prod2, 5);
        
        lov1.calcularSubtotal();
        lov2.calcularSubtotal();

        Cliente cliente1 = daoCliente.obtenerPorId(6);
        Empleado empleado1 = daoEmpleado.obtenerPorId(4);        

        ov1.getLineasOrdenVenta().add(lov1);
        ov1.getLineasOrdenVenta().add(lov2);
        
        ov1.calcularTotal();
        
        OrdenVentaDAO daoOrdenVenta = new OrdenVentaMySQL();
        daoOrdenVenta.insertar(ov1);

        
    }
}
