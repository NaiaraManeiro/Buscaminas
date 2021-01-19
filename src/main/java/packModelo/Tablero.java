package packModelo;

import packModelo.packCasilla.*;

import java.util.*;

public class Tablero {

    private Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;
    private int nCasillasRestantes;
    private String minasEspeciales;

    public Tablero(int pFilas, int pColumnas, int pMinas, String pMinasEspeciales){
        altura = pFilas;
        anchura = pColumnas;
        minas = pMinas;
        minasEspeciales = pMinasEspeciales;
        matriz = new Casilla[anchura][altura];
        nCasillasRestantes = altura*anchura;
        Juego.getmJuego().setnMinasRestantes(minas);
        for (int i = 0; i < anchura; i++){
            for(int j = 0; j < altura; j++){
                matriz[i][j] = new CasillaNormal(new NoClicada(new Coordenada(i,j)), new Coordenada(i, j));
            }
        }
        ponerMinas(minasEspeciales);
        ponerNumeros();
    }

    public Casilla devolverCasilla(int x, int y) {
        try{
            return this.matriz[x][y];
        } catch (Exception e){
            return null;
        }
    }

    public int getMinas(){return minas;}

    public int getFilas(){ return altura; }

    public int getColumnas() { return anchura; }

    public String getMinasEspeciales() { return minasEspeciales; }

    public int getnCasillasRestantes(){ return nCasillasRestantes; }
    public void decrementarCasillasRestantes(){ nCasillasRestantes--;}

    public void setCasilla(Casilla pCasilla){
        Coordenada coord = pCasilla.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        this.matriz[x][y] = pCasilla;
    }

    public void desplegarAdyacentes(int x, int y) {
        Casilla act = this.devolverCasilla(x, y);
        LinkedList<Casilla> mirar = new LinkedList<>();
        LinkedList<Casilla> visitados = new LinkedList<>();
        mirar.add(act);
        visitados.add(act);
        while (!mirar.isEmpty()) {
            Casilla c = mirar.remove();
            if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() == 0 && c.getEstado() instanceof NoClicada) {
                Coordenada coord = c.getCoordenada();
                c.setEstado(new Clicada(coord));
                x = coord.getColumna();
                y = coord.getFila();
                Casilla arriba = this.devolverCasilla(x, y + 1);
                anadir(arriba, mirar, visitados);
                Casilla abajo = this.devolverCasilla(x, y - 1);
                anadir(abajo, mirar, visitados);
                Casilla derecha = this.devolverCasilla(x + 1, y);
                anadir(derecha, mirar, visitados);
                Casilla izquierda = this.devolverCasilla(x - 1, y);
                anadir(izquierda, mirar, visitados);
                Casilla diagArribDer = this.devolverCasilla(x + 1, y + 1);
                anadir(diagArribDer, mirar, visitados);
                Casilla diagArribIzq = this.devolverCasilla(x - 1, y + 1);
                anadir(diagArribIzq, mirar, visitados);
                Casilla diagAbajDer = this.devolverCasilla(x + 1, y - 1);
                anadir(diagAbajDer, mirar, visitados);
                Casilla diagAbajIzq = this.devolverCasilla(x - 1, y - 1);
                anadir(diagAbajIzq, mirar, visitados);
                Juego.getmJuego().activarUpdate(coord);
            }
            this.decrementarCasillasRestantes();
        }
    }

    private void anadir(Casilla casilla, LinkedList<Casilla> mirar, LinkedList<Casilla> visitados){
        if (casilla != null) {
            if (casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() == 0) {
                if (!visitados.contains(casilla)){
                    mirar.add(casilla);
                    visitados.add(casilla);
                }
            } else if (casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() != 0) {
                if (casilla.getEstado() instanceof NoClicada){
                    casilla.setEstado(new Clicada(casilla.getCoordenada()));
                    this.decrementarCasillasRestantes();
                    Juego.getmJuego().activarUpdate(casilla.getCoordenada());
                }
            }
        }
    }

    public void imprimirChivato() {
        Casilla casilla;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                Coordenada c = new Coordenada(j,i);
                casilla = devolverCasilla(c.getColumna(),c.getFila());
                if(casilla instanceof CasillaMinaNormal) {
                    System.out.print(" * ");
                } else if (casilla instanceof CasillaMinaReset) {
                    System.out.print(" r ");
                } else if (casilla instanceof CasillaMina50) {
                    System.out.print(" % ");
                } else if(casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() != 0) {
                    System.out.print(" " + ((CasillaNormal) casilla).getNumero() + " ");
                } else if(casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() == 0){
                    System.out.print(" 0 ");
                }else{}
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void ponerMinas(String minasEspeciales){
        boolean hayMina;
        int fila, columna;
        Random r = new Random();
        for(int i = 0 ; i < minas; i++){
            do {
                fila = r.nextInt(altura);
                columna = r.nextInt(anchura);
                if(devolverCasilla(columna, fila) instanceof CasillaMina) hayMina = true;
                else hayMina = false;
            } while (hayMina);

            if (minasEspeciales.equals("no")) {
                setCasilla(new CasillaMinaNormal(new NoClicada(new Coordenada(columna, fila)), new Coordenada(columna, fila)));
            } else if (minasEspeciales.equals("si")) {
                int numMina = r.nextInt(3);
                if (numMina == 0) {
                    setCasilla(new CasillaMinaNormal(new NoClicada(new Coordenada(columna, fila)), new Coordenada(columna, fila)));
                } else if (numMina == 1) {
                    setCasilla(new CasillaMinaReset(new NoClicada(new Coordenada(columna, fila)), new Coordenada(columna, fila)));
                } else if (numMina == 2) {
                    setCasilla(new CasillaMina50(new NoClicada(new Coordenada(columna, fila)), new Coordenada(columna, fila)));
                }
            }
        }
    }

    private void ponerNumeros(){
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                if(devolverCasilla(j,i) instanceof CasillaMinaNormal) incrementarAdyacentes(j,i);
                if(devolverCasilla(j,i) instanceof CasillaMinaReset) incrementarAdyacentes(j,i);
                if(devolverCasilla(j,i) instanceof CasillaMina50) incrementarAdyacentes(j,i);
            }
        }
    }

    private void incrementarCasilla(int pColumna, int pFila){
        // si las coordenadas están dentro del tablero, entonces
        if ((pFila >= 0 && pColumna >= 0)&&(pFila < altura && pColumna < anchura)){
            //si es una instancia de CasillaVacia, entonces se le cambia el tipo de casilla, y le incrementamos el número
            if (devolverCasilla(pColumna, pFila) instanceof CasillaNormal) ((CasillaNormal) devolverCasilla(pColumna, pFila)).incrementarNumero();
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