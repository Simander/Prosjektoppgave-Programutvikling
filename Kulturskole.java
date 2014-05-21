/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er inneholder main metoden som starter programmet
 */
//import-setninger
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

//Main metode klasse
public class Kulturskole {
    public static void main(String[] args) 
    {
        //oppretter lister
        PersonListe personar = new PersonListe();
        RomListe rom = new RomListe();
        KursListe kurs = new KursListe();
        BookingListe booka = new BookingListe();
        InstrumentRegister iReg = new InstrumentRegister();
        UtleieListe ut = new UtleieListe();
        Historikk h = new Historikk();
        //oppretter hovedvindu, og sender med lister
        final HovedVindu vindu = new HovedVindu(personar, rom, booka, kurs, iReg, ut,h);
        //kofigurerer vinduet
        vindu.setVisible(true);
        vindu.setSize(1000, 600);
        vindu.setResizable(false);
        //Gir vinduet en lytter som redefinerer windowClosing
	vindu.addWindowListener( new WindowAdapter()
        {
            public void windowClosing ( WindowEvent e)
            { 
                //Vinduet skriver til fil
                Object[] valg = {"Ja", "Nei"};
                int check = JOptionPane.showOptionDialog(null, "Du er i ferd med å avslutte programmet, vil du lagre?", "Avslutning!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, valg, valg[1]);
               
                if(check == 0)
                {
                    vindu.writeToFile();
                    System.exit(0);
                }
                else if(check == 1)
                {
                    System.exit(0);
                }
            }
        });
        //oppretter et lydavspillingsobjekt for introlyd
        AudioPlayer ap = new AudioPlayer();
        //spiller av lyden
        ap.introPlayback();        
    }
    
}
