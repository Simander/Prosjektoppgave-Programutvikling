*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joakim
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar; 
import java.text.DateFormat;

public class InstrumentUtleie 
{
    
    private Pupil leietaker;
    Instrument instrument;
    
    public InstrumentUtleie(Instrument i, Pupil l)
    {
        Instrument instrument = i;
        Pupil leietaker = l;
    }
    public String utleie()
    {
        if(instrument.getUtleid() == true)
        {
            return "Ønsket Instrument er utlånt";
        }
        else
           instrument.setLeietaker(leietaker);
           return instrument.getRegNum() + " er registrert \n "
                + "som utlånt til elevnr : " + leietaker.getStudentcode() + "\n"
                + "Dato : " + Calendar.getInstance();
    }
    public String innlevering()
    {
        if(instrument.getUtleid() == false)
        {
            return "Ikke mulig å levere (ikke registrert som utlånt)";
        }
        else
            instrument.Innlevering();
            return instrument.getRegNum() + " er registrert \n "
                    + "som innlevert \n" + "Dato : " + Calendar.getInstance();
    }
    public String sjekkLedig()
    {
        instrument.getUtleid();
        if(instrument.getUtleid() == false)
        {
            return "Musikk instrumentet ønsket lånt er tilgjenglig";
        }
        else
            return
                    "Musikk instrumentet ønsket lånt er tilgjenglig";
    }
    
    
}
