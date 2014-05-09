/**
 *
 * @author joakim
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


public class HistorikkGUI extends JPanel
{
    
    private Historikk hist;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    private JButton sok;
    private JButton visalle;
    //private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttIG lyttIG;
    
    //JTable
    private DefaultTableModel modelHist = new DefaultTableModel(); //Oppretter en tabell-modell
    public JTable histTable; //Oppretter en tabell
    private JTextArea info;
    Object columnNames[] = { "Dato", "Objekt", "Handling", "Bruker" };
    Object columnData[][];
    
    String formatdato;
    
    HistorikkGUI(Historikk h, String d)
    {
        //super ("Instrument Behandler");
        formatdato = d;
        hist = h;
        day = new JTextField (2);
        month = new JTextField (2);
        year = new JTextField (4);
        
        //Legger til kollonner til tabellmodellen modelHist
        for(int i = 0; i < columnNames.length; i ++)
        {
            modelHist.addColumn(columnNames[i]);
        }
        histTable = new JTable(modelHist); //redefinerer tabellen etter modellen
        JScrollPane scrollHist = new JScrollPane(histTable); // lager et JScrollPane for tabellen
        
        /*når en rad legges til tabellen må raden være i form av et array, og vi bruker metoden
         * modelHist.addRow(Rad[]);
         */
        //table = new JTable();
        sok = new JButton ("Søk");
        visalle = new JButton ("Vis all historikk");
        
        //info = new JTextArea (20,32);'
        
        top = new JPanel();
        bottom = new JPanel();
        top.setPreferredSize(new Dimension (300,100));
        bottom.setPreferredSize(new Dimension(480,500));
        
        top.add(new JLabel("Dag"));
        top.add(day);
        top.add(new JLabel("Måned"));
        top.add(month);
        top.add(new JLabel("År"));
        top.add(year);
        
        bottom.add(top);
        bottom.add(sok);
        bottom.add(visalle);
        //bottom.add(del);

        
        
        add(bottom);
        
        lyttIG = new LyttIG();
        sok.addActionListener(lyttIG);
        visalle.addActionListener(lyttIG);
        //del.addActionListener(lyttIG);
    
        //       
        //frame.add(scrollPane, BorderLayout.CENTER);
        scrollHist.setSize(750, 750);
        //frame.setVisible(true);
                
        bottom.add(scrollHist);
 
    }
    private void sokDato()
    {
        String d = day.getText();
        String m = month.getText();
        String y = year.getText();
        if(d.equals("") && m.equals("") && y.equals(""))
        {
            //Husk å sette feilmelding for ikke utfylte felter her.
        }
        else
        {

            if(d.length() != 2)
            {
                d = "0" + d;
            }
            if (m.length() != 2)
            {
                m = "0" + m;
            }
             if (y.length() < 4)
            {
                if(y.length() == 1)
                {
                    y = "000" + y;
                }
                else if (y.length() == 2)
                {
                    y = "00" + y;
                }
                else if (y.length() == 3)
                {
                    y = "0" + y;
                }

            }
        
        formatdato = d + "-" + m + "-" + y;
        
        columnData = hist.finnObjekt(formatdato);
        
               
         
        //info.setText(hist.finnDato(formatdato));
        }
    }
    
       
        
        
    private class LyttIG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
         //   if (e.getSource() == del)
           // {
             //   JOptionPane.showMessageDialog (null, "Knappen funker!(del)");
            //}
            if (e.getSource() == sok)
            {
                sokDato();
            }
            else if (e.getSource() == visalle)
            {
                //Vi viser ikek alt for faen
                //info.setText(hist.visAlt());
            }
            
            
        }
    }
}
