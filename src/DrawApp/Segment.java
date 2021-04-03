package DrawApp;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Segment extends Figure
{
	private static final long serialVersionUID = 1L;
	private Point p1;
	private Point p2;

	public Segment(String n, Point p1, Point p2, Color c){
		super(n, c);
		this.p1 = p1;
		this.p2 = p2;
	}

	public Segment(Point p1, Point p2, Color c){
		super(p1.getName() + p2.getName(), c);
		this.p1 = p1;
		this.p2 = p2;
	}

	public Segment(Segment s) {
		super(s.getName(), s.getColor());
		this.p1 = s.getP1();
		this.p2 = s.getP2();
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public String toString() {
		return "Segment [name = "+ this.getName() + ", distance=" + calculDistance() + ", p1=" + p1 + ", p2=" + p2 + "]";
	}

	public void translate(int tx, int ty){
		p1.translate(tx, ty);
		p2.translate(tx, ty);
	}

	public Point getCenter(){
		return new Point(p1.getName() + p2.getName() , (p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2, getColor());
	}

	public double calculDistance(){
		return p1.getDistance(p2);
	}

	public boolean equals(Object s2) {
		Segment s = (Segment)s2;
		return p1.equals(s.p1) && p2.equals(s.p2) || p1.equals(s.p2) && p2.equals(s.p1);
	}

	public Segment clone(){
		return new Segment(this);
	}

	public void export(ObjectOutputStream out) throws IOException{
		out.writeObject(this);
		System.out.println(toString() + " has been exported");
	}

	public void paint(Graphics gc) {
		gc.setColor(super.getColor());
		p1.paint(gc);
		p2.paint(gc);
		gc.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

}
