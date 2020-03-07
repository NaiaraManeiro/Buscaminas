package packModelo;

public class CasillaNormal extends Casilla{
    private int numero;
    public CasillaNormal(boolean pCasillaClicada, boolean pBanderita, Coordenada pCoordenada){
        super(false, false, null);
        numero = 0;
    }
    public void bloquearCasilla() {
        setCasillaClicada(true);
    }
    public int getNumero(){return numero;}
    public void incrementarNumero(){numero++;}
}
