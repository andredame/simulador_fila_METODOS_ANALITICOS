import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Criação de filas
        Fila fila1 = new Fila("f1", 3, 10,5,10);
        Fila fila2 = new Fila("f2", 2, 15,11, 25);
        Fila fila3 = new Fila("f3", 3, 12, 15, 30);

        float [] primeiraChegada= {4, 8};
        LinkedList<Fila> filas = new LinkedList<Fila>();
        filas.add(fila1);filas.add(fila2); filas.add(fila3);
        //adicione as networks
        addNetwork(filas, "f1", "f2", 0.3f);
        addNetwork(filas, "f1", "f3", 0.6f);
        addNetwork(filas, "f2", "f3", 0.7f);
        addNetwork(filas, "f3", "f2", 0.4f);

    
        FilaRoteamento filaRoteamento = new FilaRoteamento(filas, primeiraChegada,2,100000,123 );
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
