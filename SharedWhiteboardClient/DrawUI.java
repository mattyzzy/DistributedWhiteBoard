/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NormalClient;
 
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.FlowLayout;  
import java.awt.Graphics2D;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.*;
import javax.swing.ButtonGroup;  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JMenu;  
import javax.swing.JMenuBar;  
import javax.swing.JMenuItem;  
import javax.swing.JPanel;  
import javax.swing.JRadioButton;  
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;   
import javax.swing.JFrame;  
import NormalClient.ClientControl;
import java.awt.HeadlessException;


/**
 *
 * @author 54185
 */
public class DrawUI extends JFrame {  
    public Color c=Color.RED;  
    public Socket socket;  
    public Graphics2D g;  
    public ClientControl control;  
    public JButton  bt ;
    public JPanel centerpanel = new JPanel();
    public JTextField chatfield;
    public JButton sendbutton;
    public JTextArea textarea1;
    public JTextArea textarea2;
    public String username;
    public String ipaddress;
    public int port;
    public String response;
    
    
       
    public DrawUI() {
        while (true) {

            try {
                String setusername = JOptionPane.showInputDialog("Please enter username");
                username = setusername;
                String ip = JOptionPane.showInputDialog("Please enter server's ip address");
                ipaddress = ip;
                String setport = JOptionPane.showInputDialog("Please enter server's port number:");
                port = Integer.parseInt(setport);

                socket = new Socket(ipaddress, port);
                InputStream ins = socket.getInputStream();
                OutputStream ous = socket.getOutputStream();
                DataInputStream dis = new DataInputStream(ins);
                DataOutputStream dos = new DataOutputStream(ous);
                dos.writeUTF(username);
                dos.flush();
                String valid = dis.readUTF();
                if (valid.equals("Username invalid.")) {
                    JOptionPane.showMessageDialog(null, "Invalid username. Try again.");
                }
                else {
                    String result = dis.readUTF();
                    System.out.println(result);
                    if (result.equals("Connection established.")) {
                        response = "Y";
                    }
                    else {
                        response = "N";
                    }
                    break;
                }
            } catch (HeadlessException | IOException | NumberFormatException e) {
                System.out.println("Connection failed");
                JOptionPane.showMessageDialog(null, "Connection failed.");
                System.exit(0);
            }
        }
    }
      
    public void initFrame() throws Exception {  
        //set the frame propoerty 

        this.setTitle("Shared Whiteboard"+" "+username);  //set title
        this.setSize(1200, 600);     // set the frame size
        this.setDefaultCloseOperation(3); //set the close method       
        this.setLocationRelativeTo(null); 
        this.setResizable(false);

        
        
        addMenu();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        this.add(panel);
        
        
        centerpanel.setLayout(new BorderLayout());
        centerpanel.setBackground(Color.white);
        panel.add(centerpanel);
        
        
        // add the left panel
        JPanel panelleft = new JPanel();
        panelleft.setPreferredSize(new Dimension(50,0));
        panelleft.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelleft.setBackground(new Color(235,233,238));
        
        
        ButtonGroup bg = new ButtonGroup();
        for(int i=0;i<16;i++){
            JRadioButton jrb = new JRadioButton();
            ImageIcon img1 = new ImageIcon(getClass().getResource("/images/draw"+i+".jpg"));
            ImageIcon img2  = new ImageIcon(getClass().getResource("/images/draw"+i+"-1.jpg"));
            ImageIcon img3  = new ImageIcon(getClass().getResource("/images/draw"+i+"-2.jpg"));  
            ImageIcon img4  = new ImageIcon(getClass().getResource("/images/draw"+i+"-3.jpg"));  
            jrb.setIcon(img1);  
            jrb.setRolloverIcon(img2);  
            jrb.setPressedIcon(img3);  
            jrb.setSelectedIcon(img4);  
            jrb.setBorder(null);  
            if(i==10){  
                jrb.setSelected(true);  
            }  
            jrb.setActionCommand("pic"+i);  
              
            bg.add(jrb);  
            panelleft.add(jrb); 
        }
        BevelBorder bb = new BevelBorder(0, Color.gray,Color.white);  
        BevelBorder bb1 = new BevelBorder(1, Color.gray,Color.white);  
          
        JPanel left = new JPanel();  
        left.setBackground(Color.white);  
        left.setLayout(null);  
        left.setBorder(bb);  
        left.setPreferredSize(new Dimension(40,40));  
         
        bt = new JButton();  
        bt.setBounds(5, 5, 20, 20);  
        bt.setBorder(bb1);  
        bt.setBackground(Color.black);  
        bt.setSize(20,20);  
        JButton bt1 = new JButton();  
        bt1.setBorder(bb1);  
        bt1.setBounds(15,15,20,20);  
        left.add(bt);  
        left.add(bt1);  
          
        //add right panel
        JPanel right = new JPanel();  
        right.setBackground(Color.BLUE);  
        right.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));  
        right.setPreferredSize(new Dimension(40,240));  
        panelleft.add(left);  
        panelleft.add(right);   
        
         ButtonListener bl =new ButtonListener(this);
         Color []colors = {new Color(0,56,67),new Color(89,3,14),new Color(189,3,14)  
                ,new Color(89,93,14),new Color(89,113,14),new Color(89,73,14)  
                ,new Color(89,3,14),new Color(89,3,14),new Color(29,83,14)  
                ,new Color(89,3,184),new Color(189,233,14),new Color(89,253,14)  
                ,new Color(89,93,14),new Color(89,89,94),new Color(1,3,14)  
                ,new Color(9,83,94),new Color(89,178,147),new Color(9,33,164)  
                ,new Color(34,23,14),new Color(89,173,154),new Color(8,193,194)  
                ,new Color(9,253,76),new Color(89,240,104),new Color(199,73,4)};  
          
        //add buttons  
        for(int i=0;i<24;i++){  
            JButton bt3 = new JButton();  
            Color c=new Color(i*10,30-i,i*7+50);  
            bt3.setBackground(colors[i]);  
            bt3.setPreferredSize(new Dimension(20,20));  
            bt3.setBorder(bb);  
            bt3.addActionListener(bl);  
            right.add(bt3);  
        } 
        panel.add(panelleft,BorderLayout.WEST);
        
        //add chatbox UI
        JPanel chatbox = new JPanel();
        chatbox.setLayout(new BorderLayout());
        chatbox.setPreferredSize(new Dimension(300,0));
        
        JPanel buttonpanel = new JPanel();
        buttonpanel.setPreferredSize(new Dimension(0,60));
        textarea1 = new JTextArea();
        textarea1.setEditable(false);
        textarea1.setLineWrap(true);
        textarea2 = new JTextArea();
        textarea2.setEditable(false);
        textarea2.setLineWrap(true);
        JScrollPane scrollbar = new JScrollPane(textarea1);
        chatfield = new JTextField(25);
        sendbutton = new JButton();
        sendbutton.setText("Send");
        buttonpanel.add(chatfield);
        buttonpanel.add(sendbutton);
        buttonpanel.add(textarea2,BorderLayout.SOUTH);
        
        chatbox.add(scrollbar);
        chatbox.add(buttonpanel, BorderLayout.SOUTH);
        panel.add(chatbox, BorderLayout.EAST);
        
        this.setVisible(true);  
        //get draw pen 
        g = (Graphics2D)this.getGraphics();
        g.setColor(Color.RED);  
        //get controller  
        control = new ClientControl(g,socket,this);  
        //add drawlistener  
        DrawListener listener = new DrawListener(control,bg,this);  
        this.addMouseListener(listener);  
        this.addMouseMotionListener(listener); 
        chatboxListener chatlistener = new chatboxListener(this,control);
        sendbutton.addActionListener(chatlistener);
        chatboxwriteLis writelistener = new chatboxwriteLis(this,control);
        chatfield.addActionListener(writelistener);
        
    
        
        control.sendData(15, 1,1,1,1,1,""," has joined us.", username);
        //send data
        control.receiveData();
    } 
    
    
    
    public void addMenu() throws Exception{
        //add menu 
        JMenuBar bar = new JMenuBar();  
        JMenu menu=new JMenu("File");  
          
        ItemListener il=new ItemListener(this,control);  
        JMenuItem item2 = new JMenuItem("save as"); 
        item2.addActionListener(il);  
        this.setJMenuBar(bar);   
        bar.add(menu); 
        menu.add(item2);
    }
  
}  