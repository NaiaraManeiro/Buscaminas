package packControlador;

import packModelo.*;
import packModelo.packCasilla.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cCasilla extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        String coor[] = btn.getName().split(";");
        String xString = coor[0]; int x = Integer.parseInt(xString);
        String yString = coor[1]; int y = Integer.parseInt(yString);

        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(x, y);

        if (!Juego.getmJuego().haPerdido() && !Juego.getmJuego().haGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) { //Mira a ver si se ha clicado con el botón izquierdo del ratón
                if (c.getEstado() instanceof NoClicada){
                    if (c instanceof CasillaMina) {
                        ((CasillaMina) c).terminarPartida(c);
                    } else {
                        if (((CasillaNormal) c).getNumero() == 0) {
                            ((CasillaNormal) c).desplegarAdyacentes(x, y);
                        } else if (((CasillaNormal) c).getNumero() != 0) {
                            ((CasillaNormal) c).mostrarCasilla(c);
                        }
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) { //Mira a ver si se ha clicado con el botón derecho del ratón
                c.marcarDesmarcarCasilla();
            }
        }
    }
}
