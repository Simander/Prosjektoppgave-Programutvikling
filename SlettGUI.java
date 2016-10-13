/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et grafisk brukergrensesnitt i form av et JPanel
 * Klassens tar i mot programmets ulike lister, og sletter objekter fra listene
 * ved hjelp av bruker input.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SlettGUI extends JPanel
{
    //lister
    private PersonListe perList;
    private RomListe romList;
    private KursListe kursList;
    private InstrumentRegister insListe;
    private Historikk hist;
    //tekstfelter
    private JTextField personKode;
    private JTextField kursKode;
    private JTextField romKode;
    private JTextField instrumentKode;
    //tekstområdet
    private JTextArea info;
    //paneler
    private JPanel[] slettPanel = new JPanel[4];
    //knappe-array
    private JButton[] slettButtons;
    //kantlinje
    Border blackline;
    //paneler
    private JPanel spacer;
    private JPanel innhald;
    //lytteklasse
    private LyttSlett slettLytt;
    //array med navn til knapper
    private String[] names = {"Slett!", "Slett!", "Slett!", "Slett!"};
    //array med labels til tekstfelter
    private JLabel[] labels = {new JLabel("PersonId:"), new JLabel("KursKode:"), new JLabel("RomKode:"), new JLabel("InstrumentKode:")};
    
    //konstruktør
    SlettGUI(PersonListe p, RomListe r, BookingListe b, KursListe k, InstrumentRegister in, Historikk h)
    {
        //initialiserer lister
        hist = h;
        perList = p;
        romList = r;
        kursList = k;
        insListe = in;
        //initialiserer lytteklasse
        slettLytt = new LyttSlett();
        //initialiserer knapper
        slettButtons = new JButton[names.length];
        for (int count = 0; count < names.length; count++)
        {
            slettButtons[ count ] = new JButton(names[count]);
            slettButtons[count].addActionListener(slettLytt);
            slettButtons[count].setBackground(Color.RED);
            slettButtons[count].setForeground(Color.white);
            labels[count].setPreferredSize(new Dimension(125, 30));
            slettButtons[count].setPreferredSize(new Dimension(80, 25));
         }
        //initialiserer tekstområdet
        info = new JTextArea(10,30);
        info.setEditable(false);
        //initialiserer paneler
        innhald = new JPanel();
        spacer = new JPanel();
        //legger til komponenter
        add(spacer);
        add(new JLabel("Sletting:"));
        add(innhald);
        //setter kantlinje
        blackline = BorderFactory.createLineBorder(Color.black);
        innhald.setBorder(blackline);
        //initialiserer paneler
        for(int i = 0; i < slettPanel.length; i++)
        {
            slettPanel[i] = new JPanel();
            //slettPanel[i].setLayout(new BorderLayout(5,5));
           // slettPanel[i].setBorder(blackline);
            slettPanel[i].setPreferredSize(new Dimension(350, 40));
            innhald.add(slettPanel[i]);
            
        }
        //setter dimensjoner
        spacer.setPreferredSize(new Dimension(400, 40));
        innhald.setPreferredSize(new Dimension(400,350));
        setPreferredSize(new Dimension(400,500));
        //initialiserer tekstfelter
        personKode = new JTextField(10);
        kursKode = new JTextField(10);
        romKode = new JTextField(10);
        instrumentKode = new JTextField(10);
        //legger til komponenter
        slettPanel[0].add(labels[0]);
        slettPanel[0].add(personKode);
        slettPanel[0].add(slettButtons[0]);
        slettPanel[1].add(labels[1]);
        slettPanel[1].add(kursKode);
        slettPanel[1].add(slettButtons[1]);
        slettPanel[2].add(labels[2]);
        slettPanel[2].add(romKode);
        slettPanel[2].add(slettButtons[2]);
        slettPanel[3].add(labels[3]);
        slettPanel[3].add(instrumentKode);
        slettPanel[3].add(slettButtons[3]);
        innhald.add(new JScrollPane(info));
        }
    //Metode for å slette en person, kaller på personlistens fjernPerson(String) metode
    public void slettPerson()
    {
        if(!personKode.getText().equals(""))
        {   
            String s = "Personen: '" + perList.fjernPerson(personKode.getText())
                +"' er fjernet fra registeret!";
            info.setText(s);
            
            String i = personKode.getText();
            String o = "Person";
            String a = "Slettet";
            
            hist.entry(o, a, i);
            personKode.setText("");
        }
    }
    //Metode for å slette et kurs, kaller på kurslistens removeKurs(String) metode
     public void slettKurs()
    {
        if(!kursKode.getText().equals(""))
        {   
            if(perList.finnAnsvarlig(kursKode.getText())!= null);
            {
                Teacher temp = perList.finnAnsvarlig(kursKode.getText());
                temp.setKursAnsvarlig("");
            }
            
            perList.fjernkursFraElev(kursKode.getText());
            String s = "Kurset: '" + kursList.removeKurs(kursKode.getText())
                +"' er fjernet fra registeret!";
            info.setText(s);
            
            String i = kursKode.getText();
            String o = "Kurs";
            String a = "Slettet";
            
            hist.entry(o, a, i);
            kursKode.setText("");
        }
    }
     //Metode for å slette et rom, kaller på romlistens removeRom(String) metode
    public void slettRom()
    {
        if(!romKode.getText().equals(""))
        {
            String s = "Rommet: '" + romList.removeRom(romKode.getText())
                +"' er fjernet fra registeret!";
            info.setText(s);
           
            String i = romKode.getText();
            String o = "Rom";
            String a = "Slettet";
            
            hist.entry(o, a, i);
             romKode.setText("");
        }
    }
    //metode for å slette et instrument
    public void slettInstrument()
    {
        if(!instrumentKode.getText().equals(""))
        {
           String s = "Instrument: '" + insListe.delIns(instrumentKode.getText())
                +"' er fjernet fra registeret!";
            info.setText(s);
            
            String i = instrumentKode.getText();
            String o = "Instrument";
            String a = "Slettet";
            instrumentKode.setText("");
            hist.entry(o, a, i);
        }
    }
    //privat lytteklasse
    private class LyttSlett implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == slettButtons[0])
                 slettPerson();
             else if (e.getSource() == slettButtons[1])
                 slettKurs();
             else if (e.getSource() == slettButtons[2])
                 slettRom();
             else if (e.getSource() == slettButtons[3])
                 slettInstrument();
        }
    }
}

