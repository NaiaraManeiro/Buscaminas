package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestorPuntuaciones {

    private static GestorPuntuaciones miGPU;

    private GestorPuntuaciones(){}

    public static GestorPuntuaciones getMiGestorPuntuaciones(){
        if (miGPU == null) miGPU = new GestorPuntuaciones();
        return miGPU;
    }

    public JSONArray obtenerPuntuaciones(int nivel){
        ArrayList<JSONObject> l = new ArrayList<JSONObject>();
        JSONArray puntuaciones = new JSONArray();
        int puntuacion;
        String nombre;
        try {
            ResultSet rs = GestorBD.getGestorBD().ejecutarConsulta("SELECT nombreJugador, puntuacion FROM Ranking WHERE nivel='"+nivel+"' ORDER BY puntuacion ASC;");
            while(rs.next()) {
                puntuacion = rs.getInt(2);
                nombre = rs.getString(1);
                JSONObject punt = new JSONObject();
                punt.put("Nombre", nombre);
                punt.put("Puntuacion", puntuacion);
                l.add(punt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i = 0;

        while(i < l.size() && i<10) {
            puntuaciones.put(l.get(i));
            i++;
        }

        GestorBD.getGestorBD().cerrarConexion();

        return puntuaciones;
    }

    public void guardarPuntuacion(int puntuacion, String jugador, int nivel){
        GestorBD.getGestorBD().ejecutarCambio("INSERT INTO Ranking (nivel, nombreJugador, puntuacion) VALUES ("+nivel+",'"+jugador+"',"+puntuacion+");");
        GestorBD.getGestorBD().cerrarConexion();
    }
}
