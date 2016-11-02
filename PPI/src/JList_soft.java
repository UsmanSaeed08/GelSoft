/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */


import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ppi.*;

public class JList_soft extends JFrame {
        JList list;
	Container contentpane;
	public JList_soft() {
                //super("List Source Demo");
                
	}

        public int index;
        public int flag;
        private String[] mw_pi;
        private JFrame f1;
       Network nt;


        public int x = 1;



        public JList_soft(final Protein arr[], Network n) {
                super("GelSoft Protein List 3D");
nt=n;

		contentpane = getContentPane();
		contentpane.setLayout(new FlowLayout());
                mw_pi = new String[arr.length];

                for (int i=0; i <= arr.length-1; i++){
                    mw_pi[i] = ("Protein id = " + arr[i].seq_id + ";    mw = " + ((float)arr[i].mw/1000) + ";     pi = " + (float)arr[i].pi);
                }

                list = new JList(mw_pi);

		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentpane.add(new JScrollPane(list));
                flag =0;
                
                
                /////////////////////MAKE THE 3D FRAME AND EVERYTHING//////////////////////

                x = 0;
                f1 = new JFrame();
                

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
                                    
                                    flag = 1;
                                    index = list.getSelectedIndex();
                                    System.out.println("selectd indx is: "+index+" while total proteins are: "+arr.length);
                                    display(arr,nt);         
			}
		});

		setSize(600, 200);
		setVisible(true);
	}

        public void display (Protein arr[],Network nt){
            f1.dispose();
            f1 = new JFrame();
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GraphPanel graphpanel = new GraphPanel(arr,nt);
            graphpanel.make_everything(index, 0);

            f1.getContentPane().add(graphpanel);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setSize(1600,860);
            f1.setLocation(0,0);
            f1.setVisible(true);
        }

}
