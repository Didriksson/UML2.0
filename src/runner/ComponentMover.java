package runner;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class ComponentMover extends MouseInputAdapter {
    Resizable res;
    
    public ComponentMover(Resizable res) {
		this.res = res;
	}
	
    @Override
    public void mouseMoved(MouseEvent me) {
        if (res.hasFocus()) {
            ResizableBorder border = (ResizableBorder) res.getBorder();
            res.setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    	res.setCursor(Cursor.getDefaultCursor());
    }

    private int cursor;
    private Point startPos = null;

    @Override
    public void mousePressed(MouseEvent me) {
        
        ResizableBorder border = (ResizableBorder) res.getBorder();
        cursor = border.getCursor(me);
        startPos = me.getPoint();
        res.requestFocus();
        res.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {

        if (startPos != null) {

            int x = res.getX();
            int y = res.getY();
            int w = res.getWidth();
            int h = res.getHeight();

            int dx = me.getX() - startPos.x;
            int dy = me.getY() - startPos.y;

            switch (cursor) {
                case Cursor.N_RESIZE_CURSOR:
                    if (!(h - dy < 50)) {
                    	res.setBounds(x, y + dy, w, h - dy);
                    	res.resize();
                    }
                    break;

                case Cursor.S_RESIZE_CURSOR:
                    if (!(h + dy < 50)) {
                    	res.setBounds(x, y, w, h + dy);
                        startPos = me.getPoint();
                        res.resize();
                    }
                    break;

                case Cursor.W_RESIZE_CURSOR:
                    if (!(w - dx < 50)) {
                    	res.setBounds(x + dx, y, w - dx, h);
                        res.resize();
                    }
                    break;

                case Cursor.E_RESIZE_CURSOR:
                    if (!(w + dx < 50)) {
                    	res.setBounds(x, y, w + dx, h);
                        startPos = me.getPoint();
                        res.resize();
                    }
                    break;

                case Cursor.NW_RESIZE_CURSOR:
                    if (!(w - dx < 50) && !(h - dy < 50)) {
                    	res.setBounds(x + dx, y + dy, w - dx, h - dy);
                    	res.resize();
                    }
                    break;

                case Cursor.NE_RESIZE_CURSOR:
                    if (!(w + dx < 50) && !(h - dy < 50)) {
                    	res.setBounds(x, y + dy, w + dx, h - dy);
                        startPos = new Point(me.getX(), startPos.y);
                        res.resize();
                    }
                    break;

                case Cursor.SW_RESIZE_CURSOR:
                    if (!(w - dx < 50) && !(h + dy < 50)) {
                    	res.setBounds(x + dx, y, w - dx, h + dy);
                        startPos = new Point(startPos.x, me.getY());
                        res.resize();
                    }
                    break;

                case Cursor.SE_RESIZE_CURSOR:
                    if (!(w + dx < 50) && !(h + dy < 50)) {
                    	res.setBounds(x, y, w + dx, h + dy);
                        startPos = me.getPoint();
                        res.resize();
                    }
                    break;

                case Cursor.MOVE_CURSOR:
                    Rectangle bounds = res.getBounds();
                    bounds.translate(dx, dy);
                    res.setBounds(bounds);
                    res.resize();
            }

            res.setCursor(Cursor.getPredefinedCursor(cursor));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        startPos = null;
    }
};