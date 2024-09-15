package pe.edu.pucp.softprog.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.softprog.rrhh.dao.AreaDAO;
import pe.edu.pucp.softprog.rrhh.dao.EmpleadoDAO;
import pe.edu.pucp.softprog.rrhh.model.Area;
import pe.edu.pucp.softprog.rrhh.model.Empleado;
import pe.edu.pucp.softprog.rrhh.mysql.AreaMySQL;
import pe.edu.pucp.softprog.rrhh.mysql.EmpleadoMySQL;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Empleado emp = new Empleado();
        emp.setDNI("17625444");
        emp.setNombre("RENZO");
        emp.setApellidoPaterno("GAMARRA");
        emp.setGenero('M');
        emp.setFechaNacimiento(sdf.parse("01-03-1983"));
        emp.setCargo("TI");
        emp.setSueldo(1500.00);
        
        EmpleadoDAO daoEmpleado = new EmpleadoMySQL();
        daoEmpleado.insertar(emp);
        
        ArrayList<Empleado> empleados = daoEmpleado.listarTodos();
        for(Empleado empt : empleados){
            System.out.println(empt.getNombre());
        }
    }
}
