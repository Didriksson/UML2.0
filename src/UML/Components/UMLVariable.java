package UML.Components;

public class UMLVariable {
    private String variableName;
    private String type;

    public UMLVariable(String type, String name) {
	this.variableName = name;
	this.type = type;
    }

    public String getName() {
        return variableName;
    }

    public void setvariableName(String methodName) {
        this.variableName = methodName;
    }

    public String toString(){
	return variableName +" : "+ type;
    }

    public String getType() {
	return this.type;
    }

    public String generateSourceString() {
	return type + " " + variableName;
    }
}
