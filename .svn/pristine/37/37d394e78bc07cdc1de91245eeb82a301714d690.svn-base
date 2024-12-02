package vue;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import util.JTextFieldWithNumbersAndSeparator;
import util.JTextFieldWithOnlyNumbers;

import java.awt.*;
import java.awt.event.*;


public class FrameParamPlayer extends JFrame implements ActionListener {
    JTextFieldWithOnlyNumbers nbDeDecalage, profondeur;
    JCheckBox MaxN, SOS, Paranoid;//type d'algo
    JButton send, retour;
    JTextFieldWithNumbersAndSeparator teamWith;

    //param de la fenetre d'avant pour lancé les exp:
    int height, length, nbPlayer, nbTeam, nbrep, RandomSpawn, taillechangeante;

    public FrameParamPlayer(int height, int length,int nbPlayer,int nbTeam,int nbrep,int RandomSpawn,int taillechangeante){
        // il faut la pronfondeur de l'algo et si il est team friendly

        super("Page param player");

        //Création des JButtons
        this.send = new JButton("Envoyer");
        this.retour = new JButton("Retour menu");

        //Ajout des actions.
        this.send.addActionListener(this);
        this.retour.addActionListener(this);


        //Création des textField
        this.nbDeDecalage = new JTextFieldWithOnlyNumbers("Si la grille est changeante : \n Entrez combien ajouter (c'est le même nombre pour la hauteur et la largeur) :");
        this.profondeur = new JTextFieldWithOnlyNumbers("La profondeur de l'algorithme:");
        this.teamWith = new JTextFieldWithNumbersAndSeparator("Les numéros des algorithmes avec qui il est en team (il faut les séparer par ';'): \n Exemple : Pour le joueur 7: 1;2;4;10 (il y a 10 joueurs)");


        //Création des CheckBox 
        this.SOS = new JCheckBox("SOS");
        this.MaxN = new JCheckBox("MaxN");
        this.Paranoid = new JCheckBox("Paranoid");


        //Ajout des actions aux CheckBox
        this.SOS.addActionListener(this);
        this.MaxN.addActionListener(this);
        this.Paranoid.addActionListener(this);

        //Création du Panel qui va contenir le choix du type d'IA
        JPanel optiType = new JPanel();
        JLabel optiTypeName = new JLabel("Choix du type d'IA : ");

        //Organisation du Panel des CheckBox
        optiType.setLayout(new GridLayout(0,1));
        optiType.add(optiTypeName);

        //Organisation du Panel des CheckBox =======> type des players (CheckBox pour chaque player)
        optiType.setLayout(new GridLayout(0,1));
        optiType.add(optiTypeName);
        optiType.add(this.SOS);
        optiType.add(this.MaxN);
        optiType.add(this.Paranoid);


        JPanel paramEntree = new JPanel();
        paramEntree.setLayout(new BoxLayout(paramEntree, BoxLayout.Y_AXIS));
        paramEntree.setBorder(new EmptyBorder(new Insets(50,20,20,20)));
        paramEntree.add(optiType);
        paramEntree.add(this.profondeur);
        paramEntree.add(this.teamWith);
        paramEntree.add(this.nbDeDecalage);
        paramEntree.add(this.send);

        add(paramEntree, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objectSelec = e.getSource();

        //Bloc permettant d'avoir une seule JCheckBox valide.
        if(objectSelec == this.SOS){
            if (this.SOS.isSelected() == true){
                this.MaxN.setSelected(false);
                this.Paranoid.setSelected(false);  
            }
        }
        else if(objectSelec == this.Paranoid){
            if (this.Paranoid.isSelected() == true){
                this.SOS.setSelected(false);
                this.MaxN.setSelected(false);
            }
        }
        else if(objectSelec == this.MaxN){
            if (this.MaxN.isSelected() == true){
                this.Paranoid.setSelected(false);
                this.SOS.setSelected(false);
            }
        }
        
        //Bloc pour l'envoi des paramètres et la création de l'expe
        if(objectSelec == this.send){

            Boolean checkIfComplete = true; // Booléen pour savoir si tous les champs sont remplis
            Integer res = null;

            if(this.MaxN.isSelected()){
                res = 1;
            }
            else if(this.SOS.isSelected()){
                res = 2;
            }
            else if(this.Paranoid.isSelected()){
                res = 3;
            }
            if(res == null){
                checkIfComplete = false;
            }

            if(checkIfComplete){
                 //CREER ORCHESTRATOR
                  //lance les exp 
                /*Orchestrator orch = null;
                if(res == 0){
                    
                    orch = new Orchestrator("human", "human", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);
                    
                }
                else if(res == 1){
                    
                    orch = new Orchestrator("human", "iar", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);
                }
                else if(res == 2){

                    orch = new Orchestrator("human", "ia", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);

                }

                if(orch != null){
                    JFrame fen = new FrameGame(orch);
                    fen.setTitle("Tetris");
                    fen.setSize(1920, 1080);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }    
            */

                //wait en fonction des exp
                //lancement de la fenetre des graphs
            }
            // Tout n'est pas bon. Création d'une frame erreur.
            else{
                JFrame error = new JFrame();
                JLabel message = new JLabel("Veuillez rentrer tous les champs");
               
                error.add(new Background("gif", "images/gyro.gif"),BorderLayout.CENTER);
                error.add(message, BorderLayout.SOUTH);
                error.setSize(200,200);
                error.setVisible(true);
                error.setLocationRelativeTo(null);
                error.setResizable(false);
            }
        }
    }
    
}
