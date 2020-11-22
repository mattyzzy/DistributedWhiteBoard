/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NormalClient;
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 54185
 */
public class DrawListener extends MouseAdapter {  
      
    public int x1,y1,x2,y2,x4,y4;  
    public ClientControl control;  
    public ButtonGroup bg;
    public String command;
    public DrawUI paint;
    
   
      
    //constructor get the graphics object 
    public DrawListener(ClientControl control, ButtonGroup bg2, DrawUI paint) {  
        this.control=control; 
        this.paint=paint;       
        bg=bg2;
        this.control.g.setColor(Color.red);
    }  
  
    //mouse press handler
    public void mousePressed(MouseEvent e) {  
        //get the mouse press zuobiao. 
     
        ButtonModel bm = bg.getSelection();
        command = bm.getActionCommand();
        x1=e.getX();  
        y1=e.getY();  
        x4=x1;  
        y4=y1;
        
    } 
    
    @Override
    public void mouseReleased(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();
        //draw lines
        if("pic10".equals(command)){
            
            control.g.drawLine(x1, y1, x2, y2);
            control.sendData(1, x1, y1, x2, y2, control.g.getColor().getRGB(),""," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
       }
        //draw rectangle
        else if("pic12".equals(command)){
            
            
            if(x1<x2){
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.drawRect(x1, y1, x3, y3);
            control.sendData(2, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else {
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.drawRect(x2, y2, x3, y3);
                control.sendData(2, x2, y2, x3, y3,control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
        }
        //draw Oval
        else if("pic14".equals(command)){            
            if(x1<x2){
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.drawOval(x1, y1, x3, y3);
            control.sendData(3, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else{
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.drawOval(x2, y2, x3, y3);
                control.sendData(3, x2, y2, x3, y3, control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
           
        }
        else if("pic5".equals(command)){
            int x3 =(x1-x2)*(x1-x2);
            int y3 = (y1-y2)*(y1-y2);
            double length = Math.sqrt(y3+x3);
            int length2 = (int)length;
            control.g.fillOval(x1, y1, length2, length2);
            control.sendData(16, x1, y1, length2, length2, control.g.getColor().getRGB(), "", "", "");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
        }
        
        else if("pic13".equals(command)){
            int x3 =(x1-x2)*(x1-x2);
            int y3 = (y1-y2)*(y1-y2);
            double length = Math.sqrt(y3+x3);
            int length2 = (int)length;
            control.g.drawOval(x1, y1, length2, length2);
            control.sendData(14, x1, y1, length2, length2, control.g.getColor().getRGB(), "", "", "");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
        }
        
        else if("pic15".equals(command)){
            if(x1<x2){
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.drawRoundRect(x1, y1, x3, y3,50,50);
            control.sendData(4, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else{
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.drawRoundRect(x2, y2, x3, y3, 50, 50);
                control.sendData(4, x2, y2, x3, y3, control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
        }
        else if ("pic4".equals(command)){
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            try{
                Robot robot = new Robot();
                Rectangle rect = new Rectangle(x,y,1,1);
                BufferedImage bi = robot.createScreenCapture(rect);
                int c = bi.getRGB(0, 0);
                Color color = new Color(c);
                control.g.setColor(color);
                
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }
        // draw filled rectangel
        else if ("pic3".equals(command)){
             if(x1<x2){
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.fillRect(x1, y1, x3, y3);
            control.sendData(9, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else {
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.fillRect(x2, y2, x3, y3);
                control.sendData(9, x2, y2, x3, y3,control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
        }
        else if ("pic0".equals(command)){
            if(x1<x2){
            
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.fillOval(x1, y1, x3, y3);
            control.sendData(10, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else{
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.fillOval(x2, y2, y3, y3);
                control.sendData(10, x2, y2, x3, y3,control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
        }
        else if ("pic1".equals(command)){
            if(x1<x2){
            int x3 = x2-x1;
            int y3 = y2-y1;
            control.g.fillRoundRect(x1, y1, x3, y3,50,50);
            control.sendData(11, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            }
            else{
                int x3 = x1-x2;
                int y3 = y1-y2;
                control.g.fillRoundRect(x2, y2, x3, y3, 50, 50);
                control.sendData(11, x2, y2, x3, y3, control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
            }
        }
        else if ("pic9".equals(command)){
            String msg = JOptionPane.showInputDialog("Please enter the text you want to draw");
            control.g.drawString(msg, x1, y1);
            control.sendData(12, x1, y1, 0, 0,control.g.getColor().getRGB(), msg," ","");
            control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
            paint.textarea2.setText(paint.username+" is drawing");
            
        }
        
    }
    
    @Override
    public void mouseDragged(MouseEvent e2){
        //free hand pen
        if("pic6".equals(command)){
        int x3 = e2.getX();
        int y3 = e2.getY();
        control.g.drawLine(x1,y1,x3,y3);
        control.sendData(5, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
        control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
        paint.textarea2.setText(paint.username+" is drawing");
        x1 = x3;
        y1 = y3;
       
        }
        //eraser
        else if ("pic2".equals(command)){
            Color temp = control.g.getColor();
            control.g.setColor(Color.white);
            control.g.setStroke(new BasicStroke(15));
             int x3 = e2.getX();
             int y3 = e2.getY();
             control.g.drawLine(x1, y1, x3, y3);
             control.sendData(6, x1, y1, x3, y3, 1," "," ","");
             control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
             paint.textarea2.setText(paint.username+" is drawing");
             x1 = x3;
             y1 = y3;
            control.g.setColor(temp); // when eraser has been used. set back the original color
        }
        //brush
        else if ("pic7".equals(command)){           
             control.g.setStroke(new BasicStroke(15));
             int x3 = e2.getX();
             int y3 = e2.getY();
             control.g.drawLine(x1, y1, x3, y3);
             control.sendData(7, x1, y1, x3, y3,control.g.getColor().getRGB()," "," ","");
             control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
             paint.textarea2.setText(paint.username+" is drawing");
             x1 = x3;
             y1 = y3;
             control.g.setStroke(new BasicStroke(1));
        }
        //pengqiang
        else if ("pic8".equals(command)){
            Random r = new Random();           
            for (int i = 0; i<30; i++){
                int xplus = r.nextInt(31)-15;
                int yplus = r.nextInt(31)-15;
                
                int x3 = e2.getX();
                int y3 = e2.getY();
                control.g.drawLine(x1+xplus, y1+yplus, x3+xplus, y3+yplus);
                control.sendData(8, x1+xplus, y1+yplus, x3+xplus, y3+yplus,control.g.getColor().getRGB()," "," ","");
                control.sendData(17, 0, 0, 0, 0, 0, "", "", paint.username);
                paint.textarea2.setText(paint.username+" is drawing");
                x1 = x3;
                y1 = y3;
            }
        }
      
}
}


