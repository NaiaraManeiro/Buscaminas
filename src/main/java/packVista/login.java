package packVista;

import packModelo.*;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class login extends JDialog{
    private JPanel rootpanel;
    private JButton aceptarButton;
    private JTextField textusuario;
    private JLabel lblnombre;
    private JLabel lblnivel;
    private JPanel panel1;
    private JComboBox comboBoxNivel;

    public static void main(String[] args) {
        try {
            login dialog = new login();
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public login() {

        comboBoxNivel.addItem(1);
        comboBoxNivel.addItem(2);
        comboBoxNivel.addItem(3);

        aceptarButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = textusuario.getText();
                Object nivel = comboBoxNivel.getSelectedItem();
                if (validarDatos(usuario)){
                    if (nivel instanceof Nivel1){
                        Juego.getmJuego().crearUsuario(usuario,1);
                        dispose(); //Cerramos la ventana actual
                        new buscaminas(); //Abrimos la pantalla del juego (Nivel 1)
                    }
                    else if (nivel instanceof Nivel2) {
                        Juego.getmJuego().crearUsuario(usuario, 2);
                        dispose(); //Cerramos la ventana actual
                        new buscaminas(); //Abrimos la pantalla del juego (Nivel 2)
                    }
                    else if (nivel instanceof Nivel3) {
                        Juego.getmJuego().crearUsuario(usuario, 3);
                        dispose(); //Cerramos la ventana actual
                        new buscaminas(); //Abrimos la pantalla del juego (Nivel 3)
                    }
                }
            }
        });
    }

    private boolean validarDatos(String usuario){
        boolean valido = false;
        if((!usuario.equals(""))) {
            String pattern= "^[a-zA-Z0-9]*$";
            if(textusuario.getText().matches(pattern)) {valido = true;}
            else{
                JOptionPane.showMessageDialog(textusuario,"Nombre de usuario no valido");
                textusuario.setText(null);
            }
        }
        else{
            JOptionPane.showMessageDialog(textusuario,"Introduzca un nombre de usuario para jugar");
            textusuario.setText(null);
        }
        return valido;
    }
}