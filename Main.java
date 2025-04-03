import java.util.*;




public class Main {

    public static void main(String[] args) {


        //TIPO DE FILA G/G/SERVIDORES/CAPACIDADE
        int servidores = 2; 
        int capacidade = 4; 

        

        Fila fila1 = new Fila(
            servidores,
            capacidade,
            0.0, //MinArrival
            1.0, //MaxArrival
            0.0, //MinService
            1.0 //MaxService
        );
        servidores = 3;
        capacidade = 5;
        Fila fila2 = new Fila(
            servidores,
            capacidade,
            0.0, //MinArrival
            1.0, //MaxArrival
            0.0, //MinService
            1.0 //MaxService

        );
        //Queue type 
        Queue <Double> numAleatorios = new LinkedList<>();
        numAleatorios.add(0.6);
        numAleatorios.add(0.8);
        numAleatorios.add(0.3);
        numAleatorios.add(0.7);
        numAleatorios.add(0.4);
        numAleatorios.add(0.1);

        PriorityQueue<Event> escalonador = new PriorityQueue<>();

        double tempoTotal= 0.0;
        double tempoChegada = 0.0;
        
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
                //ACUMULA TEMPO CHEGADA
                if(fila1.status() < fila1.getCapacidade()){
                    fila1.in();
                    if(fila1.status()<=fila1.getServidores()){
                        //adicionar passagem
                    }
                }
                else{fila1.loss();}
                //ESCALONA UMA CHEGADA                 
            }

            if(eventoAtual.getType() == EventType.SAIDA){
                //ACUMULA TEMPO 
                fila2.out();
                if(fila2.status()>=fila2.getServidores()){
                    //ADICIONA UMA SAIDA com os tempos da segunda fila 
                }
            }
            if(eventoAtual.getType() == EventType.PASSAGEM){
                fila1.out();
                if(fila1.status()>=fila1.getServidores()){
                   //ESCALONADOR ADD TG PA 5,6 SAIDA FILA 1 
                }
                if (fila2.status()<fila2.getCapacidade()){
                    fila2.in();
                    if(fila2.status()<=fila2.getServidores()){
                        //ESCALONADOR ADD SAIDA SEGUNDA FILA
                    }
                }
                else{
                    fila2.loss();
                }
            }
            


        }
        System.out.println("Tempo total: " + tempoTotal);


    }

    
}