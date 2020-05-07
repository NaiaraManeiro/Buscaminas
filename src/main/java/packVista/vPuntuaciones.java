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
        table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        return table1;
    }

    private JTable getTable2() {
        if (table2 == null) table2 = new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table2.setDefaultRenderer(String.class, centerRenderer);
        table2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        return table2;
    }

    private JTable getTable3() {
        if (table3 == null) table3 = new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table3.setDefaultRenderer(String.class, centerRenderer);
        table3.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table3.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        return table3;

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
        tabbedPane1.setBackground(new Color(-590081));
        tabbedPane1.setFocusable(false);
        Font tabbedPane1Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, tabbedPane1.getFont());
        if (tabbedPane1Font != null) tabbedPane1.setFont(tabbedPane1Font);
        tabbedPane1.setForeground(new Color(-12828863));
        tabbedPane1.setInheritsPopupMenu(false);
        tabbedPane1.setTabLayoutPolicy(0);
        tabbedPane1.setTabPlacement(1);
        tabbedPane1.setVerifyInputWhenFocusTarget(true);
        contentPane.add(tabbedPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        nivel1 = new JPanel();
        nivel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        nivel1.setBackground(new Color(-9999251));
        nivel1.setPreferredSize(new Dimension(150, 79));
        tabbedPane1.addTab("Nivel 1", nivel1);
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-9999251));
        Font label1Font = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-590081));
        label1.setText("  Puntuación");
        nivel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-590081));
        label2.setText("Usuario");
        nivel1.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        table1 = new JTable();
        table1.setAlignmentX(0.5f);
        table1.setAlignmentY(0.5f);
        table1.setAutoCreateColumnsFromModel(true);
        table1.setBackground(new Color(-12828863));
        table1.setCellSelectionEnabled(false);
        table1.setColumnSelectionAllowed(false);
        table1.setEditingColumn(-1);
        table1.setEditingRow(-1);
        table1.setFillsViewportHeight(false);
        table1.setFocusCycleRoot(false);
        table1.setFocusable(true);
        Font table1Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 15, table1.getFont());
        if (table1Font != null) table1.setFont(table1Font);
        table1.setForeground(new Color(-590081));
        table1.setGridColor(new Color(-9999251));
        table1.setInheritsPopupMenu(false);
        table1.setName("");
        table1.setOpaque(true);
        table1.setShowHorizontalLines(false);
        table1.setShowVerticalLines(false);
        table1.setUpdateSelectionOnSort(true);
        nivel1.add(table1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(300, 150), new Dimension(300, 150), new Dimension(700, 350), 0, false));
        nivel2 = new JPanel();
        nivel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        nivel2.setBackground(new Color(-9999251));
        tabbedPane1.addTab("Nivel 2", nivel2);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Microsoft JhengHei", -1, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-590081));
        label3.setText("   Puntuación");
        nivel2.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        table2 = new JTable();
        table2.setBackground(new Color(-12828863));
        Font table2Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 15, table2.getFont());
        if (table2Font != null) table2.setFont(table2Font);
        table2.setForeground(new Color(-590081));
        table2.setGridColor(new Color(-9999251));
        table2.setShowHorizontalLines(false);
        table2.setShowVerticalLines(false);
        nivel2.add(table2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-590081));
        Font label4Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-590081));
        label4.setText("Usuario");
        nivel2.add(label4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nivel3 = new JPanel();
        nivel3.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        nivel3.setBackground(new Color(-9999251));
        tabbedPane1.addTab("Nivel 3", nivel3);
        nivel3.setBorder(BorderFactory.createTitledBorder(""));
        table3 = new JTable();
        table3.setBackground(new Color(-12828863));
        Font table3Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 15, table3.getFont());
        if (table3Font != null) table3.setFont(table3Font);
        table3.setForeground(new Color(-590081));
        table3.setGridColor(new Color(-9999251));
        table3.setShowHorizontalLines(false);
        table3.setShowVerticalLines(false);
        nivel3.add(table3, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-9999251));
        Font label5Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-590081));
        label5.setText("   Puntuación");
        nivel3.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-590081));
        label6.setText("Usuario");
        nivel3.add(label6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        volverAlMenuButton = new JButton();
        volverAlMenuButton.setBackground(new Color(-590081));
        Font volverAlMenuButtonFont = this.$$$getFont$$$("Microsoft JhengHei", Font.BOLD, 14, volverAlMenuButton.getFont());
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
