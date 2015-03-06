package GUI_View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;
import runner.Diagram;
import ConstantsAndEnums.Constants;
import ConstantsAndEnums.Enums;
import Controller.UMLComponentController;
import Controller.UMLDrawAreaController;
import Controller.UMLRelationsController;
import Figures.ClassFigure;
import Figures.Resizable;
import Figures.Graphics.RelationsDrawer;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class FigureViewingPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private UMLDrawAreaController controller;
	private RelationsDrawer relationPanel;
	private Map<UMLComponent, Point> components;
	private Map<UMLComponent, Resizable> resizables;
	private Map<UMLRelation, UMLRelationPoints> relations;
	private ComponentManipulationToolbar componentTools;

	public FigureViewingPanel(UMLDrawAreaController controller) {
		this.setLayout(new MigLayout("fill", "grow", "grow"));
		this.setBorder(Constants.RAISED_BEVEL_BORDER);
		this.controller = controller;
		this.controller.setViewPanel(this);
		this.components = new HashMap<UMLComponent, Point>();
		this.relations = new HashMap<UMLRelation, UMLRelationPoints>();
		this.resizables = new HashMap<UMLComponent, Resizable>();
		this.componentTools = new ComponentManipulationToolbar();
		controller.registerMeAsObserver(this);
		createComponents();

	}

	private void createComponents() {
		relationPanel = new RelationsDrawer(this, new UMLRelationsController(
				controller));
		setComponents();
		setUpKeyBinding();
	}

	private void setUpKeyBinding() {
		Action doNothing = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deletar!" + "FigureViewingPanel");
			}
		};

		Action printPointRelations = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				for (UMLRelationPoints r : relations.values()) {
					System.out.println("Start: " + r.start + "   " + "End: "
							+ r.end);
					System.out.println(System.identityHashCode(r.end));

				}
			}
		};

		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("DELETE"), "doNothing");
		this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke("F3"), "printPointRelations");
		this.getActionMap().put("printPointRelations", printPointRelations);

	}

	private void setComponents() {
		this.relationPanel.setBackground(Color.WHITE);
		this.add(relationPanel, "dock center");
		this.add(new ToolbarUML(this), "dock west");
		this.add(componentTools, "dock south");
	}

	// public void createFigures() {
	// controller.newClass();
	// }

	private void addComponentToDrawArea(UMLComponent c) {

		if (!resizables.containsKey(c))
			resizables.put(c, new Resizable(this, new ClassFigure(new UMLComponentController(c, controller)), null, components.get(c)));
		relationPanel.add(resizables.get(c));

	}

	@Override
	public void update(Observable o, Object arg) {
		relationPanel.removeAll();
		Diagram d = (Diagram) o;
		updateUMLComponents(d);
		updateUMLRelations(d);
		this.repaint();
		this.revalidate();

	}

	public void updateUMLRelations(Diagram d) {

		List<UMLRelation> diagramRelations = d.getRelations();
		diagramRelations.stream().filter(c -> !relations.containsKey(c))
				.forEach(c -> {
					UMLRelationPoints points = new UMLRelationPoints();
					points.start = new Point(50, 50);
					points.end = new Point(100, 50);
					relations.put(c, points);
				});

		diagramRelations.forEach(c -> {
			relationPanel.updateCoordinats(c, relations.get(c).start,
					relations.get(c).end);
		});

		Set<UMLRelation> tmpRelations = new HashSet<UMLRelation>(
				relations.keySet());
		tmpRelations.removeAll(diagramRelations);
		relations.keySet().removeAll(tmpRelations);

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

	public Map<UMLRelation, UMLRelationPoints> getRelation() {
		return relations;
	}

	private void addUMLComponents() {
		components.keySet().forEach(c -> {
			addComponentToDrawArea(c);
		});
	}

	public void toolbarCommand(Enums enumeration) {
		controller.toolbarCommands(enumeration);
	}

	public boolean checkIfRelationOverlaps(Point p) {
		boolean cont = false;
		for (Component comp : relationPanel.getComponents()) {
			if (comp instanceof Resizable) {
				Resizable res = (Resizable) comp;
				res.setHoveredState(false);
				if (res.contains(p)) {
					cont = true;
					res.setHoveredState(true);
				}
			}
		}
		return cont;
	}

	public Resizable returnOverlapsedComponent(Point p) {
		Resizable res = null;
		for (Component comp : relationPanel.getComponents()) {
			if (comp instanceof Resizable) {
				if (comp.contains(p)) {
					res = (Resizable) comp;
					res.setHoveredState(true);
				}
			}
		}
		return res;
	}

	public void setComponentSelected(Resizable resizable) {
		componentTools.setSelectedComponent(resizable.getGUIComponent());
	}

	public void redoCommand() {
		controller.redoCommand();
	}

	public void undoCommand() {
		controller.undoCommand();
	}

}
