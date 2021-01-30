package packModelo.packCasilla;

import packModelo.Juego;

public class CasillaMina50 extends CasillaMina {

    public CasillaMina50(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
    }

    public void mostrarMinas(Casilla c) {
        Juego.getmJuego().activarUpdate(c.getCoordenada());
    }

}
