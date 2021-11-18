
package voltlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class help extends JPanel{
   ImageIcon BG = new ImageIcon("ttmp/bg2.jpg");
   ImageIcon back = new ImageIcon("ttmp/bac.jpg");
    JTextArea tuto = new JTextArea();
    JButton bac = new JButton(back);
    help(){
        this.setLayout(null);
        /*bung.setBackground(Color.red);
        bung.setBounds(90, 50, 770, 340);*/
       tuto.setText("You need to hack the alarm system and let no one know\n"
               + "if you fail 3 time ,they will found you\n"
               + "you have 25 second per stage\n"
               + "if you selected the symbol you can't change it\n"
               + "This game use only Keyboard to control \n"
               + "\n      W and S for select up and down\n "
               + "     D for select symbol side or confirm\n"
               + "      A for cancel selecting symbol\n"
               + "      O is x1 | = is x2 | X is x10\n");
       tuto.setForeground(Color.WHITE);
       tuto.setFont(new Font("Verdana",0,25));
       tuto.setBackground(new Color(0,0,0,0));
       tuto.setBounds(90, 50, 770, 340);
       tuto.setEditable(false);
      bac.setBounds(100,400,170,70); 
       add(bac);
       add(tuto);
       //add(bung);
    }
    public void paintComponent(Graphics g){
        g.drawImage(BG.getImage(),0,0,1024,576,this);
    }
    public static void main(String[] args){

        new help();
        
   }
}
