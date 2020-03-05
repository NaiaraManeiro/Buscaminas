package packModelo;

public class Nivel2 extends Modo {

    public Nivel2(){}

    public void definirAltura() {
        Tablero.getmTablero().setAltura(10);
    }

    public void definirAnchura() {
        Tablero.getmTablero().setAnchura(15);
    }

    public void definirMinas() {
        Tablero.getmTablero().setMinas(15*2);
    }
}
