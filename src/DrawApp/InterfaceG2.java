package DrawApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfaceG2 extends JFrame implements ActionListener, KeyListener{

    private static final long serialVersionUID = 1L;
    private Dimension dimension;
    private Editeur editor;
    private JMenuBar menuBar;
    private JMenu mnuFile, mnuEdit, mnuHelp;
    private JMenuItem mnuNewFile, mnuOpenFile, mnuSaveFile, mnuSaveFileAs, mnuExit, mnuUndo, mnuRedo, mnuCopy, mnuCut, mnuPaste, mnuSaveFigures, mnuSaveFiguresAs;
    private JToolBar toolBar1;
    private JButton btnNew, btnSave, btnSaveAs, btnCopy, btnCut, btnPaste, btnExit, btnPoint, btnLine, btnCircle, btnPolygon, btnDraw, btnColor, btnClear, btnSelect;
    private JFileChooser fileChooser;
    

    public InterfaceG2(String name) {
        setTitle(name);
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(dimension.getWidth()/2), (int)(dimension.getHeight()/2));
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
        mnuOpenFile.setMnemonic(KeyEvent.VK_N);
        mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        mnuFile.add(mnuOpenFile);
        
        mnuSaveFile = new JMenuItem( "Save File ...", new ImageIcon( "icons/save.png" ) );
        mnuSaveFile.addActionListener(this::JMenuItemListener);
        mnuSaveFile.setMnemonic(KeyEvent.VK_N);
        mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mnuFile.add(mnuSaveFile);
        
        mnuSaveFileAs = new JMenuItem( "Save File As ...", new ImageIcon( "icons/save_as.png" ) );
        mnuSaveFileAs.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFileAs);

        mnuFile.addSeparator();

        mnuSaveFiguresAs = new JMenuItem( "Save Figures As ...", new ImageIcon( "icons/export.png" ) );
        mnuSaveFiguresAs.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFiguresAs);

        mnuSaveFigures = new JMenuItem( "Save Figures ...", new ImageIcon( "icons/export.png" ) );
        mnuSaveFigures.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFigures);
        
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
// ------------------------------------------------------------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String txt = source.getToolTipText();
        
        if (txt == "New File (CTRL+N)") {
            System.out.println(editor);
            editor = new Editeur();
            add(editor, BorderLayout.CENTER);
            editor.setBackground(Color.white);
            editor.addMouseListener(editor);
            editor.addMouseMotionListener(editor);
            editor.repaint();
            this.validate();
        }
        else if (txt == "Save (CTRL+S)") {
        }
        else if (txt == "Save As...") {
            System.out.println("Saving");
            editor.save_img_as();
        }
        else if (txt == "Copy (CTRL+C)") {
            System.out.println("copy");
        }
        else if (txt == "Cut (CTRL+X)") {

        }
        else if (txt == "Paste (CTRL+V)") {
            
        }
        else if (txt == "Exit (ALT+F4)" && editor != null) {
            setVisible(false);
            dispose();
        }
        else if ((txt == "Point" || txt == "Segment" || txt == "Circle" || txt == "Polygon" || txt == "Draw" || txt == "Select") && editor != null){
            editor.setSelectedFigure(txt);
        }

        else if (txt == "Chose Color" && editor != null){  
            Color c = JColorChooser.showDialog(this,"Select a color",editor.getInitialcolor());
            editor.setColor(c);
        }
        else if(txt == "Clear The Whole Page" && editor != null) {
            editor.setBackground(Color.white);
            editor.setFigures(new LinkedList<Figure>());
            editor.repaint();
        }   
    }
    
    private void JMenuItemListener( ActionEvent e ) {
        JMenuItem source = (JMenuItem) e.getSource();
        String txt = source.getText();
        
        if (txt == "New File") {
            System.out.println("test");
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
