import java.io.IOException;

/**
 * Main
 */
public class Main {
	public static void main(String[] args) throws IOException{
		System.out.println("Point class tests:");

		Point A = new Point("A", 5, -3);
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

		C.export();


		System.out.println("\n\nSegment class tests:");
		Point D = new Point("D", 7, -1);
		Segment S1 = new Segment("S1", A, D);
		Segment S2 = new Segment(A, D);
		Segment S3 = new Segment(S2);

		System.out.println("Segment S3: ");
		S3.afficher();
		
		System.out.println("nSegment S1 before translate: ");
		S1.afficher();	

		S1.translate(5, -1);

		System.out.println("nSegment S1 after translate of 5, -1: ");
		S1.afficher();	

		System.out.println("S2 center's => " + S2.getCenter());

		System.out.println("S1 equals S3 ? =>  " + S1.equals(S1));

		System.out.println("Distance of S2 ? => " + S2.calculDistance());

		Segment S4 = S2.clone();

		S4.export();


		System.out.println("\n\nCircle class tests:");
		Circle C1 = new Circle("C1", D, 5);
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

		C4.export();
	}
}