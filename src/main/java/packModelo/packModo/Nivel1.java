package packModelo.packModo;

import packModelo.packModo.Modo;

public class Nivel1 extends Modo {

    public Nivel1() {}

    public void definirAltura() { tablero.setAltura(7); }

    public void definirAnchura() {
        tablero.setAnchura(10);
    }

    public void definirMinas() {
        tablero.setMinas(10);
    }
}
