/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joakim
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UtleieGUI extends JPanel
{
    private Instrument instrument;
    private InstrumentRegister iReg;
    private InstrumentUtleie utleie;
    private JTextField iID;
    private JTextField Navn;
    private JButton utlan;
    private JButton vinfo;
    private JButton innlevering;
    private JButton sjekk;
    private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttUG lyttUG;
    private PersonListe PLIST;
    
    UtleieGUI(InstrumentRegister ur, PersonListe p, Instrument i)
    {
        //super ("Instrument Behandler");
        InstrumentRegister IR = new InstrumentRegister();
        
        iReg = ur;
        iID = new JTextField (20);
        Navn = new JTextField (20);
        vinfo = new JButton ("Vis info");
        utlan = new JButton ("Utlån");
        innlevering = new JButton ("Innlevering");
        sjekk = new JButton ("Sjekk Ledighet");
        info = new JTextArea (20,32);
        PLIST = p;
        
        //Container c = getContentPane();
      //  c.setLayout(new FlowLayout());
        
        top = new JPanel();
        bottom = new JPanel();
        top.setPreferredSize(new Dimension (400,100));
        bottom.setPreferredSize(new Dimension(480,500));
        
        top.add(new JLabel("Instrument NR"));
        top.add(iID);
        top.add(new JLabel("Navn"));
        top.add(Navn);
        bottom.add(top);
        bottom.add(vinfo);
        bottom.add(sjekk);
        bottom.add(utlan);
        bottom.add(innlevering);
        
        
        add(bottom);
        
        lyttUG = new LyttUG();
        vinfo.addActionListener(lyttUG);
        sjekk.addActionListener(lyttUG);
        utlan.addActionListener(lyttUG);
        innlevering.addActionListener(lyttUG);
    }
    public Instrument FinnInstrument()
    {
        InstrumentRegister IR = new InstrumentRegister();
        Instrument ins = IR.findID(iID.getText());
        Instrument found = null; 
        if(ins.getRegNum() .equalsIgnoreCase(iID.getText()))
        {
            return found;
        }
        else
            
            return null;
        
    }
    public Person FinnPupil()
    {
        String N = Navn.getText();
        return PLIST.finnPerson(N);
        
    }
    public InstrumentUtleie Sjekkutlaan()
    {
        InstrumentUtleie IR = new InstrumentUtleie(FinnInstrument(), FinnPupil());
         return IR;
    }
    public InstrumentUtleie Laanut()
    {
        InstrumentUtleie L = new InstrumentUtleie(FinnInstrument(), FinnPupil());
        L.utleie();
        info.setText(L.utleie());
        return null;
        
    }
    public InstrumentUtleie Innlevering()
    {
        InstrumentUtleie Inn = new InstrumentUtleie(FinnInstrument(), FinnPupil());
        Inn.innlevering();
        info.setText(Inn.innlevering());
        return null;
    }
    private class LyttUG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == vinfo)
            {
                if(FinnInstrument() == null)
                {
                    info.setText("Noe gikk galt");
                }
                else
                info.setText(FinnInstrument().toString());
            }
            else if (e.getSource() == sjekk)
            {
                FinnInstrument().getUtleid();
                if (FinnInstrument().getUtleid() == false)
                {
                    info.setText("Instrumentet med nr :\n" +
                            FinnInstrument().getRegNum() +"\n" +
                            "Er tilgjenglig for utlån");
                }
                else
                    info.setText("Instrumentet med nr :\n" +
                            FinnInstrument().getRegNum() +"\n" +
                            "Er IKKE tilgjenglig for utlån");
            }
            else if (e.getSource() == utlan)
            {
                Laanut();
            }
            else if (e.getSource() == innlevering)
            {
                Innlevering();
            }
        }
    }
}
