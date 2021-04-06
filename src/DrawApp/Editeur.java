package DrawApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
    private String path;
    private Image img;
    private JPopupMenu rightclickmenu;
    private JMenuItem copy, cut, paste, undo, redo, translate;
    private BufferedImage bufferedImg;
    private Graphics g;
    private JFileChooser filechooser;
    
    public Editeur(){
        figures = new LinkedList<Figure>();
        tmp_points = new LinkedList<Point>();
        initialcolor = Color.RED;
        selectedFigure = "Point";
        rightclickmenu = new JPopupMenu();
        copy = new JMenuItem("Copy", new ImageIcon("copy.png"));
        cut = new JMenuItem("Cut", new ImageIcon("cut.png"));
        paste = new JMenuItem("Paste", new ImageIcon("paste.png"));
        translate = new JMenuItem("Translate");
        undo = new JMenuItem("Undo", new ImageIcon("undo.png"));
        redo = new JMenuItem("Redo", new ImageIcon("redo.png"));
        rightclickmenu.add(copy);
        rightclickmenu.add(cut);
        rightclickmenu.add(paste);
        rightclickmenu.addSeparator();
        rightclickmenu.add(translate);
        rightclickmenu.addSeparator();
        rightclickmenu.add(undo);
        rightclickmenu.add(redo);
        filechooser = new JFileChooser();
		filechooser.setApproveButtonText("Exporter");        
        repaint();
        System.out.println("appel c1");
    }
    
    public Editeur(LinkedList<Figure> f){
        figures = f;
        tmp_points = new LinkedList<Point>();
        initialcolor = Color.RED;
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

        if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println(e.getSource());
            selectedFigure = null;
            rightclickmenu.show(this, e.getX(), e.getY());
        }
        
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
            figures.add(new Point(e.getX(), e.getY(), color));
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
        if (selectedFigure == "Draw") {
            figures.add(new Point(e.getX(), e.getY(), color));
        }
        repaint();
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
    public void paintComponent(Graphics g) {
        if (img != null) {
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(img, 0, 0, null);
        }
    }
    public void setImage() {
        try {
            this.img = ImageIO.read(new File(path));
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
                        repaint();
        } 
        catch (IOException e) {
            System.err.println(path +" introuvable " + e);
        }
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void export() {
		filechooser.showOpenDialog(null); 
 
		if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 		bufferedImg = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
	 		g = this.getGraphics();
			g.setColor(Color.WHITE);
			this.paint(g);
			try {
				ImageIO.write(bufferedImg, "JPG", filechooser.getSelectedFile());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Création du fichier impossible", null, JOptionPane.ERROR_MESSAGE);
			} 
		}
    }

}