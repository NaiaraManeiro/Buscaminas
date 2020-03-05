package packVista;

import packModelo.Juego;
//import packControlador.cCasilla;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class buscaminas extends JFrame implements Observer {

    private JPanel contentPane, panel, panelTablero;
    private JButton reiniciar;
    private JButton cancelar;
    private JButton[][] tablero;
    private JButton casilla;
    private JTextField cronometro;
    private JTextField puntuacion;
    private int filas, columnas;

    private boolean mostrado;

    public buscaminas() {
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        jugar();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscaminas frame = new buscaminas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        tablero = new JButton[filas][columnas];
        //cCasilla cCasilla = new cCasilla();

        /*for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casilla = new JButton();
                tablero[i][j] = casilla;
                casilla.addMouseListener(cCasilla);
                panelTablero.add(casilla);
            }
        }*/
    }

    public void jugar() {
        Juego.getmJuego().jugar();
        Juego.getmJuego().addObserver(this);
        Juego.getmJuego().getCrono().addObserver(this);
        filas = Juego.getmJuego().getTablero().getFilas();
        columnas = Juego.getmJuego().getTablero().getColumnas();
        crearTablero(filas, columnas);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
