import java.io.FilenameFilter;

public class Event implements Comparable<Event> {
    private EventType type;
    private double tempo;

    //variaveis dadas pelo exercicio
    public  static double MIN_CHEGADA_CLIENTE; 
    public static double MAX_CHEGADA_CLIENTE;

    //variaveis dadas pelo exercicio
    public static double MIN_PEDIDO_SAIDA;
    public static double MAX_PEDIDO_SAIDA;

    public Event(EventType type, double tempo) {
        this.type = type;
        this.tempo = tempo;

    }


    public EventType getType() {
        return type;
    }
    public void setType(EventType type) {
        this.type = type;
    }
    public double getTempo() {
        return tempo;
    }
    public void setTempo(double tempo) {
        this.tempo = tempo;
    }


    @Override
    public int compareTo(Event other) {
        return Double.compare(this.tempo, other.tempo);
    }

    
    

}


