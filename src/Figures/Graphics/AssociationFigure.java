package Figures.Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class AssociationFigure extends GeomatricPosition {

	private double recSize = 10.0;

	protected Line2D.Double line;
	private Rectangle2D.Double[] rects;
	protected boolean selected;
	protected Point startPoint, endPoint;

	public AssociationFigure(Point startPoint, Point endPoint) {
		super(startPoint.x, startPoint.y);
		this.selected = false;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		line = new Line2D.Double(startPoint, endPoint);
		rects = new Rectangle2D.Double[3];

		for (int j = 0; j < rects.length; j++)
			rects[j] = new Rectangle2D.Double();
		
		updateRects();

	}

	@Override
	protected void drawSpecific(Graphics g) {
		updateRects();
		setLine();
		

		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setRenderingHints(rh);

		g2.setPaint(Color.BLACK);
		g2.draw(line);

		g2.setStroke(new BasicStroke());

		drawRects(g2);
	}

	private void updateRects() {
		rects[0] = new Rectangle2D.Double(startPoint.x - recSize / 2,
				startPoint.y - recSize / 2, recSize, recSize);
		rects[1] = new Rectangle2D.Double(endPoint.x - recSize / 2, endPoint.y
				- recSize / 2, recSize, recSize);
		rects[2] = new Rectangle2D.Double();
		setCenter();		
	}

	private void drawRects(Graphics2D g2) {
		if (selected) {
			g2.setPaint(Color.red);
			for (int j = 0; j < rects.length; j++)
				g2.draw(rects[j]);
		}
	}

	public void setSelected(boolean show) {
		this.selected = show;
	}

	public boolean getSelected() {
		return this.selected;
	}

	private void setLine() {
		line.setLine(getCenter(rects[0]), getCenter(rects[1]));
		setCenter();
	}

	private Point2D.Double getCenter(Rectangle2D.Double r) {
		return new Point2D.Double(r.getCenterX(), r.getCenterY());
	}

	private void setCenter() {
		double cx = line.getX1() + (line.getX2() - line.getX1()) / 2;
		double cy = line.getY1() + (line.getY2() - line.getY1()) / 2;
		rects[2].setFrameFromCenter(cx, cy, cx + recSize / 2, cy + recSize / 2);
	}

	public Double[] getRects() {
		return rects;
	}

	public Line2D.Double getLine() {
		return line;
	}

	public boolean getShowRects() {
		return selected;
	}

	@Override
	public boolean encloses(int x, int y) {
		double inLIne = Math.abs(Line2D.Double.ptLineDist(line.x1, line.y1,
				line.x2, line.y2, x, y));
		if (inLIne <= 3.0)
			return true;
		else
			return false;
	}

	public boolean enclosesRects(int x, int y) {
		return rects[0].contains(x, y) || rects[1].contains(x, y)
				|| rects[2].contains(x, y);
	}
	
	public Point getStartPoint(){
		return startPoint;
	}
	public Point getEndPoint(){
		return endPoint;
	}

	public void setEndPoint(Point endPoint2) {
		if(endPoint2 != null)
			this.endPoint = endPoint2;
	}

	public void setStartPoint(Point startPoint2) {
		if(startPoint2 != null)
			this.startPoint = startPoint2;
	}

	public void updatePosition(int selectedRect, int x, int y) {
		switch(selectedRect){
		case 0:
			startPoint.x = (int) x;
			startPoint.y = (int) y;
			break;
		case 1:
			endPoint.x = (int) x;
			endPoint.y = (int) y;
			break;
		case 2:
			double dy = y - rects[2].y;
			double dx = x - rects[2].x;
			startPoint.x += dx;
			startPoint.y += dy;
			endPoint.x += dx;
			endPoint.y += dy;
			break;
		}
	}
	
}
