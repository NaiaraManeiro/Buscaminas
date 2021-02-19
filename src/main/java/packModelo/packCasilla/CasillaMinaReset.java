package packModelo.packCasilla;

import packModelo.Juego;

public class CasillaMinaReset extends CasillaMina {

    public CasillaMinaReset(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
    }

    public void reiniciarJuego(Casilla c) {
        Juego.getmJuego().activarUpdate(c.getCoordenada());
    }

}
