package UMLComponents;

public class UMLClassVariable extends UMLVariable{
    String modifiers;
    public UMLClassVariable(String scopeModifier, String type, String name) {
	super(type, name);
	this.modifiers =scopeModifier;
    }
    
    public String toString(){
	return modifiers + " " + super.toString();
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setScopeModifier(String scopeModifier) {
        this.modifiers = scopeModifier;
    }
    

}
