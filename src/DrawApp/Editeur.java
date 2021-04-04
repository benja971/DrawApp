package DrawApp;

import java.awt.Color;
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
    private LinkedList<Point> tmp_points;
    private Point tmp_point;
    private Boolean tmp_figure;
    private Color initialcolor;
    private Color color;
    private Boolean clicked;
    
    public Editeur(){
        figures = new LinkedList<Figure>();
        tmp_points = new LinkedList<Point>();
        initialcolor = Color.RED;
        clicked = false;
        selectedFigure = "Point";
        repaint();
        System.out.println("editor created");
    }
    
    public Editeur(LinkedList<Figure> f){
        figures = f;
        tmp_points = new LinkedList<Point>();
        initialcolor = Color.RED;
        clicked = false;
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
            figures.add(new Point(e.getX(), e.getY(), color));
        }
        else if (selectedFigure == "Segment") {
            if (tmp_point == null) {
                tmp_point = new Point(e.getX(), e.getY(), color);
                tmp_figure = false;
                
            } else{
                figures.add(new Segment(tmp_point, new Point(e.getX(), e.getY(), color), color));
                tmp_point = null;
            }
        }
        else if (selectedFigure == "Circle") {
            if (tmp_point == null) {
                tmp_point = new Point(e.getX(), e.getY(), color);
                tmp_figure = false;
            } else{
                figures.add(new Circle(tmp_point, new Point(e.getX(), e.getY(), color), color));
                tmp_point = null;
            }
        }
        else if (selectedFigure == "Polygon") {
            if (tmp_point == null) {
                tmp_points.add(new Point(e.getX(), e.getY(), color));
            } else{
                tmp_points.clear();
            }
        }
        else if (selectedFigure == "Draw") {
            if (clicked == false) {
                figures.add(new Point(e.getX(), e.getY(), color));
                clicked = true;
            } else{
                figures.add(new Point(e.getX(), e.getY(), color));
                clicked = false;
            }
        }        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (selectedFigure == "Polygon") {
            if (e.getButton() == MouseEvent.BUTTON1) {
                tmp_points.add(new Point(e.getX(), e.getY(), color));
            } 
            else if (e.getButton() == MouseEvent.BUTTON3) {
                figures.add(new Polygon(tmp_points, color));
                tmp_points = new LinkedList<Point>();
            }
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (selectedFigure == "Segment" && tmp_point != null) {
            Point p2 = new Point("A", e.getX(), e.getY(), color);
            if (tmp_figure == true) {
               figures.removeLast();
            }
            figures.add(new Segment(tmp_point, p2, color));
            tmp_figure = true;
        }

        else if (selectedFigure == "Circle" && tmp_point != null) {
            Point p2 = new Point(e.getX(), e.getY(), color);
            if (tmp_figure == true) {
                figures.removeLast();
            }
            figures.add(new Circle(tmp_point, p2, color));
            tmp_figure = true;
        }
        
        else if (selectedFigure == "Polygon" && tmp_points.size() != 0) {
            tmp_points.remove(tmp_points.getLast());
            tmp_points.add(new Point(e.getX(), e.getY(), color));
            figures.add(new Polygon(tmp_points, color));
        }
        else if (selectedFigure == "Draw" && clicked == true) {
            figures.add(new Point(e.getX(), e.getY(), color));
        }
  
        repaint();
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

    public LinkedList<Point> getTmp_points() {
        return tmp_points;
    }

    public void setTmp_points(LinkedList<Point> tmp_points) {
        this.tmp_points = tmp_points;
    }

    public Point getTmp_point() {
        return tmp_point;
    }

    public void setTmp_point(Point tmp_point) {
        this.tmp_point = tmp_point;
    }

    public Boolean getTmp_figure() {
        return tmp_figure;
    }

    public void setTmp_figure(Boolean tmp_figure) {
        this.tmp_figure = tmp_figure;
    }

    public Color getInitialcolor() {
        return initialcolor;
    }

    public void setInitialcolor(Color initialcolor) {
        this.initialcolor = initialcolor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}