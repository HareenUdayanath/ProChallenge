/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameplate;

import PlayerRecords.Player;
import gameplate.Controller;

import gameplate.TicTacGame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 *
 * @author Asus
 */
public class NetworkedGame extends javax.swing.JFrame {

    /**
     * @return the player1
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * @param aPlayer1 the player1 to set
     */
    public static void setPlayer1(Player aPlayer1) {
        player1 = aPlayer1;
    }

    /**
     * @return the player2
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * @param aPlayer2 the player2 to set
     */
    public static void setPlayer2(Player aPlayer2) {
        player2 = aPlayer2;
    }

    
    
    private Controller myController;   
    private ArrayList<Integer> rest;
    private int simbleList[][]; 
    private int count;
 ;    
    private static Player player1;
    private static Player player2;
    private boolean isOtherFirst;
    private boolean isSingle;
    private NetworkedGame temp;
    private static Plate plate;
    private static boolean canClicked;
    
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static PrintWriter output = null;
    private static BufferedReader input = null;
    private static boolean isServer = false;
    private static String serverIP;    
    

  
    
    /*Newtwork.....................*/
    
    public static boolean startSerrver(int port){
        try {
            serverSocket = new ServerSocket(port);
            isServer = true;            
            
            serverIP = InetAddress.getLocalHost().getHostAddress();
            //System.out.println("IP of my system is := "+NetworkedGame.getServerIP());
            return true;
        } catch (IOException ex) {
            System.out.println("Sever cannot be created....");
            return false;
        }
    }
    
    public static boolean waitForClient(){
        System.out.println("Waitting for a another player...");
        try {
            socket = serverSocket.accept();            
        } catch (IOException ex) {
            System.out.println("Could not connect a player...");
            return false;
        }
        try {
            output = new PrintWriter(socket.getOutputStream(),true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            return false;
            //Logger.getLogger(GameNetworked.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public static boolean connect(String ip,int port){
        try {
            socket = new Socket(ip,port);
        } catch (UnknownHostException ex) {
            System.out.println("Cannot found host");
            return false;
        } catch (IOException ex) {
            System.out.println("Cannot connect");
            return false;
        }
        try {
            output = new PrintWriter(socket.getOutputStream(),true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            
            return false;
            //Logger.getLogger(GameNetworked.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return true;
    }
    
    public static void closeConnection(){
        try {
            socket.close();
            serverSocket.close();
            input.close();
            output.close();
        } catch (IOException ex) {
            
        }catch(java.lang.NullPointerException ex){}
    }
    
    
    
    /**
     * Creates new form G
     */
    public NetworkedGame() {
        initComponents();  
        pLable1.setText("dd");
        pLable2.setText("dd");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //this.setBounds(400, 100,this.getWidth(), this.getHeight());
        //this.setResizable(false);
        init();
        temp = this;
    }
    void init(){    
        
        /*serverSocket = null;
        socket = null;
        output = null;
        input = null;
        isServer = false;*/
        NetworkedGame.setCanClicked(true);
        
        simbleList = new int[3][3];
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent we){
                 NetworkedGame.closeConnection();
                 System.exit(0);
             }        
        });
        this.count = 2;     
        if(isServer)
            this.setIsOtherFirst(false);
        else
            this.setIsOtherFirst(true);
       
        if(this.isOtherFirst)
            this.tLable.setText(NetworkedGame.player2.getName()+"'s turn");
        else
            this.tLable.setText(NetworkedGame.player1.getName()+"'s turn");
        
        this.pLable1.setText(NetworkedGame.player1.getName()+" - "+NetworkedGame.player1.getWins()+" Wins");
        this.pLable2.setText(NetworkedGame.player2.getName()+" - "+NetworkedGame.player2.getWins()+" Wins");
      
        this.sLable.setText("Two Player Game");
        
           
        
        
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
        getPlate().drawSimble(num, isX);
        if(isX){
            setButtornColour(num,1);
        }else{
            setButtornColour(num,2);
        }
    }
    
    void buttonClicked(int but){        
       
        if(rest.remove(Integer.valueOf(but))&&canClicked){
        
            if((!isIsOtherFirst()&&count%2==0)||(isIsOtherFirst()&&count%2!=0)){
                doButton(but,true);             
                this.tLable.setText(NetworkedGame.player2.getName()+"'s turn");
            }else{
                doButton(but,false);            
                this.tLable.setText(NetworkedGame.player2.getName()+"'s turn");
            }

            count++;        

            output.println(but);


            if((myController.checher(simbleList)==-1)&&isSingle&&!isIsOtherFirst()){           
                if(count<11){   
                    //NetworkedGame.getPlate().setEnabled(false);
                    NetworkedGame.setCanClicked(false);
                    new Thread(){
                        public void run(){
                            int num = -1;                
                            try {                              
                                num = Integer.valueOf(input.readLine());
                            } catch (IOException ex) {
                           
                                JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" Other player has disconnected");
                                temp.dispose();
                            }catch(NumberFormatException ex){
                                temp.dispose();
                                TicTacGame.setIsOtherFirst(false);
                                new PlayerSelect().setVisible(true);
                            }   

                            if(num>-1){  
                                doButton(num,false);                           
                                tLable.setText(NetworkedGame.player1.getName()+"'s turn");
                                rest.remove(Integer.valueOf(num));                    
                                count++;
                            }

                            if(myController.checher(simbleList)>-1){ 
                                if(myController.checher(simbleList)==1){                            
                                    JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" is Win..");
                                    
                                    NetworkedGame.player1.setWins(1);
                                    NetworkedGame.player2.setLosses(1);
                                }else{
                                    JOptionPane.showMessageDialog(null,NetworkedGame.player2.getName()+" is Win..");
                                    
                                    NetworkedGame.player1.setWins(1);
                                    NetworkedGame.player2.setLosses(1);
                                }
                                temp.dispose();
                                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                                if(option==JOptionPane.OK_OPTION){
                                    if(TicTacGame.isIsOtherFirst()){
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(false);
                                            }
                                        }.start();                                    
                                    }else{
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(true);
                                            }
                                        }.start(); 
                                    }
                                }
                            }else if(count==11){            
                                JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" and "+NetworkedGame.player2.getName()+" are even....");
                                temp.dispose();            
                                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                                if(option==JOptionPane.OK_OPTION){
                                    if(TicTacGame.isIsOtherFirst()){
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(false);
                                            }
                                        }.start();                                    
                                    }else{
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(true);
                                            }
                                        }.start(); 
                                    }
                                }
                            }
                           //disableButtons(false); 
                            //NetworkedGame.getPlate().setEnabled(true);
                            NetworkedGame.setCanClicked(true);
                        }                   

                    }.start();

                }
            }else if((myController.checher(simbleList)==-1)&&isSingle&&isIsOtherFirst()){
                if(count<11){
                    //disableButtons(true);
                    //NetworkedGame.getPlate().setEnabled(false);
                    NetworkedGame.setCanClicked(false);
                    new Thread(){
                        public void run(){
                            int num = -1;                
                            try {                               
                                num = Integer.valueOf(input.readLine());
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" Other player has disconnected");
                                temp.dispose();
                            }catch(NumberFormatException ex){
                                temp.dispose();
                                TicTacGame.setIsOtherFirst(false);
                                new PlayerSelect().setVisible(true);
                            }   

                            if(num>-1){   
                                doButton(num,false);                           
                                tLable.setText(NetworkedGame.player1.getName()+"'s turn");
                                rest.remove(Integer.valueOf(num));                    
                                count++;
                            }
                             if(myController.checher(simbleList)>-1){ 
                                if(myController.checher(simbleList)==1){                            
                                    JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" is Win..");
                                   
                                    NetworkedGame.player1.setWins(1);
                                    NetworkedGame.player2.setLosses(1);
                                }else{
                                    JOptionPane.showMessageDialog(null,NetworkedGame.player2.getName()+" is Win..");
                                    
                                    NetworkedGame.player2.setWins(1);
                                    NetworkedGame.player1.setLosses(1);
                                }
                                temp.dispose();
                                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                                if(option==JOptionPane.YES_OPTION){
                                    if(TicTacGame.isIsOtherFirst()){
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(false);
                                            }
                                        }.start();                                    
                                    }else{
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(true);
                                            }
                                        }.start(); 
                                    }  
                                }else{
                                        NetworkedGame.closeConnection();
                                }                            
                            }else if(count==11){            
                                JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" and "+NetworkedGame.player2.getName()+" are even....");
                                temp.dispose();            
                                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                                if(option==JOptionPane.YES_OPTION){
                                    if(TicTacGame.isIsOtherFirst()){
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(false);
                                            }
                                        }.start();                                    
                                    }else{
                                        new Thread(){
                                            public void run(){
                                                TicTacGame.newNetGame(true);
                                            }
                                        }.start(); 
                                    }
                                }else{
                                    NetworkedGame.closeConnection();
                                }                            
                            } 
                            //disableButtons(false);
                            //NetworkedGame.getPlate().setEnabled(true);
                            NetworkedGame.setCanClicked(true); 
                        }

                    }.start();

                }
            }
            if(myController.checher(simbleList)>-1){ 
                if(myController.checher(simbleList)==1){
                    //output.println("Won");
                    JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" is Win..");
                    
                    NetworkedGame.player1.setWins(1);
                    NetworkedGame.player2.setLosses(1);
                }else{
                    JOptionPane.showMessageDialog(null,NetworkedGame.player2.getName()+" is Win..");
                    
                    NetworkedGame.player2.setWins(1);
                    NetworkedGame.player1.setLosses(1);
                }
                this.dispose();
                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.YES_OPTION){
                    if(TicTacGame.isIsOtherFirst())
                        TicTacGame.newNetGame(false);
                    else
                        TicTacGame.newNetGame(true);  
                }else{
                    NetworkedGame.closeConnection();
                }
            }else if(count==11){            
                JOptionPane.showMessageDialog(null,NetworkedGame.player1.getName()+" and "+NetworkedGame.player2.getName()+" are even....");
                this.dispose();            
                int option = JOptionPane.showConfirmDialog(null, "Do you want a new Game..?", "New Game", JOptionPane.YES_NO_OPTION);
                if(option==JOptionPane.YES_OPTION){
                    if(TicTacGame.isIsOtherFirst())
                        TicTacGame.newNetGame(false);
                    else
                        TicTacGame.newNetGame(true); 
                }else{
                    NetworkedGame.closeConnection();
                }
            }
        }
    } 
    
    public void startTheOtherPlayer(){   
        NetworkedGame.setCanClicked(false);
        int num = -1;  
        try {               
            num = Integer.valueOf(input.readLine());
        }catch(IOException ex) {           
            this.dispose();
        }catch(NumberFormatException ex){            
            this.dispose();
            TicTacGame.setIsOtherFirst(false);
            new PlayerSelect().setVisible(true);
        }   
       
        doButton(num,false);       
        rest.remove(Integer.valueOf(num));  
        count++;
        NetworkedGame.setCanClicked(true);
          
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
        pLable3 = new javax.swing.JLabel();
        pLable4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tLable = new javax.swing.JLabel();
        sLable = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pLable1 = new javax.swing.JLabel();
        pLable2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NewG = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        pLable3.setText("jLabel1");

        pLable4.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLable3)
                    .addComponent(pLable4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(pLable3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pLable4)
                .addGap(33, 33, 33))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        tLable.setText("jLabel1");

        sLable.setBackground(new java.awt.Color(51, 51, 0));
        sLable.setFont(new java.awt.Font("Trajan Pro", 3, 11)); // NOI18N
        sLable.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tLable))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(sLable)))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(sLable)
                .addGap(9, 9, 9)
                .addComponent(tLable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        pLable1.setText("jLabel1");

        pLable2.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLable1)
                    .addComponent(pLable2))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(pLable1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pLable2)
                .addGap(33, 33, 33))
        );

        jMenu1.setText("File");

        NewG.setText("New Game");
        NewG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGActionPerformed(evt);
            }
        });
        jMenu1.add(NewG);

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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGActionPerformed
        new PlayerSelect().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_NewGActionPerformed

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
            java.util.logging.Logger.getLogger(NetworkedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetworkedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetworkedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetworkedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetworkedGame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem NewG;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel pLable1;
    private javax.swing.JLabel pLable2;
    private javax.swing.JLabel pLable3;
    private javax.swing.JLabel pLable4;
    private javax.swing.JLabel sLable;
    private javax.swing.JLabel tLable;
    // End of variables declaration//GEN-END:variables

    
    // Getters and seters...................................................
    
    

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
     * @return the isOtherFirst
     */
    public boolean isIsOtherFirst() {
        return isOtherFirst;
    }

    /**
     * @param isOtherFirst the isOtherFirst to set
     */
    public void setIsOtherFirst(boolean isOtherFirst) {
        this.isOtherFirst = isOtherFirst;
    }

    /**
     * @return the mouseAdapter
     */
    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }
    
       /**
     * @return the serverIP
     */
    public static String getServerIP() {
        return serverIP;
    }

   
    /**
     * @return the plate
     */
    public static Plate getPlate() {
        return plate;
    }

    /**
     * @param aPlate the plate to set
     */
    public static void setPlate(Plate aPlate) {
        plate = aPlate;
    }

    /**
     * @param aCanClicked the canClicked to set
     */
    public static void setCanClicked(boolean aCanClicked) {
        canClicked = aCanClicked;
    }
    
    

 

   
}
