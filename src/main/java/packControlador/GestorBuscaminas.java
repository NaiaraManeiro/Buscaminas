package packControlador;

import packVista.Buscaminas;

import java.awt.event.MouseEvent;

public class GestorBuscaminas {

    private static GestorBuscaminas miGB;

    private GestorBuscaminas() {}

    public static GestorBuscaminas getMiGB() {
        if (miGB == null) miGB = new GestorBuscaminas();
        return miGB;
    }

    //Todo lo relacionado con las casillas

    public void clicarCasilla(MouseEvent e) {
        GestorCasillas.getMiGestorCasillas().clicarCasilla(e);
    }

    public String getTipoCasilla(int x, int y){
        return GestorCasillas.getMiGestorCasillas().getTipoCasilla(x,y);
    }

    public String getEstadoCasilla(int x, int y){
        return GestorCasillas.getMiGestorCasillas().getEstadoCasilla(x,y);
    }

    public int getNumero(int x, int y){
        return GestorCasillas.getMiGestorCasillas().getNumero(x,y);
    }

    public void cambiarEstado(int x, int y, String estado){
        GestorCasillas.getMiGestorCasillas().cambiarEstado(x,y,estado);
    }

    //Todo lo relacionado con la partida

    public void jugar(int pFilas, int pColumnas, int pMinas, String minas){
        GestorPartidas.getMiGestorPartidas().jugar(pFilas, pColumnas, pMinas, minas);
    }

    public void addObserver(Buscaminas b){
        GestorPartidas.getMiGestorPartidas().addObserver(b);
    }

    public void addObserverCrono(Buscaminas b){
        GestorPartidas.getMiGestorPartidas().addObserverCrono(b);
    }

    public boolean haPerdido(){
        return GestorPartidas.getMiGestorPartidas().haPerdido();
    }

    public boolean haGanado(){
        return GestorPartidas.getMiGestorPartidas().haGanado();
    }

    public String getMinasRestantes(){
        return GestorPartidas.getMiGestorPartidas().getMinasRestantes();
    }

    public void setMinasRestantes(int minas){ GestorPartidas.getMiGestorPartidas().setMinasRestantes(minas);
    }

    public void incrementarMinas(){
        GestorPartidas.getMiGestorPartidas().incrementarMinas();
    }

    public void resetCrono(){
        GestorPartidas.getMiGestorPartidas().resetCrono();
    }

    public void iniciarCrono() {
        GestorPartidas.getMiGestorPartidas().iniciarCrono();
    }

    public void reiniciarVariables(){
        GestorPartidas.getMiGestorPartidas().reiniciarVariables();
    }

    public void imprimirChivato(){
        GestorPartidas.getMiGestorPartidas().imprimirChivato();
    }

}
