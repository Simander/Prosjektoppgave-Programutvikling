/**
 *
 * @author simander
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SokeResultat extends JPanel
{
    private Hidelytter hL;
    private JButton hide;
    public JTextArea info;
    public JPanel content;
    
         DefaultTableModel model = new DefaultTableModel(); 
        JTable elevTable = new JTable(model); 
  
    
    SokeResultat()
    {
        hide = new JButton("Skjul vinduet.");
        content = new JPanel();
        info = new JTextArea(20, 20);
       add(content);
         
        content.add(info);
        content.add(hide);
        content.setLayout(new FlowLayout());
        content.setPreferredSize(new Dimension(600,600));
        content.setBackground(Color.DARK_GRAY);
        hL = new Hidelytter();
        content.setVisible(true);
        info.setVisible(true);
        setVisible(true);
       // Container c = getContentPane();
        //c.setLayout(new FlowLayout() );
        //c.add(content);
        //c.add(hide);
        setLayout(new FlowLayout());
        //setPreferredSize(new Dimension(400, 600));
      
        model.addColumn("ElevId:");
        model.addColumn("Navn:");
        model.addColumn("Adresse:");
        model.addColumn("Tlf:");
        model.addColumn("Instrument:");
        content.add(new JScrollPane(elevTable));
        elevTable.setVisible(false);
        hide.addActionListener(hL);
    }
     private class Hidelytter implements ActionListener
    {
        public void actionPerformed (ActionEvent eF )
        {
            if (eF.getSource() == hide)
                setVisible(false);

         
        }
    }
}
