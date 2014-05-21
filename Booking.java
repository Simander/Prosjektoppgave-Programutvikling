/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer et booking-objekt.
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Rom rom;
    private String formålOrKurs;
    private String gruppe;
    private Teacher laerer;
    private String personID;
    private Date startdato;
    private Date sluttdato;
    private Date sluttarVerdi;
    private String[] bookingTable = new String[7];
    private SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM");
    private SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm");
    Booking(Rom r, String f, String g, String p, Date start, Date slutt, Date realEnd)
    {
        //initialiserer felter
        rom = r;
        formålOrKurs = f;
        personID = p;
        startdato = start;
        sluttdato = slutt;
        sluttarVerdi = realEnd; //Date variabel lik sluttdato - ett minutt, for metode checkIfOverlap
        bookingTable[0] = rom.getRomId();
        bookingTable[1] = dFormat.format(start);
        bookingTable[2] = tFormat.format(start);
        bookingTable[3] = tFormat.format(slutt);
        bookingTable[4] = f;
        bookingTable[5] = g;
        bookingTable[6] = p;
        gruppe = g;
    }
    //get-metode som returnerer gruppen det bookingen gjelder for
    public String getGrup()
    {
        return gruppe;
    }
    //Metode som returnerer en tabellrad med bookingdata i form av et array
    public String[] getBookingRow()
    {
        return bookingTable;
    }
    //Get-metode returnerer Rommet som bookingen gjelder for.
    public Rom getRom()
    {
        return rom;
    }
    //Get-metode som returnerer bookingens startdato
    public Date getStartDato()
    {
        return startdato;
    }
    //Get-metode som returnerer bookingens sluttdato
    public Date getSluttDato()
    {
        return sluttdato;
    }
    //get-metode som returnerer fakeEnd
    public Date getFakeEnd()
    {
        return sluttarVerdi;
    }
}
