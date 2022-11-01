package Metodos;

import Conexion.conexion;
import Modelo.Lecturas;
import Modelo.Tablas;
import Principal.Vista;
import com.csvreader.CsvReader;

import com.aspose.cells.Workbook;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class metodos extends Vista {

    //LLAMANDO METODOS
    public static void metodoImportar(){
        List<Lecturas> ciclo = new ArrayList<Lecturas>();
        ciclo = importarCSV();
        //insertarSQL(ciclo);
    }

    //METODOS

    //CONVERTIR ARCHIVO XLSX A CSV PARA LEER LOS DATOS MAS FACILMENTE
    public static void ConvertirXLSX () throws Exception {
        Workbook workbook = new Workbook(rutaXLSX);
        File RutaOutPut = new File("files\\Output.csv");
        workbook.save(""+RutaOutPut);
        rutaCSV = ""+RutaOutPut;
    }

    //OBTENER LISTA DE TABLAS
    public static ArrayList<Tablas> getTablas() {
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
            ps.close();
            nT.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaTablas;
    }

    //PARA LEER LOS DATOS Y ALMACENARLOS EN UNA LISTA CON SU ESTRUCTURA
    public static List<Lecturas> importarCSV(){
        List<Lecturas> ciclo = new ArrayList<Lecturas>();
        try {
            CsvReader leerLecturas = new CsvReader(rutaCSV);
            leerLecturas.readHeaders();
            while (leerLecturas.readRecord()) {
                String codigo_porcion = leerLecturas.get(0);
                String uni_lectura = leerLecturas.get(1);
                String doc_lectura = leerLecturas.get(2);
                String cuenta_contrato = leerLecturas.get(3);
                String medidor = leerLecturas.get(4);
                String lectura_ant = leerLecturas.get(5);
                String lectura_act = leerLecturas.get(6);
                String anomalia_1 = leerLecturas.get(7);
                String anomalia_2 = leerLecturas.get(8);
                String codigo_operario = leerLecturas.get(9);
                String vigencia = leerLecturas.get(10);
                String fecha = leerLecturas.get(11);
                String orden_lectura = leerLecturas.get(12);
                String leido = leerLecturas.get(13);
                String calle = leerLecturas.get(14);
                String edificio = leerLecturas.get(15);
                String suplemento_casa = leerLecturas.get(16);
                String interloc_comercial = leerLecturas.get(17);
                String apellido = leerLecturas.get(18);
                String nombre = leerLecturas.get(19);
                String clase_instalacion = leerLecturas.get(20);
                ciclo.add(new Lecturas(codigo_porcion, uni_lectura, doc_lectura, cuenta_contrato, medidor, lectura_ant, lectura_act, anomalia_1, anomalia_2, codigo_operario, vigencia, fecha, orden_lectura, leido, calle, edificio, suplemento_casa, interloc_comercial, apellido, nombre, clase_instalacion));
            }
            leerLecturas.close();

            System.out.println("\nLISTA CVS\n");
            for (Lecturas lec: ciclo){
                System.out.println(
                        lec.getCodigo_porcion()+", "+
                        lec.getUni_lectura()+", "+
                        lec.getDoc_lectura()+", "+
                        lec.getCuenta_contrato()+", "+
                        lec.getMedidor()+", "+
                        lec.getLectura_ant()+", "+
                        lec.getLectura_act()+", "+
                        lec.getAnomalia_1()+", "+
                        lec.getAnomalia_2()+", "+
                        lec.getCodigo_operario()+", "+
                        lec.getVigencia()+", "+
                        lec.getFecha()+", "+
                        lec.getOrden_lectura()+", "+
                        lec.getLeido()+", "+
                        lec.getCalle()+", "+
                        lec.getEdificio()+ ", "+
                        lec.getSuplemento_casa()+", " +
                        lec.getInterloc_comercial()+", "+
                        lec.getApellido()+", "+
                        lec.getNombre()+", "+
                        lec.getClase_instalacion()
                );
            }

            //
            for (Lecturas lec: ciclo){
                System.out.println(
                    lec.getDoc_lectura()
                );
            }
            //
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ciclo;
    }

    // PARA INSERTAR LOS DATOS A LA BASE DE DATOS
    public static void insertarSQL(List<Lecturas> ciclo) {
        JPanel p1 = new JPanel(new BorderLayout());
        JFrame frame = new JFrame(p1.getGraphicsConfiguration());
        p1.add(new JLabel("CARGANDO REGISTROS. POR FAVOR, ESPERE...\n"), BorderLayout.CENTER);
        p1.setBackground(Color.CYAN);
        frame.setUndecorated(true);
        frame.getContentPane().add(p1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);


        System.out.println("SE VAN A INSERTAR: " + ciclo.size() + " REGISTROS\n");
        conexion sql = new conexion();
        Connection con = sql.conectarSQL();
        String query = "INSERT INTO "+valueCBXT+"(codigo_porcion, uni_lectura, doc_lectura, cuenta_contrato, medidor, lectura_ant, lectura_act, anomalia_1, anomalia_2, codigo_operario, vigencia, fecha, orden_lectura, leido, calle, edificio, suplemento_casa, interloc_comercial, apellido, nombre, clase_instalacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            for(int i = 0; i < ciclo.size(); i++) {
                ps.setString(1, ciclo.get(i).getCodigo_porcion());
                ps.setString(2, ciclo.get(i).getUni_lectura());
                ps.setString(3, ciclo.get(i).getDoc_lectura());
                ps.setString(4, ciclo.get(i).getCuenta_contrato());
                ps.setString(5, ciclo.get(i).getMedidor());
                ps.setString(6, ciclo.get(i).getLectura_ant());
                ps.setString(7, ciclo.get(i).getLectura_act());
                ps.setString(8, ciclo.get(i).getAnomalia_1());
                ps.setString(9, ciclo.get(i).getAnomalia_2());
                ps.setString(10, ciclo.get(i).getCodigo_operario());
                ps.setString(11, ciclo.get(i).getVigencia());
                ps.setString(12, ciclo.get(i).getFecha());
                ps.setString(13, ciclo.get(i).getOrden_lectura());
                ps.setString(14, ciclo.get(i).getLeido());
                ps.setString(15, ciclo.get(i).getCalle());
                ps.setString(16, ciclo.get(i).getEdificio());
                ps.setString(17, ciclo.get(i).getSuplemento_casa());
                ps.setString(18, ciclo.get(i).getInterloc_comercial());
                ps.setString(19, ciclo.get(i).getApellido());
                ps.setString(20, ciclo.get(i).getNombre());
                ps.setString(21, ciclo.get(i).getClase_instalacion());
                ps.executeUpdate();
                System.out.println("SE INSERTO EL ELEMENTO: " + (i+1) + "/" + ciclo.size());
            }
            JOptionPane.showMessageDialog(null, "SE IMPORTO CORRECTAMENTE " + ciclo.size() + " REGISTROS");
            ps.close();
            con.close();
        } catch (SQLException e) {
        }
        frame.dispose();
    }
}