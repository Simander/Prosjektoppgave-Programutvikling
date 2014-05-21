/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for søkeresultat.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SokeResultat extends JPanel
{
    private Hidelytter hL;  //privat lytteklasse
    private JButton hide;   //knapp for å skjule søkeresultatet
    public JTextArea info;  //tekstområde for visning av toString-resultat
    public JPanel beholder; //beholder for komponenter
    //overskrift til søkePanelet
    private JLabel overskrift;
    //tabeller
    protected DefaultTableModel modelElev = new DefaultTableModel();
    protected DefaultTableModel modelBook = new DefaultTableModel();
    protected DefaultTableModel modelTeach = new DefaultTableModel();
    protected JTable elevTable;
    protected JTable teachTable;
    protected JTable bookingTable;
    //JScrollPanes for lagring av tabeller
    protected JScrollPane scrollElev;
    protected JScrollPane scrollBook;
    protected JScrollPane scrollTeach;
    //konstruktør
    SokeResultat()
    {
        //Kolonner for elevtabell
        modelElev.addColumn("ID:");
        modelElev.addColumn("Navn:");
        modelElev.addColumn("Adresse:");
        modelElev.addColumn("PostNr:");
        modelElev.addColumn("Tlf:");
        modelElev.addColumn("HInstrument:");
        modelElev.addColumn("Kurs:");
        
        //Kolonner for lærertabell
        modelTeach.addColumn("ID:");
        modelTeach.addColumn("Navn:");
        modelTeach.addColumn("Adresse:");
        modelTeach.addColumn("PostNr:");
        modelTeach.addColumn("Tlf:");
        modelTeach.addColumn("HInstrument:");
        modelTeach.addColumn("Ansvarlig for:");
    
        //Kolonner for bookingTabell
        modelBook.addColumn("RomId:");
        modelBook.addColumn("Dato");
        modelBook.addColumn("Fra:");
        modelBook.addColumn("Til:");
        modelBook.addColumn("Kurs/Formål:");
        modelBook.addColumn("Gruppe:");
        modelBook.addColumn("Ansvarlig:");
       
        //initialiserer tabeller
        elevTable = new JTable(modelElev);
        teachTable = new JTable(modelTeach);
        bookingTable = new JTable(modelBook);
        scrollElev = new JScrollPane(elevTable);
        scrollTeach = new JScrollPane(teachTable);
        scrollBook = new JScrollPane(bookingTable);
        //initialiserer knapp
        hide = new JButton("Skjul vinduet.");
        //initialiserer panel
        beholder = new JPanel();
        //initialiserer tekstområdet
        info = new JTextArea(10, 40);
        //initialiserer overskrift
        overskrift = new JLabel("Søkeresultat:");
        overskrift.setForeground(Color.WHITE);
        //legger til komponenter
        add(overskrift);      
        add(beholder);
        beholder.add(info);
        add(hide);
        beholder.add(scrollElev);
        beholder.add(scrollTeach);
        beholder.add(scrollBook);
        //setter layout
        beholder.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        //setter bakgrunnsfarger
        beholder.setBackground(Color.DARK_GRAY);
        setBackground(Color.DARK_GRAY);
        //setter dimensjoner
        setPreferredSize(new Dimension(800, 400));
        beholder.setPreferredSize(new Dimension(800, 400));
        scrollElev.setPreferredSize(new Dimension(800, 500));
        scrollTeach.setPreferredSize(new Dimension(800, 500));
        scrollBook.setPreferredSize(new Dimension(800, 500));
        //skjuler/viser komponenter
        beholder.setVisible(true);
        info.setVisible(true);
        setVisible(true);
        elevTable.setVisible(true);
        bookingTable.setVisible(true);
        scrollBook.setVisible(false);
        scrollElev.setVisible(false);
        scrollTeach.setVisible(false);
        info.setVisible(false);
        hide.setVisible(true);
        //initialiserer lytter
        hL = new Hidelytter();
        //setter lytter til knapp
        hide.addActionListener(hL);
    }
    //metode som skjuler alle tabeller og tekstområdet i panelet
    public void hideAllSok()
    {
        scrollBook.setVisible(false);
        scrollElev.setVisible(false);
        scrollTeach.setVisible(false);
        info.setVisible(false);
    }
    //privat lytteklasse som skjuler panelet på knappetrykk
     private class Hidelytter implements ActionListener
    {
        public void actionPerformed (ActionEvent eF )
        {
            if (eF.getSource() == hide)
                setVisible(false);

         
        }
    }
}

    

