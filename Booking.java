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
    private String[] bookingTable = new String[5];
    public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM | HH:mm");
    Booking(Rom r, Kurs k, Person p, Date start, Date slutt)
    {
        person = p;
        startdato = start;
        sluttdato = slutt;
        bookingTable[0] = rom.getRomId();
        bookingTable[1] = kurs.getKursId();
        bookingTable[2] = person.getName();
        bookingTable[3] = dFormat.format(start);
        bookingTable[4] = dFormat.format(slutt);
        
       
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
}
