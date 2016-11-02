/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;
import uk.ac.ebi.webservices.axis1.stubs.iprscan.InputParameters;

/**
 *
 * @author student
 */
public class Interpro extends Thread{
    private IPRScanClient client;
    private InputParameters p;
    private int len;
    private ArrayList<Node> seqList;
    public Interpro(){
       
        client=new IPRScanClient();
        p=new InputParameters();
        seqList=new ArrayList();
    }
    @Override
    public void run(){
        ArrayList id=new ArrayList();
        len=seqList.size();
        System.out.println("seq run in inter pro:"+len);
        int i=1;
        String id2="";
        try {
            p.setSequence(seqList.get(0).getSeq());
            System.out.println(p.getSequence());
        id2=(client.runApp("kanwal.naz03@gmail.com", "domain", p)) ;

    System.out.println("ID is:............................................"+id2);
    seqList.get(0).setXmlId(id2);
    seqList.get(0).select=true;
        System.out.println("job id assign in interpro:"+id2);
       //  id.add(id2);
        String[] result = client.getResults(id2, id2, "xml");
            while(i<len && client.checkStatus(id2).equals("FINISHED")){
            p.setSequence(seqList.get(i).toString());
            System.out.println("interpro in loop    "+i);
            id2=(client.runApp("kanwal.naz03@gmail.com", "domain", p)) ;
          //  id.add(id2);
            seqList.get(i).setXmlId(id2);
            seqList.get(i).select=true;
            result = client.getResults(id2, id2, "xml");
            i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Interpro.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (ServiceException ex) {
            Logger.getLogger(Interpro.class.getName()).log(Level.SEVERE, null, ex);
        }
          //return seqList;  
    }

    /**
     * @return the seqList
     */
    
    public ArrayList getSeqList() {

        return seqList;
    }
    

    /**
     * @param seqList the seqList to set
     */
    public void setSeqList(ArrayList<Node> seqList) {
        this.seqList = seqList;
    }
    

}
