package Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import runner.ComponentFactory;
import runner.UMLComponentController;
import runner.UMLDrawAreaController;
import ConstantsAndEnums.Constants;
import GUI_View.ToolbarUML;

public class FigureViewingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
   
	
	private UMLDrawAreaController controller;
	private List<Resizable> res;
	
	private JPanel umlPanel;

	public FigureViewingPanel() {
		this.setLayout(new MigLayout("fill","grow","grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		this.controller = new UMLDrawAreaController();
		createComponents();
		
	}
	
	
	private void createComponents() {			
		umlPanel = new JPanel(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                requestFocus();
                repaint();           
            }
        });
		
		setComponents();
	}

	private void setComponents() {
		umlPanel.setBackground(Color.WHITE);
		
		this.add(umlPanel, "dock center");
		this.add(new ToolbarUML(this), "dock west");       
	}
	
	public void createFigures() {
	    	UMLComponentController component = ComponentFactory.getComponentController();
	    	Resizable tmp = new Resizable(new ClassFigure(component));
	    	tmp.setBounds(50, 50, 200, 200);
		umlPanel.add(tmp);
		
		tmp.repaint();
		tmp.revalidate();
	}
		
	public void paintComponent(final Graphics g) {
	    super.paintComponent(g);
	    if (Constants.FIGURE_VIEWER_BACKGROUND != null)
	        g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(), getHeight(), this);
  
	}
}
