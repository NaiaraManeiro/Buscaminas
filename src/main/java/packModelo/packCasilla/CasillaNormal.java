package packModelo.packCasilla;

public class CasillaNormal extends Casilla{
    private int numero;
    public CasillaNormal(Estado pEstado, Coordenada pCoordenada){
        super(pEstado, pCoordenada);
        numero = 0;
    }

    public int getNumero(){return numero;}
    public void incrementarNumero(){numero++;}
}
