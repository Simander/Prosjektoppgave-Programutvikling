import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
import java.util.ListIterator;

/*
 @author anders
 */
public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private long telephoneNum;
    Person next;
    //Konstruktør
    Person(String n, String a, long t)
    {
      
        name = n;
        address = a;
        telephoneNum = t;
        next = null;
                  
    }
    //metode for å hente ut personnummer

    public String getName()
    {
        return name;
    }
    //toString() metode
    public String toString()
    {
        String s = "Navn: " + name + "\nAdresse: " + address + "\nTelefonNr: " + telephoneNum;
        return s;
    }
}
//Klasse som symboliserer lærere som en subklasse av person
class Teacher extends Person{
    private long personalNum;
    private String qualifications;
    private List<String> instruments = new ArrayList<>();
    private double salary;
    
//Konstruktør
    Teacher(long p, String n, String a, long t, String q)
    {
      super(n, a, t);
      personalNum = p;
      qualifications = q;
       }
    public long getpersonalNum()
    {
        return personalNum;
    }
    //metode for å sette lærerens instrumenter. Kan lagre flere i en arraylist
    public void setInstrument(String inst)
    {
        instruments.add(inst);
    }
    //metode for å hente ut lærerens instrumenter
    public String getInstruments()
    {
        String s= "";
        for(int i = 0; i < instruments.size(); i++)
        {
            s += instruments.get(i) + " ";
        }
        return s;
    }
    //metode for å hente ut lærerens lønn
    public double getSal()
    {
        return salary;
    }
    //Metode for å sette ny lønn
    public void setSalary(double newSal)
    {
       salary = newSal; 
    }
    //redefinert toString metode
    public String toString()
    {
        String s = "Lærer: \nPersonNr: " + personalNum + "\n" + super.toString() + "\nKvalifikasjoner: " + qualifications + "\nInstrumenter: " + getInstruments();
        return s;
    }
    
           
}
class Pupil extends Person
{
    private String studentcode;
    private String instrument;
    /*
    private Teacher teacher;
    private Kurs kurs;*/
    //Konstruktør registrerer en ny elev ved skolen
    public Pupil(String s, String n, String a, long t, String i)
    {
        super(n, a, t);
        instrument = i;
        studentcode = s;
        
    }
    public String getStudentcode()
    {
        return studentcode;
    }
    //Tildeler eleven en lærer
   /* public void setTeacher(Teacher t)
    {
        teacher = t;
    }
    //Melder eleven på et kurs        
    public void setKurs(Kurs k)
    {
        kurs = k;
    }
    //redefinert toString()*/
    public String toString()
    {
        String s = "Elev: \nStudentkode: " + studentcode + "\n" + super.toString();
        s += "\nInstrument: " + instrument;
       /* if ( teacher != null)
            s += "\nLærer: " + teacher.getName();
        if( kurs != null)
            s += "\nKurs: " + kurs;*/
        return s;
    }
}
