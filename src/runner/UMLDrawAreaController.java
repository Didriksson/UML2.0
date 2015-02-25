package runner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Command.NewClassComponentCommand;
import Command.RemoveClassComponentCommand;
import Figures.FigureViewingPanel;
import Figures.UMLComponent_GUI;
import UML.Components.UMLComponent;

public class UMLDrawAreaController extends Observable implements Observer {
    private Diagram diagram;
    List<UMLComponent_GUI> components;
    Map<UMLComponent, UMLComponent_GUI> componentToPointsMap;
    FigureViewingPanel drawArea;

    public UMLDrawAreaController(Diagram d) {
	this.diagram = d;
	d.addObserver(this);
	components = new ArrayList<UMLComponent_GUI>();
    }

    public void addUMLComponent(UMLComponent c) {
	diagram.addComponent(c);
    }

    public void newClass() {
	new NewClassComponentCommand(diagram).execute();
    }

    public void registerMeAsObserver(Observer o) {
	diagram.addObserver(o);
    }

    public List<UMLComponent_GUI> getAllComponents() {
	return components;
    }

    @Override
    public void update(Observable o, Object arg) {
	boolean removed = checkIfComponentIsRemovedAndRemoveFromList();
	System.out.println(removed);
	if (!removed) {
	    for (UMLComponent c : diagram.getComponents()) {
		if (checkIfNewComponent(c))
		    components.add(new UMLComponent_GUI(c, new Point(50, 50)));
	    }
	}

	setChanged();
	notifyObservers();

    }

    private boolean checkIfComponentIsRemovedAndRemoveFromList() {
	for (UMLComponent_GUI cGUI : components) {
	    if (!diagram.getComponents().contains(cGUI.getComponent())) {
		components.remove(cGUI);
		return true;
	    }
	}
	return false;
    }

    private boolean checkIfNewComponent(UMLComponent c) {
	boolean present = false;
	for (UMLComponent_GUI gui_comp : components) {
	    if (gui_comp.getComponent().equals(c)) {
		present = true;
		continue;
	    }
	}
	return !present;
    }

    public void removeComponent(UMLComponent_GUI c) {
	new RemoveClassComponentCommand(diagram, c.getComponent()).execute();
    }

}
