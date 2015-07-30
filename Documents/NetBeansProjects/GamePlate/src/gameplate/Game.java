package gameplate;

import PlayerRecords.Player;
import gameplate.Controller;
import gameplate.TicTacGame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


public class Game extends javax.swing.JFrame {

    /**
     * @return the p1
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * @param aP1 the p1 to set
     */
    public static void setPlayer1(Player aP1) {
        player1 = aP1;
    }

    /**
     * @return the p2
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * @param aP2 the p2 to set
     */
    public static void setPlayer2(Player aP2) {
        player2 = aP2;
    }

   

   
    
    private Controller myController;   
    private int simbleList[][];    
    private ArrayList<Integer> rest;
    private int difficulty;
    private int count;   
    private static Player player1;
    private static Player player2;
    private boolean isComFirst;
    private boolean isSingle;
    private Plate plate;
    public Plate background;
    public static int num;
    
    

   

    /**
     * Creates new form Frame
     */
    public Game() {
        initComponents();
        setBackground(Color.WHITE);
        init();
        if(isComFirst)
            this.tLable.setText(Game.player2.getName()+"'s turn");
        else
            this.tLable.setText(Game.player1.getName()+"'s turn");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setBounds(400, 100,this.getWidth(), this.getHeight());
        System.out.println(this.getWidth()+" "+this.getHeight());
        this.addWindowListener(windowAdapter);
        this.setResizable(false);
    }
    void init(){   
        
        //...........................
       /* background = new Plate();
        background.setBounds(0,0, this.getWidth(),this.getHeight());
        System.out.println("1");
        this.add(background);
        background.drawImage("back");*/
        
        //...........................
        this.count = 2;      
        simbleList = new int[3][3];
        this.setIsComFirst(false);    
        
        this.pLable1.setText(Game.player1.getName()+" - "+Game.player1.getWins()+" Wins");
        this.pLable2.setText(Game.player2.getName()+" - "+Game.player2.getWins()+" Wins");
      
        if(TicTacGame.isIsSingle()){
            this.sLable.setText("Single Player Game");
        }else{
            this.sLable.setText("Two Player Game");
        }
       
        
        rest = new ArrayList<>();
        for(int i=1;i<10;i++){
            rest.add(i);
        }           
        
    }
    
    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {        
            if(getBut(e.getX(),e.getY())==1)
                buttonClicked(1);
            else if(getBut(e.getX(),e.getY())==2)
                buttonClicked(2);
            else if(getBut(e.getX(),e.getY())==3)
                buttonClicked(3);
            else if(getBut(e.getX(),e.getY())==4)
                buttonClicked(4);
            else if(getBut(e.getX(),e.getY())==5)
                buttonClicked(5);
            else if(getBut(e.getX(),e.getY())==6)
                buttonClicked(6);
            else if(getBut(e.getX(),e.getY())==7)
                buttonClicked(7);
            else if(getBut(e.getX(),e.getY())==8)
                buttonClicked(8);
            else if(getBut(e.getX(),e.getY())==9)
                buttonClicked(9);
            
        }
    
    };
    
    private final WindowAdapter windowAdapter = new WindowAdapter() {

        @Override
        public void windowClosed(WindowEvent e) {
            if(PlayerSelect.shouldSave){
                TicTacGame.db.addPalyer(player1);
                TicTacGame.db.addPalyer(player2);
            }            
        }
        
    };
    
    public void setButtornColour(int button,int colour) {
        if(button==1)
            simbleList[0][0] = colour;
        else if(button==2)
            simbleList[0][1] = colour;
        else if(button==3)
            simbleList[0][2] = colour;
        else if(button==4)
            simbleList[1][0] = colour;
        else if(button==5)
            simbleList[1][1] = colour;
        else if(button==6)
            simbleList[1][2] = colour;
        else if(button==7)
            simbleList[2][0] = colour;
        else if(button==8)
            simbleList[2][1] = colour;
        else
            simbleList[2][2] = colour;        
    }
     
    private int getBut(int x,int y){
        x-=(-5);
        y-=80;
        if(x<120&&x>32&&y<111&&y>24)
            return 1;
        if(x<219&&x>132&&y<111&&y>24)
            return 2;
        if(x<320&&x>232&&y<111&&y>24)
            return 3;
        if(x<120&&x>32&&y<210&&y>126)
            return 4;
        if(x<219&&x>132&&y<210&&y>126)
            return 5;
        if(x<320&&x>232&&y<210&&y>126)
            return 6;
        if(x<120&&x>32&&y<311&&y>227)
            return 7;
        if(x<219&&x>132&&y<311&&y>227)
            return 8;
        if(x<320&&x>232&&y<311&&y>227)
            return 9;
        return 0;
    }
    public void doButton(int num,boolean isX){        
       plate.drawSimble(num, isX);
       if(isX){
           setButtornColour(num,1);
       }else{
           setButtornColour(num,2);
       }
    }
    void buttonClicked(int but){        
        if(rest.remove(Integer.valueOf(but))){
            if(!isSingle){            
                if(!isComFirst){
                    if(count%2==0){
                        doButton(but,true);                  
                        this.tLable.setText(Game.player2.getName()+"'s turn");
                    }else{
                        doButton(but,false);                    
                        this.tLable.setText(Game.player1.getName()+"'s turn");
                    }
                }else{
                    if(count%2!=0){
                        doButton(but,true);                   
                        this.tLable.setText(Game.player2.getName()+"'s turn");
                    }else{
                        doButton(but,false);                  
                        this.tLable.setText(Game.player1.getName()+"'s turn");
                    }
                }
            }else{
                if((!isComFirst&&count%2==0)||(isComFirst&&count%2!=0)){
                    doButton(but,true);                       
                    this.tLable.setText(Game.player2.getName()+"'s turn");
                }else{
                    doButton(but,false);              
                    this.tLable.setText(Game.player2.getName()+"'s turn");
                }
            }      
            count++;     

            if((myController.checher(simbleList)==-1)&&isSingle&&!isComFirst){
                if(count<11){

                    //int num;
                    if(difficulty == 3)
                        num = myController.impossible1(simbleList,rest,but,count);  
                    else if(difficulty == 2)
                        num = myController.hard1(simbleList,rest,but,count);
                    else
                        num = myController.easy(rest);

                    if(num>-1){ 
                        plate.setEnabled(false);  
                        try {
                            Thread.sleep(2000);
                            plate.setEnabled(true);
                        } catch (InterruptedException ex) {
                            plate.setEnabled(true);
                        }

                        doButton(num,false);  
                        tLable.setText(Game.player1.getName()+"'s turn");
                        rest.remove(Integer.valueOf(num));                    
                        count++;
                              
                    }
                }
            }else if((myController.checher(simbleList)==-1)&&isSingle&&isComFirst){
                if(count<11){
                   
                    if(difficulty == 3)
                        num = myController.impossible2(simbleList,rest,but,count);  
                    else if(difficulty == 2)
                        num = myController.hard2(simbleList,rest,but,count);
                    else
                        num = myController.easy(rest);

                    if(num>-1){ 
                        plate.setEnabled(false);
                        try {
                            Thread.sleep(2000);
                            plate.setEnabled(true);
                        } catch (InterruptedException ex) {
                            plate.setEnabled(true);
                        }

                        doButton(num,false);  
                        tLable.setText(Game.player1.getName()+"'s turn");
                        rest.remove(Integer.valueOf(num));                    
                        count++;
                                  
                    }
                }
            }
            if(myController.checher(simbleList)>-1){ 
                if(myController.checher(simbleList)==1){
                    JOptionPane.showMessageDialog(null,Game.player1.getName()+" is Win..");
                   
                    Game.player1.setWins(1);
                    Game.player2.setLosses(1);
                }else{
                    JOptionPane.showMessageDialog(null,Game.player2.getName()+" is Win..");
                    
                    Game.player2.setWins(1);
                    Game.player1.setLosses(1);
                }            
                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.OK_OPTION){
                    if(TicTacGame.isIsOtherFirst()){
                        new Thread(){
                            public void run(){
                                TicTacGame.newGame(false);
                            }
                        }.start();                                    
                    }else{
                        new Thread(){
                            public void run(){
                                TicTacGame.newGame(true);
                            }
                        }.start(); 
                    }
                }
                this.dispose();
            }else if(count==11){            
                JOptionPane.showMessageDialog(null,Game.player1.getName()+" and "+Game.player2.getName()+" are even....");
                Game.player1.setTies(1);
                Game.player2.setTies(1);
                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.OK_OPTION){
                    if(TicTacGame.isIsOtherFirst()){
                        new Thread(){
                            public void run(){
                                TicTacGame.newGame(false);
                            }
                        }.start();                                    
                    }else{
                        new Thread(){
                            public void run(){
                                TicTacGame.newGame(true);
                            }
                        }.start(); 
                    }
                }
                this.dispose(); 
            }
        }
    } 
    
    public void comStart(){
        if(difficulty==3){   
            plate.setEnabled(false);                   
            tLable.setText(Game.player2.getName()+"'s turn");
            new Thread(){
                public void run(){

                    try {
                        Thread.sleep(2000);
                        plate.setEnabled(true);
                    } catch (InterruptedException ex) {
                        plate.setEnabled(true);
                    }

                    doButton(5,false);  
                    tLable.setText(Game.player1.getName()+"'s turn");
                    rest.remove(Integer.valueOf(5));                    
                    count++;
                }
            }.start();   
            
        }else if(difficulty==2){ 
            Random randomCreater = new Random();
            int arr[] = {1,3,9,7,5};         
            int but = arr[randomCreater.nextInt(5)];
            doButton(but,false);          
            rest.remove(Integer.valueOf(but));  
            count++;
        }else{           
            int num = myController.easy(rest);
            doButton(num,false);          
            rest.remove(Integer.valueOf(num));  
            count++;
        }            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pLable1 = new javax.swing.JLabel();
        pLable2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tLable = new javax.swing.JLabel();
        sLable = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        pLable1.setText("jLabel1");

        pLable2.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLable1)
                    .addComponent(pLable2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(pLable1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pLable2)
                .addGap(33, 33, 33))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        tLable.setText("jLabel1");

        sLable.setBackground(new java.awt.Color(51, 51, 0));
        sLable.setFont(new java.awt.Font("Trajan Pro", 3, 11)); // NOI18N
        sLable.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tLable))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(sLable)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(sLable)
                .addGap(9, 9, 9)
                .addComponent(tLable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setText("New Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        TicTacGame.start();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel pLable1;
    private javax.swing.JLabel pLable2;
    private javax.swing.JLabel sLable;
    private javax.swing.JLabel tLable;
    // End of variables declaration//GEN-END:variables
    
        /**
     * @param isComFirst the isComFirst to set
     */
    public void setIsComFirst(boolean isComFirst) {
        this.isComFirst = isComFirst;
    }

    /**
     * @param isSingle the isSingle to set
     */
    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    /**
     * @param myController the myController to set
     */
    public void setMyController(Controller myController) {
        this.myController = myController;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the mouseAdapter
     */
    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }

    /**
     * @param plate the plate to set
     */
    public void setPlate(Plate plate) {
        this.plate = plate;
    }
}
