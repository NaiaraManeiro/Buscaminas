package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.json.JSONArray;
import packControlador.GestorBuscaminas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class IU_Consultarinfojuego extends JDialog {
    private JPanel rootPanel;
    private JPanel infoPanel;
    private JComboBox comboBoxNivel;
    private JPanel infonivelPanel;
    private JPanel nivelPanel;
    private JLabel lblfilas;
    private JLabel lblcolumnas;
    private JLabel lbltiempo;
    private JTextField textFieldFilas;
    private JTextField textFieldColumnas;
    private JTextField textFieldtiempo;
    private JButton guardarDatosButton;
    private JButton volverAlMenuButton;
    private JLabel lblNivel;
    private JButton consultarInfoButton;

    public IU_Consultarinfojuego() {
        setTitle("Menú Administrador: Consultar Información Juego");
        setResizable(false);
        setContentPane(rootPanel);

        JSONArray niveles = GestorBuscaminas.getMiGB().obtenerNiveles();
        for (int i = 0; i < niveles.length(); i++) {
            comboBoxNivel.addItem(niveles.get(i));
        }
        /*for (int i = 1; i < 4; i++) {
            comboBoxNivel.addItem(i);
        }*/

        consultarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarInfoNivel(getlevel());

            }
        });

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAlMenu();
            }
        });

    }

    private int getlevel() {
        int level = 0;
        if (!comboBoxNivel.getSelectedItem().equals("")) {
            if (comboBoxNivel.getSelectedItem().equals(1)) level = 1;
            else if (comboBoxNivel.getSelectedItem().equals(2)) level = 2;
            else level = 3;
        }
        return level;
    }

    private void consultarInfoNivel(Integer level) {
        if (level != 0) {
            dispose();
            IU_MostrarDatosNivel nivel = null;
            try {
                comboBoxNivel.getSelectedItem();
                nivel = new IU_MostrarDatosNivel(level);
                nivel.setPreferredSize(new Dimension(450, 310));
                nivel.pack();
                nivel.setLocationRelativeTo(null);
                nivel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                nivel.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(comboBoxNivel, "TERRIBLE ERROR!!! Seleccione un nivel para consultar.");
        }
    }

    private void volverAlMenu() {
        dispose();
        IU_InfoJuego info = new IU_InfoJuego();
        info.setPreferredSize(new Dimension(720, 630));
        info.pack();
        info.setLocationRelativeTo(null);
        setVisible(false);
        info.setVisible(true);
    }


    public static void main(String[] args){
        IU_Consultarinfojuego log = new IU_Consultarinfojuego();
        log.setPreferredSize(new Dimension(300, 250));
        log.pack();
        log.setLocationRelativeTo(null);
        log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        log.setVisible(true);
    }

}