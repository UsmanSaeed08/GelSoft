/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
//import com.sun.j3d.utils.image.TextureLoader;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.geometry.Box;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Text3D;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import ppi.*;
/**
 *
 * @author Mani
 */
class GraphPanel extends JPanel
                
{

    final int
            
        HPAD = 60,
        VPAD = 40;

    Font font;
    public Protein x[];
    public Network nt;
    public ArrayList<Line2D> lineArray=new ArrayList();
    private int [] marker;
    JPopupMenu pm;
    JPopupMenu pm2;
    Point2D p0;
    Point2D p01;
    int click=0;
    boolean highLight=false;
    JCheckBox chb=new JCheckBox();
int index;
    private javax.swing.JButton btnGetInteraction=new JButton("Go");
     private javax.swing.JButton btnSaveInteraction=new JButton("Save Interaction Network");
    private javax.swing.JComboBox cboxProtein=new JComboBox();
    private javax.swing.JLabel lbSelectProtein=new JLabel();
    private javax.swing.JPanel pnl=new JPanel();
       private javax.swing.JPanel pnl2=new JPanel();
       private javax.swing.JTextArea txtArea2=new JTextArea();
 javax.swing.JTextArea txtArea=new JTextArea();
 GraphPanel gf;
    DrlTable tblInteraction=new DrlTable();
    DefaultTableModel model;
  JScrollPane pane =null;
  int selected=0;
  String path="";

    public GraphPanel(Protein[] arr,Network n, GraphPanel gf1,String pathStr){
          path=pathStr;
          pnl2.setPreferredSize(new Dimension(600,450));
          pnl2.setBackground(Color.black);
          pnl.setBorder(BorderFactory.createLineBorder (Color.black, 2));
          this.add(pnl2);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
lbSelectProtein.setAlignmentX(LEFT_ALIGNMENT);
lbSelectProtein.setAlignmentY(CENTER_ALIGNMENT);
lbSelectProtein.setForeground(Color.WHITE);
lbSelectProtein.setFont(new Font("Times New Roman",Font.PLAIN,18));

pnl.setPreferredSize(new Dimension(600, 300));
pnl.setBackground(Color.black);
pnl.setBorder(BorderFactory.createLineBorder (Color.black, 2));
        pnl2.add(lbSelectProtein);
        pnl2.add(cboxProtein);
        pnl2.add(btnGetInteraction);
        pnl2.add(btnSaveInteraction);
       this.add(pnl);
       gf=gf1; 
              setBackground(Color.BLACK);
       System.out.println("GF  LineArray          :"+gf1.lineArray.size());
        System.out.println("GF  Protein Array         :"+gf1.x.length);
       // this.add(pm2);
       // pane=new JScrollPane(); 
        index=-1;
        nt=n;
       // nt.displayNetwork();
        yes=-1;
     //   pm=new JPopupMenu();
        pm2=new JPopupMenu();

        x = new Protein[arr.length];
        for (int j=0; j<= arr.length-1; j++){
            x[j] = arr[j];
        }
        font = new Font("lucida sans regular", Font.PLAIN, 16);
        lbSelectProtein.setText("Select Protein");
        
         for(int i=0;i<arr.length;i++){
            cboxProtein.addItem(arr[i].seq_id);
        }
        if(tblInteraction!=null){
           
            
            System.out.println("Row Count is : "+tblInteraction.getRowCount());
           
            
             //tblInteraction.setVisible(false);
         }

           btnGetInteraction.addActionListener(new java.awt.event.ActionListener() {
          
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetInteractionActionPerformed(evt);
            }
        });
              btnSaveInteraction.addActionListener(new java.awt.event.ActionListener() {
          
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInteractionActionPerformed(evt);
            }
        });
       
        this.setOpaque(true);
    }
    private void btnSaveInteractionActionPerformed(java.awt.event.ActionEvent evt){
    
        System.out.println("Comes in save");
        try {
            System.out.println("path is: "+path);
            File output=new File(path+"/Reslut.txt");
            if(!output.exists()){
                try {
                    output.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              PrintStream out = new PrintStream( output );
            for(int ind=0;ind<nt.getNodes().size();ind++){
                Protein pr=getProtein(nt.getNodeAt(ind).getAccNo());
                out.println("------------"+nt.getNodeAt(ind).getSeqID()+"   "+pr.seq_id+"------------------");
                out.print('\n');
                out.println("Description:"+pr.description);
                out.println("PI:  "+pr.pi);
                out.println("Molecular Weight:  "+pr.mw);
                out.println("Migrated Distance:   "+pr.migrated_distance);
                out.print('\n');
                 out.println("---Domain List---");
                for(int j=0;j<nt.getNodeAt(ind).getDomainList().size();j++){
                   out.print(nt.getNodeAt(ind).getDomainList().get(j));
                   out.print("   ");
                }
               out.print('\n');
               out.print('\n');
                out.println("--- Interacting Nodes---");
                for(int j=0;j<nt.getNodeAt(ind).getInteraction().size();j++){
                   out.println(nt.getNodeAt(ind).getInteraction().get(j).getSeqID()+" : "+nt.getNodeAt(ind).getInteraction().get(j).getAccNo());
                }
                 out.print('\n');out.print('\n');out.print('\n');out.print('\n');        
            }
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        private void btnGetInteractionActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
            if(pane!=null){
           pnl2.remove(pane);
            pane=null;
            tblInteraction=null;
            pnl.removeAll();
            }
          
            gf.paintComponent(gf.getGraphics());
        String value=cboxProtein.getSelectedItem().toString();
    final  ppi.Node n=nt.getNode(value);
      displayProtein(n,n);
       pnl.setVisible(true);

   String[][] addressData = fillData(n);
 
   String[] column={"Acc.No.","Description","Interaction","Select"};
     model=new DefaultTableModel(addressData,column);
         tblInteraction=new DrlTable(model);
       tblInteraction.getTableHeader().setFont(new Font("Verdana", Font.BOLD,12)); 
       tblInteraction.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
       tblInteraction.getColumnModel().getColumn(0).setPreferredWidth(10);
       tblInteraction.getColumnModel().getColumn(1).setPreferredWidth(100);
       tblInteraction.getColumnModel().getColumn(2).setPreferredWidth(25);
       tblInteraction.getColumnModel().getColumn(3).setPreferredWidth(8);
            System.out.println("------------Table Data--------------");
           ButtonGroup group = new ButtonGroup();
   for(int k=0;k<addressData.length;k++){
       tblInteraction.setValueAt(addressData[k][0], k,0);
       tblInteraction.setValueAt(addressData[k][1], k,1);
       tblInteraction.setValueAt(addressData[k][2], k,2);
      
       JRadioButton jcb=new JRadioButton();
       group.add(jcb);
       Integer valuen=new Integer(k);
       jcb.setName(valuen.toString());
       jcb.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton tempJb=(JRadioButton)e.getItem();
                    selected=Integer.parseInt(tempJb.getName());
                 //  System.out.println("The selected row is\t"+tempJb.getName());
                   Protein ptem=getProtein(tblInteraction.getValueAt(0,0).toString());
                   gf.paintComponent(gf.getGraphics());
                    if(Integer.parseInt(tempJb.getName())==0 && tblInteraction.getRowCount()==1){
                       gf.highLiteProtein(ptem.x, ptem.y);
                   }
                   if(Integer.parseInt(tempJb.getName())==0)
                   {
                       for(int tmp=1;tmp<tblInteraction.getRowCount();tmp++){
                          Protein ptem2=getProtein(tblInteraction.getValueAt(tmp,0).toString()); 
                  gf.highLiteProteinInteraction(ptem.x,ptem.y,ptem2.x,ptem2.y);
                   pnl.removeAll();
               displayProtein(n,n);
                pnl.validate();
                     pnl.repaint();
                       }
                   }
                   else{
                              
                       int tmp=Integer.parseInt(tempJb.getName());
                        Protein ptem2=getProtein(tblInteraction.getValueAt(tmp,0).toString()); 
                        gf.highLiteProteinInteraction(ptem.x,ptem.y,ptem2.x,ptem2.y);
                         pnl.removeAll();
                        
                         displayProtein(n,nt.getNode(ptem2.seq_id));
                     pnl.validate();
                     pnl.repaint();
                       
      
      
                   }
                  
                }
            });
       jcb.setSelected(false);
       tblInteraction.setValueAt(jcb, k,3);
   }
    tblInteraction.setRowHeight(25);
   tblInteraction.setModel(model);
  
   int hight=(tblInteraction.getRowHeight())*(tblInteraction.getRowCount());
 
   if(hight<=400)
     tblInteraction.setPreferredScrollableViewportSize(new Dimension(420,hight));
   else
         tblInteraction.setPreferredScrollableViewportSize(new Dimension(420,400));
      tblInteraction.setVisible(true);
       pane = new JScrollPane(tblInteraction,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
      pnl2.add(pane,BorderLayout.CENTER);
       pane.setVisible(true);
       pane.validate();
      this.validate();
       this.repaint();
       
       
 
 // ye hai end men display
        }
        public void highLiteProtein(double xp,double yp){
        highLight=true;
        Graphics g1=this.getGraphics();
        Graphics2D g2 = (Graphics2D) g1;
        g2.setColor(Color.RED);
        g2.drawOval((int)xp,(int) yp,12, 12);
        g2.fillOval((int)xp,(int) yp,12, 12);
        }
      public void  highLiteProteinInteraction(double xp,double yp,double xp1, double yp1){
    
          for(int in=0;in<x.length;in++){
             if(x[in].x==xp && x[in].y==yp ){
            // System.out.println("Comes in highlite IF");   
             highLight=true;
         Graphics g1=getGraphics();
        Graphics2D g2 = (Graphics2D) g1;
        g2.setColor(Color.BLUE);
     g2.setStroke( new BasicStroke( 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10) );
       g2.drawLine((int)xp, (int)yp, (int)xp1, (int)yp1);
         g2.setColor(Color.GREEN);
         g2.drawOval((int)xp1,(int) yp1,12, 12);
       g2.fillOval((int)xp1,(int) yp1,12, 12);
         g2.setColor(Color.RED);
         g2.drawOval((int)xp,(int) yp,12, 12);
        g2.fillOval((int)xp,(int) yp,12, 12);
 
              }
          }
      }
        public String[][] fillData(ppi.Node n){
            System.out.println("SelectED Protein");
           // n.displayNode();
            String[][] s=new String[n.getInteraction().size()+1][3];
            Protein p=getProtein(n.getAccNo());
            s[0][0]=p.seq_id;
            s[0][1]=p.description;
            s[0][2]="";
            ArrayList<ppi.Node> intr=n.getInteraction();
            ArrayList<String> conf=n.getConfidence();
                System.out.println("Interacting protein are:"+intr.size());
            
            for(int m=0;m<intr.size();m++){
                ppi.Node n2=intr.get(m);
                p=getProtein(n2.getAccNo());
                s[m+1][0]=p.seq_id;
                s[m+1][1]=p.description;
                s[m+1][2]=conf.get(m);
            }
            
            return s;
        }
    public GraphPanel(Protein[] arr,Network n, int i)
    {
     
      // this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
       this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        index=-1;
        nt=n;
       // nt.displayNetwork();
        yes=1;
        pm=new JPopupMenu();
        pm2=new JPopupMenu();

        x = new Protein[arr.length];
        for (int j=0; j<= arr.length-1; j++){
            x[j] = arr[j];
        }
        font = new Font("lucida sans regular", Font.PLAIN, 16);
       
        //Color C=new Color();
        //setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
        // 
        setBackground(Color.BLACK);
        this.addMouseListener(new MouseAdapter(){

            @Override
        public void mousePressed(MouseEvent e)
        {
              //System.out.print("x = " + e.getX());
            
           // DisplayProtein(e.getX(), e.getY());

          if(pm2!=null)
          {
               //System.out.println("-----inside press----");
               pm2.removeAll(); 
               pm2.setVisible(false);
          }
            chek (e.getX(), e.getY());
           // ShowInteraction(e.getX(),e.getY()); 
        }});
//        this.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//        public void mouseMoved(MouseEvent e){
//               
//                click=-1;
//          if(pm!=null)
//          {
//              // System.out.println("-----inside move----");
//               pm.removeAll(); 
//               pm.setVisible(false);
//          }
//          if(pm2!=null)
//          {
//               //System.out.println("-----inside press----");
//               pm2.removeAll(); 
//               pm2.setVisible(false);
//       
//          }
//        ShowInteraction(e.getX(),e.getY()); 
//   
//
//    }
//        
//        }
    
//                );
    }

public void ShowInteraction(double x1,double y1){
 //statusbar1.setText("Gud Oay");
   int HIT_BOX_SIZE = 2;
    Double boxX = x1 - HIT_BOX_SIZE / 2;
    Double boxY = y1 - HIT_BOX_SIZE / 2;
    
    int width = HIT_BOX_SIZE;
    int height = HIT_BOX_SIZE;
   // System.out.println("x: "+x1+" y:"+y1);
    int check=0;
    System.out.println("lines No:"+lineArray.size());
        for(int i=0;i<lineArray.size();i++){
        // pm.setVisible(false);
                 Graphics g1=getGraphics();
        Graphics2D g2 = (Graphics2D) g1;
  
            if (lineArray.get(i).intersects(x1, y1, width, height)) {
                
              //  this.repaint();
              //  this.validate();
                this.paint_soft(g1);
            pm=new JPopupMenu();
         p0=lineArray.get(i).getP1();
         index=i;
       System.out.println("Drawing line as cyan if interaction--------------------------------");
         p01=lineArray.get(i).getP2();
   
       
       
        Line2D lin = new Line2D.Float(p0,p01);
         g2.setColor(Color.CYAN);
           g2.setStroke( new BasicStroke( 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10) );
        g2.draw(lin);
       g2.setColor(Color.RED);
         g2.drawOval((int)(p0.getX()),(int)(p0.getY()),8,8);
         g2.setColor(Color.RED);
         g2.fillOval((int)(p0.getX()),(int)(p0.getY()),8,8);
         g2.setColor(Color.RED);
         g2.drawOval((int)(p01.getX()),(int)(p01.getY()),8,8);
         g2.setColor(Color.RED);
         g2.fillOval((int)(p01.getX()),(int)(p01.getY()),8,8);
         g2.setColor(Color.RED);
         /////////////////////////////
          pm.setLocation((int)x1,(int)y1);
          pm.add(getProtein(p0.getX(),p0.getY()));
          pm.add(getProtein(p01.getX(),p01.getY()));
         
          pm.setVisible(true);
          check=1;
           
        } else{
                this.repaint();
            }
        if(check==1){
             //System.out.println("-----inside 1----found at\t"+i);
           
            break;}  
        
    }
    
}
public void printLineArray(){
    int count=0;
    while(count<lineArray.size()){
    System.out.println("Firstpoint:"+lineArray.get(count).getX1()+lineArray.get(count).getY1());
      System.out.println("second Point:"+lineArray.get(count).getX2()+lineArray.get(count).getY2());
    count++;
}
}
    public GraphPanel(Protein[] arr, Network nt1) {
        nt=nt1;
        yes =0;
        x = new Protein[arr.length];
        
        for (int j=0; j<= arr.length-1; j++){
            x[j] = arr[j];
        }
        font = new Font("lucida sans regular", Font.PLAIN, 16); 
       
        this.setOpaque(true); 
        
    }
    public void DisplayProtein(int i,int ex,int ey){
            pm2=new JPopupMenu();
            pm2.add("MW--> " + ((int)x[i].mw)/1000 + "   PI--> " + x[i].pi);
            pm2.add("Protein Accession--> " + x[i].seq_id);
            pm2.add("Protein Description--> " + x[i].description);
            pm2.add("Protein Length--> " + x[i].length); 
            pm2.setLocation(ex, ey);
            pm2.setVisible(true);
        
    }

      public void displayProtein(ppi.Node n1,ppi.Node n2){
DBHandler db=new DBHandler();
      Protein p1=getProtein(n1.getAccNo());
      Protein p2=getProtein(n2.getAccNo());
      javax.swing.JTextPane lb=new JTextPane();
      lb.setForeground(Color.red);
            lb.setFont(new Font("Times New Roman",Font.PLAIN,18));
            lb.setBackground(Color.black);
            lb.setPreferredSize(new Dimension(500,50));
  
             
            
            
            String domains="No domain found";

            for(int dom=0;dom<n1.getDomainList().size();dom++){
                if(dom==0)
                    domains="";
                domains=domains.concat(n1.getDomainList().get(dom).toString()+" ");

                
            } lb.setText("Selected Protein: "+n1.getAccNo()+"\nDomains: "+domains);
            
            pnl.add(lb);
            if(n1.getAccNo()!=n2.getAccNo()){
                System.out.println("comes here");
                  String[] column={"Selected protein Domain","Interacting protein Domain"};
               String [][]data=db.getDomainInt(n1.getDomainList(),n2.getDomainList());
   DefaultTableModel model2=new DefaultTableModel(data,column);
            javax.swing.JTable tbint=new JTable(model2);
                javax.swing.JTextPane lb1=new JTextPane();
            lb1.setForeground(Color.green);
            lb1.setBackground(Color.black);
            lb1.setFont(new Font("Times New Roman",Font.PLAIN,18));
            lb1.setPreferredSize(new Dimension(500,50));
            domains="No domain found";
            for(int dom=0;dom<n2.getDomainList().size();dom++){
                 if(dom==0)
                    domains="";
                domains=domains.concat(n2.getDomainList().get(dom).toString()+" "); 
            }
           // lb1.setText("Interacting Partner: "+n2.getAccNo()+"\n"+"PI: "+(int)p2.pi+"\n"+"MW: "+(int)p2.mw+"\nMigrated distance:"+p2.migrated_distance+"\nDomains: "+domains);
            lb1.setText("Interacting Partner: "+n2.getAccNo()+"\nDomains: "+domains);
            pnl.add(lb1);
           javax.swing.JTextPane lb2=new JTextPane();
            lb2.setForeground(Color.BLUE);
            lb2.setFont(new Font("Times New Roman",Font.PLAIN,18));
            lb2.setBackground(Color.black);
            lb2.setPreferredSize(new Dimension(500,50));
            lb2.setText("Domain Interaction of two interacting partners:    ");
            pnl.add(lb2);
        model2=new DefaultTableModel(data,column);
         tbint=new DrlTable(model2);
      tbint.getTableHeader().setFont(new Font("Verdana", Font.BOLD,12)); 
       tbint.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
       tbint.getColumnModel().getColumn(0).setPreferredWidth(80);
      tbint.getColumnModel().getColumn(1).setPreferredWidth(80);

            System.out.println("------------Table Data--------------");
  
   for(int k=0;k<data.length;k++){
       tbint.setValueAt(data[k][0], k,0);
       tbint.setValueAt(data[k][1], k,1);
            }
   tbint.setForeground(Color.white);
  tbint.setBackground(Color.black);
   tbint.setVisible(true);
            tbint.setPreferredScrollableViewportSize(new Dimension(400,80));
       tbint.setVisible(true);
       javax.swing.JScrollPane pane2=new JScrollPane();
       pane2 = new JScrollPane(tbint,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane2.setBorder(BorderFactory.createLineBorder(Color.black,2));
    pane2.setBackground(Color.black);
    pane2.setForeground(Color.white);
     int hight=(tbint.getRowHeight())*(tbint.getRowCount());
 
   if(hight<=80)
     tbint.setPreferredScrollableViewportSize(new Dimension(400,hight));
   else
         tbint.setPreferredScrollableViewportSize(new Dimension(400,80));
      pnl.add(pane2,BorderLayout.CENTER);
       pane2.setVisible(true);
       pane2.validate();
  
            
            } 
   
        
    }

    public void chek(int ex, int ey){       //finds the protein given its x axis and y axis
        
        int i=0;
       boolean  found=false;
        while(i<x.length)
        {

                if ( ex <= x[i].x + 10 && ex >= x[i].x - 10){ //+8 because it is the size of spot
                if ( ey <= x[i].y + 10 && ey >= x[i].y - 10){
                    DisplayProtein(i,ex,ey);
                    found=true;
                }
            }
                if(found)
                {
                    break;
                }
                i++;
        }
        


    }

    public void paint_soft(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);
        FontRenderContext frc = g2.getFontRenderContext();
        int w = getWidth();
        int h = getHeight();
        // scales
        float xInc = (w - HPAD - VPAD) / 11f;
        float yInc = (h - 2*VPAD) / 10f;

        // ordinate
        g2.setColor(Color.white);
        g2.draw(new Line2D.Double(HPAD, VPAD, HPAD, (h - VPAD)+15));// Y-AXIS LINE
        g2.draw(new Line2D.Double(w - VPAD, VPAD, w - VPAD, (h - VPAD)+15));// Y-AXIS LINE--for boxing
        g2.setColor(Color.white);
        //  g2.drawLine(h, w, w, h);
        // tic marks
        float x1 = HPAD, y1 = VPAD, x2 = HPAD - 3, y2;
        for(int j = 0; j < 10; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x2, y1));
             g2.setColor(Color.white);
            y1 += yInc;
        }
        // labels
        String text = null; LineMetrics lm;
        float xs, ys, textWidth, height;

        float[] tempx=new float[12];    //x axis coordinates for y labels
        float[] tempy=new float[12];    //y axis coordinates for y labels(this is ofour use) M.W.

        int large = Protein.largest;
        int small = Protein.smallest;
        marker = new int[12];

        
        for(int j = 0; j <= 10; j++)
        {
            if (j==0){
                text ="";
                marker[j]= 0;
                //text = String.valueOf(x[small].mw);
            }
             else if (j==10){
                 text = String.valueOf(10);
                 marker[j]= 10;
             }
             else if (j==9){
                 //double temp = (x[small].mw + x[large].mw)/2;
                 text = String.valueOf(15);
                 marker[j]= 15;
             }
            else if (j==8){
                text = String.valueOf(25);
                marker[j] = 25;
             }
             else if (j==7){
                 //double temp = (x[small].mw + x[large].mw)/2;
                 text = String.valueOf(35);
                 marker[j] = 35;
             }
            else if (j==6){
                 text = String.valueOf(40);
                 marker[j] = 40;
             }
             else if (j==5){
                 //double temp = (x[small].mw + x[large].mw)/2;
                 text = String.valueOf(55);
                 marker[j] = 55;
             }
            else if (j==4){
                 text = String.valueOf(70);
                 marker[j] = 70;
             }
             else if (j==3){
                 //double temp = (x[small].mw + x[large].mw)/2;
                 text = String.valueOf(100);
                 marker[j]= 100;
             }
            else if (j==2){
                 text = String.valueOf(130);
                 marker[j]= 130;
             }
             else if (j==1){
                 //double temp = (x[small].mw + x[large].mw)/2;
                 text = String.valueOf(170);
                 marker[j]= 170;
             }
            textWidth = (float)font.getStringBounds(text, frc).getWidth();
            lm = font.getLineMetrics(text, frc);
            height = lm.getAscent();
            xs = HPAD - textWidth - 7;
            ys = VPAD + (j * yInc) + height/2;
            //ys=(400-ys)-20;

            tempy[j]=ys;
            tempx[j]=xs;
//            g2.drawString(text, tempx[j] - 10, tempy[i]);
        }
      
        int i=10;
        for(int j=1;j<=10;j++){     //PRINTS Y-AXIS
            text = String.valueOf(marker[i]);
            textWidth = (float)font.getStringBounds(text, frc).getWidth();
            lm = font.getLineMetrics(text, frc);
            height = lm.getAscent();
            if (j ==1 || j== 2 || j==3){
                g2.drawString(text, tempx[j], tempy[i]);
                 g2.setColor(Color.white);
            }
            else{
                g2.drawString(text, tempx[j]-10, tempy[i]);
             g2.setColor(Color.white);
            }
            

            i--;
        }
 
        // PI and MW side strip
        g2.drawString("PI", HPAD + 80 , VPAD - 10);
        g2.drawString("M.W", HPAD - 50 , VPAD + 30);
         g2.setColor(Color.white);

        // abcissa
        g2.draw(new Line2D.Double(HPAD, (h - VPAD) + 15, w - VPAD, (h - VPAD) + 15));   //X_AXIS LINE-->for boxing
        g2.draw(new Line2D.Double(HPAD, VPAD, w - VPAD, VPAD));   //X_AXIS LINE
        g2.setColor(Color.white);
        // tic marks
        //x1 = HPAD; y1 = h - VPAD; y2 = y1 + 3;
        x1 = HPAD; y1 = VPAD - 3 ; y2 = VPAD + 3;
        for(int j = 0; j < 12; j++)
        {
            g2.draw(new Line2D.Double(x1, y1, x1, y2));
            g2.setColor(Color.white);
            x1 += xInc;
        }
        // X-axis labels
        //ys = h - VPAD;
        ys = VPAD - 25;  //for shifting it up
        float temp_coordinates_x[] = new float[12]; //for saving x coordinate values of x-labels
        for(int j = 0; j < 12; j++)
        {
            if (j != 0 && j != 1 && j != 2 && j != 11){

                text = String.valueOf(j);
                textWidth = (float)font.getStringBounds(text, frc).getWidth();
                lm = font.getLineMetrics(text, frc);
                height = lm.getHeight();
                xs = HPAD + j * xInc - textWidth/2;

                temp_coordinates_x[j]=xs;

                g2.drawString(text, xs, ys + height);
                 g2.setColor(Color.white);
            }
        }

        /*
        // plot data
         * What we hav to do is to save all the x axis and yaxis positions
         * of the labels, and then use em acordingly..
         * x->pi
         * y->mw

        */
       // System.out.println("The size of X is\t"+x.length);
        for (i=0;i<=x.length-1;i++){      //to diplay all the proteins
            float pi_cord = find_cord_pi(x[i].pi, temp_coordinates_x);  //x axis
            float mw_cord = find_cord_mw(x[i].mw, tempy, x[large].mw, x[small].mw); //y axis
            int pi = (int)pi_cord;
            int mw = (int)mw_cord;

            x[i].x = pi;
            x[i].y = mw;
        }

    }
    public void drawLines(Graphics g){
        lineArray.clear();
        Graphics2D g2 = (Graphics2D)g;
        ArrayList<ppi.Node> IntNodes=new ArrayList();
        if(lineArray.size()==0){
        for(int i=0;i<x.length;i++){
             String accNode1=x[i].seq_id;
               g2.setColor(Color.YELLOW);
                        g2.fillOval(x[i].x,x[i].y,8,8);
            IntNodes=new ArrayList();
             ppi.Node n=nt.getNode(accNode1);
             IntNodes=n.getInteraction();
            for(int j=0;j<IntNodes.size();j++){
             Protein pr=getProtein(IntNodes.get(j).getAccNo());
               Line2D lin=new Line2D.Double(x[i].x,x[i].y, pr.x, pr.y);
               lineArray.add(lin);
                        g2.setColor(Color.lightGray);
                        g2.draw(lin);
                        
                      
            }
           // System.out.println("Line array size for protein \t"+i+"\t isd \t"+lineArray.size());
        }
        }
    }
    public Protein getProtein(String acc){
        Protein p=new Protein();
        for(int i=0;i<x.length;i++){
            if(x[i].seq_id.equals(acc)){
                p=x[i];
            }
        }
        return p;
        
    }
    public int yes;
   // @Override
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        //yes is 1 when 2d display is selected...and paint would not picture all the display when 3d is selected
        if (yes ==1 ){
         
            paint_soft(g);
            drawLines(g);
           
        }
    }

    public boolean chekx(int coordinate , int i){       //finds if any protein has the same x or y coordinates

        for (int j = 0; j <= i; j++){
            if (x[j].x == coordinate){
                return true;
            }
        }
        return false;


    }
    public boolean cheky(int coordinate, int i){       //finds if any protein has the same x or y coordinates

        for (int j = 0; j <= i; j++){
            if (x[j].y == coordinate){
                return true;
            }
        }
        return false;
    }


    public float find_cord_mw (double mw, float arr[], double large, double small){//pass x[large].mw..and vice versa

    int flag = 1;
    mw = mw /1000;
    int i =1;

    float front =0;
    float front_cord =0;

    float back =0;
    float back_cord =0;

    float mid =0;
    float mid_cord =0;

    while (flag == 1){
        if (i>10){
            System.out.print("ERROR AT FIND CORD 3D");
            return 0;
        }
        else if (marker[i] > mw){
            i++;
        }
         else if (marker[i] < mw){
             flag =0;
             if (i+1 == -1){
                 back_cord = -8; //chek
                 back = 0;
             }
             else {
                 back = marker[i-1];
                 back_cord = arr[i-1];
             }
             front = marker[i];
             front_cord = arr[i];

             mid = (front + back)/2;
             mid_cord = (front_cord + back_cord)/2;

             //while (mid >= mw + 5 && mid <= mw-5){
             //while (!(mid <= mw+3 && mid >= mw-3))
             while (!(mid <= mw +0.5 && mid >= mw-0.5))
             {
                 
                 if ( mw > mid ){
                     front = mid;
                     front_cord = mid_cord;
                 }
                 else if(mw < mid)
                 {
                     back = mid;
                     back_cord = mid_cord;
                 }
                 mid = back + front;
                 mid = mid/2;
                 mid_cord = (back_cord + front_cord)/2;
                 
             }
             return mid_cord;
         }
    }
    return mid_cord;

    }

    public float find_cord_pi(double pi, float arr[]){

        float a = (int) pi;
    //float b = (int)a +1;
    float b = a +1;
    int index = (int) pi;

    float a_pixel = arr[index];
    float b_pixel = arr[index +1];

    float mid = (a+b)/2;
    float mid_pixel = (a_pixel+b_pixel)/2;

    while (mid>= pi+0.01 || mid <= pi-0.01){
        if (pi > mid){
            a = mid;
            a_pixel = mid_pixel;
        }
        else if(pi < mid){
            b = mid;
            b_pixel = mid_pixel;
        }
        mid = (a+b)/2;
        mid_pixel = (a_pixel+b_pixel)/2;
    }
    return mid_pixel;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private TransformGroup objRotate = new TransformGroup();
    private Sphere sp[];
    private float[] pi_labels;    //for the pi and mw labels x and y axis position of each label,only one dimension is needed
    private float[] mw_labels_cord;   // because for a given point we need on one position for its one axis
    private float[] mw_labels;                            // the arrays would be populated when the labels are made in CREATE_TEXTS. However a modification is to be made
                                // so that create texts moves the labels ahead acc to the %age of gel


    public void make_everything(int number,int length){

    setLayout(new BorderLayout());

    Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    add(canvas3D);

    BranchGroup scene = createSceneGraph_two(number,length);

    // SimpleUniverse is a Convenience Utility class

    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    OrbitBehavior orbit = new OrbitBehavior(canvas3D);
    ViewingPlatform vp = simpleU.getViewingPlatform();

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100);
    orbit.setSchedulingBounds(bounds);
    vp.setViewPlatformBehavior(orbit);

    // This will move the ViewPlatform back a bit so the
    // objects in the scene can be viewed.

    simpleU.getViewingPlatform().setNominalViewingTransform();
    addLights(scene);
   // simpleU.addBranchGraph(scene);

simpleU.addBranchGraph(scene);

}
    public BranchGroup drawLines(){
        System.out.println("Drawing lines");
        BranchGroup bg=new BranchGroup();
   Appearance app = new Appearance();
   Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
    ColoringAttributes ca = new ColoringAttributes(black,ColoringAttributes.SHADE_FLAT);
    app.setColoringAttributes(ca);
        for(int i=0;i<x.length;i++){
            float xx=find_cord_3D_pi(x[i].pi);
            float y=find_cord_3D_mw(x[i].mw);
             Point3f[] Pt = new Point3f[2];
             Pt[0] = new Point3f(xx, y, 1.0f);
            ppi.Node nod=nt.getNode(x[i].seq_id);
            for(int j=0;j<nod.getInteraction().size();j++){
                System.out.println("In interactions");
                ppi.Node tmpNod=nod.getInteraction().get(j);
                Protein pr= getProtein(tmpNod.getAccNo());
                xx=find_cord_3D_pi(pr.pi);
                y=find_cord_3D_mw(pr.mw);
                Pt[1] = new Point3f(xx, y, 1.0f);
                int[] strip = {2};
                LineArray pla = new LineArray(2, LineArray.COORDINATES);
                pla.setCoordinates(0, Pt);
              LineArray dotDash = new LineArray(2, LineArray.COORDINATES);
    LineAttributes dotDashLa = new LineAttributes();
    dotDashLa.setLineWidth(20.0f);
    Appearance dotDashApp = new Appearance();
    dotDashApp.setLineAttributes(dotDashLa);
     Shape3D plShape = new Shape3D(pla, app);
                bg.addChild(plShape);
            }
             
            
        }
    
        return bg;
    }
public BranchGroup create_texts (){

    pi_labels = new float[8];   //for saving label coordinates
    mw_labels_cord = new float[10];
    mw_labels = new float[10];
    
    BranchGroup text_branchgroup = new BranchGroup();
    Appearance text_app = new Appearance();
    Color3f text_color = new Color3f(0.5f,0.5f,5.0f);
    text_app.setMaterial(new Material (text_color,text_color,text_color,text_color,90.0f));
    //Font3D obj = new Font3D(new Font("Times New Roman",Font.PLAIN, (int) 1.5f),new FontExtrusion());
    Font3D obj = new Font3D(new Font("Serif",Font.PLAIN, (int) 1.5f),new FontExtrusion());

//MW
    Text3D mw = new Text3D(obj,"M.W",new Point3f(-12f,0f,0f));
    Shape3D text_shape_mw = new Shape3D(mw,text_app);

    text_branchgroup.addChild(text_shape_mw);

// Numbering PI x-axis 3-10
    Font3D obj_no = new Font3D(new Font("Arial",Font.PLAIN, (int) 1),new FontExtrusion());
    Text3D[] pi_values = new Text3D[8] ;//= new Text3D[7];
    Shape3D[] shape_pi_values = new Shape3D[8];

    int flag =0;
    float pos = -5.75f;
    int label_index = 0;    //variable used for the array of pi_labels

    for (int i=3;i<=10;i++){
        pi_values[flag] = new Text3D(obj_no,Integer.toString(i),new Point3f(pos,8f,0f));    //x-y-z
        pi_labels[label_index] = pos;

        shape_pi_values[flag] = new Shape3D(pi_values[flag],text_app);
        text_branchgroup.addChild(shape_pi_values[flag]);
        
        flag++;
        label_index++;
        pos = pos + 1.5f;
    }


//Numbering MW y-axis 170,130,100,70,55,40,35,25,15,10

    Text3D[] mw_values = new Text3D[10] ;//= new Text3D[7];
    Shape3D[] shape_mw_values = new Shape3D[10];

    int ten = 0;        //coming from the radio buttons for check of which calculation is to be performed
    int twelv = 1;
    int fifteen = 0;

    flag =0;
    float[] pos_mw = new float[10];
    //pos_mw[0] = -7.5f;

     if (ten == 1){
         pos_mw[9] = 6;
         pos_mw[8] = 5;
         pos_mw[7] = 3.92f;
         pos_mw[6] = 2.53f;
         pos_mw[5] = 1.58f;
         pos_mw[4] = 0.3f;
         pos_mw[3] = -0.2f;
         pos_mw[2] = -1.5f;
         pos_mw[1] = -3.5f;
         pos_mw[0] = -5;

     }
     else if (twelv == 1){
         pos_mw[9] = 6.7f;
         pos_mw[8] = 6.08f;
         pos_mw[7] = 5.47f;
         pos_mw[6] = 4.64f;
         pos_mw[5] = 4.08f;
         pos_mw[4] = 3.34f;
         pos_mw[3] = 3.03f;
         pos_mw[2] = 2.25f;
         pos_mw[1] = 1.07f;
         pos_mw[0] = 0.12f;

     }
     else if(fifteen == 1){
         pos_mw[9] = 6.875f;
         pos_mw[8] = 6.162f;
         pos_mw[7] = 5.5f;
         pos_mw[6] = 4.6f;
         pos_mw[5] = 3.99f;
         pos_mw[4] = 3.19f;
         pos_mw[3] = 2.85f;
         pos_mw[2] = 2f;
         pos_mw[1] = 0.71f;
         pos_mw[0] = -0.31f;
     }
    // now lets try some sketch and skew...but keeping the aspect ratio same
if (twelv ==1 || fifteen ==1){
        for (int z = 0; z<=9;z++){
            pos_mw[z] = pos_mw[z] - 3.5f;
            pos_mw[z] = pos_mw[z]*2f;
        }
    }
 else{
        for (int z = 0; z<=9;z++){
            pos_mw[z] = pos_mw[z] - 1f;
            pos_mw[z] = pos_mw[z]*1.25f;
        }
 }
    String[] texts = new String[10];
    texts[0] = "10";
    texts[1] = "15";
    texts[2] = "25";
    texts[3] = "35";
    texts[4] = "40";
    texts[5] = "55";
    texts[6] = "70";
    texts[7] = "100";
    texts[8] = "130";
    texts[9] = "170";

    for (int i=0;i<=9;i++){
        mw_values[i] = new Text3D(obj_no,texts[i],new Point3f(-8f,pos_mw[i],0f)); //x-y-z
        mw_labels_cord[i] = pos_mw[i];
        mw_labels[i] = Float.valueOf(texts[i].trim()).floatValue();
    }
        flag = 0;

        for (int i=0;i<10;i++){
        shape_mw_values[flag] = new Shape3D(mw_values[flag],text_app);
        text_branchgroup.addChild(shape_mw_values[flag]);
        flag++;
    }

//PI


    Text3D pi = new Text3D(obj,"P.I",new Point3f(0f,9f,0f));
    Shape3D text_shape_pi = new Shape3D(pi,text_app);

    text_branchgroup.addChild(text_shape_pi);



    return text_branchgroup;
}
private float find_cord_3D_pi(double pi){
    float a = (int) pi;
    //float b = (int)a +1;
    float b = a +1;
    int index = (int) pi;

    float a_pixel = pi_labels[index-3];
    float b_pixel =pi_labels[(index-3) +1];

    float mid = (a+b)/2;
    float mid_pixel = (a_pixel+b_pixel)/2;
    
    while (mid>= pi+0.01 || mid <= pi-0.01){
        if (pi > mid){
            a = mid;
            a_pixel = mid_pixel;
        }
        else if(pi < mid){
            b = mid;
            b_pixel = mid_pixel;
        }
        mid = (a+b)/2;
        mid_pixel = (a_pixel+b_pixel)/2;
    }
    return mid_pixel;

    
}
private float find_cord_3D_mw(double mw){
     int flag = 1;
    mw = mw /1000;
    int i =9;

    float front =0;
    float front_cord =0;

    float back =0;
    float back_cord =0;

    float mid =0;
    float mid_cord =0;

    while (flag == 1){
        if (i>10){
            System.out.print("ERROR AT FIND CORD 3D");
            return 0;
        }
        else if (mw_labels[i] > mw){
            i--;
        }
         else if (mw_labels[i] < mw){
             flag =0;
             if (i+1 == -1){
                 back_cord = -8; //chek
                 back = 0;
             }
             else {
                 back = mw_labels[i+1];
                 back_cord = mw_labels_cord[i+1];
             }
             front = mw_labels[i];
             front_cord = mw_labels_cord[i];

             mid = (front + back)/2;
             mid_cord = (front_cord + back_cord)/2;

             //while (mid >= mw + 5 && mid <= mw-5){
             //while (!(mid <= mw+3 && mid >= mw-3))
             while (!(mid <= mw +0.1 && mid >= mw-0.1))
             {

                 if ( mw > mid ){
                     front = mid;
                     front_cord = mid_cord;
                 }
                 else if(mw < mid)
                 {
                     back = mid;
                     back_cord = mid_cord;
                 }
                 mid = back + front;
                 mid = mid/2;
                 mid_cord = (back_cord + front_cord)/2;

             }
             return mid_cord;
         }
    }
    return mid_cord;

}
public BranchGroup createSceneGraph_two(int number,int length) {    //number is the id of the sphere to color different as selected by user.
                                                                    // length is the number of spheres present,i.e length of aray and loops
    // Create the root of the branch graph
    
//***********************************************************************************
    length = x.length;
    BranchGroup text_branchgroup = new BranchGroup();
    text_branchgroup = create_texts();

    BranchGroup objRoot = new BranchGroup();
    Appearance app = new Appearance();
    Color3f ObjColor = new Color3f(0.5f,0.5f,5.0f);
    Color3f black = new Color3f(0.0f,0.0f,0.0f);
    Color3f red=new Color3f(1.0f, 1.0f, 1.0f);
    app.setMaterial(new Material (ObjColor,ObjColor,ObjColor,ObjColor,80.0f));
    Box bx = new Box(6.0f,7.50f,0.05f,app);


    //set the branch group for gel spots
    BranchGroup spots = new BranchGroup();
    Appearance spot_app = new Appearance();
    Color3f spot_color = new Color3f(0.9f,0.1f,0.1f);
    spot_app.setMaterial(new Material(spot_color,spot_color,spot_color,spot_color,200f));

    sp = new Sphere[length];
    //TransformGroup obj_sp = new TransformGroup();
    TransformGroup obj_sp[] = new TransformGroup[length];
    

    Appearance spot_app1 = new Appearance();
    Color3f spot_color1 = new Color3f(0.1f,0.5f,0.5f);
    spot_app1.setMaterial(new Material(spot_color1,spot_color1,spot_color1,spot_color1,200f));

    Transform3D ob[];
    ob = new Transform3D[length];
    Vector3d vector[] = new Vector3d[length];
    float xx = 0f;
    float z = 0f;

  Appearance app1 = new Appearance();
    ColoringAttributes ca1 = new ColoringAttributes(black,ColoringAttributes.SHADE_FLAT);
    app1.setColoringAttributes(ca1);

    for (int i = 0;i <= length-1; i++){
        obj_sp[i] = new TransformGroup();
        obj_sp[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        if (i == number){
            sp[i] = new Sphere (0.1f,spot_app1);
            obj_sp[i].addChild(sp[i]);
            ob[i] = new Transform3D();              //call find 3d cord fr pi and mw
            xx = find_cord_3D_pi(x[i].pi);
            z = find_cord_3D_mw(x[i].mw);

            //System.out.print("pi " + x[i].pi + "\n");
           // System.out.print("xx " + xx + "\n");
          //  System.out.print("z " + z + "\n");
          //  System.out.print("\n");
          //  System.out.print("\n");


            vector[i] = new Vector3d(xx,z,0f);     //x y z these x y z would be coming from a computation of pi nd mw
            ob[i].setTranslation(vector[i]);
            obj_sp[i].setTransform(ob[i]);
        }
        else{
            sp[i] = new Sphere (0.1f,spot_app);
            obj_sp[i].addChild(sp[i]);
            ob[i] = new Transform3D();
            z = find_cord_3D_mw(x[i].mw);
            xx = find_cord_3D_pi(x[i].pi);
//            System.out.print("pi " + x[i].pi + "\n");
//            System.out.print("xx " + xx + "\n");
//            System.out.print("z " + z + "\n");
//            System.out.print("\n");
//            System.out.print("\n");



            vector[i] = new Vector3d(xx,z,0f);     //x y z these x y z would be coming from a computation of pi nd mw
            ob[i].setTranslation(vector[i]);
            obj_sp[i].setTransform(ob[i]);
        }
        //xx = (float) (xx + 0.2);
        //z=  (float) (z + 0.2);
        
        spots.addChild(obj_sp[i]);
   
    }
      BranchGroup lines=drawLines();

    objRotate.addChild(bx);
    objRotate.addChild(spots);
    objRotate.addChild(lines);
    objRotate.addChild(text_branchgroup);
    
    objRoot.addChild(objRotate);
              
    return objRoot;
  } // end of CreateSceneGraph method of SimpleBehaviorApp


 // end of CreateSceneGraph method of SimpleBehaviorApp


  // Create a simple scene and attach it to the virtual universe

private void addLights(BranchGroup bgRoot) {
        //throw new UnsupportedOperationException("Not yet implemented");
        Color3f color = new Color3f (2.0f,3.0f,1.0f);
        Vector3f direction = new Vector3f(-1.0f,-1.0f,-1.0f);
        DirectionalLight light = new DirectionalLight(color,direction);
        light.setInfluencingBounds(getBoundingSphere());
        bgRoot.addChild(light);
    }

    private Bounds getBoundingSphere() {
        //throw new UnsupportedOperationException("Not yet implemented");
        BoundingSphere x = new BoundingSphere(new Point3d(0.0f,0.0f,0.0f),200.0f);
        return x;
                //new BoundingSphere(new Point3d(0.0f,0.0f.0.0f),200.0f);
    }

    
            void printALLL() {
       Protein []pp=x;
                  for(int i=0;i<pp.length;i++)
        {
            Protein p=pp[i];
         //   System.out.println("The X loaction of"+p.seq_id+" is \t"+p.x);
        //    System.out.println("The Y loaction of"+p.seq_id+"is \t"+p.y);
            
        }
        
    }

public String getProtein(double x1,double y1){
    for( int i=0;i <x.length;i++)
        {
            Protein p=x[i];
             if(x1==p.x && y1==p.y){
                
           return p.seq_id+": "+nt.getsID(p.seq_id);
             }
            
        }
    return "NULL";
}

}