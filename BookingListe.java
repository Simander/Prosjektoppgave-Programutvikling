import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public boolean checkExist(String rId)
    {
        boolean temp = false;
        for(int i = 0; i < bookingListe.size(); i++)
        {
            if(bookingListe.get(i).getRom().getRomId().equalsIgnoreCase(rId))
                temp = true;
        }
        return temp;
    }
    
    /* public String[][] getTableByRoomId()
    {   int count = bookingListe.size();
        String[][] hjelpeArray = new String[count][];
        //Løper gjennom bookingListe og henter en rad med data for hver booking
        for(int i = 0; i < bookingListe.size(); i++)
        {
            
           hjelpeArray[i] = bookingListe.get(i).getBookingRow();          
        }
        return hjelpeArray;
    }*/
    //metode som fjerner en person med riktig navn
    public void fjernGamleBooking()
    {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        for(int i = 0; i < bookingListe.size(); i++)
        {
            if(bookingListe.get(i).getSluttDato().before(now))
                bookingListe.remove(i);
        }
    }   
}
