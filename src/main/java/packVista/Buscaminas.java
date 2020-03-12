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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class Buscaminas extends JFrame implements Observer {

    private JPanel contentPane, panel, panelTablero, panelBotones;
    private JButton[][] btntablero;
    private JButton casilla;
    private JTextField minasRestantes;
    private JTextField cronometro;
    private JButton reiniciarButton;
    private JButton volverAlMenuButton;
    private int filas, columnas;
    private boolean mostrarPerdida;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscaminas frame = new Buscaminas();
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

    public Buscaminas() {

        setTitle("Buscaminas");
        setResizable(false);

        contentPane = new JPanel();
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(getPanel(), BorderLayout.NORTH);
        contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
        minasRestantes.setEnabled(false);
        cronometro.setEnabled(false);

        volverAlMenuButton.addActionListener(new ActionListener() { //ARREGLAAAR!
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Login log = new Login(); //Abrimos la pantalla de inicio
                log.setLocationRelativeTo(null);
                log.setVisible(true);
            }
        });
        reiniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Buscaminas bus = new Buscaminas(); //Abrimos la pantalla del juego con el nivel marcado
                bus.setLocationRelativeTo(null);
                bus.setVisible(true);
                mostrarPerdida = true;
            }
        });

        jugar();
    }

    public void crearTablero(int filas, int columnas) {
        if (panelTablero != null)
            contentPane.remove(panelTablero);
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas, 0, 0));
        contentPane.add(panelTablero, BorderLayout.CENTER);
        int ancho = filas * columnas + 550;
        int alto = ancho - filas * columnas / 5;
        Dimension d = new Dimension(ancho, alto);
        this.setMinimumSize(d);
        this.setSize(d);
        btntablero = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada coord = new Coordenada(j, i);
                casilla = new JButton();
                casilla.setBounds(i, j, 100, 100);
                casilla.setName("" + j + ";" + i + "");
                casilla.addMouseListener(new cCasilla());
                btntablero[i][j] = casilla;
                panelTablero.add(casilla);
                asignarIcono(coord);
            }
        }

        minasRestantes.setText(Juego.getmJuego().getnMinasRestantes());
    }

    public void jugar() {
        mostrarPerdida = false;
        Juego.getmJuego().jugar();
        Juego.getmJuego().addObserver(this);
        //Juego.getmJuego().getCrono().addObserver(this);
        filas = Juego.getmJuego().getTablero().getFilas();
        columnas = Juego.getmJuego().getTablero().getColumnas();
        crearTablero(filas, columnas);
        Juego.getmJuego().getTablero().imprimirChivato();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Juego) {
            Coordenada coord = (Coordenada) arg;
            int fila = coord.getFila();
            int col = coord.getColumna();
            Casilla c = Juego.getmJuego().getTablero().devolverCasilla(col, fila);

            if (c.estaPulsada()) {
                if (c.estamarcada()) {
                    minasRestantes.setText(Juego.getmJuego().getnMinasRestantes());
                }
                asignarIcono(coord);
                btntablero[fila][col].setEnabled(false);
            }

            if (Juego.getmJuego().haPerdido()) {
                btntablero[fila][col].setBackground(new Color(252, 3, 3)); // La mina pulsada muestra otro fondo
                mostrarMinas();
                if (!mostrarPerdida) {
                    JOptionPane.showMessageDialog(null, "Has perdido la partida!",
                            "Información", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (Juego.getmJuego().haGanado() && !mostrarPerdida) {
                JOptionPane.showMessageDialog(null,
                        "Has ganado la partida!", "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }


        }
        /*
        else if (observador instanceof Cronometro) {
            mostrarCronometro((String) parametro);
        }*/
    }

    public void mostrarMinas() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                Casilla c = Juego.getmJuego().getTablero().devolverCasilla(i, j);
                if (c instanceof CasillaMina) {
                    Coordenada coord = c.getCoordenada();
                    c.setCasillaClicada(true);
                    asignarIcono(coord);
                }
            }
        }
    }

    private void asignarIcono(Coordenada pC) {
        boolean bloquear = false;
        ImageIcon imagen = null;
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(pC.getColumna(), pC.getFila());
        if (!c.estaPulsada()) {
            imagen = new ImageIcon(getClass().getResource("/facingDown.png"));
        } else {
            if (c instanceof CasillaMina) {
                imagen = new ImageIcon(getClass().getResource("/bomb.png"));
                bloquear = true;
            } else if (c instanceof CasillaNormal) {
                bloquear = true;
                switch (((CasillaNormal) c).getNumero()) {
                    case 0:
                        imagen = new ImageIcon(getClass().getResource("/0.png"));
                        break;
                    case 1:
                        imagen = new ImageIcon(getClass().getResource("/1.png"));
                        break;
                    case 2:
                        imagen = new ImageIcon(getClass().getResource("/2.png"));
                        break;
                    case 3:
                        imagen = new ImageIcon(getClass().getResource("/3.png"));
                        break;
                    case 4:
                        imagen = new ImageIcon(getClass().getResource("/4.png"));
                        break;
                    case 5:
                        imagen = new ImageIcon(getClass().getResource("/5.png"));
                        break;
                    case 6:
                        imagen = new ImageIcon(getClass().getResource("/6.png"));
                        break;
                    case 7:
                        imagen = new ImageIcon(getClass().getResource("/7.png"));
                        break;
                    case 8:
                        imagen = new ImageIcon(getClass().getResource("/8.png"));
                        break;
                    default:
                        break;
                }
            } else if (c.estamarcada()) {
                imagen = new ImageIcon(getClass().getResource("/flagged.png"));
            }
        }

        if (imagen != null) {
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(btntablero[pC.getFila()][pC.getColumna()].getWidth(), btntablero[pC.getFila()][pC.getColumna()].getHeight(), Image.SCALE_DEFAULT));
            btntablero[pC.getFila()][pC.getColumna()].setIcon(icono);
            btntablero[pC.getFila()][pC.getColumna()].setText("");
            //btntablero[pC.getFila()][pC.getColumna()].setDisabledIcon(btntablero[pC.getFila()][pC.getColumna()].getIcon());
            if (bloquear) {
                btntablero[pC.getFila()][pC.getColumna()].setEnabled(false);
            }
        }
    }


    private JButton getBtnReiniciar() {
        if (reiniciarButton == null) {
            reiniciarButton = new JButton("Reiniciar");
        }
        return reiniciarButton;
    }

    private JButton getBtnVolverAlMenu() {
        if (volverAlMenuButton == null) {
            volverAlMenuButton = new JButton("Volver Al Menu");
        }
        return volverAlMenuButton;
    }

    private JTextField getTxtNumMinas() {

        if (minasRestantes == null) {
            minasRestantes = new JTextField();
            minasRestantes.setEnabled(false);
            minasRestantes.setColumns(3);
        }
        return minasRestantes;
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panel.add(getTxtNumMinas());
            //panel_1.add(getTxtCronometro());
        }
        return panel;
    }

    private JPanel getPanelBotones() {
        if (panelBotones == null) {
            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panelBotones.add(getBtnReiniciar());
            panelBotones.add(getBtnVolverAlMenu());
        }
        return panelBotones;

    }

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
        contentPane.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setBackground(new Color(-9999251));
        contentPane.setEnabled(true);
        contentPane.setToolTipText("");
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Buscaminas", TitledBorder.LEFT, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, contentPane.getFont()), new Color(-4473925)));
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        minasRestantes = new JTextField();
        minasRestantes.setBackground(new Color(-590081));
        minasRestantes.setHorizontalAlignment(0);
        panel.add(minasRestantes, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cronometro = new JTextField();
        panel.add(cronometro, new GridConstraints(1, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Minas Restantes");
        panel.add(label1, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Cronometro");
        panel.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panelTablero, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelTablero.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reiniciarButton = new JButton();
        Font reiniciarButtonFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, reiniciarButton.getFont());
        if (reiniciarButtonFont != null) reiniciarButton.setFont(reiniciarButtonFont);
        reiniciarButton.setText("Reiniciar");
        panel1.add(reiniciarButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        volverAlMenuButton = new JButton();
        Font volverAlMenuButtonFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, volverAlMenuButton.getFont());
        if (volverAlMenuButtonFont != null) volverAlMenuButton.setFont(volverAlMenuButtonFont);
        volverAlMenuButton.setText("Volver al Menu");
        panel1.add(volverAlMenuButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelTablero.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
