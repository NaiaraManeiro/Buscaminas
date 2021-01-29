package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packVista.Buscaminas;

import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    /**
     * Obtiene toda la información de los personalizables que hay en el sistema (iconos de usuario, iconos de tablero, sonidos...)
     * @return devuelve un JSONObject con la siguiente forma:
     * {
     * IconosUsuario:[{id:string, nombre: string, path:string},...,{...}],
     * IconosTablero:[{id:string, nombre: string, path:string},...,{...}],
     * SonidoGameOver:[{id:string, nombre: string, path:string},...,{...}],
     * SonidoWin:[{id:string, nombre: string, path:string},...,{...}]
     * }
     */
    public JSONObject obtenerTodosPersonalizables(){
        JSONObject resultado = new JSONObject();
        resultado.put("SonidoWin", obtenerTodosPersonalizablesPorTabla("SonidoWin"));
        resultado.put("SonidoGameOver", obtenerTodosPersonalizablesPorTabla("SonidoGameOver"));
        resultado.put("IconosTablero", obtenerTodosPersonalizablesPorTabla("IconosTablero"));
        resultado.put("IconosUsuario", obtenerTodosPersonalizablesPorTabla("IconosUsuario"));
        return resultado;
    }

    /**
     * Es un método auxiliar que se usa para obtenerTodosPersonalizables(), para no repetir código.
     * @param tabla es la tabla de personalizables de la que se desea obtener la información.
     * @return devuelve un JSONArray con esta forma:
     * [{id:string, nombre: string, path:string},...,{...}]
     */
    private JSONArray obtenerTodosPersonalizablesPorTabla(String tabla) {
        JSONArray opciones = new JSONArray();
        try {
            ResultSet resconsulta = GestorBD.getGestorBD().ejecutarConsulta("SELECT * FROM "+ tabla);
            if (resconsulta != null) {
                int columnas = resconsulta.getMetaData().getColumnCount();
                int i = 1;
                while (resconsulta.next()) {
                    JSONObject informacion = new JSONObject();
                    while (i <= columnas) {
                        if (i == 1) informacion.put("id", resconsulta.getString(i));
                        else if (i == 2) informacion.put("nombre", resconsulta.getString(i));
                        else if (i == 3) informacion.put("path", resconsulta.getString(i));
                        i++;
                    }
                    opciones.put(informacion);
                    i = 1;
                }
                resconsulta.close();
            }
        }catch (SQLException e){e.printStackTrace(); System.out.println("No se han podido obtener los personalizables");}
        finally {
            GestorBD.getGestorBD().cerrarConexion();
        }
        return opciones;
    }
}
