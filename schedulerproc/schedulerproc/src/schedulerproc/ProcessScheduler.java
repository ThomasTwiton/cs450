package schedulerproc;
/**
 * @author yasiro01
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Process scheduler
 * 
 * readyQueue is a list of processes ready for execution
 * rrQuantum is the time quantum used by round-robin algorithm
 * add() and clear() are wrappers around ArrayList methods
 */
public class ProcessScheduler {
    private final ArrayList<SimpleProcess> readyQueue;
    private final int rrQuantum;

    public ProcessScheduler() {
        this.readyQueue = new ArrayList<>();
        this.rrQuantum = 4;
    }

    public void add(SimpleProcess newProcess) {
        this.readyQueue.add(newProcess);
    }

    public void clear() {
        this.readyQueue.clear();
    }
    
    public int size() {
       return this.readyQueue.size();
    }
    
    /**
     * FCFS scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useFirstComeFirstServe() {
        int num_processes = this.size();
        double wait_time = 0.0;
        double sum = 0.0;
        for(int i = 0; i < num_processes; i++){
            sum += wait_time;
            System.out.print(sum + "\n");
            wait_time += this.readyQueue.get(i).getNextBurst();
        }
        return sum / num_processes; 
    }

    /**
     * SJF scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useShortestJobFirst() {
        int num_processes = this.size();
        double wait_time = 0.0;
        double sum = 0.0;
        
        Comparator<SimpleProcess> compareByBurst = new Comparator<SimpleProcess>() {
        @Override
        public int compare(SimpleProcess o1, SimpleProcess o2) {
            double burst1 = o1.getNextBurst();
            double burst2 = o2.getNextBurst();
            if (burst1 < burst2) {return -1;}
            if (burst1 == burst2) {return 0;}
            return 1;
        }
        };
        
        this.readyQueue.sort(compareByBurst);
        
        for(int i = 0; i < num_processes; i++){
            sum += wait_time;
            wait_time += this.readyQueue.get(i).getNextBurst();
            System.out.print(sum + "\n");
        }
        return sum / num_processes;
    }

    /**
     * Priority scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double usePriorityScheduling() {
        int num_processes = this.size();
        double wait_time = 0.0;
        double sum = 0.0;
        
        Comparator<SimpleProcess> compareByPriority = new Comparator<SimpleProcess>() {
        @Override
        public int compare(SimpleProcess o1, SimpleProcess o2) {
            int prior1 = o1.getPriority();
            int prior2 = o2.getPriority();
            if (prior1 < prior2) {return -1;}
            if (prior1 == prior2) {return 0;}
            return 1;
        }
        };
        
        this.readyQueue.sort(compareByPriority);
        
        for(int i = 0; i < num_processes; i++){
            sum += wait_time;
            wait_time += this.readyQueue.get(i).getNextBurst();
            System.out.print(sum + "\n");
        }
        return sum / num_processes;
    }

    /**
     * Round-Robin scheduling algorithm implementation
     * 
     * @return average waiting time for all processes
     */
    public double useRoundRobin() {
        int num_processes = this.size();
        int original_num_processes = num_processes;
        double wait_time = 0.0;
        double sum = 0.0;
        
        int i = 0;
        
        while(i<num_processes){
                       
            SimpleProcess next = this.readyQueue.get(i);
            i++;
            if (next.getTimeLeft() > this.rrQuantum && num_processes-1>i){
                next.reduceTimeLeft(this.rrQuantum);
                this.readyQueue.add(next);
                num_processes += 1;
                
                wait_time += this.rrQuantum;
            } else {
                sum += wait_time;
                wait_time += next.getTimeLeft();
            }           
            System.out.print(sum + "\n");
            
        }
        return sum / original_num_processes; 
    }
}
