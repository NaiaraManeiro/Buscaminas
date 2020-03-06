package packModelo;

public class Tablero {

    private static Tablero mTablero;
    private Casilla[][] matriz;
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

    public void incrementarMinas(){minas++;}

    public void decrementarMinas(){minas--;}

    public void marcarCasilla(int x, int y){
        matriz[x][y].marcarCasilla();
    }

    public int getMinas(){return minas;}
    public void setAltura(int pAltura){
        this.altura = pAltura;
    }

    public void setAnchura(int pAnchura){
        this.anchura = pAnchura;
    }

    public void setMinas(int pMinas){
        this.minas = pMinas;
    }

    public int getFilas(){ return altura; }

    public int getColumnas() { return anchura; }

    public void setCasillas(Casilla[][] casillas) { this.matriz = casillas; }

    public boolean haGanado(){
        boolean ganar = false;
        if(matriz != null){
            for (int i = 0; i< getFilas();i++){
                for (int j = 0; j< getColumnas();j++){
                    Casilla cc = matriz[i][j];
                    if(cc instanceof CasillaNormal && cc.estaPulsada()) ganar = true;
                    else return false;
                }
            }
        }
        return ganar;
    }

    public void desplegarAdyacentes(Casilla pCasilla){

    }
}