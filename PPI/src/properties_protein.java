/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class properties_protein {
  String protein_seq;
    int exc;    //the check for optical density that extinction coeficient is calculated
properties_protein(String seq){

    protein_seq = seq;
    exc = 0;
    make_aa_table();
    count_aa_populate();

}

/*
float Alanine = 1.8f;       //0
float Leucine = 3.8f;       //1
float Arginine = -4.5f;     //2
float Lysine = -3.9f;       //3
float Asparagine = -3.5f;   //4
float Methionine = 1.9f;    //5
float Aspartic = -3.5f;     //6
float Phenylalanine = 2.8f; //7
float Cysteine = 2.5f;      //8
float Proline = -1.6f;      //9
float Glutamine = -3.5f;    //10
float Serine = -0.8f;       //11
float Glutamic = -3.5f;     //12
float Threonine = -0.7f;    //13
float Glycine = -0.4f;      //14
float Tryptophan = -0.9f;   //15
float Histidine = -3.2f;    //16
float Tyrosine = -1.3f;     //17
float Isoleucine = 4.5f;    //18
float Valine = 4.2f;        //19

 *
 */


amino_properties[] obj;

private void make_aa_table(){
    obj = new amino_properties[20];
    for (int i =0; i <=19; i++){
        obj[i] = new amino_properties();
    }
    obj[0].populate('A', 1.8f);
    obj[1].populate('L', 3.8f);
    obj[2].populate('R', -4.5f);
    obj[3].populate('K', -3.9f);
    obj[4].populate('N', -3.5f);
    obj[5].populate('M', 1.9f);
    obj[6].populate('D', -3.5f);
    obj[7].populate('F', 2.8f);
    obj[8].populate('C', 2.5f);
    obj[9].populate('P', -1.6f);
    obj[10].populate('Q', -3.5f);
    obj[11].populate('S', -0.8f);
    obj[12].populate('E', -3.5f);
    obj[13].populate('T', -0.7f);
    obj[14].populate('G', -0.4f);
    obj[15].populate('W', -0.9f);
    obj[16].populate('H', -3.2f);
    obj[17].populate('Y', -1.3f);
    obj[18].populate('I', 4.5f);
    obj[19].populate('V', 4.2f);

}

private int search(char code){  //returns the index in aa table where desired aa is present
    int i =0;
    while(i<20){
        if (obj[i].code == code)
            return i;
        else
            i++;
    }
   return i; //exception
}
public void count_aa_populate(){
    int index = 0;
    int found_at = 0 ;
    while (index < protein_seq.length()){
        found_at = search(protein_seq.charAt(index));
        obj[found_at].no_of_this_aa = obj[found_at].no_of_this_aa + 1;
        index ++;
    }
}

public float GRAVY(){
    float grand_avg=0;
    int count = protein_seq.length();
    int i = 0;
    while (i<count-1){
        if (protein_seq.charAt(i)=='A'){

grand_avg = grand_avg + obj[search('A')].hydropathy;
        }
        else if(protein_seq.charAt(i) == 'C')
        {
grand_avg = grand_avg + obj[search('C')].hydropathy;
        }
        else if(protein_seq.charAt(i) == 'D')
        {
grand_avg = grand_avg + obj[search('D')].hydropathy;
}
else if (protein_seq.charAt(i)=='E'){
grand_avg = grand_avg + obj[search('E')].hydropathy;
}
else if (protein_seq.charAt(i)=='F'){
grand_avg = grand_avg + obj[search('F')].hydropathy;
        }
else if (protein_seq.charAt(i)=='G'){
    grand_avg = grand_avg + obj[search('G')].hydropathy;
    }
else if (protein_seq.charAt(i)=='H'){
    grand_avg = grand_avg + obj[search('H')].hydropathy;
}
else if (protein_seq.charAt(i)=='I'){
    grand_avg = grand_avg + obj[search('I')].hydropathy;
}
else if (protein_seq.charAt(i)=='K'){
    grand_avg = grand_avg + obj[search('K')].hydropathy;
}
else if (protein_seq.charAt(i)=='L'){
grand_avg = grand_avg + obj[search('L')].hydropathy;
}
else if (protein_seq.charAt(i)=='M'){
    grand_avg = grand_avg + obj[search('M')].hydropathy;
}
else if (protein_seq.charAt(i)=='N'){
grand_avg = grand_avg + obj[search('N')].hydropathy;
}
else if (protein_seq.charAt(i)=='P'){
    grand_avg = grand_avg + obj[search('P')].hydropathy;
}
else if (protein_seq.charAt(i)=='Q'){
    grand_avg = grand_avg + obj[search('Q')].hydropathy;
}
else if (protein_seq.charAt(i)=='R'){
    grand_avg = grand_avg + obj[search('R')].hydropathy;
}
else if (protein_seq.charAt(i)=='S'){
    grand_avg = grand_avg + obj[search('S')].hydropathy;
}
else if (protein_seq.charAt(i)=='T'){
    grand_avg = grand_avg + obj[search('T')].hydropathy;
}
else if (protein_seq.charAt(i)=='V'){
    grand_avg = grand_avg + obj[search('V')].hydropathy;
}
else if (protein_seq.charAt(i)=='W'){
    grand_avg = grand_avg + obj[search('W')].hydropathy;
}
else if (protein_seq.charAt(i)=='Y'){
    grand_avg = grand_avg + obj[search('Y')].hydropathy;
}
        i++;
    }
grand_avg = grand_avg/count;
    return grand_avg;
}

/*
 * Aliphatic index = X(Ala) + a * X(Val) + b * ( X(Ile) + X(Leu) )
where X(Ala), X(Val), X(Ile), and X(Leu) are mole percent (100 X mole fraction)
 * The coefficients a and b are the relative volume of valine side chain (a = 2.9)
and of Leu/Ile side chains (b = 3.9) to the side chain of alanine.
 *
 */

public float Aliphatic_index(){
    float a = 2.9f;
    float b = 3.9f;

    float xala = obj[0].no_of_this_aa;
    float xval = obj[19].no_of_this_aa;
    float xiso = obj[18].no_of_this_aa;
    float xleu = obj[1].no_of_this_aa;

    xala = (xala/protein_seq.length()*100);
    xval = (xval/protein_seq.length()*100);
    xiso = (xiso/protein_seq.length()*100);
    xleu = (xleu/protein_seq.length()*100);

    float aliphatic_index = xala + (a*xval);
    aliphatic_index = aliphatic_index + (b*(xiso + xleu));

    return aliphatic_index;

}
/*
 * E(Prot) = Numb(Tyr)*Ext(Tyr) + Numb(Trp)*Ext(Trp) + Numb(Cystine)*Ext(Cystine)
where (for proteins in water measured at 280 nm): Ext(Tyr) = 1490, Ext(Trp) = 5500, Ext(Cystine) = 125;
The absorbance (optical density) can be calculated using the following formula:
Absorb(Prot) = E(Prot) / Molecular_weight

Two values are produced by ProtParam based on the above equations, both for proteins measured in water at 280 nm.
 The first one shows the computed value based on the assumption that all cysteine residues appear as half cystines
 (i.e. all pairs of Cys residues form cystines), and the second one assuming that no cysteine appears as half cystine
 (i.e. assuming all Cys residues are reduced).
 */

float extinction_coefficient;

public float Extinction_Coefficient(){
    extinction_coefficient = 0;
    float tyr = obj[17].no_of_this_aa * 1490 ; //where 1490 is ext (tyr)
    float trp = obj[15].no_of_this_aa * 5500 ; //where 1490 is ext (trp)
    float cys1 = obj[8].no_of_this_aa * 125 ; //where 1490 is ext (cys)
    float cys2 = (obj[8].no_of_this_aa/2) * 125 ; //where 1490 is ext (cys)

    extinction_coefficient = tyr + trp + cys2;
    exc = 1;

    return extinction_coefficient;
}
/*
 * Absorb(Prot) = E(Prot) / Molecular_weight
 */
public float absorbance(float mw){

    float absorbance = 0;
    if (exc != 1){
        extinction_coefficient = Extinction_Coefficient();
        exc = 1;
    }
    return absorbance = extinction_coefficient/mw;

}
}
