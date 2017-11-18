package utils;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Felipe
 */
public class Som {
    AudioClip somPacMan;
    
    public Som() {
        somPacMan = Applet.newAudioClip(getClass().getResource("pacman_andando.wav"));
    }
    
    public void tocar()
    {
        somPacMan.loop();
    }
    
    public void parar()
    {
        somPacMan.stop();
    }
    
}
