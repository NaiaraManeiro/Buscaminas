package packModelo;

public class Nivel1 extends Modo {

    public Nivel1(int pAltura, int pAnchura){
        super(7,10);
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
