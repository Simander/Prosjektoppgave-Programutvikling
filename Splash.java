import java.awt.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//modifisert versjon av: http://www.iu.hio.no/~evav/uvstoff/intro/gui2eks/SplashScreen/SplashScreenTest.java
public class Splash
{
  private static SplashScreen splash;
  private static final int DEFAULT_WIDTH = 300;
  private static final int DEFAULT_HEIGHT = 300;
  
  
  private static void drawOnSplash(int percent)
  {
    Rectangle bounds = splash.getBounds();
    Graphics2D g = splash.createGraphics();
    int height = 20;
    int x = 2;
    int y = bounds.height - height - 2;
    int width = bounds.width - 4;
    g.fillRect(x, y, width * percent / 100, height);
    splash.update();
  }

  /**
   * This method draws on the splash screen.
   */
  private static void init()
  {

    splash = SplashScreen.getSplashScreen();
    try
    {
      //  AudioPlayer ap = new AudioPlayer();
        //ap.introPlayback();
      for (int i = 0; i <= 100; i++)
      {
        drawOnSplash(i);
        Thread.sleep(1000000); // simulate startup work
      }
    }
    catch (InterruptedException e)
    {
    }
  }
  public static void main(String args[]) throws UnsupportedAudioFileException, LineUnavailableException
  {
    
    init();
  }
}
