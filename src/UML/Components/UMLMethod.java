package UML.Components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ConstantsAndEnums.Constants;

public class UMLMethod implements Comparable<UMLMethod>, Serializable{
	String methodName;
	String returnType;
	String modifier;
	List<UMLVariable> parameters;

	public UMLMethod(String scopeModifier, String returntype, String name) {
		this.methodName = name;
		this.returnType = returntype;
		this.modifier = scopeModifier;
		this.parameters = new ArrayList<UMLVariable>();
	}

	public UMLMethod(String scopeModifier, String returntype, String name,
			List<UMLVariable> v) {
		this.methodName = name;
		this.returnType = returntype;
		this.modifier = scopeModifier;
		this.parameters = v;
	}

	public void addVariable(UMLVariable v) {
		parameters.add(v);
	}

	public List<UMLVariable> getVariables() {
		return parameters;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getScopeModifier() {
		return modifier;
	}

	public void setScopeModifier(String scopeModifier) {
		this.modifier = scopeModifier;
	}

	public String toString() {

		String umlModifier = "";

		switch (modifier) {
		case (Constants.PUBLIC_RETURN_TYPE):
			umlModifier = "+";
			break;
		case (Constants.PRIVATE_RETURN_TYPE):
			umlModifier = "-";
			break;
		case (Constants.PROTECTED_RETURN_TYPE):
			umlModifier = "#";
			break;
		case (Constants.PACKAGE_RETURN_TYPE):
			umlModifier = "~";
			break;
		}

		String tmp = umlModifier + " " + methodName + "(";

		for (int i = 0; i < parameters.size(); i++) {
			tmp += parameters.get(i).toString();
			if (i != parameters.size() - 1)
				tmp += ", ";
		}
		tmp += ")" + " : " + returnType;

		return tmp;
	}

	public String generateSourceString() {
		String tmp = modifier + " " + returnType + " " + methodName + "(";
		for (int i = 0; i < parameters.size(); i++) {
			tmp += parameters.get(i).generateSourceString();
			if (i != parameters.size() - 1)
				tmp += ", ";
		}
		tmp += "){}";

		return tmp;
	}

	@Override
	public int compareTo(UMLMethod o) {
		return this.getMethodName().compareTo(o.getMethodName());
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("name", this.getMethodName());
		json.addProperty("returnType", this.getReturnType());
		json.addProperty("modifier", this.getScopeModifier());
		JsonArray jsonParameters = new JsonArray();
		parameters.stream().forEach(v -> {
			jsonParameters.add(v.toJson());
		});
		json.add("parameters", jsonParameters);
		
		return json;
	}
}
