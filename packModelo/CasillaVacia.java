package packModelo;

public class CasillaVacia extends Casilla{

    public CasillaVacia(boolean pCasillaClicada, boolean pBanderita){
        super(false, false);
    }

    public void bloquearCasilla() {
        setCasillaClicada(true);
    }
}
