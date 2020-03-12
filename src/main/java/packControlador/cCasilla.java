package packControlador;

import packModelo.*;
import packModelo.packCasilla.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class cCasilla implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        String coor[] = btn.getName().split(";");
        String xString = coor[0]; int x = Integer.parseInt(xString);
        String yString = coor[1]; int y = Integer.parseInt(yString);
        Coordenada coordenada = new Coordenada(x, y);

        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(x, y);

        if (!Juego.getmJuego().haPerdido() && !Juego.getmJuego().haGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) { //Mira a ver si se ha clicado con el botón izquierdo del ratón
                if (c.getEstado() instanceof NoClicada){
                    if (c instanceof CasillaMina) {
                        c.setEstado(new Clicada(coordenada));
                        Juego.getmJuego().terminarPartida(coordenada);
                    } else {
                        if (((CasillaNormal) c).getNumero() == 0) {
                            Juego.getmJuego().getTablero().desplegarAdyacentes(x,y);
                        } else if (((CasillaNormal) c).getNumero() != 0) {
                            c.setEstado(new Clicada(coordenada));
                            Juego.getmJuego().getTablero().decrementarCasillasRestantes();
                            Juego.getmJuego().activarUpdate(coordenada);
                        }
                    }
                }

            } else if (e.getButton() == MouseEvent.BUTTON3) { //Mira a ver si se ha clicado con el botón derecho del ratón
                Juego.getmJuego().marcarDesmarcarCasilla(coordenada);

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
