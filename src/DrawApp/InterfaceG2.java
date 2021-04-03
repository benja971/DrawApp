package DrawApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class InterfaceG2 extends JFrame implements ActionListener{

    private Dimension dimension;
    private Editeur ed1;
    private JMenuBar menuBar;
    private JMenu mnuFile, mnuEdit, mnuHelp;
    private JMenuItem mnuNewFile, mnuOpenFile, mnuSaveFile, mnuSaveFileAs, mnuExit, mnuUndo, mnuRedo, mnuCopy, mnuCut, mnuPaste;
    private JToolBar toolBar1;
    private JButton btnNew, btnSave, btnSaveAs, btnCopy, btnCut, btnPaste, btnExit, btnPoint, btnLine, btnCircle, btnPolygon, btnDraw, btnColor, btnClear;

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
        mnuFile.add(mnuNewFile);

        mnuFile.addSeparator();

        mnuOpenFile = new JMenuItem( "Open File ...",new ImageIcon( "icons/open_file.png" ) );
        mnuOpenFile.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuOpenFile);

        mnuSaveFile = new JMenuItem( "Save File ...", new ImageIcon( "icons/save.png" ) );
        mnuSaveFile.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFile);

        mnuSaveFileAs = new JMenuItem( "Save File As ...", new ImageIcon( "icons/save_as.png" ) );
        mnuSaveFileAs.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuSaveFileAs);

        mnuFile.addSeparator();

        mnuExit = new JMenuItem( "Exit", new ImageIcon( "icons/exit.png" ) );
        mnuExit.addActionListener(this::JMenuItemListener);
        mnuFile.add(mnuExit);

// ------------------------------------------------------------------------------------------------

        mnuEdit = new JMenu( "Edit" );

        mnuUndo = new JMenuItem( "Undo", new ImageIcon( "icons/undo.png" ) );
        mnuUndo.addActionListener(this::JMenuItemListener);
        mnuEdit.add(mnuUndo);
        
        mnuRedo = new JMenuItem( "Redo", new ImageIcon( "icons/redo.png" ) );
        mnuRedo.addActionListener(this::JMenuItemListener);
        mnuEdit.add(mnuRedo);
        
        mnuEdit.addSeparator();
        
        mnuCopy = new JMenuItem( "Copy", new ImageIcon( "icons/copy.png" ) );
        mnuCopy.addActionListener(this::JMenuItemListener);
        mnuEdit.add(mnuCopy);
        
        mnuCut = new JMenuItem( "Cut", new ImageIcon( "icons/cut.png" ) );
        mnuCut.addActionListener(this::JMenuItemListener);
        mnuEdit.add(mnuCut);
        
        mnuPaste = new JMenuItem( "Paste", new ImageIcon( "icons/paste.png" ) );
        mnuPaste.addActionListener(this::JMenuItemListener);
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
        btnPoint.setToolTipText( "Dot" );
        btnPoint.addActionListener(this);
        toolBar1.add( btnPoint );

        btnLine = new JButton( new ImageIcon( "icons/line.png" ) );
        btnLine.setToolTipText( "Line" );
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
        // TODO Auto-generated method stub
        // Object source = e.getSource();
        // String
        // if () {
        System.out.println("actionPerformed");
        // }
        // ed1 = new Editeur();
        // ed1.setBackground(Color.red);
        // add(ed1, BorderLayout.CENTER);
    }

    private void JMenuItemListener( ActionEvent event ) {
        System.out.println("JMenuItemListener");
    }

    private void JMenuListener( ActionEvent event ) {
        System.out.println("JMenuListener");
    }

    public static void main(String[] args) {
        InterfaceG2 gui = new InterfaceG2("DrawApp");
        gui.setVisible(true);
    }

}
