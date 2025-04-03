

public class Fila {
    private int servidores;
    
    private int capacidade;
    private double MinArrival;
    private double MaxArrival;
    private double MinService;
    private double MaxService;
    private int customerCount;
    private int lossCount;
    private double totalTime;
    private double[] tempo_estado;
    

    public Fila(
        int servidores,
         int capacidade, 
         double MinArrival, 
         double MaxArrival, 
         double MinService, 
         double MaxService
        ) {

        this.servidores = servidores;
        this.capacidade = capacidade;
        this.MinArrival = MinArrival;
        this.MaxArrival = MaxArrival;
        this.MinService = MinService;
        this.MaxService = MaxService;
        this.customerCount = 0;
        this.lossCount = 0;
        this.totalTime = 0.0;
        this.tempo_estado= new double[capacidade+1];
        for (int i = 0; i < capacidade; i++) {
            tempo_estado[i] = 0.0;
        }
        
    }
    public void acumulaTempoEstado(double tempoChegada){
        this.tempo_estado[this.customerCount] = tempoChegada;
        
    }
    public double getTempoEstado(int i){
        return tempo_estado[i];
    }


    public int status(){
        return customerCount;
    }

    public void loss(){
        lossCount++;
    }
    public void in(){
        customerCount++;
    }
    public void out(){
        customerCount--;
    }
    public int getServidores() {
        return servidores;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getMinArrival() {
        return MinArrival;
    }

    public double getMaxArrival() {
        return MaxArrival;
    }

    public double getMinService() {
        return MinService;
    }

    public double getMaxService() {
        return MaxService;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public int getLossCount() {
        return lossCount;
    }
    
    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public void imprimir() {
        System.out.println("Fila: ");
        System.out.println("Servidores: " + servidores);
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Min Arrival: " + MinArrival);
        System.out.println("Max Arrival: " + MaxArrival);
        System.out.println("Min Service: " + MinService);
        System.out.println("Max Service: " + MaxService);
        System.out.println("Customer Count: " + customerCount);
        System.out.println("Loss Count: " + lossCount);
        System.out.println("Total Time: " + totalTime);
        System.out.println("Probabilidade de cada estado: ");
        for (int i = 0; i < capacidade; i++) {
            System.out.println("Estado " + i + ": " + tempo_estado[i]/totalTime * 100 + "%");
        }
    }

    



}
