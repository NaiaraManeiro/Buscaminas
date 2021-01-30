package packVista;

import org.json.JSONObject;
import packControlador.GestorBuscaminas;
import packModelo.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
    private static int filas, columnas, minasSinDescubrir;
    private static boolean mostrarPerdida;
    private static boolean mostrarGanado;
    private static boolean finPartida;
    private String idJug, minas;
    private int nivel;
    private String pathPackIconos;
    private String pathSonidoWin;
    private String pathSonidoGameOver;

    public Buscaminas(String idJugador, int pNivel, String pMinas) {
        idJug = idJugador;
        nivel = pNivel;
        minas = pMinas;
        setTitle("Buscaminas");
        setResizable(false);
        JSONObject personalizables = GestorBuscaminas.getMiGB().getPersonalizables();
        pathSonidoGameOver = personalizables.getString("pathSonidoGameOver");
        pathSonidoWin = personalizables.getString("pathSonidoWin");
        pathPackIconos = personalizables.getString("pathIconosTablero");
        contentPane = new JPanel();
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                Login log = new Login();
                log.setPreferredSize(new Dimension(475, 350));
                log.pack();
                log.setLocationRelativeTo(null);
                log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                log.setVisible(true);
                GestorBuscaminas.getMiGB().iniciarCrono();
                GestorBuscaminas.getMiGB().resetCrono();
                GestorBuscaminas.getMiGB().reiniciarVariables();
                mostrarPerdida = true;
                mostrarGanado = true;
                reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facesmile.gif")));
                cronometro.setText("0:00");
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                reiniciarJuego();
                GestorBuscaminas.getMiGB().resetCrono();
            }
        });

        filas = GestorBuscaminas.getMiGB().obtenerfilasnivel(nivel);
        columnas = GestorBuscaminas.getMiGB().obtenercolumnasnivel(nivel);
        minasSinDescubrir = columnas*nivel;
        jugar();
    }

    private void crearTablero(int filas, int columnas) {
        if (panelTablero != null) contentPane.remove(panelTablero);

        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(filas, columnas, 0, 0));
        contentPane.add(panelTablero, BorderLayout.CENTER);
        contentPane.setBackground(new Color(-9999251));
        panelBotones.setBackground(new Color(-9999251));
        panel.setBackground(new Color(-9999251));
        int ancho = filas * columnas + 550;
        int alto = ancho - filas * columnas / 5;
        Dimension d = new Dimension(ancho, alto);

        btntablero = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casilla = new JButton();
                if (nivel == 1) {
                    this.setMinimumSize(d);
                    this.setSize(d);
                    casilla.setBounds(i, j, 63, 63);
                } else if (nivel == 2) {
                    this.setMinimumSize(new Dimension(800, 600));
                    this.setSize(800, 500);
                    casilla.setBounds(i, j, 45, 45);
                } else {
                    this.setMinimumSize(new Dimension(1200, 600));
                    this.setSize(new Dimension(1200, 700));
                    casilla.setBounds(i, j, 45, 45);
                }
                casilla.setName("" + j + ";" + i + "");
                casilla.addMouseListener( new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        GestorBuscaminas.getMiGB().clicarCasilla(e);
                    }
                });
                this.btntablero[i][j] = casilla;
                panelTablero.add(casilla);
                asignarIcono(i,j);
            }
        }
        minasRestantes.setText(GestorBuscaminas.getMiGB().getMinasRestantes());
    }

    private void jugar() {
        mostrarPerdida = false;
        mostrarGanado = false;
        finPartida = false;
        minasSinDescubrir = columnas*nivel;
        GestorBuscaminas.getMiGB().jugar(filas,columnas,minasSinDescubrir, minas);
        GestorBuscaminas.getMiGB().addObserver(this);
        crearTablero(filas, columnas);
        GestorBuscaminas.getMiGB().imprimirChivato();
    }

    @Override
    public void update(Observable o, Object arg) {
        reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facesmile.gif")));
        GestorBuscaminas.getMiGB().addObserverCrono(this);
        if (o instanceof Juego) {
            JSONObject coordenadas = (JSONObject) arg;
            int fila = (int) coordenadas.get("fila");
            int col = (int) coordenadas.get("columna");
            String estado = GestorBuscaminas.getMiGB().getEstadoCasilla(col,fila);
            String tipo = GestorBuscaminas.getMiGB().getTipoCasilla(col,fila);
            if (estado.equals("Bandera")) {
                if (Integer.parseInt(GestorBuscaminas.getMiGB().getMinasRestantes()) < 0) {
                    JOptionPane.showMessageDialog(null, "No se pueden poner más de " + Juego.getmJuego().getTablero().getMinas() + " banderitas.",
                            "Información", JOptionPane.ERROR_MESSAGE);
                    GestorBuscaminas.getMiGB().cambiarEstado(col,fila,"NoClicada");
                    GestorBuscaminas.getMiGB().incrementarMinas();
                } else {
                    minasRestantes.setText(GestorBuscaminas.getMiGB().getMinasRestantes());
                    asignarIcono(fila,col);
                }
            }
            if (estado.equals("Clicada") || estado.equals("NoClicada")) {
                minasRestantes.setText(GestorBuscaminas.getMiGB().getMinasRestantes());
                asignarIcono(fila,col);
            }
            if (tipo.equals("CasillaMina50") && estado.equals("NoClicada") ) {
                mostrarMinas50(fila,col);
                GestorBuscaminas.getMiGB().cambiarEstado(col,fila,"Clicada");
                asignarIcono(fila,col);
            }
            if (tipo.equals("CasillaMinaReset")&& estado.equals("NoClicada")) {
                reiniciarJuego();
                GestorBuscaminas.getMiGB().cambiarEstado(col,fila,"Clicada");
            }
            if (GestorBuscaminas.getMiGB().haPerdido()) {
                mostrarMinas();
                finPartida = true;
                asignarIcono(fila, col);
                comprobarBanderas();
                if (!mostrarPerdida) {
                    reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facedead.gif")));
                    mostrarPerdida = true;
                    reproducirSonido(pathSonidoGameOver);
                    JOptionPane.showMessageDialog(null, "Has perdido la partida!", "Información", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (GestorBuscaminas.getMiGB().haGanado()) {
                if (!mostrarGanado) {
                    reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facewin.gif")));
                    reproducirSonido(pathSonidoWin);
                    JOptionPane.showMessageDialog(null,
                            "Has ganado la partida!", "Información",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarGanado = true;
                    String[] crono = cronometro.getText().split(":");
                    int min = Integer.parseInt(crono[0]);
                    int seg = Integer.parseInt(crono[1]);
                    int puntuacion = (min*60)+seg;
                    GestorBuscaminas.getMiGB().guardarPuntuacion(puntuacion, idJug, nivel);
                }
            }
        } else if (o instanceof Cronometro) {
            mostrarCronometro((String) arg);
        }
    }

    private void reproducirSonido(String pathSonido){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(pathSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void mostrarCronometro(String tiempo) {
        getTxtCronometro().setText(tiempo);
    }

    private void reiniciarJuego() {
        dispose(); //Cerramos la ventana actual
        setLocationRelativeTo(null);
        setVisible(true);
        GestorBuscaminas.getMiGB().reiniciarVariables();
        mostrarPerdida = true;
        mostrarGanado = true;
        reiniciarButton.setIcon(new ImageIcon(getClass().getResource("/facesmile.gif")));
        jugar();
    }

    private void mostrarMinas() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                String tipo = GestorBuscaminas.getMiGB().getTipoCasilla(i,j);
                if (tipo.equals("CasillaMinaNormal") || tipo.equals("CasillaMina50") || tipo.equals("CasillaMinaReset")) {
                    GestorBuscaminas.getMiGB().cambiarEstado(i,j,"Clicada");
                    asignarIcono(j,i);
                }
            }
        }
    }

    private void mostrarMinas50(int fila, int col) {
        minasSinDescubrir--;
        int cont = 0;
        int destapar = minasSinDescubrir / 2;
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                if (minasSinDescubrir > 0 && destapar > cont) {
                    String estado = GestorBuscaminas.getMiGB().getEstadoCasilla(i,j);
                    String tipo = GestorBuscaminas.getMiGB().getTipoCasilla(i,j);
                    if ((fila != j && col != i)&&(tipo.equals("CasillaMina50") || tipo.equals("CasillaMinaReset")|| tipo.equals("CasillaMinaNormal")) && estado.equals("NoClicada")) {
                        GestorBuscaminas.getMiGB().cambiarEstado(i,j,"Clicada");
                        asignarIcono(j,i);
                        cont++;
                        minasSinDescubrir--;
                    }
                }
            }
        }
        minasRestantes.setText("" + minasSinDescubrir + "");
        GestorBuscaminas.getMiGB().setMinasRestantes(minasSinDescubrir);
    }

    private void comprobarBanderas() {
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                String estado = GestorBuscaminas.getMiGB().getEstadoCasilla(i,j);
                String tipo = GestorBuscaminas.getMiGB().getTipoCasilla(i,j);
                if (estado != null && tipo != null && estado.equals("Bandera") && tipo.equals("CasillaNormal")) {
                    asignarIcono(j,i);
                }
            }
        }
    }

    private void asignarIcono(int pFila, int pColumna) {
        ImageIcon imagen = null;
        String estado = GestorBuscaminas.getMiGB().getEstadoCasilla(pColumna,pFila);
        String tipo = GestorBuscaminas.getMiGB().getTipoCasilla(pColumna,pFila);

        if (estado != null && tipo != null) {
            if (finPartida) {
                if (estado.equals("Bandera") && tipo.equals("CasillaNormal")) {
                    imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaNoBandera.png"));
                } else if (tipo.equals("CasillaMinaNormal")) imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaPrimeraMina.png"));
            } else {
                if (estado.equals("NoClicada")) {
                    imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla.png"));
                } else if (estado.equals("Clicada")) {
                    reiniciarButton.setIcon(new ImageIcon(getClass().getResource(  "/faceooh.gif")));
                    if (tipo.equals("CasillaMinaNormal")) {
                        imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaMina.png"));
                    } else if (tipo.equals("CasillaMinaReset")) {
                        imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaMinaReset.png"));
                    } else if (tipo.equals("CasillaMina50")) {
                        imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaMina50.png"));
                    } else if (tipo.equals("CasillaNormal")) {
                        switch (GestorBuscaminas.getMiGB().getNumero(pColumna,pFila)) {
                            case 0:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaVacia.png"));
                                break;
                            case 1:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla1.png"));
                                break;
                            case 2:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla2.png"));
                                break;
                            case 3:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla3.png"));
                                break;
                            case 4:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla4.png"));
                                break;
                            case 5:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla5.png"));
                                break;
                            case 6:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla6.png"));
                                break;
                            case 7:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla7.png"));
                                break;
                            case 8:
                                imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/Casilla8.png"));
                                break;
                            default:
                                break;
                        }
                    }
                } else if (estado.equals("Bandera")) {
                    imagen = new ImageIcon(getClass().getResource(pathPackIconos + "/CasillaBandera.png"));
                }
            }

            if (imagen != null) {
                ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(this.btntablero[pFila][pColumna].getWidth(), this.btntablero[pFila][pColumna].getHeight(), Image.SCALE_DEFAULT));
                this.btntablero[pFila][pColumna].setIcon(icono);
                this.btntablero[pFila][pColumna].setText("");
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

}
