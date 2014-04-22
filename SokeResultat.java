/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public JPanel beholder;
    
    public DefaultTableModel model = new DefaultTableModel(); 
    public JTable elevTable;
    JScrollPane scrollmo;
    
    SokeResultat()
    {
        model.addColumn("ElevId:");
        model.addColumn("Navn:");
        model.addColumn("Adresse:");
        model.addColumn("Tlf:");
        model.addColumn("Instrument:");
        elevTable = new JTable(model);
       // elevTable.setPreferredScrollableViewportSize(new Dimension(40,40));
        //elevTable.setPreferredSize(new Dimension(600,600));
        hide = new JButton("Skjul vinduet.");
        beholder = new JPanel();
        info = new JTextArea(10, 40);
       add(beholder);
         
        beholder.add(info);
        add(hide);
        beholder.setLayout(new FlowLayout());
        
        beholder.setBackground(Color.DARK_GRAY);
        hL = new Hidelytter();
        beholder.setVisible(true);
        info.setVisible(true);
        setVisible(true);
       // Container c = getContentPane();
        //c.setLayout(new FlowLayout() );
        //c.add(content);
        //c.add(hide);
        setLayout(new FlowLayout());
      beholder.setPreferredSize(new Dimension(600, 400));
        setBackground(Color.DARK_GRAY);
       scrollmo = new JScrollPane(elevTable);
       //scrollmo.setPreferredSize(new Dimension(800,800));
        beholder.add(scrollmo);
        //content.setPreferredSize(new Dimension(600,600));
        elevTable.setVisible(false);
        info.setVisible(false);
     //   elevTable.setSize(400, 600);
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

    
