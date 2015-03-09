package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import UML.Components.UMLClassVariable;
import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLVariable;

public class UMLClassTest {
    @Test
    public void methodTest() {
	UMLMethod method = new UMLMethod("public", "String", "getName");
	assertEquals("public String getName(){}", method.generateSourceString());
	
	method = new UMLMethod("public", "String", "setName");
	method.addParameter(new UMLVariable("String", "name"));
	assertEquals("public String setName(String name){}", method.generateSourceString());
    }
    
    @Test
    public void variableTest(){
	UMLVariable variable = new UMLVariable("int", "numberOfCars");
	assertEquals("int numberOfCars", variable.generateSourceString());
    }
    
    @Test
    public void classVariableTest(){
	UMLVariable variable = new UMLClassVariable("public","int", "numberOfCars");
	assertEquals("public int numberOfCars", variable.generateSourceString());
    }
    
    @Test
    public void umlPrintAsJavaCodeTest(){
	UMLComponent umlClass = new UMLComponent("Animal", "class");
	assertEquals("public class Animal {\n"
		+ "}", umlClass.generateSourceString());

	umlClass.addVariable("public", "int", "legs");
	
	assertEquals("public class Animal {\n"+
		"public int legs;\n" +
		"}"
		,umlClass.generateSourceString());	

	
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
		,umlClass.generateSourceString());	
    }
    
    @Test
    public void printClassAsUML(){
	UMLComponent umlClass = new UMLComponent("Animal", "class");
	umlClass.addVariable("public", "int", "legs");
	
	try {
	    umlClass.addMethod(new UMLMethod("public", "String", "getAnimalName"));
	    List<UMLVariable> variables = new ArrayList<UMLVariable>();
	    variables.add(new UMLVariable("String", "name"));
	    umlClass.addMethod(new UMLMethod("public", "void", "setName", variables));
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	
	assertEquals("Animal\n"
		+ "----------\n"
		+ "+legs : int\n"
		+ "----------\n"
		+ "+ getAnimalName()\n"
		+ "+ setName(name : String)\n"
		,umlClass.toString());	
    }
    
    @Test
    public void exceptionThrownWhenAddingConstructorWithReturntype(){
	boolean exceptionThrownAndCaught = false;
	UMLComponent umlClass = new UMLComponent("Animal", "class");
	assertEquals("public class Animal {\n"
		+ "}", umlClass.generateSourceString());

	umlClass.addVariable("public", "int", "legs");
	assertEquals("public class Animal {\n"+
		"public int legs;\n" +
		"}"
		,umlClass.generateSourceString());
	
	
	try {
	    umlClass.addMethod(new UMLMethod("public", "String", "Animal"));
	} catch (Exception e) {
	    exceptionThrownAndCaught = true;
	}
	    assertTrue(exceptionThrownAndCaught);
	
    }
    
    
}
