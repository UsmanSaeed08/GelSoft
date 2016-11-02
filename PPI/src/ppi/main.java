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
public class main {
  
    public static void main(String[] args) throws SQLException{
      
              DBHandler db=new DBHandler();
              FileInputStream fstream = null;
           ArrayList<String> ar1=new ArrayList();
           ArrayList<String> ar2=new ArrayList();
           ArrayList<String> ar3=new ArrayList();
           try {
               fstream = new FileInputStream("pfamipmap.txt");
               DataInputStream in = new DataInputStream(fstream);
               BufferedReader br = new BufferedReader(new InputStreamReader(in));
               String strLine="";
               while((strLine = br.readLine())!=null){
                   String str="";
                   str=strLine.substring(0, 9);
                   ar1.add(str.trim());
                  System.out.println("IP :"+str.trim());
                  str="";
                  str=strLine.substring(11, 18);
                  System.out.println("PF :"+str.trim());
  
                  
                  ar2.add(str.trim());
                  str="";

                  str=strLine.substring(20);
                  System.out.println("Name :"+str.trim());
                  ar3.add(str.trim());

                   
               }
               db.addPfamIp(ar1, ar2, ar3);
           } catch (IOException ex) {
               Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
           }
              
              
              
              
              
    }
}
              
              
              
              
              
              
              
              
//            /  try {
//                  FileInputStream fstream = null;
//                  ArrayList<Node> node=new ArrayList();
//                  fstream = new FileInputStream("unipRot& IP.txt");
//                    DataInputStream in = new DataInputStream(fstream);
//                     BufferedReader br = new BufferedReader(new InputStreamReader(in));
//                     double count=0;
//                     System.out.println(count);
//
//                     String strLine="";
//                     count=0;
//                      Node n= new Node();
//                     while((strLine=br.readLine())!=null){
//                        n= new Node();
//                         if(strLine.substring(0,6)!=null && strLine.substring(8)!=null){
//                         n.accNo=strLine.substring(0,6);
//                         ArrayList<String> dr=new ArrayList();
//                         dr.add(strLine.substring(8));
//                         n.setDomainList(dr);
//                         node.add(n);
//                         }
//              }
//                     db.addR(node);
//                   
//
//          } 
//              catch (IOException ex) {
//                  Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//              }          

         

  
    
    


           




//              try{
//              FileInputStream istream= null;
//              istream=new FileInputStream("unipRot& IP.txt");
//               DataInputStream in = new DataInputStream(istream);
//             BufferedReader br = new BufferedReader(new InputStreamReader(in));
//             while( !(br.readLine().substring(0,6).equalsIgnoreCase("P0ABP4"))){
//                 
//             }
//             File output=new File("IP2.txt");
//             if(!output.exists()){
//                 output.createNewFile();
//             }
//             PrintStream out=new PrintStream(output);
//             String str="";
//             while((str=br.readLine())!=null){
//                 out.println(str);
//             }
//              
//        } catch (IOException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//
//}}    















         /*  FileInputStream fstream = null;
           ArrayList<String> ar1=new ArrayList();
           ArrayList<String> ar2=new ArrayList();
           ArrayList<String> ar3=new ArrayList();
           try {
               DB db=new DB();
               DBHandler dbb=new DBHandler();
               fstream = new FileInputStream("pFamIp.txt");
               DataInputStream in = new DataInputStream(fstream);
               BufferedReader br = new BufferedReader(new InputStreamReader(in));
               String strLine="";
               while((strLine = br.readLine())!=null){
                   int j=0;
                   String str="";
                   str=strLine.substring(0, 9);
   //                while(strLine.charAt(j)!=' '){
   //                    str=str+strLine.charAt(j);
   //                    j++;
   //                }
                   ar1.add(str.trim());
                  System.out.println("IP :"+str.trim());
   //               while(strLine.charAt(j)==' '){
   //                   j++;
   //               }
                  str="";
                  str=strLine.substring(11, 18);
   //                while(strLine.charAt(j)!=' '){
   //                    str=str+strLine.charAt(j);
   //                    j++;
   //                }
                  System.out.println("PF :"+str.trim());
   //               while(strLine.charAt(j)==' '){
   //                   j++;
   //               }
                  
                  ar2.add(str.trim());
                  str="";

                  str=strLine.substring(20);
                  System.out.println("Name :"+str.trim());
                  ar3.add(str.trim());

                   
               }
               dbb.addPfamIp(ar1, ar2, ar3);
           } catch (IOException ex) {
               Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
           }*/

    /*    FileInputStream fstream = null;
        ArrayList<String> ar1=new ArrayList();
        ArrayList<String> ar2=new ArrayList();
        ArrayList<String> ar3=new ArrayList();
        try {
            DB db=new DB();
            DBHandler dbb=new DBHandler();
            fstream = new FileInputStream("INTERACTION.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine="";
            while((strLine = br.readLine())!=null){
                int i=0;
                int j=0;
                String str="";

               System.out.println("str:"+strLine);
               j=strLine.indexOf('|');
               str=strLine.substring(0, j);
               System.out.println("First ID: "+str);
               ar1.add(str);
               str=strLine.substring(j+1);
               System.out.println("Now Str is: "+str);
               j=str.indexOf('|');
                System.out.println("j is :"+j);
                str=str.substring(0,j);
                System.out.println("2nd ID :"+str);
                ar2.add(str);
                str=strLine.substring(j+1);
                j=str.lastIndexOf('|');
                System.out.println("Last Index is:"+j);
                str=str.substring(j-2,j);
                ar3.add(str);
                System.out.println("Confidence :"+str);
                
            }
           dbb.addInteraction(ar1,ar2,ar3);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
      /*       try{
  // Open the file that is the first 
  // command line parameter
  FileInputStream fstream = new FileInputStream("pdb_sws_pfam.txt");
  int sid=dbb.getLastSeqID();
  int rid=dbb.getLastrID();
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  int i=1;
  //Read File Line By Line
          String acc="";
        ArrayList<String> pfam=new ArrayList();
        String str="";
        
  while ((str = br.readLine()) != null)   {
      String strLine="";
      //strLine=strLine.trim();
      System.out.println("str is:"+str);
      for(int k=0;k<str.length();k++){
          
          if(str.charAt(k)!=' '){
          strLine=strLine+str.charAt(k);
      }
      }
System.out.println("STRLINE IS:"+strLine);
  int indx=strLine.indexOf(":");
  if(indx<0)
      break;
  int indx2=strLine.indexOf(":", indx+1);
  if(indx2<0)
      break;
 // System.out.println("acc Start Index: "+indx);
 // System.out.println("acc Last Index: "+indx2);
  acc=strLine.substring(indx+1, indx2);
  System.out.println("acc: "+acc);
while(indx2<strLine.length()){
    indx=strLine.indexOf("@", indx2);
    if(indx<0)
        break;
    String pfamID=strLine.substring(indx2+1, indx);
    System.out.println("pfamID: "+pfamID);
    pfam.add(pfamID);
    indx2=strLine.indexOf(";", indx);
    if(indx2<0)
        break;
    // System.out.println("New Indx is: "+indx);
    //System.out.println("New Indx2 is: "+indx2);
}
sid++;
rid++;
    db.executeEntry("insert into tbsequence(sID,accNo)values("+sid+",'"+acc+"')");
    for(int j=0;j<pfam.size();j++){
        
  db.executeEntry("insert into tbresult(rID,sID,pfamID)values("+rid+","+sid+",'"+pfam.get(j) +"')");
  rid++;
    }
    pfam.clear();
  i++;
  }
  //Close the input stream
  in.close();
    }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }*/
    /*
     * 
     * 
     * 
     *         FileInputStream fstream = null;
        ArrayList<String> ar2=new ArrayList();
        ArrayList<String> ar1=new ArrayList();
        try {
            DB db=new DB();
            DBHandler dbb=new DBHandler();
            fstream = new FileInputStream("Negative DataSet.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine="";
            while((strLine = br.readLine())!=null){
               
                String p1,p2;
                int i=0;
                String str=strLine;
                     System.out.println(strLine.lastIndexOf('P'));

               System.out.println("str:"+strLine);
               i=strLine.lastIndexOf('P');
                System.out.println("i is :"+i);
                p1=str.substring(0, i-1);
                p2=str.substring(i);
                ar1.add(p1);
                System.out.println("p1:"+p1);
                ar2.add(p2);
                System.out.println("p2:"+p2);
                
            }
           dbb.addNegativeData(ar1, ar2);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
     */
