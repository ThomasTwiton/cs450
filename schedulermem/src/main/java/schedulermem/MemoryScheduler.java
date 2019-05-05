package schedulermem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Twiton
 */
public class MemoryScheduler {

    private int pageFaultCount;
    private ArrayList<String> frames;

    public MemoryScheduler(int frames) {
        this.pageFaultCount = 0;
        this.frames = new ArrayList<String>();
        for(int i =0;i<frames;i++){
            this.frames.add("_a");
        }
    }



    public int getPageFaultCount() {
        return this.pageFaultCount;
    }

    public void useFIFO(String referenceString) {
        int nextFrame= 0;
        String[] addresses = referenceString.split(",");

        for(String addr : addresses){
            if(! this.frames.contains(addr)){
                this.pageFaultCount += 1;
                this.frames.set(nextFrame, addr);
                nextFrame += 1;
                if(nextFrame>this.frames.size()-1){
                    nextFrame = 0;
                }
            }                      
        }

    }

    public void useOPT(String referenceString) {
        int nextFrame= 0;
        String[] addresses = referenceString.split(",");

        for(int i=0; i<addresses.length;i++){
            String addr = addresses[i];
            if(! this.frames.contains(addr)){
                this.pageFaultCount += 1;
                
                if(this.frames.contains("_a")){
                    this.frames.set(nextFrame, addr);  
                    nextFrame += 1;
                    if(nextFrame>this.frames.size()-1){
                        nextFrame = 0;
                    }
                } else{
                    ArrayList<String> memory = new ArrayList<String>(this.frames);
                    int forwardtrace = i;
                    String last_used = memory.get(0);
                    while(memory.size()>0 && forwardtrace < addresses.length){
                        if(memory.contains(addresses[forwardtrace])){
                            last_used = addresses[forwardtrace];
                            memory.remove(addresses[forwardtrace]);
                        }
                        forwardtrace += 1;
                    }
                    if(memory.size()>0){
                        last_used = memory.get(0);
                    }
                    nextFrame = this.frames.indexOf(last_used);
                    this.frames.set(nextFrame, addr);  
                }                  
                //System.out.println(this.frames);        
            }
        }    
    }

    public void useLRU(String referenceString) {
        int nextFrame= 0;
        String[] addresses = referenceString.split(",");

        for(int i=0; i<addresses.length;i++){
            String addr = addresses[i];
            if(! this.frames.contains(addr)){
                this.pageFaultCount += 1;
                
                if(this.frames.contains("_a")){
                    this.frames.set(nextFrame, addr); 
                    nextFrame += 1;
                    if(nextFrame>this.frames.size()-1){
                        nextFrame = 0;
                    }
                } else{
                    ArrayList<String> memory = new ArrayList<String>(this.frames);
                    int backtrace = i;
                    String last_used = memory.get(0);
                    while(memory.size()>0){
                        if(memory.contains(addresses[backtrace])){
                            last_used = addresses[backtrace];
                            memory.remove(addresses[backtrace]);
                        }
                        backtrace -= 1;
                    }
                    nextFrame = this.frames.indexOf(last_used);
                    this.frames.set(nextFrame, addr); 
                }
                           
            }
            //System.out.println(this.frames);   
        }      
                   
    }
}
