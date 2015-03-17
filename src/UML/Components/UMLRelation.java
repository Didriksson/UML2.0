package UML.Components;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UMLRelation implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1888514158819310180L;
    private final String type;
    private UMLComponent root;
    private UMLComponent destination;
    private String multiplicityRoot;
    private String multiplicityDestination;

    public UMLRelation(String type) {
	this.type = type;
	multiplicityDestination = "";
	multiplicityRoot = "";
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

    public JsonElement toJson() {
	JsonObject json = new JsonObject();
	json.addProperty("type", type);
	json.add("root", root.toJson());
	json.add("destination", destination.toJson());
	return json;
    }

    public String toString() {
	String rootName = "";
	String destName = "";

	if (root != null)
	    rootName = root.getName();
	if (destination != null)
	    destName = destination.getName();

	return "Relation type: " + type + "\n" + "Root element: " + rootName
		+ "\n" + "Destination element: " + destName + "\n";

    }

    public String getMultiplicityRoot() {
	return multiplicityRoot;
    }

    public String getMultiplicityDestination() {
	return multiplicityDestination;
    }

    public void setMultiplicityRoot(String multiplicityRoot) {
	this.multiplicityRoot = multiplicityRoot;
    }

    public void setMultiplicityDestination(String multiplicityDestination) {
	this.multiplicityDestination = multiplicityDestination;
    }

}
