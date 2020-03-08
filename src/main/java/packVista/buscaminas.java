package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import packModelo.*;
import packControlador.cCasilla;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class buscaminas extends JFrame implements Observer {

    private JPanel contentPane, panel, panelTablero, panelBotones;
    private JButton[][] btntablero;
    private JButton casilla;
    private JTextField minasRestantes;
    private JTextField cronometro;
    private JButton reiniciarButton;
    private JButton volverAlMenuButton;
    private int filas, columnas;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscaminas frame = new buscaminas();
                    frame.setPreferredSize(new Dimension(850, 500));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public buscaminas() {
        setTitle("Buscaminas");

        contentPane = new JPanel();
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(getPanel(), BorderLayout.NORTH);
        contentPane.add(getPanelBotones(), BorderLayout.SOUTH);

        jugar();
    }

    public void crearTablero(int filas, int columnas) {
        if (panelTablero != null)
            contentPane.remove(panelTablero);
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas, 0, 0));
        contentPane.add(panelTablero, BorderLayout.CENTER);
        int ancho = filas * columnas + 250;
        int alto = ancho - filas * columnas / 2;
        Dimension d = new Dimension(ancho, alto);
        this.setMinimumSize(d);
        this.setSize(d);
        btntablero = new JButton[columnas][filas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                casilla = new JButton();
                btntablero[i][j] = casilla;
                Coordenada coord = new Coordenada(i, j);
                casilla.addMouseListener(new cCasilla(coord));
                panelTablero.add(casilla);
                asignarIcono(coord);
            }
        }
    }

    public void jugar() {
        Juego.getmJuego().jugar();
        //Juego.getmJuego().addObserver(this);
        //Juego.getmJuego().getCrono().addObserver(this);
        filas = Juego.getmJuego().getTablero().getFilas();
        columnas = Juego.getmJuego().getTablero().getColumnas();
        crearTablero(filas, columnas);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void asignarIcono(Coordenada pC) {
        ImageIcon imagen = null;
        Casilla c = Tablero.getmTablero().devolverCasilla(pC.getColumna(), pC.getFila());
        if (!c.estaPulsada()) {
            imagen = new ImageIcon(getClass().getResource("facingDown.png"));
        } else {
            if (c instanceof CasillaMina) {
                imagen = new ImageIcon(getClass().getResource("bomb.png"));
            } else if (c instanceof CasillaNormal) {
                switch (((CasillaNormal) c).getNumero()) {
                    case 0:
                        imagen = new ImageIcon(getClass().getResource("0.png"));
                        break;
                    case 1:
                        imagen = new ImageIcon(getClass().getResource("1.png"));
                        break;
                    case 2:
                        imagen = new ImageIcon(getClass().getResource("2.png"));
                        break;
                    case 3:
                        imagen = new ImageIcon(getClass().getResource("3.png"));
                        break;
                    case 4:
                        imagen = new ImageIcon(getClass().getResource("4.png"));
                        break;
                    case 5:
                        imagen = new ImageIcon(getClass().getResource("5.png"));
                        break;
                    case 6:
                        imagen = new ImageIcon(getClass().getResource("6.png"));
                        break;
                    case 7:
                        imagen = new ImageIcon(getClass().getResource("7.png"));
                        break;
                    case 8:
                        imagen = new ImageIcon(getClass().getResource("8.png"));
                        break;
                    default:
                        break;
                }
            } else if (c.estamarcada()) {
                imagen = new ImageIcon(getClass().getResource("flagged.png"));
            }

        }

        if (imagen != null) {
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(
                    btntablero[pC.getColumna()][pC.getFila()].getWidth(),
                    btntablero[pC.getColumna()][pC.getFila()].getHeight(), Image.SCALE_DEFAULT));
            btntablero[pC.getColumna()][pC.getFila()].setIcon(icono);
            btntablero[pC.getColumna()][pC.getFila()].setText("");
            btntablero[pC.getColumna()][pC.getFila()].setEnabled(false);
            btntablero[pC.getColumna()][pC.getFila()].setDisabledIcon(btntablero[pC.getColumna()][pC.getFila()].getIcon());
        }
    }

    private JButton getBtnReiniciar() {
        if (reiniciarButton == null) {
            reiniciarButton = new JButton("Reiniciar");
            reiniciarButton.addMouseListener(new MouseListener() {

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        minasRestantes = new JTextField();
        panel.add(minasRestantes, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cronometro = new JTextField();
        panel.add(cronometro, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panelTablero, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reiniciar = new JButton();
        reiniciar.setText("Reiniciar");
        contentPane.add(reiniciar, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelar = new JButton();
        cancelar.setText("Volver al menú");
        contentPane.add(cancelar, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
