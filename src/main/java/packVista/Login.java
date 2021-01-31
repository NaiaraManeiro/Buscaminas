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
    private JLabel minasEspeciales;
    private JRadioButton minasSi;
    private JRadioButton minasNo;
    private JButton ayuda;
    private JButton masOpciones;

    public Login() {
        setTitle("Buscaminas: Usuario");
        setResizable(false);
        setContentPane(rootpanel);

        JSONArray niveles = GestorBuscaminas.getMiGB().obtenerNiveles();
        for (int i = 0; i < niveles.length(); i++) {
            comboBoxNivel.addItem(niveles.get(i));
        }
        comboBoxNivel.addItem("Pers.");

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
        masOpciones.setCursor(new Cursor(HAND_CURSOR));

        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = textusuario.getText();
                Object nivel = comboBoxNivel.getSelectedItem();
                String minas = " ";

                if (validarDatos(usuario)){
                    if (minasSi.isSelected()){
                        minas = "si";
                    } else if (minasNo.isSelected()) {
                        minas = "no";
                    }
                    if (!nivel.equals("Pers.")) {
                        int filas = GestorBuscaminas.getMiGB().obtenerfilasnivel((int)nivel);
                        int columnas = GestorBuscaminas.getMiGB().obtenercolumnasnivel((int)nivel);
                        Login.this.dispose(); //Cerramos la ventana actual
                        //Abrimos la pantalla del juego con el nivel marcado
                        Buscaminas bus = new Buscaminas(usuario, (int) nivel, filas, columnas, minas);
                        bus.setLocationRelativeTo(null);
                        bus.setVisible(true);
                    } else{
                        Login.this.dispose();
                        IU_PersonalizarNivel perslevel = new IU_PersonalizarNivel(usuario,4, minas);
                        perslevel.pack();
                        perslevel.setPreferredSize(new Dimension(325, 350));
                        perslevel.setLocationRelativeTo(null);
                        perslevel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        perslevel.setVisible(true);
                    }
                    }
            }
        });

        ayuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AyudaMinas ayuda = new AyudaMinas();
                ayuda.setPreferredSize(new Dimension(480, 350));
                ayuda.pack();
                ayuda.setLocationRelativeTo(null);
                ayuda.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                ayuda.setVisible(true);
            }
        });

        masOpciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                MasOpciones mo = new MasOpciones();
                mo.setPreferredSize(new Dimension(250, 250));
                mo.pack();
                mo.setLocationRelativeTo(null);
                mo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                mo.setVisible(true);
            }
        });
    }
    private boolean validarDatos(String usuario) {
        boolean valido = false;
        if ((!usuario.equals(""))) {
            String pattern = "^[a-zA-Z0-9]*$";
            if (textusuario.getText().matches(pattern)) {
                valido = true;
            } else {
                JOptionPane.showMessageDialog(textusuario, "Nombre de usuario no valido");
                textusuario.setText(null);
            }
        } else {
            JOptionPane.showMessageDialog(textusuario, "Introduzca un nombre de usuario para jugar");
            textusuario.setText(null);
        }
        return valido;
    }

}