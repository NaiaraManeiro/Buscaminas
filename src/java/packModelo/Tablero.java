package packModelo;

public class Tablero {

    private static Tablero mTablero;
    private packModelo.Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;

    private Tablero(){}

    public void generarTablero(){
        matriz = new Casilla[altura-1][anchura-1];
        for (int i = 0; i<altura;i++){
            for(int j = 0; j<anchura;j++){
                matriz[i][j] = new CasillaVacia(false,false);
            }
        }
    }
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
}