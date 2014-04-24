/**
 *
 * @author anders
 */
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.LinkedList;
/**
 *
 * @author anders
 */
public class KursListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    //Oppretter en ArrayList
    private List<Kurs> kursliste;
    KursListe()
    {
        kursliste = new ArrayList<>();
    }
    public void settInnKurs(Kurs k)
    {
        kursliste.add(k);
    }
    //metode som henter all data i listen
    public String hentListeData()
    {
        String s = "";
        for(int i = 0; i < kursliste.size(); i++)
        {
            s += kursliste.get(i).toString();
            
        }
        return s;
    }
    
    //returnerer et array av kursId.
    public String[] kursStrings()
    {
        String[] temp = new String[kursliste.size()];
        for(int i = 0; i < kursliste.size(); i++)
        {
            temp[i] = kursliste.get(i).getKursId();
        }
        return temp;
    }
    //metode som returnerer riktig kurs
    public Kurs finnKurs(String k)
    {
        Kurs kurset = null;
        for(int i = 0; i < kursliste.size(); i++)
        {
            if(kursliste.get(i).getKursId().equalsIgnoreCase(k))
                kurset = kursliste.get(i);
        }
        return kurset;
    }
    //metode som fjerner rom fra listen
    public void removeRom(String r)
    {
        for(int i = 0; i < kursliste.size(); i++)
        {
            if(kursliste.get(i).getKursId().equals(r))
                kursliste.remove(i);
        }
    }
}
