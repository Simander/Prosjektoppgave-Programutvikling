/**
 *
 * @author anders
 */
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JTable;
import java.io.*;
import java.text.*;
public class RomBooking extends JPanel implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private PersonListe perList;
    private RomListe romList;
    private KursListe kursList;
    private BookingListe bookList;
    private Calendar calender;
    public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM | HH:mm");
    private LyttRB lyttRB;
    private JTextField dag;
    private JTextField måned;
    private JTextField år;
    private JTextField time;
    private JTextField minutt;
    private JTextField personId;
   // private JTextField romId;
    private JTextField bruk;
    private JButton add;
    private JButton clearOld;
    private String[] idRom;
    private String[] idKurs;
    private String[] bookingTid = {"30min", "45min", "60min", "90min", "120min"};
    private JComboBox idRomListe;
    private JComboBox idKursList;
    private JComboBox bVarighet;
   // private JButton oppdater;
    private JPanel top;
    private JPanel bottom;
    private JPanel paRom;
    private JPanel paKurs;
    private JPanel paVarighet;
     DefaultTableModel modell = new DefaultTableModel(); 
        JTable table = new JTable(modell); 
      private List<Object> booking; 
      
    RomBooking(PersonListe p, RomListe r, BookingListe b, KursListe k)
    {
        //initialisering av relevante lister;
        perList = p;
        romList = r;
        kursList = k;
        bookList = b;
        //Verdier til JComboBoxes
        idRom = romList.romStrings();
        idKurs = kursList.kursStrings();
              
        //setter Kolonner til bookingTable;
        modell.addColumn("RomId:");
        modell.addColumn("Dato");
        modell.addColumn("Fra:");
        modell.addColumn("Til:");
        modell.addColumn("KursId:");
        modell.addColumn("Ansvarlig:");
        lyttRB = new LyttRB();
        dag = new JTextField(2);
        måned = new JTextField(2);
        time = new JTextField (2);
        minutt = new JTextField(2);
        personId = new JTextField(15);
        idRomListe = new JComboBox(idRom);//romId = new JTextField(20);
        idKursList = new JComboBox(idKurs);
        bVarighet = new JComboBox(bookingTid);
       // idRomList.setPreferredSize(new Dimension(20,20));
        bruk = new JTextField(17);
        add = new JButton("Book");
      //  clearOld = new JButton("Fjern gamle");
        //oppdater = new JButton("oppdater");
       // Container c = getContentPane();
        top = new JPanel();
        bottom = new JPanel();
        paRom = new JPanel();
        paKurs = new JPanel();
        paVarighet = new JPanel();
        setLayout(new FlowLayout() );
        add(top);
        add(bottom);
        top.add(new JLabel("Dag:"));
        top.add(dag);
        top.add(new JLabel("Måned:"));
        top.add(måned);
        top.add(new JLabel("Klokkeslett"));
        top.add(time);
        top.add(new JLabel(":"));
        top.add(minutt);
        paRom.add(new JLabel("RomId:"));
        paRom.add(idRomListe);
        paKurs.add(new JLabel("Kurs:"));
        paKurs.add(idKursList);
        paVarighet.add(new JLabel("Varighet:"));
        paVarighet.add(bVarighet);
        top.add(new JLabel("Navn:"));
        top.add(personId);
        top.add(paRom);
        top.add(paKurs);
        top.add(paVarighet);
        bottom.add(add);
        add.setPreferredSize(new Dimension(80,25));
       // bottom.add(clearOld);
        //bottom.add(oppdater);
        JScrollPane scrollBookTable = new JScrollPane(table);
        bottom.add(scrollBookTable);
        top.setPreferredSize(new Dimension(320, 122));
        bottom.setPreferredSize(new Dimension(480, 500));
        setPreferredSize(new Dimension(480, 550));
        table.setVisible(true);
        
        scrollBookTable.setPreferredSize(new Dimension(400, 350));
        add.addActionListener(lyttRB);
       // clearOld.addActionListener(lyttRB);
        addToBookingTable();
        
        
       // fjernGamal();
        //oppdater.addActionListener(lyttRB);
        //();
        
        
        
    }/*
    public void setIdRomList(String[] s)
    {
        idRomListe = new JComboBox(s);
    }
    public void setIdKursList(String[] s)
    {
        idRomListe = new JComboBox(s);
    }*/
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
        //Henter input Calender dato som integere
        int d = Integer.parseInt(dag.getText());
        int m = Integer.parseInt(måned.getText()) - 1;
        int t = Integer.parseInt(time.getText());
        int min = Integer.parseInt(minutt.getText());
        int s = 0;
        dag.setText(""); måned.setText(""); time.setText(""); minutt.setText("");
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

        fakeSluttDato.add(Calendar.MINUTE, -1);
        Date fakeEnd = fakeSluttDato.getTime(); //Falsk sluttdato, et minutt før Date end, brukes til å sjekke dobbelbooking
             
        Date now = Calendar.getInstance().getTime(); //Tidspunktet nå
       
        
        //Dersom tidspunktet now er mindre eller lik startdato
        if(now.compareTo(start) <= 0)
        {   
           String romID = idRomListe.getSelectedItem().toString();
           String kId = (String)idKursList.getSelectedItem();
           Rom bRom = romList.finnRom(romID);
           Kurs bKurs = kursList.finnKurs(kId);
           Person per = perList.finnPerson(personId.getText());
          //JOptionPane.showMessageDialog(null, start.toString() + "+"+end.toString() + "+"+realEnd.toString());
           if(bookList.checkIfOverlap(romID, start, end) == false)
           {
               Booking ny = new Booking(bRom, bKurs, per, start, end, fakeEnd);
               bookList.settInnBooking(ny);
               modell.setRowCount(0);
               addToBookingTable();
           }
           else 
            JOptionPane.showMessageDialog(null, "Rommet er ikke ledig ved det ønskede tidspunktet!");
        }
        else{JOptionPane.showMessageDialog(null, "Du kan ikke booke et rom i fortiden!");}
        //JOptionPane.showMessageDialog(null, date.getTime());
    }
    public void fjernGamal()
    {
        bookList.fjernGamleBooking();
         modell.setRowCount(0);
               addToBookingTable();
    }

    //privat lytteklasse for panelet
    private class LyttRB implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == add)
                 addRow();
             else if(e.getSource() == clearOld)
               fjernGamal();

        }
    }
}
