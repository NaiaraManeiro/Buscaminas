package packModelo.packCasilla;

import packModelo.Juego;

public class CasillaMina extends Casilla {

    public CasillaMina(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
    }

    public void terminarPartida(Casilla c){
        c.setEstado(new Clicada(c.getCoordenada()));
        Juego.getmJuego().terminarPartida(c.getCoordenada());
    }
}
