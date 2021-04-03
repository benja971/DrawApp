package DrawApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
    private JButton BColor;
    private JButton BClear;
    private Editeur ed1;
    private Container content;

    public Interface(String name) {
        setTitle(name);
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        hotbar = new JPanel();
        menu = new JPanel();
    
        mb = new JMenuBar(); 
        file = new JMenu("File");
        home = new JMenu("Home");
        display = new JMenu("Display");

        ImageIcon point = new ImageIcon("Images/Icon_dot.png");
        ImageIcon sgmt = new ImageIcon("Images/Icon_line.png");
        ImageIcon circle = new ImageIcon("Images/Icon_circle.png");
        ImageIcon poly = new ImageIcon("Images/Icon_polygon.jpg");
        ImageIcon clear = new ImageIcon("Images/Icon_clear.jpg");
    
        BPoint = new JButton("Point", point);
        BSegmt = new JButton("Segment", sgmt);
        BCircle = new JButton("Circle", circle);
        BPolygon = new JButton("Polygon", poly);
        BColor = new JButton("Color");
        BClear = new JButton("Clear", clear);
    
        ed1 = new Editeur();
    
        content = this.getContentPane();
    
        BPoint.addActionListener(this);
        BSegmt.addActionListener(this);
        BCircle.addActionListener(this);
        BPolygon.addActionListener(this);
        BColor.addActionListener(this);
        BClear.addActionListener(this);
    
        hotbar.setLayout(new GridLayout(2, 1));
        menu.setLayout(new GridLayout());
    
        mb.add(file);
        mb.add(home);
        mb.add(display);
    
        menu.add(BPoint);
        menu.add(BSegmt);
        menu.add(BCircle);
        menu.add(BPolygon);
        menu.add(BColor);
        menu.add(BClear);
    
        ed1.addMouseListener(ed1);
        ed1.addMouseMotionListener(ed1);
    
        hotbar.add(mb); 
        hotbar.add(menu); 
    
        content.add(hotbar, BorderLayout.NORTH);
        content.add(ed1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        //Ne pas oublier de changer les layout de base des jpannels
        Interface gui = new Interface("DrawApp");
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        JButton source = (JButton)evt.getSource();
        String txt = source.getText();
        if (txt != "Color") {
            ed1.setSelectedFigure(txt);
        }
        else{  
            Color c = JColorChooser.showDialog(this,"Select a color",ed1.getInitialcolor());
            ed1.setColor(c);
        }
    }
}
