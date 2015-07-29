package processsimulator;

import java.util.ArrayList;


public class ReadyQueue extends ArrayList<AProcess>{          // The class to store ready queue objects
    public AProcess remove(){
        AProcess highPriority = this.get(0);
        for(AProcess p:this){                                   // Selecting the highest priority process
            if(p.getPriority()==highPriority.getPriority()){      
                if(p.isIsComeFirst())
                    highPriority = p;                                    
            }else if(p.getPriority()>highPriority.getPriority()){
                highPriority = p;
            }
        }        
        return this.remove(this.indexOf(highPriority));
    }
   
    public AProcess pop(){            // Getting the first process from the ready queue
        return this.remove(0);
    }
}
