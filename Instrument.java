/**
 *
 * @author joakim
 */
//Imports
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class Instrument implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String InstrumentId; // Systemets registrerings nummer
    private String brand;
    private String Type; // Type : Gitar, bass, etc..
    private String Model; // Modell: med på å beskrive hva det er
    private String Extras; // Info om div. kabler etc.
    private String Condition; // Instrumentets tilstand
    private String Comments; // Eventuelle kommentarer
    public Date regDate; // Kalender
    public Date endDate;
    public String innlevert;
    public String registrert;
    public String forrige;
    Instrument next; // Neste
    private boolean utleid; // Boolean som returnerer true / false på ettetrspørrsel
    private String leietaker;
    public SimpleDateFormat kFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    Historikk logg = new Historikk();
    public Instrument (String id, String mrk, String t, String m, String co) // Constructor
    {
        brand = mrk;
        InstrumentId = id;
        Type = t;
        Model = m;
        Condition = co;
        next = null;
    }
    public String getInstrumentId() // Get metode for registrerings nummer
    {
        return InstrumentId;
    }
    public String getType() // Get metode for Type
    {
        return Type;
    }
    public String getModel() // Get metode for Model
    {
        return Model;
    }
    public String getRegistrert() // Get metode for timestamp ut
    {
        return registrert;
    }
    public String getInnlevert() // Get metode for timestamp inn
    {
        return innlevert;
    }
    public String getCondition() // Get metode for Condition
    {
        return Condition;
    }
    void setLeietaker(Pupil p)
    {     
          Pupil temp = p;
          leietaker = p.getName();
          forrige = leietaker;
          utleid = true;
          regDate = new Date();
          registrert = kFormat.format(regDate);
          
    }
    public void removeLeietaker ()
       {
           
           leietaker = null;
           utleid = false;
           endDate = new Date();
           innlevert = kFormat.format(endDate);
           String objekt = getInstrumentId();
           String action ="Innlevert";
           logg.entry(objekt, action, forrige);
       }
    
    
    public String getLeietaker()
    {
        return leietaker;
    }
    public Boolean getUtleid()
    {
        return utleid;
    }
        
   public void Innlevering()
   {
       leietaker = null;
       utleid = false;
   }

    public String toString() // Metode som returnerer info;
    {
        String utskrift = "";
        if (utleid)
        {
            utskrift  = "Status: Utleid\n" +
                        "Leietaker:" + leietaker + "\n";
        }
        else
            utskrift = "Status: Ledig\n";
        
           utskrift += "InstrumentNR: " + InstrumentId + "\n" +
                       "Merke: " + brand + "\n" +
                       "Instrument Type: " + Type + "\n" +
                       "Model: " + Model + "\n" +
                       "Tilstand: " + Condition + "\n";
      return utskrift;
    }
}
