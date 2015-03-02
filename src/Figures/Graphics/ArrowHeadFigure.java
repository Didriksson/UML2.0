package Figures.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class ArrowHeadFigure {

	public static final String TRIANGLE = "Arrow.TRIANGLE";
	public static final String POLYGON = "Arrow.POLYGON";

	private int m_arrowLength = 15;
	private int m_arrowWidth = 15;
	private GeneralPath arrowhead;	

	public ArrowHeadFigure(String type) {	
		if(type == TRIANGLE)
			arrowhead = getTriangleArrow();
		else if(type == POLYGON)
			arrowhead = getPolygonArrow();		
	}

	public void drawChoosenHead(Graphics2D g2, int startX, int startY, int endX, int endY, boolean whiteShape) {
		Stroke oldStroke = g2.getStroke();
  		Point point1 = new Point(startX, startY);
 		Point point2 = new Point(endX, endY);

		double thetaRadians = Math.atan2(( point1.getY() - point2.getY()),(point1.getX() -
 				point2.getX()))+Math.PI;

		AffineTransform at = new AffineTransform();
		at.translate(point2.getX(), point2.getY());
		at.rotate(thetaRadians);
		Shape polygon = at.createTransformedShape(arrowhead);
		
		if(whiteShape) {
			g2.setPaint(Color.WHITE);
			g2.fill(polygon);
			g2.setPaint(Color.BLACK);
			g2.draw(polygon);
		} else {
			g2.setPaint(Color.BLACK);
			g2.fill(polygon);
		}
		
		g2.setStroke(oldStroke);

	}
		

	public void drawRoofHead(Graphics2D g2, double startX, double startY, double endX, double endY) {
		g2.setPaint(Color.BLACK);
		double phi = Math.toRadians(40);
		double barb = 15;
		double dy = startY - endY;
		double dx = startX - endX;
		double theta = Math.atan2(dy, dx);
		double x, y, rho = theta + phi;
			    
		for (int j = 0; j < 3; j++) {			
			x = startX - barb * Math.cos(rho);
			y = startY - barb * Math.sin(rho);
			g2.draw(new Line2D.Double(startX, startY, x, y));
			rho = theta - phi;	
		}
	}
	
	private GeneralPath getPolygonArrow() {
		GeneralPath arrow = new GeneralPath();
		float distance = 0;
		int width = (int) (m_arrowWidth / 2.0f);
		arrow.moveTo(distance, 0);
		arrow.lineTo((distance - m_arrowLength), width);
		arrow.lineTo((distance - (2*m_arrowLength)), 0);
		arrow.lineTo((distance - m_arrowLength), -width);
		arrow.lineTo((distance), 0);
		return arrow;
	}

	private GeneralPath getTriangleArrow() {
		GeneralPath arrow = new GeneralPath();
		float distance = 0;
		int width = (int) (m_arrowWidth / 2.0f);
		arrow.moveTo(distance, 0);
		arrow.lineTo((distance - m_arrowLength), width);
		arrow.lineTo((distance - m_arrowLength), -width);
		arrow.lineTo((distance), 0);
		return arrow;
	}

	public boolean encloses(int x, int y) {
	    // TODO Auto-generated method stub
	    return false;
	}
}