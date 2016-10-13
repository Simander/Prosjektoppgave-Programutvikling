/*
 @Laget av:
 * Anders Simonsen s198739 
 * Joakim Tømmer s198769
 * 
 * Denne klassen er hovedbrukergrensesnittet med funksjonalitet for valg av ulike
 * sub-grensesnitt, og mulighet til å foreta søk i diverse lister, samt printing av
 * søkeresultat.
 */
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
public class HovedVindu extends JFrame
{
    private LyttMain hovedLytt;         //privat Lytteklasse
    private PersonListe pers;           //personliste
    private RomListe rom;               //romliste
    private KursListe kurs;             //kursliste
    private BookingListe booka;         //bookingliste
    private InstrumentRegister iReg;    //instrumentliste
    private UtleieListe utleie;         //utleieliste
    private Historikk hist;             //historikkliste
    private BorderLayout layout;        //Default BorderLayout

    //Hovedkomponenter
    private JPanel hovedInnhold;  //Panel som viser hovedinnhold, etter valg fra sidemeny
    private SokeResultat sokePanel; //Panel for visning av søkeresultat
    private JPanel SideBar;  //Sidebar, venstrestilt panel for menuButtons
    private JLayeredPane layPane;   //Inneholder sokePanel og center, tillater overflow,
                                    //slik at sokePanel kan vises over hovedinnhold
    
    //innhold til hovedInnhold panelet
    private RegMenu regMenu;                 //Panel som representerer et vindu for registrering av objekter   
    private KursPaaMelding paaMeldingsVindu;//Panel som representerer et vindu for kurspåmelding
    private RomBooking bookVindu;           //Panel som representerer et vindu for booking av rom
    private JPanel utleieVindu;             //Panel som representerer et vindu for utleie av instrumenter og utstyr
    private JPanel slettVindu;              //Panel som representerer et vindu for sletting av objekter
    private JPanel historikkVindu;          //Panel som representerer et vindu for historikkvisning
    
    //Menylinje topp
    private JMenuBar menyLinje;   //Menylinje på toppen av vinduet
    private JMenu fileMenu;     //Filmeny i menylinjen
    private JMenu help;         //Hjelpemeny i menylinjen 
    private JMenuItem save;     //Menyvalg i filmenyen
    private JMenuItem printSok; //menyvalg i filmenyen
    private JMenuItem exit;     //Menyvalg i filmenyen
    private JMenuItem doc;      //menyvalg i hjelpemenyen
    private JMenuItem about;    //menyvalg i hjelpemenyen
    private JPanel panelFind;   //panel i menylinja som inneholder sokeFelt og sokeKnapp
    private JPanel findsub;
    private JTextField sokeFelt;    //Søkefelt i menylinjen for input av søkeord
    private JButton sokeKnapp;      //Knapp i menylinjen som foretar et søk på søkeord
 
    //Knapper til sideBar
    private JButton menuButtons[];                      //KnappeArray for knapper i sidebar
    private final String names[] = {"Registrering", "Påmelding",
        "Booking", "Utleie", "Sletting", "Historikk"};  //tekst til knapper i knappeArrayet
    private String toggle[] = new String[names.length]; //sjekk for å vite om en knapp er av eller på
    
    //Skjermstørrelse
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Henter inn skjermstørrelse
    private int x = (int)screenSize.getWidth();     //skjermens bredde
    private int y = (int)screenSize.getHeight();    //skjremens høyde
    //forsøk på å midtstille vinduet
    private int xx = (x/100)*20;    //setter vinduets øvre,venstre hjørne 20% fra venstresida av skjermen
    private int yy = (y/100)*10;    //setter vinduets   øvre,venstre hjøren 10% fra toppen av skjermen;
       int lX = 150;
          
 public HovedVindu(PersonListe p, RomListe r, BookingListe b, KursListe k, InstrumentRegister ir, UtleieListe ut, Historikk h)
{
        super( "Kulturskolesystem 1.0"); //Vinduets overskrift
        hovedLytt = new LyttMain(); //initialiserer lytteklassen
        //Setter layout for vinduet
        layout = new BorderLayout(5, 5);
        Container c = getContentPane();
        c.setLayout( layout );
        setLocation(xx,yy);
        //Initialiserer lister
        pers = p;
        rom = r;
        booka = b;
        kurs = k;
        iReg = ir;
        utleie = ut;
        hist = h;
        
        readFile(); //leser data fra fil

        //Menylinje elementer
        fileMenu = new JMenu("Fil");            //initialiserer submeny "Fil"
        help = new JMenu("Hjelp");              //initialiserer submeny "Hjelp" 
        menyLinje = new JMenuBar();               //initialiserer menylinjen
        menyLinje.add(fileMenu);                  //legger submeny fil til menylinjen
        menyLinje.add(help);                      //legger submeny hjelp til menylinjen
        doc = new JMenuItem("Dokumentasjon");   //initialiserer menyvalg
        about = new JMenuItem("Om");            //initialiserer menyvalg
        help.add(doc);                          //legger til menyvalg i "Hjelp" menyen
        help.add(about);                        //legger til menyvalg i "Hjelp" menyen
        save = new JMenuItem("Lagre");                  //initialiserer menyvalg
        exit = new JMenuItem("Avslutt og lagre");       //initialiserer menyvalg
        printSok = new JMenuItem("Print søkeresultat");//initialiserer menyvalg
        fileMenu.add(printSok); //legger til menyvalg i "Fil" menyen
        fileMenu.add(save);     //legger til menyvalg i "Fil" menyen
        fileMenu.add(exit);     //legger til menyvalg i "Fil" menyen
        save.addActionListener(hovedLytt);      //legger til lytter til menyvalg
        exit.addActionListener(hovedLytt);      //legger til lytter til menyvalg
        printSok.addActionListener(hovedLytt);  //legger til lytter til menyvalg
        about.addActionListener(hovedLytt);     //legger til lytter til menyvalg
        doc.addActionListener(hovedLytt);
        //søkerelaterte komponenter 
        panelFind = new JPanel();       //initialiserer panelFind
        findsub = new JPanel();
        sokeFelt = new JTextField(20);  //initialiserer sokeFelt
        sokeFelt.addActionListener(hovedLytt);
        sokeKnapp = new JButton("Søk"); //initialiserer sokeKnapp
        sokeKnapp.setBackground(Color.DARK_GRAY);   //Setter farger på sokeKnapp
        sokeKnapp.setForeground(Color.white);       //Setter farger på sokeKnapp
        panelFind.add(sokeFelt);        //legger til sokeFelt til panelFind
        panelFind.add(sokeKnapp);       //legger til sokKnapp til panelFind
        //dimensioner til filler for menylinje
        Dimension minSize = new Dimension(400, 4);
        Dimension prefSize = new Dimension(400, 4);
        Dimension maxSize = new Dimension(x-450, 4);
        menyLinje.add(new Box.Filler(minSize, prefSize, maxSize));    //legger filler til menylinje
        menyLinje.add(findsub);                                     //legger panelFind til menylinje
        findsub.setLayout(new BorderLayout());
        findsub.add(panelFind, BorderLayout.LINE_END); 
        sokePanel = new SokeResultat();
         
      
        
        
        sokeKnapp.addActionListener(hovedLytt);
        
        //Hovedkomponenter 
        SideBar = new JPanel();         //initialiserer SideBar panelet
        hovedInnhold = new JPanel();    //initialiserer hovedInnhold panelet

        //SideBars komponenter
        SideBar.setLayout(new FlowLayout());
        SideBar.add(new JLabel("Valg:"));
        menuButtons = new JButton[names.length];
        //initialiserer menuButtons og toggle(switches)
        for (int count = 0; count < names.length; count++)
        {
            menuButtons[ count ] = new JButton(names[count]);
            menuButtons[count].addActionListener( hovedLytt );
            menuButtons[count].setBackground(Color.DARK_GRAY);
            menuButtons[count].setForeground(Color.white);
            menuButtons[count].setPreferredSize(new Dimension(125, 30));
            SideBar.add(menuButtons[count]);
            toggle[count]="off";
        }
        SideBar.setBackground(Color.LIGHT_GRAY);
        SideBar.setLocation(0, 0);
        
        findsub.setPreferredSize(new Dimension(120, 32));
     
        SideBar.setPreferredSize(new Dimension(lX, y));
            //Instansierer og skjuler paneler
            regMenu = new RegMenu(pers, kurs, rom, iReg, booka, hist);
            regMenu.setVisible(false);    
            bookVindu = new RomBooking(pers, rom, booka, kurs, hist);
            bookVindu.setVisible(false);
            slettVindu = new SlettGUI(pers, rom, booka, kurs, iReg, hist);
            slettVindu.setVisible(false);
            utleieVindu = new UtleieGUI(iReg,pers,utleie,hist);
            utleieVindu.setVisible(false);
            historikkVindu = new HistorikkGUI(hist, null);
            historikkVindu.setVisible(false);
            paaMeldingsVindu = new KursPaaMelding(kurs, pers, hist);
            paaMeldingsVindu.setVisible(false);
            
         regMenu.setLocation(0, 0);
         //legger til paneler til hovedInnhold
         hovedInnhold.add(regMenu);
         hovedInnhold.add(bookVindu);
         hovedInnhold.add(utleieVindu);
         hovedInnhold.add(slettVindu);
         hovedInnhold.add(historikkVindu);
         hovedInnhold.add(paaMeldingsVindu);
         layPane = new JLayeredPane();
         layPane.setBounds(0,0, 850, y);
         
         layPane.setPreferredSize(new Dimension(x, y));
         c.add(menyLinje, BorderLayout.PAGE_START); 
         c.add(SideBar, BorderLayout.LINE_START);
         c.setBackground(Color.DARK_GRAY);
         c.add(layPane, BorderLayout.CENTER);
         
         hovedInnhold.setLayout(new FlowLayout());
         sokePanel.setLayout(new FlowLayout());
         sokePanel.setVisible(false);
         
         hovedInnhold.setBounds(0,-10, 850,y);
    
          sokePanel.setBounds(0,0,850,y);
         layPane.add(hovedInnhold,new Integer(0));  //Setter hovedInnhold til nederste lag
         layPane.add(sokePanel,new Integer(1));     //Setter sokePanel til øverste lag
       
        
       
        
}
        public void registerVin() //Metode som Viser/Skjuler Registreringsvinduet
        {
            
            if(toggle[0].equals("off"))
            {
                hideAll();
                regMenu.hideAllRegWindows();
                menuButtons[0].setBackground(Color.RED);
                regMenu.setPreferredSize(new Dimension(850, 650));
                regMenu.setVisible(true);
                toggle[0] = "on";
            }
            else
            {
                hideAll();
                toggle[0] ="off";
            }
        }
        public void PaaMeldingVin() //Metode som Henter Åpner lukker Registreringsvinduet
        {
            
            if(toggle[1].equals("off"))
            {
                hideAll();
                
                menuButtons[1].setBackground(Color.RED);
                paaMeldingsVindu.setPreferredSize(new Dimension(650, 650));
                paaMeldingsVindu.setVisible(true);
                paaMeldingsVindu.updateComboBox();
                toggle[1] = "on";
            }
            else
            {
                hideAll();
                toggle[1] ="off";
            }
        }
        public void bookVindu() //Metode som viser/skjuler bookingVindu 
        {
           
           if(toggle[2].equals("off"))
           {
                  hideAll();
                  String[] temp = rom.romStrings();
                  //bookVindu.setIdRomList(temp);
                 // bookVindu.
                bookVindu.updateComboBoxes();
                bookVindu.updateCombo2();
                menuButtons[2].setBackground(Color.RED); //Bytter farge op knapp
                bookVindu.setVisible(true);
                bookVindu.revalidate();
                bookVindu.repaint();
                bookVindu.fjernGamal();
                toggle[2] = "on";
           }
           else
           {
               hideAll();
               toggle[2] = "off";
           }
 
        }
        public void utleieVindu()   //Metode som viser/skjuler utleieVindu
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

        public void SlettVindu()    //Metode som viser/skjuler SlettVindu
        {
           
           if(toggle[4].equals("off"))
            {
                hideAll();
                menuButtons[4].setBackground(Color.RED); //Bytter farge op knapp
                slettVindu.setVisible(true);
                toggle[4] = "on";
            }
            else
            {   
                menuButtons[4].setBackground(Color.DARK_GRAY);
                hideAll();
               toggle[4] = "off";
            } 
        }
        public void historikkVindu()    //Metode som skjuler/viser historikkVindu
        {
           
           if(toggle[5].equals("off"))
            {
                hideAll();
                menuButtons[5].setBackground(Color.RED); //Bytter farge op knapp
                historikkVindu.setVisible(true);
                toggle[5] = "on";
            }
            else
            {   
                menuButtons[5].setBackground(Color.DARK_GRAY);
                hideAll();
               
                toggle[5] = "off";
            } 
        }
        private void hideAll()  //Metode som skjuler alle vinduer
        {
            regMenu.setVisible(false);
            bookVindu.setVisible(false);
            slettVindu.setVisible(false);
            utleieVindu.setVisible(false);
            historikkVindu.setVisible(false);
            paaMeldingsVindu.setVisible(false);
            for(int i = 0; i < menuButtons.length; i++)
            {
                menuButtons[i].setBackground(Color.DARK_GRAY);
                toggle[i] = "off";
            }
            
        }
        
        private void sok()  //Metode for søking
        {   
            String sokestreng = sokeFelt.getText();
            //sokePanel.setPreferredSize(new Dimension(800, y));
            if(sokePanel.isVisible()==false)
            { 
                sokePanel.setVisible(true);              
            }
            
            sokePanel.hideAllSok();
            if(sokestreng.matches("elev.*")) //Hvis søkeordet er lik elev.*
            {       
                if(pers.getElevliste().size() > 0)
                    finnElever();
                else
                {
                    sokePanel.info.setText("Søket fant ga ingen treff, det finnes ingen elever i registeret!");
                    sokePanel.info.setVisible(true);
                }   
            }
            else if(sokestreng.matches("lærer.*")) //Hvis søkeordet er lærer.*
            {
                if(pers.getLaererListe().size() > 0)
                    finnLaerere();
                else
                {
                    sokePanel.info.setText("Søket fant ga ingen treff, det finnes ingen lærere i registeret!");
                    sokePanel.info.setVisible(true);
                }   
            }
            else if (sokestreng.matches("logg")) //om søkeordet er logg
            {
                JOptionPane.showMessageDialog(null, hist.visAlt());
            }
            else if(iReg.findInstrument(sokestreng)!=null) //dersom søkeordet er lik id-nr til et av instrumentene i registeret
            {
                finnInstrument(sokestreng);
            }
            else if(rom.finnRom(sokestreng) != null)    //dersom søkeordet er lik id til et av rommene i registeret
            {
                if(booka.checkExists(sokestreng)==true) //dersom det eksisterer en booking på rommet
                    findBookByRoomId();
                else    //Dersom der ikke finnes en booking
                {
                    sokePanel.info.setText("Søket fant ingen bookinger på valgt rom");
                    sokePanel.info.setVisible(true);
                }
            }
            else if(checkgrup() == true)
                findByGruppe();
            else    //Ellers leter etter personer med riktig navn.
            {
                if(pers.finnPersonID(sokestreng)!=null)
                {
                    sokePanel.info.setText(pers.finnPersonID(sokestreng).toString());
                    sokePanel.info.setVisible(true);
                }
                else if(pers.finnPerson(sokestreng) != null)
                    findByName();
                else if(pers.sjekkPerson(sokestreng)==true)
                    findByName2();
                else
                {
                    sokePanel.info.setText("Søket returnerte ingen treff!");
                    sokePanel.info.setVisible(true);
                }
                    
            }
        }
        private void findByName()   //Søkemetode som returnerer et tekstfelt med .toString() for en person med riktig navn.
        {
           sokePanel.hideAllSok();
           //sokePanel.modelElev.setRowCount(0);
           Person per = pers.finnPerson(sokeFelt.getText());
          // LinkedList elevListe = (LinkedList) pers.getElevliste();
           sokePanel.info.setText(per.toString());
           sokePanel.info.setVisible(true);
           //String[] tempRow = per.getElevData();
           //sokePanel.modelElev.addRow(tempRow);
           //sokePanel.scrollElev.setVisible(true);
                // hoho.elevTable.setVisible(true);
           
           //sokePanel.info.setText(per.toString());
        }
                private void findByName2()   //Søkemetode som returnerer et tekstfelt med .toString() for en person med riktig navn.
        {
                String sokString = sokeFelt.getText().toLowerCase();
                LinkedList elevListe = (LinkedList) pers.getElevliste();
                LinkedList laererListe = (LinkedList) pers.getLaererListe();
                sokePanel.scrollElev.setVisible(true);
                sokePanel.modelElev.setRowCount(0);
                {
                    for(int i = 0; i < elevListe.size(); i++)
                    {
                        Pupil temp = (Pupil) elevListe.get(i);
                        if (temp.getName().toLowerCase().contains(sokString))
                        {   
                            String[] tempRow = temp.getElevData();
                            sokePanel.modelElev.addRow(tempRow);
                        }
                    }
                    for(int i = 0; i < laererListe.size(); i++)
                    {
                        Teacher temp = (Teacher)laererListe.get(i);
                        if (temp.getName().toLowerCase().contains(sokString))
                        {   
                            String[] tempRow = temp.getLaererData();
                            sokePanel.modelElev.addRow(tempRow);
                        }
                    }
                }
        }
        private void findBookByRoomId() //Søkemetode som returnerer en tabell med bookinger for riktig rom
        {
          // hideSokElement();
           sokePanel.hideAllSok();
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
        private boolean checkgrup() //Metode som sjekker om sokeFelt.getText() er en gruppeID
        {    
            LinkedList<Booking> books = booka.getBooka();
            for(int i = 0; i < books.size(); i++)
            {
                if(books.get(i).getGrup().equalsIgnoreCase(sokeFelt.getText()))
                    return true;
            }
            return false;
        }
        private void findByGruppe() //Søkemetode som viser en tabell med bookinger for riktig gruppe
        {
          // hideSokElement();
           sokePanel.hideAllSok();
           sokePanel.scrollBook.setVisible(true);
           sokePanel.modelBook.setRowCount(0);
          // Rom rom = .finnPerson(sokeFelt.getText());
           String[][] bTableData = booka.getBookingTableData(); 
           for(int i = 0; i < bTableData.length; i++)
           {
               if(bTableData[i][5].equalsIgnoreCase(sokeFelt.getText()))
                   sokePanel.modelBook.addRow(bTableData[i]);
           }
        }
        private void finnElever()   //Søkemetode for å liste opp alle elever i tabell i sokePanelet
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
                    }
                }
        }
        private void finnLaerere()  //Søkemetode for å liste opp alle lærerer i tabell i sokePanelet
        {         
            LinkedList laererListe = (LinkedList) pers.getLaererListe();
                sokePanel.scrollTeach.setVisible(true);
                sokePanel.modelTeach.setRowCount(0);
                {
                    for(int i = 0; i < laererListe.size(); i++)
                    {
                        Teacher temp = (Teacher) laererListe.get(i);
                        String[] tempRow = temp.getLaererData();
                        sokePanel.modelTeach.addRow(tempRow);
                     
                    }
                }
        }
        public void finnInstrument(String n)
        {
            Instrument inst = iReg.findInstrument(n);
            sokePanel.info.setText(inst.toString());
            sokePanel.info.setVisible(true);
        }
        public void printSok()  //Metode som printer søkeResultat
        {
            boolean complete = false;
            if(sokePanel.isVisible())
            {
                try{
                
                    if(sokePanel.info.isVisible())
                        complete = sokePanel.info.print();
                    else if(sokePanel.scrollElev.isVisible())
                        complete = sokePanel.elevTable.print();
                    else if(sokePanel.scrollTeach.isVisible())
                        complete = sokePanel.teachTable.print();
                    else if(sokePanel.scrollBook.isVisible())
                        complete = sokePanel.bookingTable.print();
                    if(complete)
                    {
                        JOptionPane.showMessageDialog(null, "Done printing", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Printing!", "Printer", JOptionPane.ERROR_MESSAGE);
                    }
                catch(PrinterException e) 
                {
                    JOptionPane.showMessageDialog(null, e);
                }
        
            }
            else
                JOptionPane.showMessageDialog(null, "Du må foreta et søk før du kan printe resultatet!", "Feil: ingenting å printe!", JOptionPane.ERROR_MESSAGE);
        }
        public void getDocumentation()
        {
             try {
 
		File pdfFile = new File("bruksanvisning.pdf");
		if (pdfFile.exists()) {
 
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				JOptionPane.showMessageDialog(null, "Finner ikke dokumentasjon!", "Feil!", JOptionPane.ERROR_MESSAGE);
			}
 
		} else {
			JOptionPane.showMessageDialog(null, "Finner ikke dokumentasjon!", "Feil!", JOptionPane.ERROR_MESSAGE);
		}
 
		
 
	  } catch (Exception ex) {
		ex.printStackTrace();
	  }
 
	
        }
        private void readFile()//Metode for å lese fra fil
	{
		try (ObjectInputStream inFile = new ObjectInputStream(
	            new FileInputStream( "Register.data" )))
	    {
	      pers = (/*PersonRegister*/PersonListe) inFile.readObject();
              rom = (RomListe) inFile.readObject();
              booka = (BookingListe) inFile.readObject();
              kurs = (KursListe) inFile.readObject();
              iReg = (InstrumentRegister) inFile.readObject();
	    }
	    catch(ClassNotFoundException cnfe)
	    {
                pers = new PersonListe();
                iReg = new InstrumentRegister();
                booka = new BookingListe();
                rom = new RomListe();
                kurs = new KursListe();

	    }
	    catch(FileNotFoundException fne)
	    {
	        pers = new PersonListe();
                iReg = new InstrumentRegister();
                rom = new RomListe();
                kurs = new KursListe();
	    }
	    catch(IOException ioe)
	    {
                pers = new PersonListe();
                iReg = new InstrumentRegister();
                rom = new RomListe();
                kurs = new KursListe();

	    }
	  }
	public void writeToFile()//metode som skriver serialiserte data til fil
	{
		try (ObjectOutputStream outFile = new ObjectOutputStream(
				new FileOutputStream("Register.data")))
		{
			outFile.writeObject(pers);
                        outFile.writeObject(rom);
                        outFile.writeObject(booka);
                        outFile.writeObject(kurs);
                        outFile.writeObject(iReg);

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
         private class LyttMain implements ActionListener
        {
            public void actionPerformed( ActionEvent event )
            {
                if (event.getSource() == menuButtons[0])
                {
                    registerVin();
                }
                else if(event.getSource() == menuButtons[1])
                {
                    PaaMeldingVin();
                }
                else if(event.getSource() == menuButtons[2])
                    bookVindu();
                else if(event.getSource() == menuButtons[3])
                    utleieVindu();
                else if(event.getSource() == menuButtons[4])
                    SlettVindu();
                else if(event.getSource() == menuButtons[5])
                    historikkVindu();
                else if(event.getSource() == printSok)
                {
                    printSok();
                }    
                else if(event.getSource() == save )
                {
                    writeToFile();
                    JOptionPane.showMessageDialog(null, "Data er lagret!");
                }
                else if(event.getSource() == sokeKnapp)
                    sok();
                else if(event.getSource() == sokeFelt)
                    sok();
                else if(event.getSource() == exit)
                {
                    writeToFile();
                    System.exit(0);
                }
                else if(event.getSource() == doc)
                {
                    getDocumentation();
                }
                else if(event.getSource() == about)
                    JOptionPane.showMessageDialog(null, "Prosjektoppgave i programutvikling, HioA 2014:"
                        + "\nSkapt av: Anders S. Simonsen og Joakim Tømmer");
            
            }
         }
        
}
