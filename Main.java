import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Parâmetros da simulação
        //G/G/1/5, chegadas entre 2...5, atendimento entre 3...5;
        String tipoFila = "G/G/1/5"; // Tipo da fila

        simularFila(tipoFila,2,5,3,5,2);
        tipoFila="G/G/2/5";
        simularFila(tipoFila,2,5,3,5,2);
    }

    /**
     * 
     * @param tipoFila String com o tipo da fila G/G/1/5
     * @param tempoChegadaClienteMin tempo minimo de chegada do cliente
     * @param tempoChegadaClienteMax tempo maximo de chegada do cliente
     * @param tempoPedidoSaidaMin tempo minimo de pedido de saida
     * @param tempoPedidoSaidaMax tempo maximo de pedido da saida
     * @param primeiraChegada primeira chegada programada para a fila 
     */
    public static void simularFila(
        String tipoFila,double tempoChegadaClienteMin,double tempoChegadaClienteMax, double tempoPedidoSaidaMin,double tempoPedidoSaidaMax,double primeiraChegada
        ) {
        
        int lambda = Integer.parseInt(tipoFila.split("/")[2]);
        int estadoFila = Integer.parseInt(tipoFila.split("/")[3]);

        //TEMPO DE CHEGADA DO PRIMEIRO CLIENTE
        Event.MIN_CHEGADA_CLIENTE = tempoChegadaClienteMin;
        Event.MAX_CHEGADA_CLIENTE = tempoChegadaClienteMax;

        //TEMPO DE PEDIDO DE SAIDA
        Event.MIN_PEDIDO_SAIDA = tempoPedidoSaidaMin;
        Event.MAX_PEDIDO_SAIDA = tempoPedidoSaidaMax;

        double tempoChegada = primeiraChegada;

        Fila fila = new Fila();

        // Gera 100.000 números aleatórios usando LCG
        Queue<Double> numAleatorios = new LinkedList<>();
        RandomNumbers gerador = new RandomNumbers(1, 1664525, 1013904223, Math.pow(2, 32));
        for (int i = 0; i < 100000; i++) {
            numAleatorios.add(gerador.next());
        }

        double tempoTotal = 0.0;

        while (!numAleatorios.isEmpty()) {
            Event eventoAtual;
            if (tempoTotal == 0.0) {
                eventoAtual = new Event(EventType.CHEGADA, tempoChegada);
            } else {
                eventoAtual = fila.remove();
            }

            tempoTotal = eventoAtual.getTempo();

            if (eventoAtual.getType() == EventType.CHEGADA) {
                if (fila.size() < estadoFila) {
                    fila.incrementaFila();
                    if (fila.getTamFila() <= lambda && !numAleatorios.isEmpty()) {
                        fila.add(EventType.SAIDA, numAleatorios.poll(), tempoTotal);
                    }
                }
                if (!numAleatorios.isEmpty()) {
                    fila.add(EventType.CHEGADA, numAleatorios.poll(), tempoTotal);
                }
            } else {
                fila.decrementaFila();
                if (fila.getTamFila() >= lambda && !numAleatorios.isEmpty()) {
                    fila.add(EventType.SAIDA, numAleatorios.poll(), tempoTotal);
                }
            }
        }

        System.out.println("Tempo total: " + tempoTotal);
    }
}
