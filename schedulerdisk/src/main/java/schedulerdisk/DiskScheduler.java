package schedulerdisk;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author [name]
 */
public class DiskScheduler {

    private final int cylinders;
    private int currentCylinder;
    private int previousCylinder;
    private int totalMoves;

    public DiskScheduler(int cylinders, int currentCylinder, int previousCylinder) {
        this.cylinders = cylinders;
        this.currentCylinder = currentCylinder;
        this.previousCylinder = previousCylinder;
        this.totalMoves = 0;
    }

    public int getTotalMoves() {
        return this.totalMoves;
    }

    public void useFCFS(String requestQueue) {
        String[] rStrings = requestQueue.split(",");
        int[] requests = new int[rStrings.length];
        for(int i = 0; i<requests.length; i++){
            requests[i] = Integer.parseInt(rStrings[i]);
        }

        for(int i = 0; i<requests.length; i++){
            this.totalMoves += Math.abs(requests[i] - this.currentCylinder);
            this.currentCylinder = requests[i];
        }


    }

    public void useSSTF(String requestQueue) {
        String[] rStrings = requestQueue.split(",");
        ArrayList<Integer> requests = new ArrayList<Integer>();

        for(int i = 0; i<rStrings.length; i++){
            requests.add(Integer.parseInt(rStrings[i]));
        }   

        
        
        for(int i = 0; i<rStrings.length; i++){
            int closest = this.cylinders + 1;
            int closestindex = -1;
            for(int j = 0; j < requests.size(); j++){
                if(Math.abs(this.currentCylinder - requests.get(j))<Math.abs(this.currentCylinder - closest)){
                    closest = requests.get(j);
                    closestindex = j;
                }
            }
            requests.remove(closestindex);

            this.totalMoves += Math.abs(closest - this.currentCylinder);
            this.currentCylinder = closest;
        }
       
    }

    public void useLOOK(String requestQueue) {
        String[] rStrings = requestQueue.split(",");
        ArrayList<Integer> requests = new ArrayList<Integer>();

        for(int i = 0; i<rStrings.length; i++){
            requests.add(Integer.parseInt(rStrings[i]));
        }   
        
        int direction = 0;
        if(this.currentCylinder - this.previousCylinder > 0 ){
            //going right initially
            direction = 1;
        } else {
            //going left initially
            direction = -1;
        }
        this.previousCylinder = this.currentCylinder;

        while(requests.size() > 0){
            //if going right, check if we need to keep going right
            Collections.sort(requests);
            if(direction==1){
                if(this.currentCylinder > (requests.get(requests.size() -1)) || this.currentCylinder == this.cylinders){
                    direction = -1;
                    System.out.println("Turning around");
                }
            } //if going left, check if need to keep going left 
            else {
                if(this.currentCylinder < (requests.get(0)) || this.currentCylinder == 0){
                    direction = +1;
                    System.out.println("Turning around");
                }
            }
            
            this.currentCylinder = this.currentCylinder + direction;
            System.out.println(this.currentCylinder);
            System.out.println(requests);
            if(requests.contains(this.currentCylinder)){
                System.out.println("Found one");
                this.totalMoves += Math.abs(this.currentCylinder - this.previousCylinder);
                this.previousCylinder = this.currentCylinder;
                int index = requests.indexOf(this.currentCylinder);
                requests.remove(index);
            }
        }
    }

    public void useCLOOK(String requestQueue) {
        String[] rStrings = requestQueue.split(",");
        ArrayList<Integer> requests = new ArrayList<Integer>();

        for(int i = 0; i<rStrings.length; i++){
            requests.add(Integer.parseInt(rStrings[i]));
        }   
    }

}
