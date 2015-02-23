package UMLComponents;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class UMLConverter {

    public static UMLComponent toUML(Class<?> class1) {
	return ClassToUML.toUML(class1);
    }
    
    public static void generateSource(UMLComponent component, String path) {
	GenerateSourceFromUML.generate(component, path);
    }

}
