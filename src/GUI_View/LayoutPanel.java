package GUI_View;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;
import runner.ViewFactory;
import ConstantsAndEnums.Constants;

public class LayoutPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JSplitPane splitPane;
	private TreePanel treePanel;
	private JPanel figurePanel;
	
	public LayoutPanel() {
		this.setLayout(new MigLayout("", "[grow, fill]", "[grow]"));
		
		createComponents();
		
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
	}
	
	
	private void createComponents() {
		treePanel = new TreePanel();
		figurePanel = ViewFactory.getFigureViewingPanel();
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, figurePanel);
		
		setComponents();
	}

	private void setComponents() {
	
		this.add(splitPane, "grow");


		
		
	}
}
