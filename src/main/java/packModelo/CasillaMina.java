package packModelo;

public class CasillaMina extends Casilla {

    public CasillaMina(boolean pCasillaClicada, boolean pBanderita){
        super(false, false);
    }

    public void bloquearCasilla() {
        setCasillaClicada(true);
        // Hacer que se acabe la partida
        Juego.getmJuego().terminarPartida();
    }
}
