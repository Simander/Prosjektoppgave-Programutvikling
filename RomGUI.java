/**
 *
 * @author anders
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RomGUI extends JPanel implements Serializable
{
        private static final long serialVersionUID = 1L;
        private LyttR lR;
        private RomListe romList;
        private JTextField rId;
        private JTextField rName;
        private JButton reg;
        private JButton getAll;
        private JButton delete;
        private JTextArea info;
        //private JLabel overskrift;
        private JPanel beholder;
        private JPanel top;
        private JPanel bottom;
        
    RomGUI(RomListe r)
    {
        
        //overskrift = new JLabel("Romlistebehandler");
        rId = new JTextField(20);
        rName = new JTextField(20);
        reg = new JButton("Registrer");
        getAll = new JButton("hent alle");
        delete = new JButton("slett");
        info = new JTextArea(10, 30);
        romList = r;
        beholder = new JPanel();
        top = new JPanel();
        bottom = new JPanel();
        //add(overskrift);
        lR = new LyttR();
        reg.addActionListener(lR);
        getAll.addActionListener(lR);
        delete.addActionListener(lR);
        beholder.setVisible(true);
        beholder.setPreferredSize(new Dimension(400,500));
        top.add(new JLabel("RomID: "));
        top.add(rId);
        top.add(new JLabel("RomNavn: "));
        top.add(rName);
        bottom.add(new JScrollPane(info));
        bottom.add(reg);
        bottom.add(getAll);
        bottom.add(delete);
        beholder.add(top);
        beholder.add(bottom);
        add(beholder);
        top.setPreferredSize(new Dimension(350,50));
        bottom.setPreferredSize(new Dimension(400,550));
  
    }
    public void regNew()
    {
        String romId = rId.getText();
        String romName = rName.getText();
        Rom temp = new Rom(romId, romName);
        romList.settInnRom(temp);
    }
    public void getAll()
    {
        String s = romList.hentListeData();
        info.setText(s);
    }
    public void delete()
    {
        String s = rId.getText();
        romList.removeRom(s);
        info.setText("rom fjernet fra listen!");
    }
    
    //Lytteklasse for GUIs ulike knapper

   
  

private class LyttR implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == reg)
                    regNew();
            else if(e.getSource() == getAll)
                    getAll();
            else if(e.getSource() == delete)
                delete();
        }
    }
}
