package packModelo;

public class Tablero {

    private static Tablero mTablero;
    private packModelo.Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;


    public static Tablero getmTablero(){
        if(mTablero == null) mTablero = new Tablero();
        return mTablero;
    }
    public Casilla devolverCasilla(int x, int y) {
        return matriz[x][y];
    }
    public void marcarDesmarcarCasilla(int x, int y){
        matriz[x][y].marcarCasilla();
    }
    private void ponerMinas(){

    }
    private void ponerNumeros(){

    }
    public void setAltura(int pAltura){
        this.altura = pAltura;
    }

    public void setAnchura(int pAnchura){
        this.anchura = pAnchura;
    }

    public void setMinas(int pMinas){ this.minas = pMinas; }

    public int getFilas(){ return altura; }

    public int getColumnas() { return anchura; }

    public void setCasillas(Casilla[][] casillas) { this.matriz = casillas; }
}