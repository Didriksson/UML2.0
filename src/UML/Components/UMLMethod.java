package UML.Components;

import java.util.ArrayList;
import java.util.List;

public class UMLMethod implements Comparable<UMLMethod> {
    String methodName;
    String returnType;
    String modifier;
    List<UMLVariable> variables;

    public UMLMethod(String scopeModifier, String returntype, String name) {
	this.methodName = name;
	this.returnType = returntype;
	this.modifier = scopeModifier;
	this.variables = new ArrayList<UMLVariable>();
    }

    public UMLMethod(String scopeModifier, String returntype, String name,
	    List<UMLVariable> v) {
	this.methodName = name;
	this.returnType = returntype;
	this.modifier = scopeModifier;
	this.variables = v;
    }

    public void addVariable(UMLVariable v) {
	variables.add(v);
    }

    public List<UMLVariable> getVariables() {
	return variables;
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
	case ("public"):
	    umlModifier = "+";
	    break;
	case ("private"):
	    umlModifier = "-";
	    break;
	case ("protected"):
	    umlModifier = "#";
	    break;
	}

	String tmp = umlModifier + " " + methodName + "(";
	
	for (int i = 0; i < variables.size(); i++) {
	    tmp += variables.get(i).toString();
	    if (i != variables.size() - 1)
		tmp += ", ";
	}
	tmp += ")";

	return tmp;
    }

    public String generateSourceString() {
	String tmp = modifier + " " + returnType + " " + methodName + "(";
	for (int i = 0; i < variables.size(); i++) {
	    tmp += variables.get(i).generateSourceString();
	    if (i != variables.size() - 1)
		tmp += ", ";
	}
	tmp += "){}";

	return tmp;
    }

    @Override
    public int compareTo(UMLMethod o) {
	return this.getMethodName().compareTo(o.getMethodName());
    }
}