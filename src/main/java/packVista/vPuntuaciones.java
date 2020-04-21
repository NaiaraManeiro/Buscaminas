package packVista;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import packModelo.Puntuaciones;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class vPuntuaciones extends JDialog {
    private JPanel contentPane;
    private JButton volverAlMenuButton;
    private JLabel lblnombre;
    private JButton reiniciarButton;
    private JPanel panel2;
    private JTabbedPane tabbedPane1;
    private JPanel nivel1;
    private JPanel nivel2;
    private JPanel nivel3;
    private JTable table1;
    private JTable table2;
    private JTable table3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    public vPuntuaciones() {
        setTitle("Puntuaciones");
        setResizable(false);
        setContentPane(getcontentpane());
        setLocationRelativeTo(null);

        volverAlMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose(); //Cerramos la ventana actual
                //Login log = new Login(); //Abrimos la pantalla de inicio
                //log.setPreferredSize(new Dimension(375, 350));
                //log.pack();
                //log.setLocationRelativeTo(null);
                //log.setVisible(true);
            }
        });
        ponerPuntuacion();
    }

    private void ponerPuntuacion() {
        String column_names[] = {"Tiempo", "Usuario"};
        for (int i = 1; i <= 3; i++) {
            TreeMap<Integer, String> lista = Puntuaciones.getMiPuntuaciones().getLista(i);
            DefaultTableModel model = new DefaultTableModel(column_names, 2);
            for (Object obj : lista.keySet()) {
                model.addRow(new Object[]{obj, lista.get(obj)});
            }
            if (i == 1) {
                table1.setModel(model);
            } else if (i == 2) {
                table2.setModel(model);
            } else if (i == 3) {
                table3.setModel(model);
            }
        }
    }

    private JPanel getcontentpane() {
        if (contentPane == null) {
            contentPane = new JPanel();
            contentPane.add(getLblnombre());
            contentPane.add(getTabbedPane1());
            contentPane.add(getPanel2());
        }
        return contentPane;
    }

    private JPanel getPanel2() {
        if (panel2 == null) {
            panel2 = new JPanel();
            panel2.add(getVolverAlMenuButton());
            panel2.add(getReiniciarButton());
        }
        return panel2;
    }

    private JTabbedPane getTabbedPane1() {
        if (tabbedPane1 == null) {
            tabbedPane1 = new JTabbedPane();
            tabbedPane1.addTab("Nivel 1", getNivel1());
            tabbedPane1.addTab("Nivel 2", getNivel2());
            tabbedPane1.addTab("Nivel 3", getNivel3());
        }
        return tabbedPane1;
    }

    private JPanel getNivel1() {
        if (nivel1 == null) {
            nivel1 = new JPanel();
            nivel1.add(getTable1());
        }
        return nivel1;
    }

    private JPanel getNivel2() {
        if (nivel2 == null) {
            nivel2 = new JPanel();
            nivel2.add(getTable2());
        }
        return nivel2;
    }

    private JPanel getNivel3() {
        if (nivel3 == null) {
            nivel3 = new JPanel();
            nivel3.add(getTable3());
        }
        return nivel3;
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

    private JTable getTable1() {
        if (table1 == null) table1 = new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table1.setDefaultRenderer(String.class, centerRenderer);
        return table1;
    }

    private JTable getTable2() {
        if (table2 == null) table2 = new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table2.setDefaultRenderer(String.class, centerRenderer);
        return table2;
    }

    private JTable getTable3() {
        if (table3 == null) table3 = new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table3.setDefaultRenderer(String.class, centerRenderer);
        return table3;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setBackground(new Color(-9999251));
        contentPane.setForeground(new Color(-590081));
        contentPane.setMinimumSize(new Dimension(375, 150));
        contentPane.setOpaque(true);
        contentPane.setPreferredSize(new Dimension(375, 150));
        contentPane.setRequestFocusEnabled(true);
        contentPane.setVerifyInputWhenFocusTarget(true);
        contentPane.setVisible(true);
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, contentPane.getFont()), new Color(-590081)));
        lblnombre = new JLabel();
        lblnombre.setBackground(new Color(-9999251));
        Font lblnombreFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 18, lblnombre.getFont());
        if (lblnombreFont != null) lblnombre.setFont(lblnombreFont);
        lblnombre.setForeground(new Color(-590081));
        lblnombre.setText("TOP 10 JUGADORES");
        contentPane.add(lblnombre, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPane1 = new JTabbedPane();
        contentPane.add(tabbedPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        nivel1 = new JPanel();
        nivel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Nivel 1", nivel1);
        table1 = new JTable();
        table1.setBackground(new Color(-9999251));
        Font table1Font = this.$$$getFont$$$(null, -1, 15, table1.getFont());
        if (table1Font != null) table1.setFont(table1Font);
        table1.setForeground(new Color(-590081));
        table1.setGridColor(new Color(-9999251));
        table1.setShowHorizontalLines(false);
        table1.setShowVerticalLines(false);
        nivel1.add(table1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        nivel2 = new JPanel();
        nivel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Nivel 2", nivel2);
        table2 = new JTable();
        table2.setBackground(new Color(-9999251));
        Font table2Font = this.$$$getFont$$$(null, -1, 15, table2.getFont());
        if (table2Font != null) table2.setFont(table2Font);
        table2.setForeground(new Color(-590081));
        table2.setGridColor(new Color(-9999251));
        table2.setShowHorizontalLines(false);
        table2.setShowVerticalLines(false);
        nivel2.add(table2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        nivel3 = new JPanel();
        nivel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Nivel 3", nivel3);
        table3 = new JTable();
        table3.setBackground(new Color(-9999251));
        Font table3Font = this.$$$getFont$$$(null, -1, 15, table3.getFont());
        if (table3Font != null) table3.setFont(table3Font);
        table3.setForeground(new Color(-590081));
        table3.setGridColor(new Color(-9999251));
        table3.setShowHorizontalLines(false);
        table3.setShowVerticalLines(false);
        nivel3.add(table3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        volverAlMenuButton = new JButton();
        volverAlMenuButton.setBackground(new Color(-590081));
        Font volverAlMenuButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, volverAlMenuButton.getFont());
        if (volverAlMenuButtonFont != null) volverAlMenuButton.setFont(volverAlMenuButtonFont);
        volverAlMenuButton.setForeground(new Color(-16777216));
        volverAlMenuButton.setHorizontalTextPosition(0);
        volverAlMenuButton.setText("Volver al Menu");
        contentPane.add(volverAlMenuButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
