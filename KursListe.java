/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er en listeklasse, inneholder liste over Kurs.
 */
//import-setninger
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class KursListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    //Oppretter en ArrayList
    private List<Kurs> kursliste;
    //konstruktør
    KursListe()
    {
        //initialiserer listen
        kursliste = new ArrayList<>();
    }
    //metode for å sette inn et kurs i listen
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
        //Kurs kurset = null;
        for(int i = 0; i < kursliste.size(); i++)
        {
            if(kursliste.get(i).getKursId().equalsIgnoreCase(k))
                return kursliste.get(i);
        }
        return null;
    }
    //metode som finner gruppe med riktig ID, evt. returnerer null om den ikke finner gruppen
    public String findGruppe(String id)
    {
        for(int i = 0; i < kursliste.size(); i++)
        {
            String[] grup = kursliste.get(i).getGruppe();
            for(int k = 0; k < grup.length; k++)
            {
                if(grup[k].equalsIgnoreCase(id))
                    return id;
            }
        }
        return null;
    }
    //metode som fjerner kurs fra listen
    public String removeKurs(String k)
    {
        String temp = "";
        for(int i = 0; i < kursliste.size(); i++)
        {
            if(kursliste.get(i).getKursId().equalsIgnoreCase(k))
            {
                temp = kursliste.get(i).getKursId();
                kursliste.remove(i);
            }
        }
        return temp;
    }
}
