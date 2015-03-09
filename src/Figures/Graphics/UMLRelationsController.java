package Figures.Graphics;

import Controller.UMLDrawAreaController;
import UML.Components.UMLRelation;

public class UMLRelationsController {
	private UMLDrawAreaController drawController;
	public UMLRelationsController(UMLDrawAreaController controller) {
		this.drawController = controller;
	}
	
	public void removeRelation(UMLRelation r){
		drawController.removeRelation(r);
	}
}
