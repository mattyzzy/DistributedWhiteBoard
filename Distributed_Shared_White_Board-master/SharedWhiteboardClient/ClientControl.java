/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NormalClient;
import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;  
import java.awt.Graphics2D;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author 54185
 */
public class ClientControl {  
      
    private Socket socket;  
    public int x1,y1,x2,y2,color;  
    public byte type,strock; 
    public String drawtext,chatmessage,username;
    public static ArrayList<Shape> graphList = new ArrayList<Shape>();
    public DrawUI paint;
    
  
    public Graphics2D g;  
      
    public ClientControl(Graphics2D g,Socket socket,DrawUI paint) {  
        this.g=paint.g;  
        this.socket=socket;  
        this.paint = paint;
    }  
  
    //method to receive data from server.  
    public void receiveData(){  
        new Thread(){  
            public void run() {  
                try {
                        InputStream ins = socket.getInputStream();
                        OutputStream ous=socket.getOutputStream();
                    //get input stream and package them into data input stream.
                        DataInputStream dis=new DataInputStream(ins);
                        DataOutputStream dos= new DataOutputStream(ous);

                    while(true){
                        type=dis.readByte();    
                        x1=dis.readInt();  
                        y1=dis.readInt();  
                        x2=dis.readInt();  
                        y2=dis.readInt();  
                        color=dis.readInt(); 
                        drawtext = dis.readUTF();
                        chatmessage = dis.readUTF();
                        username =dis.readUTF();
                        drawGra();  
                    }

                } catch (Exception e) {  
                  
                    JOptionPane.showMessageDialog(null, "Connection Failed");
                }  
            };  
        }.start();  
    }  
      
    //method used to send data  
    public void sendData(int type,int x1,int y1,int x2,int y2,int color,String msg, String chatmessage,String username){  
        try {  
            //get inputstream
            InputStream ins = socket.getInputStream();  
            OutputStream ous=socket.getOutputStream();  
            //get dataoutput stream 
            DataInputStream dis=new DataInputStream(ins);  
            DataOutputStream dos= new DataOutputStream(ous);   
            dos.writeByte(type);  
            dos.writeInt(x1);  
            dos.writeInt(y1);  
            dos.writeInt(x2);  
            dos.writeInt(y2);
            dos.writeInt(color); 
            dos.writeUTF(msg);
            dos.writeUTF(chatmessage);
            dos.writeUTF(username);
           
            dos.flush();

            Shape shape = new Shape(type,x1,y1,x2,y2,color,msg);
            graphList.add(shape);

        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
      
    public void drawGra() throws Exception {  
        g.setColor(new Color(color));  
        g.setStroke(new BasicStroke(strock)); 
        if(type==1){  
            g.drawLine(x1, y1, x2, y2); 
              Shape shape = new Shape(1,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }  
        //draw rectangle
        else if (type == 2){
            g.drawRect(x1, y1, x2, y2);
              Shape shape = new Shape(2,x1,y1,x2,y2,color,"");
            graphList.add(shape);
            
        }
        // draw oval
        else if (type == 3){
            g.drawOval(x1, y1, x2, y2);
              Shape shape = new Shape(3,x1,y1,x2,y2,color,"");
            graphList.add(shape);
            
        }
        // draw round rectangle
        else if (type == 4){
            
            g.drawRoundRect(x1, y1, x2, y2,50,50);
              Shape shape = new Shape(4,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        // draw freehand pen
        else if (type == 5){
            g.drawLine(x1,y1,x2,y2);
              Shape shape = new Shape(5,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        // Eraser
        else if (type == 6){
            Color temp = g.getColor();
            g.setColor(Color.white);
            g.setStroke(new BasicStroke(15));
            g.drawLine(x1, y1, x2, y2);
              Shape shape = new Shape(6,x1,y1,x2,y2,color,"");
            graphList.add(shape);
            g.setColor(temp);
        }
        //brush
        else if (type == 7){
            
            g.setStroke(new BasicStroke(15));
            g.drawLine(x1, y1, x2, y2);
            g.setStroke(new BasicStroke(1));
              Shape shape = new Shape(7,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        //pengqiang
        else if (type == 8){
            g.drawLine(x1, y1, x2, y2);
              Shape shape = new Shape(8,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        // draw filled rectangle
        else if (type == 9){
            g.fillRect(x1, y1, x2, y2);
              Shape shape = new Shape(9,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        //draw filled oval
        else if (type == 10){
            g.fillOval(x1,y1,x2,y2);
              Shape shape = new Shape(10,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        //draw filled round rectangle
        else if (type == 11){
            g.fillRoundRect(x1, y1, x2, y2,50,50);
              Shape shape = new Shape(11,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        //draw String
        else if (type == 12){
            g.drawString(drawtext, x1, y1);
              Shape shape = new Shape(1,x1,y1,0,0,color,drawtext);
            graphList.add(shape);
        }
        // show message
        else if (type == 13){
            paint.textarea1.append(username+" says: "+"\n"+chatmessage+"\n");
        }
        else if(type == 14){
            g.drawOval(x1, y1, x2,y2);
              Shape shape = new Shape(14,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        //show message
        else if (type == 15) {
            paint.textarea1.append(username+chatmessage+"\n");
        }
        // draw filled circle
        else if (type == 16) {
            g.fillOval(x1, y1, x2,y2);
            Shape shape = new Shape(16,x1,y1,x2,y2,color,"");
            graphList.add(shape);
        }
        // show who is drawing
        else if(type == 17) {
            paint.textarea2.setText(username+" is drawing");
        }
        // create new canvas
        else if (type == 20){
            paint.repaint();
        }
        else if (type == 100) {
            JOptionPane.showMessageDialog(null, "You are kicked out. Have fun :)");
        }
    }  
  
}  

