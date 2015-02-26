package runner;

import Figures.ClassFigure;
import UML.Components.UMLComponent;

public class ComponentFactory {
    public static UMLComponent getClassComponent(){
	return new UMLComponent("", "Class");
    }
    
    public static UMLComponentController getComponentController(){
	return new UMLComponentController(getClassComponent());
    }    
    
    public static UMLComponentController getComponentController(UMLComponent c){
	return new UMLComponentController(c);
    }
    
    public static ClassFigure getClassFigure(UMLComponent c){
    	return new ClassFigure(getComponentController(c));
    }

}
