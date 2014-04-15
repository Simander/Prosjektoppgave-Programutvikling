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
    private Pupil leietaker;
    private Calendar utleieperiode;
   
    public Instrument (String R, String T, String M, String Co) // Constructor
    {
        RegNum = R;
        Type = T;
        Model = M;
        Condition = Co;
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
    
    public void setLeietaker(Pupil p)
    {
        
       if (leietaker == null)
      {
          leietaker = p;
          utleid = true;
      }
    }
    
    public Pupil getLeietaker()
    {
        return leietaker;
    }
    public Boolean getUtleid()
    {
        return utleid;
    }
       
    public Calendar setUtleieStartDato() // Metode for å sette data ( satt til dagens dato)
    {
        regDate = Calendar.getInstance();
        return null;
    }
        
   // public void Calendar setUtleieSluttDato(InstrumentGui.getUtleieSluttDato()) // <- sette inn dato som mottas av gui
   // {
   //   Calendar UtleieSluttDato = InstrumentGui.getUtleieSluttDato();
   //   
   // }

    public String toString() // Metode som returnerer info;
    {
        String utskrift = "";
        if (utleid != false)
        {
            utskrift  = "Status: " + "Opptatt" + "\n" +
                        "Leietaker:" + leietaker + "\n";
        }
        else
            utskrift = "Status: " + "Ledig" +"\n";
        
           utskrift += "InstrumentNR: " + RegNum + "\n" +
                       "Instrument Type: " + Type + "\n" +
                       "Model: " + Model + "\n" +
                       "Tilstand: " + Condition + "\n" +
                       "Kommentarer: " + Comments + "\n"; 
      return utskrift;
    }
}
