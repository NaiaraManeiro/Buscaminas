package packControlador;

import packModelo.*;
import packVista.buscaminas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class cCasilla implements MouseListener {
    private Coordenada coordenada;

    public cCasilla(Coordenada pCoordenada){
        this.coordenada = pCoordenada;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = coordenada.getColumna();
        int y = coordenada.getFila();
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(x, y);

        if (!Juego.getmJuego().haPerdido() && !Juego.getmJuego().haGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) { //Mira a ver si se ha clicado con el botón izquierdo del ratón
                if (c instanceof CasillaMina) {
                    Juego.getmJuego().terminarPartida();
                } else {
                    if (((CasillaNormal) c).getNumero() == 0) {
                        c.bloquearCasilla();
                        Juego.getmJuego().getTablero().desplegarAdyacentes(x,y);
                    } else {
                        c.bloquearCasilla();
                        Juego.getmJuego().getTablero().decrementarCasillasRestantes();
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) { //Mira a ver si se ha clicado con el botón derecho del ratón
                boolean pulsada = c.estaPulsada();
                if (!pulsada){
                    c.marcarCasilla();
                    c.setCasillaClicada(true);
                    Juego.getmJuego().decrementarMinas();
                }
                else {
                    c.desmarcarCasilla();
                    Juego.getmJuego().incrementarMinas();
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
