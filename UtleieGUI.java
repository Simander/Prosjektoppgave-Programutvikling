
/**
 *
 * @author joakim
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Denne klassen utgjør vindusklassen for utlån av instrumenter.
 * 
 */
public class UtleieGUI extends JPanel
{
    private Instrument instrument;
    private InstrumentRegister iReg;
    private JTextField iID;
    private JTextField pID;
    private JButton utlan;
    private JButton vinfo;
    private JButton innlevering;
    private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttUG lyttUG;
    private PersonListe PLIST;
    private InstrumentRegister IR;
    private UtleieListe UL;
    Historikk hist;
    
    
    UtleieGUI(InstrumentRegister ur, PersonListe p, UtleieListe ul, Historikk h) //Konstruktør for utleieGUI
    {
        hist = h;
        //Listeinport
        UL = ul;
        IR = ur;
        PLIST = p;
        //Tekstfelter
        iID = new JTextField (35);
        pID = new JTextField (35);
        //Knapper
        vinfo = new JButton ("Vis info");
        utlan = new JButton ("Utlån");
        innlevering = new JButton ("Innlevering");
        //Informasjonsvindu
        info = new JTextArea (20,32);
        //Paneler for oppdeling av skjermbilde
        top = new JPanel();
        bottom = new JPanel();
        //Panelstørrelse
        top.setPreferredSize(new Dimension (400,100));
        bottom.setPreferredSize(new Dimension(480,500));
        //Legger til elementer i panel
        top.add(new JLabel("Instrument NR"));
        top.add(iID);
        top.add(new JLabel("Elev ID"));
        top.add(pID);
        bottom.add(top);
        bottom.add(vinfo);
        bottom.add(utlan);
        bottom.add(innlevering);
        bottom.add(new JScrollPane(info));
        info.setEditable(false);
        add(bottom);
        //Opretter lytteklassen
        lyttUG = new LyttUG();
        //Setter ActionListener til knapper
        vinfo.addActionListener(lyttUG);
        utlan.addActionListener(lyttUG);
        innlevering.addActionListener(lyttUG);
    }
   
    public void sjekkLedig() //Metode for å sjekke om gitt instrument er ledig eller ikke.
    {
        String print = "";
        Instrument i = IR.findInstrument(iID.getText());
        
        if (iID.getText().equalsIgnoreCase("")) //Tester om tekstfelt for instrument er tomt
        {
            print = "Vennligst Skriv inn InstrumentID";
        }
        else if (i == null) //Melding om instrument ikke eksisterer
        {
            print = "Instrumentet finnes ikke.";
        }
        
        else //Melding om
        {
            print = "Musikkinstrumentet ønsket lånt er IKKE tilgjenglig";
            if(i.getUtleid() == false)
            {
                print = "Musikkinstrumentet ønsket lånt er tilgjenglig";
            }
        }         
        info.setText(print);
    }   
    public void Laanut() //Metode for å låne ut et instrument
    {
        String print = "";
        Instrument i = IR.findInstrument(iID.getText()); //Finner instrumentet som har lik instrumentID som skrevet inn 
        Person p = PLIST.finnElever(pID.getText()); //Finner personen (eleven) som har lik instrumentID som skrevet inn
        
        if(iID.getText().equalsIgnoreCase("") || pID.getText().equalsIgnoreCase("")) //Tester om noen av feltene er tomme
        {
            print = "Vennligst fyll inn alle felter";
        }
        else if (i == null) //Skriver ut melding hvis instrumentet ikke finnes i instrumentregisteret
        {
            print = "Instrumentet med ID : " + iID.getText() + "finnes ikke.";
        }
        else if (p == null) //Skriver ut melding hvis personen (eleven) ikke finnes i personlisten
        {
            print = "Ugyldig elev ID";
        }
        else if (i.getUtleid() == true) //Skriver ut melding hvis valgt instrument ikke er tilgjenglig for utlån
        {
          print = "Instrumentet med ID : " + iID.getText() + " er ikke tilgjengelig \n"
                  + "Utlån ikke mulig.";  
        }
        else if (!iID.getText().equalsIgnoreCase("") && !pID.getText().equalsIgnoreCase("") //Setter inn leietaker og setter instrumentet som utleid hvis instrument og person feltene ikke er tomme og begge finnes i hver sin liste
                && p != null && i!= null)
        {
            
            InstrumentUtleie temp = new InstrumentUtleie(i,(Pupil)p);
            UL.settInnUtleie(temp);
            i.setLeietaker((Pupil)p);
            String o = "Instrument";
            String a = "Utlånt";
            hist.entry(o, a, p.getID());
            print = i.getInstrumentId() + "er nå lånt ut." + "\n " + i.getRegistrert(); //Skriver ut melding om at utlånet er gjort, med gitt instrumentID   
        }
        info.setText(print);//Viser print i tekstboksen.
    }
    public void Innlevering() //Metode for innlevering av Instrument
    {
        String print = "";
        Instrument i = IR.findInstrument(iID.getText());
        Person p = PLIST.finnPerson(pID.getText());
        if(iID.getText().equalsIgnoreCase(""))
        {
            print = "Vennligst fyll inn info";
        }
        else if (i == null)
        {
            print = "Instrumentet med ID : " + iID.getText() + "finnes ikke.";
        }
        else if (i.getUtleid() == false)
        {
          print = "Instrumentet med ID : " + iID.getText() + " er ikke utlånt \n"
                  + "Innlevering ikke mulig.";  
        }
        else if (i.getUtleid() == true)
        {
            UL.leggTilHistorikk(iID.getText());
            i.removeLeietaker();
            print ="Innlevert: "+ i.getInnlevert() + "\n" + i.toString();
        }
        info.setText(print);
    }
    public void visInfo()
    {
        String print = "";
        Instrument i = IR.findInstrument(iID.getText());
        if(iID.getText().equalsIgnoreCase(""))
        {
            print = "Vennligst fyll Instrument ID";
        }
        else if (i == null)
        {
            print = "Instrumentet med ID : " + iID.getText() + "finnes ikke.";
        }
        else
        {
            if (i.getInstrumentId().equalsIgnoreCase (iID.getText()))
            {
                print = i.toString();
            }
            else if (!i.getInstrumentId().equalsIgnoreCase (iID.getText()))
            {
                print = "Instrument med dette registreringsnummer finnes ikke";
            }
        }
        info.setText(print);
    }

    private class LyttUG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == vinfo)
            {
                visInfo();
            }
            else if (e.getSource() == utlan)
            {
                Laanut();
            }
            else if (e.getSource() == innlevering)
            {
                Innlevering();
            }
        }
    }
}
