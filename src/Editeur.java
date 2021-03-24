/**
 * Editeur
 */
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;


public class Editeur extends JPanel implements MouseInputListener{

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures;
    private String selectedFigure;
    private Point tmp;

    public Editeur(){
        figures = new LinkedList<Figure>();
        repaint();
    }

    public Editeur(LinkedList<Figure> f){
        figures = f;
        repaint();
    }

    public void paint(Graphics gc) { 
        super.paint(gc);
        for (Figure figure : figures) {
            figure.paint(gc);
        }
    }

    public LinkedList<Figure> getFigures() {
        return figures;
    }

    public void setFigures(LinkedList<Figure> figures) {
        this.figures = figures;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (selectedFigure == "Point") {
            figures.add(new Point("A", e.getX(), e.getY()));
        }
        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        if (selectedFigure == "Segment") {
            figures.add(new Point("A", e.getX(), e.getY()));
        }
        if (selectedFigure == "Circle") {
            figures.add(new Point("A", e.getX(), e.getY()));
        }
        if (selectedFigure == "Polygon") {
            repaint();
        }
        repaint();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        if (selectedFigure == "Segment") {
            figures.add(new Segment((Point)figures.getLast(), new Point("n", e.getX(), e.getY())));
        }
        if (selectedFigure == "Circle") {
            figures.add(new Circle((Point)figures.getLast(), new Point("A", e.getX(), e.getY())));
        }
        repaint();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("moved");
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    public String getSelectedFigure() {
        return selectedFigure;
    }

    public void setSelectedFigure(String selectedFigure) {
        this.selectedFigure = selectedFigure;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}