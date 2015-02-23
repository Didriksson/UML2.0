package Test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import UML.Components.UMLComponent;
import UML.Components.UMLMethod;
import UML.Components.UMLVariable;
import UML.Utils.UMLConverter;

public class GenerateSourceTest {
    static String path = ".\\src\\GeneratedTestFiles\\";

    @Test
    public void testJavaFromUML() {
	
	UMLComponent uml = new UMLComponent("TestingClass", "class");
	try {
	    UMLMethod method = new UMLMethod("public", "void",
		    "setNumberOfNanting");
	    method.addVariable(new UMLVariable("int", "nanting"));
	    uml.addMethod(method);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	UMLConverter.generateSource(uml, path);
	File file = new File(path+uml.getName()+".java");
	assertTrue(file.exists());
    }

    
    
    @AfterClass
    public static void cleanUp(){
	File directory = new File(path);
	for(File file : directory.listFiles())
	    file.delete();
    }

    
}
