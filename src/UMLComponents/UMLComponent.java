package UMLComponents;

import java.util.ArrayList;
import java.util.List;

public class UMLComponent {
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
		String tmp = "";
		tmp += ("public " + type + " " + name + " {\n");
		for (UMLVariable v : variables) {
			tmp += v.toString() + ";" + "\n";
		}

		for (UMLMethod m : methods)
			tmp += m.toString() + "\n";
		tmp += "}";
		return tmp;
	}

	public List<UMLClassVariable> getVariables() {
		return variables;
	}

}
