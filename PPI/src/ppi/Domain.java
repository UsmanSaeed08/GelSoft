/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

/**
 *
 * @author Administrator
 */
public class Domain {
     private String mID;
     private String mName;
     private int dStart;
     private int dEnd;
     private String IPRid;
  public Domain(){
     mID="";
     mName="";
     dStart=0;
     dEnd=0;
     IPRid="";
     }
  public String getmID() {
        return mID;
    }

    /**
     * @param mID the mID to set
     */
    public void setmID(String mID) {
        this.mID = mID;
    }

    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getdStart() {
        return dStart;
    }

    /**
     * @param dStart the dStart to set
     */
    public void setdStart(int dStart) {
        this.dStart = dStart;
    }

    /**
     * @return the dEnd
     */
    public int getdEnd() {
        return dEnd;
    }

    /**
     * @param dEnd the dEnd to set
     */
    public void setdEnd(int dEnd) {
        this.dEnd = dEnd;
    }
    
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
     * @return the IPRid
     */
    
    public void printDomain(){
        System.out.println("Protein ID:"+mID);
        System.out.println("Method Name:"+mName);
        System.out.println("Domain Start:"+dStart);
        System.out.println("Domain End:"+dEnd);
        System.out.println("InterproID:"+IPRid);
    }


    
}
