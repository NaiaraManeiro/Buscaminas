package packModelo;

import java.util.*;

public class Tablero {

    private Casilla[][] matriz;
    private int altura;
    private int anchura;
    private int minas;
    private int nCasillasRestantes;

    public Casilla devolverCasilla(int x, int y) {
        try{
            return this.matriz[x][y];
        } catch (Exception e){
            return null;
        }
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
    public void setnCasillasRestantes(int pCasillas) { this.nCasillasRestantes = pCasillas; }
    public void decrementarCasillasRestantes(){ nCasillasRestantes--;}

    public void setCasillas(Casilla[][] casillas) { this.matriz = casillas; }

    public void setCasilla(Casilla pCasilla){
        Coordenada coord = pCasilla.getCoordenada();
        int x = coord.getColumna();
        int y = coord.getFila();
        this.matriz[x][y] = pCasilla;
    }

    public void desplegarAdyacentes(int x, int y) {
        boolean fin = false;
        Casilla act = this.devolverCasilla(x, y);
        LinkedList<Casilla> mirar = new LinkedList<>();
        mirar.add(act);
        while (!fin) {
            Casilla c = mirar.remove();
            if (c instanceof CasillaNormal && ((CasillaNormal) c).getNumero() == 0 && !c.estaPulsada()) {
                c.setCasillaClicada(true);
                Coordenada coord = c.getCoordenada();
                x = coord.getColumna();
                y = coord.getFila();
                Casilla arriba = this.devolverCasilla(x, y + 1);
                anadir(arriba, mirar);
                Casilla abajo = this.devolverCasilla(x, y - 1);
                anadir(abajo, mirar);
                Casilla derecha = this.devolverCasilla(x + 1, y);
                anadir(derecha, mirar);
                Casilla izquierda = this.devolverCasilla(x - 1, y);
                anadir(izquierda, mirar);
                Casilla diagArribDer = this.devolverCasilla(x + 1, y + 1);
                anadir(diagArribDer, mirar);
                Casilla diagArribIzq = this.devolverCasilla(x - 1, y + 1);
                anadir(diagArribIzq, mirar);
                Casilla diagAbajDer = this.devolverCasilla(x + 1, y - 1);
                anadir(diagAbajDer, mirar);
                Casilla diagAbajIzq = this.devolverCasilla(x - 1, y - 1);
                anadir(diagAbajIzq, mirar);
                Juego.getmJuego().activarUpdate(coord);
            }
            this.decrementarCasillasRestantes();
            if (mirar.isEmpty()){
                fin = true;
            }
        }
    }

    public void anadir(Casilla casilla, LinkedList<Casilla> mirar){
        if (casilla != null) {
            if (casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() == 0) {
                mirar.add(casilla);
            } else if (casilla instanceof CasillaNormal && ((CasillaNormal) casilla).getNumero() != 0) {
                casilla.setCasillaClicada(true);
                Juego.getmJuego().activarUpdate(casilla.getCoordenada());
                this.decrementarCasillasRestantes();
            }
        }
    }
    public void imprimirChivato() {

        Casilla casilla;
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                Coordenada c = new Coordenada(j,i);
                casilla = devolverCasilla(c.getColumna(),c.getFila());
                if(casilla instanceof CasillaMina) {
                    System.out.print(" * ");
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
}