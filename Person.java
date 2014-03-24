import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anders
 */
public class Person 
{
    private long personalNum;
    private String name;
    private String address;
    private long telephoneNum;
    
    Person next;
    //Konstruktør
    Person(long p, String n, String a, long t)
    {
        personalNum = p;
        name = n;
        address = a;
        telephoneNum = t;
       
                  
    }
    //metode for å hente ut personnummer
    public long getpersonalNum()
    {
        return personalNum;
    }
    //toString() metode
    public String toString()
    {
        String s = "PersonNr: " + personalNum + "\nNavn: " + name + "\nAdresse: " + address + "\nTelefonNr: " + telephoneNum;
        return s;
    }
}
//Klasse som symboliserer lærere som en subklasse av person
class Teacher extends Person{
    private String qualifications;
    private List<String> instruments = new ArrayList<>();
    private double salary;
    
//Konstruktør
    Teacher(long p, String n, String a, long t, String q, String i, double s)
    {
      super(p, n, a, t);
      qualifications = q;
       }
    //metode for å sette lærerens instrumenter. Kan lagre flere i en arraylist
    public void setInstrument(String inst)
    {
       
        for(int i = 0; i < instruments.size(); i++)
            if(instruments.get(i) == null)
                instruments.set(i, inst);
    }
    //metode for å hente ut lærerens instrumenter
    public String getInstruments()
    {
        String s= "";
        for(int i = 0; i < instruments.size(); i++)
        {
            s += instruments.get(i) + ", ";
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
        String s = super.toString() + "\nKvalifikasjoner: " + qualifications + "\nInstrumenter: " + getInstruments();
        return s;
    }
    
           
}
class Pupil extends Person
{
    private String Instrument;
    private Teacher teacher;
    private Kurs kurs;
    //Konstruktør registrerer en ny elev ved skolen
    public Pupil(String n, String a, long t, long p)
    {
        super(p, n, a, t);
    }
    //Tildeler eleven en lærer
    public void setTeacher(Teacher t)
    {
        teacher = t;
    }
    //Melder eleven på et kurs        
    public void setKurs(Kurs k)
    {
        kurs = k;
    }
    //redefinert toString()
    public String toString()
    {
        String s = super.toString();
        if ( teacher != null)
            s += "\nLærer: " + teacher;
        if( kurs != null)
            s += "\nKurs: " + kurs;
        return s;
    }
}
