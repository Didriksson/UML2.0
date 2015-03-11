package GUI_View;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import runner.ViewFactory;
import ConstantsAndEnums.Constants;
import UML.Utils.GenerateSource;

public class WindowFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JMenuBar menuBAR;
    private JMenu menuFILE, menuEDIT, menuSAVE;
    private JMenuItem itemEXIT, subMenuSave, subMenuOpen, subMenuNew, subMenuExport;

    public WindowFrame() {
	this.setSize(800, 800);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setVisible(true);

	createComponents();
    }

    private void createComponents() {
	menuBAR = new JMenuBar();

	menuFILE = new JMenu("File");
	menuEDIT = new JMenu("Edit");

	subMenuNew = new JMenuItem("New diagram");
	subMenuNew.addActionListener(e -> newDiagram());

	subMenuOpen = new JMenuItem("Open File", Constants.OPEN_FILE_ICON);
	subMenuOpen.addActionListener(e -> loadDiagram());

	subMenuSave = new JMenuItem("Save File As", Constants.SAVE_FILE_ICON);
	subMenuSave.addActionListener(e -> ViewFactory.saveCurrentState());

	subMenuExport = new JMenuItem("Generate skeleton");
	subMenuExport.addActionListener(e -> generateSkeletonCode());
	
	itemEXIT = new JMenuItem("Exit");
	itemEXIT.addActionListener(e -> exit());

	setComponents();
    }

    private void setComponents() {

	menuFILE.add(subMenuNew);
	menuFILE.add(subMenuOpen);
	menuFILE.add(subMenuSave);
	menuFILE.add(subMenuExport);
	menuFILE.add(itemEXIT);

	menuBAR.add(menuFILE);
	menuBAR.add(menuEDIT);

	this.setJMenuBar(menuBAR);
	newDiagram();
    }

    private void newDiagram() {
	this.setContentPane(new LayoutPanel(ViewFactory.newFigureViewPanel()));
	revalidate();
	repaint();
    }

    private void loadDiagram() {
	setContentPane(new LayoutPanel(
		ViewFactory.figureViewFromloadedDiagram()));
	revalidate();
	repaint();
    }
    
    private void generateSkeletonCode() {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File("."));
	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	fileChooser.setDialogTitle("Select target folder to generate code.");
	if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION )
		GenerateSource.generateFromDiagram(ViewFactory.getDiagram(), fileChooser.getSelectedFile().getPath());
    }

    private void exit() {
	this.dispose();
    }
}
