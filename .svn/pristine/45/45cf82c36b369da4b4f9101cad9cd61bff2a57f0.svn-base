package vue;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Background extends JPanel{

    //Type de contenu
    private String type;
    //Image finale
    private Image img;

    /** 
     * La classe background va créer un JPanel avec l'image passée en paramètre.
     * @param type String type d'image
     * @param path String path de l'image
     */
    public Background(String type, String path){
        super();// Initialisation du JPanel
        this.type = type;

        if(this.type.equals("image")){
            try {
                this.img = ImageIO.read(new File(path));
            } catch (IOException e) {
                System.out.println("jsuis la");
            }
        }
        else if(this.type.equals("gif")){
            this.img =  Toolkit.getDefaultToolkit().createImage(path);
        }
        
       
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( this.img, 0, 0, getWidth(), getHeight(), this); //déssine l'image sur le jpanel
        
    }
}