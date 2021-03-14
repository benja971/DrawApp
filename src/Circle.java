/**
 * Circle
 */
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
public class Circle extends Figure
{
	private static final long serialVersionUID = 1L;
	private Point center;
	private int rayon;

	public Circle(String n, Point center, int rayon) {
		super(n);
		this.center = center;
		this.rayon = rayon;
	}

	public Circle(Point p1, Point p2){
		super("C" + p1.getName()+p2.getName());
		this.center = p1;
		this.rayon = (int) center.getDistance(p2);
	}

	public Circle(Circle c){
		super(c.getName());
		this.center = c.getCenter();
		this.rayon = c.getRayon();
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	public String toString() {
		return "Circle [name = "+ this.getName() + ", center=" + center + ", rayon=" + rayon + "]";
	}
	
	public void translate(int tx, int ty){
		center.translate(tx, ty);
	}

	public boolean equals(Object c2) {
		Circle c = (Circle)c2;
		return center.equals(c.center) && rayon == c.rayon;
	}

	public Circle clone(){
		return new Circle(this);
	}

	public void export(ObjectOutputStream out) throws IOException{
		out.writeObject(this);
		System.out.println(toString() + " has been exported");
	}

	public void paint(Graphics gc) {
		center.paint(gc);
		gc.drawOval(center.getX() - rayon/2, center.getY() - rayon/2, rayon, rayon);		
	}

}