package packMain;

import packVista.Login;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        Login log = new Login();
        log.setPreferredSize(new Dimension(400, 400));
        log.pack();
        log.setLocationRelativeTo(null);
        log.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        log.setVisible(true);
    }
}
