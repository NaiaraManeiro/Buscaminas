package packVista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class vPuntuaciones extends JFrame{
    private JPanel contentPane, panel1;
    private JTextArea txtPuntuaciones;
    private JButton volverAlMenuButton;
    private JLabel lblnombre;
    private JButton reiniciarButton;
    private JPanel panel2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {}
        });
    }

    public vPuntuaciones(TreeMap<Integer,String> pPuntuaciones){
    //public vPuntuaciones(){
        setTitle("Buscaminas: Puntuaciones");
        setResizable(false);
        setContentPane(contentPane);

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                Login log = new Login(); //Abrimos la pantalla de inicio
                log.setPreferredSize(new Dimension(375, 350));
                log.pack();
                log.setLocationRelativeTo(null);
                log.setVisible(true);
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtPuntuaciones.setText(null);
            }
        });
        mostrarPuntuaciones(pPuntuaciones);
    }

    private void mostrarPuntuaciones(TreeMap<Integer,String> puntuaciones){
        for (Map.Entry<Integer, String> entry : puntuaciones.entrySet()) {
            //txtPuntuaciones.setText(txtPuntuaciones.getText()+"\n"+ entry.getValue()+ "\n" + entry.getKey());
            //if (txtPuntuaciones == null) {
               // txtPuntuaciones.append(entry.getValue() + "\n" + entry.getKey());
            //} else {
                txtPuntuaciones.append(txtPuntuaciones.getText());
                txtPuntuaciones.append(System.getProperty("line.separator")); // Esto para el salto de línea
                txtPuntuaciones.append("    " + entry.getKey() + "   " + entry.getValue());
            }
        }


    private JPanel getcontentpane() {
        if (contentPane == null) {
            contentPane  = new JPanel();
            contentPane.add(getPanel1());
            contentPane.add(getPanel2());
        }
        return contentPane;
    }

    private JPanel getPanel1() {
        if (panel1 == null) {
            panel1 = new JPanel();
            panel1.add(getVolverAlMenuButton());
            panel1.add(getLblnombre());
            panel1.add(getTxtPuntuaciones());
        }
        return panel1;
    }

    private JPanel getPanel2() {
        if (panel2 == null) {
            panel2 = new JPanel();
            panel2.add(getVolverAlMenuButton());
            panel2.add(getReiniciarButton());
        }
        return panel2;
    }
    private JLabel getLblnombre() {
        if (lblnombre == null) {
            lblnombre = new JLabel("Nombre: ");
        }
        return lblnombre;
    }

    private JButton getVolverAlMenuButton() {
        if (volverAlMenuButton == null) {
            volverAlMenuButton = new JButton("Volver Al Menu");
        }
        return volverAlMenuButton;
    }

    private JButton getReiniciarButton() {
        if (reiniciarButton == null) {
            reiniciarButton = new JButton("Reiniciar Puntuaciones");
        }
        return reiniciarButton;
    }

    private JTextArea getTxtPuntuaciones(){
        if (txtPuntuaciones == null) {
            txtPuntuaciones = new JTextArea();
            txtPuntuaciones.setEnabled(false);
        }
        return txtPuntuaciones;
    }


}
