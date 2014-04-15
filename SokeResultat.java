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
public class SokeResultat extends JPanel
{
    private Hidelytter hL;
    private JButton hide;
    public JTextArea info;
    public JPanel content;
    
    SokeResultat()
    {
        hide = new JButton("Skjul vinduet.");
        content = new JPanel();
        info = new JTextArea(20, 20);
       add(content);
       add(hide);     
        content.add(info);
        hL = new Hidelytter();
        content.setVisible(true);
        info.setVisible(true);
        setVisible(true);
       // Container c = getContentPane();
        //c.setLayout(new FlowLayout() );
        //c.add(content);
        //c.add(hide);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - getWidth();
        setLayout(new FlowLayout());
        setLocation(x, 0);
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

    

