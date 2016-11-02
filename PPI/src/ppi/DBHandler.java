/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class DBHandler extends Thread{
    private ResultSet s;
    public DB db;
    private ArrayList<Node> my_node;
  
    public DBHandler(){
        //db=new DB();
    }

        public ArrayList<Node> addR(ArrayList<Node> n) throws SQLException{
            double id=getLastSeqID();
            Double rid=getLastrID();
          
             db=new DB();

               for(int i=0;i<n.size();i++){
                   if(n.get(i).seqID==-1){
                 id++;
                 n.get(i).seqID=id;
                 System.out.print("ID: "+ id +" \t accNo: "+n.get(i).accNo);
                 db.i=0;
                db.executeEntry("insert into tbsequence(sID,sequence,accNo) values("+id+",'"+n.get(i).getSeq() +"','"+n.get(i).accNo+"')");
                if(db.i==0){
                for(int j=0;j<n.get(i).domainList.size();j++){
                    rid++;
                    System.out.println("rID: "+rid+" sID: "+id);
                      System.out.println();
                db.executeEntry("insert into tbresult(rID,sID,pfamID) values("+rid+","+id+",'"+n.get(i).domainList.get(j) +"')");
                }// end of for
                System.out.println();
                }//end of if
             }
               }
              db.close();
              return n;
        }
             
    
    public Double getSeqID(String acc){
        db=new DB();
         
        Double id=0.0;
        double idnt=0;
        try {
            
            s=db.execute("select * from tbsequence where accNo='"+acc+"'");
            while(s.next()){
                id=s.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
     return id;
    }
    
      public double getLastSeqID(){
          db=new DB();
        double id=0;
       
        try {
            s=db.execute("select * from tbsequence");
            while(s.next()){
                id=s.getDouble(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
     return id;
    }
        public Double getLastrID(){
            db=new DB();
            
        Double id=0.0;
        try {
            s=db.execute("select rID from tbresult");

            while(s.next()){
                id=s.getDouble(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
     return id;
    }
public ArrayList<Node> getDomainList(ArrayList<Node> n){
    db=new DB();
    for(int i=0;i<n.size();i++){
       
    ArrayList<String> arr=new ArrayList();
  try{
    s= db.execute("select * from tbresult where sID="+n.get(i).getSeqID() +"");
    while(s.next()){
               arr.add(s.getString(2)); 
    }    n.get(i).setDomainList(arr); 
  }
  catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
  db.close();
  return n;
}
public ArrayList getpfamDomainList(double id){
//    db=new DB();
       ArrayList<String> arr=new ArrayList();
  try{
    s= db.execute("select * from tbresult where sID="+id+"");
    while(s.next()){
            arr.add(s.getString("pfamID"));
           // System.out.println("pfamID:"+s.getString(1));
    }
  }
  catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  //db.close();
  return arr;
}

public String getpfamID(String id){
    db=new DB();
      String arr="";
     // System.out.println("IPID is: "+id);
  try{
    s= db.execute("select * from tbipfammap where ipID='"+id+"'");
    while(s.next()){
            arr=s.getString(2);
            // System.out.println("pfam ID is: "+arr);
            break;
    }
  }
  catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  db.close();
  return arr;
}

public boolean getInteraction(String s1,String s2){
    db=new DB();
   s= db.execute("select * from tbinteraction where ID1= '"+s1+"' AND ID2= '"+s2+"' || ID1= '"+s2+"' AND ID2= '"+s1+"'");
  
        try {
            if(s!=null)
            if(s.next()){
               
                //System.out.println("yes interaction");
                db.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
    return false;
}
public ArrayList getSeq(){
            db=new DB();
            ArrayList<String> seq=new ArrayList();
        try {
           
           s= db.execute("select sequence from tbsequence");
            while(s.next()){
            seq.add(s.getString("sequence"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
        return seq;
}
public ArrayList<Node> getNetwork(ArrayList<Node> nodes){
     db=new DB();
     for(int i=0;i<nodes.size();i++){
         ArrayList<Node> interaction=new ArrayList();
         ArrayList<Integer> pset=new ArrayList();
         ArrayList<Integer> nset=new ArrayList();
         ArrayList<String> conf=new ArrayList();
          int s1=nodes.get(i).getSeqID().intValue();
         for(int j=0;j<nodes.size();j++){
            
             int s2=nodes.get(j).getSeqID().intValue();
        try {
            s=db.execute("SELECT * from tbpinteraction where sID1="+s1+" AND sID2="+s2+"");
            while(s.next()){
                interaction.add(nodes.get(j));
                pset.add(s.getInt("domainMatch"));
                nset.add(s.getInt("negativeDomains"));
                conf.add(s.getString("Confidence"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         }//innerloop
         nodes.get(i).setInteraction(interaction);
         nodes.get(i).setDomainMatchCounter(pset);
         nodes.get(i).setNegativeDomainsCounter(nset);
         nodes.get(i).setConfidence(conf);
     }//outer loop
     for(int i=0;i<nodes.size();i++){
         nodes.get(i).displayNode();
     }
     
        db.close();
        return nodes;
}

public ArrayList<Sequence> isInSeq(ArrayList<Sequence> seq){
  db=new DB();
  for(int i=0;i<seq.size();i++){
        try {
            String acc=seq.get(i).acc;
            s=db.execute("select * from tbsequence where accNo='"+acc+"'");
            while(s.next()){
               seq.get(i).setsID(s.getDouble("sID"));
               
               break;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  db.close();
  return seq;
}
public int progress ;
    @Override
public void run (){
    db=new DB();
    System.out.println("In find interaction");
    progress =0;

    for(int i=0;i<my_node.size();i++){
         ArrayList<Integer> pset=new ArrayList();
         ArrayList<Integer> nset=new ArrayList();
         ArrayList<Node> Interaction=new ArrayList();
         ArrayList<String> conf=new ArrayList();
         
         if(i == my_node.size()/2){
             progress =50;
         }
         if(i == my_node.size()/4){
             progress =25;
         }
         
        for(int j=0;j<my_node.size();j++){
                try {
                    s=db.execute("SELECT * from tbsequence where sID IN("+my_node.get(i).getSeqID() +","+my_node.get(j).getSeqID()+") AND sID<="+Ppi.lastID+"");
                    int count1=0;
                    while(s.next()){
                        count1++;
                    }
                    
                    if(i!=j && count1==0){
                    ArrayList<String> ar1= my_node.get(i).getDomainList();
                    ArrayList<String>  ar2= my_node.get(j).getDomainList();
                     int count=0;
                     int count2=0;
                     String con="NA";
                     for(int k=0;k<ar1.size();k++){
                    for(int m=0;m<ar2.size();m++){
                                try {
                                    String s1=ar1.get(k).toString();
                                     String s2=ar2.get(m).toString();
                                   s= db.execute("select * from tbinteraction where ID1= '"+s1+"' AND ID2= '"+s2+"'|| ID1= '"+s2+"' AND ID2= '"+s1+"' ");
                                          while(s.next()){
                                              if(con!="HC"){
                                                  String gets=s.getString("Confidence");
                                                  if(gets.equalsIgnoreCase("HC"))
                                                      con="HC";
                                                  else if(gets.equalsIgnoreCase("MC"))
                                                      con="MC";
                                                  else if(con!="MC" && gets.equalsIgnoreCase("LC"))
                                                      con="LC";
                                              }
                                              count++;
                                              // System.out.println("matching domain count is: "+count);
                                          }
                                    s= db.execute("select * from negativeset where pfam1= '"+s1+"' AND pfam2= '"+s2+"' || pfam2= '"+s2+"' AND pfam1= '"+s1+"' ");
                                          while(s.next()){
                                             
                                              count2++;
                                               //System.out.println("Is Negative domain count is :"+count2);
                                          }
                                } //forth loop
                                catch (SQLException ex) {
                                    Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                           }  //forth loop
                    }//third loop
                     if(count>count2){
                           pset.add(count);
                           nset.add(count2);
                           conf.add(con);
                           Interaction.add(my_node.get(j));
                     }
                }//end of if
                } //secnd loop
                catch (SQLException ex) {
                    Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
        }//secnd loop
                for(int ins=0;ins<Interaction.size();ins++){
                try {
                    db.executeEntry("insert into tbpinteraction (sID1,sID2,domainMatch,negativeDomains,Confidence) values("+my_node.get(i).getSeqID().intValue() +","+Interaction.get(ins).getSeqID().intValue() +","+pset.get(ins)+","+nset.get(ins) +",'"+conf.get(ins) +"')");
                } catch (SQLException ex) {
                    Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        }//frst loop
    db.close();
}

public void findInteraction(ArrayList<Node> nodes){
    my_node = nodes;
    
    //return nodes;
    }
public void addNegativeData(ArrayList<String> ar1, ArrayList<String> ar2) throws SQLException{
    db=new DB();
    for(int i=0;i<ar1.size();i++){
     db.executeEntry("insert into negativeset (pfam1,pfam2) values ('"+ar1.get(i) +"','"+ar2.get(i) +"')");
    }
}
public void addInteraction(ArrayList<String> str1,ArrayList<String> str2,ArrayList<String> str3 ) throws SQLException{
    db=new DB();
    for(int i=0;i<str1.size();i++){
        db.executeEntry("insert into tbinteraction (sn,ID1,ID2,Confidence) values ("+i+",'"+str1.get(i) +"','"+str2.get(i) +"','"+str3.get(i) +"')"); 
    }
}
public void addPfamIp(ArrayList<String> str1,ArrayList<String> str2,ArrayList<String> str3) throws SQLException{
        db=new DB();
    for(int i=0;i<str1.size();i++){
        db.executeEntry("insert into tbipfammap (sn,pfamID,ipID,Domain) values ("+i+",'"+str2.get(i) +"','"+str1.get(i) +"','"+str3.get(i) +"')"); 
    }
}

    public ArrayList<Node> isInDb(ArrayList<Node> node) {
         db=new DB();
  
  for(int i=0;i<node.size();i++){
        try {
            String acc=node.get(i).getAccNo();
            s=db.execute("select * from tbsequence where accNo='"+acc+"'");
            while(s.next()){
                Double id=s.getDouble("sID");
               node.get(i).setSeqID(id);
               node.get(i).setAccNo(acc);//redundant
               node.get(i).setDomainList(getpfamDomainList(id));
                      // System.out.println("node id is: "+node.get(i).seqID);
               break;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  db.close();
        return node;
    }

    public String[][] getDomainInt(ArrayList domainList, ArrayList domainList1) {
        db=new DB();
        String[][] data=new String[domainList.size()*domainList1.size()][3];
        int x=0;
        System.out.println(".........IN DB..........");
        for(int i=0;i<domainList.size();i++){
            for(int j=0;j<domainList1.size();j++){
                try {
                    String dom1=domainList.get(i).toString() ;
                    String dom2=domainList1.get(j).toString();
                    System.out.println("FIrst: "+dom1+"\t Second: "+dom2);
                    s=db.execute("select * from tbinteraction where ID1='"+dom1+"' AND ID2='"+dom2+"' || ID1='"+dom2+"' AND ID2='"+dom1+"'  ");
                while(s.next()){
                    System.out.println(dom1+" interact with "+dom2);
                        data[x][0]=domainList.get(i).toString();
                        data[x][1]=domainList1.get(j).toString();
                        data[x][2]=s.getString("Confidence");
                        x++;
                                }
                        } catch (SQLException ex) {
                    Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
  String[][] data2=new String[x][3];
  for(int c=0;c<x;c++){
      data2[c][0]=data[c][0];
      data2[c][1]=data[c][1];
      data2[c][2]=data[c][2];
  }
        db.close();
        return data2;
    }


}

