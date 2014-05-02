
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

/**
 *
 * @author simander
 */
public class BookingListe implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private List<Booking> bookingListe;
    private List<String[]> bookingTableData;   
    
    BookingListe()
    {
        bookingListe = new LinkedList<>();       
    }
    public void settInnBooking(Booking b)
    {
        bookingListe.add(b);//Legger et bookingObjekt til lista
    }
    //Lager et dobbel-array med data fra bookingListe
    public String[][] getBookingTableData()
    {   int count = bookingListe.size();
        String[][] hjelpeArray = new String[count][];
        //Løper gjennom bookingListe og henter en rad med data for hver booking
        for(int i = 0; i < bookingListe.size(); i++)
        {
           hjelpeArray[i] = bookingListe.get(i).getBookingRow();          
        }
        return hjelpeArray;
    }
    public List getTableByRomId(String rId)
    {
        int count = bookingListe.size();
        List<String[]> lista = new ArrayList();
        for(int i = 0; i < bookingListe.size(); i++)
        {
            if(bookingListe.get(i).getRom().getRomId().equalsIgnoreCase(rId))
                lista.add(bookingListe.get(i).getBookingRow());
        }
        return lista;
    }
    public boolean checkIfOverlap(String rId, Date d, Date s)
    {
        Date start = d;
        Date slutt = s;
        String ledigOpptatt = "";
        boolean temp = false;
        Date tempo;
        
        //ListIterator<Booking> eIter = bookingListe.listIterator();
        for(int i = 0; i < bookingListe.size(); i++)
        {
            
            Date startet = bookingListe.get(i).getStartDato();
            Date sluttet = bookingListe.get(i).getFakeEnd();
            //JOptionPane.showMessageDialog(null,startet.toString() + "+" + slutten.toString() + "+" + sluttet.toString());
          
            if(bookingListe.get(i).getRom().getRomId().equals(rId))
                if(((start.after(startet) && slutt.before(sluttet)) ||      //startet->start->slutt->sluttet
                    (start.before(startet) && slutt.after(startet)) || //start->startet->slutt->
                    (start.before(sluttet) && slutt.after(sluttet)) ||  //start->sluttet-->slutt
                    (start.before(startet) && slutt.after(sluttet)))) //start->startet->slutt->sluttet
                  
                        temp = true;
        } 
        return temp;
       
    }
    //Metode som sjekker returnerer true dersom en booking finnes for innkommende romId
    public boolean checkExists(String rId)
    {
        boolean temp = false;
        for(int i = 0; i < bookingListe.size(); i++)
        {
            if(bookingListe.get(i).getRom().getRomId().equalsIgnoreCase(rId))
                temp = true;
        }
        return temp;
    }
    //metode
   public Booking getLast()
   {
       Booking temp = bookingListe.get(bookingListe.size()-1);
       return temp;
   }
    //metode som fjerner en person med riktig navn
    public void fjernGamleBooking()
    {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        
        ListIterator<Booking> bIter = bookingListe.listIterator();
        // blar gjennom lista så lenge den har objekter
        while (bIter.hasNext())
        {
            if(bIter.next().getSluttDato().before(now)) //sjekker om objektene har sluttdato før now
                bIter.remove();                         //og fjerner objektet om det er gammelt
        }
    }   
}
