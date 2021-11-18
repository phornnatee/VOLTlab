
package voltlab;
import javax.swing.JFrame;
public class mieen extends JFrame{
    MenuState homegames1 = new MenuState();

public mieen(){
    this.setSize(1024,576);
    this.add(homegames1);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
public static void main(String[] args) {
JFrame gg = new mieen();
gg.setSize(1024,576);
gg.setTitle("BOMBERMAN");
gg.setVisible(true);
  }
}