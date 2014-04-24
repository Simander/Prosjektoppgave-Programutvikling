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

public class InstrumentGUI extends JPanel
{
    
    private InstrumentRegister iReg;
    private JTextField iID;
    private JTextField iType;
    private JTextField iModel;
    private JTextField iCondition;
    private JButton register;
    private JButton del;
    private JButton find;
    private JTextArea info;
    private JPanel top;
    private JPanel bottom;
    private LyttIG lyttIG;
    
    InstrumentGUI(InstrumentRegister ir)
    {
        //super ("Instrument Behandler");
        iReg = ir;
        iID = new JTextField (20);
        iType = new JTextField (20);
        iModel = new JTextField (20);
        iCondition = new JTextField (20);
        register = new JButton ("Registrer");
        del = new JButton ("Slett");
        find = new JButton ("Finn");
        info = new JTextArea (20,32);
        
        //Container c = getContentPane();
        //c.setLayout(new FlowLayout());
        
        top = new JPanel();
        bottom = new JPanel();
        top.setPreferredSize(new Dimension (400,100));
        bottom.setPreferredSize(new Dimension(480,500));
        
        top.add(new JLabel("Instrument NR"));
        top.add(iID);
        top.add(new JLabel("Instrument Type"));
        top.add(iType);
        top.add(new JLabel("Instrument Modell"));
        top.add(iModel);
        top.add(new JLabel("Tilstand"));
        top.add(iCondition);
        bottom.add(top);
        bottom.add(register);
        bottom.add(del);
        bottom.add(find);
        bottom.add(info);
        
        
        add(bottom);
        
        lyttIG = new LyttIG();
        register.addActionListener(lyttIG);
        del.addActionListener(lyttIG);
        find.addActionListener(lyttIG);
    }
    private void SettInnInstrument()
    {
        String id = iID.getText();
        String mdl = iModel.getText();
        String tpe = iType.getText();
        String cnd = iCondition.getText();
    }
    private class LyttIG implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
            if (e.getSource() == del)
            {
                JOptionPane.showMessageDialog (null, "Knappen funker!(del)");
            }
            else if (e.getSource() == register)
            {
                
                JOptionPane.showMessageDialog (null, "Knappen funker!(reg)");
            }
            else if (e.getSource() == find)
            {
                JOptionPane.showMessageDialog (null, "Knappen funker!(find)");
            }
        }
    }
}
