package Figures;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
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

public class Resizable extends JComponent {

	ComponentMover mouseListener;
	GUIComponent content;
	Point position;
	Map<Integer, Point> pointMap;
	ResizableBorder border;
	FigureViewingPanel mainPanel;


	public Resizable(FigureViewingPanel mainPanel, GUIComponent comp, Point p) {
		this(mainPanel, comp, new ResizableBorder(8), p);
	}

	public Resizable(FigureViewingPanel mainPanel, GUIComponent comp, ResizableBorder border, Point p) {
		this.position = p;
		mouseListener = new ComponentMover(this);
		this.content = comp;
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		add(content);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		setupKeyBindings();
		this.border = border;
		setBorder(border);
		setBounds(p.x, p.y, 200, 200);
		createPointMap();
	}

	private void createPointMap() {
		pointMap = new HashMap<Integer, Point>();
		pointMap.put(SwingConstants.NORTH, new Point());
		pointMap.put(SwingConstants.SOUTH, new Point());
		pointMap.put(SwingConstants.WEST, new Point());
		pointMap.put(SwingConstants.EAST, new Point());
		pointMap.put(SwingConstants.NORTH_WEST, new Point());
		pointMap.put(SwingConstants.NORTH_EAST, new Point());
		pointMap.put(SwingConstants.SOUTH_WEST, new Point());
		pointMap.put(SwingConstants.SOUTH_EAST, new Point());
		updatePointMap();
	}

	private void updatePointMap() {
		Point p1;
		
		p1 = pointMap.get(SwingConstants.NORTH);
		p1.x = position.x + this.getWidth() / 2;
		p1.y = position.y;

		p1 = pointMap.get(SwingConstants.SOUTH);
		p1.x = position.x + this.getWidth() / 2;
		p1.y = position.y + this.getHeight();

		p1 = pointMap.get(SwingConstants.WEST);
		p1.x = position.x;
		p1.y = position.y + this.getHeight() / 2;
		
		p1 = pointMap.get(SwingConstants.EAST);
		p1.x = position.x + this.getWidth();
		p1.y = position.y + this.getHeight() / 2;
		
		p1 = pointMap.get(SwingConstants.NORTH_WEST);
		p1.x = position.x;
		p1.y = position.y;
		
		p1 = pointMap.get(SwingConstants.NORTH_EAST);
		p1.x = position.x + this.getWidth();
		p1.y = position.y;
		
		p1 = pointMap.get(SwingConstants.SOUTH_WEST);
		p1.x = position.x;
		p1.y = position.y + this.getHeight();
		
		p1 = pointMap.get(SwingConstants.SOUTH_EAST);
		p1.x = position.x + this.getWidth();
		p1.y = position.y + this.getHeight();
		
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
		updatePointMap();
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
		this.getParent().repaint();
		this.getParent().revalidate();
	}

	public Point getPositionOfMarkers(int location) {
		if (pointMap.containsKey(location)) {
			return pointMap.get(location);
		} else
			return null;
	}

	public Point getSnapPointFromMousePosition() {
		int closestsnappoint = border.getClosestSnappPoint(this);
		Point p = getPositionOfMarkers(closestsnappoint);
		this.setHoveredState(false);
		return p;
	}
	
	public void setComponentSelectedInMainView() {
		this.mainPanel.setComponentSelected(this);
	}

}