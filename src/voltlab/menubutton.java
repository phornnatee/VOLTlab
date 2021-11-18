package voltlab;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import static voltlab.VOLTlab.Digi;



public class menubutton extends JPanel{
    
    private String text = "";
    private JLabel button_label;
    public static final int width = 200;
    public static final int height = 75;

    
    menubutton(String  value){
        this.text = value;
        init();
        
    }
    private void init(){
        button_label = new JLabel(text);
        button_label.setFont(Digi);
        this.setBackground(Color.black);
        button_label.setForeground(Color.white);
        this.add(button_label);
        this.setVisible(true);
        this.setSize(width,height);
        this.unselected();
    }
    public void selected(){
        this.setBorder(new LineBorder(Color.RED,5));
    }
    public void unselected(){
        this.setBorder(new LineBorder(Color.BLACK,5));
    }
    public void apply(){
        this.setBorder(new LineBorder(Color.BLUE,5));
    }
    public void appl(){
        this.setBorder(new LineBorder(Color.WHITE,5));
    }
    
    
}
