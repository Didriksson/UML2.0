package Controller;

import java.awt.Point;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class UMLRelationsComponentController {
	private UMLDrawAreaController drawController;
	
	public UMLRelationsComponentController(UMLDrawAreaController controller) {
		this.drawController = controller;
	}

	public void removeRelation(UMLRelation r) {
		drawController.removeRelation(r);
	}

	public void setDestinationForRelation(UMLRelation rel, UMLComponent c,
			Point destination) {
		drawController.setDestinationForRelation(rel, c, destination);
	}

	public void setRootForRelation(UMLRelation rel, UMLComponent c, Point point) {
		drawController.setRootForRelation(rel, c, point);
	}

	public Diagram getDiagram() {
		return drawController.getDiagram();
	}
}
