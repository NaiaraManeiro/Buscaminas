package packModelo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void desplegarAdyacentes(int x, int y) {
        Casilla act = this.devolverCasilla(x, y);
        ArrayList<Casilla> mirar = new ArrayList<>();
        mirar.add(act);
        Iterator<Casilla> itr = mirar.iterator();
        while (itr.hasNext()) {
            Casilla c = itr.next();
            if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() == 0 && !c.getCasillaClicada()) {
                c.marcarCasilla();
                Coordenada coord = c.getCoordenada();
                x = coord.getColumna();
                y = coord.getFila();
                Casilla arriba = this.devolverCasilla(x, y + 1);
                mirar.add(arriba);
                Casilla abajo = this.devolverCasilla(x, y - 1);
                mirar.add(abajo);
                Casilla derecha = this.devolverCasilla(x + 1, y);
                mirar.add(derecha);
                Casilla izquierda = this.devolverCasilla(x - 1, y);
                mirar.add(izquierda);
                Casilla diagArribDer = this.devolverCasilla(x + 1, y + 1);
                mirar.add(diagArribDer);
                Casilla diagArribIzq = this.devolverCasilla(x - 1, y + 1);
                mirar.add(diagArribIzq);
                Casilla diagAbajDer = this.devolverCasilla(x + 1, y - 1);
                mirar.add(diagAbajDer);
                Casilla diagAbajIzq = this.devolverCasilla(x - 1, y - 1);
                mirar.add(diagAbajIzq);
            } else if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() != 0) {
                c.marcarCasilla();
            }

        }
    }
}