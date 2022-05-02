/*
 * SIMPLE CALCULATOR FOR INTEGER CALCULATONS
 * Output for Prelim Practical Exam
 * By ADRIANE TROY V. ROA
 * CC 12 Section C
 *
 * =================================================
 *
 * DESCRIPTION:
 * This calculator features basic calculations of integers.
 * It is capable of calculating numbers with givens and/or answers
 * that limit to 12 DIGITS. Java Awt and Java Swing are the two 
 * components of Java Foundational Classes (JFC) that are featured
 * in this project. Both of them influence the Graphical User Interface (GUI)
 * of the application with respect to the GridLayout Type --- the layout manager
 * used by the interface. An ArrayList object is also used for clearing
 * displays.
 *
 * This project, however, does not support any form of answers or givens
 * that involve decimal values: an instance that was clarified to the 
 * course instructor Mr. Patrick Mack.
 *
 * FEATURES:
 *  - Clickable buttons
 *      * For inputting numbers
 *      * For arithmetic operations
 *      * For equal operation
 *      * For clearing and all-clearing of display
 *  - Keys in keyboard limited to
 *      * numbers including those in number pad
 *      * symbols for arithmetic operations
 *      * ENTER key and EQUAL key for equal operation
 * */


//importing needed packages and classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//declaring class as implementation of ActionListener and KeyListener
//such implementation is the key for user actions
public class SimpleCalculator implements ActionListener, KeyListener{
    
    //declaring objects 
    JButton[] numb;
    JButton[] oper;
    JButton[] clear;
    JTextField disp;
    JTextField sign;
    JButton plus;
    JButton minus;
    JButton times;
    JButton divide;
    JButton equals;
    JButton clr;
    JButton ac;
    JLabel operLabel;

    //declaring an ArrayList object and various variables
    ArrayList<Character> spGiven;
    boolean history = true;
    String operator = "";
    long answer = 0;
    boolean command = false;
    boolean shiftPress = false;

    //constructor method declaring and initializing a JFrame object,
    //and initializing declared objects above. this part defines the
    //overall form of the application as well as the initial behavior
    //of Swing and AWT components.
    SimpleCalculator(){
        JFrame frame = new JFrame("Roa Simple Calc");

        Font aFont = new Font("defaultFont", Font.BOLD, 36);
        Font bFont = new Font("defaultFont", Font.PLAIN, 20);
        Font cFont = new Font("defaultFont", Font.PLAIN, 70);

        disp = new JTextField();
        disp.setFont(aFont);
        disp.setEditable(false);
        disp.setBorder(null);
        disp.setForeground(new Color(0,0,0));
        disp.setBackground(new Color(250,250,250));
        disp.setText("0");
        disp.setHorizontalAlignment(JTextField.RIGHT);
        disp.addActionListener(this);
        disp.addKeyListener(this);

        sign = new JTextField();
        sign.setFont(cFont);
        sign.setEditable(false);
        sign.setBorder(null);
        sign.setForeground(new Color(0,0,0));
        sign.setBackground(new Color(250,250,250));
        sign.addActionListener(this);
        sign.addKeyListener(this);

    	JPanel line = new JPanel();
	    line.setLayout(new GridLayout(3,1));
        line.setBackground(new Color(250,250,250));

        JPanel forAc = new JPanel();
        forAc.setLayout(new GridLayout(1,4));
        forAc.setBackground(new Color(250,250,250));

        JPanel pad = new JPanel();
        pad.setLayout(new GridLayout(4,4));
        pad.setBackground(new Color(250,250,250));

        numb = new JButton[10];

        for(short i=0; i<numb.length; i++){
            numb[i] = new JButton(String.valueOf(i));
            numb[i].setFocusable(false);
            numb[i].setFont(bFont);
            numb[i].addActionListener(this);
            numb[i].setForeground(new Color(0,0,0));
            numb[i].setBackground(new Color(250,250,250));
            numb[i].setBorder(null);
        }

        oper = new JButton[5];
        
        plus = new JButton("+");
        minus = new JButton("-");
        times = new JButton("*");
        divide = new JButton("/");
        equals = new JButton("=");
        clr = new JButton("C");
        ac = new JButton("AC");
        
        oper[0] = plus;
        oper[1] = minus;
        oper[2] = times;
        oper[3] = divide;
        oper[4] = equals;
        for(short i=0; i<oper.length; i++){ 
            oper[i].setFocusable(false);
            oper[i].setFont(bFont);
            oper[i].addActionListener(this);
            oper[i].setForeground(new Color(0,0,0));
            oper[i].setBackground(new Color(250,250,250));
            oper[i].setBorder(null);
        }

        clear = new JButton[2];

        clear[0] = clr;
        clear[1] = ac;

        for(short i=0; i<clear.length; i++){
            clear[i].setFocusable(false);
            clear[i].setFont(bFont);
            clear[i].addActionListener(this);
            clear[i].setForeground(new Color(0,0,0));
            clear[i].setBackground(new Color(250,250,250));
            clear[i].setBorder(null);
        }

    	line.add(disp);
        line.add(new JLabel());
        line.add(forAc);

        forAc.add(sign);
        forAc.add(new JLabel());
        forAc.add(new JLabel());
        forAc.add(ac);

        pad.add(numb[7]);
        pad.add(numb[8]);
        pad.add(numb[9]);
        pad.add(clr);
        pad.add(numb[4]);
        pad.add(numb[5]);
        pad.add(numb[6]);
        pad.add(times);
        pad.add(numb[1]);
        pad.add(numb[2]);
        pad.add(numb[3]);
        pad.add(minus);
        pad.add(numb[0]);
        pad.add(equals);
        pad.add(divide);
        pad.add(plus);

        frame.setLayout(new GridLayout(2,1));
        frame.add(line);
        frame.add(pad);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(300, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //main method
    public static void main(String[] args){
        new SimpleCalculator();
    }

    //operation method for use of arithmetic operations
    void operation(String a){
        if(history){
            switch(operator){
                case "+":
                    answer += Long.parseLong(disp.getText());
                    break;
                case "-":
                    answer -= Long.parseLong(disp.getText());
                    break;
                case "*":
                    answer *= Long.parseLong(disp.getText());
                    break;
                case "/":
                    try{
                        answer /= Long.parseLong(disp.getText());
                    }
                    catch(Exception r){
                        disp.setText("Infinity");
                        answer = 0;
                    }
                    break;
                case "":
                    answer = Long.parseLong(disp.getText());
                    disp.setText(String.valueOf(answer));
                    break;
            }

            if(!(disp.getText().equals("Infinity")))
                disp.setText(String.valueOf(answer));
            if(String.valueOf(answer).toCharArray().length>12)
                disp.setText("Limit Reached");
            operator = a;
            sign.setText(operator);
            command = true;
            history = false;
        }
        else{
            switch(operator){
                case "+":
                    operator = a;
                    sign.setText(operator);
                    break;
                case "-":
                    operator = a;
                    sign.setText(operator);
                    break;
                case "*":
                    operator = a;
                    sign.setText(operator);
                    break;
                case "/":
                    operator = a;
                    sign.setText(operator);
                    break;
                case "":
                    operator = a;
                    sign.setText(operator);
                    break;
            }
        }
    }

    //this method defines the application's feedback when a user
    //clicks or manipulates a button
    @Override
    public void actionPerformed(ActionEvent e){

        for(short i=0; i<numb.length; i++){
            if(e.getSource() == numb[i]){
                if(disp.getText().equals("0") || command || disp.getText().equals("Infinity")){
                    disp.setText(String.valueOf(i));
                    command = false;
                    history = true;
                    break;
                }
                if(disp.getText().toCharArray().length < 12){
                    disp.setText(disp.getText().concat(String.valueOf(i)));
                    break;
                }
            }
        }
        if (e.getSource() == clr){
            spGiven = new ArrayList<Character>();
            for(char i: disp.getText().toCharArray())
                spGiven.add(i);
            spGiven.remove(spGiven.size()-1);
            disp.setText("");
            for(char i: spGiven)
                disp.setText(disp.getText().concat(String.valueOf(i)));
            if(spGiven.size() == 0)
                disp.setText("0");
        }
        else if(e.getSource() == plus){
            operation("+");
        }
        else if(e.getSource() == minus){
            operation("-");
        }
        else if(e.getSource() == times){
            operation("*");
        }
        else if(e.getSource() == divide){
            operation("/");
        }
        else if(e.getSource() == equals){
            if(history){
                switch(operator){
                    case "+":
                        answer += Long.parseLong(disp.getText());
                        break;
                    case "-":
                        answer -= Long.parseLong(disp.getText());
                        break;
                    case "*":
                        answer *= Long.parseLong(disp.getText());
                        break;
                    case "/":
                        try{
                            answer /= Long.parseLong(disp.getText());
                        }
                        catch(Exception r){
                            disp.setText("Infinity");
                            answer = 0;
                        }
                        break;
                    case "":
                        answer = Long.parseLong(disp.getText());
                }
                if(!(disp.getText().equals("Infinity")))
                    disp.setText(String.valueOf(answer));
                if(String.valueOf(answer).toCharArray().length>12)
                    disp.setText("Limit Reached");
                answer = 0;
                operator = "";
                command = true;
            }

            sign.setText("");
            history = false;
        }
        else if(e.getSource() == ac){
            disp.setText("0");
            sign.setText("");
            answer = 0;
            operator = "";
            history = true;
        }
    }
    @Override
    public void keyTyped(KeyEvent arg0){
        //unused
    }

    //this method defines the application's feedback when the user presses or
    //manipulates certain keys in the keyboard.
    @Override
    public void keyPressed(KeyEvent e) {
        long[][] numbKey = {{KeyEvent.VK_0, KeyEvent.VK_NUMPAD0},{KeyEvent.VK_1, KeyEvent.VK_NUMPAD1},{KeyEvent.VK_2, KeyEvent.VK_NUMPAD2},
        {KeyEvent.VK_3, KeyEvent.VK_NUMPAD3},{KeyEvent.VK_4, KeyEvent.VK_NUMPAD4},{KeyEvent.VK_5, KeyEvent.VK_NUMPAD5},
        {KeyEvent.VK_6, KeyEvent.VK_NUMPAD6},{KeyEvent.VK_7, KeyEvent.VK_NUMPAD7},{KeyEvent.VK_8, KeyEvent.VK_NUMPAD8},
        {KeyEvent.VK_9, KeyEvent.VK_NUMPAD9}};

        for(short i=0; i<numbKey.length; i++){
            if(e.getKeyCode() == numbKey[i][0] && !shiftPress|| e.getKeyCode() == numbKey[i][1]){
                if(disp.getText().equals("0") || command || disp.getText().equals("Infinity")){
                    disp.setText(String.valueOf(i));
                    command = false;
                    history = true;
                    break;
                }
                if(disp.getText().toCharArray().length < 12){
                    disp.setText(disp.getText().concat(String.valueOf(i)));
                    break;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            shiftPress = true;

        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            spGiven = new ArrayList<Character>();
            for(char i: disp.getText().toCharArray())
                spGiven.add(i);
            spGiven.remove(spGiven.size()-1);
            disp.setText("");
            for(char i: spGiven)
                disp.setText(disp.getText().concat(String.valueOf(i)));
            if(spGiven.size() == 0)
                disp.setText("0");
        }
        else if(e.getKeyCode() == KeyEvent.VK_ADD || shiftPress && e.getKeyCode() == KeyEvent.VK_EQUALS){
            operation("+");
            shiftPress = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SUBTRACT || !(shiftPress) && e.getKeyCode() == KeyEvent.VK_MINUS){
            operation("-");
        }
        else if(e.getKeyCode() == KeyEvent.VK_MULTIPLY || shiftPress && e.getKeyCode() == KeyEvent.VK_8){
            operation("*");
            shiftPress = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DIVIDE || !(shiftPress) && e.getKeyCode() == KeyEvent.VK_SLASH){
            operation("/");
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER || !(shiftPress) && e.getKeyCode() == KeyEvent.VK_EQUALS){
            if(history){
                switch(operator){
                    case "+":
                        answer += Long.parseLong(disp.getText());
                        break;
                    case "-":
                        answer -= Long.parseLong(disp.getText());
                        break;
                    case "*":
                        answer *= Long.parseLong(disp.getText());
                        break;
                    case "/":
                        try{
                            answer /= Long.parseLong(disp.getText());
                        }
                        catch(Exception r){
                            disp.setText("Infinity");
                            answer = 0;
                        }
                        break;
                    case "":
                        answer = Long.parseLong(disp.getText());
                }
                if(!(disp.getText().equals("Infinity")))
                    disp.setText(String.valueOf(answer));
                if(String.valueOf(answer).toCharArray().length>12)
                    disp.setText("Limit Reached");
                answer = 0;
                operator = "";
                command = true;
            }

            sign.setText("");
            history = false;
        }
    }

    //this method defines the application's feedback when the user releases
    //a key (particularly the SHIFT key) in the keyboard
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            shiftPress = false;
    }
}
