package Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ConstantsAndEnums.Constants;
import Controller.UMLController;
import GUI_View.ToolbarUML;
import net.miginfocom.swing.MigLayout;

public class FigureViewingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
   
	
	private UMLController controller;
	private Resizable res;
	
	private JPanel umlPanel;

	public FigureViewingPanel() {
		this.setLayout(new MigLayout("fill","grow","grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		createComponents();
	}
	
	
	private void createComponents() {			
		controller = new UMLController();		
		umlPanel = new JPanel(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                requestFocus();
                res.repaint();           
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
		res = new Resizable(new ClassFigure(controller));
		res.setBounds(50, 50, 200, 200);
		umlPanel.add(res);
		
		res.repaint();
		res.revalidate();
	}
		
	public void paintComponent(final Graphics g) {
	    super.paintComponent(g);
	    if (Constants.FIGURE_VIEWER_BACKGROUND != null)
	        g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(), getHeight(), this);
  
	}
}
