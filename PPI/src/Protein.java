/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mani
 */
public class Protein {
    String seq_id;
    String description;
    String seq;
    int length;
    double mw;
    double pi;
    static int largest;  //saves the index of largest mw protein
    static int smallest; //saves the index of smallest mw protein
    int x;
    int y;
    float migrated_distance;

    public Protein(){
        
    }
    Protein(String accession, String seqString, String ds, int l) {
        seq_id = accession;
        seq = seqString;
        description = ds;
        length = l;
        mw=0;
        pi=0;
        largest=0;
        smallest=0;
        x = 0;
        y = 0;
        migrated_distance = 0f;
    }



    public void Protein (){
        seq_id="xxx";
        seq="xxx";
        description="xxx";
        length=0;
        mw=0;
        pi=0;
        largest=0;
        smallest=0;
     }


}
