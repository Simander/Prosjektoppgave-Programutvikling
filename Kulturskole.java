
import javax.swing.JFrame;


/*
 @author anders
 */
public class Kulturskole {
    public static void main(String[] args)
    {
        PersonRegister personar = new PersonRegister();
        regVindu vindu = new regVindu(personar);
        //MasterWindow vindu = new MasterWindow();
        vindu.setVisible(true);
        vindu.setSize(500, 540);
        vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}
