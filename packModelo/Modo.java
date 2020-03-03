package packModelo;

public abstract class Modo {
    private int altura;
    private  int anchura;

    public Modo(int pAltura, int pAnchura){
        this.altura = pAltura;
        this.anchura = pAnchura;
    }

    abstract public void definirAltura();

    abstract public void definirAnchura();

    abstract public void definirMinas();

    public int getAltura(){
        return altura;
    }

    public int getAnchura(){
        return anchura;
    }
}
