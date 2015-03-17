package Controller;

import java.awt.Point;

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

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c, Point destination) {
		drawController.setDestinationForRelation(rel, c, destination);
	}
	
	public void setRootForRelation(UMLRelation rel, UMLComponent c, Point point) {
		drawController.setRootForRelation(rel, c, point);
	}

	public void removeDestinationForRelation(UMLRelation rel) {
		drawController.removeDestinationForRelation(rel);
	}

	public void removeRootForRelation(UMLRelation rel) {
		drawController.removeRootForRelation(rel);
	}
}
