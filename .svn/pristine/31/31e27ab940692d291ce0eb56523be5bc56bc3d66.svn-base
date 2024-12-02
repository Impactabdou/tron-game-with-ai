package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class FrameEx extends JFrame{
    private JButton btnQuit;

    /**
     * Constructeur de la page présentant les regles.
     */
    public FrameEx(){
        setName("Explication");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,0,100));
        panel.setBorder(new EmptyBorder(new Insets(getHeight()/10,SwingConstants.CENTER,getHeight()/10,0)));
        panel.setBackground(new Color(0, 0, 0, 0));

        // bouton de retour
        btnQuit = new JButton("Retour");
        
        btnQuit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
          JFrame fen = new FrameMenu();
          fen.setTitle("Menu Page");
          fen.setSize(800, 800);
          fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          fen.setVisible(true);
          setVisible(false);
          dispose();
        }});
        //zone de texte non-modifiable pour les explications
        JTextArea area=new JTextArea("Le jeu de Tron est une variante multi-joueurs du célèbre jeu du serpent. Sur une grille de taille fixe, chaque joueur contrôle un point qui se déplacer dans les quatres directions (nord, est, sud et ouest). À chaque déplacement, le joueur laisse derrière lui un mur infranchissable.\r\n Le but du jeu est d’être le dernier à survivre,\r\n sachant qu’une collision avec un mur, l’adversaire ou un bord du plateau entraîne une défaite.");  
        area.setBounds(125,150, 750,150);
        area.setEditable(false);
        area.setLineWrap(true);

        panel.add(area);
        panel.add(btnQuit);
        
        //source de l'image LES CHRONIQUES DE VENDETTA:
        Background bg = new Background("image", "images/tron.jpg");
        setContentPane(bg);
        add(panel);
        setVisible(true);
        
    }
}
