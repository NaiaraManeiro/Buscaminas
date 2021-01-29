package packVista;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.json.JSONArray;
import packControlador.GestorBuscaminas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class IU_Consultarinfojuego extends JDialog {
    private JPanel rootPanel;
    private JPanel infoPanel;
    private JComboBox comboBoxNivel;
    private JPanel infonivelPanel;
    private JPanel nivelPanel;
    private JLabel lblfilas;
    private JLabel lblcolumnas;
    private JLabel lbltiempo;
    private JTextField textFieldFilas;
    private JTextField textFieldColumnas;
    private JTextField textFieldtiempo;
    private JButton guardarDatosButton;
    private JButton volverAlMenuButton;
    private JLabel lblNivel;
    private JButton consultarInfoButton;

    public IU_Consultarinfojuego() {
        setTitle("Menú Administrador: Consultar Información Juego");
        setResizable(false);
        setContentPane(rootPanel);

        JSONArray niveles = GestorBuscaminas.getMiGB().obtenerNiveles();
        for (int i = 0; i < niveles.length(); i++) {
            comboBoxNivel.addItem(niveles.get(i));
        }


        consultarInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarInfoNivel(getlevel());
            }
        });

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAlMenu();
            }
        });

        guardarDatosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarDatosJuego();
            }
        });
    }

    private void guardarDatosJuego() {
        if (getlevel() != 0) {
            String pattern = "[0-9]*";
            if (!textFieldFilas.getText().equals("") && !textFieldColumnas.getText().equals("")) {
                if (textFieldFilas.getText().matches(pattern) && textFieldColumnas.getText().matches(pattern)) {
                    Integer filas = parseInt(textFieldFilas.getText().trim());
                    Integer col = parseInt(textFieldColumnas.getText().trim());
                    //Integer tiempo = parseInt(textFieldtiempo.getText().trim());
                    if (getlevel() == 1) {
                        if (filas > 1 && filas <= 7) {
                            if (col > 0 && col <= 10) {
                                guardarDatos(1, filas, col);
                            } else {
                                JOptionPane.showMessageDialog(comboBoxNivel, "El número de columnas tiene que estar entre 1 y 10 columnas");
                            }
                        } else {
                            JOptionPane.showMessageDialog(comboBoxNivel, "El número de filas tiene que estar entre 2 y 7 filas");
                        }
                    } else if (getlevel() == 2) {
                        if (filas >= 5 && filas <= 10) {
                            if (col >= 6 && col <= 15) {
                                guardarDatos(2, filas, col);
                            } else {
                                JOptionPane.showMessageDialog(comboBoxNivel, "El número de columnas tiene que estar entre 6 y 15 columnas");
                            }
                        } else {
                            JOptionPane.showMessageDialog(comboBoxNivel, "El número de filas tiene que estar entre 5 y 10 filas");
                        }
                    } else if (getlevel() == 3) {
                        if (filas >= 8 && filas <= 12) {
                            if (col >= 10 && col <= 25) {
                                guardarDatos(3, filas, col);
                            } else {
                                JOptionPane.showMessageDialog(comboBoxNivel, "El número de columnas tiene que estar entre 10 y 25 columnas");
                            }
                        } else {
                            JOptionPane.showMessageDialog(comboBoxNivel, "El número de filas tiene que estar entre 8 y 12 filas");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(comboBoxNivel, "TERRIBLE ERROR!!! Hay campos en los que nos valores no son numéricos");
                }
            } else {
                JOptionPane.showMessageDialog(comboBoxNivel, "TERRIBLE ERROR!!! Hay campos que están en blanco");
            }
        } else {
            JOptionPane.showMessageDialog(comboBoxNivel, "TERRIBLE ERROR!!! Seleccione un nivel para modificar los datos.");
        }
    }

    private int getlevel() {
        int level = 0;
        if (!comboBoxNivel.getSelectedItem().equals("")) {
            if (comboBoxNivel.getSelectedItem().equals(1)) {
                level = 1;
            } else if (comboBoxNivel.getSelectedItem().equals(2)) {
                level = 2;
            } else {
                level = 3;
            }
        }
        return level;
    }

    private void guardarDatos(int pLevel, int pFilas, int pCol) {
        dispose();
        IU_GuardarDatosJuego guardar = null;
        try {
            guardar = new IU_GuardarDatosJuego(pLevel, pFilas, pCol);
            guardar.setPreferredSize(new Dimension(385, 200));
            guardar.pack();
            guardar.setLocationRelativeTo(null);
            guardar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            guardar.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void consultarInfoNivel(Integer level) {
        if (level != 0) {
            dispose();
            IU_MostrarDatosNivel nivel = null;
            try {
                comboBoxNivel.getSelectedItem();
                nivel = new IU_MostrarDatosNivel(level);
                nivel.setPreferredSize(new Dimension(450, 310));
                nivel.pack();
                nivel.setLocationRelativeTo(null);
                nivel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                nivel.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(comboBoxNivel, "TERRIBLE ERROR!!! Seleccione un nivel para consultar.");
        }
    }

    /*private void volverAlMenu() {
        dispose();
        IU_Admin admin = new IU_Admin();
        admin.setPreferredSize(new Dimension(385, 330));
        admin.pack();
        admin.setLocationRelativeTo(null);
        admin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        admin.setVisible(true);
    }*/

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
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setBackground(new Color(-9999251));
        rootPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menú Administrador: Consultar Información del Juego", TitledBorder.LEFT, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, rootPanel.getFont()), new Color(-590081)));
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        infoPanel.setBackground(new Color(-590081));
        rootPanel.add(infoPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-590081));
        label1.setForeground(new Color(-590081));
        label1.setText(".");
        infoPanel.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblNivel = new JLabel();
        Font lblNivelFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, lblNivel.getFont());
        if (lblNivelFont != null) lblNivel.setFont(lblNivelFont);
        lblNivel.setText("Elija el nivel que desee modificar: ");
        infoPanel.add(lblNivel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infonivelPanel = new JPanel();
        infonivelPanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        infonivelPanel.setBackground(new Color(-590081));
        infoPanel.add(infonivelPanel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblfilas = new JLabel();
        lblfilas.setBackground(new Color(-12828863));
        Font lblfilasFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, lblfilas.getFont());
        if (lblfilasFont != null) lblfilas.setFont(lblfilasFont);
        lblfilas.setText("Número de Filas:");
        infonivelPanel.add(lblfilas, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblcolumnas = new JLabel();
        lblcolumnas.setBackground(new Color(-590081));
        Font lblcolumnasFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, lblcolumnas.getFont());
        if (lblcolumnasFont != null) lblcolumnas.setFont(lblcolumnasFont);
        lblcolumnas.setText("Número de Columnas:");
        infonivelPanel.add(lblcolumnas, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lbltiempo = new JLabel();
        Font lbltiempoFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, lbltiempo.getFont());
        if (lbltiempoFont != null) lbltiempo.setFont(lbltiempoFont);
        lbltiempo.setText("Límite de tiempo (en seg):");
        infonivelPanel.add(lbltiempo, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guardarDatosButton = new JButton();
        guardarDatosButton.setBackground(new Color(-590081));
        Font guardarDatosButtonFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, guardarDatosButton.getFont());
        if (guardarDatosButtonFont != null) guardarDatosButton.setFont(guardarDatosButtonFont);
        guardarDatosButton.setForeground(new Color(-12828863));
        guardarDatosButton.setText("Guardar Datos Modificados");
        infonivelPanel.add(guardarDatosButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-590081));
        label2.setForeground(new Color(-590081));
        label2.setText(".");
        infonivelPanel.add(label2, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setBackground(new Color(-590081));
        label3.setForeground(new Color(-590081));
        label3.setText(".");
        infonivelPanel.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-590081));
        label4.setForeground(new Color(-590081));
        label4.setText(".");
        infonivelPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxNivel = new JComboBox();
        comboBoxNivel.setBackground(new Color(-590081));
        Font comboBoxNivelFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, comboBoxNivel.getFont());
        if (comboBoxNivelFont != null) comboBoxNivel.setFont(comboBoxNivelFont);
        comboBoxNivel.setForeground(new Color(-12828863));
        infoPanel.add(comboBoxNivel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nivelPanel = new JPanel();
        nivelPanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        nivelPanel.setBackground(new Color(-590081));
        infoPanel.add(nivelPanel, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textFieldFilas = new JTextField();
        Font textFieldFilasFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, textFieldFilas.getFont());
        if (textFieldFilasFont != null) textFieldFilas.setFont(textFieldFilasFont);
        textFieldFilas.setForeground(new Color(-12828863));
        textFieldFilas.setHorizontalAlignment(0);
        nivelPanel.add(textFieldFilas, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldColumnas = new JTextField();
        Font textFieldColumnasFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, textFieldColumnas.getFont());
        if (textFieldColumnasFont != null) textFieldColumnas.setFont(textFieldColumnasFont);
        textFieldColumnas.setForeground(new Color(-12828863));
        textFieldColumnas.setHorizontalAlignment(0);
        nivelPanel.add(textFieldColumnas, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldtiempo = new JTextField();
        textFieldtiempo.setBackground(new Color(-590081));
        textFieldtiempo.setEditable(false);
        Font textFieldtiempoFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, textFieldtiempo.getFont());
        if (textFieldtiempoFont != null) textFieldtiempo.setFont(textFieldtiempoFont);
        textFieldtiempo.setForeground(new Color(-12828863));
        textFieldtiempo.setHorizontalAlignment(0);
        textFieldtiempo.setText("No Disponible");
        nivelPanel.add(textFieldtiempo, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        volverAlMenuButton = new JButton();
        volverAlMenuButton.setBackground(new Color(-590081));
        Font volverAlMenuButtonFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, volverAlMenuButton.getFont());
        if (volverAlMenuButtonFont != null) volverAlMenuButton.setFont(volverAlMenuButtonFont);
        volverAlMenuButton.setForeground(new Color(-12828863));
        volverAlMenuButton.setText("Volver Al Menú");
        nivelPanel.add(volverAlMenuButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-590081));
        label5.setForeground(new Color(-590081));
        label5.setText("Label");
        nivelPanel.add(label5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setBackground(new Color(-590081));
        label6.setForeground(new Color(-590081));
        label6.setText(".");
        nivelPanel.add(label6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-590081));
        label7.setForeground(new Color(-590081));
        label7.setText(".");
        nivelPanel.add(label7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setForeground(new Color(-590081));
        label8.setText(".");
        infoPanel.add(label8, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setForeground(new Color(-590081));
        label9.setText(".");
        infoPanel.add(label9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        consultarInfoButton = new JButton();
        consultarInfoButton.setBackground(new Color(-590081));
        Font consultarInfoButtonFont = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, consultarInfoButton.getFont());
        if (consultarInfoButtonFont != null) consultarInfoButton.setFont(consultarInfoButtonFont);
        consultarInfoButton.setText("Consultar Información");
        infoPanel.add(consultarInfoButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setBackground(new Color(-590081));
        label10.setForeground(new Color(-590081));
        label10.setText(".");
        infoPanel.add(label10, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return rootPanel;
    }
}