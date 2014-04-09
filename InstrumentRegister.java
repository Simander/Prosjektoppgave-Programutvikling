/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joakim
 */

public class InstrumentRegister
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
}
