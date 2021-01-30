package packMain;

import packVista.Login;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        Login log = new Login();
        log.setPreferredSize(new Dimension(475, 350));
        log.pack();
        log.setLocationRelativeTo(null);
        log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        log.setVisible(true);
    }
}
