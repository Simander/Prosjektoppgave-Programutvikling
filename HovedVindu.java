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
    JInternalFrame regVindu;
    JInternalFrame findVindu;
    JInternalFrame romVindu;
    JInternalFrame bookVindu;
    JInternalFrame instrumentVindu;
    private JDesktopPane desktop = new JDesktopPane();
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
    private JPanel panelReg;
    private JPanel panelFind;
    private SokeResultat hoho;
    private JButton sokeKnapp;
    private JButton show;
    private JButton menuButtons[];
    private final String names[] = {"Registrering", "Hent info", "Påmelding",
        "Booking", "Sletting", "Utleie"};
 
    
    String regToggle, findToggle, romToggle, bookToggle, instrumentToggle;
    
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
        menuButtons = new JButton[names.length];
        for (int count = 0; count < names.length; count++)
        {
            menuButtons[ count ] = new JButton(names[count]);
            menuButtons[count].addActionListener( this );
            menuButtons[count].setBackground(Color.DARK_GRAY);
            menuButtons[count].setForeground(Color.white);
             menuButtons[count].setPreferredSize(new Dimension(120, 30));
        }
        //Meny greier
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Fil");
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
        regToggle = "off";
        findToggle = "off";
        romToggle = "off";
        bookToggle = "off";
        instrumentToggle = "off";
        save.addActionListener(this);
        exit.addActionListener(this);
        fileMenu.add(save);
        fileMenu.add(exit);
       
   
        panelL = new JPanel();
        
        c.add(menuBar, BorderLayout.PAGE_START);
        
        c.add(panelL, BorderLayout.LINE_START);
        c.add(desktop, BorderLayout.CENTER);
        
        desktop.setVisible(true);
        desktop.setBackground(Color.LIGHT_GRAY);
        c.setBackground(Color.DARK_GRAY);
        panelL.setLayout(new FlowLayout());
        panelL.add(new JLabel("AV/PÅ"));
        panelL.add( menuButtons[0]);
        panelL.add( menuButtons[1]);
        panelL.add( menuButtons[2]);
        panelL.add( menuButtons[3]);
        panelL.add( menuButtons[4]);
        panelL.add( menuButtons[5]);
        panelL.setBackground(Color.LIGHT_GRAY);
        panelL.setLocation(0, 0);
        panelFind = new JPanel();
      
        //desktop.add(hoho);
        sokeFelt = new JTextField(15);
        panelFind.add(sokeFelt);
        sokeKnapp = new JButton("Søk");
        show = new JButton("+");
        sokeKnapp.setBackground(Color.DARK_GRAY);
        sokeKnapp.setForeground(Color.white);
        panelFind.add(sokeKnapp);
        panelFind.add(show);
         hoho = new SokeResultat();
         hoho.setVisible(false);
        
        sokeKnapp.addActionListener(this);
        show.addActionListener(this);
        desktop.setPreferredSize(new Dimension(100,200));
        panelFind.setPreferredSize(new Dimension(32, 32));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(panelFind);
        panelL.setPreferredSize(new Dimension(150, 100));
      
       
        readFile();
}
        public void registerVin() //Metode som Henter Åpner lukker Registreringsvinduet
        {
            
            if(regToggle.equals("off"))
            {
                menuButtons[0].setBackground(Color.RED);
                regVindu = new GUI2(pers);
                desktop.add(regVindu);
                regVindu.isMaximum();
                regVindu.setLocation(0, 0);
               
                //regVindu.addInternalFrameListener();
                regVindu.pack();
                regVindu.setVisible(true);
                regToggle = "on";
            }
            else
            {   
                menuButtons[0].setBackground(Color.DARK_GRAY);
                regVindu.dispose();
                regToggle = "off";
            }
        }
        public void findInfo()
        {
           
           if(findToggle.equals("off"))
            {
                menuButtons[1].setBackground(Color.RED); //Bytter farge op knapp
                findVindu = new FinnVindu(pers, booka); // Oppretter et nytt findVindu
                desktop.add(findVindu); //Legger til i desktopPane
                findVindu.isMaximum();
                findVindu.setLocation(0, 0);
               
                //regVindu.addInternalFrameListener();
                findVindu.pack();
                findVindu.setVisible(true);
                findToggle = "on";
            }
            else
            {   
                menuButtons[1].setBackground(Color.DARK_GRAY);
                findVindu.dispose(); //Kaster findVindu.
                findToggle = "off";
            } 
        }
       /* public void romConfig()
        {
           
           if(romToggle.equals("off"))
            {
                menuButtons[2].setBackground(Color.RED); //Bytter farge op knapp
                romVindu = new Booking(rom); // Oppretter et nytt findVindu
                desktop.add(romVindu); //Legger til i desktopPane
                romVindu.isMaximum();
                romVindu.setLocation(0, 0);
               
                //regVindu.addInternalFrameListener();
                romVindu.pack();
                romVindu.setVisible(true);
                romToggle = "on";
            }
            else
            {   
                menuButtons[2].setBackground(Color.DARK_GRAY);
                romVindu.dispose(); //Kaster findVindu.
               romToggle = "off";
            } 
        }*/
        public void bookVindu()
        {
           
           if(bookToggle.equals("off"))
            {
                menuButtons[3].setBackground(Color.RED); //Bytter farge op knapp
                bookVindu = new RomBooking(rom, booka); // Oppretter et nytt findVindu
                desktop.add(bookVindu); //Legger til i desktopPane
                bookVindu.isMaximum();
                bookVindu.setLocation(0, 0);
               
                //regVindu.addInternalFrameListener();
                bookVindu.pack();
                bookVindu.setVisible(true);
                bookToggle = "on";
            }
            else
            {   
                menuButtons[3].setBackground(Color.DARK_GRAY);
                bookVindu.dispose(); //Kaster findVindu.
               bookToggle = "off";
            } 
        }
        public void InstrumentVindu()
        {
           
           if(instrumentToggle.equals("off"))
            {
                menuButtons[5].setBackground(Color.RED); //Bytter farge op knapp
                instrumentVindu = new InstrumentGUI(iReg); // Oppretter et nytt findVindu
                desktop.add(instrumentVindu); //Legger til i desktopPane
                instrumentVindu.isMaximum();
                instrumentVindu.setLocation(0, 0);
               
                //regVindu.addInternalFrameListener();
                instrumentVindu.pack();
                instrumentVindu.setVisible(true);
                instrumentToggle = "on";
            }
            else
            {   
                menuButtons[5].setBackground(Color.DARK_GRAY);
                instrumentVindu.dispose(); //Kaster findVindu.
               instrumentToggle = "off";
            } 
        }
        private void sok()
        {
           if(hoho.isVisible()==false)
           { hoho.setVisible(true);
            c.add(hoho, BorderLayout.LINE_END);
     
           }
           findByName();
            //desktop.add(hoho);
           
           // hoho.setBounds(0, 0, 200, 200)
                    
              // hoho.pack();
              //  hoho
              //          .setVisible(true);
        }
        private void findByName()
        {
           Person per = pers.finnPerson(sokeFelt.getText());
           hoho.info.setText(per.toString());
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
            else if(event.getSource() == menuButtons[3])
               bookVindu();
            else if(event.getSource() == menuButtons[4])
                JOptionPane.showMessageDialog(null, "functioning partially!");
            else if(event.getSource() == menuButtons[5])
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
        
}
