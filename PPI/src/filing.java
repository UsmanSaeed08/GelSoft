




import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.biojava.bio.BioException;
import org.biojava.bio.symbol.Alphabet;
import org.biojava.bio.symbol.AlphabetManager;
import org.biojavax.SimpleNamespace;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;
import ppi.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mani
 */
public class filing
{

    public Protein arr[];
    private ArrayList complete_file;
    private int two_dd;
    private int three_dd;

    private boolean intact;
    private boolean mint;
    private boolean gravy;
    private boolean aliphatic;
    private boolean extinction;
    private boolean absorbance;
    private boolean charts;

    private String input_file;
    private String file_intact;
    private String file_properties;
    private String host;
    private File my_input_file;
    private int gel_percentage;
    private File OutputPath;
    public Ppi pi;
    ArrayList<Sequence> seq; 
    ArrayList<Node> node; 
    DBHandler db;
    progress pframe;
    
  
    //private String chart_dir;


    filing (){
       
    }
    filing(int two, int three, boolean intact1, boolean mint1, boolean gravy1, boolean aliphatic1, boolean extinction1, 
            boolean absobance1, boolean charts1, String file_int, String h, String file_prop, File chek, int migration, File OP){

        two_dd = two;
        three_dd = three;
        intact = intact1;
        mint = mint1;
        gravy = gravy1;
        aliphatic = aliphatic1;
        extinction = extinction1;
        absorbance = absobance1;
        charts = charts1;

        //input_file = input;
        file_intact = file_int;
        file_properties = file_prop;
        host = h;
        my_input_file = chek;
        gel_percentage = migration;
        OutputPath = OP;
        seq=new ArrayList();
        node=new ArrayList();
    }
    

    void file_read() throws FileNotFoundException, BioException, IOException, SQLException{
        //while sequences end read em and pass to populate
        //recieve protein type
        //return protein type to compute button
     
        complete_file= new ArrayList();
        
        Compute obj=new Compute();

    BufferedReader br = new BufferedReader(new FileReader(my_input_file));
    
    Alphabet alpha = AlphabetManager.alphabetForName("PROTEIN");
    SimpleNamespace ns = new SimpleNamespace("biojava");

    RichSequenceIterator iterator = RichSequence.IOTools.readFasta(br,
    alpha.getTokenization("token"), ns);
    
    //System.out.print("Chek");
    
    int i = 0;

    while (iterator.hasNext()) {
        //Protein temp= new Protein();
        
        RichSequence rec = iterator.nextRichSequence();

        Protein temp = new Protein(rec.getAccession(),rec.seqString(),rec.getDescription(),rec.length());
        complete_file.add(i, temp);
        Node no=new Node();
        no.setAccNo(rec.getAccession());
        no.setSeq(rec.seqString());
        node.add(no);
        i++;       
    }
    
    pframe = new progress(i); // to make frame to show loading
    
    new Thread(pframe).start();

    
    //pframe.start(); //start that frame thread
    

    
    db=new DBHandler();
    node=db.isInDb(node); //to do this we use place holder thread
    
    pi=new Ppi(node);
    arr = obj.populate(complete_file, gel_percentage);
    
    //placeholder thred = new placeholder(node);
    //thred.run();
    
    //while(thred.isAlive()){
        
    //}
    
    //to do after thread execution
    //pi = thred.pi;
    output();
    }
    public void add(int i, Protein temp){

        complete_file.add(i, temp);
    }


    public void output() throws IOException{
        //make graph
        //make key and results
        
        pframe.close();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   f.getContentPane().add(new GraphPanel(arr));
       f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.X_AXIS));
        
        //int chek = 1;
 
        if (two_dd == 1){
            GraphPanel graphpanel = new GraphPanel(arr,pi.nt,0);
            graphpanel.setPreferredSize(new Dimension(400,500));
          //  f.getContentPane().add(graphpanel);
            GraphPanel gp=new GraphPanel(graphpanel.x,pi.nt,graphpanel,OutputPath.getPath());
            gp.setLayout(new FlowLayout(FlowLayout.CENTER));
            panel jpanel=new panel(gp,new BorderLayout());
            gp.setPreferredSize(new Dimension(400,630));
        //  f.add(graphpanel);
            f.getContentPane().add(graphpanel);
             f.getContentPane().add(gp);
            
         
      //    f.add(jpanel);
           f.setSize(600,600);
         //   f.setPreferredSize(new Dimension(600,600));
           // f.setLocation(200,100);
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
            f.setResizable(true);
            
            f.setVisible(true);
               
        }
        if (three_dd == 1){
            JList_soft obj = new JList_soft(arr,pi.nt);
        }
        //make intact mint gravy charts and all step by step for each protein

        if (intact == true || mint ==true || gravy == true || aliphatic == true || extinction == true || absorbance == true || charts == true){
            try {
                acc_functions();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(filing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



        //file write
        int percent = 10;
        if (gel_percentage == 0){
            percent = 10;
        }
        else if (gel_percentage == 1){
            percent = 12;
        }
        if (gel_percentage == 2){
            percent = 15;
        }

       
       
       FileOutputStream out = new FileOutputStream(OutputPath.getPath() + "\\" +"output_complete_file.txt");

       
       PrintStream p = new PrintStream( out );
       p.print('\n');
       p.print("Simulation for PAGE conc " + percent+"%");
       p.print('\n');
       p.print('\n');

       for (int index =0; index<arr.length;index++){

           float mww = ((float)arr[index].mw/1000);
           p.println (arr[index].seq_id + "\n");
           p.println ("Mw: " + String.valueOf(mww) + "\n");
           p.println ("PI: " + String.valueOf((float)arr[index].pi) + "\n");
           p.println ("Sequence Length: " + String.valueOf(arr[index].length) + "\n");
           p.println ("Migration: " + arr[index].migrated_distance + "\n");
           p.println ("Description: " + arr[index].description + "\n");
           p.print('\n');
           p.print('\n');
           
        }
                  p.close();

    }
    void acc_functions() throws FileNotFoundException{
        
        int size = arr.length;
        float[] gravy_arr = new float[size];;
        
       
        
        if (intact == true){
            //call intact
                
                
                for (int ii = 0; ii<= arr.length-1; ii++){
                    
                    ppi_intact obj = new ppi_intact("human",arr[ii].seq_id,OutputPath.getPath());
                    obj.start();
                    
                }
            }
        for (int i = 0; i<= arr.length-1; i++){
            

            if (mint == true){
                //call mint
                ppi_MINT obj = new ppi_MINT(arr[i].seq_id); //remove mint
            }

            if (gravy == true || aliphatic == true || extinction == true || absorbance == true){

                properties_protein obj = new properties_protein(arr[i].seq);
                float g = 0f;
                float a = 0f;
                float e = 0f;
                float ab = 0f;

                    if (gravy == true){
                    //call gravy
                        g = obj.GRAVY();
                        gravy_arr[i] = g;
                    }
                    if (aliphatic == true){
                        //call aliphatic
                        a = obj.Aliphatic_index();
                    }
                    if (extinction == true){
                        //call extinction
                        e = obj.Extinction_Coefficient();
                    }
                    if (absorbance == true){
                        //call absorbance
                        ab = obj.absorbance((float)arr[i].mw);
                    }
                // have to write the results in a file
                //so
                //PrintStream out2;
                
                    PrintStream out2 = new PrintStream(new FileOutputStream(OutputPath.getPath() + "\\" + arr[i].seq_id + ".txt"));
                
                //write the properties in out2
                String line = "Seqid " + arr[i].seq_id + "      " + "Average Hydrophobicity " + g + "       " + "Aliphatic Index " + a + "      "+ "Extinction Coefficient " + e + "     " + "Absorbance "+ab;
                out2.println(line);

                    if (charts == true){
                    //call charts
                        
                        Jcharts obj1 = new Jcharts(OutputPath.getPath(),arr[i].seq_id);
                        Polarity_calculator polarity = new Polarity_calculator();
                        polarity.calculate(arr[i].seq);
                        obj1.piechart(arr[i].seq_id, arr[i].length, polarity.a, polarity.b, polarity.h, polarity.u);
                        obj1.bargraph(arr[i].seq_id, arr[i].length, polarity.a, polarity.b, polarity.h, polarity.u);
                    }

                }
            
            if (charts == true){
                XYplot object = new XYplot(OutputPath.getPath());
                object.xyplot(gravy_arr);
            }
        }
    }



}
