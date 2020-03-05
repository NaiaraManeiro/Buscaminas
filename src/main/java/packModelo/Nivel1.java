package packModelo;

public class Nivel1 extends Modo {

    public Nivel1() {}

    public void definirAltura() {
        Tablero.getmTablero().setAltura(7);
    }

    public void definirAnchura() {
        Tablero.getmTablero().setAnchura(10);
    }

    public void definirMinas() {
        Tablero.getmTablero().setMinas(10);
    }
}
