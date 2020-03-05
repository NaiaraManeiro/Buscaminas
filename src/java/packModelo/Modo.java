package packModelo;

import java.util.Random;

public abstract class Modo {
    private Tablero tablero;

    public Modo(){}

    abstract public void definirAltura();

    abstract public void definirAnchura();

    abstract public void definirMinas();

    public Tablero generarTablero() {
        this.tablero = new Tablero();

        definirAltura();
        definirAnchura();
        definirMinas();

        int altura = this.tablero.getFilas();
        int anchura = this.tablero.getColumnas();
        Casilla[][] matriz = new Casilla[altura-1][anchura-1];

        for (int i = 0; i<altura;i++){
            for(int j = 0; j<anchura;j++){
                matriz[i][j] = new CasillaVacia(false,false);
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
            Casilla a = Tablero.getmTablero().devolverCasilla(fila,columna);
            a = new CasillaMina(false,false);
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
            if (Tablero.getmTablero().devolverCasilla(pFila,pColumna)instanceof CasillaVacia){
                CasillaNumero a = (CasillaNumero)Tablero.getmTablero().devolverCasilla(pFila,pColumna);
                a = new CasillaNumero(false,false);
                a.incrementarNumero();
            }
            // si la casilla ya fuese de tipo CasillaNumero, simplemente se le aumenta el número
            else if (Tablero.getmTablero().devolverCasilla(pFila,pColumna) instanceof CasillaNumero) ((CasillaNumero) Tablero.getmTablero().devolverCasilla(pFila,pColumna)).incrementarNumero();
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
