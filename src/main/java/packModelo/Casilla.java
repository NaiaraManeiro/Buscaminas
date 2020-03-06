package packModelo;

public abstract class Casilla {
    private boolean casillaClicada;
    private boolean banderita;
    private Coordenada coordenada;

    public Casilla(boolean pCasillaClicada, boolean pBanderita){
        this.casillaClicada = pCasillaClicada;
        this.banderita = pBanderita;
    }

    public void marcarCasilla() {
            this.banderita = true;
    }

    public void desmarcarCasilla(){
        this.banderita = false;
    }

    abstract public void bloquearCasilla();

    public void setCasillaClicada(boolean cambiar){
        this.casillaClicada = cambiar;
    }

    public boolean getCasillaClicada(){
        return this.casillaClicada;
    }

    public boolean estaPulsada(){return casillaClicada;}

    public Coordenada getCoordenada() { return this.coordenada; }
}
