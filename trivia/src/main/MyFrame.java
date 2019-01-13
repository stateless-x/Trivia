package main;
//handler 2 here
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MyFrame extends JFrame {

    //private MyImageIcon background;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //screenSize for mac
    // private int width = screenSize.width * 2 / 3; 
    // private int height = screenSize.height * 2 / 3;
    //screenSize  for window
    private int width = screenSize.width;
    private int height = screenSize.height;

    public int getFrameWidth() {
        return width;
    }

    public int getFrameHeight() {
        return height;
    }

    //frame's constructor
    public MyFrame() {
        setTitle("Welcome to the Trivia!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setBounds(0, 0, width, height);
        
        addWindowListener(new WindowAdapter() { // Handler 2 WindowEvent handler
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        //setResizable(true);
        //setVisible(true);      
    }

}
