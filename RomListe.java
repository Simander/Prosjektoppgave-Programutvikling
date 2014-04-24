import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 *
 * @author anders
 */
public class RomListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    //Oppretter en ArrayList
    private List<Rom> romliste;
    RomListe()
    {
        romliste = new ArrayList<>();
    }
    public void settInnRom(Rom r)
    {
        romliste.add(r);
    }
    //metode som henter all data i listen
    public String hentListeData()
    {
        String s = "";
        for(int i = 0; i < romliste.size(); i++)
        {
            s += romliste.get(i).toString();
            
        }
        return s;
    }
    
    //returnerer et array av romId.
    public String[] romStrings()
    {
        String[] temp = new String[romliste.size()];
        for(int i = 0; i < romliste.size(); i++)
        {
            temp[i] = romliste.get(i).getRomId();
        }
        return temp;
    }
    //
    public Rom finnRom(String r)
    {
        Rom rommet = null;
        for(int i = 0; i <romliste.size(); i++)
        {
            if(romliste.get(i).getRomId().equalsIgnoreCase(r))
                rommet = romliste.get(i);
        }
        return rommet;
    }
    
    //metode som fjerner rom fra listen
    public void removeRom(String r)
    {
        for(int i = 0; i < romliste.size(); i++)
        {
            if(romliste.get(i).getRomId().equals(r))
                romliste.remove(i);
        }
    }
}
