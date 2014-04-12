
//import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author anders
 */
public class RomListe 
{
    //Oppretter en ArrayList
    private List<Rom> romliste = new ArrayList<>();
    
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
