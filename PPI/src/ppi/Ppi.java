/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Ppi {
    //public static void main(String[] args) {
    public ArrayList sequenceID;
  public  Network nt;
    Interpro ip=new Interpro();
     DBHandler db=new DBHandler();
       public static int lastID=-1;
       
    public Ppi(ArrayList<Node> node) throws SQLException{
        lastID=(int) db.getLastSeqID();
      ArrayList<Node> seqToRun=new ArrayList();
      ArrayList<Integer> index=new ArrayList();

            for(int i=0;i<node.size();i++){
          if(node.get(i).seqID==-1){
              seqToRun.add(node.get(i));
              index.add(i);
          }
      }
            if(seqToRun.size()>0)
      node=loadData(node,index);
            
            seqToRun=new ArrayList();
            index=new ArrayList();
      for(int i=0;i<node.size();i++){
          if(node.get(i).seqID==-1){
              seqToRun.add(node.get(i));
              index.add(i);
          }
      } 
             

      if(seqToRun.size()>0){
          System.out.println("Seq to run:"+seqToRun.size());
      ip.setSeqList(seqToRun);
       //seqToRun=ip.runInterpro();//result ids
       ip.start();
       seqToRun = ip.getSeqList();
       
       
        XMLReader xmlrd=new XMLReader(seqToRun);
        seqToRun=xmlrd.readXML();
        lastID=(int) db.getLastSeqID();
        seqToRun=db.addR(seqToRun);
      }
      for(int in=0;in<index.size(); in++){
          node.get(in).setSeqID(seqToRun.get(in).getSeqID());
          node.get(in).setDomainList(seqToRun.get(in).getDomainList());
         
      }
      db.db.close();

      nt=new Network(node);
          
    }
    
    public ArrayList<Node> loadData(ArrayList<Node> node, ArrayList<Integer> index){
        
        try {
                 
                     String strLine="";
                 
  for(int i=0;i<node.size();i++){
                       
                          String se=node.get(i).getAccNo();
                           ArrayList<String> domainList=new ArrayList();
                           int count=0;
                           FileInputStream fstream = null;
                           fstream = new FileInputStream("unipRot& IP.txt");
                            DataInputStream in = new DataInputStream(fstream);
                            BufferedReader br = new BufferedReader(new InputStreamReader(in));
                     while((strLine=br.readLine())!=null){
                         if(strLine.substring(0, 6).equalsIgnoreCase(se)){
                         String pfamID="";
                         count++;
                         pfamID=db.getpfamID(strLine.substring(8));
                          if(!(pfamID.equalsIgnoreCase("")))
                              {
                          domainList.add(pfamID);
                          }
                         }
                     }// end of while
                    

                      domainList=removeDuplicateDOmains(domainList);
                      node.get(i).setDomainList(domainList);
    
                      }// end of for

         
                 node=db.addR(node);

          } catch (SQLException ex) {
            Logger.getLogger(Ppi.class.getName()).log(Level.SEVERE, null, ex);
        }
          catch (IOException ex) {
                  Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
              }          
        return node;
    }
    public ArrayList<String> removeDuplicateDOmains(ArrayList<String> domainList1){
        ArrayList<String> domainList2=new ArrayList();
        for(int i=0;i<domainList1.size();i++){
            int count=0;
            for(int j=0;j<domainList2.size();j++){
                if(domainList1.get(i).equalsIgnoreCase(domainList2.get(j))){
                    count++;
                    break;
                }
            }
            if(count==0){
                domainList2.add(domainList1.get(i));
            }
        }
        
        return domainList2;
    }
    
    
}
