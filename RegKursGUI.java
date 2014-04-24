/**
 *
 * @author simander
 */
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
public class RegKursGUI  extends JPanel
{
    private LyttRegKurs lrk;
    private PersonListe perList;
    private KursListe kursList;
    private JTextField kursId;
    private JTextField kursNavn;
    private JTextField laererNavn;
    private JTextArea info;
    private JButton reg;
    private JPanel top;
    private JPanel bottom;
    private JButton hent;
    RegKursGUI(PersonListe p, KursListe k)
    {
        lrk = new LyttRegKurs();
        perList = p;
        kursList = k;
        kursId = new JTextField(15);
        kursNavn = new JTextField(15);
        laererNavn = new JTextField(15);
        reg = new JButton("Registrer");
        hent = new JButton("Hent Info");
        info = new JTextArea(10,30);
        top = new JPanel();
        bottom = new JPanel();
        top.add(new JLabel("KursKode:"));
        top.add(kursId);
        top.add(new JLabel("KursNavn:"));
        top.add(kursNavn);
        top.add(new JLabel("LærerNavn:"));
        top.add(laererNavn);
        bottom.add(reg);
        bottom.add(hent);
        top.setPreferredSize(new Dimension(300, 80));
        bottom.setPreferredSize(new Dimension(400,400));
        bottom.add(new JScrollPane(info));
        add(top);
        add(bottom);
    
        reg.addActionListener(lrk);
        hent.addActionListener(lrk);
    }
    public void regNyKurs()
    {
        Teacher teach = perList.finnLaerer(laererNavn.getText());
        String kId = kursId.getText();
        String kName = kursNavn.getText();
        Kurs temp = new Kurs(kId, kName, teach);
        kursList.settInnKurs(temp);
        String s = "Nytt kurs: " + kId + ". er lagt til!";
        info.setText(s);
    }
    public void getSmores()
    {
        String s = kursList.hentListeData();
        info.setText(s);
    }
    private class LyttRegKurs implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == reg)
                 regNyKurs();
             else if (e.getSource() == hent)
                getSmores();
    
        }
    }
    
}
