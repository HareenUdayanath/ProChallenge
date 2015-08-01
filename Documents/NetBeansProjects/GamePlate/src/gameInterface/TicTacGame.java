package gameInterface;

import PlayerRecords.DataBase;
import org.apache.log4j.Logger;

public class TicTacGame {
    
    private static boolean isOtherFirst;
    private static boolean isSingle;
    private static Controller controller;
    private static int difficulty;
 
    private static Game game;
    private static NetworkedGame networkedGame;
    public static DataBase db;
    
    private static Logger logger = Logger.getLogger(TicTacGame.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       start();
          
    }
    public static void start(){
        db = new DataBase();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        } catch (InstantiationException ex) {
            logger.error(ex.getMessage());
        } catch (IllegalAccessException ex) {
            logger.error(ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.error(ex.getMessage());
        }
        controller = new Controller(); 
        isOtherFirst = false;      
       
        new PlayerSelect().setVisible(true); 
    
    }
    static void newGame(boolean isCF){
        game = new Game();                
        Plate p = new Plate();
        p.setBounds(0,0, game.getWidth(),game.getHeight()); 
        p.addMouseListener(game.getMouseAdapter());       
        game.add(p);   
        game.setPlate(p);
        
        setIsOtherFirst(isCF);        
        game.setIsComFirst(isIsOtherFirst());
        game.setIsSingle(isIsSingle());
        game.setMyController(controller);
        game.setDifficulty(difficulty);
        controller.setPlayerFirstBut(-1);      
        
        
        game.setVisible(true);
        p.drawImage("BB.png");
        p.drawBoard();   
        
        new Thread(){
            public void run(){
                if(isIsOtherFirst()&&isIsSingle()){             
                    game.comStart();
                }
            }
        }.start(); 
    }
    static void newNetGame(boolean isCF){
        networkedGame = new NetworkedGame();       
        Plate p = new Plate();
        p.setBounds(0,0, networkedGame.getWidth(),networkedGame.getHeight()); 
        p.addMouseListener(networkedGame.getMouseAdapter());
        networkedGame.add(p);
        networkedGame.setPlate(p);
        
        setIsOtherFirst(isCF);        
        networkedGame.setIsOtherFirst(isIsOtherFirst());
        setIsSingle(true);
        networkedGame.setIsSingle(true);       
        networkedGame.setMyController(controller);       
        controller.setPlayerFirstBut(-1);
        
        networkedGame.setVisible(true);
        
        p.drawImage("BB.png");        
        p.drawBoard(); 
        if(isIsOtherFirst()&&isIsSingle()){              
            new Thread(){
                public void run(){
                    networkedGame.startTheOtherPlayer();
                }
            }.start();
        }
    }
    
    
    /**
     * @param aIsSingle the isSingle to set
     */
    public static void setIsSingle(boolean aIsSingle) {
        isSingle = aIsSingle;
    }

    /**
     * @param aDifficulty the difficulty to set
     */
    public static void setDifficulty(int aDifficulty) {
        difficulty = aDifficulty;
    }

    /**
     * @return the isComFirst
     */
    public static boolean isIsOtherFirst() {
        return isOtherFirst;
    }

    /**
     * @param aIsComFirst the isComFirst to set
     */
    public static void setIsOtherFirst(boolean aIsComFirst) {
        isOtherFirst = aIsComFirst;        
    }

    /**
     * @return the isSingle
     */
    public static boolean isIsSingle() {
        return isSingle;
    }
}
