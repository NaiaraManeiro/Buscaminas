package packModelo;

public class Coordenada {
    private int fila;
    private int columna;

    public Coordenada(int pColumna, int pFila){
        this.fila = pFila;
        this.columna = pColumna;
    }

    public int getFila() { return this.fila; }

    public int getColumna() { return this.columna; }
}
