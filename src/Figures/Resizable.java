package Figures;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import GUI_View.FigureViewingPanel;

public class Resizable extends JComponent{

	private FigureViewingPanel mainPanel;
	private GUIComponent content;
	protected Point position;
	private ResizableBorder border;
	private ComponentMover mouseListener;

	public Resizable(FigureViewingPanel mainPanel, GUIComponent comp, Point p) {
		this(mainPanel, comp, new ResizableBorder(8), p);
	}

	public Resizable(FigureViewingPanel mainPanel, GUIComponent comp,
			ResizableBorder border, Point p) {
		this.position = p;
		this.mainPanel = mainPanel;
		this.content = comp;
		this.border = border;
		mouseListener = new ComponentMover(this);

		setLayout(new BorderLayout());
		setBorder(border);
		setBounds(p.x, p.y, 200, 200);

		add(content);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		setupKeyBindings();

		this.border.setParentComponent(this);
	}

	private void setupKeyBindings() {
		Action deleteMethod = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				content.controller.removeComponent();
			}
		};

		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("DELETE"), "delete");
		this.getActionMap().put("delete", deleteMethod);
	}

	public void updatePosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
		border.updatePointMap();
		viewUpdated();
	}

	public GUIComponent getGUIComponent() {
		return content;
	}

	public void resize() {
		if (getParent() != null) {
			((JComponent) getParent()).revalidate();
		}
	}

	public void setHoveredState(boolean state) {
		ResizableBorder border = (ResizableBorder) getBorder();
		border.setHoveredState(state);
	}

	public boolean getHoveredState() {
		ResizableBorder border = (ResizableBorder) getBorder();
		return border.getHoveredState();
	}

	public boolean equals(Object o) {
		if (o instanceof Resizable) {
			Resizable r = (Resizable) o;
		}
		return false;
	}

	@Override
	public boolean contains(Point p) {
		return containsWidth(p) && containsHeight(p);

	}

	private boolean containsWidth(Point p) {
		return p.x >= position.x && p.x <= (position.x + this.getWidth());
	}

	private boolean containsHeight(Point p) {
		return p.y >= position.y && p.y <= (position.y + this.getHeight());
	}

	public void viewUpdated() {
		this.mainPanel.repaint();
		this.mainPanel.revalidate();
	}

	public void setComponentSelectedInMainView() {
		this.mainPanel.setComponentSelected(this);
	}

	public Point getSnapPointFromMousePosition() {
		int closestsnappoint = border.getClosestSnappPoint(this);
		Point p = border.getPositionOfMarkers(closestsnappoint);
		this.setHoveredState(false);
		return p;
	}

}
