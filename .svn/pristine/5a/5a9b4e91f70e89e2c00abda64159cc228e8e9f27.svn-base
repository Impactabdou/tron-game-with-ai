package vue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import util.CSV;

public class FrameGraph extends JFrame {
    // utilisation de matplotlib 
    // graph en fonction du nb team et nb players et de victoire
    // un graph par taille de map et courbes différentes en fonctions des algo utiliser 

//jfreechart lib
    
    


    /*faire une mini cartographie des meilleure position sur la grille (rouge meilleure bleue nulle)
     List<Liste<String>> [coord][nb fois qu'elle apparait] liste triée 
     & = une couleur de case
     maxic = nb fois apparition le plus grand : & : une seule case ou plusieures si meme nombre
     moy de moy : && : plusieures cases a justifier avec des soustraction pour tester le plus proche de quelle moyenne 
     minic = nb fois apparition le plus faible : & : plusieures cases a justifier avec des soustraction pour tester le plus proche de quelle moyenne 
     moy de moy : && : plusieures cases a justifier avec des soustraction pour tester le plus proche de quelle moyenne 
     moyc = moyenne entre maxi et mini : & : une seule case ou plusieures si meme nombre
     */
    //Cartographie :
    protected int maxiC = 0;
    protected int miniC = 0;

    public FrameGraph(){
        // Data CSV :
        CSV csv = new CSV();
        csv.readCSVFile();
        List<List<String>> records = csv.getRecords();
        Map<String,Integer> listApparition = new HashMap<>();
        Integer nbtmp = 0;
        // MatplotLib : 



        //panel des graphs :
        JPanel graph = new JPanel();
        graph.setBackground(Color.GRAY);


        //Cartographie :
        for(int i=1; i<records.size();i++ ){
            //check si c'est bien tous les meme conf de partie
            if(records.get(i).get(1)==records.get(-1).get(1) && records.get(i).get(2)==records.get(-1).get(2) && records.get(i).get(3)==records.get(-1).get(3) && records.get(i).get(4)==records.get(-1).get(4)){
                String tmp = records.get(i).get(6);
                if(listApparition.containsKey(tmp)){
                    nbtmp = listApparition.get(tmp) + 1;
                    if(maxiC<nbtmp){
                        maxiC = nbtmp;
                    }
                    listApparition.replace(tmp , nbtmp);
                }else{
                    listApparition.put(tmp, 1);
                    miniC = 1;
                }
            }
            
        }

        //taille de la grille
        int h = Integer.parseInt(records.get(-1).get(1).substring(0, 2)) ;
        int l = Integer.parseInt(records.get(-1).get(1).substring(3, 6)) ;

        Integer[][] grille = new Integer[h][l];
        // grille d'info pour connaitre les couleur a placé pour cartographie
        for(int i = 0;i<h;i++){
            for(int j = 0;j<l;j++){
                if(listApparition.containsKey("("+i+","+j+")")){
                    grille[i][j]=listApparition.get("("+i+","+j+")");
                }
                else{
                    grille[i][j]=0;
                    miniC = 0;
                }
            }
        }
        //moyenne pour les différentes couleurs a utilisés
        int moyC = (maxiC + miniC)/2;
        int moymaxp = ((maxiC +moyC)/2+maxiC)/2;
        int moymaxm = ((maxiC +moyC)/2+moyC)/2;
        int moyminip = ((miniC +moyC)/2+moyC)/2;
        int moyminim = ((miniC +moyC)/2+miniC)/2;

        //panel de la cartographie

        JPanel cartographie = new JPanel();
        Border blackline = BorderFactory.createLineBorder(Color.black,1); 
        for(int i = 0; i<h;i++){
            for(int j = 0;j<l;j++){
                JPanel ptest = new JPanel(new GridLayout(h,l));
                ptest.setBorder(blackline);
                if(grille[i][j]==miniC){
                    ptest.setBackground(new Color(48,35,161)); //bleu foncé 
                }else if (grille[i][j]<=moyminim && grille[i][j]>miniC){
                    ptest.setBackground(new Color(81,68,190)); // bleu un peu moins foncé
                }else if (grille[i][j]>moyminim && grille[i][j]<=moyminip){
                    ptest.setBackground(new Color(70,137,186));//bleu clair
                }else if (grille[i][j]>moyminip && grille[i][j]<=moyC){
                    ptest.setBackground(new Color(70,186,158));//vert bleué
                }else if (grille[i][j]>moyC && grille[i][j]<=moymaxm){
                    ptest.setBackground(new Color(82,217,19));//vert
                }else if (grille[i][j]>moymaxm && grille[i][j]<=moymaxp){
                    ptest.setBackground(new Color(182,235,28));//jaune
                }else if (grille[i][j]>moymaxp && grille[i][j]<maxiC){
                    ptest.setBackground(new Color(237,146,14));//orange
                }else{
                    ptest.setBackground(new Color(224,74,13));//rouge
                }
                cartographie.add(ptest);
            }
            
        }
        cartographie.setBorder(blackline);

        //Caractéristiques de la Frame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setResizable(true);
        setVisible(true);

        JSplitPane jsPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graph, cartographie);
        jsPane.setResizeWeight(0.7);
        jsPane.setEnabled(true);
    }
    
}
