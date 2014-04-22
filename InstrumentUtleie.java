/*
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
    
    private Person leietaker;
    Instrument instrument;
    
     public InstrumentUtleie(Instrument i, Person l)
    {
        Instrument instrument = i;
        Person leietaker = l;
    }
    public String utleie()
    {
        Pupil p = (Pupil) leietaker;
        if(instrument.getUtleid() == true)
        {
            return "Ønsket Instrument er utlånt";
        }
        else
           instrument.setLeietaker(p);
           return instrument.getRegNum() + " er registrert \n "
                + "som utlånt til elevnr : " + p.getStudentcode() + "\n"
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
                    "Musikk instrumentet ønsket lånt er IKKE tilgjenglig";
    }   
}
