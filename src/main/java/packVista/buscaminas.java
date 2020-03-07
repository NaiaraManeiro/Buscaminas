package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import packModelo.*;
import packControlador.cCasilla;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class buscaminas extends JFrame implements Observer {

    private JPanel contentPane, panel, panelTablero;
    private JButton reiniciar;
    private JButton cancelar;
    private JButton[][] btntablero;
    private JButton casilla;
    private JTextField minasRestantes;
    private JTextField cronometro;
    private int filas, columnas;

    private boolean mostrado;

    public buscaminas() {
        setTitle("Buscaminas");

        contentPane = new JPanel();
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

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
        btntablero = new JButton[columnas][filas];

        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                casilla = new JButton();
                btntablero[i][j] = casilla;
                Coordenada coord = new Coordenada(i, j);
                casilla.addMouseListener(new cCasilla(coord));
                panelTablero.add(casilla);
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

    private void asignarIcono (Coordenada pC){
        ImageIcon imagen = null;
        Casilla c = Tablero.getmTablero().devolverCasilla(pC.getFila(), pC.getColumna());
        if (!c.estaPulsada()) {
            imagen = new ImageIcon(getClass().getResource("facingDown.png"));
        }
        else {
            if (c instanceof CasillaMina) {
                imagen = new ImageIcon(getClass().getResource("bomb.png"));
            }
            else if (c instanceof CasillaNormal) {
                switch (((CasillaNormal) c).getNumero()){
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
            }
            else if (c.estamarcada()){
                imagen = new ImageIcon(getClass().getResource("flagged.png"));
            }

        }

        if (imagen != null) {
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(
                    btntablero[pC.getFila()][pC.getColumna()].getWidth(),
                    btntablero[pC.getFila()][pC.getColumna()].getHeight(), Image.SCALE_DEFAULT));
                    btntablero[pC.getFila()][pC.getColumna()].setIcon(icono);
                    btntablero[pC.getFila()][pC.getColumna()].setText("");
                    btntablero[pC.getFila()][pC.getColumna()].setEnabled(false);
                    btntablero[pC.getFila()][pC.getColumna()].setDisabledIcon(btntablero[pC.getFila()][pC.getColumna()].getIcon());
        }
    }


}
