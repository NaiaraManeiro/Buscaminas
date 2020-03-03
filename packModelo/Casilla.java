package packModelo;

public abstract class Casilla {
    private boolean casillaClicada;
    private boolean banderita;

    public Casilla(boolean pCasillaClicada, boolean pBanderita){
        this.casillaClicada = pCasillaClicada;
        this.banderita = pBanderita;
    }

    public void marcarCasilla() {
        this.banderita = true;
    }

    abstract public void bloquearCasilla();

    public void setCasillaClicada(boolean cambiar){
        this.casillaClicada = cambiar;
    }
}
