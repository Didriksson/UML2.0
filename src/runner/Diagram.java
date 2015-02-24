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
		notifyObservers(this);
	}

	public boolean removeComponent(UMLComponent c) {
		setChanged();
		notifyObservers(this);
		return this.components.remove(c);
	}

	public boolean removeComponent(String name) {
		setChanged();
		notifyObservers(this);
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

}
