/**
 *
 * @author joakim
 */
import java.io.*;

public class InstrumentRegister implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Instrument first;
    
    
    public InstrumentRegister()
    {
        first = null;
    }

    public void insertInstrument(Instrument ny)
    {
        if (ny == null)
            return;
        if(first == null)
        {  
            first = ny;
           
        }
        else
        {   Instrument helper = first;
            while( helper.next != null )
            {
                helper = helper.next;
            }
            helper.next = ny;
        }
    }
    public Instrument findInstrument( String RegNum )
    {
        Instrument runner = first;
       	if (runner != null && (runner.getInstrumentId().equalsIgnoreCase(RegNum)))
            return runner;
        else if (runner == null)
            return null;
        else
	{
            while ( runner.next != null && !((runner.next.getInstrumentId()).equalsIgnoreCase(RegNum)))
            {
                runner = runner.next;
            }
	}
        return runner.next;
    }
    public Instrument findType( String type )
    {
        Instrument runner = first;
       	if (runner != null && (runner.getType().equalsIgnoreCase(type)))
            return runner;
        else if (runner == null)
            return null;
        else
	{
            while ( runner.next != null && !((runner.next.getType()).equalsIgnoreCase(type)))
            {
                runner = runner.next;
            }
                        
	}
        return runner.next;
    }
    public Instrument findModel( String Model )
    {
        Instrument runner = first;
       	if (runner != null && (runner.getModel().equalsIgnoreCase(Model)))
            return runner;
        else if (runner == null)
            return null;
        else
	{
            while ( runner.next != null && !((runner.next.getModel()).equalsIgnoreCase(Model)))
            {
                runner = runner.next;
            }     
	}
        return runner.next;
    }
        public String delIns( String regNum)
	{
            String temp = "";

            Instrument runner = first;
            if (runner != null && runner.getInstrumentId().equals(regNum))
                    first = runner.next;
            else
            {
                    while ( runner.next.getInstrumentId() != null && !(runner.next.getInstrumentId().equals(regNum)) )
                    {
                    runner = runner.next;

                    }
                    runner.next = runner.next.next;
            }
            return regNum;
	}
    public String listEntries()
	{
                Instrument instrument;
                
		Instrument runner = first;
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
			info="Ingen Instrument i registeret!";
		return info;
	}   
}
