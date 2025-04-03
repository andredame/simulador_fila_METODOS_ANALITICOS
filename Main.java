import java.util.*;




public class Main {

    public static void main(String[] args) {


        //TIPO DE FILA G/G/SERVIDORES/CAPACIDADE
        // FILA 1 G/G/2/3 CHEGADA entre 1...4, ATENDIMENTO 3..4
        int servidores = 2; 
        int capacidade = 3; 

        

        Fila fila1 = new Fila(
            servidores,
            capacidade,
            1.0, //MinArrival
            4.0, //MaxArrival
            3.0, //MinService
            4.0 //MaxService
        );
        // Fila 2 - G/G/1/5, atendimento entre 2..3.

        servidores = 1;
        capacidade = 5;
        Fila fila2 = new Fila(
            servidores,
            capacidade,
            0.0, //MinArrival
            0.0, //MaxArrival
            2.0, //MinService
            3.0 //MaxService
        );
        //Queue type 
        Queue<Double> numAleatorios = new LinkedList<>();
        RandomNumbers gerador = new RandomNumbers(1, 1664525, 1013904223, Math.pow(2, 32));
        for (int i = 0; i < 100000; i++) {
            numAleatorios.add(gerador.next());
        }
        
        PriorityQueue<Event> escalonador = new PriorityQueue<>();



        double tempoTotal= 0.0;
        double tempoChegada = 1.5;
        escalonador.add(new Event(EventType.CHEGADA, tempoChegada));
        
        while (!numAleatorios.isEmpty()){
            //Pega o proximo evento
            Event eventoAtual = escalonador.poll();

            //ACUMULA TEMPO NOS ESTADOS DE CADA FILA 
            fila1.acumulaTempoEstado( eventoAtual.getTempo()-tempoTotal);
            fila2.acumulaTempoEstado( eventoAtual.getTempo()-tempoTotal);
            //ATUALIZA O TEMPO TOTAL
            
            tempoTotal = eventoAtual.getTempo();
            //ACUMULA TEMPO
            acumulaTempo(fila1, fila2, eventoAtual.getTempo());
            if(eventoAtual.getType() == EventType.CHEGADA){   
                //ACUMULA TEMPO CHEGADA

                if(fila1.status() < fila1.getCapacidade()){
                    fila1.in();
                    if(fila1.status()<=fila1.getServidores()){
                        if(!numAleatorios.isEmpty()){
                            escalonador.add(new Event(EventType.PASSAGEM, escalonaPassagem(fila1, numAleatorios.poll(), tempoTotal)));
                        }
                    }
                }
                else{fila1.loss();}
                if(!numAleatorios.isEmpty()){
                    //ESCALONADOR ADD CHEGADA
                    escalonador.add(new Event(EventType.CHEGADA, escalonaChegada(fila1, numAleatorios.poll(), tempoTotal)));
                }
                
            }

            if(eventoAtual.getType() == EventType.SAIDA){
                
                fila2.out();
                if(fila2.status()>=fila2.getServidores()){
                    if (!numAleatorios.isEmpty()){
                        escalonador.add(new Event(EventType.SAIDA, escalonaPassagem(fila2, numAleatorios.poll(), tempoTotal)));
                    }
                }
            }
            if(eventoAtual.getType() == EventType.PASSAGEM){
                fila1.out();
                if(fila1.status()>=fila1.getServidores()){
                    if (!numAleatorios.isEmpty()){
                        escalonador.add(new Event(EventType.PASSAGEM, escalonaSaida(fila1, numAleatorios.poll(), tempoTotal)));
                    }
                }
                if (fila2.status()<fila2.getCapacidade()){
                    fila2.in();
                    if(fila2.status()<=fila2.getServidores()){
                        //ESCALONADOR ADD SAIDA SEGUNDA FILA
                        if (!numAleatorios.isEmpty()){
                            escalonador.add(new Event(EventType.SAIDA, escalonaSaida(fila2, numAleatorios.poll(), tempoTotal)));
                        }
                    }
                }
                else{
                    fila2.loss();
                }
            }
            


        }
        fila1.imprimir();
        fila2.imprimir();




    }
    public static void acumulaTempo(Fila fila1, Fila fila2, double tempoChegada){
        fila1.setTotalTime(tempoChegada);
        fila2.setTotalTime(tempoChegada);
    }

    public static double escalonaPassagem(Fila fila, double numAleatorio,double tempoTotal){

        double tempoPassagem = tempoTotal + fila.getMinService() + ((fila.getMaxService() - fila.getMinService()) * numAleatorio);
        return tempoPassagem;

    }
    public static double escalonaSaida(Fila fila, double numAleatorio,double tempoTotal){

        double tempoSaida = tempoTotal +fila.getMinService() + ((fila.getMaxService() - fila.getMinService()) * numAleatorio);
        return tempoSaida;

    }
    public static double escalonaChegada(Fila fila, double numAleatorio,double tempoTotal){

        double tempoChegada = tempoTotal + fila.getMinArrival() + ((fila.getMaxArrival() - fila.getMinArrival()) * numAleatorio);
        return tempoChegada;

    }

    
}