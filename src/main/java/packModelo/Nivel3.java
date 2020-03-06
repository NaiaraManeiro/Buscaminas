package packModelo;

public class Nivel3 extends Modo{

    public Nivel3(){}

    public void definirAltura() {
        tablero.setAltura(12);
    }

    public void definirAnchura() {
        tablero.setAnchura(25);
    }

    public void definirMinas() {
        tablero.setMinas(25*3);
    }
}
