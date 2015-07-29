package processsimulator;


import interfaces.SimulatorInterface;


public class ProcessSimulator {

    public static void main(String[] args) {
        
        start();   
        
    }
    public static void start(){
        SimulatorInterface si = new SimulatorInterface();
        si.setVisible(true);        
        SimulatorInterface.proRepTable.drawScreen(0);        // Drawing the process simulation diagram
        SimulatorInterface.queueRep.drawQueue("readyQ");     // Drawing the ready queue
    }
}
