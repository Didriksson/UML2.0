package Figures.Graphics;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import ConstantsAndEnums.Constants;
import GUI_View.FigureViewingPanel;
import UML.Components.UMLRelation;

public class FigureViewer extends JPanel {
    private static final long serialVersionUID = 1L;
    FigureViewerData data = new FigureViewerData();

    public FigureViewer(FigureViewingPanel fwp) {
	this.setLayout(null);
	this.data.topPanel = fwp;
	this.data.figureList = new FigureList();
	MouseInteraction mouseInteraction = new MouseInteraction(data.figureList,
		this);
	addMouseListener(mouseInteraction);
	addMouseMotionListener(mouseInteraction);

    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for (UMLRelation r : data.topPanel.getRelation().keySet()) {
	    if (!data.figureList.contains(r))
		try {
		    addNewAssociationFigure(r);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	for (AssociationFigure f : data.figureList)
	    f.draw(g);
    }

    private void addNewAssociationFigure(UMLRelation r) throws Exception {
	Point start = data.topPanel.getRelation().get(r).get(0);
	Point endPoint = data.topPanel.getRelation().get(r).get(1);
	AssociationFigure figure = FigureFactory.getRelationsFigure(r.getType(), start, endPoint);
	data.figureList.add(figure, r);
    }
}
