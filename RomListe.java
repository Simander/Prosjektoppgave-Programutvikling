/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer et Rom-objekt.
 */

//import-setninger
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RomListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    //Oppretter en ArrayList
    private List<Rom> romliste;
    //konstuktør
    RomListe()
    {
        //initialiserer arraylist
        romliste = new ArrayList<>();
    }
    //metode for å sette inn et nytt rom i listen
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
    //metode som finner et rom med riktig ID
    public Rom finnRom(String roomId)
    {
        Rom rommet = null;
        for(int i = 0; i <romliste.size(); i++)
        {
            if(romliste.get(i).getRomId().equalsIgnoreCase(roomId))
                rommet = romliste.get(i);
        }
        return rommet;
    }
    //metode som fjerner rom med riktig ID fra listen
    public String removeRom(String id)
    {
        String temp = "";
        for(int i = 0; i < romliste.size(); i++)
        {
            if(romliste.get(i).getRomId().equals(id))
            {
                temp = romliste.get(i).getRomId();
                romliste.remove(i);
            }
        }
        return temp;
    }
}
