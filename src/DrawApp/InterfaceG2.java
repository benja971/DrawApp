package DrawApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.DimensionUIResource;

public class InterfaceG2 extends JFrame implements ActionListener, KeyListener{

    private static final long serialVersionUID = 1L;
    private Dimension dimension;
    private Editeur editor;
    private JMenuBar menuBar;
    private JMenu mnuFile, mnuEdit, mnuHelp;
    private JMenuItem mnuNewFile, mnuOpenFile, mnuSaveFile, mnuSaveFileAs, mnuExit, mnuUndo, mnuRedo, mnuCopy, mnuCut, mnuPaste;
    private JToolBar toolBar1;
    private JButton btnNew, btnSave, btnSaveAs, btnCopy, btnCut, btnPaste, btnExit, btnPoint, btnLine, btnCircle, btnPolygon, btnDraw, btnColor, btnClear, btnSelect, btnValidate;
    private JFileChooser fileChooser;
    private JSpinner nbr_sides_field;
    private JLabel explications;
    private JPanel PolygonPanel;

    public InterfaceG2(String name) {
        setTitle(name);
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dimension.getWidth()/1.25), (int)(dimension.getHeight()/1.25));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
        
// ------------------------------------------------------------------------------------------------
        mnuFile = new JMenu( "File" );

        mnuNewFile = new JMenuItem( "New File", new ImageIcon("icons/new.png") );
        mnuNewFile.addActionListener(this::JMenuItemListener);
        mnuNewFile.setMnemonic(KeyEvent.VK_N);
        mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mnuFile.add(mnuNewFile);
        
        mnuFile.addSeparator();
        
        mnuOpenFile = new JMenuItem( "Open File ...",new ImageIcon( "icons/open_file.png" ) );
        mnuOpenFile.addActionListener(this::JMenuItemListener);
        mnuOpenFile.setMnemonic(KeyEvent.VK_O);
        mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        mnuFile.add(mnuOpenFile);
        
        mnuSaveFile = new JMenuItem( "Save File ...", new ImageIcon( "icons/save.png" ) );
        mnuSaveFile.addActionListener(this::JMenuItemListener);
        mnuSaveFile.setMnemonic(KeyEvent.VK_S);
        mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mnuFile.add(mnuSaveFile);
        
        mnuSaveFileAs = new JMenuItem( "Save File As ...", new ImageIcon( "icons/save_as.png" ) );
        mnuSaveFileAs.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFileAs);
        
        mnuFile.addSeparator();
        
        mnuExit = new JMenuItem( "Exit", new ImageIcon( "icons/exit.png" ) );
        mnuExit.addActionListener(this::JMenuItemListener);
        mnuExit.setMnemonic(KeyEvent.VK_F4);
        mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        mnuFile.add(mnuExit);

// ------------------------------------------------------------------------------------------------

        mnuEdit = new JMenu( "Edit" );

        mnuUndo = new JMenuItem( "Undo", new ImageIcon( "icons/undo.png" ) );
        mnuUndo.addActionListener(this::JMenuItemListener);
        mnuUndo.setMnemonic(KeyEvent.VK_Z);
        mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));        
        mnuEdit.add(mnuUndo);
        
        mnuRedo = new JMenuItem( "Redo", new ImageIcon( "icons/redo.png" ) );
        mnuRedo.addActionListener(this::JMenuItemListener);
        mnuRedo.setMnemonic(KeyEvent.VK_Y);
        mnuRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));        
        mnuEdit.add(mnuRedo);
        
        mnuEdit.addSeparator();
        
        mnuCopy = new JMenuItem( "Copy", new ImageIcon( "icons/copy.png" ) );
        mnuCopy.addActionListener(this::JMenuItemListener);
        mnuCopy.setMnemonic(KeyEvent.VK_C);
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        mnuEdit.add(mnuCopy);
        
        mnuCut = new JMenuItem( "Cut", new ImageIcon( "icons/cut.png" ) );
        mnuCut.addActionListener(this::JMenuItemListener);
        mnuCut.setMnemonic(KeyEvent.VK_X);
        mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        mnuEdit.add(mnuCut);
        
        mnuPaste = new JMenuItem( "Paste", new ImageIcon( "icons/paste.png" ) );
        mnuPaste.addActionListener(this::JMenuItemListener);
        mnuPaste.setMnemonic(KeyEvent.VK_V);
        mnuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        mnuEdit.add(mnuPaste);

// ------------------------------------------------------------------------------------------------

        mnuHelp = new JMenu( "Help" );
        mnuHelp.addActionListener(this::JMenuListener);

// ------------------------------------------------------------------------------------------------
        
        menuBar.add(mnuFile);
        menuBar.add(mnuEdit);
        menuBar.add(mnuHelp);

        setJMenuBar(menuBar);

// ------------------------------------------------------------------------------------------------

        toolBar1 = new JToolBar();

        btnNew = new JButton( new ImageIcon( "icons/new.png") );
        btnNew.setToolTipText( "New File (CTRL+N)" );
        btnNew.addActionListener(this);
        toolBar1.add( btnNew );
        
        btnSave = new JButton( new ImageIcon( "icons/save.png" ) );
        btnSave.setToolTipText( "Save (CTRL+S)" );
        btnSave.addActionListener(this);
        toolBar1.add( btnSave );

        btnSaveAs = new JButton( new ImageIcon( "icons/save_as.png" ) );
        btnSaveAs.setToolTipText( "Save As..." );
        btnSaveAs.addActionListener(this);
        toolBar1.add( btnSaveAs );

        toolBar1.addSeparator();

        btnCopy = new JButton( new ImageIcon( "icons/copy.png") );
        btnCopy.setToolTipText( "Copy (CTRL+C)" );
        btnCopy.addActionListener(this);
        toolBar1.add( btnCopy );

        btnCut = new JButton( new ImageIcon( "icons/cut.png") );
        btnCut.setToolTipText( "Cut (CTRL+X)" );
        btnCut.addActionListener(this);
        toolBar1.add( btnCut );

        btnPaste = new JButton( new ImageIcon( "icons/paste.png") );
        btnPaste.setToolTipText( "Paste (CTRL+V)" );
        btnPaste.addActionListener(this);
        toolBar1.add( btnPaste );

        toolBar1.addSeparator();

        btnExit = new JButton( new ImageIcon( "icons/exit.png") );
        btnExit.setToolTipText( "Exit (ALT+F4)" );
        btnExit.addActionListener(this);
        toolBar1.add( btnExit );

        toolBar1.addSeparator();

        btnPoint = new JButton( new ImageIcon( "icons/dot.png") );
        btnPoint.setToolTipText( "Point" );
        btnPoint.addActionListener(this);
        toolBar1.add( btnPoint );

        btnLine = new JButton( new ImageIcon( "icons/line.png" ) );
        btnLine.setToolTipText( "Segment" );
        btnLine.addActionListener(this);
        toolBar1.add( btnLine );

        btnCircle = new JButton( new ImageIcon( "icons/circle.png" ) );
        btnCircle.setToolTipText( "Circle" );
        btnCircle.addActionListener(this);
        toolBar1.add( btnCircle );

        btnPolygon = new JButton( new ImageIcon( "icons/polygon.png") );
        btnPolygon.setToolTipText( "Polygon" );
        btnPolygon.addActionListener(this);
        toolBar1.add( btnPolygon );
        
        btnDraw = new JButton( new ImageIcon( "icons/draw.png") );
        btnDraw.setToolTipText( "Draw" );
        btnDraw.addActionListener(this);
        toolBar1.add( btnDraw );

        toolBar1.addSeparator();

        btnSelect = new JButton( new ImageIcon( "icons/draw.png") );
        btnSelect.setToolTipText( "Select" );
        btnSelect.addActionListener(this);
        toolBar1.add( btnSelect );

        toolBar1.addSeparator();
        
        btnColor = new JButton( new ImageIcon( "icons/color.png") );
        btnColor.setToolTipText( "Chose Color" );
        btnColor.addActionListener(this);
        toolBar1.add( btnColor );

        toolBar1.addSeparator();

        btnClear = new JButton( new ImageIcon( "icons/clear.png") );
        btnClear.setToolTipText( "Clear The Whole Page" );
        btnClear.addActionListener(this);
        toolBar1.add( btnClear );

        add(toolBar1, BorderLayout.NORTH);

        SpinnerNumberModel model1 = new SpinnerNumberModel(3, 3, 30, 1);  
        nbr_sides_field = new JSpinner(model1);
        nbr_sides_field.setPreferredSize(new DimensionUIResource(60, 20));

        btnValidate = new JButton("Validate");
        btnValidate.setToolTipText("Validate");
        btnValidate.addActionListener(this);

        String expl = "How many vertices (sommets):" ;
        explications = new JLabel(expl);

        PolygonPanel = new JPanel();
        PolygonPanel.setLayout(new GridLayout(12, 1));
        PolygonPanel.add(explications, BorderLayout.NORTH);
        PolygonPanel.add(nbr_sides_field, BorderLayout.WEST);
        PolygonPanel.add(btnValidate, BorderLayout.EAST);
        
// ------------------------------------------------------------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String txt = source.getToolTipText();
        
        if (txt == "New File (CTRL+N)") {
            editor = new Editeur();
            add(editor, BorderLayout.CENTER);
            editor.setBackground(Color.white);
            editor.addMouseListener(editor);
            editor.addMouseMotionListener(editor);
            editor.repaint();
            this.validate();
        }
        else if (txt == "Save (CTRL+S)") {
            if (editor.getPath_img() != null) {
                editor.save_img();
            }
            else {
                editor.save_img_as();
            }
        }
        else if (txt == "Save As...") {
            editor.save_img_as();
        }
        else if (txt == "Copy (CTRL+C)") {
            System.out.println("copy");
            for (Figure figure : editor.getFigures()) {
                if (figure.getSelected()) {
                    editor.getCopys().add(figure.clone());
                }
            }
            System.out.println(editor.getCopys());
            editor.repaint();
        }
        else if (txt == "Cut (CTRL+X)") {
            System.out.println("cut");
            editor.getCopys().clear();
           
            for (Figure figure : editor.getFigures()) {
                if (figure.getSelected()) {
                    editor.getCopys().add(figure.clone());
                    editor.getFigures().remove(figure);
                }
            }
            editor.repaint();
        }
            
            else if (txt == "Paste (CTRL+V)") {
                System.out.println("paste");
                System.out.println(editor.getCopys());
                for (Figure figure : editor.getCopys()) {
                    if (figure instanceof Point) {
                        Point p = (Point) figure;
                        editor.getFigures().add(new Point(p.getX() + 10, p.getY() + 10 , Color.black));
                        editor.repaint();
                    }
                    else if (figure instanceof Segment) {
                        Segment s = (Segment) figure;
                        editor.getFigures().add(new Segment(new Point(s.getP1().getX()+10, s.getP1().getY()+10, Color.black), new Point(s.getP2().getX()+10, s.getP2().getY()+10, Color.black), Color.black));
                    editor.repaint();
                }
                else if (figure instanceof Circle) {
                    Circle c = (Circle) figure;
                    editor.getFigures().add(new Circle(c.getName(), new Point(c.getCenter().getX()+10, c.getCenter().getY()+10, Color.black), c.getRayon(), Color.black));
                    editor.repaint();
                }
                else if (figure instanceof Polygon) {
                    Polygon p = (Polygon) figure;
                    LinkedList<Point> l = new LinkedList<Point>();
                    for (Point point : p.getPoints()){
                        Point tmp = new Point(point.getX()+10, point.getY()+10, Color.black);
                        l.add(tmp);
                    }
                    editor.getFigures().add(new Polygon(l, Color.black));
                    editor.repaint();
                }
            }            
        }
        else if (txt == "Exit (ALT+F4)" && editor != null) {
            setVisible(false);
            dispose();
        }
        else if ((txt == "Point" || txt == "Segment" || txt == "Circle" || txt == "Polygon" || txt == "Draw" || txt == "Select") && editor != null){
            editor.setSelectedFigure(txt);
            if (txt == "Polygon") {
                add(PolygonPanel, BorderLayout.WEST);
                validate();
            }
            else if (txt != "Polygon") {
                remove(PolygonPanel);
                validate();
            }
        }

        else if (txt == "Validate") {
            editor.setPolygon_size((int) nbr_sides_field.getValue());
        }

        else if (txt == "Chose Color" && editor != null){  
            Color c = JColorChooser.showDialog(this,"Select a color",editor.getInitialcolor());
            editor.setColor(c);
        }
        else if(txt == "Clear The Whole Page" && editor != null) {
            editor.setBackground(Color.white);
            editor.setFigures(new LinkedList<Figure>());
            editor.setTmp_point(null);
            editor.setTmp_points(new LinkedList<Point>());
            editor.setTmpFigure(null);
            editor.repaint();
        }   
    }
    
    private void JMenuItemListener( ActionEvent e ) {
        JMenuItem source = (JMenuItem) e.getSource();
        String txt = source.getText();
        
        if (txt == "New File") {
            editor = new Editeur();
            add(editor, BorderLayout.CENTER);
            editor.setBackground(Color.white);
            editor.addMouseListener(editor);
            editor.addMouseMotionListener(editor);
            editor.repaint();
            this.validate();
        }
        else if (txt == "Open File ..."){
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"));
            int returnVal = fileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File path = fileChooser.getSelectedFile();
                if (editor != null) {
                editor.setPath_img(path);
                editor.setImage();
                }
            }
        }

        else if (txt == "Save File ...") {
            if (editor.getPath_img() != null) {
                editor.save_img();
            }
            else {
                editor.save_img_as();
            }
        }
        else if (txt == "Save File As ...") {
            editor.save_img_as();
        }
        else if (txt == "Undo") {
        }
        else if (txt == "Redo") {
        }
        else if (txt == "Copy") {
        }
        else if (txt == "Cut") {
        }
        else if (txt == "Paste") {
        }
        else if (txt == "Exit") {
            setVisible(false);
            dispose();
        }
        else if ((txt == "Point" || txt == "Segment" || txt == "Circle" || txt == "Polygon" || txt == "Draw") && editor != null){
            editor.setSelectedFigure(txt);

        }
    }

    private void JMenuListener( ActionEvent event ) {
    }

    public static void main(String[] args) {
        InterfaceG2 gui = new InterfaceG2("DrawApp");
        gui.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
