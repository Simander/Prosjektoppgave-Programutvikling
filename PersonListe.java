/**
 *
 * @author simander
 */
import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class PersonListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Pupil> elever;
    private List<Teacher> laerere;
   
    //Konstruktør som initialiserer 
    PersonListe()
    {
        elever = new LinkedList<>(); //Liste over elev-objekter
        laerere = new LinkedList<>(); //Liste over Lærer-objekter
         
    }
    public List getElevliste()
    {
        return elever;
    }
    //Metode for å sette inn en Person i n av de øvrige listene
    public void settInnPerson(Person p)
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
    public String listEntries()
    {
        String s =listLaerere()+listElever();
               
        return s;
    }
    //metode som lister opp alle lærere
    public String listLaerere()
    {
        String s ="";
        ListIterator<Teacher> pIter = laerere.listIterator();
        while (pIter.hasNext())
        {
            s += pIter.next().toString() +"\n\n";
        }        
        return s;
    }
    //metode som lister opp alle elever
    public String listElever()
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
    //metode som fjerner en person med riktig navn
    public void fjernPerson(String s)
    {
        for(int i = 0; i < elever.size(); i++)
        {
            if(elever.get(i).getName().equalsIgnoreCase(s))
                elever.remove(i);
        }
        for(int i = 0; i < laerere.size(); i++)
        {
            if(laerere.get(i).getName().equalsIgnoreCase(s))
                laerere.remove(i);
        }
    }
}


/**
 *
 * @author simander
 */
import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class PersonListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Pupil> elever;
    private List<Teacher> laerere;
   
    //Konstruktør som initialiserer 
    PersonListe()
    {
        elever = new LinkedList<>(); //Liste over elev-objekter
        laerere = new LinkedList<>(); //Liste over Lærer-objekter
         
    }
    public List getElevliste()
    {
        return elever;
    }
    //Metode for å sette inn en Person i n av de øvrige listene
    public void settInnPerson(Person p)
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
    public String listEntries()
    {
        String s =listLaerere()+listElever();
               
        return s;
    }
    //metode som lister opp alle lærere
    public String listLaerere()
    {
        String s ="";
        ListIterator<Teacher> pIter = laerere.listIterator();
        while (pIter.hasNext())
        {
            s += pIter.next().toString() +"\n\n";
        }        
        return s;
    }
    //metode som lister opp alle elever
    public String listElever()
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
    //metode som fjerner en person med riktig navn
    public void fjernPerson(String s)
    {
        for(int i = 0; i < elever.size(); i++)
        {
            if(elever.get(i).getName().equalsIgnoreCase(s))
                elever.remove(i);
        }
        for(int i = 0; i < laerere.size(); i++)
        {
            if(laerere.get(i).getName().equalsIgnoreCase(s))
                laerere.remove(i);
        }
    }
}
