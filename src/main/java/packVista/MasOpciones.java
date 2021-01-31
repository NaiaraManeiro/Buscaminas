package packVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Cursor.HAND_CURSOR;

public class MasOpciones extends JDialog{
    private JButton puntuaciones;
    private JPanel panel1;
    private JButton información;
    private JButton personalizar;
    private JButton volver;

    public MasOpciones(){

        setTitle("Más Opciones");
        setResizable(false);
        setContentPane(panel1);

        puntuaciones.setCursor(new Cursor(HAND_CURSOR));
        información.setCursor(new Cursor(HAND_CURSOR));
        personalizar.setCursor(new Cursor(HAND_CURSOR));
        volver.setCursor(new Cursor(HAND_CURSOR));

        puntuaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                vPuntuaciones punt = new vPuntuaciones();
                punt.setPreferredSize(new Dimension(450, 400));
                punt.pack();
                punt.setLocationRelativeTo(null);
                punt.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                punt.setVisible(true);
            }
        });

        información.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IU_InfoJuego info = new IU_InfoJuego();
                info.setPreferredSize(new Dimension(720, 630));
                info.pack();
                info.setLocationRelativeTo(null);
                info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                info.setVisible(true);
            }
        });

        personalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IU_Personalizar pers = new IU_Personalizar();
                pers.setPreferredSize(new Dimension(600, 440));
                pers.setLocationRelativeTo(null);
                pers.pack();
                pers.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                pers.setVisible(true);
            }
        });

        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

    }
}
