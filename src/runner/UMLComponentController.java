package runner;

import java.awt.Point;
import java.util.List;

import Figures.UMLComponent_GUI;
import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;

public class UMLComponentController {

    UMLComponent_GUI umlC;
    UMLDrawAreaController drawController;

    public UMLComponentController(UMLComponent_GUI c, UMLDrawAreaController cont) {
	this.umlC = c; 
	this.drawController = cont;
    }

    public String getClassName() {
	return umlC.getComponent().getName();
    }

    public List<UMLClassVariable> getVariables() {
	return umlC.getComponent().getVariables();
    }

    public List<UMLMethod> getMethods() {
	return umlC.getComponent().getMethods();
    }

    public String getUMLtoString() {
	return umlC.getComponent().toString();
    }

    public void setName(String name) {
	umlC.getComponent().setName(name);
    }

    public void newMethod() {
	try {
	    umlC.getComponent().addMethod(new UMLMethod("public", "void", "MethodName"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void removeComponent() {
	drawController.removeComponent(umlC);
    }

    public void setPosition(Point location) {
	umlC.setPoint(location);
    }
    
    public Point getPosition() {
	return umlC.getPoint();
    }
}
