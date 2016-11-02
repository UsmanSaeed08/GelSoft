
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class ppi_intact extends Thread{

    String dir;
    String host_organism;
    String protein_query;
    String op_int;

    public ppi_intact(String host, String query,String op){
        dir = "";
        host_organism = host;
        protein_query = query;
        op_int = op;

    }
/*
    
    public void makeandrun(String dir1, String host_organism1, String protein1) throws FileNotFoundException{
        
        PrintStream out;
        
            
            dir = dir1;
            host_organism = host_organism1;
            protein_query = protein1;
            //File obj = new File(dir);
            
            
            out = new PrintStream(new FileOutputStream(dir + "\\" + "OutFile.txt"));
            
            try {
                run(out);
            } catch (IOException ex) {
                Logger.getLogger(ppi_intact.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            out.close();
    }
    
    private void run(PrintStream out) throws IOException{
        PsicquicSimpleClient client = new PsicquicSimpleClient("http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/current/search/");

        final long count = client.countByQuery(protein_query + "AND" + host_organism);


        try {
            final InputStream result = client.getByQuery(protein_query + "AND" + host_organism);
            BufferedReader in = new BufferedReader(new InputStreamReader(result));
            String line;

            int i =1;
            while ((line = in.readLine()) != null) {
                out.println(line);
                PrintStream out2 = new PrintStream(new FileOutputStream(dir+"\\"+String.valueOf(i)+".txt"));
                out2.println(line);

                i++;
            }

            out.close();
            in.close();
        }
        catch (IOException e) {
        }
    }
    */
    @Override
    public void run(){
        PsicquicSimpleClient client = new PsicquicSimpleClient("http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/current/search/");

        //final long count = client.countByQuery("RUVBL2 AND hostOrganism:human");

        
        //System.out.println("Count: "+count);

        //System.out.println("\nMITAB:\n");

        try {
            //final InputStream result = client.getByQuery(protein_query + "AND" + host_organism);
            final InputStream result = client.getByQuery(protein_query);
            BufferedReader in = new BufferedReader(new InputStreamReader(result));
            String line = "Found Interactions given below: ";
            
            //System.out.print(obj.mkdir());

            //PrintStream out = new PrintStream(new FileOutputStream("D:\\interactions\\OutFile"+ protein_query +".txt"));
            PrintStream out = new PrintStream(new FileOutputStream(op_int+ "\\" +protein_query +".txt"));
            
            int i =1;
            do{
                out.println(line);
                //PrintStream out2 = new PrintStream(new FileOutputStream("D:\\interactions\\"+  protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                PrintStream out2 = new PrintStream(new FileOutputStream(op_int+ "\\" +protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                out2.println(line);
                System.out.println(line);
                i++;
            }
            while((line = in.readLine()) != null);
            /*
            while ((line = in.readLine()) != null) {
                out.println(line);
                PrintStream out2 = new PrintStream(new FileOutputStream("D:\\interactions\\"+  protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                out2.println(line);
                System.out.println(line);
                i++;
            }*/

            out.close();
            in.close();
        } catch (IOException e) {
        }
    }
    
   public void check(String op_int){
/*        PsicquicSimpleClient client = new PsicquicSimpleClient("http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/current/search/");

        //final long count = client.countByQuery("RUVBL2 AND hostOrganism:human");

        
        //System.out.println("Count: "+count);

        //System.out.println("\nMITAB:\n");

        try {
            //final InputStream result = client.getByQuery(protein_query + "AND" + host_organism);
            final InputStream result = client.getByQuery(protein_query);
            BufferedReader in = new BufferedReader(new InputStreamReader(result));
            String line = "Found Interactions given below: ";
            
            //System.out.print(obj.mkdir());

            //PrintStream out = new PrintStream(new FileOutputStream("D:\\interactions\\OutFile"+ protein_query +".txt"));
            PrintStream out = new PrintStream(new FileOutputStream(op_int+ "\\" +protein_query +".txt"));
            
            int i =1;
            do{
                out.println(line);
                //PrintStream out2 = new PrintStream(new FileOutputStream("D:\\interactions\\"+  protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                PrintStream out2 = new PrintStream(new FileOutputStream(op_int+ "\\" +protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                out2.println(line);
                System.out.println(line);
                i++;
            }
            while((line = in.readLine()) != null);
            /*
            while ((line = in.readLine()) != null) {
                out.println(line);
                PrintStream out2 = new PrintStream(new FileOutputStream("D:\\interactions\\"+  protein_query +"INTERACTION NO "+String.valueOf(i)+".txt"));
                out2.println(line);
                System.out.println(line);
                i++;
            }

            out.close();
            in.close();
        } catch (IOException e) {
        }

    }

*/
}
}
