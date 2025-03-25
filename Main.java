import java.util.*;




public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        
        Fila fila = new Fila();
        //Queue type 
        Queue <Double> numAleatorios = new LinkedList<>();
        numAleatorios.add(0.6);
        numAleatorios.add(0.8);
        numAleatorios.add(0.3);
        numAleatorios.add(0.7);
        numAleatorios.add(0.4);
        numAleatorios.add(0.1);
        

        
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

    
}