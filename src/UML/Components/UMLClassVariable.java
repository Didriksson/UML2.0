package UML.Components;

public class UMLClassVariable extends UMLVariable{
    String modifiers;
    public UMLClassVariable(String scopeModifier, String type, String name) {
	super(type, name);
	this.modifiers =scopeModifier;
    }
    
    public String generateSourceString(){
	return modifiers + " " + super.generateSourceString();
    }
    
    public String toString(){
	
	String umlModify = "";
	switch(modifiers){
	case("public"):
	    umlModify = "+";
	    break;
	case("private"):
	    umlModify = "-";
	    break;
	case("protected"):
	    umlModify = "#";
	    break;
	}
	
	return umlModify +super.toString();
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setScopeModifier(String scopeModifier) {
        this.modifiers = scopeModifier;
    }
    

}
