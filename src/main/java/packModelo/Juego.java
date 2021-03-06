package packModelo;

import org.json.JSONObject;
import packModelo.packCasilla.*;

import java.util.Observable;

public class Juego extends Observable {
    private static Juego mJuego;
    private Tablero tablero;
    private Tablero tableroPrueba;
    private Cronometro crono;
    private int nMinasRestantes;
    private boolean derrota;
    private String pathSonidosWin;
    private String pathSonidosGameOver;
    private String pathIconosTablero;

    private Juego(){
        pathIconosTablero = "/pack_iconos_tablero/pack1";
        pathSonidosGameOver = "/sonidos_gameover/lose.wav";
        pathSonidosWin = "/sonidos_win/win.wav";
    }

    public static Juego getmJuego(){
        if (mJuego == null){
            mJuego = new Juego();
        }
        return mJuego;
    }

    public void jugar(int pFilas, int pColumnas, int pMinas, String minas){
        this.tablero = new Tablero(pFilas, pColumnas, pMinas, minas);
        derrota = false;
    }

    public void iniciarCrono(){
        if (this.crono == null || crono.estaParado()) this.crono = new Cronometro();
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
        JSONObject coordenada = new JSONObject();
        coordenada.put("fila", coord.getFila());
        coordenada.put("columna",coord.getColumna());
        notifyObservers(coordenada);
    }

    private void regenerarTablero(int pFilas, int pColumnas, int pMinas, String minasEspeciales){
        this.tablero = new Tablero(pFilas, pColumnas, pMinas, minasEspeciales);
        tablero.imprimirChivato();
    }

    private void asignarTablero(Tablero tablero){ this.tableroPrueba = tablero; }

    public void reiniciarVariables(){ this.tableroPrueba = null; }

    public void marcardesmarcarCasilla(Casilla c){
        Coordenada coord = c.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        if (c.getEstado() instanceof NoClicada){
            if (c instanceof CasillaMinaNormal) {
                ((CasillaMinaNormal) c).terminarPartida(c);
            } else if (c instanceof CasillaMinaReset) {
                ((CasillaMinaReset) c).reiniciarJuego(c);
            } else if (c instanceof CasillaMina50) {
                ((CasillaMina50) c).mostrarMinas(c);
            } else {
                if (((CasillaNormal) c).getNumero() == 0) {
                    ((CasillaNormal) c).desplegarAdyacentes(x, y);
                } else if (((CasillaNormal) c).getNumero() != 0) {
                    ((CasillaNormal) c).mostrarCasilla(c);
                }
            }
        }
    }

    public Casilla tableroNuevo(Casilla c, int pFilas, int pColumnas, int pMinas, String minasEspeciales){
        Coordenada coord = c.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        while ((c instanceof CasillaMinaNormal || c instanceof CasillaMinaReset || c instanceof CasillaMina50 || (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() != 0)) && this.getTableroPrueba() == null){
            this.regenerarTablero(pFilas, pColumnas, pMinas, minasEspeciales);
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
    public void ponerPersonalizables(String pPathSonidosWin, String pPathSonidosGameOver, String pPathIconosTablero){
        pathSonidosWin = pPathSonidosWin;
        pathSonidosGameOver = pPathSonidosGameOver;
        pathIconosTablero = pPathIconosTablero;
    }
    public JSONObject getPersonalizables(){
        JSONObject resultado = new JSONObject();
        resultado.put("pathSonidoWin",pathSonidosWin);
        resultado.put("pathSonidoGameOver",pathSonidosGameOver);
        resultado.put("pathIconosTablero",pathIconosTablero);
        return resultado;
    }
}