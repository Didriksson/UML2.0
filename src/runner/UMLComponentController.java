package runner;

import java.util.List;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class UMLComponentController {

    UMLComponent umlC;

    public UMLComponentController(UMLComponent c) {
	this.umlC = c; 
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
	System.out.println(name);
	umlC.setName(name);
    }

    public void newMethod() {
	try {
	    umlC.addMethod(new UMLMethod("public", "void", "MethodName"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
