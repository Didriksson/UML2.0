package UML.Components;

import java.io.Serializable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UMLRelation implements Serializable{
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

	public JsonElement toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", type);
		json.add("root", root.toJson());
		json.add("destination", destination.toJson());
		return json;
	}

}
