import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * Interface
 */
public class Interface extends JFrame implements ActionListener {

    private Dimension dimension;
    private JPanel hotbar;
    private JPanel menu;
    private JMenuBar mb;
    private JMenu file;
    private JMenu home;
    private JMenu display;
    private JButton BPoint;
    private JButton BSegmt;
    private JButton BCircle;
    private JButton BPolygon;
    private JButton Color;
    private Editeur ed1;
    private JColorChooser colorsC;
    private Container content;

    public Interface(String name) {
        setTitle(name);
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hotbar = new JPanel();
        menu = new JPanel();
    
        mb = new JMenuBar(); 
        file = new JMenu("File");
        home = new JMenu("Home");
        display = new JMenu("Display");
    
        BPoint = new JButton("Point");
        BSegmt = new JButton("Segment");
        BCircle = new JButton("Circle");
        BPolygon = new JButton("Polygon");
        Color = new JButton("Color");
    
        ed1 = new Editeur();
    
        colorsC = new JColorChooser();
        
        content = this.getContentPane();
        
        BPoint.addActionListener(this);
        BSegmt.addActionListener(this);
        BCircle.addActionListener(this);
        BPolygon.addActionListener(this);
    
        hotbar.setLayout(new GridLayout(2, 1));
        menu.setLayout(new GridLayout());
    
        mb.add(file);
        mb.add(home);
        mb.add(display);
    
        menu.add(BPoint);
        menu.add(BSegmt);
        menu.add(BCircle);
        menu.add(BPolygon);
        menu.add(Color);
    
        ed1.addMouseListener(ed1);
    
        hotbar.add(mb); 
        hotbar.add(menu); 
    
        content.add(hotbar, BorderLayout.NORTH);
        content.add(ed1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        //Ne pas oublier de chnager les layout de base des jpannels
        Interface gui = new Interface("DrawApp");
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        JButton source = (JButton)evt.getSource();
        ed1.setSelectedFigure(source.getText());
    }
}
