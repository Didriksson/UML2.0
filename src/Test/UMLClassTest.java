package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import UMLComponents.UMLClassVariable;
import UMLComponents.UMLComponent;
import UMLComponents.UMLMethod;
import UMLComponents.UMLVariable;

public class UMLClassTest {
    @Test
    public void methodTest() {
	UMLMethod method = new UMLMethod("public", "String", "getName");
	assertEquals("public String getName(){}", method.toString());
	
	method = new UMLMethod("public", "String", "setName");
	method.addVariable(new UMLVariable("String", "name"));
	assertEquals("public String setName(String name){}", method.toString());
    }
    
    @Test
    public void variableTest(){
	UMLVariable variable = new UMLVariable("int", "numberOfCars");
	assertEquals("int numberOfCars", variable.toString());
    }
    
    @Test
    public void classVariableTest(){
	UMLVariable variable = new UMLClassVariable("public","int", "numberOfCars");
	assertEquals("public int numberOfCars", variable.toString());
    }
    
    @Test
    public void classPrintTest(){
	UMLComponent umlClass = new UMLComponent("Animal", "class");
	assertEquals("public class Animal {\n"
		+ "}", umlClass.toString());

	umlClass.addVariable("public", "int", "legs");
	
	assertEquals("public class Animal {\n"+
		"public int legs;\n" +
		"}"
		,umlClass.toString());	

	
	try {
	    umlClass.addMethod(new UMLMethod("public", "String", "getAnimalName"));
	    List<UMLVariable> variables = new ArrayList<UMLVariable>();
	    variables.add(new UMLVariable("String", "name"));
	    umlClass.addMethod(new UMLMethod("public", "void", "setName", variables));
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	
	assertEquals("public class Animal {\n"+
		"public int legs;\n" +
		"public String getAnimalName(){}\n"+
		"public void setName(String name){}\n"+
		"}"
		,umlClass.toString());	
    }
    
    @Test
    public void exceptionThrownWhenAddingConstructorWithReturntype(){
	boolean exceptionThrownAndCaught = false;
	UMLComponent umlClass = new UMLComponent("Animal", "class");
	assertEquals("public class Animal {\n"
		+ "}", umlClass.toString());

	umlClass.addVariable("public", "int", "legs");
	assertEquals("public class Animal {\n"+
		"public int legs;\n" +
		"}"
		,umlClass.toString());
	
	
	try {
	    umlClass.addMethod(new UMLMethod("public", "String", "Animal"));
	} catch (Exception e) {
	    exceptionThrownAndCaught = true;
	}
	    assertTrue(exceptionThrownAndCaught);
	
    }
    
    
}
