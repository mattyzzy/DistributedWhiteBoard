/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NormalClient;

import javax.swing.*;

/**
 *
 * @author 54185
 */
public class ClientTest {

    ClientControl control;

    public static void main(String[] args) throws Exception {  
        DrawUI ui = new DrawUI();
        if (ui.response.equals("Y")) {
            ui.initFrame();
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Connection Failed.", "Error", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

    }  
}  