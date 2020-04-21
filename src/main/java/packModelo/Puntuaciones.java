package packModelo;

import java.io.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Puntuaciones {
    private static Puntuaciones miPuntuaciones;
    private final Integer MAX_JUGADORES = 10;
    private TreeMap<Integer, String> nivel1;
    private TreeMap<Integer, String> nivel2;
    private TreeMap<Integer, String> nivel3;

    private Puntuaciones(){
        nivel1 = new TreeMap<>();
        nivel2 = new TreeMap<>();
        nivel3 = new TreeMap<>();
        try {
            cargarPuntuaciones();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Puntuaciones getMiPuntuaciones(){
        if(miPuntuaciones == null) miPuntuaciones = new Puntuaciones();
        return miPuntuaciones;
    }
    public void anadirPuntuacion(String nombre, Integer puntuacion, Integer nivel) {
        if (nivel == 1){
            if (nivel1.size() == MAX_JUGADORES) {
                if (nivel1.firstKey() >= puntuacion) {
                    nivel1.remove(nivel1.firstKey());
                    nivel1.put(puntuacion, nombre);
                }
            } else if (nivel1.size() < MAX_JUGADORES) nivel1.put(puntuacion, nombre);
        } else if(nivel == 2){
            if (nivel2.size() == MAX_JUGADORES) {
                if (nivel2.firstKey() >= puntuacion) {
                    nivel2.remove(nivel2.firstKey());
                    nivel2.put(puntuacion, nombre);
                }
            } else if (nivel2.size() < MAX_JUGADORES) nivel2.put(puntuacion, nombre);
        } else if(nivel == 3){
            if (nivel3.size() == MAX_JUGADORES) {
                if (nivel3.firstKey() >= puntuacion) {
                    nivel3.remove(nivel3.firstKey());
                    nivel3.put(puntuacion, nombre);
                }
            } else if (nivel3.size() < MAX_JUGADORES) nivel3.put(puntuacion, nombre);
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
    public void guardarPuntuaciones(){
        File f;
        BufferedWriter bw;
        f = new File("puntuaciones.txt");
        try {
            bw = new BufferedWriter(new FileWriter(f,false));
            if(!nivel1.isEmpty())
                for (Entry<Integer, String> obj : nivel1.entrySet()) bw.write(obj.getKey() + "--->" + obj.getValue() + "--->1\n");
            if(!nivel2.isEmpty())
                for (Entry<Integer,String> obj : nivel2.entrySet()) bw.write( obj.getKey()+"--->"+obj.getValue()+"--->2\n");
            if(!nivel3.isEmpty())
                for (Entry<Integer,String> obj : nivel3.entrySet()) bw.write( obj.getKey()+"--->"+obj.getValue()+"--->3\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                usu = linea.split("--->");
                if (Integer.parseInt(usu[2]) == 1) nivel1.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 2) nivel2.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 3) nivel3.put(Integer.parseInt(usu[0]), usu[1]);
            }
        }
    }
       /*public static void main(String[]args) throws IOException {
           Puntuaciones.getMiPuntuaciones().cargarPuntuaciones();
           TreeMap<Integer,String> as = Puntuaciones.getMiPuntuaciones().getLista(1);
           for (Object a: as.keySet()){
               System.out.println("seg: " +a +" nombre: "+ as.get(a));
           }
           Puntuaciones.getMiPuntuaciones().anadirPuntuacion("aitor",90,1);
           Puntuaciones.getMiPuntuaciones().anadirPuntuacion("naiara",910,2);
           Puntuaciones.getMiPuntuaciones().anadirPuntuacion("leire",920,3);
           Puntuaciones.getMiPuntuaciones().guardarPuntuaciones();
       }*/
}