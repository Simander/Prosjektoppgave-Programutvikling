/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer person objekter, + subklassene Pupil og Teacher.
 */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public abstract class Person implements Serializable
{
    private static final long serialVersionUID = 1L;    //serialiseringsID
    private String personID;        //person ID, starter med E for person av type elev eller L for person av type lærer
    protected String fodselsdato;   //personens fødselsdato
    private String name;            //personens navn
    private String address;         //personens adresse
    private String postNr;          //postnummer
    private long telephoneNum;      //personens telefonnummer
    protected String instrument1; //hovedInstrument
    protected String instrument2; //sideinstrument
    private SimpleDateFormat dForm1 = new SimpleDateFormat("ddMMyyyy"); //Dateformat1
    private SimpleDateFormat dForm2 = new SimpleDateFormat("dd-MM-yyyy");   //dateformat2
    Person next;
    //Konstruktør
    Person(String id, int fd, String n, String a, String pNr, long t, String i1, String i2) 
    {
        try
        {
            Date fodeDato = dForm1.parse(""+fd);    //tar imot innkommende String og konverterer til Date-objekt
            fodselsdato = dForm2.format(fodeDato);  //formaterer Datobjektet for mer passende visning i ny String.
        }
        catch(ParseException pe)
        {
        }
        personID = id;  //initialiserer personID
        name = n;       //initialiserer navn
        address = a;    //initialiserer adresse
        postNr = pNr;
        telephoneNum = t;   //initialiserer telefonnummer
        instrument1 = i1;   //initialiserer hovedinstrument
        instrument2 = i2;   //initialiserer biinstrument
        next = null;
    }
    //get-metode som returnerer personens ID
    public String getID()
    {
        return personID;
    }
    //get-metode som returnerer personens navn
    public String getName()
    {
        return name;
    }
    //toString() metode
    public String toString()
    {
        String s = "ID: " + personID + "\nFødselsdato: " + fodselsdato +
                "\nNavn: " + name + "\nAdresse: " + address + 
                "\nPostnummer: " + postNr + "\nTelefonNr: " + telephoneNum;
        return s;
    }
}
//Klasse som symboliserer lærere som en subklasse av person
class Teacher extends Person{
    private String qualifications;  //Lærerens kvalifikasjoner
    private String[] tableData = new String[7]; //array som representerer en rad for tabellvisning
    private String ansvar;

    //Konstruktør
    Teacher(int fd, String p, String n, String a, String pNr, long t, String q, String i1, String i2)
    {
      super(p, fd, n, a, pNr, t, i1, i2);
      qualifications = q;
      ansvar = null;
      tableData[0] = p;   //lærerId
      tableData[1] = n;   //navn
      tableData[2] = a;   //adresse
      tableData[3] = pNr;
      tableData[4] = ""+t;  //telefonNr
      tableData[5] = instrument1;   //hovedinstrument
      tableData[6] = null;   //kursansvar
    }
    //metode som setter læreren som ansvarlig for kurs
    public void setKursAnsvarlig(Kurs k)
    {
        ansvar = k.getKursId();
        tableData[6] = ansvar;
    }
    //redefinert metode for å sette lærer som ansvarlig for kurs
    public void setKursAnsvarlig(String k)
    {
        ansvar = k;
        if(ansvar.equals(""))
        {
            ansvar = null;
            tableData[6] = null;
        }
    }
    //get-metode som returnerer kurs
    public String getAnsvarlig()
    {
        return ansvar;
    }
    //get-metode som returnerer et array som representerer en rad, for visning i tabell
    public String[] getLaererData()
    {
       return tableData;
    }
    //redefinert toString-metode
    public String toString()
    {
        String s = "Lærer:\n" + super.toString() + 
                "\nKvalifikasjoner: " + qualifications + "\nHovedInstrument: " +
                instrument1 + "\nSideInstrument: " + instrument2;
                
        if(ansvar != null)
            s+="\nAnsvarlig for: " + ansvar;
        return s;
    }
    
           
}
//sub-klasse Pupil
class Pupil extends Person
{
    private String[] tableData = new String[7]; //array representerer en rad for visning i tabell
    private String kurs; //Kurs som eleven er meldt på
    private String gruppe; //elevens kursgruppe, er null om eleven ikke har en gruppe
   
    //Konstruktør registrerer en ny elev ved skolen
    public Pupil(int fd, String p, String n, String a, String pNr, long t, String i1, String i2)
    {
        super(p, fd, n, a, pNr, t, i1, i2);
        tableData[0] = p;   //studentcode
        tableData[1] = n;   //navn
        tableData[2] = a;   //adresse
        tableData[3] = pNr;
        tableData[4] = ""+t;  //telefonNr
        tableData[5] = instrument1;   //hovedinstrument
        tableData[6] = null;  //kurs
        gruppe = null;
    }
    //Metode som tildeler eleven en gruppe
    public void setGruppe(String g)
    {
        gruppe = g;
        if(gruppe == (""))
            gruppe = null;
            
        
    }
    //Metode som returnerer variabelen gruppe
    public String getGruppe()
    {
        return gruppe;
    }
    public String getKurs()
    {
        return kurs;
    }
    //Melder eleven på et kurs        
    public void setKurs(Kurs k)
    {
        kurs = k.getKursId();
        tableData[6] = k.getKursId();
    }
    //redefinert metode for å sette kurs
    public void setKurs(String k)
    {
        kurs = k;
        if(kurs.equals(""))
        {
            kurs = null;
            tableData[6] = null;
        }
    }
    public String[] getElevData()
    {
       return tableData;
    }
    //Redefinert toString-metode
    public String toString()
    {
        String s = "Elev: \n" + super.toString() + 
                "\nHovedInstrument: " + instrument1 + "\nSideInstrument: " + instrument2;
        if(kurs != null)
            s+="\nKurs: " + kurs;
        if(gruppe != null)
            s+="\nGruppe: " + gruppe;
        return s;
    }
   
}
