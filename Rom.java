/**
 *
 * @author anders
 */
import java.io.*;
import java.util.Calendar;
public class Rom implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String romId;
    private String romType;
   // private boolean opptatt;
    
    Rom(String rId, String rName)
    {
        romId = rId;
        romType = rName;
       // opptatt = false;
    }
    
    public String getRomId()
    {
        return romId;
    }
   /* public void setOpptatt(boolean b)
    {
        opptatt = b;
    }
    public boolean getOptatt()
    {
        return opptatt;
    }*/
    public String toString()
    {
        String s = "";
        String i = "";
        //if(opptatt == true)
          //  i = "optatt!";
        //else
        //     i = "ledig!";
        s = "RomNr: " + romId + "\nRomType: " + romType + "\nStatus: " + i + "\n\n";
        return s;
    }
}
