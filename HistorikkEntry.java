import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistorikkEntry 
{
    public SimpleDateFormat kFormat = new SimpleDateFormat("HH:mm");
    public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM");
    private String Dato;
    private String Klokkeslett;
    private Calendar Now;
    private Person leietaker;
    HistorikkEntry next;
    String Loggobjekt;
    //DateFormat formata;
    
    
    HistorikkEntry (Calendar N,String D, String K, Person l)
    {
        Now = N;
        Dato = D;
        Klokkeslett = K;
        leietaker = l;
        next = null;
        
        
    }
        public String Logg( String Loggobjekt) //Legg til RomNr etc.
    {
        Now = Calendar.getInstance();
        Dato = dFormat.format(Now);
        Klokkeslett = kFormat.format(Now);
        String Print = Dato + " " + Klokkeslett + " " + Loggobjekt;
        return Print;
    }
}

    
    
