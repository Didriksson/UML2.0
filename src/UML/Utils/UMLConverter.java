package UML.Utils;

import UML.Components.UMLComponent;

public class UMLConverter {

    public static UMLComponent toUML(Class<?> class1) {
	return ClassToUML.toUML(class1);
    }
    
    public static void generateSource(UMLComponent component, String path) {
	GenerateSource.generateFromComponent(component, path);
    }

}
