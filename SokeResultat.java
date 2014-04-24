/**
 *
 * @author simander
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SokeResultat extends JPanel
{
    private Hidelytter hL;
    private JButton hide;
    public JTextArea info;
    public JPanel beholder;
    private JLabel overskrift;
    
    public DefaultTableModel modelElev = new DefaultTableModel();
    public DefaultTableModel modelBook = new DefaultTableModel();
    public JTable elevTable;
    public JTable bookingTable;
    JScrollPane scrollElev;
    JScrollPane scrollBook;
    
    SokeResultat()
    {
        //Kolonner for elevtabell
        modelElev.addColumn("ElevId:");
        modelElev.addColumn("Navn:");
        modelElev.addColumn("Adresse:");
        modelElev.addColumn("Tlf:");
        modelElev.addColumn("Instrument:");
        //Kolonner for bookingTabell
        modelBook.addColumn("RomId:");
        modelBook.addColumn("KursId:");
        modelBook.addColumn("Ansvarlig:");
        modelBook.addColumn("StartDato:");
        modelBook.addColumn("SluttDato:");
        //oppretter tabeller
        elevTable = new JTable(modelElev);
        bookingTable = new JTable(modelBook);
       // elevTable.setPreferredScrollableViewportSize(new Dimension(40,40));
        //elevTable.setPreferredSize(new Dimension(600,600));
        hide = new JButton("Skjul vinduet.");
        beholder = new JPanel();
        info = new JTextArea(10, 40);
        overskrift = new JLabel("Søkeresultat:");
        overskrift.setForeground(Color.WHITE);
        add(overskrift);
        
        
       add(beholder);
         
        beholder.add(info);
        add(hide);
        beholder.setLayout(new FlowLayout());
        
        beholder.setBackground(Color.DARK_GRAY);
        hL = new Hidelytter();
        beholder.setVisible(true);
        info.setVisible(true);
        setVisible(true);
       // Container c = getContentPane();
        //c.setLayout(new FlowLayout() );
        //c.add(content);
        //c.add(hide);
        setLayout(new FlowLayout());
      beholder.setPreferredSize(new Dimension(600, 400));
        setBackground(Color.DARK_GRAY);
       scrollElev = new JScrollPane(elevTable);
       scrollBook = new JScrollPane(bookingTable);
       //scrollmo.setPreferredSize(new Dimension(800,800));
        beholder.add(scrollElev);
        beholder.add(scrollBook);
        //content.setPreferredSize(new Dimension(600,600));
        elevTable.setVisible(true);
        scrollBook.setVisible(true);
        info.setVisible(false);
     //   elevTable.setSize(400, 600);
        hide.addActionListener(hL);
    }
     private class Hidelytter implements ActionListener
    {
        public void actionPerformed (ActionEvent eF )
        {
            if (eF.getSource() == hide)
                setVisible(false);

         
        }
    }
}

    

