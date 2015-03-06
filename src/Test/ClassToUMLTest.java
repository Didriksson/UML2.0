package Test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Utils.LoadClassesFromPath;
import UML.Utils.UMLConverter;

public class ClassToUMLTest {

    @Test
    public void loadClassesFromPath() {
	String path = "\\Test\\TestClasses";
	List<Class<?>> classes = LoadClassesFromPath.loadClasses(path);
	
	assertEquals(1,classes.size());
	assertEquals("Test.TestClasses.ExampleClass",classes.get(0).getName());

    }
    
    @Test
    public void fromClassToUMLComponent(){
	String path = "\\Test\\TestClasses";
	List<Class<?>> classes = LoadClassesFromPath.loadClasses(path);
	UMLComponent uml = UMLConverter.toUML(classes.get(0));
	assertEquals(UMLComponent.class, uml.getClass());
	assertEquals("ExampleClass", uml.getName());
	
	assertEquals("testInt", uml.getVariable(0).getName());
	assertEquals("int", uml.getVariable(0).getType());
	assertEquals("public", uml.getVariable(0).getModifiers());

	List<UMLMethod> methods = uml.getMethods();
	Collections.sort(methods);
	
	
	assertEquals("testString", uml.getVariable(1).getName());
	assertEquals("private", uml.getVariable(1).getModifiers());
	
	assertEquals("testMethod", uml.getMethod(0).getMethodName());

	assertEquals("testMethodParameters", methods.get(1).getMethodName());
	assertEquals("String", methods.get(1).getVariables().get(0).getType());
	
    }

}
