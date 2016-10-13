/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for booking av rom til kurs eller andre
 * formål. Klassen inneholder også en tabell som blir oppdatert for hver ny booking.
 * Bookinger som går ut på dato slettes også automatisk.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JTable;
import java.io.*;

public class RomBooking extends JPanel implements Serializable 
{
    private static final long serialVersionUID = 1L;
    //lister
    private PersonListe perList;    //personliste
    private RomListe romList;       //romliste
    private KursListe kursList;     //kursliste
    private BookingListe bookList;  //bookingliste
    private Historikk hist; //historikk
    //lyttere
    private LyttRB lyttRB;  //privat lytteklasse av typen ActionListener
    private itemLytter1 lyttItem;   //privat lytteklasse av typen ItemListener
    //tekstfelter
    private JTextField dag;     //tekstfelt for dag, startdato for booking
    private JTextField måned;   //tekstfelt for måned, startdato for booking
    private JTextField time;    //tekstfelt for time, startdato for booking
    private JTextField minutt;  //tekstfelt for minutt, startdato for booking
    private JTextField personId;    //tekstfelt for personID, for å identifisere personen som booker
    private JButton book;    //knapp for booking
    private JCheckBox pleasureOrEd; //checkbox for kurs eller annet    
    //comboboxer
    private JComboBox idRomListe;
    private JComboBox idKursList;
    private JComboBox formal;
    private JComboBox gruppen;
    private JComboBox bVarighet;
    //Array inneholder valg til booking varighet
    private String[] bookingTid = {"30min", "45min", "60min", "90min", "120min"};
    //Array inneholder ulike formål ved booking
    private String[] formalListe = {"Øving", "Studio", "Konsert", "Masterclass"};
    private String[] idRom; //array for verdier til idRomList
    private String[] idKurs;    //array for verdier til idKursList
    //paneler
    private JPanel top;
    private JPanel bottom;
    private JPanel paRom;
    private JPanel paKurs;
    private JPanel kursInput;
    private JPanel formalInput;
    private JPanel midSection;
    private JPanel paVarighet;
    //Tabell
    protected JScrollPane scrollBookTable;
    protected DefaultTableModel modell = new DefaultTableModel(); 
    protected JTable table = new JTable(modell);  

    //Konstruktør
    RomBooking(PersonListe p, RomListe r, BookingListe b, KursListe k, Historikk h)
    {
        //initialisering av relevante lister;
        hist = h;
        perList = p;
        romList = r;
        kursList = k;
        bookList = b;
        //henter verdier til JComboBoxes
        idRom = romList.romStrings();
        idKurs = kursList.kursStrings();            
        //setter Kolonner til booking tabellen
        modell.addColumn("RomId:");
        modell.addColumn("Dato");
        modell.addColumn("Fra:");
        modell.addColumn("Til:");
        modell.addColumn("Kurs/Formål:");
        modell.addColumn("Gruppe:");
        modell.addColumn("Ansvarlig:");
        //initialiserer lytteklasser
        lyttRB = new LyttRB();
        lyttItem = new itemLytter1();
        //initialiserer tekstfelter
        dag = new JTextField(2);
        måned = new JTextField(2);
        time = new JTextField (2);
        minutt = new JTextField(2);
        personId = new JTextField(15);
        //initialiserer checkbox for formål
        pleasureOrEd = new JCheckBox();
        pleasureOrEd.setPreferredSize(new Dimension(200,20));
        //initialiserer comboboxer for rom, kurs, grupper, formål og varighet
        idRomListe = new JComboBox(idRom);
        idKursList = new JComboBox(idKurs);
        gruppen = new JComboBox();
        formal = new JComboBox(formalListe);
        bVarighet = new JComboBox(bookingTid);
        //initialiserer paneler
        top = new JPanel();
        bottom = new JPanel();
        paRom = new JPanel();
        paKurs = new JPanel();
        kursInput = new JPanel(); //Panel som holder combobox for kursbooking
        formalInput = new JPanel(); //Panel som holder combobox for andre formål
        midSection = new JPanel();
        midSection.setPreferredSize(new Dimension(310, 80));
        paVarighet = new JPanel();
        setLayout(new FlowLayout() );
        //Legger til komponenter til hovedPanelet
        book = new JButton("Book");
        add(top);
        add(bottom);
        top.add(new JLabel("Dag:"));
        top.add(dag);
        top.add(new JLabel("Måned:"));
        top.add(måned);
        top.add(new JLabel("Klokkeslett:"));
        top.add(time);
        top.add(new JLabel(":"));
        top.add(minutt);
        paRom.add(new JLabel("RomId:"));
        paRom.add(idRomListe);
        midSection.add(new JLabel("Annet formål:"));
        midSection.add(pleasureOrEd);
        paKurs.add(new JLabel("Kurs:"));
        paKurs.add(idKursList);
        paKurs.add(new JLabel("Gruppe:"));
        paKurs.add(gruppen);
        paVarighet.add(new JLabel("Varighet:"));
        paVarighet.add(bVarighet);
        top.add(new JLabel("Person ID:"));
        top.add(personId);
        top.add(paRom);
        top.add(paVarighet);
        top.add(midSection);
        formalInput.add(new JLabel("Formål:"));
        formalInput.add(formal);
        midSection.add(formalInput);
        midSection.add(kursInput);
        kursInput.add(paKurs);
        bottom.add(book);
        //initialiserer tabell
        scrollBookTable = new JScrollPane(table);
        bottom.add(scrollBookTable);
        //Definerer størrelser på komponenter
        book.setPreferredSize(new Dimension(80,25));
        top.setPreferredSize(new Dimension(320, 160));
        bottom.setPreferredSize(new Dimension(600, 500));
        setPreferredSize(new Dimension(600, 550));
        scrollBookTable.setPreferredSize(new Dimension(600, 300));
        //legger lyttere til komponenter
        book.addActionListener(lyttRB);
        idKursList.addItemListener(lyttItem);
        pleasureOrEd.addActionListener(lyttRB);
        //synliggjør komponenter
        table.setVisible(true);
        kursInput.setVisible(true);
        formalInput.setVisible(false);     
    }
    //metode som legger Booking-objekter til bookingtabellen
    public void addToBookingTable()
    {
        String[][] temp = bookList.getBookingTableData();
        for(int i = temp.length -1; i >= 0; i--)
        {
            modell.addRow(temp[i]);
        }
    }
    //Metode for å legge til en rad med bookingdata
    public void addRow()
    {
        if(dag.getText().equals("") || måned.getText().equals("") || time.getText().equals("") || minutt.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felter!");
        else
        {
            //Henter input Calender dato som integere
            int d = Integer.parseInt(dag.getText());
            int m = Integer.parseInt(måned.getText()) - 1;
            int t = Integer.parseInt(time.getText());
            int min = Integer.parseInt(minutt.getText());
            int s = 0;
        
            // Setter et Calendar objekt lik øvrige integere
            Calendar date = new GregorianCalendar();
            date.set(Calendar.MONTH, m);
            date.set(Calendar.DATE, d);
            date.set(Calendar.HOUR_OF_DAY, t);
            date.set(Calendar.MINUTE, min);
            date.set(Calendar.SECOND, s);
        
            Date start = date.getTime();    //Startdato booking Date objekt settes lik date.getTime();
            Calendar sluttDato = date;  //Oppretter et calendar objekt for sluttdato
            //Henter innkommende tid og legger til antall minutter valgt
            String tid = (String)bVarighet.getSelectedItem();
            if(tid.equalsIgnoreCase("30min"))
                sluttDato.add(Calendar.MINUTE, 30);
            else if(tid.equalsIgnoreCase("45min"))
                sluttDato.add(Calendar.MINUTE, 45);
            else if(tid.equalsIgnoreCase("60min"))
                sluttDato.add(Calendar.MINUTE, 60);
            else if(tid.equalsIgnoreCase("90min"))
                sluttDato.add(Calendar.MINUTE, 90);
            else if(tid.equalsIgnoreCase("120min"))
                sluttDato.add(Calendar.MINUTE, 120);
            //Sluttdato
            Date end = sluttDato.getTime(); //Ekte sluttdato for bookingen
            Calendar fakeSluttDato = sluttDato;
            //Fakesluttdato et minutt før sluttdato, brukes i metode for å sjekke overlapping.
            fakeSluttDato.add(Calendar.MINUTE, -1);
            Date fakeEnd = fakeSluttDato.getTime(); 
            //Tidspunktet nå
            Calendar cal = Calendar.getInstance(); 
            cal.add(Calendar.MINUTE, -1);
            Date now = cal.getTime();
       
            //Dersom tidspunktet now er mindre eller lik startdato 
            if(now.compareTo(start) <= 0)
            {    
                String romID = idRomListe.getSelectedItem().toString();
                String kursFormal = "ingen";
                String g = "ingen";
                if(pleasureOrEd.isSelected() == false)
                {
                    kursFormal = (String)idKursList.getSelectedItem();
                    g = (String)gruppen.getSelectedItem();
                }
                else
                {
                    kursFormal = (String)formal.getSelectedItem();
                }
                Rom bRom = romList.finnRom(romID);
                Person per = perList.finnPersonID(personId.getText());
                if (per == null)
                {
                    JOptionPane.showMessageDialog(null, "Personen finnes ikke i registeret!");
                }
                else if(bookList.checkIfOverlap(romID, start, end) == false)
                {
                    Booking ny = new Booking(bRom, kursFormal, g, per.getID(), start, end, fakeEnd);
                    bookList.settInnBooking(ny);
                    modell.setRowCount(0);
                    addToBookingTable();
                    dag.setText(""); måned.setText(""); time.setText(""); minutt.setText(""); personId.setText("");
                    String i = per.getID();
                    String o = "Rom NR: " + bRom.getRomId();
                    String a = "Booket";
                    hist.entry(o, a, i);
                }
                else 
                    JOptionPane.showMessageDialog(null, "Rommet er ikke ledig ved det ønskede tidspunktet!");
            }
            else{JOptionPane.showMessageDialog(null, "Du kan ikke booke et rom i fortiden!");}
        }
    }
    //metode som fjerner bookinger som er gått ut på dato
    public void fjernGamal()
    {
        bookList.fjernGamleBooking();
         modell.setRowCount(0);
               addToBookingTable();
    }
    //metode for å oppdatere combo
    public void updateComboBoxes()
    {
        idRomListe.removeAllItems();
        idKursList.removeAllItems();
        String[] romulus = romList.romStrings();
        String[] kursulus = kursList.kursStrings();
        for(int i = 0; i < romulus.length; i++)
            idRomListe.addItem(romulus[i]);
        for(int i = 0; i < kursulus.length; i++)
            idKursList.addItem(kursulus[i]);      
    }
    //metode som oppdaterer comboboxen for grupper
    protected void updateCombo2()
    {
        gruppen.removeAllItems();
       if(idKursList.getItemCount() != 0)
       {
           String temp = (String)idKursList.getSelectedItem();
           Kurs kursus = kursList.finnKurs(temp);
           String[] gruppene = kursus.getGruppe();
       
           for(int i = 0; i < gruppene.length; i++)
           {
                gruppen.addItem(gruppene[i]);
           }
       }
    }
    //privat lytteklasse
    private class itemLytter1 implements ItemListener
    {
        public void itemStateChanged(ItemEvent e) 
        {
            if (e.getStateChange() == ItemEvent.SELECTED) 
            {
                updateCombo2();
            }
        }
    }
    //metode som viser/skuler kurs og formål
    private void showHideKurs()
    {
        if(kursInput.isVisible())
        {
            kursInput.setVisible(false);
            formalInput.setVisible(true);
        }
        else
        {
            kursInput.setVisible(true);
            formalInput.setVisible(false);
        }
    }
    //privat lytteklasse for panelet
    private class LyttRB implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == book)
                 addRow();
             else if(e.getSource() == pleasureOrEd)
               showHideKurs();

        }
    }
}
