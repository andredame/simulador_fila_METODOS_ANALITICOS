import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Criação de filas
        Fila fila1 = new Fila("f1", 1, -1,1,2);
        Fila fila2 = new Fila("f2", 2, 5, 4, 8);
        Fila fila3 = new Fila("f3", 2, 10, 5, 15);

        int [] intChegada= {2, 4};
        LinkedList<Fila> filas = new LinkedList<Fila>();
        filas.add(fila1);filas.add(fila2); filas.add(fila3);
        //adicione as networks
        addNetwork(filas, "f1", "f2", 0.8f);
        addNetwork(filas, "f1", "f3", 0.2f);
        addNetwork(filas, "f2", "f1", 0.3f);
        addNetwork(filas, "f2", "f2", 0.5f);
        addNetwork(filas, "f3", "f3", 0.7f);

        //Criacao de numeros aleatorios
        Queue<Double> numAleatorios = new LinkedList<>();
        RandomNumbers gerador = new RandomNumbers(1, 1664525, 1013904223, Math.pow(2, 32));
        for (int i = 0; i < 100000; i++) {
            numAleatorios.add(gerador.next());
        }

        FilaRoteamento filaRoteamento = new FilaRoteamento(filas, intChegada,2.0, );
        // Executa a simulação
        filaRoteamento.run();
        // Imprime os resultados
        for (Fila fila : filas) {
            System.out.println("Fila: " + fila.getId() +   ", Perdas: " + fila.getPerdas());
            System.out.println("Estados: " + fila.getEstados());
        }

    }


    public static void addNetwork(LinkedList<Fila> filas,String sourceId, String targetId, float prob) {
        Fila source = null;

        for(Fila f : filas) {
            if(f.getId().equals(sourceId)) {
                source = f;
                break;
            }
        }

        for(Fila target : filas) {
            if(target.getId().equals(targetId)) {
                source.add(target, prob);
                return;
            }
        }
    }
    
}
