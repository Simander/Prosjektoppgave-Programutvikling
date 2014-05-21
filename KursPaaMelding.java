/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for å melde elever på kurs.
 */

import java.awt.event.*;
import javax.swing.*;
public class KursPaaMelding extends JPanel 
{
    //private lytteklasser
    private LyttKPaaMeld lytter;
    private itemLytter itemLytt;
    //lister
    private KursListe kursList;
    private PersonListe persList;
    private Historikk hist;
    //tekstfelt for input av elevID
    private JTextField elevId;
    //comboboxer
    private JComboBox kurs;
    private JComboBox grupper;
    //knapp for kurspåmelding
    private JButton påmeld;
    //tekstområde for tilbakemelding
    private JTextArea info;
    //paneler
    private JPanel top;
    private JPanel bottom;
    //array for data til kurs comboboxen.
    private String[] idKurs;
   //konstruktør
    KursPaaMelding(KursListe k, PersonListe p, Historikk h)
    {
        //initialiserer lister
        hist = h;
        kursList = k;
        persList = p;
        //initialiserer lyttere
        lytter = new LyttKPaaMeld();
        itemLytt = new itemLytter();
        //initialiserer tekstfelt
        elevId = new JTextField(15);
        //initialiserer array med kursdata
        idKurs = kursList.kursStrings();
        //initialiserer comboboxer
        kurs = new JComboBox(idKurs);
        grupper = new JComboBox();
        //initialiserer knapp
        påmeld = new JButton("Påmelding!");
        //initialiserer tekstområdet
        info = new JTextArea( 20, 40);
        info.setEditable(false);
        //initialiserer paneler
        top = new JPanel();
        bottom = new JPanel();
        //legger til komponenter
        top.add(new JLabel("Elev Id:"));
        top.add(elevId);
        top.add(new JLabel("Kurs:"));
        top.add(kurs);
        top.add(new JLabel("Gruppe:"));
        top.add(grupper);
        kurs.addItemListener(itemLytt);
        top.add(påmeld);
        bottom.add(new JScrollPane(info));
        add(top);
        add(bottom);
        //setter lytter til knapp
        påmeld.addActionListener(lytter);
    }
    //metode for å melde en elev på et kurs
    private void meldPå()
    {
        String eId = elevId.getText();  //Henter elevId fra tekstfelt
        if(persList.finnElever(eId)!=null)
        {    Pupil eleven = persList.finnElever(eId);  //finner elev med riktig id
            String kId = (String)kurs.getSelectedItem();    //sjekker valgt kurs
            String groupy = (String)grupper.getSelectedItem();
            Kurs temp = kursList.finnKurs(kId);     //Finner kurset
            if(persList.gruppeSjekk(groupy) < 3 ) //sjekker, max elever i gruppe lik 3
            {
                eleven.setKurs(temp);
                //sender det inn i elev-objektets setKurs() metode
                eleven.setGruppe(groupy);
                String s = "Eleven, med ID: " + eId + ", er nå meldt på kurs: " + kId;
                info.setText(s);
                String i = eId;
                String o = "KursID: " + kId;
                String a = "Påmeldt kurs";
            
                hist.entry(o, a, i);
            
            }
            else
                info.setText("Gruppen er full, prøv en annen gruppe!");
        }
        else
            info.setText("Ugyldig elevID!");
    }
    //metode for å oppdatere innholdet i kurs comboboxen
    protected void updateComboBox()
    {
        kurs.removeAllItems();
        String[] kursulus = kursList.kursStrings();
        for(int i = 0; i < kursulus.length; i++)
            kurs.addItem(kursulus[i]);      
    }
    //metode som setter verdier til gruppecomboboxen og oppdaterer den
    private void updateCombo2()
    {
       grupper.removeAllItems();
       if(kurs.getItemCount() != 0)
       {
            String temp = (String)kurs.getSelectedItem();
            Kurs kursus = kursList.finnKurs(temp);
            String[] gruppene = kursus.getGruppe();
            for(int i = 0; i < gruppene.length; i++)
            {
                grupper.addItem(gruppene[i]);
            }
       }
    }
    //privat lytteklasse for comboboxer
    private class itemLytter implements ItemListener
    {
        public void itemStateChanged(ItemEvent e) 
        {
            if (e.getStateChange() == ItemEvent.SELECTED) 
            {
                updateCombo2();
            }
        }
    }
    //privat lytteklasse for panelet
    private class LyttKPaaMeld implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == påmeld)
                 meldPå();
                // JOptionPane.showMessageDialog(null, (String)kurs.getSelectedItem());
         //    else if (e.getSource() == kurs)
             
           // updateCombo2();
                 

        }
    }
}
