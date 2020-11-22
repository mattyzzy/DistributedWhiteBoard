package Server;

/**
 * Created by Shaun on 13/10/2017.
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class UIFrame extends JFrame {

    public TablePanel tablePanel;


    public static void main(String[] args) {
        UIFrame ui = new UIFrame();
        ui.initFrame();
    }

    public void initFrame() {
        
        this.setSize(600, 400);
        this.setTitle("Server");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        tablePanel = new TablePanel();

        StartPanel startPanel = new StartPanel(tablePanel);
        this.add(startPanel,BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
