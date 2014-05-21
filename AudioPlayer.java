*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen laster inn en .wav fil og inneholder metode for avspilling.
 */

//import-setninger
import java.applet.AudioClip;
import java.net.URL;
import javax.sound.sampled.*;
public class AudioPlayer
{
    //tom konstruktør
    private AudioClip sound1;
    AudioPlayer(){}   
    //metode som laster inn og spiller av filen intro.wav
    public void introPlayback()
    {
        // from a wave File
        try
        {
            URL url = this.getClass().getClassLoader().getResource("intro.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        
        }
        catch(Exception e){}
    }
    
}
