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
import java.util.*;
//Denne klassen er et utleieObjekt som skal settes inn i en liste av utleieobjekter
//som da blir en liste over utleide instrumenter.
public class InstrumentUtleie 
{ //Set-metode som setter dato for innlevering (VIKTIG!!! brukes i forbindelse med innlevering!)
    
    private Pupil leietaker;
    private Instrument instrument;
    //Viktige felter!!!
    private Date utlevering;
    private Date innlevering;
    
    InstrumentUtleie(Instrument i, Pupil l)
    {
        //Når utleie-objektet opprettes når instrumentet leveres ut, da settes utleveringsdato til dette øyeblikket.
        utlevering = Calendar.getInstance().getTime();
        instrument = i;
        instrument.setLeietaker(l);
        leietaker = l;
    }
    //Get-metode som returnerer instrument (VIKTIG!!!)
    public Instrument getInstrument()
    {
        return instrument;
    }
    
    //Get-metode som returnerer leietakeren (VIKTIG!!!)
    public Pupil getLeietaker()
    {
        return leietaker;
    }
    //Set-metode som setter dato for innlevering (VIKTIG!!! brukes i forbindelse med innlevering!)
    public void setInnleveringsDato(Date d)
    {
        innlevering = d;
    }
    
    //Denne metoden er overflødig i denne klassen, da du foretar sjekk på instrumentet om det er utleid,
    //da dette er et objekt av typen utleidinstrument er alle instrumenter i dette objektet utleid  
    /*public String utleie()
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
    }*//*
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
    }*/
    
    
}
