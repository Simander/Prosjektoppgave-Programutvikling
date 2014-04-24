import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.*;

/*
@author anders
 */
public class GUI2 extends JPanel implements Serializable
{
    private JPanel panelReg;
    private JPanel top;
    private JPanel bottom;
    //Registreringsdel
    private Knappelytter lytter; //Privat lytteklasse;
    private JTextField name;    //Tekstfelt for input av navn
    private JTextField personNo;    //Tekstfelt for input av personNo eller studentkode
    private JCheckBox typeCheck;    //Checkbox som sjekker om den nye personen er lærer eller elev
    private JTextField teleNo;  //Tekstfelt for input av telefonnummer
    private JTextField address; //Tekstfelt for input av adresse
   
    //Diverse sjekkbokser for å velge instrument
    private JCheckBox guitar;
    private JCheckBox bass;
    private JCheckBox drums;
    private JCheckBox piano;
    private JCheckBox keyboard;
    private JCheckBox violin;
    private JCheckBox flute;
    
    private JButton alfredo;
    private JButton slett;
    private JButton register; //Knapp for å registrere ny person
    private JButton list;   //Knapp for å liste opp alle personer.
    private JButton laerer;
  //  private PersonRegister personer;
    private PersonListe personer;
    private JTextArea info;
    GUI2(/*PersonRegister*/ PersonListe p)
    {
       // super("Registrering av personer"); //Header
        
        personer = p;
        personNo = new JTextField(30);
        typeCheck = new JCheckBox("Lærer");
        name = new JTextField(35);
        address = new JTextField(35);
        teleNo = new JTextField(35);
        guitar = new JCheckBox("Gitar");
        bass = new JCheckBox("Bass");
        piano = new JCheckBox("Piano");
        keyboard = new JCheckBox("Keyboard");
        drums = new JCheckBox("Trommer");
        violin = new JCheckBox("Fiolin");
        flute = new JCheckBox("Fløyte");
       
       // Container c = getContentPane();
        //c.setLayout(new FlowLayout() );
        
        register = new JButton("Registrer Person");
        list = new JButton("Finn person");
        laerer = new JButton("Lærere");
        info = new JTextArea(18, 32);
        info.setVisible(true);
        lytter = new Knappelytter();
        top = new JPanel();
        bottom = new JPanel();
        panelReg = new JPanel();
        panelReg.setVisible(true);
        panelReg.setLayout(new FlowLayout() );
        //Legger til skjermobjekter i vinduet
        top.add(new JLabel("PersonNr: "));
        top.add(personNo);
        top.add(typeCheck);
        top.add(new JLabel("Navn: "));
        top.add(name);
        top.add(new JLabel("Adresse: "));
        top.add(address);
        top.add(new JLabel("TelefonNr: "));
        top.add(teleNo);
        bottom.add(guitar);
        bottom.add(bass);
        bottom.add(piano);
        bottom.add(keyboard);
        bottom.add(drums);
        bottom.add(violin);
        bottom.add(flute);
        bottom.add(register);
        bottom.add(list);
        bottom.add(new JScrollPane(info));
        panelReg.add(top);
        panelReg.add(bottom);
        add(panelReg);
        alfredo = new JButton("elever");
        add(alfredo);
        add(laerer);
       laerer.addActionListener(lytter);
        slett = new JButton("slett");
        add(slett);
        slett.addActionListener(lytter);
        alfredo.addActionListener(lytter);
       panelReg.setPreferredSize(new Dimension(640
               , 480));
       top.setPreferredSize(new Dimension(510
               , 100));
       bottom.setPreferredSize(new Dimension(640, 380));
        
       // setLocation(0,0);
        //setPreferredSize(null);
        //Knytter knapper til lytteobjekter
        register.addActionListener(lytter);
        list.addActionListener(lytter);
        
        
    }
  /*  public void tableElev()
    {
        String[] kolonneNavn = {"ElevId", "Navn", "Adresse", "Tlf", "Instrument"};
        String[][] content = personer.tablerus();
        JTable tabell = new JTable(content, kolonneNavn);
        panelReg.add(new JScrollPane(tabell));
        tabell.setVisible(true);
    }*/
    public void newPerson() //Metode for å registrere en ny person, skulle det være lærer eller elev;
    {
       
        //Henter data fra tekstfelterfelt, og konverterer til riktig format
        
        String scode = personNo.getText();
        personNo.setText(null);
        String navn = name.getText();
        name.setText(null);
        String add = address.getText();
        address.setText(null);
        long tNr = Long.parseLong(teleNo.getText());
        teleNo.setText(null);
        String inst = "";

        //Oppretter temporære objekter av Person-klassens sub-klasser
       
        
        //Sjekker om det er krysset av for lærer eller ei og setter inn i registeret
        if (typeCheck.isSelected() == false) //Hvis Elev
        {
                    //Bolk som sjekker valgt instrument og setter inst til dette instrument.
                    if(guitar.isSelected() == true)
                    {
                        inst = "Gitar.";
                        guitar.setSelected(false);
                    }
                    else if(bass.isSelected() == true )
                    {
                        inst = "Bass.";
                        bass.setSelected(false);
                    }
                    else if(piano.isSelected() == true)
                    {
                        inst = "Piano.";
                        piano.setSelected(false);
                    }
                    else if(keyboard.isSelected() == true)
                    {
                        inst = "Keyboard.";
                        keyboard.setSelected(false);
                    }
                    else if(drums.isSelected() == true)
                    {
                        inst = "Trommer.";
                        drums.setSelected(false);
                    }
                    else if(violin.isSelected() == true)
                    {
                         inst = "Fiolin.";
                         violin.setSelected(false);
                    }
                    else if(flute.isSelected() == true)
                    {
                        inst = "Fløyte.";
                        flute.setSelected(false);
                    }
            if(!inst.equals("")) //Hvis instrument er blitt valgt
            {    Pupil user = new Pupil(scode, navn, add, tNr, inst); //Oppretter et Elev-objekt
                 //personer.insertPerson(user);   //Setter inn i Listen over Personer
                 personer.settInnPerson(user);
                 info.setText("Ny elev er registrert!"); //printer tilbakemelding i info-feltet
            }
            else
                info.setText("Eleven må ha valgt et instrument.");
        }
        else if(typeCheck.isSelected() == true )    //Hvis Lærer
        {
            long pNr = Long.parseLong(scode);
            String qualification = JOptionPane.showInputDialog(null, "Skriv inn lærerens kvalifikasjoner.");
            Teacher user = new Teacher(pNr, navn, add, tNr, qualification);
            if(guitar.isSelected() == true)
                        user.setInstrument("Gitar,");
                        guitar.setSelected(false);
                if(piano.isSelected() == true)
                        user.setInstrument("Piano,");
                        piano.setSelected(false);
                if(keyboard.isSelected() == true)
                        user.setInstrument("Keyboard,");
                        keyboard.setSelected(false);
                if(drums.isSelected() == true)
                        user.setInstrument("Trommer,");
                        drums.setSelected(false);
                if(violin.isSelected() == true)
                         user.setInstrument("Fiolin,");
                         violin.setSelected(false);
                if(flute.isSelected() == true)
                         user.setInstrument("Fløyte,");
                         flute.setSelected(false);        
                    typeCheck.setSelected(false);
               
                    if(!user.getInstruments().equals(""))
                    {
                       // personer.insertPerson(user);
                        personer.settInnPerson(user);
                        info.setText("Ny lærer er registrert!");
                    }
                    else
                        info.setText("Læreren må spille minst ett instrument!");
            
            
        }   
    }

            //metode for å legge til ny eier
	
           
        
    	public void listInfo()  //Metode som lister opp alle personer i registeret
        {
      
            String print = personer.listEntries();
            info.setText(print);
        }
        public void slettP()
        {
            personer.fjernPerson(name.getText());
           // info.setText("personen er fjernet!");
        }
        public void listE()
        {
            String print = personer.listElever();
            info.setText(print);
        }
        public void larer(){String s = personer.listLaerere(); info.setText(s);}
    private class Knappelytter implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == register)
               newPerson();
            else if(e.getSource() == list)
               //tableElev();
           listInfo();
               // JOptionPane.showMessageDialog(null, "knappen funkar!");
            else if(e.getSource() == alfredo)
               listE();
            else if(e.getSource() == slett)
                slettP();
            else if(e.getSource() == laerer)
                larer();
        }
    }
}
