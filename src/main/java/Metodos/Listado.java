package Metodos;

import Conexion.conexion;
import Modelo.Tablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Listado {
    public ArrayList<Tablas> getTablas() {
        conexion sql = new conexion();
        Connection con = sql.conectarSQL();
        Statement ps;
        ResultSet nT;
        ArrayList<Tablas> listaTablas = new ArrayList<>();
        try {
            ps = con.createStatement();
            nT = ps.executeQuery("SELECT * FROM Tablas");

            while (nT.next()){
                Tablas tabla = new Tablas();
                tabla.setNombre_tabla(nT.getString("nombre_tabla"));
                listaTablas.add(tabla);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaTablas;
    }
}

