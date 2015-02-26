package Figures.Graphics;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import Figures.FigureViewingPanel;
import UML.Components.UMLRelation;

public class FigureViewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private FigureViewingPanel topPanel;

	public FigureViewer(FigureViewingPanel fwp) {
		this.setLayout(null);
		this.topPanel = fwp;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (UMLRelation r : topPanel.getRelation().keySet()) {
			BaseFigure figure = new AssociationFigure(topPanel.getRelation().get(r).get(0),
					topPanel.getRelation().get(r).get(1));
			figure.draw(g);
		}
	}
}
