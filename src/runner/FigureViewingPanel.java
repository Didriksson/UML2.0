package runner;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class FigureViewingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public FigureViewingPanel() {
		this.setLayout(new MigLayout("fill","grow","grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}
	
	
	private void createComponents() {	
	
		setComponents();
	}

	private void setComponents() {
			
		
		this.add(new ToolbarUML(), "dock west");
		
	}
		
	@Override
	public void paintComponent(final Graphics g) {
	    super.paintComponent(g);
	    if (Constants.FIGURE_VIEWER_BACKGROUND != null)
	        g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(), getHeight(), this);
	}
	
}
