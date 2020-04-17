package packModelo.packCasilla;

public abstract class Estado {
    private Coordenada coordenada;

    public Estado (Coordenada pCoordenada){ this.coordenada = pCoordenada; }

    public abstract void marcarDesmarcarCasilla(Casilla casilla);
}
