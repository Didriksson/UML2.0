package Figures.Graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class DirectAssociationFigure extends AssociationFigure {

	private ArrowHeadFigure arrowHead;
	private Line2D.Double line;
	
	public DirectAssociationFigure(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
		
		line = super.getLine();
		arrowHead = new ArrowHeadFigure("none");
	}

	@Override
	protected void drawSpecific(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		int startX = (int) line.x1;
		int startY = (int) line.y1;
		int endX = (int) line.x2;
		int endY = (int) line.y2;

		arrowHead.drawRoofHead(g2, startX, startY, endX, endY);
		super.drawSpecific(g2);
	}

	@Override
	public boolean encloses(int x, int y) {
		return (x <= 10 && y <= 10);
	}

}