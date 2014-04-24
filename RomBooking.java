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
    private BookingListe booka;
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
    private JComboBox idRomList;
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
        booka = b;
        //Verdier til JComboBoxes
        idRom = romList.romStrings();
        idKurs = kursList.kursStrings();
              
        //setter Kolonner til bookingTable;
        modell.addColumn("Dato:");
        modell.addColumn("RomId:");
        modell.addColumn("Kurs/Formål:");
        modell.addColumn("Opptatt til:");
        lyttRB = new LyttRB();
        dag = new JTextField(2);
        måned = new JTextField(2);
        time = new JTextField (2);
        minutt = new JTextField(2);
        personId = new JTextField(15);
        idRomList = new JComboBox(idRom);//romId = new JTextField(20);
        idKursList = new JComboBox(idKurs);
        bVarighet = new JComboBox(bookingTid);
       // idRomList.setPreferredSize(new Dimension(20,20));
        bruk = new JTextField(17);
        add = new JButton("book");
        clearOld = new JButton("Fjern gamle");
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
        paRom.add(idRomList);
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
        bottom.add(clearOld);
        //bottom.add(oppdater);
        bottom.add(new JScrollPane(table));
        top.setPreferredSize(new Dimension(320, 122));
        bottom.setPreferredSize(new Dimension(480, 500));
        setPreferredSize(new Dimension(480, 550));
        table.setVisible(true);
        add.addActionListener(lyttRB);
        clearOld.addActionListener(lyttRB);
        //oppdater.addActionListener(lyttRB);
        //();
        
        
        
    }
    public void setIdRomList(String[] s)
    {
        idRomList = new JComboBox(s);
    }
    public void setIdKursList(String[] s)
    {
        idRomList = new JComboBox(s);
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
        //Startdato booking Date objekt settes lik date.getTime();
        Date start = date.getTime();
      // String data = dFormat.format(date.getTime());//
        //Oppretter et calendar objekt for sluttdato
        Calendar sluttDato = date;
        //Henter innkommende tid og legger til antall minutter valgt
        String tid = (String)bVarighet.getSelectedItem();
        if(tid.equalsIgnoreCase("30"))
            sluttDato.add(Calendar.MINUTE, 30);
        else if(tid.equalsIgnoreCase("45"))
            sluttDato.add(Calendar.MINUTE, 45);
        else if(tid.equalsIgnoreCase("60"))
            sluttDato.add(Calendar.MINUTE, 60);
        else if(tid.equalsIgnoreCase("90"))
            sluttDato.add(Calendar.MINUTE, 90);
        else if(tid.equalsIgnoreCase("120"))
            sluttDato.add(Calendar.MINUTE, 120);
        //Sluttdato
        Date end = sluttDato.getTime();
        
        //String b = dFormat.format(sluttDato.getTime());        
        Date now = Calendar.getInstance().getTime(); //Tidspunktet nå
       
        if(now.before(date.getTime()))
        {   
           // String[] bookingData ={data, rId, kId, b};
          
           String rId = (String)idRomList.getSelectedItem();
           String kId = (String)idKursList.getSelectedItem();
           Rom bRom = romList.finnRom(rId);
           Kurs bKurs = kursList.finnKurs(kId);
           Person per = perList.finnPerson(personId.getText());
          
          //modell.addRow(new String[]{data, rId, kId, b});
           
           Booking ny = new Booking(bRom, bKurs, per, start, end);
           booka.settInnBooking(ny);
           
         //   booka.settInnData(bookingData);
        }
        else{JOptionPane.showMessageDialog(null, "Du kan ikke booke et rom i fortiden!");}
        //JOptionPane.showMessageDialog(null, date.getTime());
    }
    //metode som laster inn rader fra Array til JTable hvor kurs enten pågår eller ikke har begynt
  /*  public void tablius() 
    {
        try{
        String[][] list1 = booka.hentListeData();
       
        for(int i = 0; i < list1.length; i++)
        {
          
          String[] lista = list1[i];
           String d1 = "2014/"+lista[0];
           //JOptionPane.showMessageDialog(null, d1);
            String d2 = "2014/"+lista[3]+":00";
           //Date da1 = new SimpleDateFormat("yyyy/dd/MM | HH:mm").parse(d1);
           Date da2 = new SimpleDateFormat("yyyy/dd/MM | HH:mm:ss").parse(d2);
           Calendar now = Calendar.getInstance();
           Date n = now.getTime();
           if(n.before(da2))
            modell.addRow(list1[i]);
          
        }
        }
        catch(ParseException e)
        {
             JOptionPane.showMessageDialog(null,"Det oppsto en feil!");
        }
        
    
    }
    */
    private class LyttRB implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == add)
                 addRow();
             //else if(e.getSource() == clearOld)
               //  booka.removeOldBooking();
            // else if(e.getSource() == oppdater)
              //   tablius();
        }
    }
}
