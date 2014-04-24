/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joakim
 */
public class HistorikkListe
{
    private static final long serialVersionUID = 1L;
    private HistorikkEntry first;
    private String Loggobjekt;
    InstrumentRegister Instrumenter = new InstrumentRegister();
    
    public HistorikkListe()
    {
        first = null;
        
    }
    
    public void Instrument setLoggobjektins()
    {
        Instrument Instrument;
        Loggobjekt = Instrumenter.findID();
        
    }

    public void insertHistorikk(HistorikkEntry ny)
    {
        if (ny == null)
            return;
        if(first == null)
        {  
            first = ny;
            first.Logg(Loggobjekt);
           
        }
        else
        {   HistorikkEntry helper = first;
            while( helper.next != null )
            {
                helper = helper.next;
            }
            helper.next = ny;
        }
    }
      
    public String listEntries()
	{
                HistorikkEntry HistorikkEntry;
                
		HistorikkEntry runner = first;
		String info;
		if(first != null)
		{
                        
			info = first.Logg(Loggobjekt);
			while ( runner.next != null)
			{
				runner = runner.next;
				info += "\n" + runner.Logg(Loggobjekt) +"\n";
			}
		}
		else
			info="Ingen Loggføringer";
		return info;
	}
