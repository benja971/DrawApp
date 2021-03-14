/**
 * Editeur
 */
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;


public class Editeur extends JPanel{

	private static final long serialVersionUID = 1L;
	private LinkedList<Figure> figures;

    public Editeur(){
        figures = new LinkedList<Figure>();
    }

    public Editeur(LinkedList<Figure> f){
        figures = f;
    }

    public void paint(Graphics gc) { 
        for (Figure figure : figures) {
            figure.paint(gc);
        }
    }

    public LinkedList<Figure> getFigures() {
        return figures;
    }

    public void setFigures(LinkedList<Figure> figures) {
        this.figures = figures;
    }

}