import java.io.Serializable;

/**
@author anders
 */
public class PersonRegister implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Person first;
    
    public PersonRegister()
    {
        first = null;
    }

    public void insertPerson(Person ny)
    {
        if (ny == null)
            return;
        if(first == null)
        {  
            first = ny;
           
        }
        else
        {   Person helper = first;
            while( helper.next != null )
            {
                helper = helper.next;
            }
            helper.next = ny;
           
        }
    }
    //metode som finder eieren til en bil ved hjelp av registreringsnummer
    public Person findName( String navn )
    {
	        
        Person runner = first;
	if (runner != null && runner.getName().equals(navn))
            return runner;
        else if (runner == null)
            return null;
        else
	{
            while ( runner.next != null && !(runner.next.getName().equals(navn)))
            {
                runner = runner.next;
            }
                       
	}
        return runner.next;
    }
    //Metode som finner data om alle elever og lagrer det i en streng       
    public String findPupil()
    {
        Person person;
            
	Person runner = first;
	String info = "";
	if(runner != null)
        {
            if(runner instanceof Pupil)
                info=first.toString() +"\n";
            while ( runner.next != null)
            {
		runner = runner.next;
                if(runner instanceof Pupil)
                    info += "\n" + runner.toString() +"\n";
            }
	}
	else
            info="Ingen personer i registeret!";
        return info;
    }
    public String findTeacher()
    {
        Person person;
            
	Person runner = first;
	String info = "";
	if(runner != null)
        {
            if(runner instanceof Teacher)
                info=first.toString() +"\n";
            while ( runner.next != null)
            {
		runner = runner.next;
                if(runner instanceof Teacher)
                    info += "\n" + runner.toString() +"\n";
            }
	}
	else
            info="Ingen personer i registeret!";
        return info;
    }
     //Metode som finner data om alle elever og lagrer det i en streng  
        
    //metode som lister opp alle entries
    public String listEntries()
	{
                Person person;
                
		Person runner = first;
		String info;
		if(first != null)
		{
                        
			info=first.toString() +"\n";
			while ( runner.next != null)
			{
				runner = runner.next;
				info += "\n" + runner.toString() +"\n";
			}
		}
		else
			info="Ingen personer i registeret!";
		return info;
	}
}
