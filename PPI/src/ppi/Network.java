/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Administrator
 */
public class Network {
    private ArrayList<Node> nodes;
    ArrayList<Sequence> seqID;
    ArrayList<Node> newNodes;
    DBHandler db;
  
   
    public Network(ArrayList<Node> node){
        db=new DBHandler();
      
       nodes=new ArrayList();
       setNodes(node);
       setPInteraction();
    }
    public Node getNodeAt(int indx){
        Node n=nodes.get(indx);
        return n;
    }
    /**
     * @return the nodes
     */
    public ArrayList getNodes() {
        return nodes;
    }
    public int getIndexOf(String s){
        for(int i=0;i<nodes.size();i++){
          if(nodes.get(i).getAccNo().equals(s)) {
              return i;
          }  
        }
        return -1;
    }
        public Double getsID(String s){
        for(int i=0;i<nodes.size();i++){
          if(nodes.get(i).getAccNo().equals(s)) {
              return nodes.get(i).getSeqID();
          }  
        }
        return -1.0;
    }
    public ArrayList<String> getIntList(int indx){
        
      ArrayList<Node> n= nodes.get(indx).getInteraction();
        ArrayList<String> accNo=new ArrayList();
        for(int i=0;i<n.size();i++){
            accNo.add(n.get(i).getAccNo());
        }
        return accNo;
    }

     public Node getNode(String acc){
        Node n=new Node();
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getAccNo().equals(acc)){
                n=nodes.get(i);
            }
        }
        return n;
    }
    /**
     * @param nodes the nodes to set
     */
    public void setNodes(ArrayList<Node> node1) {

    nodes=node1;
 
    nodes=db.getDomainList(nodes); 
    }
 
    public void setPInteraction(){
       
       db.findInteraction(nodes); //make a copy of array list
       db.start(); //do the find interaction thing
       int i =0;
       
       
       
       while(db.isAlive()){
           
       }
       
       nodes=db.getNetwork(nodes);
       /*
       JProgressBar progressBar = new JProgressBar(0, 100);
       progressBar.setValue(0);
       
        JFrame frmMain = new JFrame("LOADING");
        frmMain.setSize(300, 100); //Window size 300x100 pixels
        Container pane = frmMain.getContentPane();
        pane.setLayout(null); //Apply the null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit when X is clicked
        
        
        //Add components to pane
        
        pane.add(progressBar);
        //Position controls (X, Y, width, height)
        progressBar.setBounds(10, 10, 280, 20);
        progressBar.setBounds(100, 35, 100, 25);
        //Make frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(true);
       //Add action listeners
        //btnDo.addActionListener(new btnDoAction());
progressBar.setValue(db.progress);
       progressBar.setVisible(true);
       
       while(db.isAlive()){
       //progressBar.setStringPainted(true);
       
       }*/
       
       
    }
  
    public void displayNetwork(){
        System.out.println("Nodes Interactions");
             for( int i=0;i<nodes.size();i++){
                 System.out.println("Node:"+nodes.get(i).getSeqID());
                 ArrayList<Node> interaction=new ArrayList(nodes.get(i).getInteraction()); 
                 for(int j=0;j<interaction.size();j++){
                     System.out.println(interaction.get(j).getSeqID());
                 }
              }
        
    }

}
