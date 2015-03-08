package Figures.Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Controller.UMLRelationsController;
import Figures.Resizable;
import GUI_View.FigureViewingPanel;
import GUI_View.MouseInteraction;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class RelationsDrawer extends JPanel {
	private static final long serialVersionUID = 1L;
	private FigureViewingPanel topPanel;
	private FigureList figureList;
	private UMLRelationsController controller;
	private MouseInteraction mouseInteraction;

	public RelationsDrawer(FigureViewingPanel fwp,
			UMLRelationsController controller) {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.controller = controller;
		this.topPanel = fwp;
		this.figureList = new FigureList();
		this.mouseInteraction = new MouseInteraction(figureList, this);
		addMouseListener(mouseInteraction);
		addMouseMotionListener(mouseInteraction);
		setUpKeyBinding();
	}

	public boolean checkIfOverlapping(Point p) {
		return topPanel.checkIfRelationOverlaps(p);
	}

	public Resizable returnOverlapsedComponent(Point p) {
		return topPanel.returnOverlapsedComponent(p);
	}

	private void setUpKeyBinding() {
		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (mouseInteraction.getSelectedFigure() != null)
					controller.removeRelation(figureList
							.remove(mouseInteraction.getSelectedFigure()));
			}
		};
		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("DELETE"), "doNothing");
		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("F2"), "doNothing");
		this.getActionMap().put("doNothing", delete);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (UMLRelation r : topPanel.getRelation().keySet()) {
			if (!figureList.contains(r))
				try {
					addNewAssociationFigure(r);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		for (AssociationFigure f : figureList) {
			f.draw(g);
		}

	}

	private void addNewAssociationFigure(UMLRelation r) throws Exception {
		Point start = topPanel.getRelation().get(r).start;
		Point endPoint = topPanel.getRelation().get(r).end;
		AssociationFigure figure = FigureFactory.getRelationsFigure(
				r.getType(), start, endPoint);
		figureList.add(figure, r);

	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent umlComponent, Point point) {
		controller.setDestinationForRelation(rel, umlComponent, point);
	}

	public void updateCoordinats(UMLRelation r, Point startPoint, Point endPoint) {
		AssociationFigure a = figureList.getAssociationFromRelation(r);
		if (a != null) {
			a.setEndPoint(endPoint);
			a.setStartPoint(startPoint);
		}
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent umlComponent, Point point) {
		controller.setRootForRelation(rel, umlComponent, point);
	}

}
