package packModelo;

public abstract class Modo {
    private int altura;
    private  int anchura;

    public Modo(){}

    abstract public void definirAltura();

    abstract public void definirAnchura();

    abstract public void definirMinas();
}
