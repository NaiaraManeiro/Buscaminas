package packModelo.packCasilla;

import packModelo.Juego;

public class CasillaMinaNormal extends CasillaMina {

    public CasillaMinaNormal(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
    }

    public void terminarPartida(Casilla c){
        c.setEstado(new Clicada(c.getCoordenada()));
        Juego.getmJuego().terminarPartida(c.getCoordenada());
    }
}
