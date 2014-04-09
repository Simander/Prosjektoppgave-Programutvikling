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
    private Calendar regDate; // Kalender
    DateFormat formata; // Kalender format
    Instrument next; // Neste
    private boolean utleid; // Boolean som returnerer true / false på ettetrspørrsel
    private Calendar endDate;
    private String leietaker;
    
    
    public Instrument (String R, String T, String M, String E, String Co, String Comm, boolean u, String l) // Constructor
    {
        RegNum = R;
        Type = T;
        Model = M;
        Extras = E;
        Condition = Co;
        Comments = Comm;
        next = null;
        utleid = u;
        leietaker = l;
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
    public String getLeietaker()
    {
        return leietaker;
    }
    public String setLeietaker(person)
    {
        if person != null
                {
                    leitaker = person;
                }
    }
    
    //public Calendar setUtleieStartDato() // <- sette inn dato som mottas av gui
    //{
        
   // }
    //public Calendar setUtleieSluttDato() // <- sette inn dato som mottas av gui
    //{
        
    //}
    public Calendar utleieperiode();// motta leieperide 
    {
        if (gui.leieperiode != null)
        {
            return leiedato;
        }
        else
            return null;
    
    public boolean utleid() // Metode for å definere om instrumentet er utleid eller ikke 
    {
        if(utleieperiode() != null)
        {
            return true;
        }
        else
    
        return false;
    }
    public String getUtleid(utleid()) // Get-metode for leiestatus
    {
        String S= RegNum;
        
        if(utleid == false)
        {
            return S + " Er Ledig"; // Returnerer status at den er ledig;
        }
        else
            return S + " Er utlånt" + " , Tilbake DATO"; // Sett inn dato for fra annen klasse
    }
}
