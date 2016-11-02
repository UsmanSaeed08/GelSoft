
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class progress implements Runnable{
    
    JFrame fr;
    int seq;
    public progress(int c){
        this.seq = c;

    }
    @Override
    public void run (){
        
        /*
        fr = new JFrame("Loading");
               fr.setSize(400, 200);
               
               fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               fr.setLocationRelativeTo(null); // Middle of the screen
               
               JPanel p = new JPanel();
               
               fr.setLayout(new FlowLayout(FlowLayout.CENTER));
               
               JLabel label1 = new JLabel(seq + " Number of files detected, Now LOADING");
               label1.setVisible(true);
               fr.add(label1);
               fr.setVisible(true);
         * 
         */

  
        fr = new JFrame(seq + " Number of sequences detected, LOADING would take several minutes...");
        fr.setSize(600, 100);
               
               fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               fr.setLocationRelativeTo(null); // Middle of the screen
               fr.setLayout(new FlowLayout(FlowLayout.CENTER));
               
               JLabel label1 = new JLabel(seq + " Number of files detected, Now LOADING");
               label1.setVisible(true);
               fr.add(label1);
               fr.setVisible(true);
              
    }

    public void close(){
        fr.setVisible(false);
    }
}
