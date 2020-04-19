package packModelo;

import packModelo.packModo.Modo;

public class Usuario {
    private String nombre;
    private Modo nivel;
    private int minutos;
    private int segundos;

    public Usuario(String pNombre, int pNivel){
        this.nombre = pNombre;
        nivel = new Modo(pNivel);
        this.minutos = 0;
        this.segundos =0;
    }

    public Usuario(String pNombre, int pNivel, int minutos, int segundos) {
        super();
        this.nombre= pNombre;
        nivel = new Modo(pNivel);
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public Modo getNivel(){
        return this.nivel;
    }

    public String getNombre(){ return this.nombre;}
    public void setNombre(String pNombre) { this.nombre = pNombre; }

    public int getMinutos() { return minutos; }
    public void setMinutos(int minutos) { this.minutos = minutos; }

    public int getSegundos() { return segundos; }
    public void setSegundos(int segundos) { this.segundos = segundos; }

    public String getPuntuacion() {
        return minutos+":"+segundos;
    }

    public int getPuntuacionInt() {
        return (minutos*60) + segundos;
    }

}
