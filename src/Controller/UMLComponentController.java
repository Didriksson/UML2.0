package Controller;

import java.util.List;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLRelation;

public class UMLComponentController {

	UMLComponent umlC;
	UMLDrawAreaController drawController;

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
		try {
			umlC.addMethod(new UMLMethod("public", "void", "MethodName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeComponent() {
		drawController.removeComponent(umlC);
	}

	public UMLComponent getComponent() {
		return umlC;
	}
	
}
