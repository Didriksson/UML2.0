package UML.Components;

import java.io.Serializable;

import com.google.gson.JsonObject;

import ConstantsAndEnums.Constants;


public class UMLClassVariable extends UMLVariable implements Serializable {

	String modifiers;

	public UMLClassVariable(String scopeModifier, String type, String name) {
		super(type, name);
		this.modifiers = scopeModifier;
	}

	public String generateSourceString() {
		return modifiers + " " + super.generateSourceString();
	}

	public String toString() {

		String umlModify = "";
		switch (modifiers) {
		case (Constants.PUBLIC_RETURN_TYPE):
			umlModify = "+";
			break;
		case (Constants.PRIVATE_RETURN_TYPE):
			umlModify = "-";
			break;
		case (Constants.PROTECTED_RETURN_TYPE):
			umlModify = "#";
			break;
		case (Constants.PACKAGE_RETURN_TYPE):
			umlModify = "~";
			break;
		}

		return umlModify + " " + super.toString();
	}

	public String getModifiers() {
		return modifiers;
	}

	public void setScopeModifier(String scopeModifier) {
		this.modifiers = scopeModifier;

	}

	public JsonObject toJson() {
		JsonObject json = super.toJson();
		json.addProperty("modifier", this.modifiers);
		return json;

	}

}
