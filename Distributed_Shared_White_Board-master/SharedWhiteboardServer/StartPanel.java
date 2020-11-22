package Server;

/**
 * Created by Shaun on 13/10/2017.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class StartPanel extends JPanel{

    public JTextField jtf;
    public JButton startBtn;
    public JButton stoptBtn;
    public JButton delButton;
    public boolean flag=true;
    public Myserver ms;
    public TablePanel tablePanel;

    public StartPanel(TablePanel tablePanel) {

        this.tablePanel=tablePanel;
        JLabel label =new JLabel("Port");
        jtf = new JTextField(10);
        startBtn = new JButton("Launch");
        startBtn.setEnabled(false);
        stoptBtn = new JButton("Off");
        stoptBtn.setEnabled(false);
        
        this.setBackground(Color.WHITE);
       
        this.setPreferredSize(new Dimension(0,50));
        this.setLayout(new FlowLayout());
        this.add(label);
        this.add(jtf);
        this.add(startBtn);
        this.add(stoptBtn);
      
        startBtn.addActionListener(al);
        stoptBtn.addActionListener(al);

       
        checkText();
    }

    ActionListener al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String command =e.getActionCommand();
            if(command.equals("Launch")){
             
                flag=false;
                int port =Integer.parseInt(jtf.getText());
                ms = new Myserver(port,tablePanel);
                stoptBtn.setEnabled(true);
                startBtn.setEnabled(false);
              
                new Thread(){
                    public void run() {
                        ms.startServer();
                    };
                }.start();

            }else if(command.equals("Off")){
                try {
               
                    while(ms.list.size()!=0) {
                        ms.list.get(0).socket.close();
                        ms.list.remove(0);
                    }
                   
                    ms.server.close();
                  
                    stoptBtn.setEnabled(false);
                    startBtn.setEnabled(true);
                } catch (IOException e1) {
                    System.out.println("eeeeeeeeee");
                    e1.printStackTrace();
                }

            }
        }
    };


    public void checkText(){
        new Thread(){
            public void run() {
                while(flag){
                  
                    String info=jtf.getText();
                  
                    if(!(info==null || info.equals(""))){
                        startBtn.setEnabled(true);
                    }

                    try {
                        this.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }


}

