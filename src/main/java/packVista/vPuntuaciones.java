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
}
