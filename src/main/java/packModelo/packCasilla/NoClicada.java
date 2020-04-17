package packModelo.packCasilla;

import packModelo.Juego;

public class NoClicada extends Estado {

    public NoClicada(Coordenada coordenada){
        super(coordenada);
    }

    public void marcarDesmarcarCasilla(Casilla casilla){
        casilla.setEstado(new Bandera(casilla.getCoordenada()));
        Juego.getmJuego().decrementarMinas();
    }
}
