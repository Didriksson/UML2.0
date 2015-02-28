package Figures.Graphics;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public class MouseInteraction extends MouseAdapter {

	private int selectedIndex = -1;
	private Point2D.Double offset;
	private boolean dragging = false;

	private AssociationFigure selectedFigure;
	private FigureList figureList;
	private FigureViewer viewpanel;

	public MouseInteraction(FigureList figureList, FigureViewer viewpanel) {
		this.figureList = figureList;
		this.viewpanel = viewpanel;
		this.offset = new Point2D.Double();
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		if (selectedFigure != null) {
			selectedFigure.moveTo(m.getX(), m.getY());
		}

		if (dragging) {
			double x = m.getX() - offset.x;
			double y = m.getY() - offset.y;
			selectedFigure.setRect(selectedIndex, x, y);
		}
		updateViewPanel();
	}

	private void updateViewPanel() {
		viewpanel.repaint();
		viewpanel.revalidate();
	}

	@Override
	public void mousePressed(MouseEvent m) {
		Point p = m.getPoint();
		selectedFigure = null;
		System.out.println("Yo!");

		for (AssociationFigure figure : figureList) {
			figure.setSelected(false);
			if (figureSelected(m, figure)) {
				handleSelectedFigure(m, p, figure);
			}

		}

		updateViewPanel();

	}

	private boolean figureSelected(MouseEvent m, AssociationFigure figure) {
		return figure.encloses(m.getX(), m.getY())
				|| figure.enclosesRects(m.getX(), m.getY());
	}

	private void handleSelectedFigure(MouseEvent m, Point p,
			AssociationFigure figure) {
		selectedFigure = figure;
		figure.setSelected(true);

		Rectangle2D.Double[] rects = figure.getRects();
		for (int j = 0; j < rects.length; j++) {
			if (rects[j].contains(p)) {
				selectedIndex = j;
				offset.x = m.getX() - rects[j].x;
				offset.y = m.getY() - rects[j].y;
				dragging = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		selectedIndex = -1;
		dragging = false;
		updateViewPanel();
	}
}
