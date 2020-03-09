package packControlador;

import packModelo.*;
import packVista.buscaminas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class cCasilla implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        String coor = btn.getName();
        char xString = coor.charAt(0); int x = Integer.parseInt("" + xString);
        char yString = coor.charAt(1); int y = Integer.parseInt("" + yString);
        Coordenada coordenada = new Coordenada(x, y);

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
                    Juego.getmJuego().marcarCasilla(coordenada);
                    Juego.getmJuego().decrementarMinas();
                }
                else {
                    Juego.getmJuego().desmarcarCasilla(coordenada);
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
