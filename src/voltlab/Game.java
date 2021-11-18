package voltlab;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static voltlab.VOLTlab.Digi;
import static voltlab.VOLTlab.didi;

public final class Game extends JFrame implements KeyListener,ActionListener{
    VOLTbutton[] number_buttons;
    VOLTbutton[] sign_buttons;
    private int current_number = 0;
    private int current_sign = 0;
    boolean clicked=false;
    int result=0,goal,HP=3,wincount=0;
    JLabel score,w,life,overed;
    static int [][] data;
    boolean [][] check = new boolean[3][2];
    static int [] soom;
    int run=0;
    
    //static Font Digi,didi;
    
    boolean overcut=false,pause=false,starttrigger=false,win=false;
    boolean cutscene_trigger = false;
    int cutscene_counter = 0,overc=0,startc=0,winc=0;
    
    //for precision time
    int millisec_game = 0;
    int millisec_cutscene = 0;
    
    JLabel counterLabel;
    Timer timer;	
    int second, minute;
    String ddSecond, ddMinute;	
    DecimalFormat dFormat = new DecimalFormat("00");
    
    File error = new File("ttmp/error.wav");
    File Select = new File("ttmp/Select.wav");
    File selected = new File("ttmp/selected.wav");
    File n1 = new File("ttmp/next1.wav");
    File n2 = new File("ttmp/next2.wav");
    File c = new File("ttmp/can.wav");
    File soundend = new File("ttmp/end3.wav");
    File pass = new File("ttmp/pass.wav");
    File soundstart = new File("ttmp/start5.wav");
    String ovr = "endv7.gif",strto = "start.gif",good = "good.gif";
    File back = new File("ttmp/Back.jpg");
    File god = new File("ttmp/good.wav");
    
    JPanel show,cutscene,sho,over,overpanel; 
    //JFrame main;
    
    ImageIcon bac = new ImageIcon("ttmp/Back.jpg");
    JLabel bak = new JLabel(bac);JPanel pauss =new JPanel();
        //MenuState t123 = new MenuState();
    JButton reset = new JButton("Restart");


void show(int a){
    /*show = new JPanel(); 
    sho = new JPanel();
    sho.setBounds(0, 0, 1024, 576);
    show.setVisible(true);*/
    JLabel cutscene_Label;
    Icon imgIcon; 
    if(HP==0){
        over(1);cutscene_trigger = false;
    }
    else{
        cutscene_counter = 0;
        cutscene_trigger = true;
        if(a==1){
            imgIcon = new ImageIcon(this.getClass().getResource("voltage_success.gif"));
            System.out.println("Playing success");
        }
        else{
            imgIcon = new ImageIcon(this.getClass().getResource("voltage_fail.gif"));
            System.out.println("Playing fail");
        }

        cutscene_Label = new JLabel(imgIcon);
        cutscene_Label.setBounds(-290, -100, 1600, 900);
    
    
        cutscene = new JPanel();
        cutscene.setBounds(-290, -100, 1600, 900); // You can use your own values

        cutscene.add(cutscene_Label);

        this.getContentPane().add(cutscene);
    } 
    
    
}    
public void sound (File name){
    try{
        Clip clierror = AudioSystem.getClip();
        clierror.open(AudioSystem.getAudioInputStream(name));
        clierror.start();
        //Thread.sleep(clierror.getMicrosecondLength()/1000);
        //System.out.println("Playing Sound");
    }
    catch(Exception e){
        //System.out.println("Can't play sound");
    }
}    
void dispose_cutscene(){
    cutscene.setVisible(false);
    cutscene_trigger = false;
    this.getContentPane().remove(cutscene);  
}

void pause(){
    timer.stop();
    pauss.setBackground(Color.pink);
    pauss.setBounds(100, 0, 1024, 576);
    this.add(pauss);
    pause=true;
    System.out.println(pause);
}
void resume(){
    timer.start();
    pause=false;
    System.out.println(pause);
    this.remove(pauss);
}

public void reeadfile() throws FileNotFoundException{
        Random randomGenerator = new Random();
        int randomIndex;
        int randomValue;
        data = new int[50][7]; 
        File f = new File("ttmp/mb.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()){
            for(int i=0;i<data.length;i++){
                String[] line = sc.nextLine().trim().split(" ");
                for(int j=0;j<line.length;j++){
                    data[i][j]=Integer.parseInt(line[j]);
                    
                }
            }
            //System.out.println(Arrays.deepToString(data));
            soom = new int [50];
            for (int i = 0; i < soom.length; ++i){
                soom[i] = i;
            }
            for(int i = 0; i < soom.length; ++i){
                randomIndex = randomGenerator.nextInt(soom.length);
                randomValue = soom[randomIndex];
                soom[randomIndex] = soom[i];
                soom[i] = randomValue;
            }
            
            //System.out.println(Arrays.toString(soom));
        }
        
    }

public Game() throws FileNotFoundException{
        super("VOLTlab");
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024,576);
        this.addKeyListener(this);
        this.setVisible(true);
        starttrigger=true;
        playvideo(strto);
        sound(soundstart);
        insidee();
        countdownTimer();
        timer.start();
        this.add(counterLabel);
    }
    public void insidee() throws FileNotFoundException{
        reeadfile();
        bak.setBounds(-290, -190,1600, 900);
        second =25;
        minute =0;
        
            goal=data[soom[run]][0];
            number_buttons = new VOLTbutton[3];
            sign_buttons = new VOLTbutton[3];

            score =new JLabel();
            score.setBounds(465, 421, 120, 120);//(479, 421, 120, 120)
            score.setText(result+"");
            score.setForeground(Color.gray);
            score.setFont(Digi);
            this.add(score);

            w =new JLabel();//Goal
            w.setBounds(465,10,120,120);//(479,10,120,120)
            w.setText(goal+"");
            w.setForeground(Color.white);
            w.setFont(Digi);
            this.add(w);

            life=new JLabel();
            life.setBounds(641,421,120,120);//(641,421,120,120)
            life.setText(HP+"");
            life.setForeground(Color.RED);
            life.setFont(Digi);
            this.add(life);
            
            for(int i=0;i<3;i++){
                number_buttons[i] = new VOLTbutton(data[soom[run]][1+i]);
                number_buttons[i].setLocation(220,95+((VOLTbutton.height * i))+(50*i));
                number_buttons[i].cc=false;
                this.add(number_buttons[i]);
            }       
            for(int i=0;i<3;i++){           
                sign_buttons[i] = new VOLTbutton((char)data[soom[run]][4+i]);
                sign_buttons[i].setLocation(738,95+((VOLTbutton.height * i))+(50*i));
                sign_buttons[i].cc=false;
                this.add(sign_buttons[i]);
            }	
        this.add(bak);
    }
    public void correct(){
        if((number_buttons[0].cc) && (number_buttons[1].cc) && (number_buttons[2].cc) && (sign_buttons[0].cc) && (sign_buttons[1].cc) && (sign_buttons[2].cc)){
                try {
                    if(data[soom[run]][0]==result){
                        //this.add(show);
                        if(run>=49){
                            over(2);
                        }
                        else{
                            cutscene_counter = 0;
                            cutscene_trigger = true;
                            wincount++;
                            show(1);
                            sound(pass);
                            nextround();
                        }
                    }
                    else{
                        //this.add(show);
                        HP--;
                        life.setText("");
                        show(0);
                        sound(pass);
                        nextround();
                    }
                } catch (FileNotFoundException ex) {
                }
                System.out.println(run);
            }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }    
    @Override
    public void keyPressed(KeyEvent e) {
        if(overcut!=true&&cutscene_trigger!=true&&pause!=true&&starttrigger!=true){
        if(!clicked){    
            if(e.getKeyCode() == KeyEvent.VK_W){
                //movew();
                if(current_number == -1){
                    current_number = 0;
                }
                else{
                    if(number_buttons[current_number].check==-1)number_buttons[current_number].unselected();
                    else number_buttons[current_number].apply();
                    if(current_number == 0){
                        current_number = 2;
                    }
                    else{
                        current_number--;
                    }
                }
                if(number_buttons[current_number].check==-1)
                    number_buttons[current_number].selected();
                else{
                    number_buttons[current_number].appl();
                    check[current_number][0]=true;
                }
                sound(Select);
            }        
            else if(e.getKeyCode() == KeyEvent.VK_S){
                //moves();
                if(current_number == -1){
                    current_number = 0;
                }
                else{
                    if(number_buttons[current_number].check==-1)number_buttons[current_number].unselected();
                    else number_buttons[current_number].apply();
                    if(current_number == 2){
                        current_number = 0;
                    }
                    else{
                        current_number++;
                    }
                }
                if(number_buttons[current_number].check==-1)
                    number_buttons[current_number].selected();
                else{
                    number_buttons[current_number].appl();
                    check[current_number][0]=true;
                }
                sound(Select);
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER||e.getKeyCode() ==KeyEvent.VK_D){
                if(number_buttons[current_number].cc==true){
                    //clierror.setFramePosition(0);
                    sound(error);
                    
                }
                else{
                     if(current_number != -1){
                        number_buttons[current_number].selected();
                        if(current_sign == -1){
                        current_sign = 0;
                    }
                        if(sign_buttons[current_sign].cc==true)sign_buttons[current_sign].appl();
                        else sign_buttons[current_sign].selected();
                    }
                    clicked=true;
                    sound(n1);
                }  
            }       
        }
        else{//After Enter 
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE||e.getKeyCode() ==KeyEvent.VK_A){
                clicked=false;
                sign_buttons[current_sign].unselected();
                sound(c);
            }
            if(e.getKeyCode() == KeyEvent.VK_W){
                //movew();
                if(current_sign == -1){
                    current_sign = 0;
                }
                else{
                    if(sign_buttons[current_sign].check==-1)sign_buttons[current_sign].unselected();
                    else sign_buttons[current_sign].apply();
                    if(current_sign == 0){
                        current_sign = 2;
                    }
                    else
                    {
                        current_sign--;
                    }
                }
                if(sign_buttons[current_sign].check==-1){
                    sign_buttons[current_sign].selected();    
                }
                else{
                    check[current_sign][1]=true;
                    sign_buttons[current_sign].appl();
                    
                }
                sound(selected);
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                   //moves();
                if(current_sign == -1){
                    current_sign = 0;
                }
                else{
                    if(sign_buttons[current_sign].check==-1)sign_buttons[current_sign].unselected();
                    else sign_buttons[current_sign].apply();
                    if(current_sign == 2){
                        current_sign = 0;
                    }
                    else{
                        current_sign++;
                    }
                }
                if(sign_buttons[current_sign].check==-1){
                    sign_buttons[current_sign].selected();
                }
                else{
                   sign_buttons[current_sign].appl(); 
                   check[current_sign][1]=true;
                   //repaint();
                }
                sound(selected);
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER||e.getKeyCode() ==KeyEvent.VK_D){
                if(sign_buttons[current_sign].cc==false){
                sign_buttons[current_sign].apply();
                number_buttons[current_number].apply();
                result+=number_buttons[current_number].button_value*sign_buttons[current_sign].button_value;
                score.setText(result+"");
                current_number = 0;
                if(number_buttons[current_number].cc==true)number_buttons[current_number].appl();
                else number_buttons[current_number].selected();
                current_sign = -1;
                clicked=false;
                correct();
                sound(n2);}
                else
                    sound(error);
            }
        }
        }
       if(e.getKeyCode() == KeyEvent.VK_SPACE&&cutscene_trigger!=true&&overcut!=true){
               if (pause!=true) pause();
               else resume();    
            }
            
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    void nextround() throws FileNotFoundException{
        run++;
        result =0;
        score.setText("");
        w.setText("");
        life.setText("");
        for(int i=0;i<3;i++){
            number_buttons[i].nextround();
            sign_buttons[i].nextround();
        }
        insidee();
    }
    
    public void countdownTimer() {
        counterLabel = new JLabel("");
    counterLabel.setBounds(300, 422, 150, 120);
    counterLabel.setHorizontalAlignment(JLabel.CENTER);
    counterLabel.setFont(didi);	
    counterLabel.setForeground(Color.green);
    counterLabel.setText("0"+minute+":0"+second);
	timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
                    if(cutscene_trigger == false){
 
                            second--;
                            ddSecond = dFormat.format(second);
                            ddMinute = dFormat.format(minute);	
                            counterLabel.setText(ddMinute + ":" + ddSecond);

                            if(second==-1) {
                                second = 59;
                                minute--;
                                ddSecond = dFormat.format(second);
                                ddMinute = dFormat.format(minute);	
                                counterLabel.setText(ddMinute + ":" +ddSecond);
                            }
                            if(overcut==true){
                                if(overc == 12){
                                    dispose_cutscene();
                                    //overcut = false;
                                    timer.stop();
                                }
                                else overc++;
                            }
                            if(win==true){
                                if(winc == 25){
                                    dispose_cutscene();
                                    System.out.println("st");
                                    timer.stop();
                                }
                                else winc++;
                            }
                            if(starttrigger==true){
                                if(startc == 9){
                                    dispose_cutscene();
                                    starttrigger = false;
                                }
                                else startc++;
                            }
                            if(minute==0 && second==0) {
                                //timer.stop();
                                if(win!=true){
                                clicked=false;
                                HP--;
                                life.setText("");
                                cutscene_counter = 0;
                                cutscene_trigger = true;
                                show(0);
                                try {
                                    nextround();
                                } catch (FileNotFoundException ex) {
                                }
                                if(cutscene_counter == 4){
                                    System.out.println("start up2");
                                    dispose_cutscene();
                                    cutscene_trigger = false;
                                    cutscene_counter = 0;
                                }
                                    else cutscene_counter++;
                            }
                         }
                    }
                    else{
                        if(cutscene_counter == 4){
                            System.out.println("start up");
                            dispose_cutscene();
                            cutscene_trigger = false;
                            cutscene_counter = 0;
                        }
                        else cutscene_counter++;
                    }
            }
		
	});		
    }
    
    void over(int a){
        if(a==1){
            overcut=true;
            playvideo(ovr);
            sound(soundend);
        }
        else{
            win=true;
            playvideo(good);
            sound(god);
        }
        over=new JPanel();
        over.setBounds(0, 0, 1024, 576);
        over.setBackground(Color.black);
        score.setVisible(false);
        w.setVisible(false);
        life.setVisible(false);
        counterLabel.setVisible(false);
        for(int j=1;j<=run;j++){
            for(int i=0;i<3;i++){
                number_buttons[i].nextround();
                sign_buttons[i].nextround();
            }
        }
        overed = new JLabel("You just pass "+wincount+" Stage");
        overed.setForeground(Color.WHITE);
        overed.setFont(new Font("Verdana",0,50));
        overed.setVisible(true);
        overed.setBounds(230,200,700,120);
        reset.setBounds(100,400,170,70); 
        reset.setBackground(Color.BLACK);
        reset.addActionListener(this);
        reset.setForeground(Color.WHITE);
        reset.setFont(new Font("Verdana",0,30));
        this.add(overed);
        this.add(over); 
        this.add(reset);
            
    }
    public void playvideo(String name){
        Icon den;JLabel cutscene_Label;
        den = new ImageIcon(this.getClass().getResource(name));
        cutscene_Label = new JLabel(den);
        //cutscene_Label.setBounds(0, 0, 1600, 900);    
        cutscene = new JPanel();
        cutscene.setBounds(0, -20, 1024, 576);
        cutscene.add(cutscene_Label);
        this.getContentPane().add(cutscene);
        //if(starttrigger==false&&overcut==false){starttrigger=true;System.out.println("here");}
        this.remove(bak);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.dispose();
        try {
            new Game();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
