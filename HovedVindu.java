/**
 *
 * @author anders
 */
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.LinkedList;
public class HovedVindu extends JFrame implements ActionListener
{
    //private PersonRegister pers;
    private PersonListe pers;
    private RomListe rom;
    private KursListe kurs;
    private BookingListe booka;
    private InstrumentRegister iReg;
    private UtleieListe utleie;
    private Historikk hist;
    private BorderLayout layout;
    private Container c;
    private boolean regBooleanOn;
    //Panels with different content;
    private JPanel center;
    private JLayeredPane layPane;
    private JPanel regMenu;
    private JPanel slettPanel;
    private JPanel romPanel;
    private RomBooking bookVindu;
    private JPanel utleieVindu;
   
 //   private JDesktopPane desktop = new JDesktopPane();
    //private JPanel desktop = new JPanel();
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu help;
    private JMenuItem logg;
    private JMenuItem about;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem doc;
    private JTextField sokeFelt;
    private JPanel panelL;
    private GUI2 panelReg;
    private JPanel panelFind;
    private SokeResultat sokePanel;
    private JButton sokeKnapp;
    private JButton show;
    private JButton menuButtons[];
    private final String names[] = {"Registrering", "Påmelding",
        "Booking", "Utleie", "Sletting"};
    private String toggle[] = new String[names.length];
 
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private int x = (int)screenSize.getWidth();
        private int y = (int)screenSize.getHeight();
       int lX = 150;
       private int oppdatert;
    
    
 public HovedVindu(/*PersonRegister*/PersonListe p, RomListe r, BookingListe b, KursListe k, InstrumentRegister ir, UtleieListe ut, Historikk h)
{
        super( "Kulturskolesystem 0.013");
        
        //Layout
        layout = new BorderLayout(5, 5);
        c = getContentPane();
        c.setLayout( layout );
        
        //datafelter og objekter initialiseres
        pers = p;
        rom = r;
        booka = b;
        kurs = k;
        iReg = ir;
        utleie = ut;
        hist = h;
        
        readFile(); //leser data fra fil

        //Meny greier
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Fil");
        //  menuBar.setLayout(new FlowLayout());
        menuBar.add(fileMenu);
        //sub menyer
        help = new JMenu("Hjelp");
        menuBar.add(help);
        //menu items
        doc = new JMenuItem("Dokumentasjon");
        help.add(doc);
        about = new JMenuItem("Om");
        about.addActionListener(this);
        help.add(about);
        logg = new JMenuItem("Logg");
        save = new JMenuItem("Lagre");
        exit = new JMenuItem("Avslutt og lagre");
        logg.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        fileMenu.add(logg);
        fileMenu.add(save);
        fileMenu.add(exit);
        oppdatert = 0;
   
        panelL = new JPanel();
        center = new JPanel();
        c.add(menuBar, BorderLayout.PAGE_START);
        
        c.add(panelL, BorderLayout.LINE_START);
   //     c.add(desktop, BorderLayout.CENTER);
        
     //   desktop.setVisible(true);
       // desktop.setBackground(Color.LIGHT_GRAY);
        c.setBackground(Color.DARK_GRAY);
        //Venstre menybar
        panelL.setLayout(new FlowLayout());
        panelL.add(new JLabel("AV/PÅ"));
        menuButtons = new JButton[names.length];
        for (int count = 0; count < names.length; count++)
        {
            menuButtons[ count ] = new JButton(names[count]);
            menuButtons[count].addActionListener( this );
            menuButtons[count].setBackground(Color.DARK_GRAY);
            menuButtons[count].setForeground(Color.white);
            menuButtons[count].setPreferredSize(new Dimension(125, 30));
            panelL.add(menuButtons[count]);
            toggle[count]="off";
        }
        panelL.setBackground(Color.LIGHT_GRAY);
        panelL.setLocation(0, 0);
        panelFind = new JPanel();
      
        //desktop.add(hoho);
        sokeFelt = new JTextField(20);
        panelFind.add(sokeFelt);
        sokeKnapp = new JButton("Søk");
       
        sokeKnapp.setBackground(Color.DARK_GRAY);
        sokeKnapp.setForeground(Color.white);
        panelFind.add(sokeKnapp);
    
         sokePanel = new SokeResultat();
      
        
        
        sokeKnapp.addActionListener(this);
   //     desktop.setPreferredSize(new Dimension(100,200));
       
      
       //menuBar.add(Box.createHorizontalGlue());
       //menuBar.add(Box.createHorizontalGlue());
        Dimension minSize = new Dimension(400, 4);
        Dimension prefSize = new Dimension(400, 4);
        Dimension maxSize = new Dimension(x-450, 4);
        menuBar.add(new Box.Filler(minSize, prefSize, maxSize));
        menuBar.add(panelFind);
        panelFind.setPreferredSize(new Dimension(100, 32));
        sokeFelt.setLocation(x-200, 0);
     
        panelL.setPreferredSize(new Dimension(lX, y));
            //Instansierer og skjuler paneler
            regMenu = new RegMenu(pers, kurs, rom, iReg);
            regMenu.setVisible(false);
          //  regMenu.setPreferredSize(new Dimension(40, ))
            
            bookVindu = new RomBooking(pers, rom, booka, kurs);
            bookVindu.setVisible(false);
            romPanel = new RomGUI(rom);
            romPanel.setVisible(false);
            slettPanel = new SlettGUI(pers, rom, booka, kurs, iReg);
            slettPanel.setVisible(false);
            utleieVindu = new UtleieGUI(iReg,pers,utleie);
            utleieVindu.setVisible(false);
         regMenu.setLocation(0, 0);
         center.add(regMenu);//CENTER);.
         center.add(bookVindu);
         center.add(utleieVindu);
         center.add(romPanel);
         center.add(slettPanel);
         layPane = new JLayeredPane();
         layPane.setBounds(0,0, 650, y);
         
         layPane.setPreferredSize(new Dimension(x, y));
         c.add(layPane, BorderLayout.CENTER);
        // layPane.set
         center.setLayout(new FlowLayout());
         sokePanel.setLayout(new FlowLayout());
         sokePanel.setVisible(false);
         
         center.setBounds(0,-10, 650,y);
                 //center.setLocation(0,0);
         //center.setBackground(Color.DARK_GRAY);
          sokePanel.setBounds(0,0,650,y);
        //  sokePanel.setSize(800,800);
         layPane.add(center,new Integer(0));
         layPane.add(sokePanel,new Integer(1));
       
        
       
        
}
    public JPanel getCenter()
    {
        return center;
    }
        public void registerVin() //Metode som Henter Åpner lukker Registreringsvinduet
        {
            
            if(toggle[0].equals("off"))
            {
                hideAll();
                menuButtons[0].setBackground(Color.RED);
                regMenu.setPreferredSize(new Dimension(650, 650));
                regMenu.setVisible(true);
                toggle[0] = "on";
            }
            else
            {
                hideAll();
                toggle[0] ="off";
            }
        }
        
        public void bookVindu()
        {
           
           if(toggle[2].equals("off"))
           {
                  hideAll();
                  String[] temp = rom.romStrings();
                  //bookVindu.setIdRomList(temp);
                 // bookVindu.
                menuButtons[2].setBackground(Color.RED); //Bytter farge op knapp
                bookVindu.setVisible(true);
                bookVindu.fjernGamal();
                toggle[2] = "on";
           }
           else
           {
               hideAll();
               toggle[2] = "off";
           }
 
        }
        public void utleieVindu()
        {
           
           if(toggle[3].equals("off"))
            {
                hideAll();
                menuButtons[3].setBackground(Color.RED); //Bytter farge op knapp
                utleieVindu.setVisible(true);
                toggle[3] = "on";
            }
            else
            {   
                menuButtons[3].setBackground(Color.DARK_GRAY);
                hideAll();
               toggle[3] = "off";
            } 
        }

        public void SlettVindu()
        {
           
           if(toggle[4].equals("off"))
            {
                hideAll();
                menuButtons[4].setBackground(Color.RED); //Bytter farge op knapp
                slettPanel.setVisible(true);
                toggle[4] = "on";
            }
            else
            {   
                menuButtons[4].setBackground(Color.DARK_GRAY);
                hideAll();
               toggle[4] = "off";
            } 
        }
        private void hideAll()
        {
            regMenu.setVisible(false);
            bookVindu.setVisible(false);
            slettPanel.setVisible(false);
            utleieVindu.setVisible(false);
            for(int i = 0; i < menuButtons.length; i++)
            {
                menuButtons[i].setBackground(Color.DARK_GRAY);
                toggle[i] = "off";
            }
            
        }
        //Metode for søking
        private void sok()
        {
               
               // JOptionPane.showMessageDialog(null,""+x+","+y );
           String sokestreng = sokeFelt.getText();
            sokePanel.setPreferredSize(new Dimension(800, y));
          //  hoho.setBounds
           if(sokePanel.isVisible()==false)
           { sokePanel.setVisible(true);
           // hoho.setBounds(x-300, 4, 300,y-4);
           //sokePanel.scrollElev.setVisible(false);
            //hoho.setLocation(x-300, 4);
            
     
           }
           if(sokestreng.matches("elev.*"))
           {       
               finnElever();
           }
           else if (sokestreng.matches("logg"))
           {
               JOptionPane.showMessageDialog(null, hist.visAlt());
           }
           else if(booka.checkExists(sokestreng)==true) 
           { 
               findBookByRoomId();
           }
           else{
              // sokePanel.scrollElev.setVisible(false);
               
               findByName();
          // }
           // hoho.setBounds(0, 0, 200, 200)
                    
              // hoho.pack();
              //  hoho
              //          .setVisible(true);
         }
        }
        private void findByName()
        {
           sokePanel.scrollBook.setVisible(false);
           
           sokePanel.modelElev.setRowCount(0);
           Pupil per = (Pupil)pers.finnPerson(sokeFelt.getText());
           LinkedList elevListe = (LinkedList) pers.getElevliste();
           String[] tempRow = per.getElevData();
           sokePanel.modelElev.addRow(tempRow);
           sokePanel.scrollElev.setVisible(true);
                // hoho.elevTable.setVisible(true);
           
           //sokePanel.info.setText(per.toString());
        }
        private void findBookByRoomId()
        {
          // hideSokElement();
           sokePanel.scrollElev.setVisible(false);
           sokePanel.scrollBook.setVisible(true);
           sokePanel.modelBook.setRowCount(0);
          // Rom rom = .finnPerson(sokeFelt.getText());
           String[][] bTableData = booka.getBookingTableData(); 
           for(int i = 0; i < bTableData.length; i++)
           {
               if(bTableData[i][0].equalsIgnoreCase(sokeFelt.getText()))
                   sokePanel.modelBook.addRow(bTableData[i]);
           }
    
           
                // hoho.elevTable.setVisible(true);
           
           //sokePanel.info.setText(per.toString());
        }
        
        private void finnElever()
        {
            LinkedList elevListe = (LinkedList) pers.getElevliste();
                sokePanel.scrollElev.setVisible(true);
                sokePanel.modelElev.setRowCount(0);
                {
                    for(int i = 0; i < elevListe.size(); i++)
                    {
                        Pupil temp = (Pupil) elevListe.get(i);
                        String[] tempRow = temp.getElevData();
                        sokePanel.modelElev.addRow(tempRow);
                       // hoho.elevTable.setVisible(true);
                        oppdatert = elevListe.size();
                    }
                }
        }
        private void hideSokElement()
        {
            sokePanel.scrollElev.setVisible(false);
            sokePanel.scrollBook.setVisible(false);
        }
        //Metode for å lese fra fil
        private void readFile()
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream( "src/personRegister.data" ));
                        ObjectInputStream inFile2 = new ObjectInputStream
                            (new FileInputStream("src/romliste.data"));
                        ObjectInputStream inFile3 = new ObjectInputStream
                            (new FileInputStream("src/booking.data"));
                            ObjectInputStream inFile4 = new ObjectInputStream
                                    ( new FileInputStream("src/kursliste.data"));
                            ObjectInputStream inFile5 = new ObjectInputStream
                                 ( new FileInputStream("src/instrumentliste.data")))
	    {
	      pers = (/*PersonRegister*/PersonListe) inFile.readObject();
              rom = (RomListe) inFile2.readObject();
              booka = (BookingListe) inFile3.readObject();
              kurs = (KursListe) inFile4.readObject();
              iReg = (InstrumentRegister) inFile5.readObject();
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	      //info.setText(cnfe.getMessage());
	      //info.append("\nOppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();
                iReg = new InstrumentRegister();

	    }
	    catch(FileNotFoundException fne)
	    {
	      //info.setText("Finner ikke datafil. Oppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();
                iReg = new InstrumentRegister();
	    }
	    catch(IOException ioe)
	    {
	      //info.setText("Innlesingsfeil. Oppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();
                iReg = new InstrumentRegister();

	    }
	  }
	//metode som skriver serialiserte data til fil
	public void writeToFile()
	{
		try (ObjectOutputStream outFile = new ObjectOutputStream(
				new FileOutputStream("src/personRegister.data"));
                        ObjectOutputStream outFile2 = new ObjectOutputStream(
                                new FileOutputStream("src/romliste.data"));
                        ObjectOutputStream outFile3 = new ObjectOutputStream(
                                new FileOutputStream("src/booking.data"));
                         ObjectOutputStream outFile4 = new ObjectOutputStream(
                                new FileOutputStream("src/kursliste.data"));
                        ObjectOutputStream outFile5 = new ObjectOutputStream(
                                new FileOutputStream("src/instrumentliste.data")))
		{
			outFile.writeObject(pers);
                       
                        outFile2.writeObject(rom);
                        outFile3.writeObject(booka);
                         outFile4.writeObject(kurs);
                         outFile5.writeObject(iReg);

		}
		catch( NotSerializableException nse )
		{
			//showErrorMsg("Objektet er ikke serialisert!");
                    JOptionPane.showMessageDialog(null, "Objektet er ikke serialisert!");
		}
		catch( IOException ioe )
		{
			//showErrorMsg("Problem med utskrift til fil!");
                    JOptionPane.showMessageDialog(null, "problem med utskrift til fil");
		}
	}
        //Lytteklasse for GUIs ulike knapper
        public void actionPerformed( ActionEvent event )
        {
            if (event.getSource() == menuButtons[0])
            {
                //Åpner et vindu for registrering av elever og lærere
               registerVin();
           
            }
            else if(event.getSource() == menuButtons[1])
            {
                //Åpner et vindu for å søke etter info
                 JOptionPane.showMessageDialog(null ,layPane.getLocation());
            }
            //else if(event.getSource() == menuButtons[2])
            //   romConfig();
            else if(event.getSource() == menuButtons[2])
               bookVindu();
            else if(event.getSource() == menuButtons[3])
                utleieVindu();
            else if(event.getSource() == menuButtons[4])
                SlettVindu();
            //else if(event.getSource() == menuButtons[5])
            else if(event.getSource() == logg)
            { 
                try
                {
                    JTextArea textfelt = new JTextArea();
                    
                    textfelt.append(hist.visAlt());
                    
                    JScrollPane sp = new JScrollPane(textfelt);
                    sp.setSize(100, 100);
                    JOptionPane.showMessageDialog(null,sp);
                }
                catch (NullPointerException e)
                {
                    JOptionPane.showMessageDialog(null, "null");
                }
            }
                
            else if(event.getSource() == save )
            {
                writeToFile();
                JOptionPane.showMessageDialog(null, "Data er lagret!");
            }
            else if(event.getSource() == sokeKnapp)
                sok();
            else if(event.getSource() == exit)
            {
                writeToFile();
                System.exit(0);
            }
            else if(event.getSource() == about)
                JOptionPane.showMessageDialog(null, "Prosjektoppgave i programutvikling, HioA 2014:"
                        + "\nSkapt av: Anders S. Simonsen og Joakim Tømmer");
            layout.layoutContainer( c );
            
        }
        
}
