package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import UML.Components.UMLComponent;

public class Diagram extends Observable {
    List<UMLComponent> components;

    public Diagram() {
	this.components = new ArrayList<UMLComponent>();
    }

    public void addComponent(UMLComponent c) {
	this.components.add(c);
	setChanged();
	notifyObservers();
    }

    public boolean removeComponent(UMLComponent c) {
	components.forEach(co -> System.out.println(co));
	boolean operationOK = this.components.remove(c);
	setChanged();
	notifyObservers();
	return operationOK;
    }

    public boolean removeComponent(String name) {
	setChanged();
	notifyObservers();
	return components.removeIf(c -> c.equals(name));
    }

    public UMLComponent getComponent(int n) {
	return this.components.get(n);
    }

    public List<UMLComponent> getComponents() {
	return this.components;
    }

    public UMLComponent getComponent(String name) throws Exception {
	Optional<UMLComponent> o = components.stream()
		.filter(c -> c.equals(name)).findFirst();
	if (!o.isPresent())
	    throw new Exception("Could not find component");

	return o.get();
    }

    public void newClassComponent(String name) {
	components.add(new UMLComponent(name, "Class"));
	System.out.println(components.size());
	setChanged();
	notifyObservers();
    }

}
