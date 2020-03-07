package packModelo;

import java.util.Random;

public abstract class Modo {
    protected Tablero tablero;

    protected abstract void definirAltura();
    protected abstract void definirAnchura();
    protected abstract void definirMinas();

    public Tablero generarTablero() {
        this.tablero = new Tablero();

        definirAltura();
        definirAnchura();
        definirMinas();

        int altura = this.tablero.getFilas();
        int anchura = this.tablero.getColumnas();
        Casilla[][] matriz = new Casilla[altura-1][anchura-1];

        for (int i = 0; i<altura-1;i++){
            for(int j = 0; j<anchura-1;j++){
                matriz[i][j] = new CasillaNormal(false,false);
            }
        }
        tablero.setCasillas(matriz);
        return tablero;
    }
    private void ponerMinas(){
        boolean hayMina;
        int fila, columna;
        Random r = new Random();

        for(int i = 0 ; i < tablero.getMinas(); i++){
            do {
                fila = r.nextInt(tablero.getFilas());
                columna = r.nextInt(tablero.getColumnas());
                incrementarAdyacentes(fila,columna);
                if(Tablero.getmTablero().devolverCasilla(fila,columna) instanceof CasillaMina) hayMina = true;
                else hayMina = false;
            } while (hayMina);
            Tablero.getmTablero().setCasilla(new CasillaMina(false,false),fila,columna);
        }
    }
    private void ponerNumeros(){
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if(Tablero.getmTablero().devolverCasilla(i,j) instanceof CasillaMina) {
                    incrementarAdyacentes(i,j);
                }
            }
        }
    }
    private void incrementarCasilla(int pFila, int pColumna){
        // si las coordenadas están dentro del tablero, entonces
        if ((pFila >= 0 && pColumna >= 0)&&(pFila < tablero.getFilas() && pColumna < tablero.getColumnas())){
            //si es una instancia de CasillaVacia, entonces se le cambia el tipo de casilla, y le incrementamos el número
            if (Tablero.getmTablero().devolverCasilla(pFila,pColumna) instanceof CasillaNormal) ((CasillaNormal) Tablero.getmTablero().devolverCasilla(pFila,pColumna)).incrementarNumero();
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
}
