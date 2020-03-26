package packModelo;

import packModelo.packModo.Modo;

public class Usuario {
    private String nombre;
    private Modo nivel;

    public Usuario(String pNombre, int pNivel){
        this.nombre = pNombre;
        nivel = new Modo(pNivel);
    }

    public Modo getNivel(){
        return this.nivel;
    }
}
