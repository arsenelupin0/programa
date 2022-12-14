
package Principal;

import Metodos.metodos;
import Modelo.Tablas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static Metodos.metodos.*;

public class Vista extends JFrame {
    public JButton btnSelectArchivo;
    public JButton btnImportar;
    public JTextField jtxtRuta;
    public JPanel mainPanel;
    public JComboBox cbxTablas;
    public JButton btnCrear;
    public static String rutaXLSX;
    public static String rutaCSV;
    public static String valueCBXT;
    public Vista() {
        setContentPane(mainPanel);
        setTitle("ACUEDUCTO");
        //setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Imagenes/Icono.png")).getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        llenarTablas();

        btnSelectArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivoSeleccionado = null;
                JFileChooser seleccionarArchivo = new JFileChooser();
                seleccionarArchivo.showOpenDialog(null);
                archivoSeleccionado = seleccionarArchivo.getSelectedFile();
                if (archivoSeleccionado != null) {
                    jtxtRuta.setText("" + archivoSeleccionado);
                    rutaXLSX = "" + archivoSeleccionado;
                }
            }
        });

        btnImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rutaXLSX == null){
                        JOptionPane.showMessageDialog(null,"SELECCIONE UN ARCHIVO");
                    } else {
                        valueCBXT = (String) cbxTablas.getSelectedItem();
                        Thread importarHilo = new Thread(new metodos());
                        importarHilo.start();
                    }
                } catch (Exception exc){
                }
            }
        });
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cTabla();
            }
        });
    }

    public void llenarTablas(){
        ArrayList<Tablas> listaTablas = getTablas();

        cbxTablas.removeAllItems();
        for(int i = 0; i < listaTablas.size(); i++){
            cbxTablas.addItem(listaTablas.get(i).nombre_tabla);
        }
    }

    public static void main(String[] args) {
        Vista JFrame = new Vista();
    }

}
