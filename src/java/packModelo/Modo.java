package packModelo;

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
}
