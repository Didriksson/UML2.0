package Figures.Graphics;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class DependencyFigure extends AssociationFigure {

	private ArrowHeadFigure arrowHead;
	private Line2D.Double line;
	private float[] dash4 = {8f, 8f};
	
	public DependencyFigure(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
		
		line = super.getLine();
		arrowHead = new ArrowHeadFigure("none");
	}

	@Override
	protected void drawSpecific(Graphics g) {	
		BasicStroke bs4 = new BasicStroke(1, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 1.0f, dash4, 2f);
		
		Graphics2D g2 = (Graphics2D) g;	
		
		int startX = (int) line.x1;
		int startY = (int) line.y1;
		int endX = (int) line.x2;
		int endY = (int) line.y2;

		g2.setStroke(bs4);
		super.drawSpecific(g2);

		arrowHead.drawRoofHead(g2, startX, startY, endX, endY);
	}

	@Override
	public boolean encloses(int x, int y) {
		return (x <= 10 && y <= 10);
	}

}