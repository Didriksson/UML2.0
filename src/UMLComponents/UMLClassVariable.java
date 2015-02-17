package UMLComponents;

public class UMLClassVariable extends UMLVariable{
    String scopeModifier;
    public UMLClassVariable(String scopeModifier, String type, String name) {
	super(type, name);
	this.scopeModifier =scopeModifier;
    }
    
    public String toString(){
	return scopeModifier + " " + super.toString();
    }

    public String getScopeModifier() {
        return scopeModifier;
    }

    public void setScopeModifier(String scopeModifier) {
        this.scopeModifier = scopeModifier;
    }
    

}
