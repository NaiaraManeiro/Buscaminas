package packModelo;

import java.util.Random;

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
    private void ponerMinas(){
        boolean hayMina;
        int fila, columna;
        Random r = new Random();

        for(int i = 0 ; i < minas; i++){
            do {
                fila = r.nextInt(getFilas());
                columna = r.nextInt(getColumnas());
                if(matriz[fila][columna] instanceof CasillaMina) hayMina = true;
                else hayMina = false;
            } while (hayMina);
            matriz[fila][columna] = new CasillaMina(false,false);
        }
    }
    public void marcarCasilla(int x, int y){
        matriz[x][y].marcarCasilla();
    }
    private void ponerNumeros(){
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                if(matriz[i][j] instanceof CasillaMina) {
                    incrementarAdyacentes(i,j);
                }
            }
        }
    }
    private void incrementarCasilla(int pFila, int pColumna){
        // si las coordenadas están dentro del tablero, entonces
        if ((pFila >= 0 && pColumna >= 0)&&(pFila < getFilas() && pColumna < getColumnas())){
            //si es una instancia de CasillaVacia, entonces se le cambia el tipo de casilla, y le incrementamos el número
            if (matriz[pFila][pColumna] instanceof CasillaVacia){
                matriz[pFila][pColumna] = new CasillaNumero(false,false);
                ((CasillaNumero) matriz[pFila][pColumna]).incrementarNumero();
            }
            // si la casilla ya fuese de tipo CasillaNumero, simplemente se le aumenta el número
            else if (matriz[pFila][pColumna] instanceof CasillaNumero) ((CasillaNumero) matriz[pFila][pColumna]).incrementarNumero();
        }
    }
    // incrementamos o ponemos numero a la coordenada seleccionada (los parámetros son las coordenadas donde se encuentra la mina)
    private void incrementarAdyacentes(int x, int y){
        /*
        *
        *       ^   ^   ^
        *        \  |  /
        *      < –  ▀  –  >
        *        /  |  \
        *       v   v   v
        * */
        incrementarCasilla(x-1, y-1);
        incrementarCasilla(x-1, y);
        incrementarCasilla(x-1, y+1);
        incrementarCasilla(x, y+1);
        incrementarCasilla(x+1, y+1);
        incrementarCasilla(x+1, y);
        incrementarCasilla(x+1, y-1);
        incrementarCasilla(x, y-1);
    }
    public void setAltura(int pAltura){
        this.altura = pAltura;
    }

    public void setAnchura(int pAnchura){
        this.anchura = pAnchura;
    }

    public void setMinas(int pMinas){
        this.minas = pMinas;
        ponerMinas();
    }

    public int getFilas(){ return altura; }

    public int getColumnas() { return anchura; }

    public void setCasillas(Casilla[][] casillas) { this.matriz = casillas; }
}