import java.util.*;




public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        
        Fila fila = new Fila();
        //Queue type 
        Queue <Double> numAleatorios = lerNumerosAleatorios(sc);
        

        
        System.out.println("DIGITE AQUI O TIPO DA FILA (NO FORMATO G/G/1/2):");
        String tipoFila = sc.next();
        String[] tipoFilaArray = tipoFila.split("/");
        
        
        int lambda = Integer.parseInt(tipoFilaArray[2]);

        int estadoFila = Integer.parseInt(tipoFilaArray[3]);

        System.out.println("Digite o tempo da primeira chegada:");
        double tempoChegada = sc.nextDouble();

        double tempoTotal= 0.0;
        
        while (!numAleatorios.isEmpty()){
            Event eventoAtual;

            if (tempoTotal==0.0){
                eventoAtual= new Event (EventType.CHEGADA,tempoChegada);
            }
            else{
                eventoAtual = fila.remove();
            }
            

            tempoTotal = eventoAtual.getTempo();

            if(eventoAtual.getType() == EventType.CHEGADA){   
                
                if (fila.size()<estadoFila){
                    fila.incrementaFila();
                    if(fila.getTamFila()<=lambda){
                        fila.add(EventType.SAIDA,numAleatorios.poll(),tempoTotal);
                    }
                }
                fila.add(EventType.CHEGADA,numAleatorios.poll(),tempoTotal);
            }
            else{
                fila.decrementaFila();
                if(fila.getTamFila()>=lambda){
                    fila.add(EventType.SAIDA,numAleatorios.poll(),tempoTotal);
                }
            } 
        }
        System.out.println("Tempo total: " + tempoTotal);


    }

    private static Queue<Double> lerNumerosAleatorios(Scanner sc) {
        Queue<Double> numeros = new LinkedList<>();
        System.out.println("Digite a quantidade de números aleatórios:");
        int qtd = sc.nextInt();

        for (int i = 0; i < qtd; i++) {
            System.out.println("Digite o número aleatório " + (i + 1) + ":");
            numeros.add(sc.nextDouble());
        }

        return numeros;
    }
}