/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for personregistrering.
 */
//import-setninger
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PersonRegVindu extends JPanel implements Serializable
{
    //lister
    private PersonListe personer;
    private  Historikk hist;
    //privat lytteklasse
    private Knappelytter lytter;
    //paneler
    private JPanel panelReg;
    private JPanel top;
    private JPanel bottom;
    private JPanel left;
    private JPanel right;
    //tekstfelter
    private JTextField name;    //Tekstfelt for input av navn
    private JTextField fodeDato;    //Tekstfelt for input av personNo eller studentkode
    private JTextField teleNo;  //Tekstfelt for input av telefonnummer
    private JTextField address; //Tekstfelt for input av adresse
    private JTextField postnummer;
    //checkbox
    private JCheckBox typeCheck;    //Checkbox som sjekker om den nye personen er lærer eller elev
    //knapper
    private JButton register; //Knapp for å registrere ny person
    private JButton list;   //Knapp for å liste opp alle personer.
    //Array for verdier til comboboxer
    private JLabel[] labels = {new JLabel("Fødselsdato: "),new JLabel("Navn: "),
        new JLabel("Adresse: "), new JLabel("Postnummer:"), new JLabel("TelefonNr: "), new JLabel("Hovedinstrument:"), new JLabel("Sideinstrument:")}; 
    protected static String[] instrumentTypeMain = {"Gitar", "Bass", "Piano", "Keyboard", "Trommer", "Fiolin", "Fløyte"};
    protected String[] instrumentTypeSide =  {"Ingen", "Gitar", "Bass", "Piano", "Keyboard", "Trommer", "Fiolin", "Fløyte"};
    //comboboxer
    private JComboBox hovedInstrument;
    private JComboBox sideInstrument;
    //tekstområde
    private JTextArea info; //JTextArea for print av data
    //konstruktør
    PersonRegVindu(PersonListe p, Historikk h)
    { 
        //initialiserer lister
        hist = h;
        personer = p;
        //initialiserer komponenter
        fodeDato = new JTextField(13);
        typeCheck = new JCheckBox("Lærer");
        name = new JTextField(20);
        address = new JTextField(20);
        postnummer = new JTextField(20);
        teleNo = new JTextField(20);
        hovedInstrument = new JComboBox(instrumentTypeMain);
        sideInstrument = new JComboBox(instrumentTypeSide);
        register = new JButton("Registrer Person");
        list = new JButton("Hent data");
        info = new JTextArea(15, 40);
        info.setVisible(true);
        info.setEditable(false);
        lytter = new Knappelytter();
        top = new JPanel();
        bottom = new JPanel();
        left = new JPanel();
        right = new JPanel();
        panelReg = new JPanel();
        panelReg.setVisible(true);
        //legger til komponenter
        for(int i = 0; i < labels.length; i++)
        {
            left.add(labels[i]);
            labels[i].setPreferredSize(new Dimension(150, 20));
        }
        //setter layout
        panelReg.setLayout(new FlowLayout() );
        top.setLayout(new BorderLayout());
        top.add(left, BorderLayout.LINE_START);
        right.add(fodeDato);
        right.add(typeCheck);
        right.add(name);
        right.add(address);
        right.add(postnummer);
        right.add(teleNo);
        top.add(right, BorderLayout.LINE_END);
        right.add(hovedInstrument);
        right.add(sideInstrument);
        bottom.add(register);
        bottom.add(list);
        bottom.add(new JScrollPane(info));
        panelReg.add(top);
        panelReg.add(bottom);
        add(panelReg);
        //setter dimensjoner
        hovedInstrument.setPreferredSize(new Dimension(220, 22));
        sideInstrument.setPreferredSize(new Dimension(220, 22));
        left.setPreferredSize(new Dimension(150, 100));
        right.setPreferredSize(new Dimension(250, 200));
        panelReg.setPreferredSize(new Dimension(640, 480));
        top.setPreferredSize(new Dimension(400, 180));
        bottom.setPreferredSize(new Dimension(640, 380));
        //Knytter knapper til lytteobjekter
        register.addActionListener(lytter);
        list.addActionListener(lytter);
        
        
    }
    //Metode for å registrere en ny person, skulle det være lærer eller elev
    private void newPerson() 
    {
       try{
        if(fodeDato.getText().equals("") || name.getText().equals("") ||
                address.getText().equals("") || teleNo.getText().equals(""))
            info.setText("Vennligst fyll ut alle felter!");
        else{
        //Henter data fra tekstfelterfelt, og konverterer til riktig format
        
        String scode = fodeDato.getText();
        int fdate = Integer.parseInt(scode);
       
        String navn = name.getText();
        
        String add = address.getText();
     
        String pNr = postnummer.getText();
        long tNr = Long.parseLong(teleNo.getText());
        
        String tel = "" + tNr;
        String inst1 = "";
        String inst2 = "";

        int var1 = (int)(Math.random()*10);
        int var2 = (int)(Math.random()*10);
        int var3 = (int)(Math.random()*10);
        int var4 = (int)(Math.random()*10);
        //Oppretter temporære objekter av Person-klassens sub-klasser
       
        inst1 = (String)hovedInstrument.getSelectedItem();
        inst2 = (String)sideInstrument.getSelectedItem();
        //Sjekker om det er krysset av for lærer eller ei og setter inn i registeret
        if (typeCheck.isSelected() == false) //Hvis Elev
        {
                    //Bolk som sjekker valgt instrument og setter inst til dette instrument.
              
            if(!inst1.equals("") && !inst2.equals("")) //Hvis instrument er blitt valgt
            {   
                String id = "E" + var1 + var2 + var3 + var4 ;
                while(personer.finnElever(id) != null) //Sjekk som sikrer unike ID'er
                {
                    var1 = (int)(Math.random()*10);
                    var2 = (int)(Math.random()*10);
                    var3 = (int)(Math.random()*10);
                    var4 = (int)(Math.random()*10);
                    id = "E" + var1 + var2 + var3 + var4;
                }    
                Pupil user = new Pupil(fdate, id, navn, add,pNr, tNr, inst1, inst2); //Oppretter et Elev-objekt
                String i = id;
                String o = "Person";
                String a = "Registrert";

                 if(personer.finnPerson(navn) == null)
                 {   
                    personer.settInnPerson(user);
                    info.setText("Ny elev er registrert!"); //printer tilbakemelding i info-feltet
                    hist.entry(o, a, i);
                    fodeDato.setText("");
                    name.setText("");
                    address.setText("");
                    teleNo.setText("");
                    postnummer.setText("");
                 }
                 else
                     info.setText("Eleven finnes allerede i registeret!");
            }
            else
                info.setText("Eleven må ha valgt et instrument.");
        }
        else if(typeCheck.isSelected() == true )    //Hvis Lærer
        {
            String qualification = JOptionPane.showInputDialog(null, "Skriv inn lærerens kvalifikasjoner.", "Kvalifikasjoner.", JOptionPane.QUESTION_MESSAGE);
            String id = "L" + var1 + var2 + var3 + var4;
            while(personer.finnLaerer(id) != null) //Sjekk som sikrer unike ID'er
            {
                var1 = (int)(Math.random()*10);
                var2 = (int)(Math.random()*10);
                var3 = (int)(Math.random()*10);
                var4 = (int)(Math.random()*10);
                id = "L" + var1 + var2 + var3 +var4;
            }
            Teacher user = new Teacher(fdate, id, navn, add, pNr, tNr, qualification, inst1, inst2);
            String i = id;
            String o = "Person";
            String a = "Registrert";
            
                  
                    typeCheck.setSelected(false);
             if(personer.finnPerson(navn) == null)
             {   
                personer.settInnPerson(user);
                info.setText("Ny lærer er registrert!");
                hist.entry(o, a, i);
                fodeDato.setText("");
                name.setText("");
                address.setText("");
                teleNo.setText("");
                postnummer.setText("");
                
             }
             else
                info.setText("Lærereren finnes allerede i registeret!"); 
              
            } 
       }
       }
       catch(NumberFormatException nfe)
       {
           info.setText("Det er fylt inn en ugyldig verdi!");
       }
    }
   //Metode som lister opp alle personer i registeret 
   private void listInfo()
   {
        String print = personer.listEntries();
        info.setText(print);
   }
   //privat lytteklasse
   private class Knappelytter implements ActionListener
   {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == register)
               newPerson();
            else if(e.getSource() == list)
                listInfo();

        }
    }
}
