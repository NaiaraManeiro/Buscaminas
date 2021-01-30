package packVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Cursor.HAND_CURSOR;

public class AyudaMinas extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea minaNormal;
    private JTextArea mina50;
    private JTextPane minaReset;
    private JTextArea inicio;
    private JLabel titulo;

    public AyudaMinas() {
        setTitle("Información de las minas especiales");
        setContentPane(contentPane);
        setResizable(false);
        getRootPane().setDefaultButton(buttonOK);

        añadirAyuda();

        buttonOK.setCursor(new Cursor(HAND_CURSOR));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onOK() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onOK() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    private void añadirAyuda() {
        titulo.setText("¡Minas especiales!");
        inicio.setText("A continuación se explica el funcionamiento de los tres tipos\n" +
                "de minas que aparecerán al escoger la opción de 'minas especiales':");
        minaNormal.setText("- Mina normal: Actúa con la mina que conocemos, una vez\n" +
                "clicada la mina, la partida se acaba y se muestran todas las minas\n" +
                " del tablero.");
        minaReset.setText("- Mina reset: Se reiniciará el tablero cubriendo de nuevo todas\n" +
                "las casillas y redistribuyendo las minas de nuevo. El tiempo de la\n" +
                "partida seguirá corriendo.");
        mina50.setText("- Mina 50%: Se marcará la posición del 50% de las minas que\n" +
                "todavía estén sin marcar.");
    }


}
