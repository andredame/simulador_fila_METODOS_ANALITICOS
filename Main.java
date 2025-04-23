import java.util.*;

public class Main {

    public static void main(String[] args) {
        Queue<Double> numAleatorios = gerarNumerosAleatorios(100_000);
        PriorityQueue<Event> escalonador = new PriorityQueue<>();
        ArrayList<Fila> redeFilas = new ArrayList<>();

        Fila fila1 = criarFila1();
        Fila fila2 = criarFila2();

        double tempoTotal = 0.0;
        escalonador.add(new Event(EventType.CHEGADA, 1.5));

        while (!numAleatorios.isEmpty()) {
            Event eventoAtual = escalonador.poll();
            double tempoEvento = eventoAtual.getTempo();

            acumularTempo(fila1, fila2, tempoEvento - tempoTotal);
            tempoTotal = tempoEvento;

            processarEvento(eventoAtual, fila1, fila2, escalonador, numAleatorios, tempoTotal);
        }

        fila1.imprimir();
        fila2.imprimir();
    }
    private static Fila criarFila1() {
        return new Fila(2, 3, 1.0, 4.0, 3.0, 4.0,0.2); // G/G/2/3
    }

    private static Fila criarFila2() {
        return new Fila(1, 5, 0.0, 0.0, 2.0, 3.0,0.2); // G/G/1/5
    }

    // ------------------ Métodos auxiliares ----------------------

    private static Queue<Double> gerarNumerosAleatorios(int quantidade) {
        Queue<Double> fila = new LinkedList<>();
        RandomNumbers gerador = new RandomNumbers(1, 1664525, 1013904223, Math.pow(2, 32));
        for (int i = 0; i < quantidade; i++) {
            fila.add(gerador.next());
        }
        return fila;
    }

    

    private static void acumularTempo(Fila origem, Fila destino, double delta) {
        origem.acumulaTempoEstado(delta);
        destino.acumulaTempoEstado(delta);
        origem.setTotalTime(origem.getTotalTime() + delta);
        destino.setTotalTime(destino.getTotalTime() + delta);
    }

    private static void processarEvento(
        Event evento, Fila origem, Fila destino, PriorityQueue<Event> escalonador, Queue<Double> aleatorios, double tempoAtual
    ) {
        switch (evento.getType()) {
            case CHEGADA -> processarChegada(origem, escalonador, aleatorios, tempoAtual);
            case PASSAGEM -> processarPassagem(origem, destino, escalonador, aleatorios, tempoAtual);
            case SAIDA -> processarSaida(destino, escalonador, aleatorios, tempoAtual);
        }
    }

    private static void processarChegada(Fila origem, PriorityQueue<Event> escalonador, Queue<Double> aleatorios, double tempo) {
        if (origem.status() < origem.getCapacidade()) {
            origem.in();
            if (origem.status() <= origem.getServidores() && !aleatorios.isEmpty()) {
                escalonador.add(new Event(EventType.PASSAGEM, calculaTempo(tempo, aleatorios.poll(), origem.getMinService(), origem.getMaxService())));
            }
        } else {
            origem.loss();
        }

        if (!aleatorios.isEmpty()) {
            escalonador.add(new Event(EventType.CHEGADA, calculaTempo(tempo, aleatorios.poll(), origem.getMinArrival(), origem.getMaxArrival())));
        }
    }

    private static void processarPassagem(Fila origem, Fila destino, PriorityQueue<Event> escalonador, Queue<Double> aleatorios, double tempo) {
        origem.out();
        if (origem.status() >= origem.getServidores() && !aleatorios.isEmpty()) {
            escalonador.add(new Event(EventType.PASSAGEM, calculaTempo(tempo, aleatorios.poll(), origem.getMinService(), origem.getMaxService())));
        }

        if (destino.status() < destino.getCapacidade()) {
            destino.in();
            if (destino.status() <= destino.getServidores() && !aleatorios.isEmpty()) {
                escalonador.add(new Event(EventType.SAIDA, calculaTempo(tempo, aleatorios.poll(), destino.getMinService(), destino.getMaxService())));
            }
        } else {
            destino.loss();
        }
    }

    private static void processarSaida(Fila destino, PriorityQueue<Event> escalonador, Queue<Double> aleatorios, double tempo) {
        destino.out();
        if (destino.status() >= destino.getServidores() && !aleatorios.isEmpty()) {
            escalonador.add(new Event(EventType.SAIDA, calculaTempo(tempo, aleatorios.poll(), destino.getMinService(), destino.getMaxService())));
        }
    }

    private static double calculaTempo(double tempoAtual, double aleatorio, double min, double max) {
        return tempoAtual + min + ((max - min) * aleatorio);
    }
}
