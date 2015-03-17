package UML.Components;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UMLVariable implements Serializable{
	private String variableName;
	private String type;

	public UMLVariable(String type, String name) {
		this.variableName = name;
		this.setType(type);
	}

	public String getName() {
		return variableName;
	}

	public void setvariableName(String methodName) {
		this.variableName = methodName;
	}

	public String toString() {
		return variableName + " : " + getType();
	}

	public String getType() {
		return this.type;
	}

	public String generateSourceString() {
		return getType() + " " + variableName;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("name", variableName);
		json.addProperty("type", getType());
		return json;
	}

	public void setType(String type) {
		this.type = type;
	}
}
