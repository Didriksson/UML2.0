package runner;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;


public class Resizable extends JComponent {

	ComponentMover mouseListener;
	
    public Resizable(Component comp) {
        this(comp, new ResizableBorder(8));
    }

    public Resizable(Component comp, ResizableBorder border) {
        
    	mouseListener = new ComponentMover(this);
    	
        setLayout(new BorderLayout());
        add(comp);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setBorder(border);
    }
    
//    public void registerComponent(Component components) {
//		for (Component component : components)
//			component.addMouseListener( mouseListener );
//	}
    

    public void resize() {
        if (getParent() != null) {
            ((JComponent) getParent()).revalidate();
        }
    }
}

