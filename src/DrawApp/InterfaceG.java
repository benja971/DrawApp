package DrawApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Interface
 */
public class InterfaceG extends JFrame implements ActionListener {

    private Dimension dimension;
    private JPanel hotbar, menuL1, menuL2;
    private JMenuBar mb;
    private JToolBar tb;
    private JMenu file, home, display;
    private JButton BPoint, BSegmt, BCircle, BPolygon, BDraw, BColor, BClear;
    private Editeur ed1;
    private Container content;

    public InterfaceG(String name) {
        setTitle(name);
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        hotbar = new JPanel();
        menuL1 = new JPanel();
        menuL2 = new JPanel();
    
        mb = new JMenuBar(); 
        tb = new JToolBar(); 
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
        BDraw = new JButton("Draw"); 
        BColor = new JButton("Color");
        BClear = new JButton("Clear", clear);
    
        ed1 = new Editeur();
        ed1.setBackground(Color.white);
    
        content = this.getContentPane();
    
        BPoint.addActionListener(this);
        BSegmt.addActionListener(this);
        BCircle.addActionListener(this);
        BPolygon.addActionListener(this);
        BDraw.addActionListener(this);
        BColor.addActionListener(this);
        BClear.addActionListener(this);
    
        hotbar.setLayout(new GridLayout(3, 1));
        menuL1.setLayout(new GridLayout(1, 5, 3, 0));
        menuL2.setLayout(new GridLayout(1, 5, 3, 0));
    
        mb.add(file);
        mb.add(home);
        mb.add(display);
    
        menuL1.add(BPoint);
        menuL1.add(BSegmt);
        menuL1.add(BCircle);
        menuL1.add(BPolygon);
        menuL1.add(BDraw);
        menuL2.add(BColor);
        menuL2.add(BClear);
    
        ed1.addMouseListener(ed1);
        ed1.addMouseMotionListener(ed1);
        
        hotbar.add(menuL1); 
        hotbar.add(menuL2); 

        home.add(hotbar);
        
        // setJMenuBar(mb); 
        tb.add(menuL1);
        add(tb);
        

        // content.add(hotbar, BorderLayout.NORTH);
        content.add(ed1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        //Ne pas oublier de changer les layout de base des jpannels
        InterfaceG gui = new InterfaceG("DrawApp");
        gui.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        JButton source = (JButton)evt.getSource();
        String txt = source.getText();

        if(txt == "Color"){  
            Color c = JColorChooser.showDialog(this,"Select a color",ed1.getInitialcolor());
            ed1.setColor(c);
        }

        else if(txt == "Clear"){
            ed1.setFigures(new LinkedList<Figure>());
            ed1.setBackground(Color.white);
            repaint();
        }

        else {
            ed1.setSelectedFigure(txt);
        }
    }
}
