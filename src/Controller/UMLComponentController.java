package Controller;

import java.io.Serializable;
import java.util.List;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;

public class UMLComponentController implements Serializable {

	private UMLComponent umlC;
	private transient UMLDrawAreaController drawController;
	private boolean variableState = false;
	private int indexOfVariableList = 0;
	private int indexOfMehodList = 0;

	public UMLComponentController(UMLComponent c, UMLDrawAreaController cont) {
		this.umlC = c;

		this.drawController = cont;
	}

	public String getClassName() {
		return umlC.getName();
	}

	public List<UMLClassVariable> getVariables() {
		return umlC.getVariables();
	}

	public List<UMLMethod> getMethods() {
		return umlC.getMethods();
	}

	public String getUMLtoString() {
		return umlC.toString();
	}

	public void setName(String name) {
		umlC.setName(name);
	}

	public void newMethod() {
		drawController.addMethod(umlC);
	}

	public void newVariable() {
		drawController.addVariable(umlC);
	}

	public void removeComponent() {
		drawController.removeComponent(umlC);
	}

	public UMLComponent getComponent() {
		return umlC;
	}

	public void setVariableState(boolean state) {
		this.variableState = state;
	}

	public boolean getVariableState() {
		return this.variableState;
	}

	public void setIndexOfVariableList(int index) {
		this.indexOfVariableList = index;
	}

	public int getIndexOfVariableList() {
		return this.indexOfVariableList;
	}

	public void setIndexOfMethodList(int index) {
		this.indexOfMehodList = index;
	}

	public int getIndexOfMethodList() {
		return this.indexOfMehodList;
	}

}
