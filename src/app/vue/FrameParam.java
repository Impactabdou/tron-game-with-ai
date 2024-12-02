package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import util.JTextFieldWithOnlyNumbers;

import java.awt.*;
import java.awt.event.*;

public class FrameParam extends JFrame implements ActionListener{

    //Field .
    JTextFieldWithOnlyNumbers height, length, nbPlayer,nbTeam,nbrep;

    //Checkbox .
    JCheckBox RandomSpawn,NonRandomSpawn, oui,non;//oui et non pour taille de grille changeante
        


    //Bouttons pour envoyer les paramètres ou retour au menu.
    JButton send, retour;


    /**
     * Constructeur d'un objet pour créer la page de paramètres de création.
     */
    public FrameParam(){
        
        super("Page param");

        //Création des JButtons
        this.send = new JButton("Envoyer");
        this.retour = new JButton("Retour menu");

        //Ajout des actions.
        this.send.addActionListener(this);
        this.retour.addActionListener(this);




        //---------------  PARTIE PANEL DES PARAMETRES

        //Création des JTextFieldWithOnlyNumbers avec en paramètre le String du jlabel pour les paramètres de l'experimentation
        this.height = new JTextFieldWithOnlyNumbers("Hauteur de la grille :");
        this.length = new JTextFieldWithOnlyNumbers("Largeur de la grille : ");
        this.nbPlayer = new JTextFieldWithOnlyNumbers("Nombre de joueurs : ");
        this.nbTeam = new JTextFieldWithOnlyNumbers("Nombre d'équipes de joueurs : ");
        this.nbrep = new JTextFieldWithOnlyNumbers("Nombre de répétitions");
        
        //Checkbox 
        this.oui = new JCheckBox("Oui");
        this.non = new JCheckBox("Non");
        this.NonRandomSpawn = new JCheckBox("NonRandomSpawn");
        this.RandomSpawn = new JCheckBox("RandomSpawn");

        this.oui.addActionListener(this);
        this.non.addActionListener(this);
        this.RandomSpawn.addActionListener(this);
        this.NonRandomSpawn.addActionListener(this);

        //Création du Panel qui va contenir le choix du type d'IA
        JPanel optiType = new JPanel();
        JLabel optiTypeName = new JLabel("Taille de grille changeante : ");
        JLabel optiTypeName2 = new JLabel("Type de spawn : ");

        //Organisation du Panel des CheckBox
        optiType.setLayout(new GridLayout(0,1));

        //Organisation du Panel des CheckBox 
        optiType.setLayout(new GridLayout(0,1));
        optiType.add(optiTypeName);
        optiType.add(oui);
        optiType.add(non);
        optiType.add(optiTypeName2);
        optiType.add(RandomSpawn);
        optiType.add(NonRandomSpawn);


        //Panel principal des paramètres
        JPanel paramEntree = new JPanel();
        paramEntree.setLayout(new BoxLayout(paramEntree, BoxLayout.Y_AXIS));
        paramEntree.setBorder(new EmptyBorder(new Insets(50,20,20,20)));
        paramEntree.add(this.height);
        paramEntree.add(this.length);
        paramEntree.add(this.nbPlayer);
        paramEntree.add(this.nbTeam);
        paramEntree.add(this.nbrep);
        paramEntree.add(optiType);
        paramEntree.add(this.send);

        

        //------------ FIN PARTIE PANEL PARAMETRES

    

    
        //------------ PARTIE ORGA DE LA PAGE

        
        add(paramEntree, BorderLayout.CENTER);
        
        
    }



    @Override
    public void actionPerformed(ActionEvent a){
        Object objectSelec = a.getSource();

        //Bloc permettant d'avoir les JCheckBox valide.
        if(objectSelec == this.non){
            if (this.non.isSelected() == true){
                this.oui.setSelected(false);
            }
        }
        else if(objectSelec == this.oui){
            if (this.oui.isSelected() == true){
                this.non.setSelected(false);
            }
        }
        else if(objectSelec == this.RandomSpawn){
            if (this.RandomSpawn.isSelected() == true){
                this.NonRandomSpawn.setSelected(false);
            }
        }
        else if(objectSelec == this.NonRandomSpawn){
            if (this.NonRandomSpawn.isSelected() == true){
                this.RandomSpawn.setSelected(false);
            }
        }
                
        //Bloc pour l'envoi des paramètres et la création de l'expe
        if(objectSelec == this.send){

            Boolean checkIfComplete = true; // Booléen pour savoir si tous les champs sont remplis

            if(this.height.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            if(this.length.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            if(this.nbPlayer.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            if(this.nbTeam.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            
            Integer res = 0;

            if(this.oui.isSelected()){
                res += 10;
            }
            else if(this.non.isSelected()){
                res += 20;
            }
            else if(this.RandomSpawn.isSelected()){
                res += 1;
            }
            else if(this.NonRandomSpawn.isSelected()){
                res += 2;
            }
            else if(res==0){
                checkIfComplete = false;
            }
            
            
            //Tout est complet l'algo est créé 
            if(checkIfComplete){
                if(res == 22){
                    JFrame fen = new FrameParamPlayer(Integer.parseInt(this.height.getTextField().getText()),Integer.parseInt(this.length.getTextField().getText()),Integer.parseInt(this.nbPlayer.getTextField().getText()),Integer.parseInt(this.nbTeam.getTextField().getText()),Integer.parseInt(this.nbrep.getTextField().getText()),0,0 );
                    fen.setTitle("Suite des parametres");
                    fen.setSize(800, 800);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }else if(res==12){
                    JFrame fen = new FrameParamPlayer(Integer.parseInt(this.height.getTextField().getText()),Integer.parseInt(this.length.getTextField().getText()),Integer.parseInt(this.nbPlayer.getTextField().getText()),Integer.parseInt(this.nbTeam.getTextField().getText()),Integer.parseInt(this.nbrep.getTextField().getText()),0,1 );
                    fen.setTitle("Suite des parametres");
                    fen.setSize(800, 800);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }else if (res == 21){
                    JFrame fen = new FrameParamPlayer(Integer.parseInt(this.height.getTextField().getText()),Integer.parseInt(this.length.getTextField().getText()),Integer.parseInt(this.nbPlayer.getTextField().getText()),Integer.parseInt(this.nbTeam.getTextField().getText()),Integer.parseInt(this.nbrep.getTextField().getText()),1,0 );
                    fen.setTitle("Suite des parametres");
                    fen.setSize(800, 800);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }else{
                    JFrame fen = new FrameParamPlayer(Integer.parseInt(this.height.getTextField().getText()),Integer.parseInt(this.length.getTextField().getText()),Integer.parseInt(this.nbPlayer.getTextField().getText()),Integer.parseInt(this.nbTeam.getTextField().getText()),Integer.parseInt(this.nbrep.getTextField().getText()),1,1 );
                    fen.setTitle("Suite des parametres");
                    fen.setSize(800, 800);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }
                
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
