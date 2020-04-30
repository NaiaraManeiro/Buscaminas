package packModelo;

import packModelo.packCasilla.*;
import packModelo.packModo.Modo;

import java.util.Observable;

public class Juego extends Observable {
    private static Juego mJuego;
    private Tablero tablero;
    private Tablero tableroPrueba;
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
        if (this.crono == null || crono.estaParado()) this.crono = new Cronometro();
    }
    public Modo getModo(){return nivel;}
    public void setModo(Modo pModo){
        this.nivel = pModo;
    }

    public void crearUsuario(String pNombre, int pNivel) {
        this.usuario = new Usuario(pNombre, pNivel);
    }

    public Tablero getTablero() { return tablero; }
    private Tablero getTableroPrueba() { return tableroPrueba; }

    public Cronometro getCrono() { return crono; }

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

    public int getPuntuacion(){
        return crono.getMinutos()*60+crono.getSegundos();
    }

    private void regenerarTablero(){
        this.tablero = this.nivel.generarTablero();
        tablero.imprimirChivato();
    }

    private void asignarTablero(Tablero tablero){ this.tableroPrueba = tablero; }

    public void reiniciarVariables(){ this.tableroPrueba = null; }

    public void guardarPartida(){
        Puntuaciones.getMiPuntuaciones().anadirPuntuacion(usuario.getNombre(), getPuntuacion(), usuario.getNivel().getNumero());
        Puntuaciones.getMiPuntuaciones().guardarPuntuaciones();
    }

    public void marcardesmarcarCasilla(Casilla c){
        Coordenada coord = c.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        if (c.getEstado() instanceof NoClicada){
            if (c instanceof CasillaMina) {
                ((CasillaMina) c).terminarPartida(c);
            } else {
                if (((CasillaNormal) c).getNumero() == 0) {
                    ((CasillaNormal) c).desplegarAdyacentes(x, y);
                } else if (((CasillaNormal) c).getNumero() != 0) {
                    ((CasillaNormal) c).mostrarCasilla(c);
                }
            }
        }
    }

    public Casilla tableroNuevo(Casilla c){
        Coordenada coord = c.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        while ((c instanceof CasillaMina || (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() != 0)) && this.getTableroPrueba() == null){
            this.regenerarTablero();
            Casilla cNueva = this.getTablero().devolverCasilla(x, y);
            if (cNueva instanceof CasillaNormal && ((CasillaNormal) cNueva).getNumero() == 0){
                this.asignarTablero(this.getTablero());
                c = cNueva;
            }
        }

        if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() == 0){
            this.asignarTablero(this.getTablero());
        }
        return c;
    }
}
