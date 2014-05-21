/**
 *
 * @author joakim
 */
//import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import javax.swing.*;

public class Historikk implements Serializable
{
    public Date timestamp; // Kalender
    public String stamp;
    public String objekt;
    public String action;
    public String navn;
    public String everything;
    public SimpleDateFormat kFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    HistorikkGUI histg;
    Historikk hist;
   
   
    public void writeToFile(String p)
	{
                
		try (BufferedWriter out = new BufferedWriter(new FileWriter("historikk.txt", true)))
		{
                   out.write(p);    
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
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "something wrong");
                }
	}
    
    public String entry (String o,String a,String n ) //o = objektet, a = action
    {
        //readFile();
        String print;
        //objekt = o;
        //action = a;
        //navn = n;
        timestamp = new Date();
        stamp = kFormat.format(timestamp);
        
        if(o.equals("") && a.equals(""))
        {
            print ="";
        }
        else if (!(o.equals("") && a.equals("")))
        {
            print = stamp + " || " + o + " || " + a + " || " + n + "\n";
            writeToFile(print);
        }
        else
            print = "";
            
        return print;
    }
        public String[][] visAlt()
    {
            
            int i = 0;
            int k = 0;
            String print = "";
            
        try(BufferedReader les = new BufferedReader(new FileReader("historikk.txt")))
        {
           
            String[] parts = new String[4];
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader tell = new BufferedReader(new FileReader("historikk.txt"));
            String count;
            while (( count = tell.readLine()) != null)
            {
                k++;
            }
            tell.close();
            
            String rowData[][] = new String[k][4];
            
     
            while( (line = les.readLine()) != null)
            {
                
                
                        parts = line.split(" \\|\\| ");
                 
                                rowData[i] = parts;
                                 i++;
                   
                
                
            }
            //rowData = new String [i][4];
            
            les.close();
            return rowData;
        }
            catch (FileNotFoundException exception)
            {
                return null; //"Det er ikke gjort noen aktivitet enda. File Not Found";
            }
            catch (IOException ex)
            {
                return null; //"Noe gikk galt.";
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
           // JOptionPane.showMessageDialog(null, "NullPointer Exception! noe gikk galt.");
            }
            //if (print.equals(""))
            //{
            //    print = "Finner ingen oppføringer på valgt dato \n " + d;
            //}
            return null;
           
    }
        
   
    
}
