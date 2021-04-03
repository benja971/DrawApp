package DrawApp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException{
		Random rand = new Random();
		System.out.println("Point class tests:");

		OutputStream f = new FileOutputStream("Exports/Figures.txt");
		ObjectOutputStream out = new ObjectOutputStream(f);

		Point A = new Point("A", rand.nextInt(16)-8, rand.nextInt(16)-8);
		Point B = new Point(A);

		System.out.println("Point A: ");
		A.afficher();

		System.out.println("Point B before translate: ");
		B.afficher();	

		B.translate(-2, 4);

		System.out.println("Point B after translate of -2, 4: ");
		B.afficher();	

		System.out.println("A equals B ? =>  " + A.equals(B));

		System.out.println("Distance between A and B ? => " + A.getDistance(B));

		Point C = A.clone();

		A.export(out); 
		B.export(out); 
		C.export(out); 


		System.out.println("\n\nSegment class tests:");
		Point D = new Point("D", rand.nextInt(16)-8, rand.nextInt(16)-8);
		Segment S1 = new Segment("S1", A, D);
		Segment S2 = new Segment(A, D);
		Segment S3 = new Segment(S2);

		System.out.println("Segment S3: ");
		S3.afficher();
		
		System.out.println("Segment S1 before translate: ");
		S1.afficher();	

		S1.translate(5, -1);

		System.out.println("Segment S1 after translate of 5, -1: ");
		S1.afficher();	

		System.out.println("S2 center's => " + S2.getCenter());

		System.out.println("S1 equals S3 ? =>  " + S1.equals(S1));

		System.out.println("Distance of S2 ? => " + S2.calculDistance());

		Segment S4 = S2.clone();

		S4.export(out);


		System.out.println("\n\nCircle class tests:");
		Circle C1 = new Circle("C1", D, rand.nextInt(8));
		Circle C2 = new Circle(A, D);
		Circle C3 = new Circle(C2);

		System.out.println("Circle C1: ");
		C1.afficher();
		
		System.out.println("Circle C2 before translate: ");
		C2.afficher();	

		C2.translate(3, -3);

		System.out.println("Circle C2 after translate of 3, -3: ");
		C2.afficher();	

		System.out.println("C2's center => " + C2.getCenter());

		System.out.println("C1 equals C3 ? =>  " + C1.equals(C3));

		Circle C4 = C2.clone();

		C4.export(out);

		LinkedList<Point> points = new LinkedList<Point>();
		Point[] points2 = new Point[25];

		int nbr_pts = rand.nextInt(16);
		for (int i = 0; i < nbr_pts + 4; i++){
			points.add(new Point("P", rand.nextInt(16)-8, rand.nextInt(16)-8));
			points2[i] = new Point("P", rand.nextInt(16)-8, rand.nextInt(16)-8);
		}

		System.out.println("\n\nPolygon class tests:");
		Polygon P1 = new Polygon("Pol1", points);
		Polygon P2 = new Polygon("Pol2", points2, nbr_pts);
		Polygon P3 = new Polygon(P2);

		System.out.println("Polygon C1: ");
		P1.afficher();
		
		System.out.println("Polygon P2 before translate: ");
		P2.afficher();	

		P2.translate(3, -3);

		System.out.println("Polygon P2 after translate of 3, -3: ");
		P2.afficher();	

		P2.add(new Point("Z", rand.nextInt(16)-8, rand.nextInt(16)-8));

		P2.afficher();	

		System.out.println("P2's center => " + P2.getCenter());

		System.out.println("P1 equals P2 ? =>  " + P1.equals(P3)); //Ne fonctionne pas

		Polygon P4 = P2.clone();

		P4.export(out);

		out.close();
	}
}