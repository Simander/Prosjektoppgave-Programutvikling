/**
 *
 * @author joakim
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InstrumentGUI extends JPanel
{
    
    private InstrumentRegister iReg;
    //private JTextField iID;
    private JComboBox iType;
    private JTextField brand;
    private JTextField iModel;
    private JTextField iCondition;
    private JButton register;
    //private JButton del;
    private JButton listut;
    private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private JPanel left;
    private JPanel right;
    private LyttIG lyttIG;
    
    private JLabel[] labels = {new JLabel("Type:"), new JLabel("Merke:"), new JLabel("Modell:"),new JLabel("Tilstand:")};
    Historikk hist;
    InstrumentGUI(InstrumentRegister ir, Historikk h)
    {
        hist = h;
        iReg = ir;
        iType = new JComboBox(new String[]
        {"Gitar","Bass","Keyboard","Tromme","Mikrofon","PA"});
        brand = new JTextField(20);
        iModel = new JTextField (20);
        iCondition = new JTextField (20);
        register = new JButton ("Registrer");
        listut = new JButton ("Hent data");
        info = new JTextArea (20,32);
        info.setEditable(false);
        top = new JPanel();
        bottom = new JPanel();
        left = new JPanel();
        right = new JPanel();
        top.setPreferredSize(new Dimension (350,120));
        bottom.setPreferredSize(new Dimension(380,500));
        for(int i = 0; i < labels.length; i++)
        {
            left.add(labels[i]);
            labels[i].setPreferredSize(new Dimension(70, 20));
        }
        iType.setPreferredSize(new Dimension(230, 20));
        left.setPreferredSize(new Dimension(70, 100));
        right.setPreferredSize(new Dimension(230, 120));
        top.setLayout(new BorderLayout());
        top.add(left, BorderLayout.LINE_START);
        top.add(right, BorderLayout.LINE_END);
        right.add(iType);
        right.add(brand);
        right.add(iModel);
        right.add(iCondition);
        bottom.add(top);
        bottom.add(register);
        bottom.add(listut);
        bottom.add(new JScrollPane(info));
        add(bottom);
        lyttIG = new LyttIG();
        register.addActionListener(lyttIG);
        listut.addActionListener(lyttIG);
    }
    private void SettInnInstrument() //Metode for å sette Instrument inn i registeret
    {
        String id = "";
        String tpe = iType.getSelectedItem().toString();
        
        if(tpe.equalsIgnoreCase("gitar")) //Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "G" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "G" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        if(tpe.equalsIgnoreCase("bass"))//Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "B" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "B" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        if(tpe.equalsIgnoreCase("keyboard"))//Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "K" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "K" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        if(tpe.equalsIgnoreCase("tromme"))//Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "T" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "T" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        if(tpe.equalsIgnoreCase("mikrofon"))//Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "M" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "M" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        if(tpe.equalsIgnoreCase("pa"))//Oppretter ID for Instrument og sjekker at nummeret ikke finnes fra før.
        {
            id = "P" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            
            while(iReg.findInstrument(id) != null)
            {
                id = "P" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
            }
        }
        String mrk = brand.getText();
        String mdl = iModel.getText();
        String cnd = iCondition.getText();
        Instrument temp = new Instrument(id,mrk,tpe,mdl,cnd);
        
        if(id.equals("") || tpe.equals("")|| mdl.equals("")||cnd.equals(""))
        {
            info.setText("Vennligst fyll ut alle felter");
        }
        
        else if(iReg.findInstrument(id) == null)
        {
          iReg.insertInstrument(temp);
          info.setText("Instrument med ID : " + id + " er registrert");
          String o = "Instrument";
          String a = "Registrert";
          hist.entry(o, a, id);
          brand.setText("");
          iModel.setText("");
          iCondition.setText("");
        }
    }
    
    private void ListEntries() //Liste metode, Lister ut registrerte instrumenters detaljer
    {
       String temp = iReg.listEntries();
       info.setText(temp);
    }
    
    private class LyttIG implements ActionListener //Lytter metode for knapper
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == register)
            {
                SettInnInstrument();
            }
            else if (e.getSource() == listut)
            {
                ListEntries();
            }
        }
    }
}
