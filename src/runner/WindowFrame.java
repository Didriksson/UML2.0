package runner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WindowFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBAR;
	private JMenu menuFILE, menuEDIT;
	private JMenuItem itemEXIT, subMenuSave, subMenuOpen;
	
	public WindowFrame() {
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
		createComponents();
	}

	private void createComponents() {
		menuBAR = new JMenuBar();
		
		menuFILE = new JMenu("File");
		menuEDIT = new JMenu("Edit");
		
		subMenuOpen = new JMenuItem("Open File", Constants.OPEN_FILE_ICON);
		subMenuSave = new JMenuItem("Save File As", Constants.SAVE_FILE_ICON);
		itemEXIT = new JMenuItem("Exit");
		
		setComponents();
	}

	private void setComponents() {
				
		menuFILE.add(subMenuOpen);
		menuFILE.add(subMenuSave);
		menuFILE.add(itemEXIT);
		
		menuBAR.add(menuFILE);
		menuBAR.add(menuEDIT);
		
		this.setJMenuBar(menuBAR);
		this.setContentPane(new LayoutPanel());
		
	}
	
	
	
}
