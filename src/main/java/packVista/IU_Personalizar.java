package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.GestorBuscaminas;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class IU_Personalizar extends JDialog {
    private JPanel contentPane;
    private JButton guardar;
    private JTabbedPane tabbedPane1;
    private JComboBox opcionesIcoTablero;
    private JScrollPane jspsonidoswin;
    private JPanel jpanelwin;
    private JPanel pestanaSonidos;
    private JScrollPane jspsonidosgo;
    private JPanel jpanellose;
    private JPanel jpiconostablero;
    private JPanel jpiconosusuario;
    private JPanel jpButtonContainer;
    private JPanel pestanaIcoTablero;
    private JPanel wrapper;
    private JPanel jpanelPreview;
    private JScrollPane jspIcoTablero;
    private JButton volverAlMenuButton;
    private JSONArray sonidosWin;
    private JSONArray sonidosGameOver;
    private JSONArray iconosTablero;
    private JSONObject personalizablesJugador;

    public IU_Personalizar() {

        getPersonalizables();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardar);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Personaliza tu buscaminas");
        getIconosDeTablero();
        ponerSonidos(sonidosWin, jpanelwin, jspsonidoswin, (String) personalizablesJugador.get("pathSonidoWin"), true);
        ponerSonidos(sonidosGameOver, jpanellose, jspsonidosgo, (String) personalizablesJugador.get("pathSonidoGameOver"), false);
        guardar.addActionListener(e -> onGuardar());
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        volverAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        getPreferredSize();
        pack();
        setLocationRelativeTo(null);
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void ponerSonidos(JSONArray sonidos, JPanel panel, JScrollPane jsp, String idSonidoJugador, boolean tipoSonido) {
        ButtonGroup win = new ButtonGroup();
        JSONObject sonidoObjeto;
        JRadioButton sonido;
        panel = new JPanel();
        Box verticalBox = Box.createVerticalBox();
        for (int i = 0; i < sonidos.length(); i++) {
            sonidoObjeto = (JSONObject) sonidos.get(i);
            String path = sonidoObjeto.getString("path");
            JButton reproducir = new JButton("Reproducir");
            reproducir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                }
            });
            sonido = new JRadioButton((String) sonidoObjeto.getString("nombre"));
            JRadioButton finalSonido = sonido;
            win.add(sonido);
            sonido.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombreSonidoSeleccionado = finalSonido.getText();
                    boolean enc = false;
                    int i = 0;
                    if (tipoSonido) { //si sonidosWin
                        while (!enc && i < sonidosWin.length()) {
                            JSONObject iconoTablero = (JSONObject) sonidosWin.get(i);
                            if (iconoTablero.getString("nombre").equals(nombreSonidoSeleccionado)) {
                                enc = true;
                                personalizablesJugador.put("pathSonidoWin", iconoTablero.getString("path"));
                            } else i++;
                        }
                    } else {
                        while (!enc && i < sonidosGameOver.length()) {
                            JSONObject iconoTablero = (JSONObject) sonidosGameOver.get(i);
                            if (iconoTablero.getString("nombre").equals(nombreSonidoSeleccionado)) {
                                enc = true;
                                personalizablesJugador.put("pathSonidoGameOver", iconoTablero.getString("path"));
                            } else i++;
                        }
                    }
                }
            });
            if (idSonidoJugador.equals(sonidoObjeto.getString("path"))) sonido.setSelected(true);
            Box hori = Box.createHorizontalBox();
            hori.add(sonido);
            hori.add(reproducir);
            verticalBox.add(hori);
            panel.add(verticalBox);
        }
        jsp.setViewportView(panel);
    }

    private void getIconosDeTablero() {
        DefaultComboBoxModel iconosTableroModel = new DefaultComboBoxModel();
        opcionesIcoTablero.setModel(iconosTableroModel);
        jpanelPreview = new JPanel();
        //rellenamos el jcombobox
        for (int i = 0; i < iconosTablero.length(); i++) {
            JSONObject nombreIcono = (JSONObject) iconosTablero.get(i);
            iconosTableroModel.addElement(nombreIcono.get("nombre"));
        }
        //marcamos lo que tenga seleccionado el usuario
        String idIcoTableroJugador = (String) personalizablesJugador.get("pathIconosTablero");
        boolean enc = false;
        int i = 0;
        while (!enc && i < iconosTablero.length()) {
            JSONObject iconoTablero = (JSONObject) iconosTablero.get(i);
            String idiconoTablero = iconoTablero.getString("path");
            if (idiconoTablero.equals(idIcoTableroJugador)) {
                enc = true;
                ponerPreview(iconoTablero.getString("nombre"));
                opcionesIcoTablero.setSelectedItem(iconoTablero.getString("nombre"));
            } else i++;
        }
        // actualizamos las previews de las imagenes de lo seleccionado por el usuario antes de guardar
        opcionesIcoTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ponerPreview(String.valueOf(opcionesIcoTablero.getSelectedItem()));
                boolean enc = false;
                int i = 0;
                while (!enc && i < iconosTablero.length()) {
                    JSONObject iconoUsuario = (JSONObject) iconosTablero.get(i);
                    if (iconoUsuario.getString("nombre").equals(String.valueOf(opcionesIcoTablero.getSelectedItem()))) {
                        enc = true;
                        personalizablesJugador.put("pathIconosTablero", iconoUsuario.getString("path"));
                    } else i++;
                }
            }
        });
    }

    private void ponerPreview(String seleccionado2) {
        String seleccionado = seleccionado2;
        boolean enc = false;
        int j = 0;
        //buscamos el path del pack de iconos
        String path = "";
        while (!enc && j < iconosTablero.length()) {
            JSONObject icono = (JSONObject) iconosTablero.get(j);
            if (icono.get("nombre").equals(seleccionado)) {
                enc = true;
                path = (String) icono.get("path");
            }
            j++;
        }
        //añadir la imagen de previews de los packs de iconos
        Image dimg = null;
        try {
            URL pppp = new URL(String.valueOf(getClass().getResource(path)) + "/preview.png");
            BufferedImage img = ImageIO.read(pppp);
            dimg = img.getScaledInstance(320, 180, Image.SCALE_SMOOTH);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        assert dimg != null;
        ImageIcon icon = new ImageIcon(dimg);
        JRadioButton preview = new JRadioButton(icon);
        jpanelPreview = new JPanel();
        Box horizontal = Box.createHorizontalBox();
        horizontal.add(preview);
        jpanelPreview.add(horizontal);
        jpanelPreview.repaint();
        jpanelPreview.revalidate();
        jspIcoTablero.setViewportView(jpanelPreview);
    }

    private void getPersonalizables() {
        JSONObject personalizables = GestorBuscaminas.getMiGB().obtenerTodosPersonalizables();
        sonidosWin = (JSONArray) personalizables.get("SonidoWin");
        sonidosGameOver = (JSONArray) personalizables.get("SonidoGameOver");
        iconosTablero = (JSONArray) personalizables.get("IconosTablero");
        personalizablesJugador = GestorBuscaminas.getMiGB().getPersonalizables();
    }


    private void onGuardar() {
        GestorBuscaminas.getMiGB().ponerPersonalizables((String) personalizablesJugador.get("pathSonidoWin"), (String) personalizablesJugador.get("pathSonidoGameOver"), (String) personalizablesJugador.get("pathIconosTablero"));
        dispose();
    }

    private void onCancel() {
        dispose();
    }


    public static void main(String[] args) {
        IU_Personalizar dialog = new IU_Personalizar();
        dialog.pack();
        dialog.setVisible(true);
    }
}
