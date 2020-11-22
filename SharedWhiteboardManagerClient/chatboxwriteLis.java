/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  

/**
 *
 * @author 54185
 */
public class chatboxwriteLis implements ActionListener {
    public DrawUI paint;
    public ClientControl control;
    
    public chatboxwriteLis(DrawUI paint, ClientControl control){
        this.paint = paint;
        this.control = control;
    }
    
    public void actionPerformed(ActionEvent e){
        String message = paint.chatfield.getText().trim();
        control.sendData(13, 0, 0, 0, 0, 0, " ",message,paint.username);
        paint.textarea1.append(paint.username+" says: "+"\n"+message+"\n");
        paint.chatfield.setText("");
    }
    
    
}
