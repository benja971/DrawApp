import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
// 
/**
 * Interface
 */
public class Interface extends JFrame {

    private static final long serialVersionUID = 1L;
    
    public Interface(String name) {
        setTitle(name);
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(new GridLayout(5, 0, 15, 15));
        setSize((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame gui = new Interface("DrawApp");
        JPanel menu = new JPanel();
        JPanel tst = new JPanel();
        JTabbedPane ctrlBar = new JTabbedPane();
        Editeur ed1 = new Editeur();
        JComponent fichier = new JMenu("fichier");
        JComponent acceuil = new JMenu("acceuil");
        JComponent affichage = new JMenu("affichage");
        JColorChooser colorsC = new JColorChooser();

        menu.setLayout(new GridLayout(1, 3));
        menu.setBounds(0, 0, (int)gui.getSize().getWidth(), (int)gui.getSize().getHeight()/10);
        menu.setBackground(Color.green);

        tst.setBackground(Color.red);
        tst.setBounds(0, 0, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/10);
        
        menu.add(fichier); 
        menu.add(acceuil);
        menu.add(affichage);
        gui.add(menu);
        gui.add(tst);

        // gui.add(ed1);

        gui.setVisible(true);
    }

}
