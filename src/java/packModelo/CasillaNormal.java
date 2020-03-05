package packModelo;

public class CasillaNormal extends Casilla{
    private int numero;
    public CasillaNormal(boolean pCasillaClicada, boolean pBanderita){
        super(false, false);
        numero = 0;
    }
    public void bloquearCasilla() {
        setCasillaClicada(true);
    }
    public int getNumero(){return numero;}
    public void incrementarNumero(){numero++;}
}
