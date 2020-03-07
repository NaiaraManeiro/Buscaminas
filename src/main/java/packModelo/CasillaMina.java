package packModelo;

public class CasillaMina extends Casilla {

    public CasillaMina(boolean pCasillaClicada, boolean pBanderita, Coordenada pCoordenada){
        super(false, false, null);
    }

    public void bloquearCasilla() {
        setCasillaClicada(true);
        // Hacer que se acabe la partida
        Juego.getmJuego().terminarPartida();
    }
}
