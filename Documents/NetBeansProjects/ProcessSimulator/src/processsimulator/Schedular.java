package processsimulator;

import java.util.ArrayList;


public class Schedular {
    private ArrayList<AProcess> newProcessList;
    private AProcess[] processList;
    private ReadyQueue readyQueue;
    private int currentTime;
    private AProcess next;
    private int pTime;                       // The time that a process is able to access the processor at a single time
    
    
    public Schedular(ArrayList<AProcess> processList){
        this.newProcessList = processList;
        this.processList = new AProcess[processList.size()];
        for(int i=0;i<processList.size();i++){
            this.processList[i] = processList.get(i);
        }
        this.currentTime = 0;
        this.createReady();
        this.next = null;
    }
    
    // The Highest Response Ratio Next Algorithm .............................................
    
    public AProcess hrrn(){
        if(this.getReadyQueue().size()!=0){
            next = this.getReadyQueue().remove();        // Getting the first process from the ready queue
            this.currentTime+=next.getServiceTime();
            next.setExcutedTime(next.getServiceTime());

            for(AProcess p:this.newProcessList){                   //Checking for the suitable processes to add to ready queue
                if(p.getArrivalTime()<=this.getCurrentTime()){                
                    this.getReadyQueue().add(p);         
                }
            }
            for(AProcess p:this.getReadyQueue()){                  // Set the priorities according to response ratio
                p.setPriority(giveRatio(p, this.getCurrentTime()));            
                this.newProcessList.remove(p);            
            }
         }else{                // When the ready queue is empty...
            int count = 0;
            while(true){
                this.currentTime++;     
                count++;
                if(this.newProcessList.size()==0)
                    break;
                for(AProcess p:this.newProcessList){                   
                    if(p.getArrivalTime()<=this.getCurrentTime()){ 
                        p.setProcessWaitTime(count);
                        this.getReadyQueue().add(p);                // Removing the process fro process queue and adding it to the ready queue
                        this.newProcessList.remove(p);
                        return hrrn();                     // Calling the method again for the non-empty ready queue  
                    }
                }                
            }
        }     
        return next;
    }    
     
     // The Highest Response Ratio Next preemptive algorithm....................................
    
    public AProcess hrrnPree(){
        if(this.getReadyQueue().size()!=0){ 
            next = this.getReadyQueue().remove();
            this.currentTime+=pTime;
            next.setExcutedTime(next.getExcutedTime()+pTime);      

            for(AProcess p:this.newProcessList){                     //Checking for the suitable processes to add to ready queue
                if(p.getArrivalTime()<=this.getCurrentTime()){                 
                    this.getReadyQueue().add(p);         
                }
            }
            for(AProcess p:this.getReadyQueue()){
                p.setIsComeFirst(true);
                p.setPriority(giveRatioPree(p, this.getCurrentTime()));           // Set priority to each process 
                this.newProcessList.remove(p);            
            }

            if(next.getExcutedTime()<next.getServiceTime()){
                next.setIsComeFirst(false);
                next.setArrivalTime(getCurrentTime());
                next.setPriority(giveRatioPree(next, this.getCurrentTime()));
                this.getReadyQueue().add(next);
            }

            return next;
       }else{                   // If the ready queue is empty..........
            int count = 0;
            while(true){
                this.currentTime++;     
                count++;
                if(this.newProcessList.size()==0)
                    break;
                for(AProcess p:this.newProcessList){                   
                    if(p.getArrivalTime()<=this.getCurrentTime()){ 
                        p.setProcessWaitTime(count);
                        this.getReadyQueue().add(p);
                        this.newProcessList.remove(p);         // Removing the process fro process queue and adding it to the ready queue
                        return hrrnPree();                    
                    }
                }               
                
            }
        }   
        return null;
    }   
    
    //  The Round Robin Algorithm.............................................
    
    public AProcess roundRobin(){        
        if(this.getReadyQueue().size()!=0){            
            next = this.getReadyQueue().pop();
            
            this.currentTime+=pTime;
            next.setExcutedTime(next.getExcutedTime()+pTime);      
                     
            for(AProcess p:this.newProcessList){
                System.out.println(p.getArrivalTime());
                if(p.getArrivalTime()<=this.getCurrentTime()){                 
                    this.getReadyQueue().add(p);                       // Adding processes to the ready queue
                }
            }
            for(AProcess p:this.getReadyQueue()){                 
                this.newProcessList.remove(p);            
            }
            System.out.println(next.getExcutedTime());
            if(next.getExcutedTime()<next.getServiceTime()){                   
                this.getReadyQueue().add(next);
            }        
            return next;
        }else{          
            int count = 0;
            while(true){
                this.currentTime++;     
                count++;
                if(this.newProcessList.size()==0)
                    break;
                for(AProcess p:this.newProcessList){                   
                    if(p.getArrivalTime()<=this.getCurrentTime()){ 
                        p.setProcessWaitTime(count);
                        this.getReadyQueue().add(p);
                        this.newProcessList.remove(p);
                        return roundRobin();                    
                    }
                }               
                
            }
        }   
        return null;
    }   
    
    // The First Come First Serve algorithm...............................................
    
    public AProcess firstComeFirstServe(){
        if(this.getReadyQueue().size()!=0){
            next = this.getReadyQueue().pop();
            this.currentTime+=next.getServiceTime();  
            next.setExcutedTime(next.getServiceTime());

            for(AProcess p:this.newProcessList){
                if(p.getArrivalTime()<=this.getCurrentTime()){                 
                    this.getReadyQueue().add(p);                   // Add processses to the ready queue  
                }
            }
            for(AProcess p:this.getReadyQueue()){               
                this.newProcessList.remove(p);            
            }            
            return next;
        }else{    
            int count=0;
            while(true){
                this.currentTime++;
                 count++;
                if(this.newProcessList.size()==0)
                    break;
                for(AProcess p:this.newProcessList){
                    System.out.println(p.getArrivalTime()+"    "+this.currentTime);
                    if(p.getArrivalTime()<=this.currentTime){            
                         System.out.println("dddd  "+  count);
                        p.setProcessWaitTime(count);
                        this.getReadyQueue().add(p);
                        this.newProcessList.remove(p);
                        return firstComeFirstServe();                    
                    }
                }
            }
        } 
        return null;
    }   
    
    public double getThroughput(){                  // Getting the throughput
        int count = 0;
        for(AProcess p:this.getProcessList()){
            if(p.getExcutedTime()>=p.getServiceTime())
                count++;
        }
        return ((float)count)/this.getCurrentTime();
    }
    
    public double[] getPercentages(){                   // Getting the percentage of completence of a process at a given time 
        double[] presentages = new double[this.getProcessList().length];
        int count = 0;
        for(AProcess p:this.getProcessList()){
            if(p.getExcutedTime()<=p.getServiceTime())
                presentages[count++] = (((double)p.getExcutedTime())/p.getServiceTime())*100;
            else
                presentages[count++] = 100;
        }
        return presentages;
    }
    
    private double giveRatio(AProcess p,int time){         // Get the HRRN ratio
        int waitedTime = time-p.getArrivalTime();
        double serviceTime = (double)p.getServiceTime();
        return (waitedTime+serviceTime)/(serviceTime);
    }
    
    private double giveRatioPree(AProcess p,int time){         // Get the HRRN preemptive ratio
        int waitedTime = time-p.getArrivalTime();
        double remainingTime = (double)(p.getServiceTime()-p.getExcutedTime());
        return (waitedTime+remainingTime)/(remainingTime);
    }
        
    private void createReady(){                  // Creating the ready queue
        this.readyQueue = new ReadyQueue();
        for(AProcess p:this.newProcessList){
            if(p.getArrivalTime()==0){
                this.getReadyQueue().add(p);
                this.newProcessList.remove(p);
                break;
            }
                
        }        
    }

    /**
     * @return the readyQueue
     */
    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    /**
     * @return the pTime
     */
    public int getpTime() {
        return pTime;
    }

    /**
     * @param pTime the pTime to set
     */
    public void setpTime(int pTime) {
        this.pTime = pTime;
    }

    /**
     * @return the processList
     */
    public AProcess[] getProcessList() {
        return processList;
    }

    /**
     * @return the currentTime
     */
    public int getCurrentTime() {
        return currentTime;
    }
    
}
