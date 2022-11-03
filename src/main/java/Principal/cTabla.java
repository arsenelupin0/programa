package Principal;

import Conexiones.conexion_lectura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cTabla extends JFrame {
    public JPanel pTabla;
    public JButton btnCTabla;
    public JButton btnCancelar;
    public JTextField tfNTabla;

    public cTabla() {
        setUndecorated(true);
        setContentPane(pTabla);
        setSize(400, 100);
        setLocationRelativeTo(null);
        setVisible(true);

        btnCTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (tfNTabla.getText() == null) {
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR LA TABLA SIN NOMBRE");
                } else {*/
                conexion_lectura sql = new conexion_lectura();
                Connection con = sql.conectarSQL();
                String queryCreate = "CREATE TABLE L2024(CODIGO_PORCION STRING,UNI_LECTURA STRING,DOC_LECTURA INT PRIMARY KEY UNIQUE,CUENTA_CONTRATO INT,MEDIDOR STRING,LECTURA_ANT INT,LECTURA_ACT INT,ANOMALIA_1 INT,ANOMALIA_2 INT,CODIGO_OPERARIO STRING,VIGENCIA INT,FECHA DATETIME,ORDEN_LECTURA INT,LEIDO INT,CALLE STRING,EDIFICIO INT,SUPLEMENTO_CASA STRING,INTERLOC_COMERCIAL STRING,APELLIDO STRING,NOMBRE STRING, CLASE_INSTALACION INT);\n";
                String queryInsert = "INSERT INTO Tablas " + tfNTabla.getText();
                try {
                    PreparedStatement psC = (PreparedStatement) con.prepareStatement(queryCreate);
                    PreparedStatement psI = (PreparedStatement) con.prepareStatement(queryInsert);
                    psC.executeUpdate();
                    psI.executeUpdate();
                    JOptionPane.showMessageDialog(null, "SE CREO LA LECTURA " + tfNTabla.getText() + " EN LA LISTA DE TABLAS");
                    psC.close();
                    psI.close();
                    con.close();
                } catch (SQLException ex) {
                }
            }
            /*}*/
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}