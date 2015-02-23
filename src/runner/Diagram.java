package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import UML.Components.UMLComponent;

public class Diagram {
    List<UMLComponent> components;

    public Diagram() {
	this.components = new ArrayList<UMLComponent>();
    }

    public void addComponent(UMLComponent c) {
	this.components.add(c);
    }
    
    public boolean removeComponent(UMLComponent c) {
	return this.components.remove(c);
    }
    
    public boolean removeComponent(String name) {
	return components.removeIf(c-> c.equals(name));
    }

    public UMLComponent getComponent(int n) {
	return this.components.get(n);
    }

    public UMLComponent getComponent(String name) throws Exception {
	Optional<UMLComponent> o = components.stream()
		.filter(c -> c.equals(name)).findFirst();
	if (!o.isPresent())
	    throw new Exception("Could not find component");

	return o.get();
    }

}
