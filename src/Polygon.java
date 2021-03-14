/**
 * InnerPolygon
 */
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Polygon extends Figure
{
	private static final long serialVersionUID = 1L;
    private LinkedList<Point> points;
     
	public Polygon(String n, LinkedList<Point> points) {
		super(n);
		this.points = points;
	}

	public Polygon(String n, Point[] pts, int nbr) {
		super(n);
		points = new LinkedList<Point>();
		
		for (int i = 0; i < nbr; i++) {
			this.points.add(pts[i]);
		}
	}

	public Polygon(Polygon p) {
		super(p.getName());
		this.points = p.getPoints();
	}

	public String toString() {
		return "Polygon [name=" + getName() +", "+ points.size() + " points" + "]";
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

	public void export(ObjectOutputStream out) throws IOException{
		out.writeObject(this);
		System.out.println(toString() + " has been exported");
	}

	public LinkedList<Point> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<Point> points) {
		this.points = points;
	}

	public boolean equals(Object f) {
        Polygon p2 = (Polygon)f;
        
        if(p2.points.size() != points.size()) {
            return false;
        }
        
        int i = 0;
        int j = 0;
        j = p2.points.indexOf(points.get(i));
        if(j == -1) {
            return false;
        }
        
        int nbCorrespondance = 1;
        j = j+1;
        for(i = 0; i < points.size(); i++, j++) {
            if(points.get(i).equals(p2.points.get(j%points.size()))) {
                nbCorrespondance++;
            }
        }
        if(nbCorrespondance == points.size()) {
            return true;
        }
        
        nbCorrespondance = 1;
        j = p2.points.indexOf(points.get(i));
        for(i = 1;i < points.size(); i++, j--) {
            if(points.get(i).equals(p2.points.get(j%points.size()))) {
                nbCorrespondance ++;
            }
        }
        if(nbCorrespondance == points.size()) {
            return true;
        }
        
        return false;
    }

	public void paint(Graphics gc) {
		int x[]= new int[50], y[]= new int[50], i = 0;

		for (Point point : points) {
			point.paint(gc);
			x[i] = point.getX();
			y[i] = point.getY();
			i++;
		}
		
		gc.drawPolygon(x, y, points.size());

	}
}