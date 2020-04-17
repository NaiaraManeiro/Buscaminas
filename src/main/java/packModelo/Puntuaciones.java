package packModelo;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Puntuaciones {
    private static Puntuaciones miPuntuaciones;
    private TreeMap<Integer, String> nivel1;
    private TreeMap<Integer, String> nivel2;
    private TreeMap<Integer, String> nivel3;

    private Puntuaciones(){
        nivel1 = new TreeMap<>();
        nivel2 = new TreeMap<>();
        nivel3 = new TreeMap<>();
    }
    public static Puntuaciones getMiPuntuaciones() {
        if(miPuntuaciones == null) miPuntuaciones = new Puntuaciones();
        return miPuntuaciones;
    }
    public void anadirPuntuacion(String nombre, Integer puntuacion, Integer nivel) {
        if (nivel == 1){
            if (nivel1.size() == 10) {
                if (nivel1.lowerKey(puntuacion) >= puntuacion) {
                    nivel1.replace(puntuacion, nombre);
                }
            } else if (nivel1.size() < 10) {
                nivel1.put(puntuacion, nombre);
            }
        } else if(nivel == 2){
            if (nivel2.size() == 10) {
                if (nivel2.lowerKey(puntuacion) >= puntuacion) {
                    nivel2.replace(puntuacion, nombre);
                }
            } else if (nivel2.size() < 10) {
                nivel2.put(puntuacion, nombre);
            }
        } else if(nivel == 3){
            if (nivel3.size() == 10) {
                if (nivel3.lowerKey(puntuacion) >= puntuacion) {
                    nivel3.replace(puntuacion, nombre);
                }
            } else if (nivel3.size() < 10) {
                nivel3.put(puntuacion, nombre);
            }
        }
    }
    public TreeMap<Integer,String> getLista(Integer nivel){
        TreeMap <Integer,String> lista = null;
        if (nivel == 1){
            lista = nivel1;
        }else if (nivel == 2){
            lista = nivel2;
        }else if (nivel == 3){
            lista = nivel3;
        }
        return lista;
    }
    public void guardarPuntuaciones() throws IOException {
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter pw = null;

        f = new File("puntuaciones.txt");
        w = new FileWriter(f);
        bw = new BufferedWriter(w);
        pw = new PrintWriter(bw);

        // guardamos nivel 1
        if(!nivel2.isEmpty())
        for (Map.Entry<Integer, String> obj : nivel1.entrySet()) {
            pw.write(obj.getKey() + "--->" + obj.getValue() + "--->" + "1");
            pw.println();
        }

        // guardamos nivel 2
        if(!nivel2.isEmpty())
        for (Map.Entry<Integer,String> obj : nivel2.entrySet()) {
            pw.write( obj.getKey()+"--->"+obj.getValue()+"--->"+"2");
            pw.println();
        }

        // guardamos nivel 3
        if(!nivel3.isEmpty())
        for (Map.Entry<Integer,String> obj : nivel3.entrySet()) {
            pw.write( obj.getKey()+"--->"+obj.getValue()+"--->"+"3");
            pw.println();
        }
    }
    public void cargarPuntuaciones() throws IOException {
        File archivo = new File ("puntuaciones.txt");
        if (archivo.exists()) {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            String[] usu;
            while ((linea = br.readLine()) != null) {
                usu = linea.split("\\s+--->\\s+");
                if (Integer.parseInt(usu[2]) == 1) nivel1.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 2) nivel2.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 3) nivel3.put(Integer.parseInt(usu[0]), usu[1]);
            }
        }
    }
}
