package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import packModelo.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

    public static void main(String[] args) {
        try {
            Login dialog = new Login();
            dialog.setPreferredSize(new Dimension(375, 350));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Login() {

        setTitle("Buscaminas: Usuario");
        setResizable(false);
        setContentPane(rootpanel);

        comboBoxNivel.addItem(1);
        comboBoxNivel.addItem(2);
        comboBoxNivel.addItem(3);

        aceptarButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = textusuario.getText();
                Object nivel = comboBoxNivel.getSelectedItem();
                if (validarDatos(usuario)) {
                    if (nivel.equals(1)) {
                        Juego.getmJuego().crearUsuario(usuario, 1);
                    } else if (nivel.equals(2)) {
                        Juego.getmJuego().crearUsuario(usuario, 2);
                    } else if (nivel.equals(3)) {
                        Juego.getmJuego().crearUsuario(usuario, 3);
                    }
                    dispose(); //Cerramos la ventana actual
                    Buscaminas bus = new Buscaminas(); //Abrimos la pantalla del juego con el nivel marcado
                    bus.setLocationRelativeTo(null);
                    bus.setVisible(true);
                }
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