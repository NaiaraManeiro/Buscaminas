package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import packControlador.GestorBuscaminas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class IU_MostrarDatosNivel extends JFrame {
    private JPanel rootPanel;
    private JButton volverConfButton;
    private JTextField textFieldlevel;
    private JTextField textFieldFilas;
    private JTextField textFieldColumnas;
    private JTextField textFieldtiempo;
    private JPanel infoNivelPanel;
    private JPanel buttonpanel;
    private JPanel infoPanel;
    private JLabel lblnivel;
    private JLabel lblfilas;
    private JLabel lblcolumnas;
    private JLabel lbltiempo;
    private JTextField textFieldMinas;

    public IU_MostrarDatosNivel(int pLevel) throws SQLException {
        setTitle("Mostrar Datos Nivel");
        setResizable(false);
        setContentPane(rootPanel);

        añadirDatos(pLevel);

        volverConfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
    }

    private void añadirDatos(int nivel) {
        textFieldlevel.setEditable(false);
        textFieldlevel.setText("Nivel " + nivel);
        int filas = GestorBuscaminas.getMiGB().obtenerfilasnivel(nivel);
        textFieldFilas.setText("" + filas);
        Integer columnas = GestorBuscaminas.getMiGB().obtenercolumnasnivel(nivel);
        textFieldColumnas.setText("" + columnas);
        textFieldMinas.setText("" + nivel * columnas);
    }

    private void back() {
        dispose();
        IU_Consultarinfojuego cij = new IU_Consultarinfojuego();
        cij.pack();
        cij.setPreferredSize(new Dimension(300, 200));
        cij.setLocationRelativeTo(null);
        cij.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //setVisible(false);
        cij.setVisible(true);
    }

}