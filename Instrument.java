/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joakim
 */
//Imports
import java.util.Calendar; 
import java.text.DateFormat;

public class Instrument 
{
    private String RegNum; // Systemets registrerings nummer
    private String Type; // Type : Gitar, bass, etc..
    private String Model; // Modell: med på å beskrive hva det er
    private String Extras; // Info om div. kabler etc.
    private String Condition; // Instrumentets tilstand
    private String Comments; // Eventuelle kommentarer
    public Calendar regDate; // Kalender
    DateFormat formata; // Kalender format
    Instrument next; // Neste
    private boolean utleid; // Boolean som returnerer true / false på ettetrspørrsel
    public Calendar endDate;
    
    
    public Instrument (String R, String T, String M, String E, String Co, String Comm) // Constructor
    {
        RegNum = R;
        Type = T;
        Model = M;
        Extras = E;
        Condition = Co;
        Comments = Comm;
        next = null;
    }
    
    public String getRegNum() // Get metode for registrerings nummer
    {
        return RegNum;
    }
    
    public String getType() // Get metode for Type
    {
        return Type;
    }
    
    public String getModel() // Get metode for Model
    {
        return Model;
    }
    
    public String getExtras() // Get metode for Extras
    {
        return Extras;
    }
    
    public String getCondition() // Get metode for Condition
    {
        return Condition;
    }
    
    public String getComments() // Get metode for Comments
    {
        return Comments;
    }
    //public String getLeietaker()
    //{
      //  return leietaker;
    //}
    public String setLeietaker(person.getname())
    {
        String leietaker;
       if (leietaker == null)
      {
          leitaker = person.getname();
      }
    }
    
    public Calendar setUtleieStartDato() // Metode for å sette data ( satt til dagens dato)
    {
        regDate = Calendar.getInstance();
        return null;
    }
        
    public Calendar setUtleieSluttDato(InstrumentGui.getUtleieSluttDato()) // <- sette inn dato som mottas av gui
    {
      Calendar UtleieSluttDato = InstrumentGui.getUtleieSluttDato();
      return UtleieSluttDato;
    }
    public Calendar utleieperiode();// motta leieperide 
    {
        if (endDate == null || regDate = null)
        {
            return null;
        }
            else   
           utleieperiode = endDate - regDate; 
           return utleieperiode;
    }

    //Set metode for utleid utleid
    
    public String toString() // Metode som returnerer info;
    {
        return RegNum + "\n" +
                Type + "\n" +
                Model + "\n" +
                Condition + "\n" +
                Comments + "\n";   
    }
}
