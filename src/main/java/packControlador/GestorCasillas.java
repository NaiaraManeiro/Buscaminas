package packControlador;

import packModelo.*;
import packModelo.packCasilla.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestorCasillas extends MouseAdapter {

    private static GestorCasillas miGC;

    private GestorCasillas(){}

    public static GestorCasillas getMiGestorCasillas(){
        if (miGC == null) miGC = new GestorCasillas();
        return miGC;
    }

    public void clicarCasilla(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        String coor[] = btn.getName().split(";");
        String xString = coor[0]; int x = Integer.parseInt(xString);
        String yString = coor[1]; int y = Integer.parseInt(yString);

        Casilla cP = Juego.getmJuego().getTablero().devolverCasilla(x, y);

        Casilla c = Juego.getmJuego().tableroNuevo(cP,Juego.getmJuego().getTablero().getFilas(),Juego.getmJuego().getTablero().getColumnas(), Juego.getmJuego().getTablero().getMinas());

        Juego.getmJuego().iniciarCrono();

        if (!Juego.getmJuego().haPerdido() && !Juego.getmJuego().haGanado()) {
            if (e.getButton() == MouseEvent.BUTTON1) { //Mira a ver si se ha clicado con el botón izquierdo del ratón
                Juego.getmJuego().marcardesmarcarCasilla(c);
            } else if (e.getButton() == MouseEvent.BUTTON3) { //Mira a ver si se ha clicado con el botón derecho del ratón
                c.marcarDesmarcarCasilla();
            }
        }
    }

    public String getTipoCasilla(int x, int y){
        Casilla cP = Juego.getmJuego().getTablero().devolverCasilla(x, y);
        String tipo = null;
        if (cP instanceof CasillaNormal) tipo = "CasillaNormal";
        else if(cP instanceof CasillaMinaNormal ) tipo = "CasillaMinaNormal";
        else if(cP instanceof CasillaMinaReset ) tipo = "CasillaMinaReset";
        else if(cP instanceof CasillaMina50 ) tipo = "CasillaMina50";
        else if(cP instanceof CasillaMina ) tipo = "CasillaMina";
        return tipo;
    }

    public String getEstadoCasilla(int x, int y){
        Estado estado = Juego.getmJuego().getTablero().devolverCasilla(x, y).getEstado();
        String tipo = null;
        if(estado instanceof Bandera) tipo = "Bandera";
        else if (estado instanceof NoClicada) tipo = "NoClicada";
        else if (estado instanceof Clicada) tipo = "Clicada";
        return tipo;
    }

    public int getNumero(int x, int y){
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(x,y);
        return ((CasillaNormal) c).getNumero();
    }

    public void cambiarEstado(int x, int y, String estado){
        Casilla c = Juego.getmJuego().getTablero().devolverCasilla(x,y);
        Coordenada coord = c.getCoordenada();
        if (estado.equals("Clicada")) c.setEstado(new Clicada(coord));
        else if (estado.equals("NoClicada")) c.setEstado(new NoClicada(coord));
    }
}
