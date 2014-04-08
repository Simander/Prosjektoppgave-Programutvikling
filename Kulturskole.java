
import javax.swing.JFrame;

/**
 *
 * @author anders
 */
public class Kulturskole {
    public static void main(String[] args)
    {
       
        PersonRegister personar = new PersonRegister();
       // Oppretter ett nytt GUI av type HovedVindu
        HovedVindu vindu = new HovedVindu(personar);
        //MasterWindow vindu = new MasterWindow();
        vindu.setVisible(true);
        vindu.setSize(670, 660);
        vindu.setLocation(0, 0);
        vindu.setLocationRelativeTo(null);
        vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}
