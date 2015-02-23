package UMLComponents;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class ClassToUML {
    public static UMLComponent toUML(Class<?> class1) {
	UMLComponent uml = new UMLComponent(class1.getSimpleName(), "Class");
	getFields(class1, uml);
	getMethods(class1, uml);

	return uml;
    }

    private static void getMethods(Class<?> class1, UMLComponent uml) {
	for (Method m : class1.getDeclaredMethods()) {
	    UMLMethod method = new UMLMethod(
		    Modifier.toString(m.getModifiers()), m.getReturnType()
			    .getSimpleName(), m.getName());
	    if (!m.isSynthetic()) {
		addParameters(m, method);
		try {
		    uml.addMethod(method);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    private static void addParameters(Method m, UMLMethod method) {
	List<Parameter> parameters = Arrays.asList(m.getParameters());

	parameters.stream().forEach(
		p -> {
		    UMLVariable variable = new UMLVariable(p.getType()
			    .getSimpleName(), p.getName());
		    method.addVariable(variable);
		});
    }

    private static void getFields(Class<?> class1, UMLComponent uml) {
	for (Field f : class1.getDeclaredFields()) {
	    if (!f.isSynthetic()) {
		int modifiers = f.getModifiers();
		uml.addVariable(Modifier.toString(modifiers), f.getType()
			.getSimpleName(), f.getName());
	    }
	}
    }
}
