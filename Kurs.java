import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.io.*;

/**
 *
 * @author anders
 */
public class Kurs implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String kursId;
    private String kursNavn;
    private Teacher tutor;
    private List<Pupil> elever = new ArrayList<>(3);
    private String[] kursTable = new String[5];    
           
    public Kurs(String kId, String kname, Teacher t)
    {
        kursId = kId;
        kursNavn = kname;
        tutor = t;
        kursTable[0] = kursId;
        kursTable[1] = kursNavn;
        kursTable[2] = t.getName();
    }

    public void setElev(Pupil p)
    {
        elever.add(p);
    }
    public String getKursId()
    {
        return kursId;
    }
    public Teacher getTeacher()
    {
        return tutor;
    }
    public void setTeacher( Teacher t)
    {
        tutor = t;
    }
    public List<Pupil> getElever()
    {
       return elever;
    }
    public String toString()
    {
       String s;
       s = kursId + ", " + kursNavn + ", " + tutor.getName();
       return s;
    }
}
