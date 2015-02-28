package Figures.Graphics;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import Figures.FigureViewingPanel;
import UML.Components.UMLRelation;

public class FigureViewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private FigureViewingPanel topPanel;
	FigureList figureList;


	public FigureViewer(FigureViewingPanel fwp) {
		this.setLayout(null);
		this.topPanel = fwp;
		this.figureList = new FigureList();
		MouseInteraction mouseInteraction = new MouseInteraction(figureList,this);
		addMouseListener(mouseInteraction);
		addMouseMotionListener(mouseInteraction);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (UMLRelation r : topPanel.getRelation().keySet()) {
			if(!figureList.contains(r))
				addNewAssociationFigure(r);
		}
		
		for(AssociationFigure f : figureList)
			f.draw(g);
	}

	private void addNewAssociationFigure(UMLRelation r) {
		AssociationFigure figure = new AssociationFigure(topPanel
				.getRelation().get(r).get(0), topPanel.getRelation()
				.get(r).get(1));
		figureList.add(figure, r);
	}

}
