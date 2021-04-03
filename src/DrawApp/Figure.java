package DrawApp;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Figure implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private Color color;

	public Figure(String n, Color c){
		name = n;
		color = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Figure [name=" + name + "]";
	}

	public void afficher(){
		System.out.println(toString());
	}

	public abstract void translate(int tx, int ty);

	public abstract Point getCenter();
	
	public abstract boolean equals(Object f2);

	public abstract Figure clone();
	
	public abstract void export(ObjectOutputStream out) throws IOException;

	public abstract void paint(Graphics g);

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
