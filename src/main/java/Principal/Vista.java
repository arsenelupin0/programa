package Principal;

import Metodos.Metodos;
import Modelo.Tablas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Vista extends JFrame {
    public static String rutaXLSX;
    public static String rutaCSV;
    public static String valueCBXT;
    public JButton btnSelectArchivo;
    public JButton btnImportar;
    public JTextField jtxtRuta;
    public JPanel mainPanel;
    public JComboBox cbxTablas;
    public JButton btnCrear;

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
                    if (rutaXLSX == null) {
                        JOptionPane.showMessageDialog(null, "SELECCIONE UN ARCHIVO");
                    } else {
                        valueCBXT = (String) cbxTablas.getSelectedItem();
                        Metodos.run();
                    }
                } catch (Exception exc) {
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

    public static void main(String[] args) {
        Vista JFrame = new Vista();
    }

    public void llenarTablas() {
        ArrayList<Tablas> listaTablas = Metodos.getTablas();

        cbxTablas.removeAllItems();
        for (int i = 0; i < listaTablas.size(); i++) {
            cbxTablas.addItem(listaTablas.get(i).nombre_tabla);
        }
    }
}