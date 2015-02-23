package runner;

import UML.Components.UMLComponent;

public class UMLDrawAreaController {
    private Diagram diagram;
    
    
    public UMLDrawAreaController() {
	this.diagram = new Diagram();
    }

    public void addUMLComponent(UMLComponent c) {
	diagram.addComponent(c);
    }
    
    public void removeUMLComponent(UMLComponent c) {
	diagram.addComponent(c);
    }
}
