package packControlador;

import packModelo.Juego;
import packVista.Buscaminas;

public class GestorPartidas {

    private static GestorPartidas miGP;

    private GestorPartidas(){}

    public static GestorPartidas getMiGestorPartidas(){
        if (miGP == null) miGP = new GestorPartidas();
        return miGP;
    }

    public void jugar(int pFilas, int pColumnas, int pMinas, String minas){
        Juego.getmJuego().jugar(pFilas, pColumnas, pMinas, minas);
    }

    public void addObserver(Buscaminas b){
        Juego.getmJuego().deleteObservers();
        Juego.getmJuego().addObserver(b);
    }

    public void addObserverCrono(Buscaminas b){
        Juego.getmJuego().getCrono().deleteObservers();
        Juego.getmJuego().getCrono().addObserver(b);
    }

    public boolean haPerdido(){
        return Juego.getmJuego().haPerdido();
    }

    public boolean haGanado(){
        return Juego.getmJuego().haGanado();
    }

    public String getMinasRestantes(){
        return Juego.getmJuego().getnMinasRestantes();
    }

    public void setMinasRestantes(int minas){ Juego.getmJuego().setnMinasRestantes(minas); }

    public void incrementarMinas(){
        Juego.getmJuego().incrementarMinas();
    }

    public void resetCrono(){
        Juego.getmJuego().getCrono().reset();
    }

    public void iniciarCrono() { Juego.getmJuego().iniciarCrono(); }

    public void reiniciarVariables(){
        Juego.getmJuego().reiniciarVariables();
    }

    public void imprimirChivato(){
        Juego.getmJuego().getTablero().imprimirChivato();
    }
}
