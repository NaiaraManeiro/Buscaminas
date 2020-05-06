package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import packModelo.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JPanel rootpanel;
    private JButton aceptarButton;
    private JTextField textusuario;
    private JLabel lblnombre;
    private JLabel lblnivel;
    private JPanel panel1;
    private JComboBox comboBoxNivel;
    private JButton puntuacionesButton;

    public static void main(String[] args) {
        try {
            Login dialog = new Login();
            dialog.setPreferredSize(new Dimension(375, 350));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Login() {
        setTitle("Buscaminas: Usuario");
        setResizable(false);
        setContentPane(rootpanel);
        comboBoxNivel.addItem(1);
        comboBoxNivel.addItem(2);
        comboBoxNivel.addItem(3);
        aceptarButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                String usuario = textusuario.getText();
                Object nivel = comboBoxNivel.getSelectedItem();
                if (validarDatos(usuario)) {
                    if (nivel.equals(1)) {
                        Juego.getmJuego().crearUsuario(usuario, 1);
                    } else if (nivel.equals(2)) {
                        Juego.getmJuego().crearUsuario(usuario, 2);
                    } else if (nivel.equals(3)) {
                        Juego.getmJuego().crearUsuario(usuario, 3);
                    }
                    Login.this.dispose(); //Cerramos la ventana actual
                     //Abrimos la pantalla del juego con el nivel marcado
                    Buscaminas.getmBuscaminas().setLocationRelativeTo(null);
                    Buscaminas.getmBuscaminas().setVisible(true);
                }
            }
        });

        puntuacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                vPuntuaciones punt = new vPuntuaciones();
                punt.setPreferredSize(new Dimension(450, 400));
                punt.pack();
                punt.setLocationRelativeTo(null);
                punt.setVisible(true);
            }
        });
    }

    private boolean validarDatos(String usuario) {
        boolean valido = false;
        if ((!usuario.equals(""))) {
            String pattern = "^[a-zA-Z0-9]*$";
            if (textusuario.getText().matches(pattern)) {
                valido = true;
            } else {
                JOptionPane.showMessageDialog(textusuario, "Nombre de usuario no valido");
                textusuario.setText(null);
            }
        } else {
            JOptionPane.showMessageDialog(textusuario, "Introduzca un nombre de usuario para jugar");
            textusuario.setText(null);
        }
        return valido;
    }

    private JButton getAceptarButton() {
        if (aceptarButton == null) {
            aceptarButton = new JButton("Aceptar");
        }
        return aceptarButton;
    }

    private JButton getPuntuacionesButton() {
        if (puntuacionesButton == null) {
            puntuacionesButton = new JButton("Puntuaciones");
        }
        return puntuacionesButton;
    }

    private JPanel getrootpanel() {
        if (rootpanel == null) {
            rootpanel = new JPanel();
            rootpanel.add(getPanel1());
        }
        return rootpanel;
    }

    private JTextField getTextusuario() {
        if (textusuario == null) {
            textusuario = new JTextField();
        }
        return textusuario;
    }

    private JLabel getLblnombre() {
        if (lblnombre == null) {
            lblnombre = new JLabel("Nombre: ");
        }
        return lblnombre;
    }

    private JLabel getLblnivel() {
        if (lblnivel == null) {
            lblnivel = new JLabel("Nivel: ");
        }
        return lblnombre;
    }

    private JPanel getPanel1() {
        if (panel1 == null) {
            panel1 = new JPanel();
            rootpanel.add(getAceptarButton());
            rootpanel.add(getPuntuacionesButton());
            rootpanel.add(getcomboBoxNivel());
            rootpanel.add(getLblnivel());
            rootpanel.add(getLblnombre());
            rootpanel.add(getTextusuario());
        }
        return panel1;
    }

    private JComboBox getcomboBoxNivel() {
        if (comboBoxNivel == null) {
            comboBoxNivel = new JComboBox();
        }
        return comboBoxNivel;
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
        rootpanel = new JPanel();
        rootpanel.setLayout(new BorderLayout(0, 0));
        rootpanel.setBackground(new Color(-9999251));
        rootpanel.setEnabled(true);
        Font rootpanelFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, rootpanel.getFont());
        if (rootpanelFont != null) rootpanel.setFont(rootpanelFont);
        rootpanel.setForeground(new Color(-590081));
        rootpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Buscaminas: Usuario", TitledBorder.LEADING, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, rootpanel.getFont()), new Color(-590081)));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-590081));
        panel1.setEnabled(true);
        rootpanel.add(panel1, BorderLayout.CENTER);
        lblnombre = new JLabel();
        lblnombre.setBackground(new Color(-16777216));
        lblnombre.setEnabled(true);
        Font lblnombreFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, lblnombre.getFont());
        if (lblnombreFont != null) lblnombre.setFont(lblnombreFont);
        lblnombre.setForeground(new Color(-16777216));
        lblnombre.setText("Introduzca un nombre de usuario:");
        panel1.add(lblnombre, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textusuario = new JTextField();
        Font textusuarioFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, textusuario.getFont());
        if (textusuarioFont != null) textusuario.setFont(textusuarioFont);
        textusuario.setHorizontalAlignment(0);
        panel1.add(textusuario, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblnivel = new JLabel();
        lblnivel.setBackground(new Color(-16777216));
        Font lblnivelFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, lblnivel.getFont());
        if (lblnivelFont != null) lblnivel.setFont(lblnivelFont);
        lblnivel.setForeground(new Color(-16777216));
        lblnivel.setText("Seleccione un nivel para jugar:");
        panel1.add(lblnivel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxNivel = new JComboBox();
        Font comboBoxNivelFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, comboBoxNivel.getFont());
        if (comboBoxNivelFont != null) comboBoxNivel.setFont(comboBoxNivelFont);
        panel1.add(comboBoxNivel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-590081));
        rootpanel.add(panel2, BorderLayout.SOUTH);
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        aceptarButton = new JButton();
        aceptarButton.setBackground(new Color(-590081));
        aceptarButton.setEnabled(true);
        Font aceptarButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, aceptarButton.getFont());
        if (aceptarButtonFont != null) aceptarButton.setFont(aceptarButtonFont);
        aceptarButton.setForeground(new Color(-16777216));
        aceptarButton.setHideActionText(false);
        aceptarButton.setText("ACEPTAR");
        aceptarButton.putClientProperty("hideActionText", Boolean.FALSE);
        panel2.add(aceptarButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        puntuacionesButton = new JButton();
        puntuacionesButton.setBackground(new Color(-590081));
        Font puntuacionesButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, puntuacionesButton.getFont());
        if (puntuacionesButtonFont != null) puntuacionesButton.setFont(puntuacionesButtonFont);
        puntuacionesButton.setForeground(new Color(-16777216));
        puntuacionesButton.setText("PUNTUACIONES");
        panel2.add(puntuacionesButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
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
        return rootpanel;
    }

}