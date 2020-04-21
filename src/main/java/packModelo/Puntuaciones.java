package packModelo;

import java.io.*;
import java.util.Map;
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
        try {
            cargarPuntuaciones();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Puntuaciones getMiPuntuaciones() throws IOException {
        if(miPuntuaciones == null) miPuntuaciones = new Puntuaciones();
        return miPuntuaciones;
    }



    public void anadirPuntuacion(String nombre, Integer puntuacion, Integer nivel) {
        if (nivel == 1){
            if (nivel1.size() == 10) {
                if (nivel1.firstKey() >= puntuacion) {
                    nivel1.remove(nivel1.firstKey());
                    nivel1.put(puntuacion, nombre);
                }
            } else if (nivel1.size() < 10) {
                nivel1.put(puntuacion, nombre);
            }
        } else if(nivel == 2){
            if (nivel2.size() == 10) {
                if (nivel2.firstKey() >= puntuacion) {
                    nivel2.remove(nivel2.firstKey());
                    nivel2.put(puntuacion, nombre);
                }
            } else if (nivel2.size() < 10) {
                nivel2.put(puntuacion, nombre);
            }
        } else if(nivel == 3){
            if (nivel3.size() == 10) {
                if (nivel3.firstKey() >= puntuacion) {
                    nivel3.remove(nivel3.firstKey());
                    nivel3.put(puntuacion, nombre);
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
        PrintWriter pw;

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
        File archivo = new File ("/Users/aitorjavierperez/puntuaciones.txt");
        if (archivo.exists()) {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            String[] usu;
            while ((linea = br.readLine()) != null) {
                usu = linea.split("--->");
                /*for(int i = 0; i<usu.length; i++){
                    System.out.println(i);
                    System.out.println(usu[0]);
                    System.out.println(usu[1]);
                }*/
                if (Integer.parseInt(usu[2]) == 1) nivel1.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 2) nivel2.put(Integer.parseInt(usu[0]), usu[1]);
                if (Integer.parseInt(usu[2]) == 3) nivel3.put(Integer.parseInt(usu[0]), usu[1]);
            }
        }
    }

    /*Set<Integer> set1 = nivel1.keySet();
    Set<Integer> set2 = nivel2.keySet();
    Set<Integer> set3 = nivel3.keySet();

    Iterator it1 = set1.iterator();
    Iterator it2 = set2.iterator();
    Iterator it3 = set3.iterator();


    private Iterator<Integer> getIterator1() {
        return set1.iterator();
    }

    private Iterator<Integer> getIterator2() {
        return miPuntuaciones.set2.iterator();
    }

    private Iterator<Integer> getIterator3() {
        return miPuntuaciones.set3.iterator();
    }*/

    /*public TreeMap<Integer,String> imprimir(Usuario usuario) {
        Puntuaciones.getMiPuntuaciones().anadirPuntuacion(usuario.getNombre(),usuario.getPuntuacionInt(),usuario.getNivel().getNumero());
        int niv = usuario.getNivel().getNumero();
        TreeMap<Integer,String> listaPuntuaciones = new TreeMap<Integer, String>();
        if(niv==1){
            for (Map.Entry<Integer, String> obj : nivel1.entrySet()) {
                listaPuntuaciones.put(obj.getKey(),obj.getValue());
            }
        }
        else if(niv==2){
            for (Map.Entry<Integer, String> obj : nivel2.entrySet()) {
                listaPuntuaciones.put(obj.getKey(),obj.getValue());
            }
        }
        else if(niv==3){
            for (Map.Entry<Integer, String> obj : nivel3.entrySet()) {
                listaPuntuaciones.put(obj.getKey(),obj.getValue());
            }
        }

        /*if (niv==1) {itr = miPuntuaciones.getIterator1();}
        else if (niv==2) {itr = miPuntuaciones.getIterator2();}
        else if (niv==3) {itr = miPuntuaciones.getIterator3();}

        Integer unJugador = null;
        int ind = 1;
        TreeMap<Integer,String> listaPuntuaciones = new TreeMap<Integer, String>();
        while ( itr.hasNext() ) {
            unJugador = itr.next();
            listaPuntuaciones.put(unJugador.getPuntuacionInt(),unJugador.getNombre());
            ind = ind + 1;
        }

        try {miPuntuaciones.guardarPuntuaciones();}
        catch (IOException e) {e.printStackTrace();}*/

       /* return listaPuntuaciones;
    }*/
       /*public static void main(String[]args) throws IOException {
           Puntuaciones.getMiPuntuaciones().cargarPuntuaciones();
           TreeMap<Integer,String> as = Puntuaciones.getMiPuntuaciones().getLista(1);
           for (Object a: as.keySet()){
               System.out.println("seg: " +a +" nombre: "+ as.get(a));
           }
       }*/
}
