package packModelo;

public class Nivel2 extends Modo {

    public Nivel2(int pAltura, int pAnchura){
        super(10,15);
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
