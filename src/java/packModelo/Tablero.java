package packModelo;

public class Tablero {

    private static Tablero mTablero;
    private Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;

    private Tablero(){}

    public Tablero generarTablero(){
        Tablero tablero = null; //Lo he puesto así para que no salga error

        return tablero;
    }

    public void setAltura(int pAltura){
        this.altura = pAltura;
    }

    public void setAnchura(int pAnchura){
        this.anchura = pAnchura;
    }

    public void setMinas(int pMinas){
        this.minas = pMinas;
    }

    public static Tablero getmTablero(){
        return mTablero;
    }

    public int getFilas() {
        return altura;
    }

    public int getColumnas(){
        return anchura;
    }

}
