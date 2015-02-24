package Figures;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;


public class Resizable extends JComponent{
	
	ComponentMover mouseListener;
	GUIComponent content;
    public Resizable(GUIComponent comp, Point p) {
        this(comp, new ResizableBorder(8), p); 
    }

    public Resizable(GUIComponent comp, ResizableBorder border, Point p) {
        
    	mouseListener = new ComponentMover(this);
    	this.content = comp;
        setLayout(new BorderLayout());
        add(content);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setBorder(border);
    	setBounds(p.x, p.y, 200, 200);
    }
    
    
    public GUIComponent getComponent(){
    	return content;
    }

    public void resize() {
        if (getParent() != null) {
            ((JComponent) getParent()).revalidate();
        }
    }
    
    public boolean equals(Object o){
    	if(o instanceof Resizable)
    	{
    		Resizable r = (Resizable) o;
    	}
    	return false;
    }
    
}

