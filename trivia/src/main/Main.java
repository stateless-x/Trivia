/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
// Handler 1 here mouse press handler
/**
 *
 * @author MSI GT62VR
 */
import java.lang.String;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main extends JFrame {

    JPanel p = new JPanel(), opanel = new JPanel();
    CardLayout cards = new CardLayout();
    private int TrvNums = 0; // numbers of questions
     int incorrects = 0;
     int corrects = 0;
    private int subjnext = 0; // subjnextect choice passed from main
    private int lvlnext = 0;
    private String playerName;
    private boolean trvEnd;

    public int getTrvNums() {
        return TrvNums;
    }

    public int getSubjnext() {
        return subjnext;
    }

    public int getLvLnext() {
        return lvlnext;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getTrvEnd() {
        return trvEnd;
    }

// The questions and answers are not finished yet, sorry for that.        
    String[][] DCDansEZ = {
        {"101100", "110010", "011000"},
        {"100101", "010011", "011001"},
        {"Yes", "No"},
        {"001010", "110010", "011000"},
        {"NAND GATE", "XOR GATE", "OR GATE"}
    };
    String[][] DCDansH = {
        {"D FLIP-FLOP", "J-K FLIP-FLOP", "16 Bits Counter"},
        {"-407,868", "-407,886", "-407,688"},
        {"Binary Coded Decimal", "Binary Coded Digit", "Bidecimal Character Divisor"},
        {"Cyclic Redundancy Coder", "Cylic Redundancy Check", "Code Redundancy Checker"},
        {"4", "8", "12"}
    };
    String[][] ECAansEZ = {
        {"10", "25", "20"},
        {"When the dependent source are in series.", "When the resistors are in parallel.", "When we short the circuit at the resistors."},
        {"1.2 kV", "12000 V", "0.12 MV"},
        {"Pressure", "Polarity", "Length"},
        {"Ampere", "Candela", "Coulomb"}
    };
    String[][] ECAansH = {
        {"Q/V", "QV", "Q-V"},
        {"IR1", "IR2", "IR2/(R1+R2)"},
        {"Mesh-voltage analysis", "Node-current analysis", "Supernode"},
        {"Voltage", "Current", "Resistance"},
        {"5062.5 W", "506.25 W", "50.625 W"}
    };
    String[][] ProParaansEZ = {
        {"malloc", "alloc", "new"},
        {"Row", "Column", "Both Row and Column"},
        {"Integer", "Boolean", "Integer or Boolean"},
        {"ASCII", "ISO-LATIN-1", "UNICODE"},
        {"True & False", "0 & 1", "Any integer value"}
    };
    String[][] ProParaansH = {
        {"yield()", "notify()", "wait()"},
        {"Process", "Process scheduler", "Thread Scheduler"},
        {"0 & 256", "0 & 1", "1 & 10"},
        {"Process", "Daemon Thread", "User Thread"},
        {"-1.0 to 1.0", "-1 to 1", "0.0 to 1.0"}
    };

    main.QuestionMethods Trvqs[][][] = { // We use 3D array to defy subjnextects(1st D) ,Modes(2nd D), and Questions(3rd D)
        { //DCD
            { // DCD Easy
                new main.QuestionMethods("What is the base-2 of 50?",
                DCDansEZ[0], 1, this),
                new main.QuestionMethods("What is the base-2 of 25?",
                DCDansEZ[1], 2, this),
                new main.QuestionMethods("Can we implement Full-Adder using Half-Adder?",
                DCDansEZ[2], 0, this),
                new main.QuestionMethods("What is the base-2 of 10?",
                DCDansEZ[3], 0, this),
                new main.QuestionMethods("What is the name of IC 7432?",
                DCDansEZ[4], 2, this),},
            { // DCD Hard
                new main.QuestionMethods("What is the name of IC 7476?",
                DCDansH[0], 1, this),
                new main.QuestionMethods("Determine the decimal value of this floating-point number '1 10010001 100011100010000000'",
                DCDansH[1], 2, this),
                new main.QuestionMethods("What does BCD stand for?",
                DCDansH[2], 0, this),
                new main.QuestionMethods("What is CRC?",
                DCDansH[3], 1, this),
                new main.QuestionMethods("How many rules are there in the rule of Boolean Algebra?",
                DCDansH[4], 2, this),}
        },
        //---------------------- subject sepa(corrects*100/(corrects+incorrects))n ---------------------------
        { // ECA
            {
                new main.QuestionMethods("What is the voltage at 2 Ohm resistor with 10 A current passing through?",
                ECAansEZ[0], 2, this),
                new main.QuestionMethods("When do we use voltage divider?",
                ECAansEZ[1], 1, this),
                new main.QuestionMethods("Determine the value of current B passing through 10 kOhm resistor with the voltage difference of 12 MV",
                ECAansEZ[2], 0, this),
                new main.QuestionMethods("Which of the following is a defined quantity?",
                ECAansEZ[3], 2, this),
                new main.QuestionMethods("The basic unit for luminous intensity is b",
                ECAansEZ[4], 1, this),},
            {
                new main.QuestionMethods("The formula used to find the capacitance C is __________",
                ECAansH[0], 0, this),
                new main.QuestionMethods("What is the voltage at 2 Ohm resistor with 10 A current passing through?",
                ECAansH[1], 2, this),
                new main.QuestionMethods("When there is a current source between two loops which method is preferred?",
                ECAansH[2], 2, this),
                new main.QuestionMethods("The Inductor doesn’t allow sudden changes in ___________ ",
                ECAansH[3], 1, this),
                new main.QuestionMethods("If a capacitor of capacitance 9.2F has a voltage of 22.5V across it. Calculate the energy of the capacitor.",
                ECAansH[4], 1, this),}
        },
        //---------------------- subject sepa(corrects*100/(corrects+incorrects))n ---------------------------  
        { // ProPara
            {
                new main.QuestionMethods("Which of these operators is used to allocate memory to array variable in Java?",
                ProParaansEZ[0], 2, this),
                new main.QuestionMethods("Which of these is necessary to specify at time of array initialization?",
                ProParaansEZ[1], 0, this),
                new main.QuestionMethods("Which of these can be returned by the operator &?",
                ProParaansEZ[2], 2, this),
                new main.QuestionMethods("Which of these coding types is used for data type characters in Java?",
                ProParaansEZ[3], 2, this),
                new main.QuestionMethods("Which of these values can a boolean variable contain?",
                ProParaansEZ[4], 0, this)
            },
            {
                new main.QuestionMethods("Which of the following will ensure the thread will be in running state?",
                ProParaansH[0], 2, this),
                new main.QuestionMethods("What decides thread priority?",
                ProParaansH[1], 2, this),
                new main.QuestionMethods("What is the default value of priority variable MIN_PRIORITY AND MAX_PRIORITY?",
                ProParaansH[2], 2, this),
                new main.QuestionMethods("What does not prevent JVM from terminating?",
                ProParaansH[3], 1, this),
                new main.QuestionMethods("What is the range of numbers returned by Math.random() method?",
                ProParaansH[4], 2, this)
            }
        }
    };

    public static void main(String args[]) {

        Main t = new Main();
        
    }

    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 600); // setbound of combobox
        setTitle("Course and Difficulty Category");// title
        getContentPane().setLayout(null);

        // This part is to create a combobox
        String[] courseStrings = {"", "DCD", "ECA", "ProPara"}; // list of course
        String[] DifficultyStrings = {"", "Easy", "Hard"}; // list of course

        final JComboBox comboBox = new JComboBox(courseStrings); // create new combobox
        final JComboBox comboBox2 = new JComboBox(DifficultyStrings); // create new combobox
        final JTextField plyrnm = new JTextField();

       
        comboBox.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        
        plyrnm.setBounds(50, 110, 140, 30);
        comboBox.setBounds(200, 110, 90, 30); // setbound of combobox
        comboBox2.setBounds(300, 110, 90, 30); // setbound of combobox
       
        
        getContentPane().add(plyrnm);
        getContentPane().add(comboBox); // create panel
        getContentPane().add(comboBox2); // create panel
        
        labelBundle(); // method for adding every labels
        
// This par is to create a button
        JButton btn = new JButton("Click"); // create new click button
        btn.setBounds(400, 110, 90, 30); // button bound
        getContentPane().add(btn); // display the button 
        setVisible(true);

        btn.addMouseListener(new MouseAdapter() { //Handler 1
            @Override
            public void mousePressed(MouseEvent e) {
                trvEnd = false;
                playerName = plyrnm.getText();
                subjnext = comboBox.getSelectedIndex() - 1;
                lvlnext = comboBox2.getSelectedIndex() - 1;
                if (playerName.trim().isEmpty()) {
                    playerName = null;
                    JOptionPane.showMessageDialog(null, "Please enter your name.");
                }
                if (subjnext == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a subject.");
                }
                if (lvlnext == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a mode.");
                }
                if (playerName != null && subjnext > -1 && lvlnext > -1) {
                    JOptionPane.showMessageDialog(null, "Let's play, " + playerName + " !");
                    setVisible(false);

                    class questions extends JFrame {

                        questions() {
                            setTitle("Trivias");
                            setBounds(0, 0, 600, 600);
                            setResizable(true);
                            setSize(650, 300);
                            setDefaultCloseOperation(EXIT_ON_CLOSE);
                            p.setLayout(cards);
                            TrvNums = Trvqs[subjnext][lvlnext].length; // length within string array Trvqs[1D][2D]{1,2,3,4,5 << lenght}
                            for (int i = 0; i < TrvNums; i++) {
                                p.add(Trvqs[subjnext][lvlnext][i], "q" + i);
                            }
                            Random r = new Random();
                            int i = r.nextInt(TrvNums);
                            cards.show(p, "q" + i);
                            add(p);
                            setVisible(true);
                        }
                    }
                    new questions();
                } //end if 

            }

        });

    }

    public void next() {

        if ((corrects + incorrects) == TrvNums) {
            JOptionPane.showMessageDialog(null, "finished");
        } 
        else 
        {
            Random r = new Random();
            boolean found = false;
            int i = 0;
            while (!found) {
                i = r.nextInt(TrvNums);
                if (!Trvqs[subjnext][lvlnext][i].answered) {
                    found = true;
                }
            }
            cards.show(p, "q" + i);
        }
    }

    public void showSummary() {

        if (corrects == 0 && incorrects == 0) {
            JOptionPane.showMessageDialog(null,
                    "You didn't answer any questions " + playerName + " U_U.");
        } else if ((corrects * 100 / (corrects + incorrects)) > 80) {
            JOptionPane.showMessageDialog(null,
                    "Finished! Here is your summary, " + playerName
                    + " :\nYou answered " + incorrects + " questions wrong"
                    + "\nYou answered " + (corrects) + " right"
                    + "\nIn total you got : \t\t" + (corrects * 100 / (corrects + incorrects))
                    + "%\nWow! You are the best, " + playerName + " .");
        } else if ((corrects * 100 / (corrects + incorrects)) < 50) {
            JOptionPane.showMessageDialog(null,
                    "Finished! Here is your summary, " + playerName
                    + " :\nYou answered " + incorrects + " questions wrong"
                    + "\nYou answered " + (corrects) + " right"
                    + "\nIn total you got : \t\t" + (corrects * 100 / (corrects + incorrects))
                    + "%\nCheer up! Atleast you tried, " + playerName + " .");
        } else {
            JOptionPane.showMessageDialog(null, "Finished! Here is your summary, " + playerName
                    + " :\nYou answered " + incorrects + " questions wrong"
                    + "\nYou answered " + (corrects) + " right"
                    + "\nIn total you got : \t\t" + (corrects * 100 / (corrects + incorrects))
                    + "%\nYou did good, " + playerName + " .");
        }
        if (corrects != 0 || incorrects != 0){
        Record();}
        trvEnd = true;
        Ending ed = new Ending(trvEnd);
    }

    public void Record() {
        try {
            PrintStream rec1 = new PrintStream(new FileOutputStream("record1.txt", true));
            rec1.println(" ");
            rec1.println("Player : " + playerName);
            rec1.println("Score : " + corrects + " out of " + (corrects + incorrects));
            rec1.println("Percentage : " + String.valueOf(corrects * 100 / (corrects + incorrects)) + "%");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void labelBundle()
   {
        final JLabel lbl = new JLabel("Subject");
        final JLabel lbl2 = new JLabel("Mode");
        final JLabel lbl3 = new JLabel("Name");
        lbl3.setBounds(50, 60, 100, 50);
        lbl.setBounds(200, 60, 100, 50);
        lbl2.setBounds(300, 60, 100, 50);
        getContentPane().add(lbl3);
        getContentPane().add(lbl);
        getContentPane().add(lbl2);
        final JLabel name1 = new JLabel("Wanatbodee");
        final JLabel name2 = new JLabel("Thanakrit");
        final JLabel name3 = new JLabel("Natharaj");
        final JLabel name4 = new JLabel("Vittaya");
        final JLabel id1 = new JLabel("5980026");
        final JLabel id2 = new JLabel("5981016");
        final JLabel id3 = new JLabel("5980450");
        final JLabel id4 = new JLabel("5980462");
        name1.setBounds(100, 180, 100, 30);
        name2.setBounds(100, 220, 100, 30);
        name3.setBounds(100, 260, 100, 30);
        name4.setBounds(100, 300, 100, 30);
        id1.setBounds(200, 180, 80, 30);
        id2.setBounds(200, 220, 80, 30);
        id3.setBounds(200, 260, 80, 30);
        id4.setBounds(200, 300, 80, 30);
        getContentPane().add(name1);
        getContentPane().add(id1);
        getContentPane().add(name2);
        getContentPane().add(id2);
        getContentPane().add(name3);
        getContentPane().add(id3);
        getContentPane().add(name4);
        getContentPane().add(id4);
   }
}
