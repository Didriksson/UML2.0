package Figures.Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;


public class RectangleMarkersFigure {

	private double recSize = 10.0;
	private Rectangle2D.Double[] rects;
    private boolean showRects = false;
    private Line2D.Double line;
   
	public RectangleMarkersFigure(Line2D.Double line) {
		this.line = line;
			
		rects = new Rectangle2D.Double[3];
	       
		for(int j = 0; j < rects.length; j++)
            rects[j] = new Rectangle2D.Double();
        
        rects[0] = new Rectangle2D.Double(this.line.getX1()-recSize/2, this.line.getY1()-recSize/2, recSize, recSize);
        rects[1] = new Rectangle2D.Double(this.line.getX2()-recSize/2, this.line.getY2()-recSize/2, recSize, recSize);
        rects[2] = new Rectangle2D.Double();
	}
	
	
	protected void drawSpecific(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
           
       
        g2.draw(line);
        
		drawRectangles(g2);	
	}

	private void drawRectangles(Graphics2D g2) {
		if(showRects) {
            g2.setPaint(Color.red);
            for(int j = 0; j < rects.length; j++)
                g2.draw(rects[j]);
        }
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
	 
	    public void setLine() {
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
