/**
 *
 * @author simander
 */
import java.util.*;
import java.io.*;
public class PersonListe implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Person> personer;
   
    
    PersonListe()
    {
        personer = new LinkedList<>();
         
    }
    //Metode for å sette inn en Person i listen
    public void settInnPerson(Person p)
    {
        personer.add(p);
    }
    //Metode som lister opp toString for alle personer i registeret
    public String listEntries()
    {
        String s ="";
        ListIterator<Person> pIter = personer.listIterator();
        while (pIter.hasNext())
        {
            s += pIter.next().toString() +"\n\n";
        }        
        return s;
    }
    public String listElever()
    {
        String s ="";
        ListIterator<Person> pIter = personer.listIterator();
        while (pIter.hasNext())
        {
            if(pIter.next() instanceof Pupil)
            {
               // Pupil p = (Pupil)pIter.next();
                s+= pIter.previous().toString() + "\n\n";
            }
            else
              pIter.next();
        }
        return s;
    }
    //Metode som returnerer et persom objekt med riktig navn
    public Person finnPerson(String n)
    {    
        ListIterator<Person> pIter = personer.listIterator();
        while (pIter.hasNext())
        {
            if(pIter.next().getName().equalsIgnoreCase(n))
                return pIter.previous();
         }        
        return null;
    }
    public void fjernPerson(String s)
    {
        Pupil p; Teacher t;
        ListIterator<Person> pIter = personer.listIterator();
        while (pIter.hasNext())
        {
            if(pIter.next() instanceof Pupil)
            {   p = (Pupil)pIter.previous();
                if( p.getStudentcode().equals(s))
                pIter.remove();
            }
            else if(pIter.next() instanceof Teacher)
            {
               long pNum = Long.parseLong(s); 
               t = (Teacher)pIter;
               if(t.getpersonalNum()==(pNum))
                   pIter.remove();
            }
            else
                pIter.next();
        }
    }
    
}
