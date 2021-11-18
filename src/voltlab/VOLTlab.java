
package voltlab;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
//import static voltlab.Game.Digi;

public class VOLTlab extends JFrame implements ActionListener{
    Game stato;
    MenuState t123;
    static Font Digi,didi;
    help how;
    public VOLTlab() {
        try {
            Digi = Font.createFont(Font.TRUETYPE_FONT, new File("ttmp/DS-DIGI.ttf")).deriveFont(60f);
            didi = Font.createFont(Font.TRUETYPE_FONT, new File("ttmp/DS-DIGI.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ttmp/DS-DIGI.ttf")));
        } catch(Exception e){}
    this.t123 = new MenuState();
    this.add(t123);
    t123.BExit1.addActionListener(this);
    t123.BStart.addActionListener(this);
    t123.BE.addActionListener(this);
    this.setSize(1024,576);this.setDefaultCloseOperation(EXIT_ON_CLOSE);        

    }
    public void actionPerformed(ActionEvent e){
            if(e.getSource()== t123.BStart){
                try {
                    this.setLocationRelativeTo(null);
                    this.dispose();
                    this.stato = new Game();


                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(VOLTlab.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getSource() == t123.BExit1){
		System.exit(0);
            }
            else if(e.getSource() == t123.BE){
                t123.setVisible(false);
		this.how = new help();
                this.add(how);
                how.bac.addActionListener(this);
            }
            else if(e.getSource() == how.bac){
                        how.setVisible(false);
                       t123.setVisible(true);
                       this.remove(how);
            }
}
    public static void main(String[] args) throws FileNotFoundException  {
        JFrame gg = new VOLTlab();
        gg.setSize(1024,576);
        gg.setTitle("VOLTlab");
        gg.setResizable(false);
        gg.setVisible(true);
        
    }
    
}
