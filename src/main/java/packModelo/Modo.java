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
        int minas = this.tablero.getMinas();

        Casilla[][] matriz = new Casilla[anchura][altura];
        this.tablero.setnCasillasRestantes((altura*anchura)-minas);
        Juego.getmJuego().setnMinasRestantes(minas);

        for (int i = 0; i < anchura; i++){
            for(int j = 0; j < altura; j++){
                matriz[i][j] = new CasillaNormal(false,false, new Coordenada(i, j));
            }
        }
        tablero.setCasillas(matriz);
        ponerMinas();
        ponerNumeros();
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
                //incrementarAdyacentes(columna,fila);
                if(tablero.devolverCasilla(columna, fila) instanceof CasillaMina) hayMina = true;
                else hayMina = false;
            } while (hayMina);
            tablero.setCasilla(new CasillaMina(false,false, new Coordenada(columna, fila)));
        }
    }
    private void ponerNumeros(){
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if(tablero.devolverCasilla(j,i) instanceof CasillaMina) {
                    incrementarAdyacentes(j,i);
                }
            }
        }
    }
    private void incrementarCasilla(int pColumna, int pFila){
        // si las coordenadas están dentro del tablero, entonces
        if ((pFila >= 0 && pColumna >= 0)&&(pFila < tablero.getFilas() && pColumna < tablero.getColumnas())){
            //si es una instancia de CasillaVacia, entonces se le cambia el tipo de casilla, y le incrementamos el número
            if (tablero.devolverCasilla(pColumna, pFila) instanceof CasillaNormal) ((CasillaNormal) tablero.devolverCasilla(pColumna, pFila)).incrementarNumero();
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

        incrementarCasilla(x, y+1);
        incrementarCasilla(x, y-1);

        incrementarCasilla(x+1, y);
        incrementarCasilla(x-1, y);

        incrementarCasilla(x-1, y-1);
        incrementarCasilla(x-1, y+1);

        incrementarCasilla(x+1, y+1);
        incrementarCasilla(x+1, y-1);

    }
}
