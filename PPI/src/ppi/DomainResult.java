/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

/**
 *
 * @author student
 */
public class DomainResult {
   
     private String type;
     private String IPRid;
     private String pfamID;
     public DomainResult(){
    
     type="";
     IPRid="";
     pfamID="";
     }


    public String getType() {
        return type;
    }

    /**
     * @param domain the domain to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public void printDomainReslut(){
        System.out.println("InterproID:"+IPRid);
         System.out.println("PfamID:"+getPfamID());
    }

    /**
     * @return the IPRid
     */
    public String getIPRid() {
        return IPRid;
    }

    /**
     * @param IPRid the IPRid to set
     */
    public void setIPRid(String IPRid) {
        this.IPRid = IPRid;
    }

    /**
     * @return the pfamID
     */
    public String getPfamID() {

        return pfamID;
    }

    /**
     * @param pfamID the pfamID to set
     */
    public void setPfamID() {
         DBHandler db=new DBHandler();
         pfamID=db.getpfamID(IPRid);
    }
    public void setPfamID(String id) {
       this.pfamID=id;
    }
}
