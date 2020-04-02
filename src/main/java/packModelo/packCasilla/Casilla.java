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
        if (this.estado instanceof NoClicada){
            setEstado(new Bandera(this.getCoordenada()));
            Juego.getmJuego().decrementarMinas();
        } else if (this.estado instanceof Bandera){
            setEstado(new NoClicada(this.getCoordenada()));
            Juego.getmJuego().incrementarMinas();
        }
    }
}
