package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.Juego;
import packVista.Buscaminas;

import javax.swing.*;
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

    //Todo lo relacionado con los niveles

    /**
     * Método encargado de obtener todos los niveles posibles para jugar
     *
     * @return Un JSONArray con el id de los niveles existentes en la db
     * */
    public JSONArray obtenerNiveles() {
        Integer resultado;
        JSONArray niveles = new JSONArray();
        try {
            ResultSet res = GestorBD.getGestorBD().ejecutarConsulta("SELECT idNivel FROM Nivel;");
            while (res.next()) {
                resultado = res.getInt("idNivel");
                niveles.put(resultado);
            }
        } catch (SQLException e) {
            System.out.println("No se han podido obtener los niveles del juego");
            e.printStackTrace();
        }
        GestorBD.getGestorBD().cerrarConexion();
        return niveles;
    }

    /**
     * Método encargado de obtener el número de filas de un nivel determinado.
     *@param pNivel es el nivel del que se desea obtener la información.
     * @return Devuelve el número de filas del nivel que se introduce como parámetro.
     * */
    public int obtenerfilasnivel(int pNivel){
        int filas = 0;
        if (pNivel==1 || pNivel ==2 || pNivel ==3){
            try {
                ResultSet rs = GestorBD.getGestorBD().ejecutarConsulta("SELECT numFilas FROM Nivel WHERE idNivel='" + pNivel + "';");
                rs.next();
                filas = rs.getInt("numFilas");
                GestorBD.getGestorBD().cerrarConexion();
            }catch (SQLException throwables) {
                System.out.println("No se ha podido obtener el numero de filas");
                JOptionPane.showMessageDialog(null, "No se ha podido obtener el número de filas del nivel " + pNivel);
            }
        }
        return filas;
    }

    /**
     * Método encargado de obtener el número de columnas de un nivel determinado.
     *@param pNivel es el nivel del que se desea obtener la información.
     * @return Devuelve el número de columnas del nivel que se introduce como parámetro.
     * */
    public int obtenercolumnasnivel(int pNivel){
        int columnas = 0;
        if (pNivel==1 || pNivel ==2 || pNivel ==3) {
            try {
                ResultSet rs = GestorBD.getGestorBD().ejecutarConsulta("SELECT numColumnas FROM Nivel WHERE idNivel='" + pNivel + "';");
                rs.next();
                columnas = rs.getInt("numColumnas");
                GestorBD.getGestorBD().cerrarConexion();
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(null, "No se ha podido obtener el número de columnas del nivel " + pNivel);
                throwables.printStackTrace();
            }
        }
        return columnas;
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

    //Todo lo relacionado con las puntuaciones

    public JSONArray obtenerPuntuaciones(int nivel){ return GestorPuntuaciones.getMiGestorPuntuaciones().obtenerPuntuaciones(nivel);}

    public void guardarPuntuacion(int puntuacion, String jugador, int nivel) {GestorPuntuaciones.getMiGestorPuntuaciones().guardarPuntuacion(puntuacion, jugador, nivel);}

    //Todo lo relacionado con los objetos a personalizar

    /**
     * Obtiene toda la información de los personalizables que hay en el sistema (iconos de usuario, iconos de tablero, sonidos...)
     * @return devuelve un JSONObject con la siguiente forma:
     * {
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
    public void ponerPersonalizables(String pPathSonidosWin, String pPathSonidosGameOver, String pPathIconosTablero){
        Juego.getmJuego().ponerPersonalizables(pPathSonidosWin, pPathSonidosGameOver, pPathIconosTablero);
    }
    public JSONObject getPersonalizables(){
        return Juego.getmJuego().getPersonalizables();
    }
}
