/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Usman
 */
public class XYplot {

    String dir;

    public XYplot(){

    }

    public XYplot(String dir1){
    dir = dir1;//+ "\\";
           // File obj = new File(dir);
        /*    if (!obj.mkdir()) {
                System.out.print("Error in directory path");
                return;
            }
         *
         */
}

public void xyplot(float gravy[]){



        XYSeries series = new XYSeries("XYGraph-Protein Properties");

        //make this count dynamic according to the no of proteins;
        int count =1;
        int add = 7;
        if (gravy.length <50)
            add = 7;
        else if(gravy.length <100 && gravy.length >50)
            add = 5;
        else if(gravy.length <100 && gravy.length >50)
            add = 3;


        for (int i = 0;i<=gravy.length-1;i++){
            series.add(count, gravy[i]);
            count = count + add;
        }


        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);

        //         Generate the graph

        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title

                "Proteins", // x-axis Label

                "GRAVY", // y-axis Label

                dataset, // Dataset

                PlotOrientation.VERTICAL, // Plot Orientation

                true, // Show Legend

                true, // Use tooltips

                false // Configure chart to generate URLs?
           );



        try {

            ChartUtilities.saveChartAsJPEG(new File(dir +"\\XYchart.jpg"), chart, 500,

                300);

        } catch (IOException e) {

            System.err.println("Problem occurred creating chart.");

        }
    }
}

