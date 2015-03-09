package runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import ConstantsAndEnums.Constants;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;
import UML.Components.UMLVariable;

public class Diagram extends Observable {
	private List<UMLComponent> components;
	private List<UMLRelation> relations;
	private Set<String> parameterSet;
	
	
	public Diagram() {
		this.components = new ArrayList<UMLComponent>();
		this.relations = new ArrayList<UMLRelation>();
		this.parameterSet = new HashSet<String>();
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
		System.out.println("meeep");
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

	public UMLComponent newClassComponent(String name) {
		UMLComponent c = new UMLComponent(name, "Class");
		components.add(c);
		setChanged();
		notifyObservers(this);
		return c;
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
		if (rel != null)
			rel.setDestination(comp);
		setChanged();
		notifyObservers(this);
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent comp) {
		if (rel != null)
			rel.setRoot(comp);
		setChanged();
		notifyObservers(this);
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
	
	
	
}
