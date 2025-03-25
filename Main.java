import java.util.*;



public class Main {

    public static void main(String[] args) {

        
        Fila fila = new Fila();

        //Queue type 
        Queue <Double> numAleatorios = new LinkedList<>();
        
        

        //adicionar o primeiro evento
        double tempoTotal= 0.0;
        fila.add(EventType.CHEGADA,numAleatorios.poll(),2.0);
        
        while (!numAleatorios.isEmpty()){
            Event eventoAtual = fila.remove();


            tempoTotal = eventoAtual.getTempo();

            if(eventoAtual.getType() == EventType.CHEGADA){   
                
                if (fila.size()<5){
                    fila.incrementaFila();
                    if(fila.getTamFila()<=1){
                        fila.add(EventType.SAIDA,numAleatorios.poll(),tempoTotal);
                    }
                }
                fila.add(EventType.CHEGADA,numAleatorios.poll(),tempoTotal);
            }
            else{
                fila.decrementaFila();
                if(fila.getTamFila()>=1){
                    fila.add(EventType.SAIDA,numAleatorios.poll(),tempoTotal);
                }
            } 
            
        }


    }
}