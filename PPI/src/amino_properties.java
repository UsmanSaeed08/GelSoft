/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class amino_properties {
char code;
    float hydropathy;
    //float halflife;
    int no_of_this_aa;

    amino_properties(){
        code = 'x';
        hydropathy = 0;
        no_of_this_aa = 0;

    }

    public void populate(char cd, float hyd){
        code = cd;
        hydropathy = hyd;
        no_of_this_aa = 0;

    }
}
