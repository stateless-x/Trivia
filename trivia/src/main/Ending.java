package main;
import sun.audio.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

class Ending extends MyFrame {

    private JLabel draw;
    private MyImageIcon background;
    private Sound jojo;
    public Ending(boolean endT) {

        if (endT == true) {
            
            MyFrame endingFrame = new MyFrame();
            addMusic();
            addBackground(endingFrame);
            
            //setBounds(0, 0, 1920, 1080);
            setResizable(true);
            setVisible(true);
            //Create Characters object.
            Characters ch = new Characters();
            draw.add(ch);
            ch.setFocusable(true);
            ch.requestFocus();
            repaint();

        }

    }

    public void addBackground(MyFrame f) {
        this.setContentPane(draw = new JLabel());
        background = new MyImageIcon(("pink.jpg"));
        //window-setbackground
        //draw.setIcon(background.resize(f.getFrameWidth(), f.getFrameWidth()));
        //mac-setbackground
        draw.setIcon(background.resize(f.getFrameWidth()*3/2, f.getFrameWidth()*3/2));
    }

    public void addMusic() {
        jojo = new Sound("jojo.wav");
        jojo.play();
        System.out.println("JOJO PLAYED");

    }
}

// auxiliary class to resize image
class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}

class Sound {

    private java.applet.AudioClip audio;

    public Sound(String f) {
        try {
            java.io.File file = new java.io.File(f);;
            audio = java.applet.Applet.newAudioClip(file.toURL());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        audio.play();
        System.out.println("JOJO(audio) PLAYED");
    }

    public void playLoop() {
        audio.loop();
        System.out.println("JOJO(audio) LOOPED");
    }

public void stop() {
        audio.stop();
}
}
