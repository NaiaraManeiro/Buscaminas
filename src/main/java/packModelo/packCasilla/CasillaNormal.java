package packModelo.packCasilla;

import packModelo.Juego;

public class CasillaNormal extends Casilla{
    private int numero;
    public CasillaNormal(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
        numero = 0;
    }

    public int getNumero(){return numero;}
    public void incrementarNumero(){numero++;}

    public void desplegarAdyacentes(int x, int y){
        Juego.getmJuego().getTablero().desplegarAdyacentes(x,y);
    }

    public void mostrarCasilla(Casilla c){
        c.setEstado(new Clicada(c.getCoordenada()));
        Juego.getmJuego().getTablero().decrementarCasillasRestantes();
        Juego.getmJuego().activarUpdate(c.getCoordenada());
    }
}
