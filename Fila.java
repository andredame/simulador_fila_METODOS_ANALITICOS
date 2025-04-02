import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


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

    public Fila(int servidores, int capacidade, double MinArrival, double MaxArrival, double MinService, double MaxService) {
        this.servidores = servidores;
        this.capacidade = capacidade;
        this.MinArrival = MinArrival;
        this.MaxArrival = MaxArrival;
        this.MinService = MinService;
        this.MaxService = MaxService;
        this.customerCount = 0;
        this.lossCount = 0;
        this.totalTime = 0.0;
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

    public void setServidores(int servidores) {
        this.servidores = servidores;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public double getMinArrival() {
        return MinArrival;
    }
    public void setMinArrival(double minArrival) {
        MinArrival = minArrival;
    }
    public double getMaxArrival() {
        return MaxArrival;
    }
    public void setMaxArrival(double maxArrival) {
        MaxArrival = maxArrival;
    }
    public double getMinService() {
        return MinService;
    }
    public void setMinService(double minService) {
        MinService = minService;
    }
    public double getMaxService() {
        return MaxService;
    }
    public void setMaxService(double maxService) {
        MaxService = maxService;
    }
    public int getCustomerCount() {
        return customerCount;
    }
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
    public int getLossCount() {
        return lossCount;
    }
    public void setLossCount(int lossCount) {
        this.lossCount = lossCount;
    }
    public double getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
    
    



}
