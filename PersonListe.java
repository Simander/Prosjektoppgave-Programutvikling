/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen inneholder lister over elever og lærere + metoder for å hente ut
 * data fra disse listene.
 */
import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class PersonListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Pupil> elever;     //elevliste
    private List<Teacher> laerere;  //lærereliste
   
    //Konstruktør som initialiserer 
    PersonListe()
    {
        elever = new LinkedList<>(); //Liste over elev-objekter
        laerere = new LinkedList<>(); //Liste over Lærer-objekter
         
    }
    //get-metode returnerer listen over elever
    protected List getElevliste()
    {
        return elever;
    }
    //get-metode returnerer listen over lærerere
    protected List getLaererListe()
    {
        return laerere;
    }
    //Metode for å sette inn en Person i en av de øvrige listene
    protected void settInnPerson(Person p)
    {
        if(p instanceof Pupil)
        {
            Pupil pu = (Pupil)p;
            elever.add(pu);
        }
        else if( p instanceof Teacher)
        {
            Teacher tu = (Teacher)p;
            laerere.add(tu);
        }
    }
    //Metode som lister opp toString for alle personer i registeret
    protected String listEntries()
    {
        String s =listLaerere()+listElever();
               
        return s;
    }
    //metode som lister opp alle lærere
    protected String listLaerere()
    {
        String s ="";
        ListIterator<Teacher> pIter = laerere.listIterator();
        while (pIter.hasNext())
        {
            s += pIter.next().toString() +"\n\n";
        }        
        return s;
    }
    //metode som sjekker hvor mange elever det er i en gruppe
    protected int gruppeSjekk(String gID)
    {
        int count = 0;
        for(int i = 0;i < elever.size(); i++)
        {
            if(elever.get(i).getGruppe() !=null && elever.get(i).getGruppe().equals(gID))
                count += 1;
        }
        return count;
    }
    //metode som lister opp alle elever
    protected String listElever()
    {
       String s ="";
        ListIterator<Pupil> pIter = elever.listIterator();
        while (pIter.hasNext())
        {
            s += pIter.next().toString() +"\n\n";
        }        
        return s;
    }
    //Metode som returnerer et person objekt med riktig navn
    public Person finnPerson(String n)
    {    
        ListIterator<Pupil> eIter = elever.listIterator();
        ListIterator<Teacher> tIter = laerere.listIterator();
        while (eIter.hasNext())
        {
            if(eIter.next().getName().equalsIgnoreCase(n))
                return eIter.previous();
         }
        while (tIter.hasNext())
        {
            if(tIter.next().getName().equalsIgnoreCase(n))
                return tIter.previous();
        }
        return null;
        
    }
    //metode som sjekker om det finnes en person med navn som inneholder innkommende parameter
    public boolean sjekkPerson(String n)
    {    
        ListIterator<Pupil> eIter = elever.listIterator();
        ListIterator<Teacher> tIter = laerere.listIterator();
        while (eIter.hasNext())
        {
            if(eIter.next().getName().toLowerCase().contains(n.toLowerCase()))
                return true;
         }
        while (tIter.hasNext())
        {
            if(tIter.next().getName().toLowerCase().contains(n.toLowerCase()))
                return true;
        }
        return false;

    }
    //metode som returnerer et Person-objekt med riktig personID
    public Person finnPersonID(String id)
    {    
        ListIterator<Pupil> eIter = elever.listIterator();
        ListIterator<Teacher> tIter = laerere.listIterator();
        while (eIter.hasNext())
        {
            if(eIter.next().getID().equalsIgnoreCase(id))
                return eIter.previous();
         }
        while (tIter.hasNext())
        {
            if(tIter.next().getID().equalsIgnoreCase(id))
                return tIter.previous();
        }
        return null;
        
    }
    //metode som sjekker om en lærer er ansvarlig for fag med innkommende ID
    protected Teacher finnAnsvarlig(String id)
    {
        ListIterator<Teacher> tIter = laerere.listIterator();
        for(int i = 0; i < laerere.size(); i++)
        {
            if(laerere.get(i).getAnsvarlig() != null && laerere.get(i).getAnsvarlig().equalsIgnoreCase(id))
                return laerere.get(i);
        }
        return null;
    }
    protected void fjernkursFraElev(String id)
    {
        for(int i = 0; i < elever.size(); i++)
        {
            if(elever.get(i).getKurs() != null && elever.get(i).getKurs().equalsIgnoreCase(id))
            {
                elever.get(i).setKurs("");
                elever.get(i).setGruppe("");
            }
            
        }
    }
    //metode som returnerer et Elev-objekt med riktig personID
    protected Pupil finnElever(String n)
    {    
        ListIterator<Pupil> eIter = elever.listIterator();
        while (eIter.hasNext())
        {
            if(eIter.next().getID().equalsIgnoreCase(n))
                return eIter.previous();
        }
        return null;
    }
    //Metode som returnerer et lærerobjekt med riktig personID
    protected Teacher finnLaerer(String n)
    {    
        ListIterator<Teacher> tIter = laerere.listIterator();
        while (tIter.hasNext())
        {
            if(tIter.next().getID().equalsIgnoreCase(n))
                return tIter.previous();
        }
        return null;       
    }
    //metode som fjerner en person med riktig personkode
    protected String fjernPerson(String s)
    {
        String temp = "";
        for(int i = 0; i < elever.size(); i++)
        {
            if(elever.get(i).getID().equalsIgnoreCase(s))
            {
                temp = elever.get(i).getName();
                elever.remove(i);
            }
        }
        for(int i = 0; i < laerere.size(); i++)
        {
            if(laerere.get(i).getID().equalsIgnoreCase(s))
            {
                temp = laerere.get(i).getName();
                laerere.remove(i);
            }
        }
        return temp;
    }
}
