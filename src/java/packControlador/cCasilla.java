/*package packControlador;

import packVista.buscaminas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class cCasilla implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton)e.getSource();


        if (!buscaminas.getElBuscaminas().hasPerdido()
                && !Buscaminas.getElBuscaminas().hasGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                Buscaminas.getElBuscaminas().desplegarCasilla(c);

            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (btn.isEnabled() == true) Buscaminas.getElBuscaminas().marcarDesmarcarCasilla(c);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}*/
