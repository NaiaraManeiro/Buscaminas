package packVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IU_InfoJuego extends JDialog {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel comoJugarPanel;
    private JTextArea expl;
    private JButton volveralmenuButton;
    private JTextArea expl2;
    private JButton volverAlMenuButton;
    private JLabel lblwelcome;
    private JTextArea welcome;
    private JTextArea personalizar;
    private JTextArea jugar;
    private JButton infonivelesButton;
    private JButton infoNivelesButton;
    private JTextArea puntuaciones;

    public IU_InfoJuego() {
        setTitle("Información del juego");
        setResizable(false);
        setContentPane(rootPanel);

        anadirExplicaciones();

        volveralmenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
                log.pack();
                log.setPreferredSize(new Dimension(475, 350));
                log.setLocationRelativeTo(null);
                log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                log.setVisible(true);
            }
        });

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
                log.pack();
                log.setPreferredSize(new Dimension(475, 350));
                log.setLocationRelativeTo(null);
                log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                log.setVisible(true);
            }
        });

        infonivelesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                IU_Consultarinfojuego cij = new IU_Consultarinfojuego();
                cij.pack();
                cij.setPreferredSize(new Dimension(300, 200));
                cij.setLocationRelativeTo(null);
                cij.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                //setVisible(false);
                cij.setVisible(true);
                }
        });

        infoNivelesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                IU_Consultarinfojuego cij = new IU_Consultarinfojuego();
                cij.pack();
                cij.setPreferredSize(new Dimension(300, 250));
                cij.setLocationRelativeTo(null);
                cij.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                //setVisible(false);
                cij.setVisible(true);
            }

        });

    }

    public JPanel getRootPanel(){
        if (rootPanel == null){
            rootPanel = new JPanel();
        }
        return rootPanel;
    }
    private void anadirExplicaciones() {

        lblwelcome.setText("         ¡¡Bienvenido al Buscaminas!!");

        welcome.setText("       Si has llegado hasta aqui, eso significa que, " +
                "o te has unido a nuestra comunidad, o ya \n        pertenecías a ella, " +
                "así que, sea cual sea el motivo por el que has llegado hasta aqui...\n\n" +
                "                                                   ¡¡BIENVENIDO A ESTA GRAN FAMILIA!! \n\n" +
                "       A continuación encontrarás información acerca de los botones " +
                "que encontrarás en la \n   pantalla principal del juego. Si quieres saber como " +
                "funciona el juego, puedes seleccionar \n                            " +
                "                 la pestaña 'Como Jugar' que aparece arriba.");

        personalizar.setText("\n Con el botón 'Personalizar', podrás elegir tu temática favorita," +
                " el \n sonido de ganar partida y el de Game Over, etc..");

        jugar.setText("\n Tras haber elegido un nivel y un nombre de usuario, pulsando el botón \n " +
                      "jugar accederás a jugar a nuestro buscaminas con el nivel seleccionado");

        puntuaciones.setText("\n Pulsando el botón puntuaciones, podrás ver las puntuaciones (propias y \n " +
                                " de otros jugadores) de los distintos niveles del juego");

        expl.setText("\n    El juego consiste en despejar todas las casillas \n    de "
                + "una pantalla que no oculten una mina. "
                + "Algunas \n    casillas tienen un número, el cual indica la "
                + "cantidad \n    de minas que hay en las casillas "
                + "circundantes. Así, \n    si una casilla  tiene el número 3, "
                + "significa que de las \n    ocho  casillas que hay alrededor "
                + "(si no es en una \n    esquina o borde)  hay 3 con minas y 5 sin minas. \n"
                + "    Por otro lado, tenemos las minas Reset y minas 50%. ");

        expl2.setText("\n    Si se destapa la mina 'reset', se reiniciará el tablero "
                + " cubriendo de nuevo todas \n    las casillas y redistribuyendo las "
                + " minas de nuevo. El tiempo de la partida seguirá \n    corriendo."
                + " Si se destapa la mina '50%', se marcará la posición del 50%  "
                + "de las minas \n    que todavía estén sin marcar. "
                + "Si se descubre una casilla sin número indica  que "
                + "ninguna \n    de las casillas vecinas tiene mina y "
                + "éstas se descubren automáticamente. Si se descubre\n    "
                + "una casilla con una mina se pierde la partida. "
                + "Se puede poner una marca en las casillas \n    que el "
                + "jugador piensa que hay minas para ayudar a "
                + "descubrir las que están cerca.");
    }

}
