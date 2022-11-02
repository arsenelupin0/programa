
package Principal;

import Metodos.metodos;
import Modelo.Tablas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
                    try {
                        metodos.ConvertirXLSX();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        btnImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valueCBXT = (String) cbxTablas.getSelectedItem();
                    if (rutaXLSX == null){
                        JOptionPane.showMessageDialog(null,"SELECCIONE UN ARCHIVO");
                    } else {
                        metodos.metodoImportar();
                        metodos.ConvertirCSV();
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
        ArrayList<Tablas> listaTablas = metodos.getTablas();

        cbxTablas.removeAllItems();
        for(int i = 0; i < listaTablas.size(); i++){
            cbxTablas.addItem(listaTablas.get(i).nombre_tabla);
        }
    }

    public static void main(String[] args) {
        Vista JFrame = new Vista();
    }

}
