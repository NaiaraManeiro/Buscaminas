package packVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Login() {
        setTitle("Buscaminas: Usuario");
        setResizable(false);
        setContentPane(rootpanel);
        comboBoxNivel.addItem(1);
        comboBoxNivel.addItem(2);
        comboBoxNivel.addItem(3);
        ButtonGroup group = new ButtonGroup();
        group.add(minasSi);
        group.add(minasNo);
        minasSi.setSelected(true);
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
    }

}