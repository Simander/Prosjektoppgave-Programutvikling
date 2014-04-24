import javax.swing.JFrame;

/**
 *
 * @author anders
 */
//Main metode klasse
public class Kulturskole {
    public static void main(String[] args)
    {
        PersonListe personar = new PersonListe();
        RomListe rom = new RomListe();
        KursListe kurs = new KursListe();
        BookingListe booka = new BookingListe();
        InstrumentRegister iReg = new InstrumentRegister();
        HovedVindu vindu = new HovedVindu(personar, rom, booka, kurs, iReg);
        vindu.setVisible(true);
        vindu.setSize(800, 600);
        vindu.setLocation(0, 0);
        vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}
