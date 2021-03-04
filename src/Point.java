import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * InnerPoint
 */
public class Point extends Figure {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point(String n, int x, int y){
		super(n);
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		super(p.getName());
		this.x = p.getX();
		this.y = p.getY();
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "Point [name = "+ this.getName() + ", x=" + x + ", y=" + y + "]";
	}

	public void afficher(){
		System.out.println(toString());
	}

	public void translate(int tx, int ty){
		x += tx;
		y += ty;
	}

	public Point getCenter(){
		return this;
	}

	public double getDistance(Point p2) {
		return Math.sqrt(Math.pow((p2.getX() - this.x), 2) + Math.pow((p2.getY() - this.y), 2));
	}

	public boolean equals(Object p2) {
		Point p = (Point)p2;
		return p.x == x && p.y == y;
	}

	public Point clone(){
		return new Point(this);
	}

	public void export() throws IOException{
		File file  = new File("../Exports/Points.txt");
		OutputStream f = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(toString());
		out.close();
		System.out.println(toString() + " has been exported");
	}

	public void paint(Graphics gc) {
		gc.drawLine(x, y, x, y);
	}

}