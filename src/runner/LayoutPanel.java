package runner;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LayoutPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel drawingPanel, pathPanel;

	public LayoutPanel() {
		this.setLayout(new MigLayout("", "[grow, 30%][grow, 70%]", "[grow]"));
		
		createComponents();
		
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
	}
	
	
	private void createComponents() {
		
		drawingPanel = new JPanel(new MigLayout());
		pathPanel = new JPanel(new MigLayout());
		
		setComponents();
	}

	private void setComponents() {
		pathPanel.setBorder(Constants.RAISED_BEVEL_BORDER);		
		drawingPanel.setBorder(Constants.RAISED_BEVEL_BORDER);
		
		this.add(new TreePanel(), "grow");
		this.add(new FigureViewingPanel(), "grow");

		
		
	}
}
