/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

/**
 *
 * @author Administrator
 */
public class Sequence {
    private Double sID;
    String seq;
    String acc;
    private String xmlId;
     boolean select;
    public Sequence(String s1, String s2){
        acc=s1;
        seq=s2;
        sID=-1.0;
        xmlId="";
        select=false;
    }

    /**
     * @return the sID
     */
    public Double getsID() {
        return sID;
    }

    /**
     * @param sID the sID to set
     */
    public void setsID(Double sID) {
        this.sID = sID;
    }

    /**
     * @return the xmlId
     */
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
