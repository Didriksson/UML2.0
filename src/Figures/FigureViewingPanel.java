package Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import runner.Diagram;
import Command.ICommand;
import ConstantsAndEnums.Constants;
import ConstantsAndEnums.Enums;
import Controller.UMLComponentController;
import Controller.UMLDrawAreaController;
import Figures.Graphics.AssociationFigure;
import Figures.Graphics.BaseFigure;
import Figures.Graphics.FigureViewer;
import GUI_View.ToolbarUML;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class FigureViewingPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private UMLDrawAreaController controller;
	private FigureViewer umlPanel;
	private Map<UMLComponent, Point> components;
	private Map<UMLRelation, List<Point>> relations;

	public FigureViewingPanel(UMLDrawAreaController controller) {
		this.setLayout(new MigLayout("fill", "grow", "grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		this.controller = controller;
		this.components = new HashMap<UMLComponent, Point>();
		this.relations = new HashMap<UMLRelation, List<Point>>();
		controller.registerMeAsObserver(this);
		createComponents();

	}

	private void createComponents() {
		umlPanel = new FigureViewer(this);

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

	private void addComponentToDrawArea(UMLComponent c) {
		umlPanel.add(new Resizable(new ClassFigure(new UMLComponentController(
				c, controller)), components.get(c)));
	}

	@Override
	public void update(Observable o, Object arg) {
		umlPanel.removeAll();
		Diagram d = (Diagram) o;
		updateUMLComponents(d);
		updateUMLRelations(d);

		umlPanel.repaint();
		umlPanel.revalidate();
	}

	private void updateUMLRelations(Diagram d) {
		List<UMLRelation> diagramComponents = d.getRelations();
		diagramComponents.stream().filter(c -> !relations.containsKey(c))
				.forEach(c -> {
					List<Point> points = new ArrayList<Point>();
					UMLComponent root = c.getRoot();
					UMLComponent dest = c.getDestination();

					if (root != null)
						points.add(components.get(root));
					else
						points.add(new Point(50, 50));

					if (dest != null)
						points.add(components.get(dest));
					else
						points.add(new Point(100, 100));
					
					relations.put(c , points);
				});
	}

	private void updateUMLComponents(Diagram d) {
		List<UMLComponent> diagramComponents = d.getComponents();
		diagramComponents.stream().filter(c -> !components.containsKey(c))
				.forEach(c -> components.put(c, new Point(50, 50)));

		Set<UMLComponent> tmpComponents = new HashSet<UMLComponent>(
				components.keySet());
		tmpComponents.removeAll(diagramComponents);
		components.keySet().removeAll(tmpComponents);
		addUMLComponents();
	}

	public Map<UMLRelation, List<Point>> getRelation() {
		return relations;
	}

	private void addUMLComponents() {
		components.keySet().forEach(c -> {
			addComponentToDrawArea(c);
		});
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

	public void toolbarCommand(Enums enumeration) {
		controller.toolbarCommands(enumeration);
	}

}
