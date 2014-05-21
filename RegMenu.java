/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen representerer et sub-GUI, med knapper for visning av ulike registreringsGUI.
 */

//import-setninger
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RegMenu extends JPanel
{
   private LyttRegSub lrs;
   private JButton[] subMenuButtons;
   private String[] buttonText = {"Ny Person", "Nytt Kurs", "Nytt Instrument", "Nytt Rom"};  
   String[] toggle = new String[buttonText.length];
   private JPanel top;
   private JPanel bottom;
   private PersonRegVindu regVindu;
   private JPanel romVindu;
   private JPanel kursVindu;
   private JPanel instVindu;
   private int wit;
   private Historikk hist;
   //konstruktør
   RegMenu(PersonListe p, KursListe k, RomListe r, InstrumentRegister iReg, BookingListe b, Historikk h)
   {
       //initialiserer liste
        hist = h;
        //initialiserer lytteklasse
        lrs = new LyttRegSub();
        //initialiserer paneler
        top = new JPanel();
        bottom = new JPanel();
        //initialiserer knapper
        subMenuButtons = new JButton[buttonText.length];
        for (int count = 0; count < buttonText.length; count++)
        {
            
            subMenuButtons[count] = new JButton(buttonText[count]);
            subMenuButtons[count].addActionListener( lrs );
            subMenuButtons[count].setBackground(Color.DARK_GRAY);
            subMenuButtons[count].setForeground(Color.white);
            subMenuButtons[count].setPreferredSize(new Dimension(/*125*/150, 30));
            top.add(subMenuButtons[count]);
            toggle[count]="off";
        }
        //setter bakgrunnsfarge til top-panelet
        top.setBackground(Color.LIGHT_GRAY);
       
        //initialiserer sub-GUI
        regVindu = new PersonRegVindu(p,hist);
        regVindu.setVisible(false);
        kursVindu = new RegKursGUI(p, k,hist);
        kursVindu.setVisible(false);
        romVindu = new RomGUI(r, b, hist);
        romVindu.setVisible(false);
        instVindu = new InstrumentGUI(iReg, h);
        instVindu.setVisible(false);
        //setter dimensjoner og grenser
        top.setBounds(0, 0, 850, 40);
        setPreferredSize(new Dimension(850, 40));
        top.setPreferredSize(new Dimension(850, 40));
        bottom.setPreferredSize(new Dimension(850,650));
        //legger til komponenter
        add(top);
        add(bottom);
        bottom.add(regVindu);
        bottom.add(kursVindu);
        bottom.add(romVindu);
        bottom.add(instVindu);
          
   }
   //Metode som viser/skjuler personregistreringsvinduet
    protected void perRegVin() 
    {
        if(toggle[0].equals("off"))
        {
            hideAllRegWindows();
            subMenuButtons[0].setBackground(Color.RED);
            regVindu.setPreferredSize(new Dimension(650, 650));
            regVindu.setVisible(true);
            toggle[0] = "on";
        }
       else
       {
            hideAllRegWindows();
            toggle[0] ="off";
       }
     }
    //Metode som viser/skjuler kursregistreringsvinduet
    protected void kursRegVin() 
    {       
        if(toggle[1].equals("off"))
        {
            hideAllRegWindows();
            subMenuButtons[1].setBackground(Color.RED);
            kursVindu.setPreferredSize(new Dimension(600, 550));
            kursVindu.setVisible(true);
            toggle[1] = "on";
        }
       else
       {
            hideAllRegWindows();
            toggle[1] ="off";
       }
     }
    //Metode som viser/skjuler instrumentregistreringsvinduet
    protected void instRegVin()
    {
        if(toggle[2].equals("off"))
        {
            hideAllRegWindows();
            subMenuButtons[2].setBackground(Color.RED);
            instVindu.setPreferredSize(new Dimension(600, 550));
            instVindu.setVisible(true);
            toggle[2] = "on";
        }
       else
       {
            hideAllRegWindows();
            toggle[2] ="off";
       }
     }
    //metode som viser/skjuler romregistreringsvinduet
     protected void romRegVin()
     {
           if(toggle[3].equals("off"))
            {
                hideAllRegWindows();
                
                subMenuButtons[3].setBackground(Color.RED); //Bytter farge op knapp
                romVindu.setVisible(true);
                toggle[3] = "on";
            }
            else
            {   
                subMenuButtons[3].setBackground(Color.DARK_GRAY);
                hideAllRegWindows();
               toggle[3] = "off";
            } 
        }
        //metode som skjuler alle vinduer/paneler
        protected void hideAllRegWindows()
        {
            regVindu.setVisible(false);
            kursVindu.setVisible(false);
            romVindu.setVisible(false);
            instVindu.setVisible(false);
            for(int i = 0; i < subMenuButtons.length; i++)
            {
                subMenuButtons[i].setBackground(Color.DARK_GRAY);
                toggle[i] = "off";
            }
            
        }
    //privat lytteklasse
    private class LyttRegSub implements ActionListener
    {
        public void actionPerformed (ActionEvent e )
        {
             if (e.getSource() == subMenuButtons[0])
                 perRegVin();
             else if (e.getSource() == subMenuButtons[1])
                 kursRegVin();
             else if (e.getSource() == subMenuButtons[3])
                 romRegVin();
            else if(e.getSource() == subMenuButtons[2])
                    instRegVin();
        }
    }
}
