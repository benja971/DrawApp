package DrawApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Editeur extends JPanel implements ActionListener, MouseInputListener{

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures;
    private String selectedFigure;
    private Figure hoveredFigure;
    private Point hoveredPoint;
    private LinkedList<Point> tmp_points;
    private Point tmp_point;
    private Boolean tmp_figure;
    private Color initialcolor;
    private Color color;
    private File path_img;
    private String path_figures;
    private Image img;
    private JPopupMenu rightclickmenu;
    private JMenuItem copy, cut, paste, undo, redo, translate;
    private BufferedImage bufferedImg;
    private JFileChooser filechooser;

    public Editeur(){
        figures = new LinkedList<Figure>();
        tmp_points = new LinkedList<Point>();
        initialcolor = Color.RED;
        rightclickmenu = new JPopupMenu();
        copy = new JMenuItem("Copy", new ImageIcon("copy.png"));
        copy.addActionListener(this);
        cut = new JMenuItem("Cut", new ImageIcon("cut.png"));
        cut.addActionListener(this);
        paste = new JMenuItem("Paste", new ImageIcon("paste.png"));
        paste.addActionListener(this);
        translate = new JMenuItem("Translate");
        translate.addActionListener(this);
        undo = new JMenuItem("Undo", new ImageIcon("undo.png"));
        undo.addActionListener(this);
        redo = new JMenuItem("Redo", new ImageIcon("redo.png"));
        redo.addActionListener(this);
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
            figure.paint(gc, figure.equals(hoveredFigure));
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
            selectedFigure = null;
            rightclickmenu.show(this, e.getX(), e.getY());
        }
        
        else if (selectedFigure == "Select") {
            if (hoveredFigure != null) {
                hoveredFigure.setSelected(true);
            }

            else {
                
                for (Figure figure : figures) {
                    figure.setSelected(false);
                }
            }
        }
        
        else {
            System.out.println("test for 2");
            for (Figure figure : figures) {
                figure.setSelected(false);
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
        }
        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (selectedFigure == "Translate") {
            hoveredPoint = new Point(e.getX(), e.getY(), Color.BLACK);
        }

        else if (selectedFigure == "Polygon") {
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
    
    public void hover_point(int m_x, int m_y) {
        Point mouse = new Point(m_x - 3, m_y - 3, Color.black);
        hoveredFigure = null;

        for (Figure figure : figures) {
            if (figure instanceof Point) {
                Point p = (Point) figure;
                if (p.getDistance(mouse) < 6 ) {
                    hoveredFigure = p;
                }
            }
            else if (figure instanceof Segment) {
                Segment s = (Segment) figure;
                if (s.getP1().getDistance(mouse) < 6) {
                    hoveredFigure = s;
                }
                if (s.getP2().getDistance(mouse) < 6) {
                    hoveredFigure = s;
                }
            }
            else if (figure instanceof Circle) {
                Circle c = (Circle) figure;
                if (c.getCenter().getDistance(mouse) < 6) {
                    hoveredFigure = c;
                }
            }
            else if (figure instanceof Polygon) {
                Polygon p = (Polygon) figure;
                for (Point pt : p.getPoints()) {
                    if (pt.getDistance(mouse) < 6) {
                        hoveredFigure = p;
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (selectedFigure == "Select") {
            hover_point(e.getX(), e.getY());
        }
        else if (selectedFigure == "Segment" && tmp_point != null) {
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
        if (selectedFigure == "Translate") {
            int tx = e.getX() - hoveredPoint.getX();
            int ty = e.getY() - hoveredPoint.getY();
            for (Figure figure : figures) {
                if (figure.getSelected()) {
                    figure.translate(tx, ty);
                }
            }
            hoveredPoint.translate(tx, ty);
        }

        else if (selectedFigure == "Draw") {
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
        super.paintComponent(g);
        if (img != null) {
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(img, 0, 0, null);
        }
    }
    public void setImage() {
        try {
            this.img = ImageIO.read(path_img);
            img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
                        repaint();
        } 
        catch (IOException e) {
            System.err.println(path_img +" introuvable " + e);
        }
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void save_img_as() {
		if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	 		bufferedImg = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
	 		Graphics g = bufferedImg.createGraphics();
			this.paint(g);
            g.dispose();
			try {
                path_img = filechooser.getSelectedFile();
				ImageIO.write(bufferedImg, "JPG", filechooser.getSelectedFile());
                
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Création du fichier impossible", null, JOptionPane.ERROR_MESSAGE);
			} 
		}
    }
    public void save_img() {
        bufferedImg = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImg.createGraphics();
        this.paint(g);
        g.dispose();
        try {
            ImageIO.write(bufferedImg, "JPG", path_img);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Création du fichier impossible", null, JOptionPane.ERROR_MESSAGE);
        } 
    }

    public File getPath_img() {
        return path_img;
    }

    public void setPath_img(File path_img) {
        this.path_img = path_img;
    }

    public String getPath_figures() {
        return path_figures;
    }

    public void setPath_figures(String path_figures) {
        this.path_figures = path_figures;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        String txt = source.getText();
        System.out.println(txt);
        if (txt == "Translate") {
            selectedFigure = txt;
        }
    }
}