package processsimulator;


import interfaces.SimulatorInterface;


public class ProcessSimulator {

    public static void main(String[] args) {
        
        SimulatorInterface si = new SimulatorInterface();
        si.setVisible(true);        
        SimulatorInterface.proRepTable.drawScreen(0);
        SimulatorInterface.queueRep.drawQueue("readyQ");
        /*ReadyQueue q = new ReadyQueue();
        q.pop();*/
        
        /*JFrame f = new JFrame();
        f.setResizable(false);
        Chart c = new Chart();
        c.setBounds(0, 80, 1000,600);        
        f.setBounds(10, 80, 1000, 600);
        f.add(c);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);        
        c.drawScreen();*/
        //FullRep r = new FullRep();
        
        /*Chart1 c = new Chart1();
        c.setBounds(0, 80, 900,620);
        r.add(c);
        
        r.setVisible(true);
        
        c.drawAt(0,220, "0");
        c.drawAt(80,20, "sim2");
        c.pQueueToRQueue("C");
        
        for(int i=0; i<380 ;i++){
            Chart1 cc = new Chart1();
            cc.setBounds(0, 80, 900,620);
            r.add(cc);
            r.setVisible(true);
            cc.drawAt(0,220,"sim2");
        
        }
        
        */
        
        
    }
}
