package Figures.Graphics;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public class MouseInteraction extends Observable implements MouseListener,
		MouseMotionListener {
	
	private AssociationFigure asso;

	private int size = 6;
	private Rectangle net = new Rectangle(size, size);
	private int selectedIndex = -1;
	private Point2D.Double offset = new Point2D.Double();
	private boolean dragging = false;

	private BaseFigure selectedFigure;
	private FigureList figureList;
	
	
	
	public MouseInteraction(FigureList figureList, BaseFigure figure) {
		this.figureList = figureList;
		this.addObserver(figureList);
		this.asso = (AssociationFigure)figure;
	}

	@Override
	public void mouseDragged(MouseEvent m) {
		if (selectedFigure != null) {
			selectedFigure.moveTo(m.getX(), m.getY());
		}

		if (dragging) {
			double x = m.getX() - offset.x;
			double y = m.getY() - offset.y;
			asso.setRect(selectedIndex, x, y);	
		}
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void mousePressed(MouseEvent m) {
		java.awt.Point p = m.getPoint();

		for (BaseFigure figure : figureList)
			if (figure.encloses(m.getX(), m.getY()))
				selectedFigure = figure;

		if (!asso.getShowRects()) {
			net.setFrameFromCenter(m.getX(), m.getY(), m.getX() + size / 2, m.getY() + size / 2);
			if (asso.getLine().intersects(net)) {
				asso.setShowRects(true);
			}
		} else {
			Rectangle2D.Double[] rects = asso.getRects();
			for (int j = 0; j < rects.length; j++) {
				if (rects[j].contains(p)) {
					selectedIndex = j;
					offset.x = m.getX() - rects[j].x;
					offset.y = m.getY() - rects[j].y;
					dragging = true;
				}
			}
			if (selectedIndex == -1) {
				asso.setShowRects(false);			
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		selectedFigure = null;
		selectedIndex = -1;
		dragging = false;
	}

	
	
	@Override
	public void mouseMoved(MouseEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent m) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
}
