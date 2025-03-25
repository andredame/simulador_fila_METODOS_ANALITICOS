import java.util.*;




public class Main {

    public static void main(String[] args) {


        //TIPO DE FILA G/G/1/2
        int lambda = 2; // PRIMEIRO NUMERO DO TIPO DE FILA EX G/G/1/2
        int estadoFila = 2; // PRIMEIRO NUMERO DO TIPO DE FILA EX G/G/1/2

        //TEMPO DE CHEGADA DO PRIMEIRO CLIENT
        Event.MIN_CHEGADA_CLIENTE = 1.0;
        Event.MAX_CHEGADA_CLIENTE = 3.0;

        //TEMPO DE PEDIDO DE SAIDA
        Event.MIN_PEDIDO_SAIDA = 1.0;
        Event.MAX_PEDIDO_SAIDA = 4.0;

        //TEMPO DE CHEGADA DO PRIMEIRO CLIENTE 
        double tempoChegada = 1.0;

        





        Fila fila = new Fila();
        //Queue type 
        Queue <Double> numAleatorios = new LinkedList<>();
        numAleatorios.add(0.6);
        numAleatorios.add(0.8);
        numAleatorios.add(0.3);
        numAleatorios.add(0.7);
        numAleatorios.add(0.4);
        numAleatorios.add(0.1);
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