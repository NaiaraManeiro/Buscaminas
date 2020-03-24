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

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Login log = new Login(); //Abrimos la pantalla de inicio
                log.setPreferredSize(new Dimension(375, 350));
                log.pack();
                log.setLocationRelativeTo(null);
                log.setVisible(true);
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
                casilla.setBounds(i, j, 53, 53);
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
        Juego.getmJuego().jugar();
        Juego.getmJuego().addObserver(this);
        Juego.getmJuego().getCrono().addObserver(this);
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

            if (c.getEstado() instanceof Clicada) {
                if (c.getEstado() instanceof Bandera) {
                    minasRestantes.setText(Juego.getmJuego().getnMinasRestantes());
                }
                asignarIcono(coord);
            }
            if (Juego.getmJuego().haPerdido()) {
                btntablero[fila][col].setBackground(new Color(252, 3, 3)); // La mina pulsada muestra otro fondo
                mostrarMinas();
                if (!mostrarPerdida) {
                    JOptionPane.showMessageDialog(null, "Has perdido la partida!",
                            "Información", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (Juego.getmJuego().haGanado()) {
                if (!mostrarGanado) {
                    JOptionPane.showMessageDialog(null,
                            "Has ganado la partida!", "Información",
                            JOptionPane.INFORMATION_MESSAGE);
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

    private void asignarIcono(Coordenada pC) {
        boolean bloquear = false;
        ImageIcon imagen = null;
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(pC.getColumna(), pC.getFila());
        if (c.getEstado() instanceof NoClicada) {
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

    private JTextField getTxtCronometro() {
        if (cronometro == null) {
            cronometro = new JTextField();
            cronometro.setEnabled(false);
            cronometro.setColumns(3);

        }
        return cronometro;
    }

}
