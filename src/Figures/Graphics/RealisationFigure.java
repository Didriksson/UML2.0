package Figures.Graphics;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import Controller.RelationController;

public class RealisationFigure extends AssociationFigure {

	private ArrowHeadFigure arrowHead;
	private float[] dash4 = {8f, 8f};
	
	public RealisationFigure(RelationController controller, Point startPoint, Point endPoint) {
		super(controller, startPoint, endPoint);
		arrowHead = new ArrowHeadFigure("Arrow.TRIANGLE");
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
		
		arrowHead.drawChoosenHead(g2, startX, startY, endX, endY, true);
	
	}

	@Override
	public String getDestinationMulString(){
	    return "";
	    
	}
	
	@Override
	public String getRootMulString(){
	    return "";
	    
	}
	
	@Override
	public boolean encloses(int x, int y) {
	    return super.encloses(x, y) || arrowHead.encloses(x,y);
	}

}