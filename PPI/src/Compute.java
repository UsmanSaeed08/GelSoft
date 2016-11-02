/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//computes mass and pi

import java.util.ArrayList;
import org.biojava.bio.BioException;
import org.biojava.bio.proteomics.IsoelectricPointCalc;
import org.biojava.bio.proteomics.MassCalc;
import org.biojava.bio.seq.io.SymbolTokenization;
import org.biojava.bio.symbol.Alphabet;
import org.biojava.bio.symbol.AlphabetManager;
import org.biojava.bio.symbol.SimpleSymbolList;
import org.biojava.bio.symbol.SymbolList;
import org.biojava.bio.symbol.SymbolPropertyTable;

/**
 *
 * @author Mani
 */
public class Compute extends filing{
    
    Protein [] ar;

    
    public void Compute(){
        
    }

        public Protein[] populate(ArrayList x, int migration) throws BioException{

        //Protein[] ar = new Protein[x.size()];
        ar = new Protein[x.size()];
 
        for (int j = 0; j<= x.size()-1; j++){
            ar[j] = (Protein) x.get(j);
            //System.out.print(ar[j].seq_id + "\n" );
            
        }
        
        //Alphabet p = ProteinTools.getAlphabet(); //DNATools.getDNA();

                
        //make 3 functions which recieves the migration needed...this function calcs the mol wt and calls the respectiv migratio function
        
        if (migration == 0){
            calc();
        }
        else if (migration == 1){
            calc1();
        }
        else if (migration == 2){
            calc2();
        }
        return ar;

    }
        private void calc ()throws BioException{
            
            //for mig 0
            
            double max = 0;
            double min =0;

            for(int i=0;i<=ar.length-1;i++){
            
            //calc mw and pi

            MassCalc mc = new MassCalc(SymbolPropertyTable.AVG_MASS, true);
            IsoelectricPointCalc ic = new IsoelectricPointCalc();

            Alphabet p = AlphabetManager.alphabetForName("PROTEIN");
            SymbolTokenization pToke = p.getTokenization("token");

            SymbolList seq1 = new SimpleSymbolList(pToke, ar[i].seq);
            //System.out.print(ar[i].seq_id + "\n");
            
            ar[i].mw=mc.getMass(seq1);

            if (max<ar[i].mw){
                max = ar[i].mw;
                Protein.largest=i;
                if (i == 0){
                    min = ar[i].mw;
                }
            }
            if (min>ar[i].mw){
                min = ar[i].mw;
                Protein.smallest=i;
            }

            ar[i].pi=ic.getPI(seq1, true, true);
            //calc migration for each protein
            
            ar[i].migrated_distance = compute_migration_ten (ar[i].mw/1000);
        
            
        }
        }
        
        private void calc1 ()throws BioException{
            
            //for mig 1
            
            double max = 0;
            double min =0;

            for(int i=0;i<=ar.length-1;i++){
            
            //calc mw and pi

            MassCalc mc = new MassCalc(SymbolPropertyTable.AVG_MASS, true);
            IsoelectricPointCalc ic = new IsoelectricPointCalc();

            Alphabet p = AlphabetManager.alphabetForName("PROTEIN");
            SymbolTokenization pToke = p.getTokenization("token");

            SymbolList seq1 = new SimpleSymbolList(pToke, ar[i].seq);
            //System.out.print(ar[i].seq_id + "\n");
            
            ar[i].mw=mc.getMass(seq1);

            if (max<ar[i].mw){
                max = ar[i].mw;
                Protein.largest=i;
                if (i == 0){
                    min = ar[i].mw;
                }
            }
            if (min>ar[i].mw){
                min = ar[i].mw;
                Protein.smallest=i;
            }

            ar[i].pi=ic.getPI(seq1, true, true);
            //calc migration for each protein
            
            ar[i].migrated_distance = compute_migration_twelve (ar[i].mw/1000);
        
            
        }
        }
        
        private void calc2 ()throws BioException{
            
            //for mig 2
            
            double max = 0;
            double min =0;

            for(int i=0;i<=ar.length-1;i++){
            
            //calc mw and pi

            MassCalc mc = new MassCalc(SymbolPropertyTable.AVG_MASS, true);
            IsoelectricPointCalc ic = new IsoelectricPointCalc();

            Alphabet p = AlphabetManager.alphabetForName("PROTEIN");
            SymbolTokenization pToke = p.getTokenization("token");

            SymbolList seq1 = new SimpleSymbolList(pToke, ar[i].seq);
            System.out.print(ar[i].seq_id + "\n");
            
            ar[i].mw=mc.getMass(seq1);

            if (max<ar[i].mw){
                max = ar[i].mw;
                Protein.largest=i;
                if (i == 0){
                    min = ar[i].mw;
                }
            }
            if (min>ar[i].mw){
                min = ar[i].mw;
                Protein.smallest=i;
            }

            ar[i].pi=ic.getPI(seq1, true, true);
            //calc migration for each protein
            
            ar[i].migrated_distance = compute_migration_fifteen (ar[i].mw/1000);
        
        }
        }
        
        //make function for each equation separate and call tht accordingly
        
        private float compute_migration_ten (double mw){
            /*
             * For 10% below given equation was computed
                y = -3.93 ln (x) + 22.08
             */
            float y = (float) (Math.log(mw)* (-3.93f));
            y = y + 22.08f;
            return y;
        }
        private float compute_migration_twelve (double mw){
            /*
             
                For 12% below given equation was computed
                y = -2.61 ln (x) + 14.08
             */
            float y = (float) (Math.log(mw)* (-2.61f));
            y = y + 14.08f;
            
            return y;
            
            
        }
        private float compute_migration_fifteen(double mw){
            /*
                For 15% below given equation was computed
                y = -2.49 ln (x) + 13.12
             */
            
            float y = (float) (Math.log(mw)* (-2.49f));
            y = y + 13.12f;
            return y;
 
        }

}
