package gameInterface;


import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Plate extends Canvas implements MouseListener{ 
    
    int simbleList[][];     
    
    
    
    public void drawImage(String name){
        
            Graphics2D h = (Graphics2D) this.getGraphics();          
            BufferedImage image=null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/"+name));            
            } catch (IOException ex) {
                System.out.println("dasdasd");
            }       
            h.drawImage(image, 0, 0,this);       
            Toolkit.getDefaultToolkit().sync();
            h.dispose();   
            
    }
    
    
   
    public void drawBoard(){
        
            Graphics2D h = (Graphics2D) this.getGraphics();          
            BufferedImage image=null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/Board2.png"));            
            } catch (IOException ex) {
                System.out.println("dasdasd");
            }       
            h.drawImage(image, -5, 80,this);       
            Toolkit.getDefaultToolkit().sync();
            h.dispose();   
            
    }
    public void drawX(int x,int y){
        
            Graphics2D h = (Graphics2D) this.getGraphics();             
            BufferedImage image=null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/iiX.png"));            
            } catch (IOException ex) {
                System.out.println("dasdasd");
            }       
            h.drawImage(image, x-5,y+80,this);       
            Toolkit.getDefaultToolkit().sync();
            h.dispose();
            
    }
    
    public void drawO(int x,int y){
        
            Graphics2D h = (Graphics2D) this.getGraphics();            
            BufferedImage image=null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/res/iO.png"));            
            } catch (IOException ex) {
                System.out.println("dasdasd");
            }       
            h.drawImage(image, x-5,y+80,this);       
            Toolkit.getDefaultToolkit().sync();
            h.dispose();
            
    }
    
    public void drawSimble(int num,boolean isX){    
        switch(num){
            case 1: if(isX)drawX(38,30);
                    else drawO(38,30);break;
            case 2: if(isX)drawX(138,30);
                    else drawO(138,30);break;
            case 3: if(isX)drawX(238,30);
                    else drawO(238,30);break;
            case 4: if(isX)drawX(38,132);
                    else drawO(38,132);break;
            case 5: if(isX)drawX(138,132);
                    else drawO(138,132);break;
            case 6: if(isX)drawX(238,132);
                    else drawO(238,132);break;
            case 7: if(isX)drawX(38,230);
                    else drawO(38,230);break;
            case 8: if(isX)drawX(138,230);
                    else drawO(138,230);break;
            case 9: if(isX)drawX(238,230);
                    else drawO(238,230);break;                                           
        }
    }   
    
    private int getBut(int x,int y){
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

    @Override
    public void mouseClicked(MouseEvent e) {        
        if(getBut(e.getX(),e.getY())==1)
            drawSimble(1,true);
        else if(getBut(e.getX(),e.getY())==2)
            drawSimble(2,false);
        else if(getBut(e.getX(),e.getY())==3)
            drawSimble(3,true);
        else if(getBut(e.getX(),e.getY())==4)
            drawSimble(4,false);
        else if(getBut(e.getX(),e.getY())==5)
            drawSimble(5,true);
        else if(getBut(e.getX(),e.getY())==6)
            drawSimble(6,false);
        else if(getBut(e.getX(),e.getY())==7)
            drawSimble(7,true);
        else if(getBut(e.getX(),e.getY())==8)
            drawSimble(8,false);
        else if(getBut(e.getX(),e.getY())==9)
            drawSimble(9,true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
