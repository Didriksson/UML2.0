package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import ConstantsAndEnums.Constants;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class Diagram extends Observable {
	private List<UMLComponent> components;
	private List<UMLRelation> relations;

	public Diagram() {
		this.components = new ArrayList<UMLComponent>();
		this.relations = new ArrayList<UMLRelation>();
}

	public void addComponent(UMLComponent c) {
		this.components.add(c);
		setChanged();
		notifyObservers(this);
	}

	public boolean removeComponent(UMLComponent c) {
		boolean operationOK = this.components.remove(c);
		setChanged();
		notifyObservers(this);
		return operationOK;
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

	public void newClassComponent(String name) {
		components.add(new UMLComponent(name, "Class"));
		setChanged();
		notifyObservers(this);
	}

	public void newRelation(String type) {
		relations.add(new UMLRelation(type));
		setChanged();
		notifyObservers(this);
	}

	public List<UMLRelation> getRelations() {
		return relations;
	}

	public void removeRelation(UMLRelation relation) {
		relations.remove(relation);
		setChanged();
		notifyObservers(this);
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent comp) {
		if(rel != null)
			rel.setDestination(comp);
		setChanged();
		notifyObservers(this);
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent comp) {
		if(rel != null)
			rel.setRoot(comp);
		setChanged();
		notifyObservers(this);		
	}
}
