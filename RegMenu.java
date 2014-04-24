/**
 *
 * @author simander
 */
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class RegMenu extends JPanel
{
   private LyttRegSub lrs;
   private JButton[] subMenuButtons;
   private String[] buttonText = {"Ny Person", "Ny Kurs", "Ny Instrument", "Ny Rom"};  
   String[] toggle = new String[buttonText.length];
   private JPanel top;
   private JPanel bottom;
   private GUI2 regVindu;
   private JPanel romVindu;
   private JPanel kursVindu;
   private int wit;
   
   RegMenu(PersonListe p, KursListe k, RomListe r)
   {
      
        lrs = new LyttRegSub();
        top = new JPanel();
        bottom = new JPanel();
        subMenuButtons = new JButton[buttonText.length];
        for (int count = 0; count < buttonText.length; count++)
        {
            
            subMenuButtons[count] = new JButton(buttonText[count]);
            subMenuButtons[count].addActionListener( lrs );
            subMenuButtons[count].setBackground(Color.DARK_GRAY);
            subMenuButtons[count].setForeground(Color.white);
            subMenuButtons[count].setPreferredSize(new Dimension(125, 30));
            top.add(subMenuButtons[count]);
            toggle[count]="off";
        }
//        top.get
        //setLayout(new BorderLayout(0,0));
        top.setBackground(Color.LIGHT_GRAY);
        //setBackground(Color.DARK_GRAY);
       // bottom.setBackground(Color.WHITE);
        bottom.setPreferredSize(new Dimension(650,650));
        add(top);//, BorderLayout.PAGE_START);
        add(bottom);//, BorderLayout.LINE_START);
        regVindu = new GUI2(p);
        regVindu.setVisible(false);
        kursVindu = new RegKursGUI(p, k);
        kursVindu.setVisible(false);
        romVindu = new RomGUI(r);
        romVindu.setVisible(false);
        //top.setPreferredSize(new Dimension(650,40));
        top.setBounds(0, 0, 650, 40);
        top.setPreferredSize(new Dimension(650, 40));
        bottom.add(regVindu);
        bottom.add(kursVindu);
        bottom.add(romVindu);
        //setBounds(0,0,650,650);
          wit = top.getWidth();
   }
    public void perRegVin() //Metode som Henter Åpner lukker Registreringsvinduet
    {
            
        if(toggle[0].equals("off"))
        {
            hideAll();
            subMenuButtons[0].setBackground(Color.RED);
            regVindu.setPreferredSize(new Dimension(650, 650));
            regVindu.setVisible(true);
            toggle[0] = "on";
        }
       else
       {
            hideAll();
            toggle[0] ="off";
       }
     }
    
    public void kursRegVin() //Metode som Henter Åpner lukker Registreringsvinduet
    {
            
        if(toggle[1].equals("off"))
        {
            hideAll();
            subMenuButtons[1].setBackground(Color.RED);
            kursVindu.setPreferredSize(new Dimension(600, 550));
            kursVindu.setVisible(true);
            toggle[1] = "on";
        }
       else
       {
            hideAll();
            toggle[1] ="off";
       }
     }
     public void romRegVin()
     {
           
           if(toggle[3].equals("off"))
            {
                hideAll();
                
                subMenuButtons[3].setBackground(Color.RED); //Bytter farge op knapp
                romVindu.setVisible(true);
                toggle[3] = "on";
            }
            else
            {   
                subMenuButtons[3].setBackground(Color.DARK_GRAY);
                hideAll();
               toggle[3] = "off";
            } 
        }
     private void hideAll()
        {
         regVindu.setVisible(false);
         kursVindu.setVisible(false);
         romVindu.setVisible(false);
        for(int i = 0; i < subMenuButtons.length; i++)
        {
            subMenuButtons[i].setBackground(Color.DARK_GRAY);
            toggle[i] = "off";
        }
            
    }
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
                    JOptionPane.showMessageDialog(null, top.getLocation());
            /*else if(e.getSource() == subMenuButtons[2])
                delete();*/
        }
    }
}
