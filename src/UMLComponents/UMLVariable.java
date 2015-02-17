package UMLComponents;

public class UMLVariable {
    String variableName;
    String type;

    public UMLVariable(String type, String name) {
	this.variableName = name;
	this.type = type;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setvariableName(String methodName) {
        this.variableName = methodName;
    }

    public String toString(){
	return type + " " + variableName;
    }
}
