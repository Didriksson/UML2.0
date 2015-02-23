package runner;

import java.util.List;

import javax.swing.ListModel;

import UMLComponents.UMLClassVariable;
import UMLComponents.UMLComponent;
import UMLComponents.UMLMethod;

public class UMLController {

	UMLComponent umlC;
	
	public UMLController() {
		umlC = new UMLComponent("TestUML", "class");
		
		umlC.addVariable("private", "int", "number");
		umlC.addVariable("public", "String", "name");
		umlC.addVariable("", "List<JComponents>", "listComponents");
		
		
		try {
			
			umlC.addMethod(new UMLMethod("public", "String", "getName"));

			umlC.addMethod(new UMLMethod("private", "int", "getNumber"));

			umlC.addMethod(new UMLMethod("protected", "List", "getList"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
}
