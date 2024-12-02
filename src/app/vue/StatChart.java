package vue;


import java.awt.*;
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.data.xy.*;


public class StatChart extends JPanel {

    //data contenue dans le graphique
    private XYSeriesCollection dataSet;

    //Les lignes du graphique
    private XYSeries max,mean,min;


    //Algorithme Génétique en cours
    private String data;

    

    /**
     * StatChart permet d'obtenir un Graphique sur l'avancement de l'algorithme génétique.
     * @param data Données à transformé en graph 
     */
    public StatChart(String data){
        super();//Deviens un JPanel
        this.data = data;

        this.setLayout(new BorderLayout());

        //Construction de dataSet
        this.dataSet = new XYSeriesCollection();
        this.max = new XYSeries("Maximum");
        this.mean = new XYSeries("Mediane");
        this.min = new XYSeries("Minimum");
        this.dataSet.addSeries(this.max);
        this.dataSet.addSeries(this.mean);
        this.dataSet.addSeries(this.min);

        
        JPanel chart = this.createChartPanel();
        chart.setLayout(new GridLayout());

        add(chart, BorderLayout.CENTER);
    }

    /**
     * createChartPanel est utile pour créer un nouveau chartPanel.
     * @return Un objet ChartPanel (graphique)
     */
    public JPanel createChartPanel(){
        String title = "Résultats optimisation";
        String xAxisLabel = "Tour d'optimisation";
        String yAxisLabel = "Taux de victoire (%)";

        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, this.dataSet);

        return new ChartPanel(chart);
    }

}