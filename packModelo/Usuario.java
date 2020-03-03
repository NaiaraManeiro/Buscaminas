package packModelo;

public class Usuario {
    private String nombre;
    private Modo nivel;

    public Usuario(String pNombre, int nivel){
        this.nombre = pNombre;
        definirNivel(nivel);
    }

    public void definirNivel(int pNivel) {
        switch (pNivel) {
            case 1:
                this.nivel = new Nivel1();
                break;
            case 2:
                this.nivel = new Nivel2();
                break;
            case 3:
                this.nivel = new Nivel3();
                break;
            default:
                break;
        }
    }

    public Modo getNivel(){
        return this.nivel;
    }
}
