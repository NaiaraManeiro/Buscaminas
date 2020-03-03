package packModelo;

public class Nivel3 extends Modo{

    public Nivel3(){}

    public void definirAltura() {
        Tablero.getmTablero().setAltura(12);
    }

    public void definirAnchura() {
        Tablero.getmTablero().setAnchura(25);
    }

    public void definirMinas() {
        Tablero.getmTablero().setMinas(25*3);
    }
}
