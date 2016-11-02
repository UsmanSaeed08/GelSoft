/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Node {
     Double seqID;
     String accNo;
     private String seq;
     ArrayList<String> domainList;
     ArrayList<String> type;
             
      ArrayList<Node> interaction;
     public ArrayList<Integer> domianMatchCounter;
      public ArrayList<Integer> negativeDomainCounter;
      private ArrayList<String> confidence;
     DBHandler db;
     private String xmlId;
     boolean select;
     
public Node(){
         xmlId="";
        select=false;
        seq="";
        seqID=-1.0;
        accNo="";
        db=new DBHandler();
        domainList=new ArrayList<String>();
        interaction=new ArrayList<Node>();
        domianMatchCounter=new ArrayList();
        negativeDomainCounter=new ArrayList();
        confidence=new ArrayList();
        
}




    /**
     * @return the seqID
     */
    public Double getSeqID() {
        return seqID;
    }

    /**
     * @param seqID the seqID to set
     */
    public void setSeqID(Double seqID) {
        this.seqID = seqID;
    }

    /**
     * @return the domainList
     */
    public ArrayList getDomainList() {
        return domainList;
    }

    /**
     * @param domainList the domainList to set
     */
    public void setDomainList(ArrayList<String> domianList1) {
        
        domainList=domianList1;
//        }
        
    }

    /**
     * @return the interaction
     */
    public ArrayList<Node> getInteraction() {
        return this.interaction;
    }

    /**
     * @param interaction the interaction to set
     */
    public void setInteraction(ArrayList<Node> interaction1) {
        this.interaction = interaction1;
    }
    public void setDomainMatchCounter(ArrayList<Integer> counter){
        this.domianMatchCounter=counter;
    }
     public void setNegativeDomainsCounter(ArrayList<Integer> counter){
        this.negativeDomainCounter=counter;
    }
    public void displayNode(){
        System.out.println();
        System.out.println("-------NODE----------");
        System.out.println("Sequnece ID:"+seqID);
        System.out.println("accNO:"+accNo);
        System.out.println("..Domain..");
        for(int i=0;i<domainList.size();i++){
            System.out.println(domainList.get(i).toString());
        }System.out.println("||INTERACTING NODES||");
          for(int i=0;i<interaction.size();i++){
            System.out.print(interaction.get(i).getSeqID()+"\t");
            System.out.println(confidence.get(i));
        }
    }

    /**
     * @return the accNo
     */
    public String getAccNo() {
        return accNo;
    }

    /**
     * @param accNo the accNo to set
     */
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    /**
     * @return the confidence
     */
    public ArrayList<String> getConfidence() {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(ArrayList<String> conf) {
        for(int i=0;i<conf.size();i++){
            if(conf.get(i).equalsIgnoreCase("HC") ||conf.get(i).equalsIgnoreCase("MC") ){
                confidence.add("Strong");
            }
            else{
                confidence.add("Weak");
            }
        }
    }

    /**
     * @return the seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
       public String getXmlId() {
        return xmlId;
    }

    /**
     * @param xmlId the xmlId to set
     */
    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }
}

    

    

