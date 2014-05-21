/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for å registrere nye kurs, og inneholder
 * metoder for registrering.
 */
import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
public class RegKursGUI  extends JPanel
{
    //privat lytteklasse
    private LyttRegKurs lrk;
    //lister
    private PersonListe perList;
    private KursListe kursList;
    private Historikk hist;
    //tekstfelter for input av info for lagring av kurs
    private JTextField kursId;
    private JTextField kursNavn;
    private JTextField laererID;
    //textarea for å gi tilbakemelding
    private JTextArea info;
    //paneler
    private JPanel top;
    private JPanel bottom;
    private JPanel left;
    private JPanel right;
    //combobox
    private JComboBox kurstype; 
    //array med data til kurstype
    private String[] kType = PersonRegVindu.instrumentTypeMain;
    //array med labels
    private JLabel[] labels = {new JLabel("KursKode:"), new JLabel("KursNavn:"), new JLabel("LærerID:"), new JLabel("KursType:")};
    //knapper
    private JButton reg;    //knapp for registring
    private JButton hent;   //knapp for henting av listedata
    //konstruktør
    RegKursGUI(PersonListe p, KursListe k, Historikk h)
    {
        //initialiserer lytteklasse
        lrk = new LyttRegKurs();
        //initialiserer lister
        hist = h;
        perList = p;
        kursList = k;
        //initialiserer tekstfelter
        kursId = new JTextField(15);
        kursNavn = new JTextField(15);
        laererID = new JTextField(15);
        //initialiserer knapper
        reg = new JButton("Registrer");
        hent = new JButton("Hent data");
        //initialiserer tekstfelt
        info = new JTextArea(10,30);
        info.setEditable(false);
        //initialiserer paneler
        top = new JPanel();
        bottom = new JPanel();
        left = new JPanel();
        right = new JPanel();
        //fikser labels
        for(int i = 0; i < labels.length; i++)
        {
            left.add(labels[i]);
            labels[i].setPreferredSize(new Dimension(80, 20));
        }
        //initialiserer comboboxen for kurstype
        kurstype = new JComboBox(kType);
        //setter layout for panelet top
        top.setLayout(new BorderLayout());
        //legger til komponenter
        top.add(left, BorderLayout.LINE_START);
        top.add(right, BorderLayout.LINE_END);
        right.add(kursId);
        right.add(kursNavn);
        right.add(laererID);
        right.add(kurstype);
        bottom.add(reg);
        bottom.add(hent);
        bottom.add(new JScrollPane(info));
        add(top);
        add(bottom);
         //setter dimensioner
        left.setPreferredSize(new Dimension(80, 120));
        right.setPreferredSize(new Dimension(250, 120));
        kurstype.setPreferredSize(new Dimension(160, 20));
        top.setPreferredSize(new Dimension(330, 100));
        bottom.setPreferredSize(new Dimension(400,400));
        //legger lyttere til knapper
        reg.addActionListener(lrk);
        hent.addActionListener(lrk);
    }
    //metode for å registrere et nytt kurs
    private void regNyKurs()
    {
        if(!laererID.getText().equals("") || !kursId.getText().equals("") || !kursNavn.getText().equals(""))
        {
            if(perList.finnLaerer(laererID.getText()) != null)
            {
                Teacher teach = perList.finnLaerer(laererID.getText());
                String kId = kursId.getText();
                String kName = kursNavn.getText();
                String kursT = (String)kurstype.getSelectedItem();
                Kurs temp = new Kurs(kId, kName, teach.getName(), kursT);
                kursList.settInnKurs(temp);
                teach.setKursAnsvarlig(temp);
                String s = "Nytt kurs: " + kId + ". er lagt til!";
                info.setText(s);
                String i = kId;
                String o = "Kurs";
                String a = "Registrert";
                hist.entry(o, a, i);
                kursId.setText("");
                kursNavn.setText("");
            }
            else
            {
                info.setText("Ugyldig ID!");
            }
        }
        else
        {
            info.setText("Vennligst fyll ut alle felter!");
        }
    }
    //metode som printer kurslistedata til tekstområdet info
    private void getKursInfo()
    {
        String s = kursList.hentListeData();
        info.setText(s);
    }
    //privat lytteklasse
    private class LyttRegKurs implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == reg)
                 regNyKurs();
             else if (e.getSource() == hent)
                getKursInfo();
    
        }
    }
    
}
