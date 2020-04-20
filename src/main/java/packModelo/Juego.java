package packModelo;

import packModelo.packCasilla.Coordenada;
import packModelo.packModo.Modo;

import java.io.IOException;
import java.util.Observable;
import java.util.TreeMap;

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
    public void iniciarCrono(){
        if (this.crono == null) this.crono = new Cronometro();
    }
    public Modo getModo(){return nivel;}
    public void setModo(Modo pModo){
        this.nivel = pModo;
    }

    public void crearUsuario(String pNombre, int pNivel) {
        this.usuario = new Usuario(pNombre, pNivel);
    }

    public Tablero getTablero() { return tablero; }

    public Cronometro getCrono() { return crono; }

    public boolean getDerrota() { return derrota;}

    public void terminarPartida(Coordenada coord){
        derrota = true;
        activarUpdate(coord);
    }
    public boolean haPerdido(){ return derrota; }
    public boolean haGanado() { return this.tablero.getMinas() == this.tablero.getnCasillasRestantes(); }

    public String getnMinasRestantes() { return nMinasRestantes+""; }
    public void setnMinasRestantes(int minas){ nMinasRestantes = minas;}

    public void incrementarMinas(){nMinasRestantes++;}
    public void decrementarMinas(){nMinasRestantes--;}

    public void activarUpdate(Coordenada coord){
        setChanged();
        notifyObservers(coord);
    }
    public boolean cronoInicializado(){return crono == null;}
    /*public TreeMap<Integer,String> mostrarPuntuacion() throws IOException {
        Usuario u = new Usuario(usuario.getNombre(),usuario.getNivel().getNumero());
        u.setMinutos(crono.getMinutos());
        u.setSegundos(crono.getSegundos());
        return Puntuaciones.getMiPuntuaciones().imprimir(usuario);
    }*/

    public Usuario getUsuario(){return usuario;}
    public int getPuntuacion(){
        return crono.getMinutos()*60+crono.getSegundos();
    }
}
