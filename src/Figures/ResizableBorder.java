package Figures;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ResizableBorder implements Border {

    private int dist = 8;
    private boolean hovered;
    private Map<Integer, Point> pointMap;
    private Resizable component;

    int locations[] = { SwingConstants.NORTH, SwingConstants.SOUTH,
	    SwingConstants.WEST, SwingConstants.EAST,
	    SwingConstants.NORTH_WEST, SwingConstants.NORTH_EAST,
	    SwingConstants.SOUTH_WEST, SwingConstants.SOUTH_EAST };

    int cursors[] = { Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR,
	    Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR,
	    Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR,
	    Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR };

    public ResizableBorder(int dist) {
	this.dist = dist;
	this.hovered = false;

    }

    @Override
    public Insets getBorderInsets(Component component) {
	return new Insets(dist, dist, dist, dist);
    }

    @Override
    public boolean isBorderOpaque() {
	return false;
    }

    @Override
    public void paintBorder(Component component, Graphics g, int x, int y,
	    int w, int h) {

	g.setColor(Color.black);
	g.drawRect(x + dist / 2, y + dist / 2, w - dist, h - dist);
	if (component.hasFocus() || hovered) {
	    int closestRec = -1;
	    if (hovered) {
		closestRec = getClosestSnappPoint(component);
	    }

	    for (int i = 0; i < locations.length; i++) {
		Rectangle rect = getRectangle(w, h, locations[i]);
		if (locations[i] == closestRec && hovered)
		    g.setColor(Color.RED.darker());
		else
		    g.setColor(Color.WHITE);
		g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
		g.setColor(Color.BLACK);
		g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
	    }
	}
    }

    public void setParentComponent(Resizable res) {
	component = res;
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

    public void updatePointMap() {
	Point p1;
	Point position = component.position;

	p1 = pointMap.get(SwingConstants.NORTH);
	p1.x = position.x + component.getWidth() / 2;
	p1.y = position.y;

	p1 = pointMap.get(SwingConstants.SOUTH);
	p1.x = position.x + component.getWidth() / 2;
	p1.y = position.y + component.getHeight();

	p1 = pointMap.get(SwingConstants.WEST);
	p1.x = position.x;
	p1.y = position.y + component.getHeight() / 2;

	p1 = pointMap.get(SwingConstants.EAST);
	p1.x = position.x + component.getWidth();
	p1.y = position.y + component.getHeight() / 2;

	p1 = pointMap.get(SwingConstants.NORTH_WEST);
	p1.x = position.x;
	p1.y = position.y;

	p1 = pointMap.get(SwingConstants.NORTH_EAST);
	p1.x = position.x + component.getWidth();
	p1.y = position.y;

	p1 = pointMap.get(SwingConstants.SOUTH_WEST);
	p1.x = position.x;
	p1.y = position.y + component.getHeight();

	p1 = pointMap.get(SwingConstants.SOUTH_EAST);
	p1.x = position.x + component.getWidth();
	p1.y = position.y + component.getHeight();

    }

    public Point getPositionOfMarkers(int location) {
	if (pointMap.containsKey(location)) {
	    return pointMap.get(location);
	} else
	    return null;
    }

    public int getClosestSnappPoint(Component component) {
	Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
	locationOnScreen.x -= component.getLocationOnScreen().x;
	locationOnScreen.y -= component.getLocationOnScreen().y;

	int closestRec = -1;

	double previousDistance = Integer.MAX_VALUE;
	for (int i = 0; i < locations.length; i++) {
	    Rectangle rect = getRectangle(component.getWidth(),
		    component.getHeight(), locations[i]);
	    double distance = rect.getLocation().distance(locationOnScreen);
	    if (distance < previousDistance) {
		closestRec = i;
		previousDistance = distance;

	    }
	}

	return locations[closestRec];
    }

    private Rectangle getRectangle(int w, int h, int location) {
	int x = 0, y = 0;
	switch (location) {
	case SwingConstants.NORTH:
	    return new Rectangle(x + w / 2 - dist / 2, y, dist, dist);
	case SwingConstants.SOUTH:
	    return new Rectangle(x + w / 2 - dist / 2, y + h - dist, dist, dist);
	case SwingConstants.WEST:
	    return new Rectangle(x, y + h / 2 - dist / 2, dist, dist);
	case SwingConstants.EAST:
	    return new Rectangle(x + w - dist, y + h / 2 - dist / 2, dist, dist);
	case SwingConstants.NORTH_WEST:
	    return new Rectangle(x, y, dist, dist);
	case SwingConstants.NORTH_EAST:
	    return new Rectangle(x + w - dist, y, dist, dist);
	case SwingConstants.SOUTH_WEST:
	    return new Rectangle(x, y + h - dist, dist, dist);
	case SwingConstants.SOUTH_EAST:
	    return new Rectangle(x + w - dist, y + h - dist, dist, dist);
	}
	return null;
    }

    public int getCursor(MouseEvent me) {

	Component c = me.getComponent();
	int w = c.getWidth();
	int h = c.getHeight();

	for (int i = 0; i < locations.length; i++) {
	    Rectangle rect = getRectangle(w, h, locations[i]);
	    if (rect.contains(me.getPoint())) {
		return cursors[i];
	    }
	}

	return Cursor.MOVE_CURSOR;
    }

    public boolean isHovered() {
	return hovered;
    }

    public void setHoveredState(boolean hovered) {
	this.hovered = hovered;
    }

    public boolean getHoveredState() {
	return this.hovered;
    }

    public Point printPointsOfBorder(Point point) {
	Optional<Point> optionalPoint = pointMap.values().stream()
		.filter(p -> (p.x == point.x && p.y == point.y)).findFirst();
	if (optionalPoint.isPresent())
	    return optionalPoint.get();
	return point;
    }
}