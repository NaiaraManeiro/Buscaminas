package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {

    private Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;
    private int nCasillasRestantes;

    public Casilla devolverCasilla(int x, int y) {
        return this.matriz[x][y];
    }

    public void marcarCasilla(int x, int y){
        matriz[x][y].marcarCasilla();
    }
    public void desmarcarCasilla(int x, int y){ matriz[x][y].desmarcarCasilla(); }

    public int getMinas(){return minas;}
    public void setMinas(int pMinas){
        this.minas = pMinas;
    }

    public int getFilas(){ return altura; }
    public void setAltura(int pAltura){
        this.altura = pAltura;
    }

    public int getColumnas() { return anchura; }
    public void setAnchura(int pAnchura){
        this.anchura = pAnchura;
    }

    public int getnCasillasRestantes(){ return nCasillasRestantes; }
    public void decrementarCasillasRestantes(){ nCasillasRestantes--;}

    public void setCasillas(Casilla[][] casillas) { this.matriz = casillas; }

    public void setCasilla(Casilla pCasilla){
        Coordenada coord = pCasilla.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        this.matriz[x][y] = pCasilla;
    }

    public void desplegarAdyacentes(int x, int y) {
        Casilla act = this.devolverCasilla(x, y);
        ArrayList<Casilla> mirar = new ArrayList<>();
        mirar.add(act);
        Iterator<Casilla> itr = mirar.iterator();
        while (itr.hasNext()) {
            Casilla c = itr.next();
            if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() == 0 && !c.estaPulsada()) {
                c.setCasillaClicada(true);
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
                c.setCasillaClicada(true);
            }
            this.decrementarCasillasRestantes();
        }
    }
}