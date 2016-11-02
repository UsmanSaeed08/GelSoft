/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class Polarity_calculator {

    public int a;
    public int b;
    public int h;
    public int u;


    Polarity_calculator(){
        a = 0;
        b = 0;
        h = 0;
        u = 0;
    }

    public void calculate(String protein_seq){
        int count = protein_seq.length();
        int i = 0;
    while (i<count){
        if (protein_seq.charAt(i)=='A'){
            h = h+1;
        }
        else if(protein_seq.charAt(i) == 'C')
        {
            u = u+1;
        }
        else if(protein_seq.charAt(i) == 'D')
        {
            a++;
        }
        else if (protein_seq.charAt(i)=='E'){
                a++;
        }
        else if (protein_seq.charAt(i)=='F'){
                h++;
                }
        else if (protein_seq.charAt(i)=='G'){
                u++;
            }
        else if (protein_seq.charAt(i)=='H'){
                b++;
        }
        else if (protein_seq.charAt(i)=='I'){
                h++;
        }
        else if (protein_seq.charAt(i)=='K'){
                b++;
        }
        else if (protein_seq.charAt(i)=='L'){
                h++;
        }
        else if (protein_seq.charAt(i)=='M'){
                h++;
        }
        else if (protein_seq.charAt(i)=='N'){
                u++;
        }
        else if (protein_seq.charAt(i)=='P'){
            h++;
        }
        else if (protein_seq.charAt(i)=='Q'){
            u++;
        }
        else if (protein_seq.charAt(i)=='R'){
            b++;
        }
        else if (protein_seq.charAt(i)=='S'){
            u++;
        }
        else if (protein_seq.charAt(i)=='T'){
            u++;
        }
        else if (protein_seq.charAt(i)=='V'){
            h++;
        }
        else if (protein_seq.charAt(i)=='W'){
            h++;
        }
        else if (protein_seq.charAt(i)=='Y'){
            u++;
        }
        i++;
    }
    }

}

