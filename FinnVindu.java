/**
 *
 * @author anders
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FinnVindu extends JInternalFrame
{
    private Knappelytter lytt;
    private PersonRegister per;
    private JPanel panelFind;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea info;
    
    FinnVindu(PersonRegister p)
    {
        super("Hent info!");
        per = p;
        panelFind = new JPanel();
        panelFind.setVisible(true);
        searchField = new JTextField(30);
        searchButton = new JButton("Søk");
        info = new JTextArea(28, 38);
        info.setVisible(true);
        info.setBackground(Color.white);
        lytt = new Knappelytter();
        Container c = getContentPane();
        c.setLayout(new FlowLayout() );
        panelFind.add(new JLabel("Søketekst:"));
        panelFind.add(searchField);
        panelFind.add(searchButton);
        panelFind.add(new JLabel("Resultat av søk:"));
        panelFind.add(new JScrollPane(info));
        c.add(panelFind);
        panelFind.setPreferredSize(new Dimension(480, 550));
        //c.setPreferredSize(null);
        searchButton.addActionListener(lytt);
    }
    public void findByName()
    {
        Person testarhaud = per.findName(searchField.getText());
        String s = testarhaud.toString();
        info.setText(s);
    }
    public void findPupil()
    {
        String s = per.findPupil();
        info.setText(s);
    }
    public void findTeacher()
    {
        String s = per.findTeacher();
        info.setText(s);
    }
    public void stringSelector()
    {
        if(searchField.getText().matches("elev.*"))
            findPupil();
        else if(searchField.getText().matches("lærer.*"))
            findTeacher();
        else
            findByName();
    }
    private class Knappelytter implements ActionListener
    {
        public void actionPerformed (ActionEvent eF )
        {
            if (eF.getSource() == searchButton)
             stringSelector();
            
        }
    }
}
