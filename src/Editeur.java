import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 * Editeur
 */
public class Editeur extends JFrame {

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures;
	private JFrame jf;

    public Editeur(){
        figures = new LinkedList<Figure>();
        jf = new JFrame();
        jf.repaint();
    }

    public static void main(String[] args) {
        
        Editeur ed1 = new Editeur();
        ed1.setSize(500, 500);
        ed1.setVisible(true);
        Graphics gc = new ;
        for (int i = 0; i < 5; i++) {
            ed1.figures.add(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1)) ;
            ed1.figures.add(new Segment(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1), new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1))) ;
            ed1.figures.add(new Circle(new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1), new Point("P"+ i, (int)(Math.random()*((500-1)+1))+1, (int)(Math.random()*((500-1)+1))+1))) ;
        }

        for (Figure figure : ed1.figures) {
            figure.paint(ed1.jf);
        }

    }


}