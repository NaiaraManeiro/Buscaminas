package packControlador;

import packModelo.*;
import packVista.buscaminas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class cCasilla implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        Casilla c = Tablero.getmTablero().devolverCasilla(x, y);

        if (!Juego.getmJuego().haPerdido() && !Tablero.getmTablero().haGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) { //Mira a ver si se ha clicado con el botón izquierdo del ratón
                if (c instanceof CasillaMina) {
                    Juego.getmJuego().terminarPartida();
                } else {
                    if (((CasillaNormal) c).getNumero() == 0) {
                        c.bloquearCasilla();
                        Tablero.getmTablero().desplegarAdyacentes(x,y);
                    } else {
                        c.bloquearCasilla();
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) { //Mira a ver si se ha clicado con el botón derecho del ratón
                boolean pulsada = c.estaPulsada();
                if (!pulsada){
                    c.marcarCasilla();
                    c.setCasillaClicada(true);
                }
                else {
                    c.desmarcarCasilla();
                }

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
}
