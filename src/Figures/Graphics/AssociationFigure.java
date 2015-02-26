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
    
	private Line2D.Double line;
	private Rectangle2D.Double[] rects;
    private boolean showRects = false;

	public AssociationFigure(Point startPoint, Point endPoint) {
		super(startPoint.x, startPoint.y);

		line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, startPoint.y);
		rects = new Rectangle2D.Double[3];
       
		for(int j = 0; j < rects.length; j++)
            rects[j] = new Rectangle2D.Double();
        
        rects[0] = new Rectangle2D.Double(startPoint.x-recSize/2, startPoint.y-recSize/2, recSize, recSize);
        rects[1] = new Rectangle2D.Double(endPoint.x-recSize/2, startPoint.y-recSize/2, recSize, recSize);
        rects[2] = new Rectangle2D.Double();
	}

	@Override
	protected void drawSpecific(Graphics g) {	
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);
        
        g2.setPaint(Color.BLACK);
        g2.draw(line);        
        
        g2.setStroke(new BasicStroke());
        drawRects(g2);
	}

	private void drawRects(Graphics2D g2) {
		if(showRects) {
            g2.setPaint(Color.red);
            for(int j = 0; j < rects.length; j++)
                g2.draw(rects[j]);
        }
	}

	@Override
	public boolean encloses(int x, int y) {		
		return (x <= 10 && y <= 10) ;
	}
	
	
    public void setShowRects(boolean show) {
        showRects = show;
    }
 
    public void setRect(int index, double x, double y) {
        switch(index) {
            case 2:
                double dy = y - rects[2].y;
                double dx = x - rects[2].x;
                rects[0].setFrame(rects[0].x+dx, rects[0].y+dy, recSize, recSize);
                rects[1].setFrame(rects[1].x+dx, rects[1].y+dy, recSize, recSize);
                break;
            default:
                rects[index].setFrame(x, y, recSize, recSize);
        }
        setLine();
    }
 
    private void setLine() {
        line.setLine(getCenter(rects[0]), getCenter(rects[1]));
        setCenter();
    }
 
    private Point2D.Double getCenter(Rectangle2D.Double r) {
        return new Point2D.Double(r.getCenterX(), r.getCenterY());
    }
 
    private void setCenter() {
        double cx = line.getX1() + (line.getX2() - line.getX1())/2;
        double cy = line.getY1() + (line.getY2() - line.getY1())/2;
        rects[2].setFrameFromCenter(cx, cy, cx+recSize/2, cy+recSize/2);
    }

	
	public Double[] getRects() {
		return rects;
	}

	public Line2D.Double getLine() {
		return line;
	}

	public boolean getShowRects() {
		return showRects;
	}
	
}
