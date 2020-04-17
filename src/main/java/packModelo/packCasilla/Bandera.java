package packModelo.packCasilla;

import packModelo.Juego;

public class Bandera extends Estado {

    public Bandera(Coordenada coordenada){
        super(coordenada);
    }

    public void marcarDesmarcarCasilla(Casilla casilla){
        casilla.setEstado(new NoClicada(casilla.getCoordenada()));
        Juego.getmJuego().incrementarMinas();
    }
}
