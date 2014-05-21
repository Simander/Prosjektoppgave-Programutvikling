/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er et brukergrensesnitt for registrering av Rom-objekter.
 */
//import-setninger
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RomGUI extends JPanel implements Serializable
{
        private static final long serialVersionUID = 1L;
        //privat lytteklasse
        private LyttR lR;
        //lister
        private RomListe romList;
        private BookingListe bookList;
        private Historikk hist;
        //textfelter
        private JTextField rId;
        private JTextField rName;
        //knapper
        private JButton reg;
        private JButton getAll;
        //tekstområde for tilbakemelding
        private JTextArea info;
        //paneler
        private JPanel beholder;
        private JPanel top;
        private JPanel bottom;
        private JPanel left;
        private JPanel right;
        //array som inneholder labels
        private JLabel[] labels = {new JLabel("ID: "), new JLabel("Navn: ")};
   
    RomGUI(RomListe r, BookingListe b, Historikk h)
    {
        //initialiserer lister
        hist = h;
        bookList = b;
        romList = r;
        //initialiserer tekstfelter
        rId = new JTextField(20);
        rName = new JTextField(20);
        //initialiserer knapper
        reg = new JButton("Registrer");
        getAll = new JButton("Hent data");
        //initialiserer textArea
        info = new JTextArea(10, 30);
        info.setEditable(false);
        //initialiserer paneler       
        beholder = new JPanel();
        top = new JPanel();
        bottom = new JPanel();
        left = new JPanel();
        right = new JPanel();
        //løkke som legger til labels
        for(int i = 0; i < labels.length; i++)
        {
            left.add(labels[i]);
            labels[i].setPreferredSize(new Dimension(50, 20));
        }
        //setter dimensjoner
        left.setPreferredSize(new Dimension(50, 50));
        right.setPreferredSize(new Dimension(280, 50));
        beholder.setPreferredSize(new Dimension(400,500));
        top.setPreferredSize(new Dimension(330,50));
        bottom.setPreferredSize(new Dimension(400,550));
        //setter layout
        top.setLayout(new BorderLayout());
        //legger til komponenter
        top.add(left, BorderLayout.LINE_START);
        top.add(right, BorderLayout.LINE_END); 
        right.add(rId);
        right.add(rName);
        top.add(left, BorderLayout.LINE_START);
        top.add(right, BorderLayout.LINE_END);
        bottom.add(new JScrollPane(info));
        bottom.add(reg);
        bottom.add(getAll);
        beholder.add(top);
        beholder.add(bottom);
        add(beholder);
        //initialiserer lytter
        lR = new LyttR();
        //setter beholder synlig
        beholder.setVisible(true);
        //setter lytteobjekter
        reg.addActionListener(lR);
        getAll.addActionListener(lR);
    }
    //metode for å registrere nytt rom
    public void regNew()
    {
        if(!rId.getText().equals("") || !rName.getText().equals(""))
        {
            String romId = rId.getText();
            String romName = rName.getText();
            if(romList.finnRom(romId) == null)
            {
                Rom temp = new Rom(romId, romName, bookList);
                romList.settInnRom(temp);
                info.setText("Nytt rom: " + romId + " er lagt til i registeret!");
                String i = romId;
                String o = "Rom";
                String a = "Registrert";
                hist.entry(o, a, i);
                rId.setText("");
                rName.setText("");
            }
            else
            {
                info.setText("Rommet er allerede registrert!");
            }
        }
        else
            info.setText("Vennligst fyll ut alle felter!");
    }
    //metode som henter data om alle rom og printer til tekstområdet
    public void getAll()
    {
        String s = romList.hentListeData();
        info.setText(s);
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
        //    else if(e.getSource() == delete)
         //       delete();
        }
    }
}
