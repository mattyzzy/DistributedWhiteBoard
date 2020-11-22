/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;  
  
import java.awt.Color;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.JButton;  
  
//add button listener 
public class ButtonListener implements ActionListener{  
      
      
    public DrawUI db; 
    

    
  
    //constructor 
    public ButtonListener(DrawUI db1) {  
        db=db1; 
        
        
    }  
  
    //listener handler  
    public void actionPerformed(ActionEvent e) {  
          
        //get the selected button  
        JButton bt =(JButton)e.getSource(); 
       
        //get the color of selected button  
        Color c= bt.getBackground();  
        // set the pen color and leftpanel color selection color
        db.bt.setBackground(c);
        db.control.g.setColor(c);
        
          
    }  
  
}  