package UML.Components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UMLComponent implements Serializable{
	private String name;
	private String type;
	private List<UMLMethod> methods;
	private List<UMLClassVariable> variables;

	public UMLComponent(String name, String type) {
		this.name = name;
		this.type = type;
		methods = new ArrayList<UMLMethod>();
		variables = new ArrayList<UMLClassVariable>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UMLClassVariable> getVariables() {
		return variables;
	}

	public void addMethod(UMLMethod method) throws Exception {
		if (method.getMethodName().equals(this.name)
				&& !(method instanceof UMLConstructor))
			throw new Exception("Method should have type UMLConstructor."
					+ method);

		methods.add(method);
	}

	public void addVariable(String scopeModifier, String type, String name) {
		variables.add(new UMLClassVariable(scopeModifier, type, name));
	}

	public void addVariable(UMLClassVariable v) {
		variables.add(v);
	}

	public UMLMethod getMethod(int n) {
		return methods.get(n);
	}

	public List<UMLMethod> getMethods() {
		return methods;
	}

	public UMLClassVariable getVariable(int n) {
		return variables.get(n);
	}

	public String toString() {
		String tmp = getName() + "\n----------\n";

		for (UMLVariable v : variables) {
			tmp += v.toString() + "\n";
		}

		tmp += "----------\n";

		for (UMLMethod m : methods)
			tmp += m.toString() + "\n";

		return tmp;
	}

	public String generateSourceString() {
		String tmp = "";
		tmp += ("public " + type.toLowerCase() + " " + name + " {\n");
		for (UMLVariable v : variables) {
			tmp += v.generateSourceString() + ";" + "\n";
		}

		for (UMLMethod m : methods)
			tmp += m.generateSourceString() + "\n";
		tmp += "}";

		return tmp;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("name", name);
		json.addProperty("type", type);

		JsonArray jsonMethods = new JsonArray();
		methods.stream().forEach(m -> {
			jsonMethods.add(m.toJson());
		});

		json.add("methods", jsonMethods);

		JsonArray jsonVariables = new JsonArray();
		variables.stream().forEach(v -> {
			jsonVariables.add(v.toJson());
		});

		json.add("variables", jsonVariables);

		return json;
	}
}
