package Controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import GUI_View.MethodMenu;
import GUI_View.VariableMenu;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class UMLComponentController implements Serializable {

	private UMLComponent umlC;
	private transient UMLDrawAreaController drawController;
	private boolean variableState = false;
	private int indexOfVariableList = 0;
	private int indexOfMehodList = 0;
	private MethodMenu methodMenu;
	private VariableMenu variableMenu;

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

	public Set<String> getParameterList() {
		return drawController.getParameterList();
	}
	
	public void setVariableState(boolean state) {
		this.variableState = state;
	}

	public boolean getVariableState() {
		return this.variableState;
	}

	public void setIndexOfVariableList(int index, String fieldText, boolean isSelected) {
		this.indexOfVariableList = index;
		this.variableMenu.updateTextField(fieldText);
		this.variableMenu.setIsSelectedInList(isSelected);
	}

	public int getIndexOfVariableList() {
		return this.indexOfVariableList;
	}

	public void setIndexOfMethodList(int index, String fieldText, boolean isSelected) {
		this.indexOfMehodList = index;
		this.methodMenu.updateTextField(fieldText);
		this.methodMenu.setIsSelectedInList(isSelected);
	}

	public int getIndexOfMethodList() {
		return this.indexOfMehodList;
	}

	public void setVariableMenu(VariableMenu variableMenu) {
		 this.variableMenu = variableMenu;
	}
	
	public void setMethodMenu(MethodMenu methodMenu) {
		 this.methodMenu = methodMenu;
	}

}
