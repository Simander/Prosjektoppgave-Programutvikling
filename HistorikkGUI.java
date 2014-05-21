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
    private JButton pershistorikk;
    private JButton objektlogg;
    private JButton handlingslogg;
    private JComboBox handlinger;
    private JComboBox objekter;
    
    //private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttIG lyttIG;
    
    //JTable
    private DefaultTableModel modelHist = new DefaultTableModel(); //Oppretter en tabell-modell
    public JTable histTable; //Oppretter en tabell
    private JTextArea info;
    Object columnNames[] = { "Dato", "Objekt", "Handling", "ID" };
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
        handlinger = new JComboBox(new String []{"Registrert","Slettet","Påmeldt","Booket","Påmeldt Kurs"});
        objekter = new JComboBox(new String []{"Person","Instrument","Kurs","Rom Nr"});
        //Legger til kollonner til tabellmodellen modelHist
        for(int i = 0; i < columnNames.length; i ++)
        {
            modelHist.addColumn(columnNames[i]);
        }
        histTable = new JTable(modelHist); //redefinerer tabellen etter modellen
        JScrollPane scrollHist = new JScrollPane(histTable); // lager et JScrollPane for tabellen
        
        sok = new JButton ("Søk");
        visalle = new JButton ("Vis alt");
        pershistorikk = new JButton ("IDlogg");
        objektlogg = new JButton ("Objektlogg");
        handlingslogg = new JButton("Handlingslogg");
        
        
        top = new JPanel();
        bottom = new JPanel();
        top.setPreferredSize(new Dimension (350,30));
        bottom.setPreferredSize(new Dimension(600,600));
        
        top.add(new JLabel("Dag"));
        top.add(day);
        top.add(new JLabel("Måned"));
        top.add(month);
        top.add(new JLabel("År"));
        top.add(year);
        
        
       
        
        bottom.add(top);
        bottom.add(sok);
        bottom.add(visalle);
        bottom.add(pershistorikk);
        bottom.add(objektlogg);
        bottom.add(handlingslogg);
        
        add(bottom);
        
        lyttIG = new LyttIG();
        sok.addActionListener(lyttIG);
        visalle.addActionListener(lyttIG);
        pershistorikk.addActionListener(lyttIG);
        objektlogg.addActionListener(lyttIG);
        handlingslogg.addActionListener(lyttIG);
        scrollHist.setPreferredSize(new Dimension(600,450));        
        bottom.add(scrollHist);
 
    }
    private void sokDato()
    {
        String d = day.getText();
        String m = month.getText();
        String y = year.getText();
        if(d.equals("") && m.equals("") && y.equals("") || d.equals("") || m.equals("") || y.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Vennligst fyll inn alle dato felter");
            modelHist.setRowCount(0);
        }
        else if (d.length() > 2 || m.length() > 2 || Integer.parseInt(d) > 31 || Integer.parseInt(m) > 12)
        {
            JOptionPane.showMessageDialog(null, "Ugyldig dato");
            modelHist.setRowCount(0);
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
        String[][] resultat = hist.visAlt();
        
        modelHist.setRowCount(0);
        for(int i = 0; i < resultat.length; i++)
        {
            if(resultat[i][0].contains(formatdato))
            {
            modelHist.addRow(resultat[i]);
            }
        }
            if(modelHist.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(null,"Ingen oppføringer på valgt dato.");
            }
        
       }
    }
    
    private void visAlt()
    {
        String[][] resultat = hist.visAlt();
        modelHist.setRowCount(0);
        for(int i = 0; i < resultat.length; i++)
        {
            modelHist.addRow(resultat[i]);
        }
    }
    private void idSok()
    {
        modelHist.setRowCount(0);
        try
        {
            String search = JOptionPane.showInputDialog("Skriv en ID:");
            while (search.equals(""))
            {

                JOptionPane.showMessageDialog(null,"Vennligst fyll inn søketekst.");
                search = JOptionPane.showInputDialog("Skriv en ID:");
            }
            String[][] resultat = hist.visAlt();
            for(int x = 0; x < resultat.length; x++)
            {
                if(resultat[x][3].equalsIgnoreCase(search))
                {
                     modelHist.addRow(resultat[x]);
                } 
            }
            if(modelHist.getRowCount() == 0)
            {
                 JOptionPane.showMessageDialog(null,"Ingen oppføring med ID: " + search);
            }
        }
        catch (NullPointerException e)
        {
            //Kan kanskje unngås, nødvendig for å lukke uten å ha skrevet noe.
        }
         
    }
    private void objektSok()
    {
        
        modelHist.setRowCount(0);
        JOptionPane.showMessageDialog(null,objekter);
        String search = objekter.getSelectedItem().toString();
        
        String[][] resultat = hist.visAlt();
        
        for(int x = 0; x < resultat.length; x++)
        {
            if(resultat[x][1].contains(search))
            {
                 modelHist.addRow(resultat[x]);
            }
        }
        if(modelHist.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null,"Objektet: " + search + " finnes ikke, eller har ingen oppføringer.");
            modelHist.setRowCount(0);
        }
    }
    private void handlingsSok()
    {
        modelHist.setRowCount(0);
        JOptionPane.showMessageDialog(null,handlinger);
        String search = handlinger.getSelectedItem().toString();
        String[][] resultat = hist.visAlt();
        for(int x = 0; x < resultat.length; x++)
        {
            if(resultat[x][2].contains(search))
            {
                 modelHist.addRow(resultat[x]);
            }
        }
        if(modelHist.getRowCount() == 0)
        {
                JOptionPane.showMessageDialog(null,"Ingen hendelse av type " + search + " oppført.");
                modelHist.setRowCount(0);
        }
    }
    private class LyttIG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {

            if (e.getSource() == sok)
            {
                sokDato();
            }
            else if (e.getSource() == visalle)
            {
                visAlt();
            }
            else if(e.getSource() == pershistorikk)
            {
               
               idSok();
            }
            else if(e.getSource() == objektlogg)
            {
               
               objektSok();
            }
            else if(e.getSource() == handlingslogg)
            {
               
               handlingsSok();
            }
        }
    }
}
