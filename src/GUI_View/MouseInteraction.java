package GUI_View;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import UML.Components.UMLRelation;
import Figures.Resizable;
import Figures.Graphics.AssociationFigure;
import Figures.Graphics.FigureList;
import Figures.Graphics.RelationsDrawer;

public class MouseInteraction extends MouseAdapter {

    private int selectedIndex = -1;
    private Point2D.Double offset;
    private boolean dragging = false;

    private AssociationFigure selectedFigure;
    private FigureList figureList;
    private RelationsDrawer relationDrawer;

    public MouseInteraction(FigureList figureList,
	    RelationsDrawer relationDrawer) {
	this.figureList = figureList;
	this.relationDrawer = relationDrawer;
	this.offset = new Point2D.Double();
    }


    public AssociationFigure getSelectedFigure() {
	return selectedFigure;
    }

    private void updateViewPanel() {
	relationDrawer.requestFocus();
	relationDrawer.repaint();
	relationDrawer.revalidate();
	relationDrawer.updateToolbar(selectedFigure);
    }

    @Override
    public void mousePressed(MouseEvent m) {
	Point p = m.getPoint();
	selectedFigure = null;

	for (AssociationFigure figure : figureList) {
	    figure.setSelected(false);
	    if (figureSelected(m, figure)) {
		handleSelectedFigure(m, p, figure);
	    }
	}
	updateViewPanel();
    }

    @Override
    public void mouseDragged(MouseEvent m) {
    	if (dragging && selectedFigure != null) {
    		double x = m.getX() - offset.x;
    		double y = m.getY() - offset.y;
    		selectedFigure.updatePosition(selectedIndex, (int) x, (int) y);
    		relationDrawer.checkIfOverlapping(m.getPoint());
    	}
    	updateViewPanel();
    }

    @Override
    public void mouseReleased(MouseEvent m) {
	if (relationDrawer.checkIfOverlapping(m.getPoint())) {
	    Resizable res = relationDrawer.returnOverlapsedComponent(m
		    .getPoint());
	    UMLRelation rel = figureList.getRelation(selectedFigure);

	    if (selectedIndex == 0) {
		relationDrawer
			.setRootForRelation(rel, res.getGUIComponent()
				.getUMLComponent(), res
				.getSnapPointFromMousePosition());

	    } else if (selectedIndex == 1) {
		relationDrawer.setDestinationForRelation(rel, res
			.getGUIComponent().getUMLComponent(), res
			.getSnapPointFromMousePosition());
	    }
	}
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

}
