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
import javax.swing.plaf.ColorUIResource;

public class Editeur extends JPanel implements ActionListener, MouseInputListener{

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures, copys;
    private String selectedFigure;
    private Figure hoveredFigure, tmpFigure;
    private Point hoveredPoint, tmp_point, mouse_point;
    private LinkedList<Point> tmp_points;
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
    private int polygon_size, count;


    public Editeur(){
        figures = new LinkedList<Figure>();
        copys = new LinkedList<Figure>();
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
		filechooser.setApproveButtonText("Export");        
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
        if (tmpFigure != null) {
            tmpFigure.paint(gc, tmpFigure.equals(hoveredFigure));
        }
        if (mouse_point != null) {
            mouse_point.paint(gc, mouse_point.equals(hoveredFigure));
        }
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
            mouse_point = null;
            tmpFigure = null;
            tmp_point = null;
            tmp_points = new LinkedList<Point>();
            rightclickmenu.show(this, e.getX(), e.getY());
        }
        
        else if (selectedFigure == "Select") {
            selectedFigure = null;
            mouse_point = null;
            tmpFigure = null;
            tmp_point = null;
            tmp_points = new LinkedList<Point>();
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
            for (Figure figure : figures) {
                figure.setSelected(false);
            }
            
            if (selectedFigure == "Point") {
                figures.add(new Point(e.getX(), e.getY(), new ColorUIResource(color == null ? Color.black : color)));
            }
            else if (selectedFigure == "Segment") {
                if (tmp_point == null) {
                    tmp_point = mouse_point;
                }
                else {
                    figures.add(new Segment(tmp_point, mouse_point, new ColorUIResource(color == null ? Color.black : color)));
                    tmp_point = null;
                    tmpFigure = null;
                }
            }
            else if (selectedFigure == "Circle") {
                if (tmp_point == null) {
                    tmp_point = mouse_point;
                }
                else {
                    figures.add(new Circle(tmp_point, mouse_point, new ColorUIResource(color == null ? Color.black : color)));
                    tmp_point = null;
                    tmpFigure = null;
                }
            }
            else if (selectedFigure == "Polygon") {
                if (polygon_size == 0) {
                    JOptionPane.showMessageDialog(null, "Your must chose a size for your polygon first !", null, JOptionPane.ERROR_MESSAGE);
                }
                else if (count < polygon_size - 1){
                    tmp_points.add(mouse_point);
                    count ++;
                    
                }
                else {
                    tmp_points.add(mouse_point);
                    figures.add(new Polygon((LinkedList<Point>) tmp_points.clone(), new ColorUIResource(color == null ? Color.black : color)));
                    tmp_points.clear();
                    tmpFigure = null;
                    count = 0;
                }
            }
            repaint();
        }
        System.out.println(figures);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (selectedFigure == "Select") {
            hover_point(e.getX(), e.getY());
        }
        else if (selectedFigure == "Point" || selectedFigure == "Segment" || selectedFigure == "Circle" || selectedFigure == "Polygon" || selectedFigure == "Draw"){
            mouse_point = new Point(e.getX(), e.getY(), new ColorUIResource(color == null ? Color.black : color));
            if (selectedFigure == "Segment") {
                if (tmp_point != null) {
                    tmpFigure = new Segment(tmp_point, mouse_point, color == null ? Color.black : color);
                }
            }
            else if (selectedFigure == "Circle") {
                if (tmp_point != null) {
                    tmpFigure = new Circle(tmp_point, mouse_point, color == null ? Color.black : color);
                }
            }
            else if (selectedFigure == "Polygon") {
                tmp_points.add(mouse_point);
                System.out.println(tmp_points);
                tmpFigure = new Polygon((LinkedList<Point>) tmp_points.clone(), new ColorUIResource(color == null ? Color.black : color));
                System.out.println(tmpFigure);
                tmp_points.removeLast();
            }

        }
        repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (selectedFigure == "Translate") {
            hoveredPoint = new Point(e.getX(), e.getY(), Color.black);
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
        tmpFigure = null;
        
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
            mouse_point = new Point(e.getX(), e.getY(), new ColorUIResource(color == null ? Color.black : color));
            figures.add(mouse_point);
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
				JOptionPane.showMessageDialog(null, "Cannot create file", null, JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Cannot create file", null, JOptionPane.ERROR_MESSAGE);
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
        if (txt == "Translate") {
            selectedFigure = txt;
        }
    }

    public Figure getHoveredFigure() {
        return hoveredFigure;
    }

    public void setHoveredFigure(Figure hoveredFigure) {
        this.hoveredFigure = hoveredFigure;
    }

    public Point getHoveredPoint() {
        return hoveredPoint;
    }

    public void setHoveredPoint(Point hoveredPoint) {
        this.hoveredPoint = hoveredPoint;
    }

    public LinkedList<Figure> getCopys() {
        return copys;
    }

    public void setCopys(LinkedList<Figure> copys) {
        this.copys = copys;
    }

    public int getPolygon_size() {
        return polygon_size;
    }

    public void setPolygon_size(int polygon_size) {
        this.polygon_size = polygon_size;
    }

    public Figure getTmpFigure() {
        return tmpFigure;
    }

    public void setTmpFigure(Figure tmpFigure) {
        this.tmpFigure = tmpFigure;
    }
    
}