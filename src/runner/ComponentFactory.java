package runner;

import UML.Components.UMLComponent;

public class ComponentFactory {
    public static UMLComponent getClassComponent(){
	return new UMLComponent("", "Class");
    }
    
    public static UMLComponentController getComponentController(){
	return new UMLComponentController(getClassComponent());
    }
}
