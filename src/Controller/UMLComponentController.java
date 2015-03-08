package Controller;

import java.util.List;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;

public class UMLComponentController {

	private UMLComponent umlC;
	private UMLDrawAreaController drawController;
	private int indexOfList = 0;
	private boolean variableState;

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

	public void setIndexOfList(int index) {
		this.indexOfList = index;
	}
	
	public int getIndexOfList() {
		return this.indexOfList;
	}

	public void setVariableState(boolean state) {
		this.variableState = state;
		
	}
	
	public boolean getVariableState() {
		return this.variableState;
	}


	
}
