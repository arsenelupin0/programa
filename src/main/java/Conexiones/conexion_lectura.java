package Conexiones;
import java.io.File;
import java.sql.*;
public class conexion_lectura {
    File RutaDB = new File("dbs\\lectura");
    String url = ""+RutaDB;

    public Connection conectarSQL(){
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}