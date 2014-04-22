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
    private RomListe rom;
    private BookingList booka;
    private Calendar calender;
    public SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM | HH:mm");
    private LyttRB lyttRB;
    private JTextField dag;
    private JTextField måned;
    private JTextField år;
    private JTextField time;
    private JTextField minutt;
    private JTextField romId;
    private JTextField bruk;
    private JButton add;
    private JButton clearOld;
   // private JButton oppdater;
    private JPanel top;
    private JPanel bottom;
     DefaultTableModel modell = new DefaultTableModel(); 
        JTable table = new JTable(modell); 
      private List<Object> booking; 
    RomBooking(RomListe r, BookingList b)
    {
        //super("Rombooking!");
        rom = r;
        booka = b;
        String[] kolonneNavn = {"Dato","RomId", "Kurs/Formål", "Opptatt til:"};
        modell.addColumn("Dato:");
        modell.addColumn("RomId:");
        modell.addColumn("Kurs/Formål:");
        modell.addColumn("Opptatt til:");
        lyttRB = new LyttRB();
        dag = new JTextField(2);
        måned = new JTextField(2);
        time = new JTextField (2);
        minutt = new JTextField(2);
        romId = new JTextField(20);
        bruk = new JTextField(17);
        add = new JButton("book");
        clearOld = new JButton("Fjern gamle");
        //oppdater = new JButton("oppdater");
       // Container c = getContentPane();
        top = new JPanel();
        bottom = new JPanel();
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
        top.add(new JLabel("RomId:"));
        top.add(romId);
        top.add(new JLabel("Kurs/Formål:"));
        top.add(bruk);
        bottom.add(add);
        bottom.add(clearOld);
        //bottom.add(oppdater);
        bottom.add(new JScrollPane(table));
        top.setPreferredSize(new Dimension(320, 75));
        bottom.setPreferredSize(new Dimension(480, 500));
        setPreferredSize(new Dimension(480, 550));
        table.setVisible(true);
        add.addActionListener(lyttRB);
        clearOld.addActionListener(lyttRB);
        //oppdater.addActionListener(lyttRB);
        tablius();
        
        
        
    }
    //Metode for å legge til en rad med bookingdata
    public void addRow()
    {
        int d = Integer.parseInt(dag.getText());
        int m = Integer.parseInt(måned.getText()) - 1;
        int t = Integer.parseInt(time.getText());
        int min = Integer.parseInt(minutt.getText());
        int s = 0;
        String id = romId.getText();
        String formål = bruk.getText(); 
        dag.setText(""); måned.setText(""); time.setText(""); minutt.setText("");
        romId.setText(""); bruk.setText("");
        Calendar date = new GregorianCalendar();
        date.set(Calendar.MONTH, m);
        date.set(Calendar.DATE, d);
        date.set(Calendar.HOUR_OF_DAY, t);
        date.set(Calendar.MINUTE, min);
        date.set(Calendar.SECOND, s);
        String data = dFormat.format(date.getTime());
        Calendar beef = date;
        beef.add(Calendar.MINUTE, 30);
        String b = dFormat.format(beef.getTime());        
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        if(now.before(date.getTime()))
        {   modell.addRow(new String[]{data, id, formål, b});
            String[] bookingData ={data, id, formål, b};
            booka.settInnData(bookingData);
        }
        else{JOptionPane.showMessageDialog(null, "Du kan ikke booke et rom i fortiden!");}
        //JOptionPane.showMessageDialog(null, date.getTime());
    }
    //metode som laster inn rader fra Array til JTable hvor kurs enten pågår eller ikke har begynt
    public void tablius() 
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
    
    private class LyttRB implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == add)
                 addRow();
             else if(e.getSource() == clearOld)
                 booka.removeOldBooking();
            // else if(e.getSource() == oppdater)
              //   tablius();
        }
    }
}
