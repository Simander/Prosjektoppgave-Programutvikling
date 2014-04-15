
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author anders
 */
public class Kulturskole {
    public static void main(String[] args)
    {
       
        PersonRegister personar = new PersonRegister();
        RomListe rom = new RomListe();
        BookingList booka = new BookingList();
        InstrumentRegister iReg = new InstrumentRegister();
        List<Rom> romliste = new ArrayList<>();
       // Oppretter ett nytt GUI av type HovedVindu
      HovedVindu vindu = new HovedVindu(personar, rom, booka, iReg);
       // Booking vindu = new Booking();
        //MasterWindow vindu = new MasterWindow();
        vindu.setVisible(true);
        vindu.setSize(670, 660);
        vindu.setLocation(0, 0);
        vindu.setLocationRelativeTo(null);
        vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}
