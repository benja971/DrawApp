package DrawApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.DimensionUIResource;

public class PolygonPanel extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JSpinner nbr_sides_field;
    private JButton validate;
    private JLabel explications;
    private int nbr_sides;
    private JPanel p;

    public PolygonPanel() {
        // setLayout(new GridLayout(2, 1));
        
        SpinnerNumberModel model1 = new SpinnerNumberModel(3, 3, 30, 1);  
        nbr_sides_field = new JSpinner(model1);
        nbr_sides_field.setPreferredSize(new DimensionUIResource(60, 20));

        validate = new JButton("Validate");
        validate.addActionListener(this);

        String expl = "How many vertices (sommets):" ;
        explications = new JLabel(expl);

        p = new JPanel();

        add(explications, BorderLayout.NORTH);
        p.add(nbr_sides_field, BorderLayout.WEST);
        p.add(validate, BorderLayout.EAST);
        add(p, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText() == "Validate") {
            nbr_sides = (int) nbr_sides_field.getValue();
        }
        
    }

    public int getNbr_sides() {
        return nbr_sides;
    }

    public void setNbr_sides(int nbr_sides) {
        this.nbr_sides = nbr_sides;
    }


}
