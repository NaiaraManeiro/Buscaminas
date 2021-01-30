package packVista;

import org.json.JSONArray;
import packControlador.GestorBuscaminas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Cursor.HAND_CURSOR;

public class Login extends JDialog {
    private JPanel rootpanel;
    private JButton aceptarButton;
    private JTextField textusuario;
    private JLabel lblnombre;
    private JLabel lblnivel;
    private JPanel panel1;
    private JComboBox comboBoxNivel;
    private JButton puntuacionesButton;
    private JLabel minasEspeciales;
    private JRadioButton minasSi;
    private JRadioButton minasNo;
    private JButton ayuda;
    private JButton personalizar;

    public Login() {
        setTitle("Buscaminas: Usuario");
        setResizable(false);
        setContentPane(rootpanel);

        JSONArray niveles = GestorBuscaminas.getMiGB().obtenerNiveles();
        for (int i = 0; i < niveles.length(); i++) {
            comboBoxNivel.addItem(niveles.get(i));
        }

        ButtonGroup group = new ButtonGroup();
        group.add(minasSi);
        group.add(minasNo);
        minasSi.setSelected(true);

        ayuda.setBorderPainted(false);
        ayuda.setBorder(null);
        ayuda.setMargin(new Insets(0, 0, 0, 0));
        ayuda.setContentAreaFilled(false);
        ayuda.setCursor(new Cursor(HAND_CURSOR));
        aceptarButton.setCursor(new Cursor(HAND_CURSOR));
        puntuacionesButton.setCursor(new Cursor(HAND_CURSOR));
        personalizar.setCursor(new Cursor(HAND_CURSOR));

        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = textusuario.getText();
                Object nivel = comboBoxNivel.getSelectedItem();
                String minas = " ";

                if (minasSi.isSelected()){
                    minas = "si";
                } else if (minasNo.isSelected()) {
                    minas = "no";
                }

                Login.this.dispose(); //Cerramos la ventana actual
                //Abrimos la pantalla del juego con el nivel marcado
                Buscaminas bus = Buscaminas.getmBuscaminas(usuario, (int) nivel, minas);
                bus.setLocationRelativeTo(null);
                bus.setVisible(true);
            }
        });

        puntuacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                vPuntuaciones punt = new vPuntuaciones();
                punt.setPreferredSize(new Dimension(450, 400));
                punt.pack();
                punt.setLocationRelativeTo(null);
                punt.setVisible(true);
            }
        });

        ayuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AyudaMinas ayuda = new AyudaMinas();
                ayuda.setPreferredSize(new Dimension(480, 350));
                ayuda.pack();
                ayuda.setLocationRelativeTo(null);
                ayuda.setVisible(true);
            }
        });

        personalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IU_Personalizar pers = new IU_Personalizar();
                pers.setPreferredSize(new Dimension(600, 440));
                pers.setLocationRelativeTo(null);
                pers.pack();
                setVisible(false);
                pers.setVisible(true);
            }
        });
    }
}