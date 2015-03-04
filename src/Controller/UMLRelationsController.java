package Controller;

import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class UMLRelationsController {
	private UMLDrawAreaController drawController;
	public UMLRelationsController(UMLDrawAreaController controller) {
		this.drawController = controller;
	}
	
	public void removeRelation(UMLRelation r){
		drawController.removeRelation(r);
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c) {
		drawController.setDestinationForRelation(rel, c);
	}
	
	public void setRootForRelation(UMLRelation rel, UMLComponent c) {
		drawController.setRootForRelation(rel, c);
	}
}
