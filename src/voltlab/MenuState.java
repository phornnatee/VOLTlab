package voltlab;


import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuState extends JPanel {
    menubutton[] buttons;
    private final ImageIcon BG = new ImageIcon("ttmp/bg.jpg");
    private final ImageIcon help = new ImageIcon("ttmp/help.jpg");
    private final ImageIcon start = new ImageIcon("ttmp/start.jpg");
    private final ImageIcon ex = new ImageIcon("ttmp/ex.jpg");
    public JButton BStart = new JButton(start);
    public JButton BExit1 = new JButton(ex);
    public JButton BE = new JButton(help);
    
    MenuState(){
        setLayout(null);
        BExit1.setBounds(100, 270, 170,70);
        add(BExit1);
        BStart.setBounds(100,90,170,70); 
        add(BStart);
        BE.setBounds(100,180,170,70); 
        add(BE);
        
 }
    public static void main(String[] args){

        new MenuState();
        
   }
    @Override
    public void paintComponent(Graphics g){
        buttons=new menubutton[3];
        super.paintComponent(g);
        g.drawImage(BG.getImage(),0,0,1024,576,this);
        //g.setColor(Color.RED);
       // g.setFont(new Font("Verdana",Font.CENTER_BASELINE,20));
        //g.drawString("Trry",70,200);
        System.out.println("Showing menu");
        //g.setColor(GREEN);
        
        /*for(int i=0;i<3;i++){      
               buttons[i] = new menubutton(options[i]);
               buttons[i].setLocation(465,95+((menubutton.height * i))+(50*i));
               add(buttons[i]);
            }	 */
 }

}