package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import packModelo.*;
import packControlador.cCasilla;
import packModelo.packCasilla.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private boolean mostrarGanado;
    private boolean finPartida;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscaminas frame = new Buscaminas();
                    frame.pack();
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
        cronometro.setDisabledTextColor(new Color(0, 0, 0));
        minasRestantes.setDisabledTextColor(new Color(0, 0, 0));
        reiniciarButton.setBounds(50, 50, 50, 50);
        mostrarCronometro("0:00");
        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Login log = new Login(); //Abrimos la pantalla de inicio
                log.setPreferredSize(new Dimension(375, 350));
                log.pack();
                log.setLocationRelativeTo(null);
                log.setVisible(true);
                Juego.getmJuego().getCrono().reset();
                Juego.getmJuego().reiniciarVariables();
                mostrarPerdida = true;
                mostrarGanado = true;
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Buscaminas bus = new Buscaminas(); //Abrimos la pantalla del juego con el nivel marcado
                bus.setLocationRelativeTo(null);
                bus.setVisible(true);
                Juego.getmJuego().getCrono().reset();
                Juego.getmJuego().reiniciarVariables();
                mostrarPerdida = true;
                mostrarGanado = true;
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
        contentPane.setBackground(new Color(-9999251));
        panelBotones.setBackground(new Color(-9999251));
        panel.setBackground(new Color(-9999251));
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
                if (Juego.getmJuego().getModo().getNumero() == 1) {
                    casilla.setBounds(i, j, 63, 63);
                } else if (Juego.getmJuego().getModo().getNumero() == 2) {
                    this.setMinimumSize(new Dimension(1000, 800));
                    casilla.setBounds(i, j, 65, 65);
                } else {
                    this.setMinimumSize(new Dimension(1700, 1000));
                    casilla.setBounds(i, j, 70, 70);
                }

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
        mostrarGanado = false;
        finPartida = false;
        Juego.getmJuego().jugar();
        Juego.getmJuego().addObserver(this);
        filas = Juego.getmJuego().getTablero().getFilas();
        columnas = Juego.getmJuego().getTablero().getColumnas();
        crearTablero(filas, columnas);
        Juego.getmJuego().getTablero().imprimirChivato();
    }

    @Override
    public void update(Observable o, Object arg) {
        Juego.getmJuego().getCrono().addObserver(this);
        if (o instanceof Juego) {
            Coordenada coord = (Coordenada) arg;
            int fila = coord.getFila();
            int col = coord.getColumna();
            Casilla c = Juego.getmJuego().getTablero().devolverCasilla(col, fila);

            if (c.getEstado() instanceof Bandera) {
                if (Integer.parseInt(Juego.getmJuego().getnMinasRestantes()) < 0) {
                    if (Juego.getmJuego().getModo().getNumero() == 1) {
                        JOptionPane.showMessageDialog(null, "No se pueden poner más de 10 banderitas.",
                                "Información", JOptionPane.ERROR_MESSAGE);
                    } else if (Juego.getmJuego().getModo().getNumero() == 2) {
                        JOptionPane.showMessageDialog(null, "No se pueden poner más de 30 banderitas.",
                                "Información", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden poner más de 75 banderitas.",
                                "Información", JOptionPane.ERROR_MESSAGE);
                    }
                    c.setEstado(new NoClicada(coord));
                    Juego.getmJuego().incrementarMinas();
                } else {
                    minasRestantes.setText(Juego.getmJuego().getnMinasRestantes());
                    asignarIcono(coord);
                }
            }
            if (c.getEstado() instanceof Clicada || c.getEstado() instanceof NoClicada) {
                minasRestantes.setText(Juego.getmJuego().getnMinasRestantes());
                asignarIcono(coord);
            }
            if (Juego.getmJuego().haPerdido()) {
                btntablero[fila][col].setBackground(new Color(252, 3, 3)); // La mina pulsada muestra otro fondo
                mostrarMinas();
                finPartida = true;
                comprobarBanderas();
                if (!mostrarPerdida) {
                    JOptionPane.showMessageDialog(null, "Has perdido la partida!", "Información", JOptionPane.ERROR_MESSAGE);
                    mostrarPerdida = true;
                }
            }
            if (Juego.getmJuego().haGanado()) {
                if (!mostrarGanado) {
                    JOptionPane.showMessageDialog(null,
                            "Has ganado la partida!", "Información",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarGanado = true;
                    Juego.getmJuego().guardarPartida();
                }
            }
        } else if (o instanceof Cronometro) {
            mostrarCronometro((String) arg);
        }
    }

    private void mostrarCronometro(String tiempo) {
        getTxtCronometro().setText(tiempo);
    }

    public void mostrarMinas() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                Casilla c = Juego.getmJuego().getTablero().devolverCasilla(i, j);
                if (c instanceof CasillaMina) {
                    Coordenada coord = c.getCoordenada();
                    c.setEstado(new Clicada(coord));
                    asignarIcono(coord);
                }
            }
        }
    }

    public void comprobarBanderas() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                Casilla c = Juego.getmJuego().getTablero().devolverCasilla(i, j);
                if (c.getEstado() instanceof Bandera && c instanceof CasillaNormal) {
                    asignarIcono(c.getCoordenada());
                }
            }
        }
    }

    private void asignarIcono(Coordenada pC) {
        ImageIcon imagen = null;
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(pC.getColumna(), pC.getFila());

        if (finPartida) {
            if (c.getEstado() instanceof Bandera && c instanceof CasillaNormal) {
                imagen = new ImageIcon(getClass().getResource("/wrongFlagged.png"));
            }
        } else {
            if (c.getEstado() instanceof NoClicada) {
                imagen = new ImageIcon(getClass().getResource("/facingDown.png"));
            } else if (c.getEstado() instanceof Clicada) {
                if (c instanceof CasillaMina) {
                    imagen = new ImageIcon(getClass().getResource("/bomb.png"));
                } else if (c instanceof CasillaNormal) {
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
                }
            } else if (c.getEstado() instanceof Bandera) {
                imagen = new ImageIcon(getClass().getResource("/flagged.png"));
            }
        }

        if (imagen != null) {
            ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(btntablero[pC.getFila()][pC.getColumna()].getWidth(), btntablero[pC.getFila()][pC.getColumna()].getHeight(), Image.SCALE_DEFAULT));
            btntablero[pC.getFila()][pC.getColumna()].setIcon(icono);
            btntablero[pC.getFila()][pC.getColumna()].setText("");
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
            panel.add(getTxtCronometro());
            panel.add(getBtnReiniciar());
        }
        return panel;
    }

    private JPanel getPanelBotones() {
        if (panelBotones == null) {
            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panelBotones.add(getBtnVolverAlMenu());
        }
        return panelBotones;
    }

    private JTextField getTxtCronometro() {
        if (cronometro == null) {
            cronometro = new JTextField();
            cronometro.setEnabled(false);
            cronometro.setColumns(3);
        }
        return cronometro;
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
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Buscaminas", TitledBorder.LEFT, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, contentPane.getFont()), new Color(-4473925)));
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-9999251));
        contentPane.add(panel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(90, 50), new Dimension(90, 50), new Dimension(90, 50), 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-9999251));
        panel1.setForeground(new Color(-9999251));
        panel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-9999251));
        Font label1Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-590081));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Minas Restantes");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minasRestantes = new JTextField();
        minasRestantes.setBackground(new Color(-590081));
        Font minasRestantesFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, minasRestantes.getFont());
        if (minasRestantesFont != null) minasRestantes.setFont(minasRestantesFont);
        minasRestantes.setHorizontalAlignment(0);
        panel1.add(minasRestantes, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel2.setBackground(new Color(-9999251));
        panel2.setForeground(new Color(-9999251));
        panel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reiniciarButton = new JButton();
        reiniciarButton.setBackground(new Color(-9999251));
        reiniciarButton.setBorderPainted(false);
        reiniciarButton.setContentAreaFilled(false);
        reiniciarButton.setForeground(new Color(-9999251));
        reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facesmile.png")));
        reiniciarButton.setLabel("");
        reiniciarButton.setMaximumSize(new Dimension(30, 30));
        reiniciarButton.setMinimumSize(new Dimension(30, 30));
        reiniciarButton.setPreferredSize(new Dimension(25, 25));
        reiniciarButton.setText("");
        panel2.add(reiniciarButton, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-9999251));
        panel3.setForeground(new Color(-9999251));
        panel.add(panel3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-9999251));
        Font label2Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-590081));
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Cronometro");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cronometro = new JTextField();
        cronometro.setBackground(new Color(-590081));
        cronometro.setFocusable(false);
        Font cronometroFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, cronometro.getFont());
        if (cronometroFont != null) cronometro.setFont(cronometroFont);
        cronometro.setForeground(new Color(-590081));
        cronometro.setHorizontalAlignment(0);
        panel3.add(cronometro, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTablero.setBackground(new Color(-590081));
        Font panelTableroFont = this.$$$getFont$$$("Microsoft JhengHei", Font.PLAIN, 14, panelTablero.getFont());
        if (panelTableroFont != null) panelTablero.setFont(panelTableroFont);
        panelTablero.setForeground(new Color(-16777216));
        contentPane.add(panelTablero, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTablero.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelTablero.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        volverAlMenuButton = new JButton();
        Font volverAlMenuButtonFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, volverAlMenuButton.getFont());
        if (volverAlMenuButtonFont != null) volverAlMenuButton.setFont(volverAlMenuButtonFont);
        volverAlMenuButton.setText("Volver al Menu");
        panel4.add(volverAlMenuButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelTablero.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
