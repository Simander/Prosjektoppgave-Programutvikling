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
    private PersonRegister pers;
    private BorderLayout layout;
    private Container c;
    private boolean regBooleanOn;
    JInternalFrame regVindu;
    JInternalFrame findVindu;
    private JDesktopPane desktop = new JDesktopPane();
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu help;
    private JMenuItem about;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem doc;
  
    private JPanel panelL;
    private JPanel panelReg;
    private JPanel panelFind;
    private JButton menuButtons[];
    private final String names[] = {"Registrering", "Hent info", "Påmelding",
        "Booking", "Sletting"};
 
    
    String regToggle, findToggle;
    
 public HovedVindu(PersonRegister p)
{
        super( "Kulturskolesystem 0.01");
        //Layout
        layout = new BorderLayout(5, 5);
        c = getContentPane();
        c.setLayout( layout );
        //datafelter og objekter initialiseres
        pers = p;
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
        panelL.setBackground(Color.LIGHT_GRAY);
        panelL.setLocation(0, 0);
        
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
                findVindu = new FinnVindu(pers); // Oppretter et nytt findVindu
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
        //Metode for å lese fra fil
        private void readFile()
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream( "src/personRegister.data" )))
	    {
	      pers = (PersonRegister) inFile.readObject();
	 
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	      //info.setText(cnfe.getMessage());
	      //info.append("\nOppretter tom liste.\n");
	      pers = new PersonRegister();
	  
	    }
	    catch(FileNotFoundException fne)
	    {
	      //info.setText("Finner ikke datafil. Oppretter tom liste.\n");
	      pers = new PersonRegister();
	     
	    }
	    catch(IOException ioe)
	    {
	      //info.setText("Innlesingsfeil. Oppretter tom liste.\n");
	      pers = new PersonRegister();
	    
	    }
	  }
	//metode som skriver serialiserte data til fil
	public void writeToFile()
	{
		try (ObjectOutputStream outFile = new ObjectOutputStream(
				new FileOutputStream("src/personRegister.data")))
		{
			outFile.writeObject(pers);
			
		}
		catch( NotSerializableException nse )
		{
			//showErrorMsg("Objektet er ikke serialisert!");
		}
		catch( IOException ioe )
		{
			//showErrorMsg("Problem med utskrift til fil!");
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
            else if(event.getSource() == menuButtons[2])
                JOptionPane.showMessageDialog(null, "functioning partially!");
            else if(event.getSource() == menuButtons[3])
                JOptionPane.showMessageDialog(null, "functioning partially!");
            else if(event.getSource() == menuButtons[4])
                JOptionPane.showMessageDialog(null, "functioning partially!");
            else if(event.getSource() == save )
            {
                writeToFile();
                JOptionPane.showMessageDialog(null, "Data er lagret!");
            }
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
