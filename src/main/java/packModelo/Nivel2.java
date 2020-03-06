package packModelo;

public class Nivel2 extends Modo {

    public Nivel2(){}

    public void definirAltura() {
        tablero.setAltura(10);
    }

    public void definirAnchura() {
        tablero.setAnchura(15);
    }

    public void definirMinas() {
        tablero.setMinas(15*2);
    }
}
