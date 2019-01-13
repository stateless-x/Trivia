package main;
//Handler 3 mouse and key event handler
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

class Characters extends JLabel implements KeyListener, MouseListener{

    private MyImageIcon[] characters;
    private int mode;
    private int cWidth = 600, cLength = 800; //character's width x length

    public MyImageIcon[] getMyImageIcon() {
        return characters;
    }

    public Characters() {
        System.out.println("Characters called");
        characters = new MyImageIcon[4];
        characters[0] = new MyImageIcon("pooh.png").resize(cWidth, cLength);
        characters[1] = new MyImageIcon("krit.png").resize(cWidth, cLength);
        characters[2] = new MyImageIcon("man.png").resize(cWidth, cLength);
        characters[3] = new MyImageIcon("keen.png").resize(cWidth, cLength);
        mode = 0;
        setIcon(characters[mode]);
        
        //mac's initial loc for image
        setBounds(350, -50, 1000, 1000);
        
        //window's initial loc for image
        //setBounds(600, -50, 1000, 1000);
        
        addMouseListener(this);
        addKeyListener(this);
    }
    
//===============================================================KEY EVENT ======================================================================
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
             System.out.println("D-pressed");
            //to the right
            if (mode >= 3) {
                mode = 0;
                setIcon(characters[mode]);

            } else {

                setIcon(characters[mode + 1]);
                //mac's initial loc for image
                setBounds(350, -50, 1000, 1000);
                //window's initial loc for image
                //setBounds(600, -50, 1000, 1000);
                mode += 1;
           
            }
        }
        //to the left
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A-pressed");
            if (mode <= 0) {
                mode = 3;
                setIcon(characters[mode]);
            } else {

                setIcon(characters[mode - 1]);
                //mac's initial loc for image
                setBounds(350, -50, 1000, 1000);
                //window's initial loc for image
                //setBounds(600, -50, 1000, 1000);
                mode -= 1;
               

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
//=============================================================== MOUSE EVENT ======================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
        if (e.getButton() == MouseEvent.BUTTON1) {

            //to the right
            if (mode >= 3) {
                mode = 0;
                setIcon(characters[mode]);
            } else {
                setIcon(characters[mode + 1]);
                //mac's initial loc for image
                setBounds(350, -50, 1000, 1000);
                //window's initial loc for image
                //setBounds(600, -50, 1000, 1000);
                mode += 1;

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
  
    }

    @Override
    public void mouseEntered(MouseEvent e) {
 
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
