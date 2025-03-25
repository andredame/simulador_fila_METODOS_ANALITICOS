import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Fila {
    private PriorityQueue<Event> events;
    private int tamFila;



    public Fila() {
        this.events = new PriorityQueue<>();
        this.tamFila = 0;
    }


    public void add(EventType evento,double numAleatorio,double tempoTotal) {
        System.out.println("--------------------");
        System.out.println("Evento: "+evento);
        System.out.println("Numero Aleatorio: "+numAleatorio);
        System.out.println("Tempo: "+tempoTotal);
        double tempoNovo=calculoPosicao(tempoTotal ,evento, numAleatorio);
        System.out.println("Tempo Novo: "+tempoNovo);
        Event novoEvento ;
        if (evento == EventType.CHEGADA){
            //U(a, b) = a + [(b - a)*x]
            novoEvento= new Event(EventType.CHEGADA,calculoPosicao(tempoTotal,evento, numAleatorio));
        }
        else{
            novoEvento = new Event(EventType.SAIDA,calculoPosicao(tempoTotal,evento, numAleatorio));
        }
        events.add(novoEvento);

        System.out.println("--------------------");
    }

    public Event remove() {
        return events.poll();
    }



    public double calculoPosicao(double tempoTotal,EventType evento,double numAleatorio){
        if (evento == EventType.CHEGADA){

            return tempoTotal + (Event.MIN_CHEGADA_CLIENTE + ((Event.MAX_CHEGADA_CLIENTE - Event.MIN_CHEGADA_CLIENTE)*numAleatorio));
        }
        else{
            return tempoTotal + Event.MIN_PEDIDO_SAIDA + ((Event.MAX_PEDIDO_SAIDA - Event.MIN_PEDIDO_SAIDA)*numAleatorio);
        }
    }


    public void incrementaFila(){
        this.tamFila++;
    }
    public void decrementaFila(){
        this.tamFila--;
    }



    public boolean isEmpty() {
        return events.isEmpty();
    }
    public int size() {
        return events.size();
    }

    public int getTamFila() {
        return tamFila;
    }
    public void setTamFila(int tamFila) {
        this.tamFila = tamFila;
    }




}
