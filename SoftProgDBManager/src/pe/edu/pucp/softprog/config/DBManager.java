package pe.edu.pucp.softprog.config;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBManager {
    private static DBManager dbManager;
    private String url = "jdbc:mysql://" +
"db-prog3-clases.c8izfhuif1lk.us-east-1.rds.amazonaws.com" + 
            ":3306/" + "prog3";
    private String usuario = "adminprog3";
    private String password = "prog31inf30";
    private Connection con;
    
    public static DBManager getInstance(){
        if(dbManager == null)
            createInstance();
        return dbManager;
    }
    
    private static void createInstance(){
        dbManager = new DBManager();
    }
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection
        (url, usuario, password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}