package Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import runner.UMLComponentController;
import runner.UMLDrawAreaController;
import ConstantsAndEnums.Constants;
import GUI_View.ToolbarUML;

public class FigureViewingPanel extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;

    private UMLDrawAreaController controller;
    private JPanel umlPanel;

    public FigureViewingPanel(UMLDrawAreaController controller) {
	this.setLayout(new MigLayout("fill", "grow", "grow"));
	this.setBorder(Constants.RAISED_BEVEL_BORDER);
	this.controller = controller;
	controller.addObserver(this);
	createComponents();

    }

    private void createComponents() {
	umlPanel = new JPanel(null);

	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent me) {
		requestFocus();
		repaint();
	    }
	});
	this.addKeyListener(new KeyHandler());
	setComponents();
    }

    private void setComponents() {
	umlPanel.setBackground(Color.WHITE);
	

	this.add(umlPanel, "dock center");
	this.add(new ToolbarUML(this), "dock west");
    }

    public void createFigures() {
	controller.newClass();
    }

    private void addComponentToDrawArea(UMLComponent_GUI c) {
	umlPanel.add(new Resizable(new ClassFigure(new UMLComponentController(c, controller))));
    }

    public void paintComponent(final Graphics g) {
	super.paintComponent(g);
	if (Constants.FIGURE_VIEWER_BACKGROUND != null)
	    g.drawImage(Constants.FIGURE_VIEWER_BACKGROUND, 0, 0, getWidth(),
		    getHeight(), this);

    }

    @Override
    public void update(Observable o, Object arg) {
	umlPanel.removeAll();
	List<UMLComponent_GUI> components = controller.getAllComponents();
	components.stream().forEach(c -> addComponentToDrawArea(c));
	repaint();
	revalidate();
    }

    public class KeyHandler implements KeyListener {
   	private Set<Integer> pressedKeys;

   	public KeyHandler() {
   	    pressedKeys = new HashSet<Integer>();
   	}

   	@Override
   	public void keyPressed(KeyEvent e) {
   	    pressedKeys.add(e.getKeyCode());
   	    if (pressedKeys.size() > 1) {
   		if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
   		    if (pressedKeys.contains(KeyEvent.VK_C))
   			System.out.println("Kopiera!");
   		}
   	    }

   	    else if (pressedKeys.size() == 1) {
   		if (pressedKeys.contains(KeyEvent.VK_DELETE)) {
   		    System.out.println("Delete!");
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
