/*
@author anders
 */
public class PersonRegister 
{
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
