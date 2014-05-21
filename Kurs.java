/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer et Kurs-objekt.
 */

import java.io.*;

public class Kurs implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String kursId; //Kurskode som identifiserer det spesifikke kurset
    private String kursNavn;    //navn på kurs
    private String ansvarlig;  //Lærereren som har ansvaret for kurset
    private String kursType;    //kursType
    private String[] kurstyper = PersonRegVindu.instrumentTypeMain; //kurstype verdier
    //private List<Gruppe> grupper = new ArrayList<>(); //Liste over grupper som tar kurset;
    private String[] gruppe; //antall grupper pr kurs
           
    //Konstruktør
    public Kurs(String kId, String kname, String lName, String kType)
    {
        //initialiserer variabler
        kursId = kId;
        kursNavn = kname;
        ansvarlig = lName;
        kursType = kType;
        int count = 0;
        gruppe = new String[10]; //antall grupper pr kurs
        //løkke som genererer 10 Grupper for kurs-objektet
        for(int i = 0; i < gruppe.length; i++)
        {
            for(int k=0; k<kurstyper.length; k++)
            {
                if(kurstyper[k].equalsIgnoreCase(kursType))
                {
                    count += 1;
                    gruppe[i] = kursId + "G" + count;
                }
            }
        }
    }
    //metode som finner en gruppe med riktig ID, evt returnerer null om gruppen ikke finnes
    protected String FindGruppe(String id)
    {
        for(int i = 0; i < gruppe.length; i++)
        {
            if(gruppe[i].equalsIgnoreCase(id))
                return gruppe[i];
                
        }
        return null;
    }
    //get-metode som returnerer arrayet gruppe
    protected String[] getGruppe()
    {
        return gruppe;
    }
    //get-metode som returnerer kursID
    protected String getKursId()
    {
        return kursId;
    }
    //get-metode som henter ansvarlig lærer
    protected String getTeacher()
    {
        return ansvarlig;
    }
    //set-metode som setter lærer ansvarlig for kurs
    protected void setTeacher( Teacher t)
    {
        ansvarlig = t.getName();
    }
    //toString-metode
    public String toString()
    {
       String s ="";
       s = "KursId: " + kursId + "\nKursNavn: " + kursNavn + "\nKursType: " + kursType + "\nLærer: " + ansvarlig + "\n";
    
       return s;
    }
}
