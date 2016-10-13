/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer et Rom-objekt.
 */

//import-setninger
import java.io.*;
import java.util.Calendar;
import java.util.Date;
public class Rom implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String romId;   //romID
    private String romType; //romType
    private BookingListe bookList;  //bookingliste
       //konstruktør
    Rom(String rId, String rName, BookingListe b)
    {
        //initialiserer felter
        romId = rId; 
        romType = rName;
        bookList = b;
       
       // opptatt = false;
    }
    //get-metode returnerer romID
    public String getRomId()
    {
        return romId;
    }
    //metode som sjekker rommets status for øyeblikket
    private boolean status()
    {
        Calendar dateStart = Calendar.getInstance();
        Date date1 = dateStart.getTime();
        Calendar dateSlutt = Calendar.getInstance();
        dateSlutt.add(Calendar.MINUTE, 30);
        Date date2 = dateSlutt.getTime();
        Boolean temp = bookList.checkIfOverlap(romId, date1, date2);
        return temp;
    }
    //toString-metode
    public String toString()
    {
        String s = "";
        String i = "";
        boolean state = status();
        if (state == true)
            i = "opptatt!";
        else
            i = "ledig!";
        //if(opptatt == true)
          //  i = "optatt!";
        //else
        //     i = "ledig!";
        s = "RomNr: " + romId + "\nRomType: " + romType + "\nStatus: " + i + "\n\n";
        return s;
    }
}
