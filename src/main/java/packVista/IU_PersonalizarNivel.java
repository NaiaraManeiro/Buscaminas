package packVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Cursor.HAND_CURSOR;

public class IU_PersonalizarNivel extends JDialog {
    private JPanel rootPanel;
    private JTextField numFilas;
    private JTextField numCol;
    private JButton jugarButton;
    private JButton infoButton;


    public IU_PersonalizarNivel(String pUsuario, int pNivel, String pMinas){
        setTitle("Buscaminas: " + pUsuario);
        setResizable(false);
        setContentPane(rootPanel);

        infoButton.setBorderPainted(false);
        infoButton.setBorder(null);
        infoButton.setMargin(new Insets(0, 0, 0, 0));
        infoButton.setContentAreaFilled(false);
        infoButton.setCursor(new Cursor(HAND_CURSOR));
        jugarButton.setCursor(new Cursor(HAND_CURSOR));

        jugarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validarDatos()) {
                    int filas = Integer.parseInt(numFilas.getText().trim());
                    int col = Integer.parseInt(numCol.getText().trim());
                    if (comprobarFilas(filas) && comprobarColumnas(col)) {
                        dispose(); //Cerramos la ventana actual
                        //Abrimos la pantalla del juego con el nivel marcado
                        Buscaminas bus = new Buscaminas(pUsuario, pNivel, filas, col, pMinas);
                        bus.setLocationRelativeTo(null);
                        bus.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "El nº de filas y/o columnas no cumplen "+
                                "las condiciones. \n Consulte el botón de ayuda e inténtelo de nuevo");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Los datos introducidos no son numéricos. \n Inténtelo de nuevo");
                }
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/duda.png"));
                JOptionPane.showMessageDialog(null,"Número mínimo y máximo de filas: 7 y 12 \n"+
                        "Número mínimo y máximo de columnas: 10 y 25", "Ayuda", JOptionPane.INFORMATION_MESSAGE,icon);
            }
        });
    }

    public JPanel getRootPanel(){
        if (rootPanel == null){
            rootPanel = new JPanel();
        }
        return rootPanel;
    }

    private boolean validarDatos(){
        String pattern = "[0-9]*";
        boolean validos = false;
        if (!numFilas.getText().equals("") && !numCol.getText().equals("")) {
            if (numFilas.getText().matches(pattern) && numCol.getText().matches(pattern)) {
                validos = true;
            }
        }
        return validos;
    }

    private boolean comprobarFilas(int filas){
        boolean ok = true;
        if (filas<7 || filas>12) ok = false;
        return ok;
    }

    private boolean comprobarColumnas(int col){
        boolean ok = true;
        if (col<10 || col>25) ok = false;
        return ok;
    }

}
