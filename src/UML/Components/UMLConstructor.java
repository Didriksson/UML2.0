package UML.Components;

import java.io.Serializable;

public class UMLConstructor extends UMLMethod implements Serializable{

    public UMLConstructor(String scopeModifier, String name){
	super(scopeModifier, "", name);
    }

    //We do not want user to be allowed to change returntype of a constructor. Hide from user maybe? Composition?
    //Still, we want to be able to "treat" this constructor as a method at some point, as it is a method without returntype.
    public void setReturnType(String returnType) {
    }
}
