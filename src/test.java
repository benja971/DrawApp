import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
  
//le listener est la classe test      
public class test extends JFrame implements ActionListener
{
    
    /** Constructeur de test */
    public test()
    {
        //titre de la fenetre
        super("Test");
        
        //panel
        JPanel  pan=new JPanel();
        pan.setLayout(new BorderLayout());
        
        //bouton ici
        JButton but=new JButton("Ici !");
        
        //ajoute un listener : ici le listener est cette classe
        but.addActionListener(this);
        
        //ajoute le boutton dans le panel
        pan.add(but,BorderLayout.CENTER);
        
        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pan);
        pack(); //permet de mettre une bonne dimension a la fenetre
        setVisible(true);
    }
    
    /**
     * obligatoire car test implémente l'interface ActionListener
     */
    public  void    actionPerformed(ActionEvent e)
    {
        //quand on a cliqué sur le bouton ici
        System.out.println("Ici !");
    }
    
    public  static  void    main(String args[])
    {
        new test();
    }
}