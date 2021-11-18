package voltlab;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import static voltlab.VOLTlab.Digi;


public class VOLTbutton extends JPanel{
    
    public int button_value;
    private char button_sign = '-';
    private JLabel button_label;
    public static final int width = 58;
    public static final int height = 75;
    public int check=-1;
    public boolean cc = false;
    
    VOLTbutton(int value){
        this.button_value = value;
        init();
        
    }
    
    VOLTbutton(char sign){
        this.button_value = 0;
        this.button_sign = sign;
        init();
        
    }
    
    private void init(){
        button_label = new JLabel();
        button_label.setFont(Digi);
        button_label.setForeground(Color.white);
        
        if(button_sign == '-'){
            this.setBackground(Color.black);
            button_label.setText(button_value+"");
        }
        else if(button_sign == 61){
            this.setBackground(Color.black);
            button_label.setText("=");
            button_value=2;
            button_label.setFont(new Font("Verdana",0,50));
        }
        else if(button_sign == 79){
            this.setBackground(Color.black);
            button_label.setText("O");
            button_value=1;button_label.setFont(new Font("Verdana",0,50));
        }
        else {
            this.setBackground(Color.black);
            button_label.setText("X");
            button_value=10;button_label.setFont(new Font("Verdana",0,50));
        }
        
        this.add(button_label);
        this.setVisible(true);
        this.setSize(width,height);
        this.unselected();
    }
    
    public void nextround(){
        this.setVisible(false);
    }
    
    public void render(){
        this.setVisible(true);
    }
    
    public void selected(){
        this.setBorder(new LineBorder(Color.RED,5));
    }
    
    public void unselected(){
        this.setBorder(new LineBorder(Color.BLACK,5));
    }
    public void apply(){
        this.setBorder(new LineBorder(Color.BLUE,5));
        check=1;
        cc=true;
    }
    public void appl(){
        this.setBorder(new LineBorder(Color.WHITE,5));
    }
    
    
}
