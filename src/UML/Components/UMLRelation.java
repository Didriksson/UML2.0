package UML.Components;

public class UMLRelation {
	private final String type;
	private UMLComponent root;
	private UMLComponent destination;

	public UMLRelation(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public UMLComponent getRoot() {
		return root;
	}

	public void setRoot(UMLComponent root) {
		this.root = root;
	}

	public UMLComponent getDestination() {
		return destination;
	}

	public void setDestination(UMLComponent destination) {
		this.destination = destination;
	}
}
