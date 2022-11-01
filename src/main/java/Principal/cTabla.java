package Principal;

import Conexion.conexion;

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
                    conexion sql = new conexion();
                    Connection con = sql.conectarSQL();
                    String queryCreate = "CREATE TABLE L2024(codigo_porcion STRING,uni_lectura STRING,doc_lectura INT PRIMARY KEY UNIQUE,cuenta_contrato INT,medidor STRING,lectura_ant INT,lectura_act INT,anomalia_1 INT,anomalia_2 INT,codigo_operario STRING,vigencia INT,fecha DATETIME,orden_lectura INT,leido INT,calle STRING,edificio INT,suplemento_casa STRING,interloc_comercial STRING,apellido STRING,nombre STRING, clase_instalacion INT);\n";
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
