package packModelo;

public class CasillaNumero extends Casilla {

    public CasillaNumero(boolean pCasillaClicada, boolean pBanderita){
        super(false, false);
    }

    public void bloquearCasilla() {
        setCasillaClicada(true);
    }
}
