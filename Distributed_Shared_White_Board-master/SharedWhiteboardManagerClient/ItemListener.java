/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;  
  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.util.ArrayList;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;


public class ItemListener implements ActionListener{  
      
    public DrawUI paint; 
    public ClientControl control;
      
    //constructor  
    public ItemListener(DrawUI paint, ClientControl control){
        this.paint=paint;
        this.control = control;
          
    }  
    //add the action   
    public void actionPerformed(ActionEvent e) {  
          
        //get the button that is pressed  
        String command =e.getActionCommand();  
          
// create canvas
        if("new".equals(command)){  
              
            int value=JOptionPane.showConfirmDialog(null, "Do you need to save current file£¿", "message", 0);  
            if(value==0){  
                  
                saveFile();  
            }  
            if(value==1){  
              
                try {
                    paint.repaint();
                    this.paint.control.sendData(20, 20, 20, 20, 20, 20, "123", "123", "123");
                    
                } catch (Exception ex) {
                    Logger.getLogger(ItemListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }  
        
        else if("open".equals(command)) {
            int value = JOptionPane.showConfirmDialog(null, "Do you need to save file£¿", "message", 0);
            if (value == 0) {

                saveFile();
            }
            if (value == 1) {

                try {
                   
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File file = chooser.getSelectedFile();
                    if (file == null) {
                        JOptionPane.showMessageDialog(null, "Did not select file");
                    } else {
                       
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                      
                        ArrayList<Shape> list = (ArrayList<Shape>) ois.readObject();
                      
                        for (int i = 0; i < list.size(); i++) {
                            Shape shape = (Shape) list.get(i);
                            this.paint.control.graphList.add(shape);
                            this.paint.control.g.setStroke(new BasicStroke(1));
                            if (shape.type == 1) {
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            else if (shape.type == 2) {
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawRect(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            else if (shape.type == 3) {
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawOval(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            else if (shape.type == 4) {
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawRoundRect(shape.x1, shape.y1, shape.x2, shape.y2, 50, 50);
                            }
                            else if (shape.type == 5) {
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            // Eraser
                            else if (shape.type == 6){
                                Color temp = control.g.getColor();
                                this.paint.control.g.setColor(Color.white);
                                this.paint.control.g.setStroke(new BasicStroke(15));
                                this.paint.control.g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
                                this.paint.control.g.setColor(temp);
                            }
                            //brush
                            else if (shape.type == 7){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.setStroke(new BasicStroke(15));
                                this.paint.control.g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
                                this.paint.control.g.setStroke(new BasicStroke(1));
                            }
                            //pengqiang
                            else if (shape.type == 8){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            // draw filled rectangle
                            else if (shape.type == 9){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.fillRect(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            //draw filled oval
                            else if (shape.type == 10){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.fillOval(shape.x1, shape.y1, shape.x2, shape.y2);
                            }
                            //draw filled round rectangle
                            else if (shape.type == 11){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.fillRoundRect(shape.x1, shape.y1, shape.x2, shape.y2,50,50);
                            }
                            //draw String
                            else if (shape.type == 12){
                                this.paint.control.g.setColor(new Color(shape.color));
                                this.paint.control.g.drawString(shape.text, shape.x1, shape.y1);
                            }
                            this.paint.control.sendData(shape.type,shape.x1,shape.y1,shape.x2,shape.y2,shape.color,shape.text,"","");
                        }
                        ois.close();
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            }
        }else if("save".equals(command)){
              
              
            saveFile();  
              
              
        }
        else if ("save as".equals(command)){
            saveAs(paint.centerpanel);
        }
      
    }  
    
    public void saveAs(JPanel panel){
  
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        double x = panel.getLocationOnScreen().getX();
        int x1 = (int) x;
        double y = panel.getLocationOnScreen().getY();
        int y1 = (int) y;
        int height = panel.getHeight();
        int width = panel.getWidth();
        Rectangle rect = new Rectangle(x1,y1,width,height);
        BufferedImage bi = robot.createScreenCapture(rect);  
    File f=new File("saveScreen.jpg");  
    try {  
        ImageIO.write(bi, "jpg", f);
    } catch (IOException e) {  
        e.printStackTrace();  
    }
    }
      
    public void saveFile(){   
        JFileChooser chooser = new JFileChooser();  
        chooser.showSaveDialog(null);  
        File file =chooser.getSelectedFile();  
          
        if(file==null){  
            JOptionPane.showMessageDialog(null, "Haven't selected files");  
        }else {  
  
            try {    
                FileOutputStream fis = new FileOutputStream(file);  
                ObjectOutputStream oos = new ObjectOutputStream(fis);
                oos.writeObject(control.graphList); 
                JOptionPane.showMessageDialog(null, "save successfully£¡");  
                oos.close();  
            } catch (Exception e1) {  
                e1.printStackTrace();  
            }  
        }  
    }  
  
}  