package GUI_View;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.Serializable;
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

public class FigureViewingPanel extends JPanel implements Observer,
	Serializable {
    private static final long serialVersionUID = 1L;

    private FigureViewingPanelPositionData dataPosition;

    public ComponentManipulationToolbar componentTools;
    public UMLDrawAreaController controller;
    public RelationsDrawer relationPanel;

    public FigureViewingPanel(UMLDrawAreaController controller) {
	this.setLayout(new MigLayout("fill", "grow", "grow"));
	this.setBorder(Constants.RAISED_BEVEL_BORDER);
	this.setDataPosition(new FigureViewingPanelPositionData());
	this.controller = controller;
	this.controller.setViewPanel(this);
	createDataPositions();
	this.componentTools = new ComponentManipulationToolbar();
	controller.registerMeAsObserver(this);
	createComponents();
    }

    private void createDataPositions() {
	this.getDataPosition().components = new HashMap<UMLComponent, Point>();
	this.getDataPosition().relations = new HashMap<UMLRelation, UMLRelationPoints>();
	this.getDataPosition().resizables = new HashMap<UMLComponent, Resizable>();
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

	this.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
		KeyStroke.getKeyStroke("DELETE"), "doNothing");
    }

    private void setComponents() {
	this.add(componentTools, "dock south");
	this.add(relationPanel, "grow");
	this.add(new ToolbarUML(this), "dock west");
    }

    private void addComponentToDrawArea(UMLComponent c) {
	if (!getDataPosition().resizables.containsKey(c))
	    addComponentToResizeableList(c);
	relationPanel.add(getDataPosition().resizables.get(c));
    }

    private void addComponentToResizeableList(UMLComponent c) {
	getDataPosition().resizables.put(c, new Resizable(this,
		new ClassFigure(componentTools, new UMLComponentController(c, controller)),getDataPosition().components.get(c)));
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
	diagramRelations.stream()
		.filter(c -> !getDataPosition().relations.containsKey(c))
		.forEach(c -> {
		    UMLRelationPoints points = new UMLRelationPoints();
		    points.start = new Point(50, 50);
		    points.end = new Point(100, 50);
		    getDataPosition().relations.put(c, points);
		});

	diagramRelations.forEach(c -> {
	    relationPanel.updateCoordinats(c,
		    getDataPosition().relations.get(c).start,
		    getDataPosition().relations.get(c).end);
	});

	Set<UMLRelation> tmpRelations = new HashSet<UMLRelation>(
		getDataPosition().relations.keySet());
	tmpRelations.removeAll(diagramRelations);
	getDataPosition().relations.keySet().removeAll(tmpRelations);

    }

    private void updateUMLComponents(Diagram d) {
	List<UMLComponent> diagramComponents = d.getComponents();
	diagramComponents
		.stream()
		.filter(c -> !getDataPosition().components.containsKey(c))
		.forEach(
			c -> getDataPosition().components.put(c, new Point(50,
				50)));

	Set<UMLComponent> tmpComponents = new HashSet<UMLComponent>(
		getDataPosition().components.keySet());
	tmpComponents.removeAll(diagramComponents);
	getDataPosition().components.keySet().removeAll(tmpComponents);
	addUMLComponents();
    }

    public Map<UMLRelation, UMLRelationPoints> getRelation() {
	return getDataPosition().relations;
    }

    private void addUMLComponents() {
	getDataPosition().components.keySet().forEach(c -> {
	    addComponentToDrawArea(c);
	});
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

    public void setComponentSelected(Resizable resizable) {
	componentTools.setSelectedComponent(resizable.getGUIComponent());
    }

    public void redoCommand() {
	controller.redoCommand();
    }

    public FigureViewingPanelPositionData getDataPosition() {
	return dataPosition;
    }

    public void undoCommand() {
	controller.undoCommand();
    }

    public void toolbarCommand(Enums enumeration) {
	controller.toolbarCommands(enumeration);
    }

    public void hideToolbar() {
	componentTools.hideToolbar();
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

    public void setDataPosition(FigureViewingPanelPositionData dataPosition) {
	this.dataPosition = dataPosition;
	this.dataPosition.checkForMissingPositionValues();

	this.dataPosition.components.keySet().forEach(
		c -> addComponentToResizeableList(c));

	this.dataPosition.relations.keySet().forEach(
		r -> {
		    UMLRelationPoints points = this.dataPosition.relations
			    .get(r);

		    Resizable root = this.dataPosition.resizables.get(r
			    .getRoot());
		    Resizable destination = this.dataPosition.resizables.get(r
			    .getDestination());

		    if (root != null)
			points.start = root
				.getSnapPointFromPosition(points.start);

		    if (destination != null)
			points.end = destination
				.getSnapPointFromPosition(points.end);

		});

    }
}