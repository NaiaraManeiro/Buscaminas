package packModelo;

import java.util.Observable;

public class Cronometro extends Observable implements Runnable {
    private Thread t;
    private int minutos;
    private int segundos;
    private volatile boolean stopped;

    public Cronometro() {
        this.t = new Thread(this, "Crono");
        this.t.start();
    }

    public int getMinutos() { return minutos; }
    public int getSegundos() { return segundos; }

    public void pararCrono(){stopped=true;}
    @Override
    public void run() {
        minutos = 0;
        segundos = 0;
        while(!stopped) {
            if(segundos == 60) {
                minutos++;
                segundos = 0;
            }
            setChanged();
            String tiempo = (segundos < 10) ? minutos+":0"+segundos : minutos+":"+segundos ;
            notifyObservers(tiempo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
            segundos++;
        }
    }
    public void reset(){
        minutos = 0;
        segundos = 0;
        stopped = false;
    }
}

