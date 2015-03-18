package runner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;

public class Diagram extends Observable implements Serializable {
	private List<UMLComponent> components;
	private List<UMLRelation> relations;
	private Set<String> parameterSet;

	public Diagram() {
		this.components = new ArrayList<UMLComponent>();
		this.relations = new ArrayList<UMLRelation>();
		this.parameterSet = new TreeSet<String>();
		addingTypesToParameterSet();
	}

	
	private void addingTypesToParameterSet() {
		parameterSet.add("byte");
		parameterSet.add("short");
		parameterSet.add("int");
		parameterSet.add("long");
		parameterSet.add("float");
		parameterSet.add("double");
		parameterSet.add("char");
		parameterSet.add("String");		
		parameterSet.add("boolean");
	}
	
	public void addComponent(UMLComponent c) {
		this.components.add(c);
		setChanged();
		notifyObservers(this);
		signalUpdate();
	}

	public void removeObserver(Observer o) {
		this.removeObserver(o);
	}

	public boolean removeComponent(UMLComponent c) {
		boolean operationOK = this.components.remove(c);
		setChanged();
		notifyObservers(this);
		signalUpdate();
		return operationOK;
	}

	public boolean removeComponent(String name) {
		setChanged();
		notifyObservers(this);
		signalUpdate();
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

		System.out.println(o.get());
		return o.get();
	}

	public UMLComponent newClassComponent(String name) {
		UMLComponent c = new UMLComponent(name, "Class");
		components.add(c);
		setChanged();
		notifyObservers(this);
		return c;
	}


	public UMLRelation newRelation(String type) {
		UMLRelation relation = new UMLRelation(type);
		relations.add(relation);
		signalUpdate();
		return relation;
	}

	public List<UMLRelation> getRelations() {
		return relations;
	}


	public boolean removeRelation(UMLRelation relation) {
		boolean operationOK = relations.remove(relation);
		signalUpdate();
		return operationOK;
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent comp) {
		if (rel != null)
			rel.setDestination(comp);
		setChanged();
		notifyObservers(this);
		signalUpdate();
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent comp) {
		if (rel != null)
			rel.setRoot(comp);
		setChanged();
		notifyObservers(this);
		signalUpdate();
	}

	public void addMethod(UMLComponent c, UMLMethod m) throws Exception {
		c.addMethod(m);
	}

	public void addClassVariable(UMLComponent c, UMLClassVariable v) {
		c.addVariable(v);
	}

	public Set<String> getParametersForMethod() {
		return parameterSet;
	}
	
	public void addRelation(UMLRelation r) {
		relations.add(r);
		signalUpdate();
	}

	@Override
	public String toString() {
		String tmp = "";
		for (UMLComponent c : components)
			tmp = tmp + " " + c.toString();

		for (UMLRelation r : relations)
			tmp = tmp + " " + r.toString();
		return tmp;
	}

	public void signalUpdate() {
		this.setChanged();
		this.notifyObservers(this);
	}
}
