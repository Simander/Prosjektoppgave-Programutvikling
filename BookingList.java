/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anders
 */
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.text.*;
   // private List<String>
import javax.swing.*;

public class BookingList implements Serializable
{
    private static final long serialVersionUID = 1L;
    //Oppretter en ArrayList
    private List<String[]> bookingListe;
    private List<Object[]> realListe;
    BookingList()
    {
        bookingListe = new ArrayList<>();
        realListe = new ArrayList<>();
    }
    public void settInnData(String[] o)
    {
        bookingListe.add(o);
    }
    //metode som henter all data i listen
    public String[][] hentListeData()
    {   int count = bookingListe.size();
        String[][] hjelpeliste = new String[count][];
        String s = "";
        for(int i = 0; i < bookingListe.size(); i++)
        {
           hjelpeliste[i] = bookingListe.get(i);          
        }
        return hjelpeliste;
    }
    public List getList()
    {
        return bookingListe;
    }
    //metode som fjerner rom fra listen
    public void removeOldBooking()
    {
        try{
     
        String[] ob = new String[3];
        for(int i = 0; i <  bookingListe.size(); i++)
        {
            
            ob = bookingListe.get(i);
            Calendar cal1 = Calendar.getInstance();
            String test  = cal1.YEAR +"/"+ ob[3] + ":00";
            Date d1 = new SimpleDateFormat("yyyy/dd/MM | HH:mm:ss").parse(test);
             Calendar cal = Calendar.getInstance();
            Date n = cal.getTime();
            if (n.after(d1))
                bookingListe.remove(i);
        }
        }
        catch(ParseException e){JOptionPane.showMessageDialog(null, "Noe gikk galt!");}
    }
}


