package Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import runner.ComponentFactory;
import runner.UMLDrawAreaController;
import ConstantsAndEnums.Constants;
import GUI_View.ToolbarUML;
import UML.Components.UMLComponent;

public class FigureViewingPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	private UMLDrawAreaController controller;

	private JPanel umlPanel;

	public FigureViewingPanel(UMLDrawAreaController controller) {
		this.setLayout(new MigLayout("fill", "grow", "grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		this.controller = controller;
		controller.addObserver(this);
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
	    	System.out.println("Ny figur");
		controller.newClass();
	}

	private void addComponentToDrawArea(UMLComponent_GUI c) {
		umlPanel.add(new Resizable(ComponentFactory.getClassFigure(c.getComponent()),c.getPoint()));
		c.clearChanged();
	}

	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (Constants.FIGURE_VIEWER_BACKGROUND != null)
			g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(),
					getHeight(), this);

	}


	@Override
	public void update(Observable o, Object arg) {
	    	List<UMLComponent_GUI> components = controller.getAllComponents();
	    	components.stream().filter(c -> c.hasChanged()).forEach(c -> addComponentToDrawArea(c));
		repaint();
		revalidate();
	}
}
