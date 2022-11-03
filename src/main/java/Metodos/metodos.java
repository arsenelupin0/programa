package Metodos;

import Conexiones.conexion_lectura;
import Modelo.Lecturas;
import Modelo.Tablas;
import Principal.Vista;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Worksheet;
import com.csvreader.CsvReader;
import com.aspose.cells.Workbook;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class metodos extends Vista /*implements Runnable*/{

    //METODO QUE LLAMA OTROS METODOS
    public static void metodoImportar(){
        List<Lecturas> datos = new ArrayList<Lecturas>();
        List<Lecturas> datosRepetidos = new ArrayList<Lecturas>();
        datos = importarCSV();
        insertarSQL(datos);
    }

    //METODOS

    //CONVERTIR ARCHIVO XLSX A CSV PARA LEER LOS DATOS MAS FACILMENTE
    public static void ConvertirXLSX () throws Exception {
        Workbook wbXLSX = new Workbook(rutaXLSX);
        Worksheet worksheet = wbXLSX.getWorksheets().get(0);
        int cols = worksheet.getCells().getMaxDataColumn();
        cols=cols+1;
        if (cols == 21){
            File RutaOutPut = new File("files\\Importe.csv");
            wbXLSX.save(""+RutaOutPut);
            rutaCSV = ""+RutaOutPut;
        } else {
            JOptionPane.showMessageDialog(null, "error de estructura: VERIFIQUE LA ESTRUCTURA DEL ARCHIVO");
        }

    }
    //CONVERTIR ARCHIVO CSV A XLMX PARA VISUALIZAR DE MANERA MAS OPTIMA LOS DATOS REPETIDOS
    public static void ConvertirCSV () throws Exception {
        Workbook wbCSV = new Workbook("files\\Repetidos.csv");
        wbCSV.save("files\\Repetidos.xlsx", SaveFormat.XLSX);
        File D1 = new File("files\\Importe.csv");
        File D2 = new File("files\\Repetidos.csv");
        D1.delete();
        D2.delete();
    }

    //OBTENER LISTA DE TABLAS
    public static ArrayList<Tablas> getTablas() {
        conexion_lectura sql = new conexion_lectura();
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
        List<Lecturas> datos = new ArrayList<Lecturas>();
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
                datos.add(new Lecturas(codigo_porcion, uni_lectura, doc_lectura, cuenta_contrato, medidor, lectura_ant, lectura_act, anomalia_1, anomalia_2, codigo_operario, vigencia, fecha, orden_lectura, leido, calle, edificio, suplemento_casa, interloc_comercial, apellido, nombre, clase_instalacion));
            }
            leerLecturas.close();

            //NUEVA LISTA COMPLETA SIN DATOS REPETIDOS
            List<Lecturas> completa = datos.stream().distinct().collect(Collectors.toList());
            System.out.println("COMPLETOS SIN REPETIDOS:");
            completa.forEach(System.out::println);

            //NUEVA LISTA DE DATOS REPETIDOS
            Set<Lecturas> repetidos = new HashSet<>();
            List<Lecturas> repetidosFinal = datos.stream().filter(lectura -> !repetidos.add(lectura)).collect(Collectors.toList());
            System.out.println("REPETIDOS:");
            repetidosFinal.forEach(System.out::println);

            File csvFile = new File("files\\Repetidos.csv");
            PrintWriter write = new PrintWriter(csvFile);
            String estructura = "COD_PORCION,COD_UNILEC,ID_DOC_LECTURA,CUENTA_CONTRATO,NUM_MEDIDOR,LEC_ANTERIOR,LEC_ACTUAL,COD_EVENTO1,COD_EVENTO2,COD_OPERARIO,VIGENCIA,FECHA,ORDEN_LECTURA,LEIDO,CALLE,EDIFICIO,SUPLEM_CASA,INTERLOC_COMER,APELLIDO,NOMBRE2,CLASE_INSTALA";
            write.println(estructura);
            for (int j = 0; j < repetidosFinal.size(); j++){
                write.print(repetidosFinal.get(j).getCodigo_porcion()+",");
                write.print(repetidosFinal.get(j).getUni_lectura()+",");
                write.print(repetidosFinal.get(j).getDoc_lectura()+",");
                write.print(repetidosFinal.get(j).getCuenta_contrato()+",");
                write.print(repetidosFinal.get(j).getMedidor()+",");
                write.print(repetidosFinal.get(j).getLectura_ant()+",");
                write.print(repetidosFinal.get(j).getLectura_act()+",");
                write.print(repetidosFinal.get(j).getAnomalia_1()+",");
                write.print(repetidosFinal.get(j).getAnomalia_2()+",");
                write.print(repetidosFinal.get(j).getCodigo_operario()+",");
                write.print(repetidosFinal.get(j).getVigencia()+",");
                write.print(repetidosFinal.get(j).getFecha()+",");
                write.print(repetidosFinal.get(j).getOrden_lectura()+",");
                write.print(repetidosFinal.get(j).getLeido()+",");
                write.print(repetidosFinal.get(j).getCalle()+",");
                write.print(repetidosFinal.get(j).getEdificio()+",");
                write.print(repetidosFinal.get(j).getSuplemento_casa()+",");
                write.print(repetidosFinal.get(j).getInterloc_comercial()+",");
                write.print(repetidosFinal.get(j).getApellido()+",");
                write.print(repetidosFinal.get(j).getNombre()+",");
                write.print(repetidosFinal.get(j).getClase_instalacion());
                write.println();
            }
            write.close();

            if (repetidosFinal.size() == 0){
                JOptionPane.showMessageDialog(null,"NO SE ENCONTRO NINGUN REGISTRO REPETIDO EN EL ARCHIVO");
            } else {
                JOptionPane.showMessageDialog(null,"SE ENCONTRO "+repetidosFinal.size()+" REGISTROS REPETIDOS EN EL ARCHIVO");
            }

            datos = completa;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    // PARA INSERTAR LOS DATOS A LA BASE DE DATOS
    public static void insertarSQL(List<Lecturas> datos) {
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

        System.out.println("SE VAN A INSERTAR: " + datos.size() + " REGISTROS en "+valueCBXT+"\n");
        conexion_lectura sql = new conexion_lectura();
        Connection con = sql.conectarSQL();
        String query = "INSERT OR IGNORE INTO "+valueCBXT+"(codigo_porcion, uni_lectura,doc_lectura,cuenta_contrato, medidor, lectura_ant, lectura_act, anomalia_1, anomalia_2, codigo_operario, vigencia, fecha, orden_lectura, leido, calle, edificio, suplemento_casa, interloc_comercial, apellido, nombre, clase_instalacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            for(int i = 0; i < datos.size(); i++) {
                ps.setString(1, datos.get(i).getCodigo_porcion());
                ps.setString(2, datos.get(i).getUni_lectura());
                ps.setString(3, datos.get(i).getDoc_lectura());
                ps.setString(4, datos.get(i).getCuenta_contrato());
                ps.setString(5, datos.get(i).getMedidor());
                ps.setString(6, datos.get(i).getLectura_ant());
                ps.setString(7, datos.get(i).getLectura_act());
                ps.setString(8, datos.get(i).getAnomalia_1());
                ps.setString(9, datos.get(i).getAnomalia_2());
                ps.setString(10, datos.get(i).getCodigo_operario());
                ps.setString(11, datos.get(i).getVigencia());
                ps.setString(12, datos.get(i).getFecha());
                ps.setString(13, datos.get(i).getOrden_lectura());
                ps.setString(14, datos.get(i).getLeido());
                ps.setString(15, datos.get(i).getCalle());
                ps.setString(16, datos.get(i).getEdificio());
                ps.setString(17, datos.get(i).getSuplemento_casa());
                ps.setString(18, datos.get(i).getInterloc_comercial());
                ps.setString(19, datos.get(i).getApellido());
                ps.setString(20, datos.get(i).getNombre());
                ps.setString(21, datos.get(i).getClase_instalacion());
                ps.executeUpdate();
                System.out.println("SE INSERTO EL ELEMENTO: " + (i+1) + "/" + datos.size());
            }
            JOptionPane.showMessageDialog(null, "SE IMPORTO CORRECTAMENTE " + datos.size() + " REGISTROS");
            ps.close();
            con.close();
        } catch (SQLException e) {
        }
        frame.dispose();
    }

    /*
    @Override
    public synchronized void run() {

    }
     */
}