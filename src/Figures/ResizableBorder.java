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

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ResizableBorder implements Border {

	private int dist = 8;
	private boolean hovered;

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
			Point locationOnScreen = MouseInfo.getPointerInfo().getLocation();
			locationOnScreen.x -= component.getLocationOnScreen().x;
			locationOnScreen.y -= component.getLocationOnScreen().y;

			int closestRec = 0;
			if (hovered) {
				double previousDistance = Integer.MAX_VALUE;
				for (int i = 0; i < locations.length; i++) {
					Rectangle rect = getRectangle(x, y, w, h, locations[i]);
					double distance = rect.getLocation().distance(locationOnScreen);
					if (distance < previousDistance) {
						closestRec = i;
						previousDistance = distance;
					}
				}
			}


			for (int i = 0; i < locations.length; i++) {
				Rectangle rect = getRectangle(x, y, w, h, locations[i]);
				if(i == closestRec && hovered)
					g.setColor(Color.LIGHT_GRAY);
				else
					g.setColor(Color.WHITE);
				g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
				g.setColor(Color.BLACK);
				g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
			}
		}
	}

	private Rectangle getRectangle(int x, int y, int w, int h, int location) {

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
			Rectangle rect = getRectangle(0, 0, w, h, locations[i]);
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
}