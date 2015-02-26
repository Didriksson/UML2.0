package Figures;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;


public class Resizable extends JComponent{
	
	ComponentMover mouseListener;
	GUIComponent content;
	Point position;
    public Resizable(GUIComponent comp, Point p) {
        this(comp, new ResizableBorder(8), p);
    }

    public Resizable(GUIComponent comp, ResizableBorder border, Point p) {
        this.position = p;
    	mouseListener = new ComponentMover(this);
    	this.content = comp;
        setLayout(new BorderLayout());
        add(content);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(new KeyHandler());
        setBorder(border);
    	setBounds(p.x, p.y, 200, 200);
    }
    
    public void updatePosition(int x, int y){
    	this.position.x = x;
    	this.position.y = y;
    }
    
    public GUIComponent getGUIComponent(){
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
    
    public class KeyHandler implements KeyListener {
   	private Set<Integer> pressedKeys;

   	public KeyHandler() {
   	    pressedKeys = new HashSet<Integer>();
   	}

   	@Override
   	public void keyPressed(KeyEvent e) {
   	    pressedKeys.add(e.getKeyCode());
   	  if (pressedKeys.size() == 1) {
   		if (pressedKeys.contains(KeyEvent.VK_DELETE)) {
   		    System.out.println("Delete!");
   		    content.controller.removeComponent();
   		}
   	    }
   	}

   	@Override
   	public void keyReleased(KeyEvent e) {
   	    pressedKeys.remove(e.getKeyCode());
   	}

   	@Override
   	public void keyTyped(KeyEvent e) {

   	}

       }
    
}

