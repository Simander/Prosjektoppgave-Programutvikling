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

public class UtleieGUI extends JInternalFrame
{
    
    private InstrumentRegister iReg;
    private JTextField iID;
    private JButton utlan;
    private JButton vinfo;
    private JButton innlevering;
    private JButton sjekk;
    private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttUG lyttUG;
    
    UtleieGUI(InstrumentRegister ur)
    {
        super ("Instrument Behandler");
        iReg = ur;
        iID = new JTextField (20);
        vinfo = new JButton ("Vis info");
        utlan = new JButton ("Utlån");
        innlevering = new JButton ("Innlevering");
        sjekk = new JButton ("Sjekk Ledighet");
        info = new JTextArea (20,32);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        top = new JPanel();
        bottom = new JPanel();
        top.setPreferredSize(new Dimension (400,100));
        bottom.setPreferredSize(new Dimension(480,500));
        
        top.add(new JLabel("Instrument NR"));
        top.add(iID);
        bottom.add(top);
        bottom.add(vinfo);
        bottom.add(sjekk);
        bottom.add(utlan);
        bottom.add(innlevering);
        
        
        c.add(bottom);
        
        lyttUG = new LyttUG();
        vinfo.addActionListener(lyttUG);
        sjekk.addActionListener(lyttUG);
        utlan.addActionListener(lyttUG);
        innlevering.addActionListener(lyttUG);
    }
    private class LyttUG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == vinfo)
            {
                JOptionPane.showMessageDialog (null, "Knappen funker!(vinfo)");
            }
            else if (e.getSource() == sjekk)
            {
                
                JOptionPane.showMessageDialog (null, "Knappen funker!(sjekk)");
            }
            else if (e.getSource() == utlan)
            {
                JOptionPane.showMessageDialog (null, "Knappen funker!(utlan)");
            }
            else if (e.getSource() == innlevering)
            {
                JOptionPane.showMessageDialog (null, "Knappen funker!(innlevering)");
            }
        }
    }
