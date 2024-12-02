package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextFieldWithOnlyNumbers extends JPanel{
    
   private JTextField jText;
   private JLabel jLabel;

    /**
     * Constructeur d'un objet JTextFieldWithOnlyNumbers.
     * Ce JTextField n'accepte que les chiffres.
     * @param name String représentant son nom
     */
    public JTextFieldWithOnlyNumbers(String name){
        super();

        this.jText = new JTextField();
        this.jLabel = new JLabel(name);
        setLayout(new GridLayout(0,1));
        add(this.jLabel);
        this.jText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if((c<'0') || (c>'9')){
                    e.consume();
                }
            }
        });
        add(this.jText);
   }

    /**
     * Accesseur au jtextfield.
     * @return JTextField jText
     */
    public JTextField getTextField(){
        return this.jText;
    }
}
