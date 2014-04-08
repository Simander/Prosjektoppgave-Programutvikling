
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.*;

/*
@author anders
 */
public class GUI2 extends JInternalFrame implements Serializable
{
    private JPanel panelReg;
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
    
    private JButton register; //Knapp for å registrere ny person
    private JButton list;   //Knapp for å liste opp alle personer.
    private PersonRegister personer;
    private JTextArea info;
    GUI2(PersonRegister p)
    {
        super("Registrering av personer"); //Header
        
        personer = p;
        personNo = new JTextField(30);
        typeCheck = new JCheckBox("Lærer");
        name = new JTextField(35);
        address = new JTextField(35);
        teleNo = new JTextField(35);
        guitar = new JCheckBox("gitar.");
        bass = new JCheckBox("Bass.");
        piano = new JCheckBox("piano.");
        keyboard = new JCheckBox("keyboard.");
        drums = new JCheckBox("trommer.");
        violin = new JCheckBox("fiolin.");
        flute = new JCheckBox("fløyte.");
       
        Container c = getContentPane();
        c.setLayout(new FlowLayout() );
        
        register = new JButton("Registrer Person");
        list = new JButton("Finn person");
        info = new JTextArea(18, 32);
        info.setVisible(true);
        lytter = new Knappelytter();
        panelReg = new JPanel();
        panelReg.setVisible(true);
        panelReg.setLayout(new FlowLayout() );
        //Legger til skjermobjekter i vinduet
        panelReg.add(new JLabel("PersonNr: "));
        panelReg.add(personNo);
        panelReg.add(typeCheck);
        panelReg.add(new JLabel("Navn: "));
        panelReg.add(name);
        panelReg.add(new JLabel("Adresse: "));
        panelReg.add(address);
        panelReg.add(new JLabel("TelefonNr: "));
        panelReg.add(teleNo);
        panelReg.add(guitar);
        panelReg.add(bass);
        panelReg.add(piano);
        panelReg.add(keyboard);
        panelReg.add(drums);
        panelReg.add(violin);
        panelReg.add(flute);
        panelReg.add(register);
        panelReg.add(list);
        panelReg.add(new JScrollPane(info));
        c.add(panelReg);
       
        panelReg.setPreferredSize(new Dimension(480, 550));
        c.setPreferredSize(null);
        //Knytter knapper til lytteobjekter
        register.addActionListener(lytter);
        list.addActionListener(lytter);
        
        
    }
    
    public void newPerson() //Metode for å registrere en ny person, skulle det være lærer eller elev;
    {
       
        //Henter data fra tekstfelterfelt, og konverterer til riktig format
        long pNr = Long.parseLong(personNo.getText());
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
                 personer.insertPerson(user);   //Setter inn i Listen over Personer
                 info.setText("Ny elev er registrert!"); //printer tilbakemelding i info-feltet
            }
            else
                info.setText("Eleven må ha valgt et instrument.");
        }
        else if(typeCheck.isSelected() == true )    //Hvis Lærer
        {
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
                        personer.insertPerson(user);
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
