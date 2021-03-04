/**
 * InnerPolygon
 */
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class Polygon extends Figure
{
	private static final long serialVersionUID = 1L;
    private LinkedList<Point> points;
     
	public Polygon(String n, LinkedList<Point> points) {
		super(n);
		this.points = points;
	}

	public Polygon(String n, Point[] points) {
		super(n);
		for (Point point : points) {
			this.points.add(point);
		}
	}

	public Polygon(Polygon p) {
		super(p.getName());
		this.points = p.getPoints();
	}

	public String toString() {
		return "Polygon [name=" + getName() + "points=" + points + "]";
	}

	public void afficher() {
		System.out.println(toString());
	}

	public Point getCenter() {
        int x = 0, y = 0;
        String n = "";
        for (Point point : points) {
            x += point.getX();
            y += point.getY();
            n += point.getName();
        }
        x /= points.size();
        y /= points.size();

		return new Point(n, x, y);
	}

	public void translate(int tx, int ty) {
		for (Point point : points) {
            point.translate(tx, ty);
        }
	}

	public Polygon clone(){
		return new Polygon(this);
	}

	public void export() throws IOException{
		System.out.println(toString());
		File file  = new File("src/Exports/Polygons.txt");
		OutputStream f = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(toString());
		out.close();
	}

	public LinkedList<Point> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<Point> points) {
		this.points = points;
	}

	public boolean equals(Object f2) {
		return false;
	}

	public void paint(Graphics gc) {
		for (Point point : points) {
			gc.drawLine(point.getX(), point.getY(), point.getX(), point.getY());
		}
		
	}
}