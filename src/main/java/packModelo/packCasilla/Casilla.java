package packModelo.packCasilla;

import packModelo.Juego;

public abstract class Casilla {
    private Estado estado;
    private Coordenada coordenada;

    public Casilla(Estado pEstado, Coordenada pCoordenada){
        this.estado = pEstado;
        this.coordenada = pCoordenada;
    }

    public Estado getEstado(){ return this.estado; }
    public void setEstado(Estado estado){ this.estado = estado; }

    public Coordenada getCoordenada() { return this.coordenada; }

    public void marcarDesmarcarCasilla(){
        estado.marcarDesmarcarCasilla(this);
        Juego.getmJuego().activarUpdate(this.coordenada);
    }
}
