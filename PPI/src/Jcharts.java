/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Usman
 */
public class Jcharts {
    String dir;

    //send jcharts the dir + seq id
    Jcharts(){

    }
    Jcharts(String dir1, String seqid){

        dir = dir1 ;//+"/"+seqid; //+ "/" +seqid;

            //File obj = new File("dir");
           /* File obj = new File(dir);
            if (!obj.mkdir()) {
                System.out.print("Error in directory path");
                return;
            }*/

    }

public void piechart(String seqid, float len, float acidic, float basic, float hydrophobic,float uncharged){


    float a = (acidic/len)*100;

    float b = (basic/len)*100;
    float h = (hydrophobic/len)*100;
    float u = (uncharged/len)*100;


        DefaultPieDataset pieDataset = new DefaultPieDataset();

        pieDataset.setValue("Hydrophobic", new Integer((int)h));

        pieDataset.setValue("Acidic", new Integer((int)a));

        pieDataset.setValue("Basic", new Integer((int)b));

        pieDataset.setValue("Uncharged", new Integer((int)u));

        JFreeChart chart = ChartFactory.createPieChart("Protein Polarity Analysis",pieDataset,true,true,false);

        try {

            ChartUtilities.saveChartAsJPEG(new File(dir+"\\"+ seqid +"piechart.jpg"), chart, 500,300);

        } catch (Exception e) {

            System.out.println("Problem occurred creating chart.");

        }

    }


public void bargraph(String seqid, float len, float acidic, float basic, float hydrophobic,float uncharged){
            // Create a simple Bar chart
        float a = (acidic/len)*100;
        float b = (basic/len)*100;
        float h = (hydrophobic/len)*100;
        float u = (uncharged/len)*100;


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(a, "Percentage", "acidic");

        dataset.setValue(b, "Percentage", "basic");

        dataset.setValue(h, "Percentage", "hydrophobic");

        dataset.setValue(u, "Percentage", "uncharged");

        //dataset.setValue(12, "Profit", "Fred");

        JFreeChart chart = ChartFactory.createBarChart("Protein Polarity Bargraph",

                "aa Type", "Percentage", dataset, PlotOrientation.VERTICAL, false,

                true, false);

        try {

            ChartUtilities.saveChartAsJPEG(new File(dir+"\\"+ seqid +"bargraph.jpg"), chart, 500,

                300);

        } catch (IOException e) {

            System.err.println("Problem occurred creating chart.");

        }

}

}

