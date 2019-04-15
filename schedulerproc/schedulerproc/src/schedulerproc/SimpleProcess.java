package schedulerproc;
/**
 * @author yasiro01
 */

/**
 * Simple process
 * 
 * nextBurst - next burst of execution of the process
 * priority - process priority
 * arrivaltime - time of arrival into the queue (not used)
 */
public class SimpleProcess {
    private final int nextBurst;
    private final int priority;
    private final int arrivalTime;
    
    //keep track how much more time a process needs if it gets preempted. 
    private int timeLeft;

    public SimpleProcess(int nextBurst, int priority, int arrivalTime) {
        this.nextBurst = nextBurst;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.timeLeft = nextBurst;
    }

    public int getNextBurst() {
        return this.nextBurst;
    }
    
    public int getTimeLeft() {
        return this.timeLeft;
    }
    
    public void reduceTimeLeft(int i) {
        this.timeLeft -= i;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }
    
}
