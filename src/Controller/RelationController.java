package Controller;

import runner.Diagram;
import UML.Components.UMLComponent;
import UML.Components.UMLRelation;

public class RelationController {

	private UMLRelation relation;
	private UMLDrawAreaController controller;
	
	
	public RelationController(UMLRelation relation, UMLDrawAreaController controller) {
		this.relation = relation;
		this.controller = controller;
	}

	public String getDestinationMultiplicity() {
		return relation.getMultiplicityDestination();
	}

	public String getRootMultiplicity() {
		return relation.getMultiplicityRoot();
	}
	
	public void updateMulti(String root, String dest) {
		controller.updateMultiplicites(relation, root, dest);
	}
	
	public UMLComponent getRootComponent() {
		return relation.getRoot();
	}
	
	public UMLComponent getDestinationComponent() {
		return relation.getDestination();
	}
	

}
