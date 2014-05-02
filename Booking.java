/**
 *
 * @author anders
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Booking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Rom rom;
    private Kurs kurs;
    private Teacher laerer;
    private Person person;
    private Date startdato;
    private Date sluttdato;
    private Date sluttarVerdi;
    private String[] bookingTable = new String[6];
   // public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM | HH:mm");
    public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM");
    public SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm");
    Booking(Rom r, Kurs k, Person p, Date start, Date slutt, Date realEnd)
    {
        rom = r;
        kurs = k;
        person = p;
        startdato = start;
        sluttdato = slutt;
        sluttarVerdi = realEnd; //Date variabel lik sluttdato - ett minutt, for metode checkIfOverlap
        bookingTable[0] = rom.getRomId();
        bookingTable[1] = dFormat.format(start);
        bookingTable[2] = tFormat.format(start);
        bookingTable[3] = tFormat.format(slutt);
        bookingTable[4] = kurs.getKursId();
        bookingTable[5] = person.getName();
      
        
       
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
    //Get-metode returnerer Kurset rommet er booket for;
    public Kurs getKurs()
    {
        return kurs;
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
    //
    public Date getFakeEnd()
    {
        return sluttarVerdi;
    }
}
