/**
 * Editeur
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Editeur extends JPanel implements Action{

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures;

    public Editeur(){
        figures = new LinkedList<Figure>();
    }

    public static void main(String[] args) {
        
        JFrame jf = new JFrame();
        Editeur ed1 = new Editeur();

        jf.setSize(1000, 800);
        jf.setTitle("DrawApp");
        jf.add(ed1);
        jf.setVisible(true);
        
        LinkedList<Point> points = new LinkedList<Point>();
        LinkedList<Figure> figures_save = new LinkedList<Figure>();

        for (int i = 0; i < 5; i++) {
            figures_save.add(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1)) ;
            figures_save.add(new Segment(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1), new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1))) ;
            figures_save.add(new Circle(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1), new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1))) ;
            points.add(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1));
        }

        figures_save.add(new Polygon("P", points));

        JMenuBar menuBar = new JMenuBar();

        JButton set = new JButton("Set figures");
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ed1.figures = (LinkedList<Figure>) figures_save.clone();
                ed1.repaint();
            }});        
        JButton clear = new JButton("Clear figures");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ed1.figures.clear();
            }});

        menuBar.add(clear);
        menuBar.add(set);
        ed1.add(menuBar);

    }

    public void paint(Graphics gc) { 
        for (Figure figure : figures) {
            figure.paint(gc);
        }
    }

    // Méthodes de l'interface Action non utilisées

    @Override
    public Object getValue(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}