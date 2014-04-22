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
    private BookingList booka;
    private InstrumentRegister iReg;
    private BorderLayout layout;
    private Container c;
    private boolean regBooleanOn;
    //Panels with different content;
    private JPanel center;
    private JLayeredPane layPane;
    private JPanel regVindu;
    private JInternalFrame findVindu;
    private JInternalFrame romVindu;
    private JPanel bookVindu;
    private JPanel instrumentVindu;
 //   private JDesktopPane desktop = new JDesktopPane();
    //private JPanel desktop = new JPanel();
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu help;
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
        "Booking", "Sletting", "Utleie"};
    private String toggle[] = new String[names.length];
 
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private int x = (int)screenSize.getWidth();
        private int y = (int)screenSize.getHeight();
       int lX = 150;
       private int oppdatert;
    
    
 public HovedVindu(/*PersonRegister*/PersonListe p, RomListe r, BookingList b, InstrumentRegister ir)
{
        super( "Kulturskolesystem 0.01");
        
        //Layout
        layout = new BorderLayout(5, 5);
        c = getContentPane();
        c.setLayout( layout );
        
        //datafelter og objekter initialiseres
        pers = p;
        rom = r;
        booka = b;
        iReg = ir;
        readFile(); //leser data fra fil
        menuButtons = new JButton[names.length];
        for (int count = 0; count < names.length; count++)
        {
            menuButtons[ count ] = new JButton(names[count]);
            menuButtons[count].addActionListener( this );
            menuButtons[count].setBackground(Color.DARK_GRAY);
            menuButtons[count].setForeground(Color.white);
            menuButtons[count].setPreferredSize(new Dimension(125, 30));
            toggle[count]="off";
        }
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
        save = new JMenuItem("Lagre");
        exit = new JMenuItem("Avslutt og lagre");
        save.addActionListener(this);
        exit.addActionListener(this);
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
        panelL.add( menuButtons[0]);
        panelL.add( menuButtons[1]);
        panelL.add( menuButtons[2]);
        panelL.add( menuButtons[3]);
        panelL.add( menuButtons[4]);
      //  panelL.add( menuButtons[5]);
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
            regVindu = new GUI2(pers);
            regVindu.setVisible(false);
            
            bookVindu = new RomBooking(rom, booka);
            bookVindu.setVisible(false);
            instrumentVindu = new InstrumentGUI(iReg);
            instrumentVindu.setVisible(false);
       regVindu.setLocation(lX, y-4);
         center.add(regVindu);//CENTER);.
         center.add(bookVindu);
         center.add(instrumentVindu);
         
         layPane = new JLayeredPane();
         //layPane.setBounds(0,0, 600, 600);
         
         layPane.setPreferredSize(new Dimension(x, y));
         c.add(layPane, BorderLayout.CENTER);
         center.setLayout(new FlowLayout());
         sokePanel.setLayout(new FlowLayout());
         sokePanel.setVisible(false);
        // center.setLocation(0,0);
         
         center.setBounds(0,0, 600,y-4);
          sokePanel.setBounds(0,0,800,y-4);
          sokePanel.beholder.setSize(800,800);
         layPane.add(center,new Integer(0));
         layPane.add(sokePanel,new Integer(1));
        // layPane.setVisible(true);
         //center.setVisible(true);
         
        
       
        
}
        public void registerVin() //Metode som Henter Åpner lukker Registreringsvinduet
        {
            
            if(toggle[0].equals("off"))
            {
                hideAll();
                menuButtons[0].setBackground(Color.RED);
                regVindu.setPreferredSize(new Dimension(600, 550));
                regVindu.setVisible(true);
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
                menuButtons[2].setBackground(Color.RED); //Bytter farge op knapp
                bookVindu.setVisible(true);
                toggle[2] = "on";
           }
           else
           {
               hideAll();
               toggle[2] = "off";
           }
 
        }
        public void InstrumentVindu()
        {
           
           if(toggle[4].equals("off"))
            {
                hideAll();
                menuButtons[4].setBackground(Color.RED); //Bytter farge op knapp
                instrumentVindu.setVisible(true);
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
            regVindu.setVisible(false);
            bookVindu.setVisible(false);
            instrumentVindu.setVisible(false);
            for(int i = 0; i < menuButtons.length; i++)
            {
                menuButtons[i].setBackground(Color.DARK_GRAY);
                toggle[i] = "off";
            }
            
        }
        private void sok()
        {
               LinkedList elevListe = (LinkedList) pers.getElevliste();
               // JOptionPane.showMessageDialog(null,""+x+","+y );
           
            sokePanel.beholder.setPreferredSize(new Dimension(400, y));
          //  hoho.setBounds
           if(sokePanel.isVisible()==false)
           { sokePanel.setVisible(true);
           // hoho.setBounds(x-300, 4, 300,y-4);
           
            //hoho.setLocation(x-300, 4);
            
     
           }
           if(sokeFelt.getText().matches("elev.*"))
           {   //    finnElever();
               sokePanel.elevTable.setVisible(true);
                if(elevListe.size() > oppdatert)
                {
                    for(int i = 0; i < elevListe.size(); i++)
                    {
                        Pupil temp = (Pupil) elevListe.get(i);
                        String[] tempRow = temp.getElevData();
                        sokePanel.model.addRow(tempRow);
                       // hoho.elevTable.setVisible(true);
                        oppdatert = elevListe.size();
                    }
                }
               
           }
            else 
           { 
               sokePanel.elevTable.setVisible(false);
               
               findByName();
           }
           // hoho.setBounds(0, 0, 200, 200)
                    
              // hoho.pack();
              //  hoho
              //          .setVisible(true);
        }
        private void findByName()
        {
           Person per = pers.finnPerson(sokeFelt.getText());
           sokePanel.info.setText(per.toString());
        }
        private void finnElever()
        {
            String s = pers.listElever();
            sokePanel.info.setText(s);
        }
        //Metode for å lese fra fil
        private void readFile()
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream( "src/personRegister.data" ));
                        ObjectInputStream inFile2 = new ObjectInputStream
                            (new FileInputStream("src/romliste.data"));
                        ObjectInputStream inFile3 = new ObjectInputStream
                            (new FileInputStream("src/booking.data")))
	    {
	      pers = (/*PersonRegister*/PersonListe) inFile.readObject();
              rom = (RomListe) inFile2.readObject();
              booka = (BookingList) inFile3.readObject();

	    }
	    catch(ClassNotFoundException cnfe)
	    {
	      //info.setText(cnfe.getMessage());
	      //info.append("\nOppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();

	    }
	    catch(FileNotFoundException fne)
	    {
	      //info.setText("Finner ikke datafil. Oppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();
	    }
	    catch(IOException ioe)
	    {
	      //info.setText("Innlesingsfeil. Oppretter tom liste.\n");
	      //pers = new PersonRegister();
                pers = new PersonListe();

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
                                new FileOutputStream("src/booking.data")))
		{
			outFile.writeObject(pers);
                        outFile2.writeObject(rom);
                        outFile3.writeObject(booka);

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
                 findInfo();
            }
            //else if(event.getSource() == menuButtons[2])
            //   romConfig();
            else if(event.getSource() == menuButtons[2])
               bookVindu();
            else if(event.getSource() == menuButtons[3])
                JOptionPane.showMessageDialog(null, "functioning partially!");
            else if(event.getSource() == menuButtons[4])
                InstrumentVindu();
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
