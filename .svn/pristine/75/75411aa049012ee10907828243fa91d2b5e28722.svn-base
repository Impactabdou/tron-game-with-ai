package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class FrameMenu extends JFrame implements ActionListener{
    private JButton btn1, btn2,btnQuit;


    /**
     * Constructeur de la page de base de notre aplication.
     */
    public FrameMenu(){
        setName("Menu Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        //paramétrage de l'affichage du Panel principale
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,0,100));
        panel.setBorder(new EmptyBorder(new Insets(getHeight()/10,SwingConstants.CENTER,getHeight()/10,0)));
        panel.setBackground(new Color(0, 0, 0, 0));

        //Création des boutons pour faire jouer les IA, les Explications et Quitter.
        btn1 = new JButton("Jouer");
        btn2 = new JButton("Explications");
        btnQuit = new JButton("Quitter");

        //paramétrage des boutons.
        btn1.setBackground(Color.red);
        btn1.addActionListener(this);
        panel.add(btn1);

        btn2.setBackground(Color.magenta);
        btn2.addActionListener(this);
        panel.add(btn2);

        btnQuit.setBackground(Color.green);
        btnQuit.addActionListener(this);
        panel.add(btnQuit);

        //source de l'image Ubisoft:
        Background bg = new Background("image", "images/tron.jpg");
        setContentPane(bg);
        //rendre la Frame visible et ajouter Panel à la Frame
        
        add(panel);
        setVisible(true);
    }

    //Ajout des fonctions pour les boutons
    public void actionPerformed(ActionEvent e){

        //Premier Bouton
        if(e.getSource() == btn1){
            //creation de la JFrame et de son paramétrage
            JFrame fen = new FrameParam();
            fen.setTitle("Jeu de Tron");
            fen.setSize(800, 800);
            fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fen.setVisible(true);
            //On ferme cette fenetre derière
            setVisible(false);
            dispose();
        }

        //Second Bouton
        else if(e.getSource()==btn2){
            //Fenetre d'explication
            JFrame fen = new FrameEx();
            fen.setTitle("Explications");
            fen.setSize(800, 800);
            fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fen.setVisible(true);
            //On ferme cette fenetre derière
            setVisible(false);
            dispose();
        }

        //Bouton d'Exit
        else if(e.getSource() == btnQuit){
            dispose();
        }
    }


    
}
