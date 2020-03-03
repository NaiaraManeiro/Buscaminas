package packModelo;

public class Nivel3 extends Modo{

    public Nivel3(int pAltura, int pAnchura){
        super(12,25);
    }

    public void definirAltura() {
        Tablero.getmTablero().setAltura(getAltura());
    }

    public void definirAnchura() {
        Tablero.getmTablero().setAnchura(getAnchura());
    }

    public void definirMinas() {
        int altura = getAltura();
        int anchura = getAnchura();
        int minas = altura * anchura;
        Tablero.getmTablero().setMinas(minas);
    }
}
