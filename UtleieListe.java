
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simander
 */
public class UtleieListe 
{
    private List<InstrumentUtleie> utleieliste;
    private List<InstrumentUtleie> historikk;
    
    UtleieListe()
    {
        utleieliste = new LinkedList<>();
        historikk = new LinkedList<>();
    }
    
    public void settInnUtleie(InstrumentUtleie iu)
    {
        utleieliste.add(iu);
    }
    public InstrumentUtleie getInstrumentUtleie(String icode)
    {
        InstrumentUtleie temp = null;
        for( int i = 0; i < utleieliste.size(); i++ )
        {
            if(utleieliste.get(i).getInstrument().getInstrumentId().equalsIgnoreCase(icode))
                temp = utleieliste.get(i);
        }
        return temp;
    }
    //metode som flytter utleie objektet til historikk listen, og setter Instrumentet i instrumentlisten til ledig.
    public void leggTilHistorikk(String s)
    {
        InstrumentUtleie temp = getInstrumentUtleie(s);
        for(int i= 0; i < utleieliste.size(); i++)
        {
            if(utleieliste.get(i) == temp)
            {
                utleieliste.get(i).setInnleveringsDato(Calendar.getInstance().getTime());
                utleieliste.get(i).getInstrument().Innlevering();
                historikk.add(temp); // legger objektet til i historikk-listen
                utleieliste.remove(i); //fjerner objektet fra utleie-listen
            }
        }
        
    }
}
