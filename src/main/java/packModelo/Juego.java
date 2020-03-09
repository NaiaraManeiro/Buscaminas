package packModelo;

import java.util.Observable;

public class Juego extends Observable {
    private static Juego mJuego;
    private Tablero tablero;
    private Modo nivel;
    private Usuario usuario;
    private Cronometro crono;
    private int nMinasRestantes;
    private boolean derrota;

    private Juego(){}

    public static Juego getmJuego(){
        if (mJuego == null){
            mJuego = new Juego();
        }
        return mJuego;
    }

    public void jugar(){
        setModo(this.usuario.getNivel());
        this.tablero = this.nivel.generarTablero();
        derrota = false;
    }

    public void setModo(Modo pModo){
        this.nivel = pModo;
    }

    public void crearUsuario(String pNombre, int pNivel) {
        this.usuario = new Usuario(pNombre, pNivel);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Cronometro getCrono() {
        return crono;
    }

    public void terminarPartida(){ derrota = true; }

    public boolean haPerdido(){
        return derrota;
    }

    public boolean haGanado() { return this.tablero.getMinas() == this.tablero.getnCasillasRestantes(); }

    public String getnMinasRestantes() { return nMinasRestantes+""; }

    public void incrementarMinas(){nMinasRestantes++;}

    public void decrementarMinas(){nMinasRestantes--;}
}
